package cn.abcsys.devops.v2.deployer.db.model;

/**
 * PV实体类
 *
 * @Author Zuxingyu zuxingyu<zuxingyu@beyondcent.com>
 * @Date 2018-02-08 14:21
 */
public class V2PersistentVolume {
    
    private Integer id;
    private String persistentVolumeName;
    private String persistentVolumeType;
    private String typeParams;
    private String storage;
    private String accessModes;
    private String persistentVolumeReclaimPolicy;
    private String status;
    
    public Integer getId () {
        return id;
    }
    
    public void setId (Integer id) {
        this.id = id;
    }
    
    public String getPersistentVolumeName () {
        return persistentVolumeName;
    }
    
    public void setPersistentVolumeName (String persistentVolumeName) {
        this.persistentVolumeName = persistentVolumeName;
    }
    
    public String getPersistentVolumeType () {
        return persistentVolumeType;
    }
    
    public void setPersistentVolumeType (String persistentVolumeType) {
        this.persistentVolumeType = persistentVolumeType;
    }
    
    public String getTypeParams () {
        return typeParams;
    }
    
    public void setTypeParams (String typeParams) {
        this.typeParams = typeParams;
    }
    
    public String getStorage () {
        return storage;
    }
    
    public void setStorage (String storage) {
        this.storage = storage;
    }
    
    public String getAccessModes () {
        return accessModes;
    }
    
    public void setAccessModes (String accessModes) {
        this.accessModes = accessModes;
    }
    
    public String getPersistentVolumeReclaimPolicy () {
        return persistentVolumeReclaimPolicy;
    }
    
    public void setPersistentVolumeReclaimPolicy (String persistentVolumeReclaimPolicy) {
        this.persistentVolumeReclaimPolicy = persistentVolumeReclaimPolicy;
    }
    
    public String getStatus () {
        return status;
    }
    
    public void setStatus (String status) {
        this.status = status;
    }
}
