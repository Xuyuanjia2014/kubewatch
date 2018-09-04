package cn.abcsys.devops.v2.deployer.db.model;

import cn.abcsys.devops.v2.deployer.db.inerfaces.ILabels;

public class V2Labels implements ILabels {
    private Integer id;

    private Integer imageGroupId;

    private String labelType;

    private String labelKey;

    private String labelValue;

    private String status;

    public V2Labels(String labelType, String labelKey, String labelValue) {
        this.labelType = labelType;
        this.labelKey = labelKey;
        this.labelValue = labelValue;
    }

    public V2Labels(Integer imageGroupId,String labelType, String labelKey, String labelValue) {
        this.imageGroupId = imageGroupId;
        this.labelType = labelType;
        this.labelKey = labelKey;
        this.labelValue = labelValue;
    }

    public V2Labels(){

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

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType == null ? null : labelType.trim();
    }

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey == null ? null : labelKey.trim();
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue == null ? null : labelValue.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}