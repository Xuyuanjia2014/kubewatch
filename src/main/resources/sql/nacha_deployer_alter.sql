-- 4-9 add by xzg
alter table project_core  modify column project_description text  DEFAULT NULL COMMENT '描述';
-- 4-12修改project_desc字段类型
-- ----------------------------
-- Procedure structure for insertOrGetProjectCoreIfNotExist
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetProjectCoreIfNotExist`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetProjectCoreIfNotExist`(IN namespace varchar(200),IN project_desc text,IN cluster_id int(11),IN default_env_id int(11), IN env_id int(11),OUT project_id int(11))
BEGIN
	DECLARE real_env int(11) DEFAULT default_env_id;

	start transaction;

	IF env_id IS NOT NULL THEN
		set real_env = env_id;
	END IF;
	SET project_id = NULL;

  INSERT INTO project_core (project_name,project_description,project_runtime_id,project_comments,project_create_datetime,project_delete_datetime,project_current_status,project_namespace,cluster_id)
	SELECT namespace,project_desc,real_env,NULL,NOW(),NULL,"created",namespace,cluster_id FROM DUAL
	WHERE NOT EXISTS( SELECT * FROM project_core WHERE project_core.project_name = namespace and project_core.cluster_id = cluster_id AND project_core.project_delete_datetime IS NULL);

	SELECT t.project_id into project_id from project_core t WHERE t.project_name = namespace and t.cluster_id = cluster_id and t.project_delete_datetime IS NULL;

	commit;
END
;;
DELIMITER ;


-- 4-17修改存储过程insertOrGetProjectCoreIfNotExist，添加字段createtime
-- ----------------------------
-- Procedure structure for insertOrGetProjectCoreIfNotExist
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetProjectCoreIfNotExist`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetProjectCoreIfNotExist`(IN namespace varchar(200),IN project_desc text,IN cluster_id int(11),IN default_env_id int(11), IN env_id int(11),IN createtime datetime,OUT project_id int(11))
BEGIN
	DECLARE real_env int(11) DEFAULT default_env_id;

	start transaction;

	IF env_id IS NOT NULL THEN
		set real_env = env_id;
	END IF;
	SET project_id = NULL;

  INSERT INTO project_core (project_name,project_description,project_runtime_id,project_comments,project_create_datetime,project_delete_datetime,project_current_status,project_namespace,cluster_id)
	SELECT namespace,project_desc,real_env,NULL,createtime ,NULL,"created",namespace,cluster_id FROM DUAL
	WHERE NOT EXISTS( SELECT * FROM project_core WHERE project_core.project_name = namespace and project_core.cluster_id = cluster_id AND project_core.project_delete_datetime IS NULL);

	SELECT t.project_id into project_id from project_core t WHERE t.project_name = namespace and t.cluster_id = cluster_id and t.project_delete_datetime IS NULL;

	commit;
END
;;
DELIMITER ;

-- 20180508
create unique index project_index on project_core(project_name,cluster_id,project_create_datetime);
create unique index application_index on v2_application(project_id,application_name,platform_type,real_name,create_time);
create unique index version_index on v2_version(application_id,version_name,version_type,create_datetime);
create unique index img_index on v2_image_group(version_id,application_id,project_id,env_id,real_name,image_group_name,create_time);
create unique index pod_index on v2_pod(image_group_id,parent_name,real_name,update_time);
create unique index container_index on v2_container(pod_id,image_group_id,version_id,application_id,project_id,env_id,real_name,container_name,create_datetime);


-- 5-15
-- ----------------------------
-- Table structure for v2_policy_elastic
-- ----------------------------
DROP TABLE IF EXISTS `v2_policy_elastic`;
CREATE TABLE `v2_policy_elastic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `application_id` int(11) DEFAULT NULL,
  `application_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `cpu_ceiling` float DEFAULT NULL,
  `cpu_floor` float DEFAULT NULL,
  `mem_ceiling` float DEFAULT NULL,
  `mem_floor` float DEFAULT NULL,
  `network_ceiling` float DEFAULT NULL,
  `network_floor` float DEFAULT NULL,
  `io_ceiling` float DEFAULT NULL,
  `io_floor` float DEFAULT NULL,
  `pirority` int(11) DEFAULT NULL,
  `max_count` int(11) DEFAULT NULL,
  `min_count` int(11) DEFAULT NULL,
  `expansion_steps` int(11) DEFAULT NULL,
  `shrinkage_steps` int(11) DEFAULT NULL,
  `loop_mills` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `delete_datetime` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
