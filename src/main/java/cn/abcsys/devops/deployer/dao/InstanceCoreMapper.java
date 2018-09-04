package cn.abcsys.devops.deployer.dao;

import cn.abcsys.devops.deployer.model.InstanceCore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="instanceCoreMapper")
public interface InstanceCoreMapper {
    int deleteByPrimaryKey(Integer instanceCoreId);

    int insert(InstanceCore record);

    int insertSelective(InstanceCore record);

    InstanceCore selectByPrimaryKey(Integer instanceCoreId);

    InstanceCore selectByUniqueRename(String instanceRename);

    int updateByPrimaryKeySelective(InstanceCore record);

    int updateByPrimaryKey(InstanceCore record);

    //instance select.
    List<InstanceCore> selectByMultipleFields(@Param("record") InstanceCore record, @Param("page") Integer page, @Param("rows") Integer rows);
    // 查询当前环境下所有容器
    List<InstanceCore> selectAllContainersMultipleFields(@Param("record") InstanceCore record);
    int selectCountByMultipleFields(@Param("record") InstanceCore record);
    // 查询正在运行中的容器数量
    int selectCountRunningByMultipleFields(@Param("record") InstanceCore record);

    //network
    int selectDistinctAppNameCountByInstanceNetwork(@Param("record") InstanceCore record);
    int selectCountByInstanceNetwork(@Param("record") InstanceCore record);
    List<InstanceCore> selectDistinctAppNameByInstanceNetwork(@Param("record") InstanceCore record);
    List<InstanceCore> selectContainerNameByInstanceNetwork(@Param("record") InstanceCore record);
    int selectNetworksTypeCountsByNetwork(@Param("record") InstanceCore record);
    List<InstanceCore> selectNetworksByNetwork(@Param("record") InstanceCore record, @Param("page") Integer page, @Param("rows") Integer rows);

    // runtimeID? if not null will check imageName
    int selectInstanceCountsByImageName(@Param("record") InstanceCore record);
    int selectAppCountsByImageName(@Param("record") InstanceCore record);
    List<InstanceCore> selectDistinctImageNameByImageName(@Param("record") InstanceCore record, @Param("page") Integer page, @Param("rows") Integer rows);
    int selectDistinctImageNameCountByImageName(@Param("record") InstanceCore record);
    List<InstanceCore> selectDistinctAppNameByImageName(@Param("record") InstanceCore record);
    List<InstanceCore> selectAllInfoNameByImageName(@Param("record") InstanceCore record);

    // get containers' info under specific appName
    List<InstanceCore> selectByImageNameAppName(@Param("record") InstanceCore record, @Param("page") Integer page, @Param("rows") Integer rows);
    int selectCountByImageNameAppName(@Param("record") InstanceCore record);
    InstanceCore selectByComponentId(@Param("componentId") Integer componentId);

    InstanceCore isExistInstance(@Param("componentId") Integer componentId);

    int selectComponentAllCount(@Param("envId") Integer envId);

    int selectComponentRunningCount(@Param("envId") Integer envId);

    List<InstanceCore> selectAllComponent(@Param("componentIds") List<Integer> componentIds);
}