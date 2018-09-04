package cn.abcsys.devops.v3.handler.pod;

import cn.abcsys.devops.v2.deployer.db.dao.V2ImageGroupMapper;
import cn.abcsys.devops.v3.handler.utils.DateUtil;
import watch.SpringContextHelper;
import cn.abcsys.devops.v3.initialization.HandlerInit;
import com.github.isdream.cwatcher.Handler;
import com.github.isdream.cwatcher.listeners.KubernetesListener;
import io.fabric8.kubernetes.api.model.Pod;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PodConfigurationHandler extends Handler {

    private static Logger logger = Logger.getLogger(PodConfigurationHandler.class);
    private V2ImageGroupMapper v2ImageGroupMapper = (V2ImageGroupMapper) SpringContextHelper.getBean("v2ImageGroupMapper");

    @Override
    public void doHandle(Properties props) {
        if(!props.get(KubernetesListener.PROPERTY_ACTION).equals("DELETED")) {
            getImageGroup(props);
        }
    }

    private synchronized void getImageGroup(Properties props) {
        //logger.info(props.get("podName") + " is ready to synchronize v2_image_group:");
        Pod pod = (Pod) props.get("resource");
        HandlerInit contextData = (HandlerInit) props.get("contextData");
        try {
            if (props.get("versionId") == null) {
                return;
            }
            Map<String, String> labelMap = pod.getMetadata().getLabels();
            String createTime = "";
            try {
                if (pod.getMetadata() != null && !StringUtils.isEmpty(pod.getMetadata().getCreationTimestamp())) {
                    createTime = DateUtil.parseDateStr(pod.getMetadata().getCreationTimestamp(), "yyyy-MM-dd'T'HH:mm:ss'Z'");
                }
            } catch (ParseException e) {
                logger.error(props.get("podName") + "get createTime, have exception:" + e.getMessage());
            }
            Map<String, Object> paramMap = new HashMap<>();
            String controllerName = pod.getMetadata().getOwnerReferences().get(0).getName();
            paramMap.put("version_id", props.get("versionId"));
            paramMap.put("application_id", props.get("applicationId"));
            paramMap.put("project_id", props.get("projectId"));
            paramMap.put("env_id", props.get("envId"));

            paramMap.put("uuid", pod.getMetadata().getOwnerReferences().get(0).getUid());
            paramMap.put("namespace", pod.getMetadata().getNamespace());
            paramMap.put("apiversion", pod.getMetadata().getOwnerReferences().get(0).getApiVersion());

            paramMap.put("app_name", props.get("applicationName"));//ocp平台应用
            paramMap.put("base_name", props.get("imageGroupName"));
            paramMap.put("real_name", props.get("imageGroupName"));
            paramMap.put("kind", props.get("imageGroupKind"));
            paramMap.put("replica", props.get("imageGroupReplica"));
            paramMap.put("ip_host", contextData.getMASTER_IP());
            paramMap.put("if_http", contextData.getMASTER_TYPE());
            paramMap.put("api_port", contextData.getMASTER_PORT());
            paramMap.put("createtime", createTime);
            v2ImageGroupMapper.getImageGroup(paramMap);
            if (paramMap.get("ig_id") == null) {
                return;
            }
            props.put("igId", paramMap.get("ig_id"));
            logger.info(props.get("podName") + " 's image_group_id:" + props.get("igId"));
        } catch (Exception e) {
            logger.error(props.get("podName") + " is ready to synchronize v2_image_group, have exception");
            e.printStackTrace();
        }
    }
}
