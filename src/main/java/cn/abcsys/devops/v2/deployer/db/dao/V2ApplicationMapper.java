package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.ProjectCore;
import cn.abcsys.devops.v2.deployer.db.model.V2Application;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "v2ApplicationMapper")
public interface V2ApplicationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(V2Application record);

    int insertSelective(V2Application record);

    V2Application selectByPrimaryKey(Integer id);

    List<Integer> selectProjectByApplicationName(@Param("appName") String appName, @Param("start") Integer start, @Param("num") Integer num);

    Integer getTotalNumOfProjectByApplicationName(String appName);

    List<V2Application> selectByProjectId(@Param("projectId") Integer projectId, @Param("start") Integer start, @Param("num") Integer num);

    List<V2Application> selectByApplicationName(@Param("appName") String appName, @Param("projectId") Integer projectId, @Param("start") Integer start, @Param("num") Integer num);

    Integer getTotalNumByProjectId(Integer projectId);

    Integer getTotalNumByApplicationName(@Param("appName") String appName, @Param("projectId") Integer projectId);

    V2Application selectExisingByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2Application record);

    int updateByPrimaryKey(V2Application record);

    Integer checkApplicationName(@Param("name") String name, @Param("project") ProjectCore project);

    List<V2Application> selectTop5UpdateByProjectIdList(List<Integer> projectIdList);

    List<V2Application> selectDescUpdateByProjectIdList(List<Integer> projectIdList);

    List<V2Application> selectByProjectIds(@Param("projectIds") String[] projectIds, @Param("start") Integer start, @Param("num") Integer num);

    Integer selectCountByProjectIds(@Param("projectIds") String[] projectIds);

    String insertOrUpdateApplication(Map<String, Object> map);
    String insertOrUpdateApplicationIfNotExist(Map<String, Object> map);

    String getApplication(Map<String, Object> map);

    V2Application selectByMultipleFields(@Param("application") V2Application application);

}