package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2SvcLabels;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2SvcLabelsMapper")
public interface V2SvcLabelsMapper{
    int deleteAllByServiceId(Integer serviceId);

    int deleteByPrimaryKey(Integer id);

    int insert(V2SvcLabels record);

    int insertSelective(V2SvcLabels record);

    V2SvcLabels selectByPrimaryKey(Integer id);

    List<V2SvcLabels> selectAllByServiceId(Integer serviceId);

    int updateByPrimaryKeySelective(V2SvcLabels record);

    int updateByPrimaryKey(V2SvcLabels record);
}