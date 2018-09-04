package cn.abcsys.devops.v2.deployer.db.model;

import java.util.Date;

public class ApplicationEvents {
    private Integer applicationEventId;

    private Integer applicationCoreId;

    private String applicationEventType;

    private String applicationEventCurrentStatus;

    private String applicationEventPolicy;

    private Date applicationEventStartDatetime;

    private Date applicationEventFinishDatetime;

    private String applicationEventDescription;

    private Integer envId;

    private String applicationEventDescription2;

    public String getApplicationEventDescription2() {
        return applicationEventDescription2;
    }

    public void setApplicationEventDescription2(String applicationEventDescription2) {
        this.applicationEventDescription2 = applicationEventDescription2;
    }

    public Integer getApplicationEventId() {
        return applicationEventId;
    }

    public void setApplicationEventId(Integer applicationEventId) {
        this.applicationEventId = applicationEventId;
    }

    public Integer getApplicationCoreId() {
        return applicationCoreId;
    }

    public void setApplicationCoreId(Integer applicationCoreId) {
        this.applicationCoreId = applicationCoreId;
    }

    public String getApplicationEventType() {
        return applicationEventType;
    }

    public void setApplicationEventType(String applicationEventType) {
        this.applicationEventType = applicationEventType == null ? null : applicationEventType.trim();
    }

    public String getApplicationEventCurrentStatus() {
        return applicationEventCurrentStatus;
    }

    public void setApplicationEventCurrentStatus(String applicationEventCurrentStatus) {
        this.applicationEventCurrentStatus = applicationEventCurrentStatus == null ? null : applicationEventCurrentStatus.trim();
    }

    public String getApplicationEventPolicy() {
        return applicationEventPolicy;
    }

    public void setApplicationEventPolicy(String applicationEventPolicy) {
        this.applicationEventPolicy = applicationEventPolicy == null ? null : applicationEventPolicy.trim();
    }

    public Date getApplicationEventStartDatetime() {
        return applicationEventStartDatetime;
    }

    public void setApplicationEventStartDatetime(Date applicationEventStartDatetime) {
        this.applicationEventStartDatetime = applicationEventStartDatetime;
    }

    public Date getApplicationEventFinishDatetime() {
        return applicationEventFinishDatetime;
    }

    public void setApplicationEventFinishDatetime(Date applicationEventFinishDatetime) {
        this.applicationEventFinishDatetime = applicationEventFinishDatetime;
    }

    public String getApplicationEventDescription() {
        return applicationEventDescription;
    }

    public void setApplicationEventDescription(String applicationEventDescription) {
        this.applicationEventDescription = applicationEventDescription == null ? null : applicationEventDescription.trim();
    }

    public Integer getEnvId() {
        return envId;
    }

    public void setEnvId(Integer envId) {
        this.envId = envId;
    }
}