package cn.abcsys.devops.v3.handler.daemonset;

import cn.abcsys.devops.v3.handler.utils.ApplicationHandler;
import cn.abcsys.devops.v3.initialization.AllInit;
import cn.abcsys.devops.v3.initialization.HandlerInit;
import com.github.isdream.cwatcher.Handler;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import org.apache.log4j.Logger;

import java.util.Properties;

public class DsApplicationHandler extends Handler {
    private static Logger logger = Logger.getLogger(DsApplicationHandler.class);


    @Override
    public void doHandle(Properties props) {
        HandlerInit contextData = AllInit.contextMap.get(ApplicationHandler.getClientURL(props));
        props.put("contextData", contextData);
        Object obj = props.get("resource");
        DaemonSet ds = (DaemonSet) obj;
        props.put("resourceKind", ds.getKind());
        ApplicationHandler applicationHandler = new ApplicationHandler();
        applicationHandler.doHandle(ds.getMetadata(), props);
    }
}