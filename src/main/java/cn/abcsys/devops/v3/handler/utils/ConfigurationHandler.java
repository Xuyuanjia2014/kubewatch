package cn.abcsys.devops.v3.handler.utils;

import cn.abcsys.devops.v2.deployer.db.dao.ApplicationEventsMapper;
import cn.abcsys.devops.v2.deployer.db.dao.V2ImageGroupMapper;
import cn.abcsys.devops.v2.deployer.db.model.ApplicationEvents;
import cn.abcsys.devops.v3.initialization.HandlerInit;
import com.github.isdream.cwatcher.listeners.KubernetesListener;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import watch.SpringContextHelper;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigurationHandler {
    private static Logger logger = Logger.getLogger(ConfigurationHandler.class);
    private V2ImageGroupMapper v2ImageGroupMapper = (V2ImageGroupMapper) SpringContextHelper.getBean("v2ImageGroupMapper");
    private ApplicationEventsMapper applicationEventsMapper = (ApplicationEventsMapper) SpringContextHelper.getBean("applicationEventsMapper");

    public void doHandle(ObjectMeta meta, String apiVersion, Properties props) {
        if (!props.get(KubernetesListener.PROPERTY_ACTION).equals("DELETED")) {
            insertOrUpdateImageGroup(meta, apiVersion, props);
        }
    }

    private synchronized void doGreyParameters(ObjectMeta meta, Map<String, Object> parameter) {
        if (meta.getLabels() != null && meta.getLabels().containsKey("imageGroupStrategy")) {
            parameter.put("image_group_strategy", meta.getLabels().get("imageGroupStrategy"));
        }
        if (meta.getLabels() != null && meta.getLabels().containsKey("shrinkageImageGroupId")) {
            parameter.put("shrinkage_image_group_id", Integer.valueOf(meta.getLabels().get("shrinkageImageGroupId")));
        }
        if (meta.getLabels() != null && meta.getLabels().containsKey("targetReplica")) {
            parameter.put("target_replica", Integer.valueOf(meta.getLabels().get("targetReplica")));
        }
        if (meta.getLabels() != null && meta.getLabels().containsKey("oldReplica")) {
            parameter.put("old_replica", Integer.valueOf(meta.getLabels().get("oldReplica")));
        }
    }

    private synchronized void insertOrUpdateImageGroup(ObjectMeta meta, String apiVersion, Properties props) {
        logger.info(props.get("controllerHandlerName") + " is ready to synchronize v2_image_group:");
        try {
            HandlerInit contextData = (HandlerInit) props.get("contextData");
            if (props.get("versionId") == null) {
                return;
            }
            Map<String, String> labelMap = meta.getLabels();
            String createTime = "";
            try {
                if (meta != null && !StringUtils.isEmpty(meta.getCreationTimestamp())) {
                    createTime = DateUtil.parseDateStr(meta.getCreationTimestamp(), "yyyy-MM-dd'T'HH:mm:ss'Z'");
                } else {
                    createTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                }
            } catch (ParseException e) {
                logger.error(props.get("controllerHandlerName") + "get createTime, have exception:" + e.getMessage());
            }
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("version_id", props.get("versionId"));
            paramMap.put("application_id", props.get("applicationId"));
            paramMap.put("project_id", props.get("projectId"));
            paramMap.put("env_id", props.get("envId"));
            paramMap.put("uuid", meta.getUid());
            paramMap.put("namespace", meta.getNamespace());
            paramMap.put("apiversion", apiVersion);
            paramMap.put("app_name", props.get("applicationName"));//ocp平台应用
            paramMap.put("base_name", props.get("imageGroupName"));
            paramMap.put("real_name", props.get("imageGroupName"));
            paramMap.put("kind", props.get("imageGroupKind"));
            paramMap.put("replica", props.get("imageGroupReplica"));
            paramMap.put("ip_host", contextData.getMASTER_IP());
            paramMap.put("if_http", contextData.getMASTER_TYPE());
            paramMap.put("api_port", contextData.getMASTER_PORT());
            paramMap.put("createtime", createTime);

            doGreyParameters(meta, paramMap);

            v2ImageGroupMapper.insertOrUpdateImageGroup(paramMap);
            if (paramMap.get("ig_id") == null) {
                return;
            }
            props.put("igId", paramMap.get("ig_id"));
            logger.info(props.get("controllerHandlerName") + " 's image_group_id:" + props.get("igId"));
        } catch (MySQLIntegrityConstraintViolationException e) {
            logger.error("Duplicate entry for key 'img_index'");
            return;
        } catch (Exception e) {
            logger.error(props.get("controllerHandlerName") + " is ready to synchronize v2_image_group, have exception");
            e.printStackTrace();
        }
    }

    public Map<String, Object> addEventInEnglish(int applicationCoreId, String type, String description, String description2, Integer envId) {
        Map<String, Object> resMap = new HashMap<>();
        ApplicationEvents ae = new ApplicationEvents();
        ae.setApplicationCoreId(applicationCoreId);
        ae.setApplicationEventDescription(description);
        ae.setApplicationEventDescription2(description2);
        ae.setApplicationEventStartDatetime(new Date());
        ae.setApplicationEventType(type);
        ae.setEnvId(envId);
        ae.setApplicationEventCurrentStatus("Done");

        if (applicationEventsMapper.selectCountByApplicationEvent(ae) == 0) {
            applicationEventsMapper.insertSelective(ae);
        }

        resMap.put("success", true);
        resMap.put("message", "add one event,applicationEventId:" + ae.getApplicationEventId());
        return resMap;
    }
}
