package cn.abcsys.devops.v3.handler.replicaset;

import cn.abcsys.devops.v3.handler.utils.ApplicationHandler;
import cn.abcsys.devops.v3.initialization.AllInit;
import cn.abcsys.devops.v3.initialization.HandlerInit;
import com.github.isdream.cwatcher.Handler;
import io.fabric8.kubernetes.api.model.apps.ReplicaSet;
import org.apache.log4j.Logger;

import java.util.Properties;

public class RsApplicationHandler extends Handler {
    private static Logger logger = Logger.getLogger(RsApplicationHandler.class);

    @Override
    public void doHandle(Properties props) {
        HandlerInit contextData = AllInit.contextMap.get(ApplicationHandler.getClientURL(props));
        props.put("contextData", contextData);
        Object obj = props.get("resource");
        ReplicaSet rs = (ReplicaSet) obj;
        props.put("resourceKind",rs.getKind());
        if (rs.getMetadata().getOwnerReferences() != null && rs.getMetadata().getOwnerReferences().size() > 0) {
            return;
        }
        ApplicationHandler applicationHandler = new ApplicationHandler();
        applicationHandler.doHandle(rs.getMetadata(), props);
    }
}