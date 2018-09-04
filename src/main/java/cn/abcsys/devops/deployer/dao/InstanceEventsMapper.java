package cn.abcsys.devops.deployer.dao;

import cn.abcsys.devops.deployer.model.InstanceEvents;
import org.springframework.stereotype.Repository;

@Repository(value="instanceEventsMapper")
public interface InstanceEventsMapper {
    int deleteByPrimaryKey(Integer instanceEventsId);

    int insert(InstanceEvents record);

    int insertSelective(InstanceEvents record);

    InstanceEvents selectByPrimaryKey(Integer instanceEventsId);

    int updateByPrimaryKeySelective(InstanceEvents record);

    int updateByPrimaryKey(InstanceEvents record);
}