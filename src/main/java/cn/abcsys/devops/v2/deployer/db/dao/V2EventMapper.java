package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2Event;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2EventMapper")
public interface V2EventMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(V2Event record);

    int insertSelective(V2Event record);

    V2Event selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2Event record);

    int updateByPrimaryKey(V2Event record);

    V2Event getMaxResourceVersion(Integer versionId);

    List<V2Event> selectByVersionId(Integer versionId);
}