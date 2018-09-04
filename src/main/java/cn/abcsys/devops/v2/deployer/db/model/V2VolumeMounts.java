package cn.abcsys.devops.v2.deployer.db.model;

import java.util.Date;

public class V2VolumeMounts {
    private Integer id;

    private Integer imageId;

    private String mountPath;

    private String volumeMountName;

    private Date createTime;

    private Integer imageGroupId;

    private Integer versionId;

    private Integer applicationId;

    private Integer projectId;

    private Integer envId;

    private String status;

    private String subPath;

    private Boolean readOnly;

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getMountPath() {
        return mountPath;
    }

    public void setMountPath(String mountPath) {
        this.mountPath = mountPath == null ? null : mountPath.trim();
    }

    public String getVolumeMountName() {
        return volumeMountName;
    }

    public void setVolumeMountName(String volumeMountName) {
        this.volumeMountName = volumeMountName == null ? null : volumeMountName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getImageGroupId() {
        return imageGroupId;
    }

    public void setImageGroupId(Integer imageGroupId) {
        this.imageGroupId = imageGroupId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}