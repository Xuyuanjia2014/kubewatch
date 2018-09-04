package cn.abcsys.devops.v3.handler.pod;

import cn.abcsys.devops.v2.deployer.db.dao.ProjectCoreMapper;
import cn.abcsys.devops.v2.deployer.db.dao.V2ApplicationMapper;
import cn.abcsys.devops.v2.deployer.db.dao.V2VersionMapper;
import cn.abcsys.devops.v2.deployer.db.model.V2Application;
import cn.abcsys.devops.v3.handler.utils.ApplicationHandler;
import cn.abcsys.devops.v3.handler.utils.DateUtil;
import watch.SpringContextHelper;
import cn.abcsys.devops.v3.initialization.AllInit;
import cn.abcsys.devops.v3.initialization.HandlerInit;
import com.github.isdream.cwatcher.Handler;
import com.github.isdream.cwatcher.listeners.KubernetesListener;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.ReplicaSet;
import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import io.fabric8.kubernetes.api.model.batch.Job;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.openshift.api.model.DeploymentConfig;
import io.fabric8.openshift.client.OpenShiftClient;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 1. 注意加的logger的位置；
 * 2. 注意什么时候return
 */
public class PodApplicationHandler extends Handler {

    private static Logger logger = Logger.getLogger(PodApplicationHandler.class);

    private ProjectCoreMapper projectCoreMapper = (ProjectCoreMapper) SpringContextHelper.getBean("projectCoreMapper");
    private V2ApplicationMapper v2ApplicationMapper = (V2ApplicationMapper) SpringContextHelper.getBean("v2ApplicationMapper");
    private V2VersionMapper v2VersionMapper = (V2VersionMapper) SpringContextHelper.getBean("v2VersionMapper");

    private static V2ApplicationMapper applicationDao = (V2ApplicationMapper) SpringContextHelper.getBean("v2ApplicationMapper");

    @Override
    public void doHandle(Properties props) {
        initialOperation(props);
        if (!props.get(KubernetesListener.PROPERTY_ACTION).equals("DELETED")) {
            defaultNormal(props);
        }
    }

    public void initialOperation(Properties props) {
        HandlerInit contextData = AllInit.contextMap.get(ApplicationHandler.getClientURL(props));
        props.put("contextData", contextData);

        Object obj = props.get("resource");
        Pod pod = (Pod) obj;
        logger.info("Pod name:" + pod.getMetadata().getName() + "( in namespace " + pod.getMetadata().getNamespace() + ") has action:" + props.get(KubernetesListener.PROPERTY_ACTION));
        props.put("podName", pod.getMetadata().getName());
        props.put("namespace", pod.getMetadata().getNamespace());
    }

