package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2Deployment;
import org.springframework.stereotype.Repository;

@Repository(value = "v2DeploymentMapper")
public interface V2DeploymentMapper{
    int deleteByPrimaryKey(Integer id);

    int deleteByUid(String uid);

    int insert(V2Deployment record);

    int insertSelective(V2Deployment record);

    V2Deployment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2Deployment record);

    int updateByPrimaryKey(V2Deployment record);

    V2Deployment getMaxResourceVersion();

    V2Deployment selectByName(String name);
}