package cn.abcsys.devops.v2.deployer.db.model;

import java.util.Date;

public class V2Resources {
    private Integer id;

    private Integer imageId;

    private String maxCpu;

    private String maxMem;

    private String minCpu;

    private String minMem;
    
    private String maxGpu;

    private String network;

    private Date createTime;

    private Double maxCpuValue;

    private Double maxMemValue;

    private Double minCpuValue;

    private Double minMemValue;

    private Integer imageGroupId;

    private Integer versionId;

    private Integer applicationId;

    private Integer projectId;

    private Integer envId;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getMaxCpu() {
        return maxCpu;
    }

    public void setMaxCpu(String maxCpu) {
        this.maxCpu = maxCpu == null ? null : maxCpu.trim();
    }

    public String getMaxMem() {
        return maxMem;
    }

    public void setMaxMem(String maxMem) {
        this.maxMem = maxMem == null ? null : maxMem.trim();
    }

    public String getMinCpu() {
        return minCpu;
    }

    public void setMinCpu(String minCpu) {
        this.minCpu = minCpu == null ? null : minCpu.trim();
    }

    public String getMinMem() {
        return minMem;
    }

    public void setMinMem(String minMem) {
        this.minMem = minMem == null ? null : minMem.trim();
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network == null ? null : network.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getMaxCpuValue() {
        return maxCpuValue;
    }

    public void setMaxCpuValue(Double maxCpuValue) {
        this.maxCpuValue = maxCpuValue;
    }

    public Double getMaxMemValue() {
        return maxMemValue;
    }

    public void setMaxMemValue(Double maxMemValue) {
        this.maxMemValue = maxMemValue;
    }

    public Double getMinCpuValue() {
        return minCpuValue;
    }

    public void setMinCpuValue(Double minCpuValue) {
        this.minCpuValue = minCpuValue;
    }

    public Double getMinMemValue() {
        return minMemValue;
    }

    public void setMinMemValue(Double minMemValue) {
        this.minMemValue = minMemValue;
    }

    public Integer getImageGroupId() {
        return imageGroupId;
    }

    public void setImageGroupId(Integer imageGroupId) {
        this.imageGroupId = imageGroupId;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getEnvId() {
        return envId;
    }

    public void setEnvId(Integer envId) {
        this.envId = envId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getMaxGpu () {
        return maxGpu;
    }
    
    public void setMaxGpu (String maxGpu) {
        this.maxGpu = maxGpu;
    }
}