ALTER TABLE v2_container DROP INDEX container_index;
create unique index container_index on v2_container(pod_id,image_group_id,version_id,application_id,project_id,env_id,real_name,create_datetime);

-- 5-16
-- ----------------------------
-- Procedure structure for insertOrUpdateApplication
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrUpdateApplication`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrUpdateApplication`(IN project_id int(11),IN controller_name varchar(200),IN platform varchar(200),IN affair_type varchar(200),IN create_time datetime,OUT application_id int(11))
BEGIN
	SELECT t.id INTO application_id FROM v2_application t WHERE t.project_id = project_id AND t.application_name = controller_name;
	IF application_id IS NOT NULL THEN
		UPDATE v2_application t SET t.update_time = NOW() WHERE t.id = application_id;
	END IF;

	IF application_id IS NULL THEN
		INSERT INTO v2_application VALUES(NULL,project_id,controller_name,NULL,NULL,controller_name,platform,affair_type,NULL,create_time,NOW(),NULL,"created");
		SELECT LAST_INSERT_ID() INTO application_id;
	END IF;
END
;;
DELIMITER ;
-- 5-17
-- ----------------------------
-- Procedure structure for insertOrUpdateApplicationIfNotExist
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrUpdateApplicationIfNotExist`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrUpdateApplicationIfNotExist`(IN project_id int(11),IN controller_name varchar(200),IN platform varchar(200),IN affair_type varchar(200),IN create_time datetime,OUT application_id int(11))
BEGIN
	start transaction;

	SELECT t.id INTO application_id FROM v2_application t WHERE t.project_id = project_id AND t.application_name = controller_name AND t.delete_time IS NULL;
	IF application_id IS NOT NULL THEN
		UPDATE v2_application t SET t.update_time = NOW() WHERE t.id = application_id;
	END IF;

	IF application_id IS NULL THEN
		INSERT INTO v2_application (project_id,application_name,url,description,real_name,platform_type,affair_type,module_type,create_time,update_time,delete_time,`status`)
		SELECT project_id,controller_name,NULL,NULL,controller_name,platform,affair_type,NULL,create_time,NOW(),NULL,"created" FROM DUAL
    WHERE NOT EXISTS( SELECT * FROM v2_application WHERE v2_application.project_id = project_id AND v2_application.application_name = controller_name AND v2_application.delete_time IS NULL);
	END IF;

	SELECT t.id INTO application_id FROM v2_application t WHERE t.project_id = project_id AND t.application_name = controller_name AND t.delete_time IS NULL;

	commit;
END
;;
DELIMITER ;
-- ----------------------------
-- Procedure structure for getApplication
-- ----------------------------
DROP PROCEDURE IF EXISTS `getApplication`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `getApplication`(IN project_id int(11),IN controller_name varchar(200),IN platform varchar(200),IN affair_type varchar(200),IN create_time datetime,OUT application_id int(11))
BEGIN
	SELECT t.id INTO application_id FROM v2_application t WHERE t.project_id = project_id AND t.application_name = controller_name and t.delete_time IS NULL;
END
;;
DELIMITER ;
alter table v2_application modify column affair_type varchar(255)  DEFAULT NULL;

