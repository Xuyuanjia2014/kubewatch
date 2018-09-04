package cn.abcsys.devops.v3.initialization;

public class HandlerInit {
    private String MASTER_TYPE;
    private String MASTER_IP;
    private String MASTER_PORT;
    private String PLATFORMTYPE;
    private int CLUSTER_ID;
    private int ENV_ID;

    public HandlerInit(String MASTER_TYPE, String MASTER_IP, String MASTER_PORT, String PLATFORMTYPE, int CLUSTER_ID, int ENV_ID) {
        this.MASTER_TYPE = MASTER_TYPE;
        this.MASTER_IP = MASTER_IP;
        this.MASTER_PORT = MASTER_PORT;
        this.PLATFORMTYPE = PLATFORMTYPE;
        this.CLUSTER_ID = CLUSTER_ID;
        this.ENV_ID = ENV_ID;
    }

    public String getMASTER_TYPE() {
        return MASTER_TYPE;
    }

    public void setMASTER_TYPE(String MASTER_TYPE) {
        this.MASTER_TYPE = MASTER_TYPE;
    }

    public String getMASTER_IP() {
        return MASTER_IP;
    }

    public void setMASTER_IP(String MASTER_IP) {
        this.MASTER_IP = MASTER_IP;
    }

    public String getMASTER_PORT() {
        return MASTER_PORT;
    }

    public void setMASTER_PORT(String MASTER_PORT) {
        this.MASTER_PORT = MASTER_PORT;
    }

    public String getPLATFORMTYPE() {
        return PLATFORMTYPE;
    }

    public void setPLATFORMTYPE(String PLATFORMTYPE) {
        this.PLATFORMTYPE = PLATFORMTYPE;
    }

    public int getCLUSTER_ID() {
        return CLUSTER_ID;
    }

    public void setCLUSTER_ID(int CLUSTER_ID) {
        this.CLUSTER_ID = CLUSTER_ID;
    }

    public int getENV_ID() {
        return ENV_ID;
    }

    public void setENV_ID(int ENV_ID) {
        this.ENV_ID = ENV_ID;
    }
}
