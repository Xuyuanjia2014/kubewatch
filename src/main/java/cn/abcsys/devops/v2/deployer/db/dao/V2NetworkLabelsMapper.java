package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2NetworkLabels;
import cn.abcsys.devops.v2.deployer.db.model.V2NetworkPorts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2NetworkLabelsMapper")
public interface V2NetworkLabelsMapper{
    int deleteByPrimaryKey(Integer id);

    int deleteByNetworkPolicyId(Integer networkPolicyId);

    int insert(V2NetworkLabels record);

    int insertSelective(V2NetworkLabels record);

    V2NetworkLabels selectByPrimaryKey(Integer id);

    List<V2NetworkLabels> selectByNetworkPolicyId(Integer networkPolicyId);

    int updateByPrimaryKeySelective(V2NetworkLabels record);

    int updateByPrimaryKey(V2NetworkLabels record);
}