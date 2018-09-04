package cn.abcsys.devops.v2.deployer.db.model;

import io.fabric8.kubernetes.api.model.Event;

public class V2Event {
    private Integer id;

    private String apiVersion;

    private Integer versionId;

    private Integer count;

    private String firstTimestamp;

    private String kind;

    private String message;

    private String name;

    private String namespace;

    private String resourceVersion;

    private String selfLink;

    private String uid;

    private String reason;

    private String host;

    private String type;

    public V2Event(){

    }

    public V2Event(Event event){
        this.apiVersion = event.getApiVersion();
        this.count = event.getCount();
        this.firstTimestamp = event.getFirstTimestamp();
        this.kind = event.getKind();
        this.message = event.getMessage();
        this.name = event.getMetadata().getName();
        this.namespace = event.getMetadata().getNamespace();
        this.resourceVersion = event.getMetadata().getResourceVersion();
        this.selfLink = event.getMetadata().getSelfLink();
        this.uid = event.getMetadata().getUid();
        this.reason = event.getReason();
        this.host = event.getSource().getHost();
        this.type = event.getType();
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion == null ? null : apiVersion.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getFirstTimestamp() {
        return firstTimestamp;
    }

    public void setFirstTimestamp(String firstTimestamp) {
        this.firstTimestamp = firstTimestamp == null ? null : firstTimestamp.trim();
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace == null ? null : namespace.trim();
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion == null ? null : resourceVersion.trim();
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink == null ? null : selfLink.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}