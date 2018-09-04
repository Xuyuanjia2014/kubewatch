package cn.abcsys.devops.v2.deployer.db.model;

import java.util.Date;

public class V2ImageGroup {
    private Integer id;

    private Integer versionId;

    private Integer applicationId;

    private Integer projectId;

    private Integer envId;

    private Integer versionGroupId;

    private String realName;

    private String uuid;

    private String imageGroupName;

    private String description;

    private String namespace;

    private String apiVersion;

    private String kind;

    private Integer replica;

    private String parentName;

    private String masterIp;

    private String masterType;

    private String masterPort;

    private Date createTime;

    private Date deleteTime;

    private Date updateTime;

    private String status;

    private String imageGroupStrategy;

    private Integer shrinkageImageGroupId;

    private Integer targetReplica;

    private Integer oldReplica;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getEnvId() {
        return envId;
    }

    public void setEnvId(Integer envId) {
        this.envId = envId;
    }

    public Integer getVersionGroupId() {
        return versionGroupId;
    }

    public void setVersionGroupId(Integer versionGroupId) {
        this.versionGroupId = versionGroupId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImageGroupName() {
        return imageGroupName;
    }

    public void setImageGroupName(String imageGroupName) {
        this.imageGroupName = imageGroupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getReplica() {
        return replica;
    }

    public void setReplica(Integer replica) {
        this.replica = replica;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getMasterIp() {
        return masterIp;
    }

    public void setMasterIp(String masterIp) {
        this.masterIp = masterIp;
    }

    public String getMasterType() {
        return masterType;
    }

    public void setMasterType(String masterType) {
        this.masterType = masterType;
    }

    public String getMasterPort() {
        return masterPort;
    }

    public void setMasterPort(String masterPort) {
        this.masterPort = masterPort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageGroupStrategy() {
        return imageGroupStrategy;
    }

    public void setImageGroupStrategy(String imageGroupStrategy) {
        this.imageGroupStrategy = imageGroupStrategy;
    }

    public Integer getShrinkageImageGroupId() {
        return shrinkageImageGroupId;
    }

    public void setShrinkageImageGroupId(Integer shrinkageImageGroupId) {
        this.shrinkageImageGroupId = shrinkageImageGroupId;
    }

    public Integer getTargetReplica() {
        return targetReplica;
    }

    public void setTargetReplica(Integer targetReplica) {
        this.targetReplica = targetReplica;
    }

    public Integer getOldReplica() {
        return oldReplica;
    }

    public void setOldReplica(Integer oldReplica) {
        this.oldReplica = oldReplica;
    }
}