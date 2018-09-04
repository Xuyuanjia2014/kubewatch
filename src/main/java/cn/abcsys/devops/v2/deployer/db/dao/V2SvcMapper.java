package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2Svc;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2SvcMapper")
public interface V2SvcMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteAllByApplicationId(Integer applicationId);

    int insert(V2Svc record);

    int insertSelective(V2Svc record);

    V2Svc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2Svc record);

    int updateByPrimaryKey(V2Svc record);

    V2Svc selectOneByApplicationId(Integer applicationId);

    Integer getTotalNumByApplicationId(Integer applicationId);

    List<V2Svc> selectAllByApplicationId(@Param("applicationId") Integer applicationId, @Param("start") Integer start, @Param("end") Integer end);

    List<V2Svc> selectAllByApplicationIdNotPage(Integer applicationId);

    List<V2Svc> selectAllBySvcNameAndApplicationIdNotPage(@Param("svcName") String svcName, @Param("applicationId") Integer applicationId, @Param("masterIp") String masterIp);
}