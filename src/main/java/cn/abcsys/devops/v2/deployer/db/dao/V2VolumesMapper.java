package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.objects.InstanceVolumes;
import cn.abcsys.devops.v2.deployer.db.model.V2Volumes;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2VolumesMapper")
public interface V2VolumesMapper {
    List<V2Volumes> selectByImageGroupId(Integer imageGroupId);
    int deleteByPrimaryKey(Integer id);

    int deleteByImageGroupId(Integer imageGroupId);

    int insert(V2Volumes record);

    int insertSelective(V2Volumes record);

    V2Volumes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2Volumes record);

    int updateByPrimaryKey(V2Volumes record);

    Integer getTotalNumByEnvId(Integer envId);

    Integer getTotalNumByEnvIdAndProjectIds(@Param("projectIds") String[] projectIds, @Param("envId") Integer envId);

    Integer selectCountPath(@Param("record") InstanceVolumes record, @Param("runtimeId") Integer runtimeId);
}