package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2Probe;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2ProbeMapper")
public interface V2ProbeMapper{

    List<V2Probe> selectByImageId(Integer imageId);

    int deleteByImageId(Integer imageId);

    int deleteByPrimaryKey(Integer id);

    int insert(V2Probe record);

    int insertSelective(V2Probe record);

    V2Probe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2Probe record);

    int updateByPrimaryKey(V2Probe record);
}