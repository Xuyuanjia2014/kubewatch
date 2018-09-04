package cn.abcsys.devops.deployer.model;

import java.util.Date;

public class InstanceEvents {
    private Integer instanceEventsId;

    private Integer instanceCoreId;

    private String instanceEventsSource;

    private String instanceEventsType;

    private Date instanceEventsDatetime;

    private String instanceEventsOriginal;

    private String instanceEventsContent;

    private String instanceEventsComment;

    private Date instanceEventsCreateDatetime;

    private Date instanceEventsDeleteDatetime;

    private String instanceEventsCurrentStatus;

    public Integer getInstanceEventsId() {
        return instanceEventsId;
    }

    public void setInstanceEventsId(Integer instanceEventsId) {
        this.instanceEventsId = instanceEventsId;
    }

    public Integer getInstanceCoreId() {
        return instanceCoreId;
    }

    public void setInstanceCoreId(Integer instanceCoreId) {
        this.instanceCoreId = instanceCoreId;
    }

    public String getInstanceEventsSource() {
        return instanceEventsSource;
    }

    public void setInstanceEventsSource(String instanceEventsSource) {
        this.instanceEventsSource = instanceEventsSource == null ? null : instanceEventsSource.trim();
    }

    public String getInstanceEventsType() {
        return instanceEventsType;
    }

    public void setInstanceEventsType(String instanceEventsType) {
        this.instanceEventsType = instanceEventsType == null ? null : instanceEventsType.trim();
    }

    public Date getInstanceEventsDatetime() {
        return instanceEventsDatetime;
    }

    public void setInstanceEventsDatetime(Date instanceEventsDatetime) {
        this.instanceEventsDatetime = instanceEventsDatetime;
    }

    public String getInstanceEventsOriginal() {
        return instanceEventsOriginal;
    }

    public void setInstanceEventsOriginal(String instanceEventsOriginal) {
        this.instanceEventsOriginal = instanceEventsOriginal == null ? null : instanceEventsOriginal.trim();
    }

    public String getInstanceEventsContent() {
        return instanceEventsContent;
    }

    public void setInstanceEventsContent(String instanceEventsContent) {
        this.instanceEventsContent = instanceEventsContent == null ? null : instanceEventsContent.trim();
    }

    public String getInstanceEventsComment() {
        return instanceEventsComment;
    }

    public void setInstanceEventsComment(String instanceEventsComment) {
        this.instanceEventsComment = instanceEventsComment == null ? null : instanceEventsComment.trim();
    }

    public Date getInstanceEventsCreateDatetime() {
        return instanceEventsCreateDatetime;
    }

    public void setInstanceEventsCreateDatetime(Date instanceEventsCreateDatetime) {
        this.instanceEventsCreateDatetime = instanceEventsCreateDatetime;
    }

    public Date getInstanceEventsDeleteDatetime() {
        return instanceEventsDeleteDatetime;
    }

    public void setInstanceEventsDeleteDatetime(Date instanceEventsDeleteDatetime) {
        this.instanceEventsDeleteDatetime = instanceEventsDeleteDatetime;
    }

    public String getInstanceEventsCurrentStatus() {
        return instanceEventsCurrentStatus;
    }

    public void setInstanceEventsCurrentStatus(String instanceEventsCurrentStatus) {
        this.instanceEventsCurrentStatus = instanceEventsCurrentStatus == null ? null : instanceEventsCurrentStatus.trim();
    }
}