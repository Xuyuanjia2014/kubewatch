package cn.abcsys.devops.v2.deployer.db.model;

import java.util.List;

public class V2Probe {
    private Integer id;

    private Integer imageId;

    private String probeType;

    private String requestType;

    private String probePath;

    private Integer probePort;
    
    private String probeHost;

    private String scheme;

    private Integer initialDelaySeconds;

    private Integer timeoutSeconds;
    
    private Integer periodSeconds;

    private Integer successThreshold;

    private Integer failureThreshold;

    private String status;
    
    private List<String> command;

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

    public String getProbeType() {
        return probeType;
    }

    public void setProbeType(String probeType) {
        this.probeType = probeType == null ? null : probeType.trim();
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType == null ? null : requestType.trim();
    }

    public String getProbePath() {
        return probePath;
    }

    public void setProbePath(String probePath) {
        this.probePath = probePath == null ? null : probePath.trim();
    }

    public Integer getProbePort() {
        return probePort;
    }

    public void setProbePort(Integer probePort) {
        this.probePort = probePort;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme == null ? null : scheme.trim();
    }

    public Integer getInitialDelaySeconds() {
        return initialDelaySeconds;
    }

    public void setInitialDelaySeconds(Integer initialDelaySeconds) {
        this.initialDelaySeconds = initialDelaySeconds;
    }

    public Integer getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public void setTimeoutSeconds(Integer timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
    }

    public Integer getSuccessThreshold() {
        return successThreshold;
    }

    public void setSuccessThreshold(Integer successThreshold) {
        this.successThreshold = successThreshold;
    }

    public Integer getFailureThreshold() {
        return failureThreshold;
    }

    public void setFailureThreshold(Integer failureThreshold) {
        this.failureThreshold = failureThreshold;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Integer getPeriodSeconds () {
        return periodSeconds;
    }
    
    public void setPeriodSeconds (Integer periodSeconds) {
        this.periodSeconds = periodSeconds;
    }
    
    public String getProbeHost () {
        return probeHost;
    }
    
    public void setProbeHost (String probeHost) {
        this.probeHost = probeHost;
    }
    
    public List<String> getCommand () {
        return command;
    }
    
    public void setCommand (List<String> command) {
        this.command = command;
    }
}