-- 5-21
-- ----------------------------
-- Procedure structure for insertOrUpdateContainer
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrUpdateContainer`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrUpdateContainer`(IN pod_id int(11), IN image_group_id int(11), IN version_id int(11),IN application_id int(11), IN project_id int(11), IN env_id int(11),IN pod_name varchar(255),IN real_name varchar(255), IN container_name varchar(255), IN image_name_tag varchar(255), IN container_status varchar(255),IN max_cpu varchar(255), IN max_memory varchar(255), IN host_ip varchar(255),IN host_name varchar(255),IN create_datetime datetime,IN  start_datetime datetime,IN project_name varchar(255),OUT container_id int(11))
BEGIN
	set container_id = NULL;
	SELECT t.id into container_id from v2_container t WHERE t.pod_id = pod_id and t.real_name = real_name;
	IF container_id is NOT NULL THEN
		UPDATE v2_container SET v2_container.`status` = container_status,v2_container.start_datetime = start_datetime,v2_container.pod_name = pod_name,v2_container.container_name = container_name,v2_container.real_name= real_name,v2_container.host_ip = host_ip,v2_container.host_name=host_name  WHERE v2_container.id = container_id;
	END IF;

	IF container_id is NULL THEN
		INSERT INTO v2_container VALUES(NULL,pod_id,image_group_id,version_id,application_id,project_id,env_id,pod_name,real_name,container_name,image_name_tag,container_status,max_cpu,max_memory,host_ip,host_name,create_datetime,start_datetime,project_name);
		SELECT LAST_INSERT_ID() INTO container_id;
	END IF;
END
;;
DELIMITER ;

-- 5-24
-- ----------------------------
-- Procedure structure for insertOrUpdateImageGroupIfNotExist
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrUpdateImageGroupIfNotExist`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrUpdateImageGroupIfNotExist`(IN version_id int(11),IN application_id int(11),IN project_id int(11),IN env_id int(11),IN base_name varchar(200),IN real_name varchar(200),IN uuid varchar(200),IN namespace varchar(200),IN apiversion varchar(200),IN kind varchar(200),IN replica int(11),IN app_name varchar(200),IN ip_host varchar(200),IN if_http varchar(20),IN api_port varchar(20),IN createtime datetime,IN image_group_strategy varchar(255),IN shrinkage_image_group_id int(11), IN target_replica int(11) ,IN old_replica int(11) , OUT ig_id int(11))
BEGIN
	set ig_id = NULL;
	select t.id into ig_id FROM v2_image_group t where t.version_id = version_id AND t.application_id = application_id and t.project_id = project_id and t.env_id = env_id and t.delete_time IS NULL;
	IF ig_id is NOT NULL THEN
		UPDATE v2_image_group t SET t.update_time = NOW(),t.replica = replica WHERE t.id = ig_id;
	END IF;
		INSERT INTO v2_image_group (version_id,application_id,project_id,env_id,version_group_id,
																real_name,uuid,image_group_name,description,namespace,api_version,
																kind,replica,parent_name,master_ip,master_type,master_port,create_time,
																delete_time,update_time,`status`,image_group_strategy,shrinkage_image_group_id,
																target_replica,old_replica)
		SELECT version_id,application_id,project_id,env_id,NULL,real_name,uuid,base_name,NULL,namespace,
								apiversion,kind,replica,app_name,ip_host,if_http,api_port,createtime,NULL,NOW(),"created",
								image_group_strategy,shrinkage_image_group_id,target_replica,old_replica FROM DUAL
		WHERE NOT EXISTS ( SELECT * FROM v2_image_group WHERE v2_image_group.version_id = version_id AND
											v2_image_group.application_id = application_id
											and v2_image_group.project_id = project_id
											and v2_image_group.env_id = env_id
											and v2_image_group.delete_time IS NULL);

	select t.id into ig_id FROM v2_image_group t where t.version_id = version_id AND t.application_id = application_id and t.project_id = project_id and t.env_id = env_id and t.delete_time IS NULL;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for deletePodAndContainersUUid
-- ----------------------------
DROP PROCEDURE IF EXISTS `deletePodAndContainersUUid`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `deletePodAndContainersUUid`(IN uuid varchar(255),IN real_name varchar(255),OUT pod_id int(11),OUT container_count int(11))
BEGIN
	set pod_id = NULL;
	SELECT t.id INTO pod_id FROM v2_pod t WHERE t.uuid = uuid and t.real_name = real_name;

	IF pod_id IS NOT NULL THEN
		SELECT count(*) into container_count FROM v2_container WHERE v2_container.pod_id = pod_id;
		DELETE FROM v2_container WHERE v2_container.pod_id = pod_id;
		DELETE FROM v2_pod WHERE v2_pod.id = pod_id;
	END IF;
END
;;
DELIMITER ;


