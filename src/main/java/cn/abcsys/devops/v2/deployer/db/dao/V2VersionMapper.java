package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2Version;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "v2VersionMapper")
public interface V2VersionMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(V2Version record);

    Integer insertSelective(V2Version record);

    V2Version selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(V2Version record);

    Integer updateByPrimaryKey(V2Version record);

    Integer getTotalNumByApplicationId(Integer appId);

    Integer getTotalNumByField(@Param("version") V2Version version);

    Integer getTotalNumByVersionName(String versionName);

    Integer getTotalNumOfLaterVersionByVersionId(@Param("appId") Integer appId, @Param("versionId") Integer versionId);

    Integer getTotalNumOfEarlierVersionByVersionId(@Param("appId") Integer appId, @Param("versionId") Integer versionId);

    List<V2Version> selectVersionByApplicationId(@Param("appId") Integer appId, @Param("start") Integer start, @Param("num") Integer num);

    List<V2Version> selectVersionByVersionName(@Param("versionName") String versionName, @Param("start") Integer start, @Param("num") Integer num);

    List<V2Version> selectVersionByExactVersionName(@Param("appId") Integer appId, @Param("versionName") String versionName, @Param("start") Integer start, @Param("num") Integer num);

    List<V2Version> selectAllVersionByApplicationId(Integer appId);

    List<V2Version> selectVersionByField(@Param("version") V2Version version, @Param("start") Integer start, @Param("num") Integer num);

    List<V2Version> selectLaterVersionByVersionId(@Param("appId") Integer appId, @Param("versionId") Integer versionId, @Param("start") Integer start, @Param("num") Integer num);

    List<V2Version> selectEarlierVersionByVersionId(@Param("appId") Integer appId, @Param("versionId") Integer versionId, @Param("start") Integer start, @Param("num") Integer num);

    String insertOrGetVersion(Map<String, Object> map);
    String insertOrGetVersionIfNotExist(Map<String, Object> map);

    String getVersion(Map<String, Object> map);
}