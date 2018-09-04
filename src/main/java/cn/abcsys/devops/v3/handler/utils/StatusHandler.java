package cn.abcsys.devops.v3.handler.utils;

import cn.abcsys.devops.v2.deployer.db.dao.V2ImageGroupMapper;
import com.github.isdream.cwatcher.Handler;
import com.github.isdream.cwatcher.listeners.KubernetesListener;
import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.ReplicaSet;
import io.fabric8.openshift.api.model.DeploymentConfig;
import org.apache.log4j.Logger;
import watch.SpringContextHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class StatusHandler extends Handler {

    private static Logger logger = Logger.getLogger(StatusHandler.class);
    private V2ImageGroupMapper v2ImageGroupMapper = (V2ImageGroupMapper) SpringContextHelper.getBean("v2ImageGroupMapper");

    @Override
    public void doHandle(Properties props) {
        if (props.get(KubernetesListener.PROPERTY_ACTION).equals("DELETED")) {
            deleteVersionAndImageGroup(props);
            if (!ifBoco(props))
                deleteApplication(props);
        }
    }

    private Boolean ifBoco(Properties props) {
        Object resources = props.get("resource");
        if (resources instanceof Deployment) {
            return ((Deployment) resources).getMetadata().getLabels().containsKey("applicationName") && ((Deployment) resources).getMetadata().getLabels().containsKey("versionName") &&
                    ((Deployment) resources).getMetadata().getLabels().containsKey("envId");
        }
        if (resources instanceof DeploymentConfig) {
            return ((DeploymentConfig) resources).getMetadata().getLabels().containsKey("applicationName") && ((DeploymentConfig) resources).getMetadata().getLabels().containsKey("versionName") &&
                    ((DeploymentConfig) resources).getMetadata().getLabels().containsKey("envId");
        }
        if (resources instanceof ReplicationController) {
            return ((ReplicationController) resources).getMetadata().getLabels().containsKey("applicationName") && ((ReplicationController) resources).getMetadata().getLabels().containsKey("versionName") &&
                    ((ReplicationController) resources).getMetadata().getLabels().containsKey("envId");
        }
        if (resources instanceof ReplicaSet) {
            return ((ReplicaSet) resources).getMetadata().getLabels().containsKey("applicationName") && ((ReplicaSet) resources).getMetadata().getLabels().containsKey("versionName") &&
                    ((ReplicaSet) resources).getMetadata().getLabels().containsKey("envId");
        }
        if (resources instanceof DaemonSet) {
            return ((DaemonSet) resources).getMetadata().getLabels().containsKey("applicationName") && ((DaemonSet) resources).getMetadata().getLabels().containsKey("versionName") &&
                    ((DaemonSet) resources).getMetadata().getLabels().containsKey("envId");
        }
        return false;
    }

    private synchronized void deleteVersionAndImageGroup(Properties props) {
        logger.info(props.get("controllerHandlerName") + " is ready to handle deleteVersionAndImageGroup:");
        try {
            if (props.get("versionId") == null) {
                return;
            }
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("version_id", props.get("versionId"));
            v2ImageGroupMapper.deleteVersionAndImageGroup(paramMap);
            if (paramMap.get("ig_id") == null) {
                return;
            }
            props.put("igId", paramMap.get("ig_id"));
        } catch (Exception e) {
            logger.error(props.get("controllerHandlerName") + " is ready to handle deleteVersionAndImageGroup: have exception");
            e.printStackTrace();
        }
    }

    private synchronized void deleteApplication(Properties props) {
        logger.info(props.get("controllerHandlerName") + " is ready to handle deleteApplication:");
        try {
            if (props.get("applicationId") == null) {
                return;
            }
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("application_id", props.get("applicationId"));
            v2ImageGroupMapper.deleteApplication(paramMap);
            props.put("result", paramMap.get("result"));
        } catch (Exception e) {
            logger.error(props.get("controllerHandlerName") + " is ready to handle deleteApplication: have exception");
            e.printStackTrace();
        }
    }
}
