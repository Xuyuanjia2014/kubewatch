package cn.abcsys.devops.deployer.model;

import java.util.Date;

public class InstanceEnvs {
    private Integer instanceEnvsId;

    private Integer instanceCoreId;

    private String instanceEnvsName;

    private String instanceEnvsValue;

    private String instanceEnvsDescription;

    private Date instanceEnvsCreateDatetime;

    private Date instanceEnvsDeleteDatetime;

    private String instanceEnvsCurrentStatus;

    public Integer getInstanceEnvsId() {
        return instanceEnvsId;
    }

    public void setInstanceEnvsId(Integer instanceEnvsId) {
        this.instanceEnvsId = instanceEnvsId;
    }

    public Integer getInstanceCoreId() {
        return instanceCoreId;
    }

    public void setInstanceCoreId(Integer instanceCoreId) {
        this.instanceCoreId = instanceCoreId;
    }

    public String getInstanceEnvsName() {
        return instanceEnvsName;
    }

    public void setInstanceEnvsName(String instanceEnvsName) {
        this.instanceEnvsName = instanceEnvsName == null ? null : instanceEnvsName.trim();
    }

    public String getInstanceEnvsValue() {
        return instanceEnvsValue;
    }

    public void setInstanceEnvsValue(String instanceEnvsValue) {
        this.instanceEnvsValue = instanceEnvsValue == null ? null : instanceEnvsValue.trim();
    }

    public String getInstanceEnvsDescription() {
        return instanceEnvsDescription;
    }

    public void setInstanceEnvsDescription(String instanceEnvsDescription) {
        this.instanceEnvsDescription = instanceEnvsDescription == null ? null : instanceEnvsDescription.trim();
    }

    public Date getInstanceEnvsCreateDatetime() {
        return instanceEnvsCreateDatetime;
    }

    public void setInstanceEnvsCreateDatetime(Date instanceEnvsCreateDatetime) {
        this.instanceEnvsCreateDatetime = instanceEnvsCreateDatetime;
    }

    public Date getInstanceEnvsDeleteDatetime() {
        return instanceEnvsDeleteDatetime;
    }

    public void setInstanceEnvsDeleteDatetime(Date instanceEnvsDeleteDatetime) {
        this.instanceEnvsDeleteDatetime = instanceEnvsDeleteDatetime;
    }

    public String getInstanceEnvsCurrentStatus() {
        return instanceEnvsCurrentStatus;
    }

    public void setInstanceEnvsCurrentStatus(String instanceEnvsCurrentStatus) {
        this.instanceEnvsCurrentStatus = instanceEnvsCurrentStatus == null ? null : instanceEnvsCurrentStatus.trim();
    }
}