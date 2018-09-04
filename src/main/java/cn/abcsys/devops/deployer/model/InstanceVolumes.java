package cn.abcsys.devops.deployer.model;

import java.util.Date;

public class InstanceVolumes {
    private Integer instanceVolumesId;

    private Integer instanceCoreId;

    private String instanceVolumesType;

    private String instanceVolumesName;

    private String instanceVolumesMountPath;

    private String instanceVolumesServer;

    private String instanceVolumesPath;

    private Date instanceVolumesCreateDatetime;

    private Date instanceVolumesDeleteDatetime;

    private String instanceVolumesCurrentStatus;

    public Integer getInstanceVolumesId() {
        return instanceVolumesId;
    }

    public void setInstanceVolumesId(Integer instanceVolumesId) {
        this.instanceVolumesId = instanceVolumesId;
    }

    public Integer getInstanceCoreId() {
        return instanceCoreId;
    }

    public void setInstanceCoreId(Integer instanceCoreId) {
        this.instanceCoreId = instanceCoreId;
    }

    public String getInstanceVolumesType() {
        return instanceVolumesType;
    }

    public void setInstanceVolumesType(String instanceVolumesType) {
        this.instanceVolumesType = instanceVolumesType == null ? null : instanceVolumesType.trim();
    }

    public String getInstanceVolumesName() {
        return instanceVolumesName;
    }

    public void setInstanceVolumesName(String instanceVolumesName) {
        this.instanceVolumesName = instanceVolumesName == null ? null : instanceVolumesName.trim();
    }

    public String getInstanceVolumesMountPath() {
        return instanceVolumesMountPath;
    }

    public void setInstanceVolumesMountPath(String instanceVolumesMountPath) {
        this.instanceVolumesMountPath = instanceVolumesMountPath == null ? null : instanceVolumesMountPath.trim();
    }

    public String getInstanceVolumesServer() {
        return instanceVolumesServer;
    }

    public void setInstanceVolumesServer(String instanceVolumesServer) {
        this.instanceVolumesServer = instanceVolumesServer == null ? null : instanceVolumesServer.trim();
    }

    public String getInstanceVolumesPath() {
        return instanceVolumesPath;
    }

    public void setInstanceVolumesPath(String instanceVolumesPath) {
        this.instanceVolumesPath = instanceVolumesPath == null ? null : instanceVolumesPath.trim();
    }

    public Date getInstanceVolumesCreateDatetime() {
        return instanceVolumesCreateDatetime;
    }

    public void setInstanceVolumesCreateDatetime(Date instanceVolumesCreateDatetime) {
        this.instanceVolumesCreateDatetime = instanceVolumesCreateDatetime;
    }

    public Date getInstanceVolumesDeleteDatetime() {
        return instanceVolumesDeleteDatetime;
    }

    public void setInstanceVolumesDeleteDatetime(Date instanceVolumesDeleteDatetime) {
        this.instanceVolumesDeleteDatetime = instanceVolumesDeleteDatetime;
    }

    public String getInstanceVolumesCurrentStatus() {
        return instanceVolumesCurrentStatus;
    }

    public void setInstanceVolumesCurrentStatus(String instanceVolumesCurrentStatus) {
        this.instanceVolumesCurrentStatus = instanceVolumesCurrentStatus == null ? null : instanceVolumesCurrentStatus.trim();
    }
}