    public void defaultNormal(Properties props) {
        int i = 0;
        while (props.get("projectId") == null && i < 5) {
            checkProjectCore(props);
            if (props.get("projectId") != null) {
                break;
            } else {
                try {
                    Thread.sleep(10);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        int j = 0;
        while (props.get("applicationId") == null && j < 5) {
            getApplication(props);
            if (props.get("applicationId") != null) {
                break;
            } else {
                try {
                    Thread.sleep(10);
                    j++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        int k = 0;
        while (props.get("versionId") == null && k < 5) {
            getVersion(props);
            if (props.get("versionId") != null) {
                break;
            } else {
                try {
                    Thread.sleep(10);
                    k++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object getLabelValue(Pod pod, Object labelKey) {
        Map<String, String> labelMap = pod.getMetadata().getLabels();
        if (labelMap != null && labelMap.get(labelKey) != null) {
            return labelMap.get(labelKey).toString();
        }
        return "";
    }

    /**
     * replicatSet上级deployment，replicationController上级deploymentConfig，如果存在则保存最上级
     *
     * @param pod
     * @param client
     * @param controllerName
     */
    public static void setKindParamMap(Properties prop, Pod pod, KubernetesClient client, String controllerName, Map<String, Object> paramMap) {
        paramMap.put("kind", pod.getMetadata().getOwnerReferences().get(0).getKind());
        paramMap.put("controller_name", controllerName);
        DaemonSet daemonSet = client.extensions().daemonSets().inNamespace(pod.getMetadata().getNamespace())
                .withName(controllerName).get();
        Job job = client.extensions().jobs().inNamespace(pod.getMetadata().getNamespace())
                .withName(controllerName).get();
        ReplicationController rc = client.replicationControllers().inNamespace(pod.getMetadata().getNamespace())
                .withName(controllerName).get();
        ReplicaSet replicaSet = client.extensions().replicaSets().inNamespace(pod.getMetadata().getNamespace())
                .withName(controllerName).get();
        StatefulSet statefulSet = client.apps().statefulSets().inNamespace(pod.getMetadata().getNamespace())
                .withName(controllerName).get();

        if (daemonSet != null && paramMap.get("kind").equals("DaemonSet") || job != null && paramMap.get("kind").equals("Job")) {
            paramMap.put("replica", 1);//需修改要
        }
        if (rc != null && paramMap.get("kind").equals("ReplicationController")) {
            paramMap.put("replica", rc.getSpec().getReplicas());
        }
        if (replicaSet != null && paramMap.get("kind").equals("ReplicaSet")) {
            paramMap.put("replica", replicaSet.getSpec().getReplicas());
        }
        if (statefulSet != null && paramMap.get("kind").equals("StatefulSet")) {
            paramMap.put("replica", statefulSet.getSpec().getReplicas());
        }

        if (paramMap.get("kind").equals("ReplicationController") && controllerName.lastIndexOf("-") != -1 && prop.getProperty(KubernetesListener.PROPERTY_CLIENT_TYPE).equals("openshift")) {
            OpenShiftClient ocpClient = (OpenShiftClient) client;
            String probablyName = controllerName.substring(0, controllerName.lastIndexOf("-"));
            V2Application param = new V2Application();
            param.setApplicationName(probablyName);
            param.setProjectId((Integer) prop.get("projectId"));
            DeploymentConfig dc = ocpClient.deploymentConfigs().inNamespace(pod.getMetadata().getNamespace()).withName(probablyName).get();
            V2Application matchApplication = applicationDao.selectByMultipleFields(param);
            if (dc != null) {
                paramMap.put("kind", "DeploymentConfig");
                paramMap.put("replica", dc.getSpec().getReplicas());
                paramMap.put("controller_name", probablyName);
            } else if (dc == null && matchApplication != null) {
                paramMap.put("kind", "DeploymentConfig");
                paramMap.put("replica", 0);
                paramMap.put("controller_name", probablyName);
            }
        } else if (paramMap.get("kind").equals("ReplicaSet") && controllerName.lastIndexOf("-") != -1) {
            String probablyName = controllerName.substring(0, controllerName.lastIndexOf("-"));
            V2Application param = new V2Application();
            param.setApplicationName(probablyName);
            param.setProjectId((Integer) prop.get("projectId"));
            Deployment d = client.extensions().deployments().inNamespace(pod.getMetadata().getNamespace())
                    .withName(probablyName).get();
            V2Application matchApplication = applicationDao.selectByMultipleFields(param);
            if (d != null) {
                paramMap.put("kind", "Deployment");
                paramMap.put("replica", d.getSpec().getReplicas());
                paramMap.put("controller_name", probablyName);
            } else if (d == null && matchApplication != null) {
                paramMap.put("kind", "Deployment");
                paramMap.put("replica", 0);
                paramMap.put("controller_name", probablyName);
            }
        }
        if (paramMap.get("replica") == null) {
            paramMap.put("replica", 0);
        }
    }

    private synchronized void checkProjectCore(Properties props) {
        try {
            Pod pod = (Pod) props.get("resource");
            HandlerInit contextData = (HandlerInit) props.get("contextData");

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("namespace", pod.getMetadata().getNamespace());
            paramMap.put("cluster_id", contextData.getCLUSTER_ID());
            paramMap.put("env_id", null);
            paramMap.put("project_id", null);
            projectCoreMapper.checkProjectCore(paramMap);
            if (paramMap.get("env_id") == null) {
                logger.info("waiting project_core's namespace :" + pod.getMetadata().getNamespace() + " to insert");
                return;
            }
            props.put("envId", paramMap.get("env_id"));
            props.put("projectId", paramMap.get("project_id"));
            logger.info("Find projectId: " + paramMap.get("project_id") + " of pod :" + props.get("podName"));
        } catch (Exception e) {
            logger.error(props.get("podName") + " is ready to synchronize ProjectCore, have exception");
            e.printStackTrace();
        }
    }

    private synchronized void getApplication(Properties props) {
        Pod pod = (Pod) props.get("resource");
        try {
            if (props.get("projectId") == null) {
                return;
            }
            if (pod.getMetadata().getOwnerReferences() == null || pod.getMetadata().getOwnerReferences().size() == 0) {
                return;
            }
            String createTime = "";
            try {
                if (pod.getMetadata() != null && !StringUtils.isEmpty(pod.getMetadata().getCreationTimestamp())) {
                    createTime = DateUtil.parseDateStr(pod.getMetadata().getCreationTimestamp(), "yyyy-MM-dd'T'HH:mm:ss'Z'");
                } else {
                    createTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                }
            } catch (ParseException e) {
                logger.error(props.get("podName") + "get createTime, have exception");
            }
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("project_id", props.get("projectId"));
            paramMap.put("platform", props.get(KubernetesListener.PROPERTY_CLIENT_TYPE));
            paramMap.put("create_time", createTime);

            //先查询父亲的信息
            setKindParamMap(props, pod, (KubernetesClient) props.get(KubernetesListener.CLIENT), pod.getMetadata().getOwnerReferences().get(0).getName(), paramMap);

            //如果是本平台发的应用
            if (!StringUtils.isEmpty(getLabelValue(pod, "applicationName")) &&
                    StringUtils.isEmpty(getLabelValue(pod, "helmName"))) {
                props.put("imageGroupName", paramMap.get("controller_name"));
                paramMap.put("controller_name", getLabelValue(pod, "applicationName"));//本平台应用（label中有值）
                props.put("applicationName", getLabelValue(pod, "applicationName"));
                paramMap.put("affair_type", "boco");
                props.put("affairType", "boco");
            } else if (!StringUtils.isEmpty(getLabelValue(pod, "applicationName")) &&
                    !StringUtils.isEmpty(getLabelValue(pod, "helmName"))) {
                props.put("imageGroupName", paramMap.get("controller_name"));
                paramMap.put("controller_name", getLabelValue(pod, "applicationName"));//本平台应用（label中有值）
                props.put("applicationName", getLabelValue(pod, "applicationName"));
                paramMap.put("affair_type", "helm");
                props.put("affairType", "helm");
            }
            //如果是后台发的
            else {
                props.put("applicationName", paramMap.get("controller_name"));
                props.put("imageGroupName", paramMap.get("controller_name"));
                paramMap.put("affair_type", "others");
                props.put("affairType", "others");
            }
            v2ApplicationMapper.getApplication(paramMap);
            if (paramMap.get("application_id") == null) {
                logger.info("waiting v2_application of: " + props.get("podName") + " to insert");
                return;
            }
            props.put("applicationId", paramMap.get("application_id"));
            props.put("imageGroupKind", paramMap.get("kind"));
            props.put("imageGroupReplica", paramMap.get("replica"));
            logger.info(props.get("podName") + " 's applicationId:" + props.get("applicationId"));
        } catch (Exception e) {
            logger.error(props.get("podName") + " is ready to synchronize v2_application, have exception");
            e.printStackTrace();
        }
    }

    private synchronized void getVersion(Properties props) {
        logger.info(props.get("podName") + " pod is ready to synchronize v2_version:");
        Pod pod = (Pod) props.get("resource");
        try {
            if (props.get("applicationId") == null) {
                return;
            }
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("application_id", props.get("applicationId"));
            if (!StringUtils.isEmpty(getLabelValue(pod, "versionName"))) {
                paramMap.put("vname", getLabelValue(pod, "versionName"));//本平台应用（label中有值）
            } else {
                paramMap.put("vname", "v1");//ocp平台应用
            }
            v2VersionMapper.getVersion(paramMap);
            try {
                props.put("versionId", paramMap.get("version_id"));
                logger.info(props.get("podName") + " 's version_id:" + props.get("versionId"));
            } catch (Exception e) {
                logger.info(props.get("podName") + " will try again to find versionId.");
            }
        } catch (Exception e) {
            logger.error(props.get("podName") + " is ready to synchronize v2_version, have unknown exception");
            e.printStackTrace();
        }
    }

}
