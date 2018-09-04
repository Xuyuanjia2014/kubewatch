package cn.abcsys.devops.deployer.model;/**
 * Copyright (2017, ) Institute of Software, Chinese Academy of Sciences
 * Copyright (2017, ) Bocloud Co,. Lmt
 */

import java.util.List;

/**
 * @author xuyuanjia2017@otcaix.iscsa.ac.cn
 * @date 2017/6/12 0012
 * say sth.
 */
public class ParamsWrapper {
    public InstanceCore instanceCore;
    public List<InstanceVolumes> instanceVolumesList;
    public String instanceVolumesListString;
    public List<InstanceEnvs> instanceEnvsList;
    public String instanceEnvsListString;
    public List<InstancePorts> instancePorts;
    public String instancePortsString;
    public Integer envId;
    public String clusterIp;
    public String pathName;
    public Integer instanceReplica;
    public String masterPort;
    public String masterType;
    public String getClusterIp() {
        return clusterIp;
    }

    public void setClusterIp(String clusterIp) {
        this.clusterIp = clusterIp;
    }

    public Integer getEnvId() {
        return envId;
    }

    public void setEnvId(Integer envId) {
        this.envId = envId;
    }

    public InstanceCore getInstanceCore() {
        return instanceCore;
    }

    public void setInstanceCore(InstanceCore instanceCore) {
        this.instanceCore = instanceCore;
    }

    public List<InstanceVolumes> getInstanceVolumesList() {
        return instanceVolumesList;
    }

    public void setInstanceVolumesList(List<InstanceVolumes> instanceVolumesList) {
        this.instanceVolumesList = instanceVolumesList;
    }

    public List<InstanceEnvs> getInstanceEnvsList() {
        return instanceEnvsList;
    }

    public void setInstanceEnvsList(List<InstanceEnvs> instanceEnvsList) {
        this.instanceEnvsList = instanceEnvsList;
    }

    public List<InstancePorts> getInstancePorts() {
        return instancePorts;
    }

    public void setInstancePorts(List<InstancePorts> instancePorts) {
        this.instancePorts = instancePorts;
    }

    public String getInstanceVolumesListString() {
        return instanceVolumesListString;
    }

    public void setInstanceVolumesListString(String instanceVolumesListString) {
        this.instanceVolumesListString = instanceVolumesListString;
    }

    public String getInstanceEnvsListString() {
        return instanceEnvsListString;
    }

    public void setInstanceEnvsListString(String instanceEnvsListString) {
        this.instanceEnvsListString = instanceEnvsListString;
    }

    public String getInstancePortsString() {
        return instancePortsString;
    }

    public void setInstancePortsString(String instancePortsString) {
        this.instancePortsString = instancePortsString;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public Integer getInstanceReplica() {
        return instanceReplica;
    }

    public void setInstanceReplica(Integer instanceReplica) {
        this.instanceReplica = instanceReplica;
    }

    public String getMasterPort() {
        return masterPort;
    }

    public void setMasterPort(String masterPort) {
        this.masterPort = masterPort;
    }

    public String getMasterType() {
        return masterType;
    }

    public void setMasterType(String masterType) {
        this.masterType = masterType;
    }
}
