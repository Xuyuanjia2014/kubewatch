package cn.abcsys.devops.v3.handler.pod;

import cn.abcsys.devops.v2.deployer.db.dao.V2ContainerMapper;
import cn.abcsys.devops.v2.deployer.db.dao.V2ImageGroupMapper;
import cn.abcsys.devops.v2.deployer.db.dao.V2PodMapper;
import cn.abcsys.devops.v3.handler.utils.DateUtil;
import watch.SpringContextHelper;
import cn.abcsys.devops.v3.initialization.HandlerInit;
import com.github.isdream.cwatcher.Handler;
import com.github.isdream.cwatcher.listeners.KubernetesListener;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.openshift.client.OpenShiftClient;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.*;

public class PodStatusHandler extends Handler {

    private static Logger logger = Logger.getLogger(PodStatusHandler.class);
    private V2ImageGroupMapper v2ImageGroupMapper = (V2ImageGroupMapper) SpringContextHelper.getBean("v2ImageGroupMapper");
    private V2PodMapper v2PodMapper = (V2PodMapper) SpringContextHelper.getBean("v2PodMapper");
    private V2ContainerMapper v2ContainerMapper = (V2ContainerMapper) SpringContextHelper.getBean("v2ContainerMapper");

    @Override
    public void doHandle(Properties props) {
        insertOrUpdatePod(props);
        insertOrUpdateContainer(props);
        if (props.get(KubernetesListener.PROPERTY_ACTION).equals("DELETED")) {
            deletePodAndContainers(props);
        }
        if (props.get("applicationId") == null && !props.get(KubernetesListener.PROPERTY_ACTION).equals("DELETED")) {
            logger.info("Pod name : " + props.get("podName") + " will neglect, for the reason that its top owner-reference has not been watched.");
        }
    }

    private synchronized void insertOrUpdatePod(Properties props) {
        Pod pod = (Pod) props.get("resource");
        try {
            if (props.get("igId") == null) {
                return;
            }
            Map<String, Object> paramMap = new HashMap<>();
            String controllerName = pod.getMetadata().getOwnerReferences().get(0).getName();
            if (!StringUtils.isEmpty(PodApplicationHandler.getLabelValue(pod, "applicationName"))) {
                paramMap.put("parent_name", controllerName);
            } else {
                paramMap.put("parent_name", controllerName);//ocp平台应用
            }

            paramMap.put("parent_name", props.get("imageGroupName"));
            paramMap.put("image_group_id", props.get("igId"));
            paramMap.put("uuid", pod.getMetadata().getUid());
            paramMap.put("kind", pod.getKind());
            paramMap.put("real_name", pod.getMetadata().getName());
            paramMap.put("namespace", pod.getMetadata().getNamespace());
            paramMap.put("api_version", pod.getApiVersion());
            paramMap.put("resource_version", pod.getMetadata().getResourceVersion());
            paramMap.put("pod_status", pod.getStatus().getPhase());
            //pod.getStatus().getContainerStatuses().get(0).getState().getRunning().getStartedAt();
            //pod.getStatus().getContainerStatuses().get(0).getState().getWaiting().getReason();
            //pod.getStatus().getContainerStatuses().get(0).getState().getTerminated().getReason();
            v2ImageGroupMapper.insertOrUpdatePod(paramMap);
            props.put("podId", paramMap.get("pod_id"));
        } catch (Exception e) {
            logger.error(props.get("podName") + " is ready to synchronize v2_pod, have exception");
            e.printStackTrace();
        }
    }

    private String checkAndGetReason(String reason, String phase) {
        if (reason == null)
            return phase;
        if (reason.length() > 0)
            return reason;
        return phase;
    }

    private List<Node> getAllNodes(Properties props) {
        if (props.get(KubernetesListener.PROPERTY_CLIENT_TYPE).equals("openshift")) {
            return ((OpenShiftClient) props.get(KubernetesListener.CLIENT)).nodes().list().getItems();
        }
        return ((KubernetesClient) props.get(KubernetesListener.CLIENT)).nodes().list().getItems();
    }