-- ----------------------------
-- Procedure structure for insertOrGetProjectCoreIfNotExist
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetProjectCoreIfNotExist`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetProjectCoreIfNotExist`(IN namespace varchar(200),IN project_desc text,IN cluster_id int(11),IN default_env_id int(11), IN env_id int(11),IN createtime datetime,OUT project_id int(11))
BEGIN
	DECLARE real_env int(11) DEFAULT default_env_id;
	IF env_id IS NOT NULL THEN
		set real_env = env_id;
	END IF;
	SET project_id = NULL;

  INSERT INTO project_core (project_name,project_description,project_runtime_id,project_comments,project_create_datetime,project_delete_datetime,project_current_status,project_namespace,cluster_id)
	SELECT namespace,project_desc,real_env,NULL,createtime ,NULL,"created",namespace,cluster_id FROM DUAL
	WHERE NOT EXISTS( SELECT * FROM project_core WHERE project_core.project_name = namespace and project_core.cluster_id = cluster_id AND project_core.project_delete_datetime IS NULL);

	SELECT t.project_id into project_id from project_core t WHERE t.project_name = namespace and t.cluster_id = cluster_id and t.project_delete_datetime IS NULL;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insertOrUpdateApplicationIfNotExist
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrUpdateApplicationIfNotExist`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrUpdateApplicationIfNotExist`(IN project_id int(11),IN controller_name varchar(200),IN platform varchar(200),IN affair_type varchar(200),IN create_time datetime,OUT application_id int(11))
BEGIN
	SELECT t.id INTO application_id FROM v2_application t WHERE t.project_id = project_id AND t.application_name = controller_name AND t.delete_time IS NULL;
	IF application_id IS NOT NULL THEN
		UPDATE v2_application t SET t.update_time = NOW() WHERE t.id = application_id;
	END IF;

	IF application_id IS NULL THEN
		INSERT INTO v2_application (project_id,application_name,url,description,real_name,platform_type,affair_type,module_type,create_time,update_time,delete_time,`status`)
		SELECT project_id,controller_name,NULL,NULL,controller_name,platform,affair_type,NULL,create_time,NOW(),NULL,"created" FROM DUAL
    WHERE NOT EXISTS( SELECT * FROM v2_application WHERE v2_application.project_id = project_id AND v2_application.application_name = controller_name AND v2_application.delete_time IS NULL);
	END IF;

	SELECT t.id INTO application_id FROM v2_application t WHERE t.project_id = project_id AND t.application_name = controller_name AND t.delete_time IS NULL;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insertOrGetVersionIfNotExist
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetVersionIfNotExist`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetVersionIfNotExist`(IN application_id int(11),IN vname varchar(200),IN vtype varchar(255),OUT version_id int(11))
BEGIN
	set version_id = NULL;

	INSERT INTO v2_version (application_id,version_name,version_type,replica,create_datetime)
	SELECT application_id,vname,vtype,1,NOW() FROM DUAL
	WHERE NOT EXISTS( SELECT * FROM  v2_version WHERE v2_version.application_id = application_id and v2_version.version_name = vname and v2_version.delete_datetime IS NULL);

	SELECT t.id into version_id from v2_version t WHERE t.application_id = application_id and t.version_name = vname and t.delete_datetime IS NULL;
END
;;
DELIMITER ;

-- 6-6修改v2_container字段
ALTER TABLE v2_container CHANGE max_cpu limit_cpu varchar(255);
ALTER TABLE v2_container CHANGE max_memory limit_memory varchar(255);
ALTER TABLE v2_container ADD request_cpu varchar(255);
ALTER TABLE v2_container ADD request_memory varchar(255);
-- ----------------------------
-- Procedure structure for insertOrUpdateContainer
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrUpdateContainer`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrUpdateContainer`(IN pod_id int(11), IN image_group_id int(11), IN version_id int(11),IN application_id int(11), IN project_id int(11), IN env_id int(11),IN pod_name varchar(255),IN real_name varchar(255), IN container_name varchar(255), IN image_name_tag varchar(255), IN container_status varchar(255),IN limit_cpu varchar(255), IN limit_memory varchar(255),IN request_cpu varchar(255), IN request_memory varchar(255), IN host_ip varchar(255),IN host_name varchar(255),IN create_datetime datetime,IN  start_datetime datetime,IN project_name varchar(255),OUT container_id int(11))
BEGIN
	set container_id = NULL;
	SELECT t.id into container_id from v2_container t WHERE t.pod_id = pod_id and t.real_name = real_name;
	IF container_id is NOT NULL THEN
		UPDATE v2_container SET v2_container.`status` = container_status,v2_container.start_datetime = start_datetime,v2_container.pod_name = pod_name,v2_container.container_name = container_name,v2_container.real_name= real_name,v2_container.host_ip = host_ip,v2_container.host_name=host_name  WHERE v2_container.id = container_id;
	END IF;

	IF container_id is NULL THEN
		INSERT INTO v2_container
		 (id,pod_id,image_group_id,version_id,application_id,project_id,env_id,pod_name,real_name,container_name,image_name_tag,status,limit_cpu,limit_memory,request_cpu,request_memory,host_ip,host_name,create_datetime,start_datetime,project_name) VALUES(NULL,pod_id,image_group_id,version_id,application_id,project_id,env_id,pod_name,real_name,container_name,image_name_tag,container_status,limit_cpu,limit_memory,request_cpu,request_memory,host_ip,host_name,create_datetime,start_datetime,project_name);
		SELECT LAST_INSERT_ID() INTO container_id;
	END IF;
