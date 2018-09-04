package cn.abcsys.devops.v2.deployer.db.model;

import java.util.Date;

public class V2Image {
    private Integer id;

    private Integer imageGroupId;

    private String containerName;

    private String imageName;

    private String imageTag;

    private String imageType;

    private String pullSecret;

    private Date storageTime;

    private Date buildTime;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImageGroupId() {
        return imageGroupId;
    }

    public void setImageGroupId(Integer imageGroupId) {
        this.imageGroupId = imageGroupId;
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

    public String getImageTag() {
        return imageTag;
    }

    public void setImageTag(String imageTag) {
        this.imageTag = imageTag == null ? null : imageTag.trim();
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType == null ? null : imageType.trim();
    }

    public String getPullSecret() {
        return pullSecret;
    }

    public void setPullSecret(String pullSecret) {
        this.pullSecret = pullSecret == null ? null : pullSecret.trim();
    }

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}