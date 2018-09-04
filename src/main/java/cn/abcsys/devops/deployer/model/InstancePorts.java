package cn.abcsys.devops.deployer.model;

import java.util.Date;

public class InstancePorts {
    private Integer instancePortsId;

    private Integer instanceCoreId;

    private Integer instancePortsPort;

    private Integer instancePortsTargetPort;

    private Integer instancePortsNodePort;

    private String instancePortsClusterIp;

    private String instancePortsDescription;

    private Date instancePortsCreateDatetime;

    private Date instancePortsDeleteDatetime;

    private String instancePortsCurrentStatus;

    public Integer getInstancePortsId() {
        return instancePortsId;
    }

    public void setInstancePortsId(Integer instancePortsId) {
        this.instancePortsId = instancePortsId;
    }

    public Integer getInstanceCoreId() {
        return instanceCoreId;
    }

    public void setInstanceCoreId(Integer instanceCoreId) {
        this.instanceCoreId = instanceCoreId;
    }

    public Integer getInstancePortsPort() {
        return instancePortsPort;
    }

    public void setInstancePortsPort(Integer instancePortsPort) {
        this.instancePortsPort = instancePortsPort;
    }

    public Integer getInstancePortsTargetPort() {
        return instancePortsTargetPort;
    }

    public void setInstancePortsTargetPort(Integer instancePortsTargetPort) {
        this.instancePortsTargetPort = instancePortsTargetPort;
    }

    public Integer getInstancePortsNodePort() {
        return instancePortsNodePort;
    }

    public void setInstancePortsNodePort(Integer instancePortsNodePort) {
        this.instancePortsNodePort = instancePortsNodePort;
    }

    public String getInstancePortsClusterIp() {
        return instancePortsClusterIp;
    }

    public void setInstancePortsClusterIp(String instancePortsClusterIp) {
        this.instancePortsClusterIp = instancePortsClusterIp == null ? null : instancePortsClusterIp.trim();
    }

    public String getInstancePortsDescription() {
        return instancePortsDescription;
    }

    public void setInstancePortsDescription(String instancePortsDescription) {
        this.instancePortsDescription = instancePortsDescription == null ? null : instancePortsDescription.trim();
    }

    public Date getInstancePortsCreateDatetime() {
        return instancePortsCreateDatetime;
    }

    public void setInstancePortsCreateDatetime(Date instancePortsCreateDatetime) {
        this.instancePortsCreateDatetime = instancePortsCreateDatetime;
    }

    public Date getInstancePortsDeleteDatetime() {
        return instancePortsDeleteDatetime;
    }

    public void setInstancePortsDeleteDatetime(Date instancePortsDeleteDatetime) {
        this.instancePortsDeleteDatetime = instancePortsDeleteDatetime;
    }

    public String getInstancePortsCurrentStatus() {
        return instancePortsCurrentStatus;
    }

    public void setInstancePortsCurrentStatus(String instancePortsCurrentStatus) {
        this.instancePortsCurrentStatus = instancePortsCurrentStatus == null ? null : instancePortsCurrentStatus.trim();
    }
}