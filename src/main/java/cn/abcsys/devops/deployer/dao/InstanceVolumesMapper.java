package cn.abcsys.devops.deployer.dao;

import cn.abcsys.devops.deployer.model.InstanceVolumes;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="instanceVolumesMapper")
public interface InstanceVolumesMapper {
    int deleteByPrimaryKey(Integer instanceVolumesId);

    int insert(InstanceVolumes record);

    int insertSelective(InstanceVolumes record);

    InstanceVolumes selectByPrimaryKey(Integer instanceVolumesId);


    int updateByPrimaryKeySelective(InstanceVolumes record);

    int updateByPrimaryKey(InstanceVolumes record);

    List<InstanceVolumes> selectByInstanceCoreId(Integer instanceCoreId);
    List<InstanceVolumes> selectLikeByInstanceVolumesName(@Param("record") InstanceVolumes record, @Param("page") Integer page, @Param("rows") Integer rows, @Param("runtimeId") Integer runtimeId);
    Integer selectLikeCountByInstanceVolumesName(@Param("record") InstanceVolumes record, @Param("runtimeId") Integer runtimeId);
    InstanceVolumes selectByInstanceVolumesName(String name);
    Integer selectCountPath(@Param("record") InstanceVolumes record, @Param("runtimeId") Integer runtimeId);
}