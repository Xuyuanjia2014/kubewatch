package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.ProjectCore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository(value = "projectCoreMapper")
public interface ProjectCoreMapper {
    int deleteByPrimaryKey(Integer projectId);

    int insert(ProjectCore record);

    int insertProject(ProjectCore record) throws SQLException;


    List<ProjectCore> selectAllByEnvId(@Param("envId") Integer envId, @Param("start") Integer start, @Param("num") Integer num);

    Integer getTotalNumOfProjectByEnvId(Integer envId);

    ProjectCore selectByPrimaryKey(Integer projectId);

    int updateByPrimaryKeySelective(ProjectCore record);

    int updateByPrimaryKey(ProjectCore record);

    List<ProjectCore> selectByFieldsNew(@Param("record") ProjectCore record, @Param("projectIds") String[] projectIds, @Param("page") Integer page, @Param("rows") Integer rows, @Param("applicationCoreRename") String applicationCoreRename);

    Integer selectCountByFieldsNew(@Param("record") ProjectCore record, @Param("projectIds") String[] projectIds, @Param("applicationCoreRename") String applicationCoreRename);

    List<ProjectCore> selectByFields(@Param("record") ProjectCore record, @Param("projectIds") String[] projectIds, @Param("page") Integer page, @Param("rows") Integer rows);
    
    List<ProjectCore> selectByFieldsWithAppName(@Param("record") ProjectCore record, @Param("projectIds") String[] projectIds, @Param("appName") String appName, @Param("page") Integer page, @Param("rows") Integer rows);

    Integer selectCountByFields(@Param("record") ProjectCore record, @Param("projectIds") String[] projectIds);
    
    Integer selectCountByFieldsWithAppName(@Param("record") ProjectCore record, @Param("projectIds") String[] projectIds, @Param("appName") String appName);

    List<ProjectCore> selectByNamespace(@Param("projectName") String projectName, @Param("projectRuntimeId") Integer projectRuntimeId);

    String insertOrGetProjectCore(Map<String, Object> map);

    String insertOrGetProjectCoreIfNotExist(Map<String, Object> map);

    String deleteProjectCore(Map<String, Object> map);

    String checkProjectCore(Map<String, Object> map);

    List<ProjectCore> selectAllProjects();
    
    List<String> getProjectNames(@Param("projectIds") List<Integer> projectIds);
    
    List<String> getProjectNamesByEnvId(@Param("envId") Integer envId);
    
    List<ProjectCore> selectIsExist(ProjectCore record);

}