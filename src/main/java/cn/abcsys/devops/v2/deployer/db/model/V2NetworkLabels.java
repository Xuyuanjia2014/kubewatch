package cn.abcsys.devops.v2.deployer.db.model;

import cn.abcsys.devops.v2.deployer.db.inerfaces.ILabels;

public class V2NetworkLabels implements ILabels{
    private Integer id;

    private Integer networkPolicyId;

    private String labelType;

    private String labelKey;

    private String labelValue;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNetworkPolicyId() {
        return networkPolicyId;
    }

    public void setNetworkPolicyId(Integer networkPolicyId) {
        this.networkPolicyId = networkPolicyId;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}