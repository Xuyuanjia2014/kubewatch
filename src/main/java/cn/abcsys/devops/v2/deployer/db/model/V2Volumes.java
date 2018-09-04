package cn.abcsys.devops.v2.deployer.db.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class V2Volumes {
    private Integer id;

    private Integer imageGroupId;

    private String hostPath;

    private String volumeName;

    private String volumeType;

    private Date createTime;

    private Integer versionId;

    private Integer applicationId;

    private Integer projectId;

    private Integer envId;

    private String status;

    private String configMapName;
    private List<Map<String,String>> configMapItem;

    private String pvcClaimName;

    public String getConfigMapName() {
        return configMapName;
    }

    public void setConfigMapName(String configMapName) {
        this.configMapName = configMapName;
    }

    public List<Map<String, String>> getConfigMapItem() {
        return configMapItem;
    }

    public void setConfigMapItem(List<Map<String, String>> configMapItem) {
        this.configMapItem = configMapItem;
    }

    public String getPvcClaimName() {
        return pvcClaimName;
    }

    public void setPvcClaimName(String pvcClaimName) {
        this.pvcClaimName = pvcClaimName;
    }

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

    public String getHostPath() {
        return hostPath;
    }

    public void setHostPath(String hostPath) {
        this.hostPath = hostPath == null ? null : hostPath.trim();
    }

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName == null ? null : volumeName.trim();
    }

    public String getVolumeType() {
        return volumeType;
    }

    public void setVolumeType(String volumeType) {
        this.volumeType = volumeType == null ? null : volumeType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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