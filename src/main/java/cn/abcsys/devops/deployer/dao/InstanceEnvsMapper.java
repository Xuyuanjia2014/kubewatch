package cn.abcsys.devops.deployer.dao;

import cn.abcsys.devops.deployer.model.InstanceEnvs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="instanceEnvsMapper")
public interface InstanceEnvsMapper {
    int deleteByPrimaryKey(Integer instanceEnvsId);

    int insert(InstanceEnvs record);

    int insertSelective(InstanceEnvs record);

    InstanceEnvs selectByPrimaryKey(Integer instanceEnvsId);

    int updateByPrimaryKeySelective(InstanceEnvs record);

    int updateByPrimaryKey(InstanceEnvs record);

    List<InstanceEnvs> selectByInstanceCoreId(Integer instanceCoreId);
}