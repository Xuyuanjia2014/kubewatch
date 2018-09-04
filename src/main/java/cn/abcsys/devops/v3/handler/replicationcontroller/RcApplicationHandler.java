package cn.abcsys.devops.v3.handler.replicationcontroller;

import cn.abcsys.devops.v3.handler.utils.ApplicationHandler;
import cn.abcsys.devops.v3.initialization.AllInit;
import cn.abcsys.devops.v3.initialization.HandlerInit;
import com.github.isdream.cwatcher.Handler;
import io.fabric8.kubernetes.api.model.ReplicationController;
import org.apache.log4j.Logger;

import java.util.Properties;

public class RcApplicationHandler extends Handler {
    private static Logger logger = Logger.getLogger(RcApplicationHandler.class);

    @Override
    public void doHandle(Properties props) {
        HandlerInit contextData = AllInit.contextMap.get(ApplicationHandler.getClientURL(props));
        props.put("contextData", contextData);
        Object obj = props.get("resource");
        ReplicationController rc = (ReplicationController) obj;
        if (rc.getMetadata().getOwnerReferences() != null && rc.getMetadata().getOwnerReferences().size() > 0) {
            return;
        }
        props.put("resourceKind",rc.getKind());
        ApplicationHandler applicationHandler = new ApplicationHandler();
        applicationHandler.doHandle(rc.getMetadata(), props);
    }
}