package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2Ports;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2PortsMapper")
public interface V2PortsMapper {
    List<V2Ports> selectByImageId(Integer imageId);

    int deleteByPrimaryKey(Integer id);

    int deleteByImageId(Integer imageId);

    int insert(V2Ports record);

    int insertSelective(V2Ports record);

    V2Ports selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2Ports record);

    int updateByPrimaryKey(V2Ports record);
}