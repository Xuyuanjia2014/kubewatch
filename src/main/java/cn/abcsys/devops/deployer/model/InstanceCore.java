package cn.abcsys.devops.deployer.model;

import java.util.Date;

public class InstanceCore {
    private Integer instanceCoreId;

    private String instanceName;

    private String instanceRename;

    private String instanceProerties;

    private String instanceNamespace;

    private String instanceType;

    private Date instanceCreateDatetime;

    private Date instanceDeleteDatetime;

    private String instanceCurrentStatus;

    private String instanceImage;

    private String instanceImageTag;

    private String instanceCpu;

    private String instanceMemory;

    private String instanceRequestCpu;

    private String instanceRequestMemory;

    private String instanceNetwork;

    private String instanceImagePullSecret;

    private Integer appId;

    private String appName;

    private String clusterName;

    private String hostName;

    private String hostIp;

    private String containerStatus;

    private String containerName;

    private String imageName;

    private String ip;

    private String cmd;

    private Integer applicationRuntimeId;

    private Integer applicationTenantId;

    private String podName;

    private String clusterIp;

    private Integer componentId;

    public Integer instanceReplica;

    private String masterPort;

    private String masterType;

    public String getInstanceRequestCpu() {
        return instanceRequestCpu;
    }

    public void setInstanceRequestCpu(String instanceRequestCpu) {
        this.instanceRequestCpu = instanceRequestCpu;
    }

    public String getInstanceRequestMemory() {
        return instanceRequestMemory;
    }

    public void setInstanceRequestMemory(String instanceRequestMemory) {
        this.instanceRequestMemory = instanceRequestMemory;
    }

    public String getClusterIp() {
        return clusterIp;
    }

    public void setClusterIp(String clusterIp) {
        this.clusterIp = clusterIp;
    }

    public String getPodName() {
        return podName;
    }

    public void setPodName(String podName) {
        this.podName = podName;
    }

    public Integer getInstanceCoreId() {
        return instanceCoreId;
    }

    public void setInstanceCoreId(Integer instanceCoreId) {
        this.instanceCoreId = instanceCoreId;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName == null ? null : instanceName.trim();
    }

    public String getInstanceRename() {
        return instanceRename;
    }

    public void setInstanceRename(String instanceRename) {
        this.instanceRename = instanceRename == null ? null : instanceRename.trim();
    }

    public String getInstanceProerties() {
        return instanceProerties;
    }

    public void setInstanceProerties(String instanceProerties) {
        this.instanceProerties = instanceProerties == null ? null : instanceProerties.trim();
    }

    public String getInstanceNamespace() {
        return instanceNamespace;
    }

    public void setInstanceNamespace(String instanceNamespace) {
        this.instanceNamespace = instanceNamespace == null ? null : instanceNamespace.trim();
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType == null ? null : instanceType.trim();
    }

    public Date getInstanceCreateDatetime() {
        return instanceCreateDatetime;
    }

    public void setInstanceCreateDatetime(Date instanceCreateDatetime) {
        this.instanceCreateDatetime = instanceCreateDatetime;
    }

    public Date getInstanceDeleteDatetime() {
        return instanceDeleteDatetime;
    }

    public void setInstanceDeleteDatetime(Date instanceDeleteDatetime) {
        this.instanceDeleteDatetime = instanceDeleteDatetime;
    }

    public String getInstanceCurrentStatus() {
        return instanceCurrentStatus;
    }

    public void setInstanceCurrentStatus(String instanceCurrentStatus) {
        this.instanceCurrentStatus = instanceCurrentStatus == null ? null : instanceCurrentStatus.trim();
    }

    public String getInstanceImage() {
        return instanceImage;
    }

    public void setInstanceImage(String instanceImage) {
        this.instanceImage = instanceImage == null ? null : instanceImage.trim();
    }

    public String getInstanceImageTag() {
        return instanceImageTag;
    }

    public void setInstanceImageTag(String instanceImageTag) {
        this.instanceImageTag = instanceImageTag == null ? null : instanceImageTag.trim();
    }

    public String getInstanceCpu() {
        return instanceCpu;
    }

    public void setInstanceCpu(String instanceCpu) {
        this.instanceCpu = instanceCpu == null ? null : instanceCpu.trim();
    }

    public String getInstanceMemory() {
        return instanceMemory;
    }

    public void setInstanceMemory(String instanceMemory) {
        this.instanceMemory = instanceMemory == null ? null : instanceMemory.trim();
    }

    public String getInstanceNetwork() {
        return instanceNetwork;
    }

    public void setInstanceNetwork(String instanceNetwork) {
        this.instanceNetwork = instanceNetwork == null ? null : instanceNetwork.trim();
    }

    public String getInstanceImagePullSecret() {
        return instanceImagePullSecret;
    }

    public void setInstanceImagePullSecret(String instanceImagePullSecret) {
        this.instanceImagePullSecret = instanceImagePullSecret == null ? null : instanceImagePullSecret.trim();
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName == null ? null : clusterName.trim();
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName == null ? null : hostName.trim();
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp == null ? null : hostIp.trim();
    }

    public String getContainerStatus() {
        return containerStatus;
    }

    public void setContainerStatus(String containerStatus) {
        this.containerStatus = containerStatus == null ? null : containerStatus.trim();
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName == null ? null : containerName.trim();
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName == null ? null : imageName.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd == null ? null : cmd.trim();
    }

    public Integer getApplicationRuntimeId() {
        return applicationRuntimeId;
    }

    public void setApplicationRuntimeId(Integer applicationRuntimeId) {
        this.applicationRuntimeId = applicationRuntimeId;
    }

    public Integer getApplicationTenantId() {
        return applicationTenantId;
    }

    public void setApplicationTenantId(Integer applicationTenantId) {
        this.applicationTenantId = applicationTenantId;
    }

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public Integer getInstanceReplica() {
        return instanceReplica;
    }

    public void setInstanceReplica(Integer instanceReplica) {
        this.instanceReplica = instanceReplica;
    }

    public String getMasterPort() {
        return masterPort;
    }

    public void setMasterPort(String masterPort) {
        this.masterPort = masterPort;
    }

    public String getMasterType() {
        return masterType;
    }

    public void setMasterType(String masterType) {
        this.masterType = masterType;
    }
}