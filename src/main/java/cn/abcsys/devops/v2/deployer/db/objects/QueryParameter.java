
/**
 * Copyright (2017, ) Institute of Software, Chinese Academy of Sciences
 */
package cn.abcsys.devops.v2.deployer.db.objects;

/**
 * @Author Xuyuanjia xuyuanjia2017@otcaix.iscas.ac.cn
 * @Date 2017/12/13 0013 23:27
 */
public abstract class QueryParameter {
    String resourceAndQueryType;

    public String getResourceAndQueryType() {
        return resourceAndQueryType;
    }

    public void setResourceAndQueryType(String resourceAndQueryType) {
        this.resourceAndQueryType = resourceAndQueryType;
    }

    public String getResourceType() {
        return resourceAndQueryType;
    }

    public void setResourceType(String resourceType) {
        this.resourceAndQueryType = resourceType;
    }
}
