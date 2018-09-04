package cn.abcsys.devops.v2.deployer.db.dao;

import cn.abcsys.devops.v2.deployer.db.model.ApplicationEvents;
import org.springframework.stereotype.Repository;

@Repository(value = "applicationEventsMapper")
public interface ApplicationEventsMapper {
    int insertSelective(ApplicationEvents record);

    Integer selectCountByApplicationEvent(ApplicationEvents record);
}