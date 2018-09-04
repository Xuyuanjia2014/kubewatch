package cn.abcsys.devops.v2.deployer.db.dao;
import cn.abcsys.devops.v2.deployer.db.model.V2SvcPorts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2SvcPortsMapper")
public interface V2SvcPortsMapper {

    int deleteAllByServiceId(Integer serviceId);

    int deleteByPrimaryKey(Integer id);

    int insert(V2SvcPorts record);

    int insertSelective(V2SvcPorts record);

    V2SvcPorts selectByPrimaryKey(Integer id);

    List<V2SvcPorts> selectAllByServiceId(Integer serviceId);

    int updateByPrimaryKeySelective(V2SvcPorts record);

    int updateByPrimaryKey(V2SvcPorts record);

    List<Integer> selectAllNodeport();

    List<Integer> selectAllByServiceIdExcept(Integer serviceId);
}