    private String getHostNameByIp(String ip, Properties props) {
        try {
            List<Node> nodes = this.getAllNodes(props);
            if (nodes != null && nodes.size() >= 0) {
                for (Node temp : nodes) {
                    if (temp.getStatus().getAddresses() != null) {
                        String tIp = null, tName = null;
                        for (NodeAddress add : temp.getStatus().getAddresses()) {
                            if (add.getType().equals("InternalIP")) {
                                tIp = add.getAddress();
                            }
                            if (add.getType().equals("Hostname")) {
                                tName = add.getAddress();
                            }
                        }
                        if (tIp != null && tName != null && ip != null && ip.equals(tIp)) {
                            return tName;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    private synchronized void insertOrUpdateContainer(Properties props) {
        //logger.info(props.get("podName") + " is ready to synchronize v2_containers:");
        Pod pod = (Pod) props.get("resource");
        HandlerInit contextData = (HandlerInit) props.get("contextData");
        try {
            if (props.get("podId") == null) {
                return;
            }
            Map<String, Object> paramMap = new HashMap<>();
            for (int i = 0; pod.getSpec().getContainers() != null && i < pod.getSpec().getContainers().size(); i++) {
                Container container = pod.getSpec().getContainers().get(i);
                String createTime = "";
                try {
                    if (pod.getMetadata() != null && !StringUtils.isEmpty(pod.getMetadata().getCreationTimestamp())) {
                        createTime = DateUtil.parseDateStr(pod.getMetadata().getCreationTimestamp(), "yyyy-MM-dd'T'HH:mm:ss'Z'");
                    } else {
                        createTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                    }
                } catch (ParseException e) {
                    logger.error(props.get("podName") + "get createTime, have exception:" + e.getMessage());
                }
                String startDatetime = "";
                try {
                    if (pod.getStatus() != null && !StringUtils.isEmpty(pod.getStatus().getStartTime())) {
                        startDatetime = DateUtil.parseDateStr(pod.getStatus().getStartTime(), "yyyy-MM-dd'T'HH:mm:ss'Z'");
                    } else {
                        startDatetime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                    }
                } catch (ParseException e) {
                    logger.error(props.get("podName") + "get startDatetime, have exception:" + e.getMessage());
                }
                paramMap.put("pod_id", props.get("podId"));
                paramMap.put("image_group_id", props.get("igId"));
                paramMap.put("version_id", props.get("versionId"));
                paramMap.put("application_id", props.get("applicationId"));
                paramMap.put("project_id", props.get("projectId"));
                paramMap.put("env_id", props.get("envId"));
                paramMap.put("uuid", pod.getMetadata().getUid());
                paramMap.put("pod_name", pod.getMetadata().getName());
                paramMap.put("real_name", container.getName());
                paramMap.put("image_name_tag", container.getImage());

                StringBuffer sb = new StringBuffer("");
                List<ContainerStatus> containerStatusList = pod.getStatus().getContainerStatuses();
                if (containerStatusList != null && i <= containerStatusList.size() - 1) {
                    ContainerStatus currentStatus = containerStatusList.get(i);
                    if (currentStatus.getState() != null && currentStatus.getState().getRunning() != null) {
                        sb.append("Running");
                    } else if (currentStatus.getState() != null && currentStatus.getState().getTerminated() != null) {
                        sb.append(this.checkAndGetReason(currentStatus.getState().getTerminated().getReason(), "Terminated"));
                    } else if (currentStatus.getState() != null && currentStatus.getState().getWaiting() != null) {
                        sb.append(this.checkAndGetReason(currentStatus.getState().getWaiting().getReason(), "Waiting"));
                    } else {
                        sb.append(pod.getStatus().getPhase());
                    }
                    sb.append("-");

                    if (currentStatus.getReady() != null) {
                        sb.append(currentStatus.getReady().toString());
                    } else {
                        sb.append("false");
                    }
                } else {
                    sb.append(pod.getStatus().getPhase() + "-false");
                }
                paramMap.put("container_status", sb.toString());
                paramMap.put("container_name", "needed to be implemented.");
                if (container.getResources() != null) {
                    if (container.getResources().getLimits() != null && container.getResources().getLimits().get("cpu") != null) {
                        paramMap.put("limit_cpu", container.getResources().getLimits().get("cpu").getAmount());
                    } else {
                        paramMap.put("limit_cpu", "");
                    }
                    if (container.getResources().getLimits() != null && container.getResources().getLimits().get("memory") != null) {
                        paramMap.put("limit_memory", container.getResources().getLimits().get("memory").getAmount());
                    } else {
                        paramMap.put("limit_memory", "");
                    }
                    if (container.getResources().getRequests() != null && container.getResources().getRequests().get("cpu") != null) {
                        paramMap.put("request_cpu", container.getResources().getRequests().get("cpu").getAmount());
                    } else {
                        paramMap.put("request_cpu", "");
                    }
                    if (container.getResources().getRequests() != null && container.getResources().getRequests().get("memory") != null) {
                        paramMap.put("request_memory", container.getResources().getRequests().get("memory").getAmount());
                    } else {
                        paramMap.put("request_memory", "");
                    }
                }
                paramMap.put("host_ip", pod.getStatus().getHostIP());
                paramMap.put("host_name", this.getHostNameByIp(pod.getStatus().getHostIP(), props));
                paramMap.put("create_datetime", createTime);
                paramMap.put("start_datetime", startDatetime);
                paramMap.put("project_name", pod.getMetadata().getNamespace());
                v2ImageGroupMapper.insertOrUpdateContainer(paramMap);
            }
        } catch (Exception e) {
            logger.error(props.get("podName") + " is ready to synchronize v2_containers, have exception");
            e.printStackTrace();
        }
    }

    private synchronized void deletePodAndContainers(Properties props) {
        logger.info(props.get("podName") + " is ready to handle deletePodAndContainers:");
        Pod pod = (Pod) props.get("resource");
        try {
            v2ContainerMapper.deleteContainerByUuidAndPodName(pod.getMetadata().getUid(), pod.getMetadata().getName());
            v2PodMapper.deletePodByUuidAndRealName(pod.getMetadata().getUid(), pod.getMetadata().getName());
        } catch (Exception e) {
            logger.error(props.get("podName") + " is ready to handle deletePodAndContainers: have exception");
            e.printStackTrace();
        }
    }
}
