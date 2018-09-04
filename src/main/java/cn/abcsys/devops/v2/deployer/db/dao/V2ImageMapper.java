package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2ImageMapper")
public interface V2ImageMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(V2Image record);

    int insertSelective(V2Image record);

    V2Image selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2Image record);

    int updateByPrimaryKey(V2Image record);

    List<V2Image> selectByImageGroupId(Integer imageGroupId);

    List<V2Image> selectByVersionId(Integer versionId);
}