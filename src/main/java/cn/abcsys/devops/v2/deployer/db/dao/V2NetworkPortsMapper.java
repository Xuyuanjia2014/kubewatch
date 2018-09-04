package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2NetworkPorts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2NetworkPortsMapper")
public interface V2NetworkPortsMapper{
    int deleteByPrimaryKey(Integer id);

    int deleteByNetworkPolicyId(Integer networkPolicyId);

    int insert(V2NetworkPorts record);

    int insertSelective(V2NetworkPorts record);

    V2NetworkPorts selectByPrimaryKey(Integer id);

    List<V2NetworkPorts> selectByNetworkPolicyId(Integer networkPolicyId);

    int updateByPrimaryKeySelective(V2NetworkPorts record);

    int updateByPrimaryKey(V2NetworkPorts record);
}