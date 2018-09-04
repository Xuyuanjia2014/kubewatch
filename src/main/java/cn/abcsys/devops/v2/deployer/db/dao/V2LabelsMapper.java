package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2Labels;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2LabelsMapper")
public interface V2LabelsMapper{
    List<V2Labels> selectByImageGroupId(Integer imageGroupId);

    List<V2Labels> select3DistinctByImageGroupId(Integer imageGroupId);
    int deleteByPrimaryKey(Integer id);

    int deleteByImageGroupId(Integer imageGroupId);

    int insert(V2Labels record);

    int insertSelective(V2Labels record);

    V2Labels selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2Labels record);

    int updateByPrimaryKey(V2Labels record);

    List<V2Labels> selectByFields(V2Labels v2Labels);

    List<V2Labels> selectApplicationLabelByApplicationId(Integer applicationId);

    List<V2Labels> selectNetworkLabelsByApplicationId(Integer applicationId);

}