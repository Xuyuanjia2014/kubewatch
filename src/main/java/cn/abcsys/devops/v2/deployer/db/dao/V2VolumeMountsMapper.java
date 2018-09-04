package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2VolumeMounts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "v2VolumeMountsMapper")
public interface V2VolumeMountsMapper {

    List<V2VolumeMounts> selectByImageId(Integer imageId);

    int deleteByPrimaryKey(Integer id);

    int deleteByImageId(Integer imageId);

    int insert(V2VolumeMounts record);

    int insertSelective(V2VolumeMounts record);

    V2VolumeMounts selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(V2VolumeMounts record);

    int updateByPrimaryKey(V2VolumeMounts record);
}