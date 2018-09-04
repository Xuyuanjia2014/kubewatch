package cn.abcsys.devops.v3.handler.deployment;

import cn.abcsys.devops.v3.handler.utils.ApplicationHandler;
import cn.abcsys.devops.v3.initialization.AllInit;
import cn.abcsys.devops.v3.initialization.HandlerInit;
import com.github.isdream.cwatcher.Handler;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import org.apache.log4j.Logger;

import java.util.Properties;

public class DeploymentApplicationHandler extends Handler {
    private static Logger logger = Logger.getLogger(DeploymentApplicationHandler.class);

    @Override
    public void doHandle(Properties props) {
        HandlerInit contextData = AllInit.contextMap.get(ApplicationHandler.getClientURL(props));
        props.put("contextData", contextData);
        Object obj = props.get("resource");
        Deployment deployment = (Deployment) obj;
        props.put("resourceKind",deployment.getKind());

        ApplicationHandler applicationHandler = new ApplicationHandler();
        applicationHandler.doHandle(deployment.getMetadata(), props);
    }
}