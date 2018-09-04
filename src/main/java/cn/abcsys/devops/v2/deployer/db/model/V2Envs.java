package cn.abcsys.devops.v2.deployer.db.model;

import java.util.Date;

public class V2Envs {
    private Integer id;

    private Integer imageId;

    private String envsKey;

    private String envsValue;

    private String configMapName;

    private String configMapKey;

    private Date createTime;

    private String status;

    public String getConfigMapName() {
        return configMapName;
    }

    public void setConfigMapName(String configMapName) {
        this.configMapName = configMapName;
    }

    public String getConfigMapKey() {
        return configMapKey;
    }

    public void setConfigMapKey(String configMapKey) {
        this.configMapKey = configMapKey;
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

    public String getEnvsKey() {
        return envsKey;
    }

    public void setEnvsKey(String envsKey) {
        this.envsKey = envsKey;
    }

    public String getEnvsValue() {
        return envsValue;
    }

    public void setEnvsValue(String envsValue) {
        this.envsValue = envsValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}