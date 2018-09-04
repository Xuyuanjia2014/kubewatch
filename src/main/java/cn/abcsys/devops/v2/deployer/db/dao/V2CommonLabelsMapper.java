package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.V2CommonLabels;
import org.springframework.stereotype.Repository;

@Repository(value = "v2CommonLabelsMapper")
public interface V2CommonLabelsMapper {
    int insert(V2CommonLabels record);

    int insertSelective(V2CommonLabels record);
    
    V2CommonLabels selectByPrimaryKey(Integer id);
}