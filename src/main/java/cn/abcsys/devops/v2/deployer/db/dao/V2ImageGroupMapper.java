package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2ImageGroup;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "v2ImageGroupMapper")
public interface V2ImageGroupMapper {
    Integer checkIsExist(@Param("versionGroupId") Integer versionGroupId, @Param("name") String name);

    int deleteByPrimaryKey(Integer id);

    int insert(V2ImageGroup record);

    int insertSelective(V2ImageGroup record);

    V2ImageGroup selectByPrimaryKey(Integer id);

    V2ImageGroup selectByRealName(String realName);

    int updateByPrimaryKeySelective(V2ImageGroup record);
    
    int updateEnvIdByProjectId(@Param("projectId") Integer projectId, @Param("envId") Integer envId);
    
    int updateByPrimaryKey(V2ImageGroup record);

    List<V2ImageGroup> selectByVersionId(Integer versionId);

    List<V2ImageGroup> selectAllByVersionId(Integer versionId);

    List<Integer> selectRuningVersionByApplicationId(Integer appId);

    List<Integer> selectDeadVersionByApplicationId(Integer appId);

    List<Integer> selectDeadAndCreatedVersionByVersionId(Integer versionId);

    Integer selectVersionCountsByApplicationId(Integer applicationId);

    Integer getRunningDeploymentCountByVersionId(Integer versionId);

    Integer getRunningDeploymentSumByVersionId(Integer versionId);

    //List<V2ImageGroup> getRunningDeploymentByApplicationId(Integer applicationId);

    List<V2ImageGroup> selectByFields(@Param("imageGroup") V2ImageGroup imageGroup, @Param("offset") Integer offset, @Param("rows") Integer rows);

    Integer selectCountByFields(@Param("imageGroup") V2ImageGroup imageGroup);

    String insertOrUpdateImageGroup(Map<String, Object> map) throws MySQLIntegrityConstraintViolationException;

    String insertOrUpdateImageGroupIfNotExist(Map<String, Object> map) throws MySQLIntegrityConstraintViolationException;

    String getImageGroup(Map<String, Object> map);

    String insertOrGetEnvs(Map<String, Object> map);

    String insertOrGetImage(Map<String, Object> map);

    String insertOrGetLabel(Map<String, Object> map);

    String insertOrGetMount(Map<String, Object> map);

    String insertOrGetPorts(Map<String, Object> map);

    String insertOrGetProbes(Map<String, Object> map);

    String insertOrGetResource(Map<String, Object> map);

    String insertOrGetVolume(Map<String, Object> map);

    String insertOrUpdateContainer(Map<String, Object> map);

    String insertOrUpdatePod(Map<String, Object> map);

    String deletePodAndContainers(Map<String, Object> map);

    String deletePodAndContainersUUid(Map<String, Object> map);

    String deleteVersionAndImageGroup(Map<String, Object> map);

    String deleteApplication(Map<String, Object> map);
}