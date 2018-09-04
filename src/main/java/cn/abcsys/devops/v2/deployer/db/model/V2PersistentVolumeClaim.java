package cn.abcsys.devops.v2.deployer.db.model;

/**
 * @Describe PVC实体类
 * @Author Zuxingyu zuxingyu<zuxingyu@beyondcent.com>
 * @Date 2018/02/09
 */
public class V2PersistentVolumeClaim {
    private Integer id;
    private String persistentVolumeClaimName;
    private String persistentVolumeName;
    private Integer persistentVolumeId;
    private Integer projectId;
    private String projectName;
    private String accessModes;
    private String status;
    
    public Integer getId () {
        return id;
    }
    
    public void setId (Integer id) {
        this.id = id;
    }
    
    public String getPersistentVolumeClaimName () {
        return persistentVolumeClaimName;
    }
    
    public void setPersistentVolumeClaimName (String persistentVolumeClaimName) {
        this.persistentVolumeClaimName = persistentVolumeClaimName;
    }
    
    public String getPersistentVolumeName () {
        return persistentVolumeName;
    }
    
    public void setPersistentVolumeName (String persistentVolumeName) {
        this.persistentVolumeName = persistentVolumeName;
    }
    
    public Integer getPersistentVolumeId () {
        return persistentVolumeId;
    }
    
    public void setPersistentVolumeId (Integer persistentVolumeId) {
        this.persistentVolumeId = persistentVolumeId;
    }
    
    public Integer getProjectId () {
        return projectId;
    }
    
    public void setProjectId (Integer projectId) {
        this.projectId = projectId;
    }
    
    public String getAccessModes () {
        return accessModes;
    }
    
    public void setAccessModes (String accessModes) {
        this.accessModes = accessModes;
    }
    
    public String getStatus () {
        return status;
    }
    
    public void setStatus (String status) {
        this.status = status;
    }
    
    public String getProjectName () {
        return projectName;
    }
    
    public void setProjectName (String projectName) {
        this.projectName = projectName;
    }
}
