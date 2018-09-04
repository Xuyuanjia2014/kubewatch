package cn.abcsys.devops.deployer.dao;

import cn.abcsys.devops.deployer.model.InstanceCore;
import cn.abcsys.devops.deployer.model.InstancePorts;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="instancePortsMapper")
public interface InstancePortsMapper {
    int deleteByPrimaryKey(Integer instancePortsId);

    int insert(InstancePorts record);

    int insertSelective(InstancePorts record);

    InstancePorts selectByPrimaryKey(Integer instancePortsId);

    int updateByPrimaryKeySelective(InstancePorts record);

    int updateByPrimaryKey(InstancePorts record);

    List<InstancePorts> selectByInstanceCoreId(Integer instanceCoreId);

    List<InstancePorts> selectDistinctPortsByAppName(@Param("ic") InstanceCore ic);
}