package cn.abcsys.devops.v2.deployer.db.model;

public class V2VersionCount {
    private Integer versionCount;

    private Integer applicationId;


    public Integer getVersionCount() {
        return versionCount;
    }

    public void setVersionCount(Integer versionCount) {
        this.versionCount = versionCount;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }
}