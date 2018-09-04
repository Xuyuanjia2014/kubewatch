package cn.abcsys.devops.v3.handler.deploymentconfig;

import cn.abcsys.devops.v3.handler.utils.ApplicationHandler;
import cn.abcsys.devops.v3.initialization.AllInit;
import cn.abcsys.devops.v3.initialization.HandlerInit;
import com.github.isdream.cwatcher.Handler;
import io.fabric8.openshift.api.model.DeploymentConfig;
import org.apache.log4j.Logger;

import java.util.Properties;

public class DcApplicationHandler extends Handler {
    private static Logger logger = Logger.getLogger(DcApplicationHandler.class);

    @Override
    public void doHandle(Properties props) {
        HandlerInit contextData = AllInit.contextMap.get(ApplicationHandler.getClientURL(props));
        props.put("contextData", contextData);
        Object obj = props.get("resource");
        DeploymentConfig deploymentConfig = (DeploymentConfig) obj;
        props.put("resourceKind",deploymentConfig.getKind());
        ApplicationHandler applicationHandler = new ApplicationHandler();
        applicationHandler.doHandle(deploymentConfig.getMetadata(), props);
    }

}
