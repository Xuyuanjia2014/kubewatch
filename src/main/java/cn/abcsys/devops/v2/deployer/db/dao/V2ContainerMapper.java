package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.dto.V2ContainerDTO;
import cn.abcsys.devops.v2.deployer.db.model.V2Container;
import cn.abcsys.devops.v2.deployer.db.model.V2Pod;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2ContainerMapper")
public interface V2ContainerMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByImageGroupId(Integer imageGroupId);

    int insert(V2Container record);

    int insertSelective(V2Container record);

    V2Container selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2Container record);

    int updateByPrimaryKeySelectiveStrict(@Param("record") V2Container record);

    int updateByPrimaryKey(V2Container record);

    int updateEnvIdByProjectId(@Param("projectId") Integer projectId, @Param("envId") Integer envId);
    
    List<V2Container> selectByPodId(V2Pod pod);

    List<V2Container> selectByProjectId(Integer projectId);

    List<V2Container> selectByEnvId(Integer envId);

    List<V2Container> selectAllLive(@Param("container") V2Container container, @Param("offset") Integer offset, @Param("rows") Integer rows);

    List<V2Container> selectAll(@Param("container") V2Container container, @Param("offset") Integer offset, @Param("rows") Integer rows);

    List<V2Container> selectByFields(@Param("container") V2Container container, @Param("offset") Integer offset, @Param("rows") Integer rows);

    Integer selectCountByFields(@Param("container") V2Container container);
    
    Integer selectCountByLikeFields(@Param("container") V2Container container);

    Integer selectCountOfAllLive(@Param("container") V2Container container);

    Integer getRunningApplicationCount(Integer envId);

    Integer getRunningApplicationCountByProjectIdList(List<Integer> projectIdList);

    List<V2Container> selectByFieldsAndProjectIds(@Param("projectIds") String[] projectIds, @Param("container") V2Container container, @Param("offset") Integer offset, @Param("rows") Integer rows);
    
    List<V2ContainerDTO> selectByProjectIdsWithContainerCount(@Param("projectIds") String[] projectIds, @Param("container") V2Container container, @Param("offset") Integer offset, @Param("rows") Integer rows);
    
    List<V2ContainerDTO> selectWithContainerCount(@Param("container") V2Container container, @Param("offset") Integer offset, @Param("rows") Integer rows);

    Integer selectCountByFieldsAndProjectIds(@Param("projectIds") String[] projectIds, @Param("container") V2Container container);

    Integer selectLiveCountByFieldsAndProjectIds(@Param("projectIds") String[] projectIds, @Param("container") V2Container container);

    List<V2Container> selectOldContainers();

    int deleteByPodId(Integer podId);

    void deleteContainerByUuidAndPodName(@Param("uuid") String uuid, @Param("podName") String podName);

    List<V2Container> selectContainersByDeleteTime();
}