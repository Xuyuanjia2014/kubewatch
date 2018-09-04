package cn.abcsys.devops.v2.deployer.db.model;

import cn.abcsys.devops.v2.deployer.db.objects.DeploymentComponent;

import java.util.Date;

public class V2Pod {
    private Integer id;

    private String uuid;

    private Integer imageGroupId;

    private String filePath;

    private String kind;

    private Integer replacePodId;

    private Integer replaceOldParentId;

    private Integer ifDeleteParent;

    private String parentName;

    private String realName;

    private String namespace;

    private String apiVersion;

    private String resourceVersion;

    private Date updateTime;

    private String status;

    private Integer deploymentId;

    public V2Pod(){

    }

    public V2Pod(DeploymentComponent component){
        this.imageGroupId = component.getImageGroup().getId();
        this.kind = component.getImageGroup().getKind();
        this.namespace = component.getImageGroup().getNamespace();
        this.apiVersion = component.getImageGroup().getApiVersion();
        this.parentName = component.getImageGroup().getRealName();
        this.updateTime = new Date();
        this.status = "creating";
    }


    /**
     * 灰度发布仅仅添加一个 replaceOldParentId 字段即可，标记处需要替换的 imageGroup 的 id
     * @param component
     * @param oldComponent
     */
    public V2Pod(DeploymentComponent component,DeploymentComponent oldComponent){  //灰度发布需要用到
        this.imageGroupId = component.getImageGroup().getId();
        this.kind = component.getImageGroup().getKind();
        this.namespace = component.getImageGroup().getNamespace();
        this.apiVersion = component.getImageGroup().getApiVersion();
        this.parentName = component.getImageGroup().getRealName();
        this.updateTime = new Date();
        // replaceOldParentId 在这里打标记后 watcher 才会认为该 pod 是灰度发布来的 pod，才回去删除目标 deployment？
        this.replaceOldParentId = oldComponent.getImageGroup().getId();
        this.status = "creating";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getImageGroupId() {
        return imageGroupId;
    }

    public void setImageGroupId(Integer imageGroupId) {
        this.imageGroupId = imageGroupId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getReplacePodId() {
        return replacePodId;
    }

    public void setReplacePodId(Integer replacePodId) {
        this.replacePodId = replacePodId;
    }

    public Integer getReplaceOldParentId() {
        return replaceOldParentId;
    }

    public void setReplaceOldParentId(Integer replaceOldParentId) {
        this.replaceOldParentId = replaceOldParentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
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

    public Integer getDeploymentId() {
        return this.deploymentId;
    }

    public void setDeploymentId(Integer deploymentId) {
        this.deploymentId = deploymentId;
    }

    public Integer getIfDeleteParent() {
        return ifDeleteParent;
    }

    public void setIfDeleteParent(Integer ifDeleteParent) {
        this.ifDeleteParent = ifDeleteParent;
    }
}