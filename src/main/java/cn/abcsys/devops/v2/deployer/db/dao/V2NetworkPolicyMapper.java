package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.objects.ResultBean;
import cn.abcsys.devops.v2.deployer.db.model.V2NetworkPolicy;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2NetworkPolicyMapper")
public interface V2NetworkPolicyMapper{
    Integer checkIsExist(@Param("envId") Integer envId, @Param("name") String name);

    List<ResultBean> selectByEnvId(@Param("envId") Integer envId, @Param("start") Integer start, @Param("end") Integer end);

    Integer getTotalNumByEnvId(Integer envId);

    Integer getTotalNumByEnvIdAndProjectIds(@Param("projectIds") String[] projectIds, @Param("envId") Integer envId);

    int deleteByPrimaryKey(Integer id);

    int insert(V2NetworkPolicy record);

    int insertSelective(V2NetworkPolicy record);

    V2NetworkPolicy selectByPrimaryKey(Integer id);

    List<V2NetworkPolicy> selectByNetworkName(String networkName);

    List<V2NetworkPolicy> selectByNetworkNameList(@Param("networkNameList") List<String> networkNameList, @Param("envId") Integer envId);

    int updateByPrimaryKeySelective(V2NetworkPolicy record);

    int updateByPrimaryKey(V2NetworkPolicy record);

    List<V2NetworkPolicy> selectByFields(@Param("networkPolicy") V2NetworkPolicy networkPolicy, @Param("start") Integer start, @Param("end") Integer end);

    Integer getTotalNumByFields(@Param("networkPolicy") V2NetworkPolicy networkPolicy);
}