package cn.abcsys.devops.v3.handler.utils;

import cn.abcsys.devops.v2.deployer.db.dao.ApplicationEventsMapper;
import cn.abcsys.devops.v2.deployer.db.dao.ProjectCoreMapper;
import cn.abcsys.devops.v2.deployer.db.dao.V2ApplicationMapper;
import cn.abcsys.devops.v2.deployer.db.dao.V2VersionMapper;
import cn.abcsys.devops.v2.deployer.db.model.ApplicationEvents;
import cn.abcsys.devops.v3.initialization.HandlerInit;
import com.github.isdream.cwatcher.listeners.KubernetesListener;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.ReplicaSet;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.openshift.api.model.DeploymentConfig;
import io.fabric8.openshift.client.OpenShiftClient;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import watch.SpringContextHelper;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApplicationHandler {
    private static Logger logger = Logger.getLogger(ApplicationHandler.class);
    private static ProjectCoreMapper projectCoreMapper = (ProjectCoreMapper) SpringContextHelper.getBean("projectCoreMapper");
    private V2ApplicationMapper v2ApplicationMapper = (V2ApplicationMapper) SpringContextHelper.getBean("v2ApplicationMapper");
    private V2VersionMapper v2VersionMapper = (V2VersionMapper) SpringContextHelper.getBean("v2VersionMapper");
    private ApplicationEventsMapper applicationEventsMapper = (ApplicationEventsMapper) SpringContextHelper.getBean("applicationEventsMapper");

    public static String getClientURL(Properties props) {
        if (props.get(KubernetesListener.PROPERTY_CLIENT_TYPE).equals("kubernetes")) {
            return ((KubernetesClient) props.get(KubernetesListener.CLIENT)).getMasterUrl().getHost();
        } else if (props.get(KubernetesListener.PROPERTY_CLIENT_TYPE).equals("openshift")) {
            return ((OpenShiftClient) props.get(KubernetesListener.CLIENT)).getMasterUrl().getHost();
        }
        return null;
    }

    public void doHandle(ObjectMeta meta, Properties props) {
        logger.info(meta.getName() + "( in namespace " + meta.getNamespace() + ") has action:" + props.get(KubernetesListener.PROPERTY_ACTION) + " Type:" + props.get("resourceKind"));
        props.put("controllerHandlerName", meta.getName());
        props.put("namespace", meta.getNamespace());
        int i = 0;
        while (props.get("projectId") == null && i < 5) {
            checkProjectCore(props);
            if (props.get("projectId") != null) {
                break;
            } else {
                try {
                    Thread.sleep(1000);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        insertOrUpdateApplication(meta, props);
        insertOrGetVersion(meta, props);
    }

    private synchronized void checkProjectCore(Properties props) {
        try {
            HandlerInit contextData = (HandlerInit) props.get("contextData");
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("namespace", props.get("namespace"));
            paramMap.put("cluster_id", contextData.getCLUSTER_ID());
            paramMap.put("env_id", null);
            paramMap.put("project_id", null);
            projectCoreMapper.checkProjectCore(paramMap);
            if (paramMap.get("env_id") == null) {
                logger.info("waiting project_core's namespace :" + props.get("namespace") + " to insert");
                return;
            }
            props.put("envId", paramMap.get("env_id"));
            props.put("projectId", paramMap.get("project_id"));
            logger.info("Find projectId: " + paramMap.get("project_id") + " of " + props.get("controllerHandlerName"));
        } catch (Exception e) {
            logger.error(props.get("controllerHandlerName") + " is ready to synchronize ProjectCore, have exception");
            e.printStackTrace();
        }
    }

    private static Object getLabelValue(ObjectMeta meta, Object labelKey) {
        Map<String, String> labelMap = meta.getLabels();
        if (labelMap != null && labelMap.get(labelKey) != null) {
            return labelMap.get(labelKey).toString();
        }
        return "";
    }

    public String getType(String applicationType) {
        String type = "";
        switch (applicationType) {
            case "throughCicd":
                type = "CICD";
                break;
            case "throughImage":
                type = "镜像";
                break;
            case "throughYaml":
                type = "导入YAML";
                break;
            case "throughHelm":
                type = "组件仓库";
                break;
            default:
                type = "K8S后台";
                break;
        }
        return type;
    }

    private synchronized void insertOrUpdateApplication(ObjectMeta meta, Properties props) {
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("project_id", props.get("projectId"));
            paramMap.put("platform", props.get(KubernetesListener.PROPERTY_CLIENT_TYPE));

            if (props.get("projectId") == null) {
                return;
            }
            createParamMap(meta, props, paramMap);
            v2ApplicationMapper.insertOrUpdateApplication(paramMap);
            props.put("applicationId", paramMap.get("application_id"));
            props.put("imageGroupKind", paramMap.get("kind"));
            props.put("imageGroupReplica", paramMap.get("replica"));
            if (props.get("affairType") != null && !props.get("affairType").equals("helm")) {
                addEventInEnglish(Integer.valueOf(paramMap.get("application_id").toString()),
                        "CreateApplication",
                        "成功通过" + getType(props.get("applicationType").toString()) + "创建应用：" + props.get("applicationName"),
                        "Create watch.Application Success:  " + props.get("applicationName"),
                        Integer.valueOf(props.get("envId").toString()));
            }
            logger.info(props.get("controllerHandlerName") + " 's applicationId:" + props.get("applicationId"));
        } catch (Exception e) {
            logger.error(props.get("controllerHandlerName") + " is ready to synchronize v2_application, have exception");
            e.printStackTrace();
        }
    }

    private void createParamMap(ObjectMeta meta, Properties props, Map<String, Object> paramMap) {
        String createTime = "";
        try {
            if (meta != null && !StringUtils.isEmpty(meta.getCreationTimestamp())) {
                createTime = DateUtil.parseDateStr(meta.getCreationTimestamp(), "yyyy-MM-dd'T'HH:mm:ss'Z'");
            } else {
                createTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            }
        } catch (ParseException e) {
            logger.error(props.get("controllerHandlerName") + "get createTime, have exception");
        }
        paramMap.put("create_time", createTime);
        paramMap.put("controller_name", meta.getName());
        Object object = props.get("resource");
        if (object instanceof Deployment) {
            Deployment deployment = (Deployment) object;
            paramMap.put("kind", "Deployment");
            paramMap.put("replica", deployment.getSpec().getReplicas());
        } else if (object instanceof DeploymentConfig) {
            DeploymentConfig deploymentConfig = (DeploymentConfig) object;
            paramMap.put("kind", "DeploymentConfig");
            paramMap.put("replica", deploymentConfig.getSpec().getReplicas());
        } else if (object instanceof ReplicaSet) {
            ReplicaSet rs = (ReplicaSet) object;
            paramMap.put("kind", "ReplicaSet");
            paramMap.put("replica", rs.getSpec().getReplicas());
        } else if (object instanceof ReplicationController) {
            ReplicationController rc = (ReplicationController) object;
            paramMap.put("kind", "ReplicationController");
            paramMap.put("replica", rc.getSpec().getReplicas());
        } else if (object instanceof DaemonSet) {
            DaemonSet ds = (DaemonSet) object;
            paramMap.put("kind", "DaemonSet");
            paramMap.put("replica", 1);
        }
        //如果是本平台发的应用（镜像、CICD）
        if (!StringUtils.isEmpty(getLabelValue(meta, "applicationName"))
                && StringUtils.isEmpty(getLabelValue(meta, "helmName"))) {
            props.put("imageGroupName", paramMap.get("controller_name"));
            paramMap.put("controller_name", getLabelValue(meta, "applicationName"));//本平台应用（label中有值）
            props.put("applicationName", getLabelValue(meta, "applicationName"));
            paramMap.put("affair_type", "boco");
            props.put("affairType", "boco");
            props.put("applicationType", getLabelValue(meta, "applicationType"));
        } else if (!StringUtils.isEmpty(getLabelValue(meta, "applicationName"))
                && !StringUtils.isEmpty(getLabelValue(meta, "helmName"))) {
            props.put("imageGroupName", paramMap.get("controller_name"));
            paramMap.put("controller_name", getLabelValue(meta, "applicationName"));//本平台应用（label中有值）
            props.put("applicationName", getLabelValue(meta, "applicationName"));
            paramMap.put("affair_type", "helm");
            props.put("affairType", "helm");
            props.put("applicationType", getLabelValue(meta, "applicationType"));
        }
        //如果是后台发的，导入YAML
        else {
            props.put("applicationName", paramMap.get("controller_name"));
            props.put("imageGroupName", paramMap.get("controller_name"));
            paramMap.put("affair_type", "others");
            props.put("affairType", "others");
            props.put("applicationType", getLabelValue(meta, "applicationType"));
        }
    }

    private synchronized void insertOrGetVersion(ObjectMeta meta, Properties props) {
        try {
            if (props.get("applicationId") == null) {
                return;
            }
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("application_id", props.get("applicationId"));
            if (!StringUtils.isEmpty(getLabelValue(meta, "versionName"))) {
                paramMap.put("vname", getLabelValue(meta, "versionName"));//本平台应用（label中有值）
            } else {
                paramMap.put("vname", "v1");//ocp平台应用
            }
            paramMap.put("vtype", String.valueOf(props.get("imageGroupKind")));
            v2VersionMapper.insertOrGetVersion(paramMap);
            if (props.get("affairType") != null && !props.get("affairType").equals("helm")) {
                addEventInEnglish(Integer.valueOf(paramMap.get("application_id").toString()),
                        "CreateVersion",
                        "应用" + props.get("applicationName") + "成功创建版本：" + paramMap.get("vname"),
                        "Create " + props.get("applicationName") + "'s Version: " + props.get("applicationName") + " Success",
                        Integer.valueOf(props.get("envId").toString()));
            }
            props.put("versionId", paramMap.get("version_id"));
            logger.info(props.get("controllerHandlerName") + " 's version_id:" + props.get("versionId"));
        } catch (Exception e) {
            logger.error(props.get("controllerHandlerName") + " is ready to synchronize v2_version, have exception");
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
