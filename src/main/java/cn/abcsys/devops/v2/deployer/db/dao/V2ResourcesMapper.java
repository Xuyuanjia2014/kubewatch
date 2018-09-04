package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2Resources;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2ResourcesMapper")
public interface V2ResourcesMapper{
    List<V2Resources> selectByImageId(Integer imageId);
    int deleteByPrimaryKey(Integer id);

    int deleteByImageId(Integer imageId);

    int insert(V2Resources record);

    int insertSelective(V2Resources record);

    V2Resources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2Resources record);

    int updateByPrimaryKey(V2Resources record);
}