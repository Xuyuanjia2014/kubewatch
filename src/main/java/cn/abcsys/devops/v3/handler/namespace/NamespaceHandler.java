package cn.abcsys.devops.v3.handler.namespace;

import cn.abcsys.devops.v2.deployer.db.dao.ProjectCoreMapper;
import cn.abcsys.devops.v3.handler.utils.ApplicationHandler;
import cn.abcsys.devops.v3.handler.utils.DateUtil;
import watch.SpringContextHelper;
import cn.abcsys.devops.v3.initialization.AllInit;
import cn.abcsys.devops.v3.initialization.HandlerInit;
import com.github.isdream.cwatcher.Handler;
import com.github.isdream.cwatcher.listeners.KubernetesListener;
import io.fabric8.kubernetes.api.model.Namespace;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class NamespaceHandler extends Handler {
    private ProjectCoreMapper projectCoreMapper = (ProjectCoreMapper) SpringContextHelper.getBean("projectCoreMapper");
    private static Logger logger = Logger.getLogger(NamespaceHandler.class);
    private HandlerInit contextData = null;

    @Override
    public void doHandle(Properties props) {
        insertOrGetProjectCoreIfNotExist(props);
        if (props.get(KubernetesListener.PROPERTY_ACTION).equals("DELETED")) {
            deleteProjectCore(props);
        }
    }

    private synchronized void insertOrGetProjectCoreIfNotExist(Properties props) {
        try {
            contextData = AllInit.contextMap.get(ApplicationHandler.getClientURL(props));
            Object obj = props.get("resource");
            Namespace ns = (Namespace) obj;

            Integer envId = null;
            String projectDesc = "";
            Map<String, String> labelMap = ns.getMetadata().getLabels();
            if (labelMap != null && labelMap.get("envId") != null) {
                envId = Integer.valueOf(labelMap.get("envId").toString());
            }
            if (ns.getMetadata().getAnnotations() != null && ns.getMetadata().getAnnotations().get("openshift.io/description") != null) {
                projectDesc = ns.getMetadata().getAnnotations().get("openshift.io/description").toString();
            }
            String createTime = "";
            try {
                if (ns.getMetadata() != null && !StringUtils.isEmpty(ns.getMetadata().getCreationTimestamp())) {
                    createTime = DateUtil.parseDateStr(ns.getMetadata().getCreationTimestamp(), "yyyy-MM-dd'T'HH:mm:ss'Z'");
                } else {
                    createTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                }
            } catch (ParseException e) {
                logger.error("ns " + ns.getMetadata().getName() + "get createTime, have exception:" + e.getMessage());
            }
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("namespace", ns.getMetadata().getName());
            paramMap.put("project_desc", projectDesc);
            paramMap.put("cluster_id", contextData.getCLUSTER_ID());
            paramMap.put("default_env_id", contextData.getENV_ID());
            paramMap.put("env_id", envId);
            paramMap.put("createtime", createTime);
            projectCoreMapper.insertOrGetProjectCore(paramMap);
            logger.info("ns " + ns.getMetadata().getName() + " stores to ProjectCore.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void deleteProjectCore(Properties props) {
        try {
            contextData = AllInit.contextMap.get(ApplicationHandler.getClientURL(props));
            Object obj = props.get("resource");
            Namespace ns = (Namespace) obj;
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("namespace", ns.getMetadata().getName());
            paramMap.put("cluster_id", contextData.getCLUSTER_ID());
            projectCoreMapper.deleteProjectCore(paramMap);
            props.put("namespace", ns.getMetadata().getName());
        } catch (Exception e) {
            logger.error(props.get("namespace") + " is ready to handle deleteProjectCore: have exception");
            e.printStackTrace();
        }
    }
}
