package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2Envs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2EnvsMapper")
public interface V2EnvsMapper{

    List<V2Envs> selectByImageId(Integer imageId);

    int deleteByPrimaryKey(Integer id);

    int deleteByImageId(Integer imageId);

    int insert(V2Envs record);

    int insertSelective(V2Envs record);

    V2Envs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2Envs record);

    int updateByPrimaryKey(V2Envs record);

    List<V2Envs> selectByEnvKey(@Param("envKey") String envKey, @Param("imageId") Integer imageId);
}