END
;;
DELIMITER ;

-- 6-8修改deletePodAndContainersUUid存储过程
-- ----------------------------
-- Procedure structure for insertOrUpdateContainer
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrUpdateContainer`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrUpdateContainer`(IN pod_id int(11), IN image_group_id int(11), IN version_id int(11),IN application_id int(11), IN project_id int(11), IN env_id int(11),IN pod_name varchar(255),IN real_name varchar(255), IN container_name varchar(255), IN image_name_tag varchar(255), IN container_status varchar(255),IN limit_cpu varchar(255), IN limit_memory varchar(255),IN request_cpu varchar(255), IN request_memory varchar(255), IN host_ip varchar(255),IN host_name varchar(255),IN create_datetime datetime,IN  start_datetime datetime,IN project_name varchar(255),IN uuid varchar(255),OUT container_id int(11))
BEGIN
	set container_id = NULL;
	SELECT t.id into container_id from v2_container t WHERE t.pod_id = pod_id and t.real_name = real_name;
	IF container_id is NOT NULL THEN
		UPDATE v2_container SET v2_container.`status` = container_status,v2_container.start_datetime = start_datetime,v2_container.pod_name = pod_name,v2_container.container_name = container_name,v2_container.real_name= real_name,v2_container.host_ip = host_ip,v2_container.host_name=host_name  WHERE v2_container.id = container_id;
	END IF;

	IF container_id is NULL THEN
		INSERT INTO v2_container
		 (id,pod_id,image_group_id,version_id,application_id,project_id,env_id,pod_name,real_name,container_name,image_name_tag,status,limit_cpu,limit_memory,request_cpu,request_memory,host_ip,host_name,create_datetime,start_datetime,project_name,uuid) VALUES(NULL,pod_id,image_group_id,version_id,application_id,project_id,env_id,pod_name,real_name,container_name,image_name_tag,container_status,limit_cpu,limit_memory,request_cpu,request_memory,host_ip,host_name,create_datetime,start_datetime,project_name,uuid);
		SELECT LAST_INSERT_ID() INTO container_id;
	END IF;
END
;;
DELIMITER ;

-- 6-8修改v2_container字段uuid
ALTER TABLE v2_container ADD uuid varchar(255);

-- 6-20增加application_events联合索引
create unique index application_events_index on application_events(application_core_id,application_event_type,application_event_start_datetime,application_event_description);

