package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2PersistentVolumeClaim;
import org.springframework.stereotype.Repository;

/**
 * @describe
 * @Author Zuxingyu zuxingyu<zuxingyu@beyondcent.com>
 * @Date 2018/02/28
 */
@Repository(value = "v2PersistentVolumeClaimMapper")
public interface V2PersistentVolumeClaimMapper {
    
    int insert(V2PersistentVolumeClaim pv);
    
    int deleteByPrimaryKey(Integer id);
    
    V2PersistentVolumeClaim selectByPrimaryKey(Integer id);
    
    V2PersistentVolumeClaim selectByPersistentVolumeName(String volumeName);
}
