package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2Args;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2ArgsMapper")
public interface V2ArgsMapper{

    List<V2Args> selectByImageId(Integer imageId);
    int deleteByPrimaryKey(Integer id);

    int deleteByImageId(Integer imageId);

    int insert(V2Args record);

    int insertSelective(V2Args record);

    V2Args selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2Args record);

    int updateByPrimaryKey(V2Args record);
}