-- 6-26修改存储过程
-- ----------------------------
-- Procedure structure for insertOrGetProjectCore
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetProjectCore`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetProjectCore`(IN namespace varchar(200),IN project_desc text,IN cluster_id int(11),IN default_env_id int(11), IN env_id int(11),IN createtime datetime,OUT project_id int(11))
BEGIN
	DECLARE real_env int(11) DEFAULT default_env_id;
	IF env_id IS NOT NULL THEN
		set real_env = env_id;
	END IF;
	SET project_id = NULL;
  SELECT t.project_id into project_id from project_core t WHERE t.project_name = namespace and t.cluster_id = cluster_id and t.project_delete_datetime IS NULL;
  IF project_id IS NULL THEN
		INSERT INTO project_core (project_name,project_description,project_runtime_id,project_comments,project_create_datetime,project_delete_datetime,project_current_status,project_namespace,cluster_id)
		VALUES(namespace,project_desc,real_env,NULL,createtime ,NULL,"created",namespace,cluster_id);
  SELECT t.project_id into project_id from project_core t WHERE t.project_name = namespace and t.cluster_id = cluster_id and t.project_delete_datetime IS NULL;

	END IF;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insertOrUpdateApplication
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrUpdateApplication`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrUpdateApplication`(IN project_id int(11),IN controller_name varchar(200),IN platform varchar(200),IN affair_type varchar(200),IN create_time datetime,OUT application_id int(11))
BEGIN
  SELECT t.id INTO application_id FROM v2_application t WHERE t.project_id = project_id AND t.application_name = controller_name AND t.delete_time IS NULL;
	IF application_id IS NOT NULL THEN
		UPDATE v2_application t SET t.update_time = NOW() WHERE t.id = application_id;
	END IF;

	IF application_id IS NULL THEN
		INSERT INTO v2_application (project_id,application_name,url,description,real_name,platform_type,affair_type,module_type,create_time,update_time,delete_time,`status`)
		VALUES(project_id,controller_name,NULL,NULL,controller_name,platform,affair_type,NULL,create_time,NOW(),NULL,"created");
    SELECT t.id INTO application_id FROM v2_application t WHERE t.project_id = project_id AND t.application_name = controller_name AND t.delete_time IS NULL;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insertOrGetVersion
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetVersion`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetVersion`(IN application_id int(11),IN vname varchar(200),IN vtype varchar(255),OUT version_id int(11))
BEGIN
	set version_id = NULL;
	SELECT t.id into version_id from v2_version t WHERE t.application_id = application_id and t.version_name = vname and t.delete_datetime IS NULL;
	IF version_id is NULL THEN
		INSERT INTO v2_version (application_id,version_name,version_type,replica,create_datetime)
		VALUES(application_id,vname,vtype,1,NOW());
		SELECT t.id into version_id from v2_version t WHERE t.application_id = application_id and t.version_name = vname and t.delete_datetime IS NULL;

	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insertOrUpdateImageGroup
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrUpdateImageGroup`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrUpdateImageGroup`(IN version_id int(11),IN application_id int(11),IN project_id int(11),IN env_id int(11),IN base_name varchar(200),IN real_name varchar(200),IN uuid varchar(200),IN namespace varchar(200),IN apiversion varchar(200),IN kind varchar(200),IN replica int(11),IN app_name varchar(200),IN ip_host varchar(200),IN if_http varchar(20),IN api_port varchar(20),IN createtime datetime,IN image_group_strategy varchar(255),IN shrinkage_image_group_id int(11), IN target_replica int(11) ,IN old_replica int(11) , OUT ig_id int(11))
BEGIN
	set ig_id = NULL;
	select t.id into ig_id FROM v2_image_group t where t.version_id = version_id AND t.application_id = application_id and t.project_id = project_id and t.env_id = env_id and t.delete_time IS NULL;
	IF ig_id is NOT NULL THEN
		UPDATE v2_image_group t SET t.update_time = NOW(),t.replica = replica WHERE t.id = ig_id;
	END IF;
	IF ig_id is NULL THEN
		INSERT INTO v2_image_group
		    (version_id,application_id,project_id,env_id,version_group_id,
																real_name,uuid,image_group_name,description,namespace,api_version,
																kind,replica,parent_name,master_ip,master_type,master_port,create_time,
																delete_time,update_time,`status`,image_group_strategy,shrinkage_image_group_id,
																target_replica,old_replica)
		VALUES(version_id,application_id,project_id,env_id,NULL,real_name,uuid,base_name,NULL,namespace,
								apiversion,kind,replica,app_name,ip_host,if_http,api_port,createtime,NULL,NOW(),"created",
								image_group_strategy,shrinkage_image_group_id,target_replica,old_replica);
    select t.id into ig_id FROM v2_image_group t where t.version_id = version_id AND t.application_id = application_id and t.project_id = project_id and t.env_id = env_id and t.delete_time IS NULL;
	END IF;
END
;;
DELIMITER ;
