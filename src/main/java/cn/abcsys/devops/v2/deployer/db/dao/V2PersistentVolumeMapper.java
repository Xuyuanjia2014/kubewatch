package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2PersistentVolume;
import org.springframework.stereotype.Repository;

/**
 * @describe
 * @Author Zuxingyu zuxingyu<zuxingyu@beyondcent.com>
 * @Date 2018/02/28
 */
@Repository(value = "v2PersistentVolumeMapper")
public interface V2PersistentVolumeMapper {
    
    int insert(V2PersistentVolume pv);
    
    int deleteByPrimaryKey(Integer id);
    
    V2PersistentVolume selectByPrimaryKey(Integer id);
}
