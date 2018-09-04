package cn.abcsys.devops.v2.deployer.db.model;

import cn.abcsys.devops.v2.deployer.db.objects.QueryParameter;

import java.util.Date;

public class ProjectCore extends QueryParameter {
    private Integer projectId;

    private String projectName;

    private String projectDescription;

    private Integer projectRuntimeId;

    private String projectComments;

    private Date projectCreateDatetime;

    private Date projectDeleteDatetime;

    private String projectCurrentStatus;

    private String projectNamespace;

    //记录集群信息
    private Integer clusterId;
    private String clusterIp;
    private String port;
    private String protocolType;

    public ProjectCore() {

    }

    public ProjectCore(String projectName, Integer projectRuntimeId) {
        this.projectName = projectName;
        this.projectRuntimeId = projectRuntimeId;
        this.projectNamespace = projectName;
    }
    public ProjectCore(String projectName,Integer clusterId ,Integer projectRuntimeId) {
        this.projectName = projectName;
        this.projectRuntimeId = projectRuntimeId;
        this.projectNamespace = projectName;
        this.clusterId = clusterId;
    }
    public String getProjectName() {
        return projectName;
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription == null ? null : projectDescription.trim();
    }

    public Integer getProjectRuntimeId() {
        return projectRuntimeId;
    }

    public void setProjectRuntimeId(Integer projectRuntimeId) {
        this.projectRuntimeId = projectRuntimeId;
    }

    public String getProjectComments() {
        return projectComments;
    }

    public void setProjectComments(String projectComments) {
        this.projectComments = projectComments == null ? null : projectComments.trim();
    }

    public Date getProjectCreateDatetime() {
        return projectCreateDatetime;
    }

    public void setProjectCreateDatetime(Date projectCreateDatetime) {
        this.projectCreateDatetime = projectCreateDatetime;
    }

    public Date getProjectDeleteDatetime() {
        return projectDeleteDatetime;
    }

    public void setProjectDeleteDatetime(Date projectDeleteDatetime) {
        this.projectDeleteDatetime = projectDeleteDatetime;
    }

    public String getProjectCurrentStatus() {
        return projectCurrentStatus;
    }

    public void setProjectCurrentStatus(String projectCurrentStatus) {
        this.projectCurrentStatus = projectCurrentStatus == null ? null : projectCurrentStatus.trim();
    }

    public String getProjectNamespace() {
        return projectNamespace;
    }

    public void setProjectNamespace(String projectNamespace) {
        this.projectNamespace = projectNamespace == null ? null : projectNamespace.trim();
    }

    public String getClusterIp() {
        return clusterIp;
    }

    public void setClusterIp(String clusterIp) {
        this.clusterIp = clusterIp;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public Integer getClusterId() {
        return clusterId;
    }

    public void setClusterId(Integer clusterId) {
        this.clusterId = clusterId;
    }
}