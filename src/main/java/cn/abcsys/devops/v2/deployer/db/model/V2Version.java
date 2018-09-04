package cn.abcsys.devops.v2.deployer.db.model;

import java.util.Date;

public class V2Version {
    private Integer id;

    private Integer applicationId;

    private String versionName;

    private String versionType;

    private Integer replica;

    private Date createDatetime;

    private Date deleteDatetime;

    private Integer oldVersionId;

    public V2Version(){

    }

    public V2Version(Integer id, Integer applicationId, String versionName, String versionType, Integer replica, Date createDatetime, Date deleteDatetime, Integer oldVersionId) {
        this.id = id;
        this.applicationId = applicationId;
        this.versionName = versionName;
        this.versionType = versionType;
        this.replica = replica;
        this.createDatetime = createDatetime;
        this.deleteDatetime = deleteDatetime;
        this.oldVersionId = oldVersionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
    }

    public Integer getReplica() {
        return replica;
    }

    public void setReplica(Integer replica) {
        this.replica = replica;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getDeleteDatetime() {
        return deleteDatetime;
    }

    public void setDeleteDatetime(Date deleteDatetime) {
        this.deleteDatetime = deleteDatetime;
    }

    public Integer getOldVersionId() {
        return oldVersionId;
    }

    public void setOldVersionId(Integer oldVersionId) {
        this.oldVersionId = oldVersionId;
    }
}