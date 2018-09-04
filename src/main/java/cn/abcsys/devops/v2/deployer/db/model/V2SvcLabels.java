package cn.abcsys.devops.v2.deployer.db.model;

import cn.abcsys.devops.v2.deployer.db.inerfaces.ILabels;

public class V2SvcLabels implements ILabels{
    private Integer id;

    private Integer svcId;

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

    public Integer getSvcId() {
        return svcId;
    }

    public void setSvcId(Integer svcId) {
        this.svcId = svcId;
    }

    @Override
    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    @Override
    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    @Override
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