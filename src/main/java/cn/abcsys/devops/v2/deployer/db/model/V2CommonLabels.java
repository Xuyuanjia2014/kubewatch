package cn.abcsys.devops.v2.deployer.db.model;

import cn.abcsys.devops.v2.deployer.db.inerfaces.ILabels;

public class V2CommonLabels implements ILabels {
    private Integer id;

    private Integer commonId;

    private String labelType;

    private String labelKey;

    private String labelValue;

    private String status;

    public V2CommonLabels (String labelType, String labelKey, String labelValue) {
        this.labelType = labelType;
        this.labelKey = labelKey;
        this.labelValue = labelValue;
    }

    public V2CommonLabels (Integer commonId, String labelType, String labelKey, String labelValue) {
        this.commonId = commonId;
        this.labelType = labelType;
        this.labelKey = labelKey;
        this.labelValue = labelValue;
    }

    public V2CommonLabels (){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getCommonId () {
        return commonId;
    }
    
    public void setCommonId (Integer commonId) {
        this.commonId = commonId;
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