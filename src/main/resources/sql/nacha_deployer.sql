/*
Navicat MariaDB Data Transfer

Source Server         : 133.133.133.122
Source Server Version : 100214
Source Host           : 133.133.133.122:3306
Source Database       : nacha_deployer

Target Server Type    : MariaDB
Target Server Version : 100214
File Encoding         : 65001

Date: 2018-05-16 22:56:35
*/
USE nacha_deployer;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for application_events
-- ----------------------------
DROP TABLE IF EXISTS `application_events`;
CREATE TABLE `application_events` (
  `application_event_id` int(11) NOT NULL AUTO_INCREMENT,
  `application_core_id` int(11) DEFAULT NULL,
  `application_event_type` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `application_event_current_status` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `application_event_policy` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `application_event_start_datetime` datetime DEFAULT NULL,
  `application_event_finish_datetime` datetime DEFAULT NULL,
  `application_event_description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `env_id` int(11) DEFAULT NULL,
  `application_event_description2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`application_event_id`),
  KEY `fk3` (`application_core_id`),
  UNIQUE KEY `application_events_index` (`application_core_id`,`application_event_type`,`application_event_start_datetime`,`application_event_description`)
) ENGINE=InnoDB AUTO_INCREMENT=2962 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for cluster_env
-- ----------------------------
DROP TABLE IF EXISTS `cluster_env`;
CREATE TABLE `cluster_env` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_id` int(11) NOT NULL,
  `cluster_id` int(11) NOT NULL,
  `env_id` int(11) NOT NULL,
  `cluster_service_ip` varchar(90) NOT NULL,
  `protocol_type` varchar(12) DEFAULT 'http',
  `port` varchar(8) DEFAULT '8080',
  `PLATFORM_TYPE` varchar(255) DEFAULT 'kubernetes',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for configuration_file
-- ----------------------------
DROP TABLE IF EXISTS `configuration_file`;
CREATE TABLE `configuration_file` (
  `file_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '配置文件id',
  `file_name` varchar(30) DEFAULT NULL COMMENT '配置文件名称',
  `file_content` blob DEFAULT NULL COMMENT '配置文件内容',
  `group_id` bigint(11) DEFAULT NULL COMMENT '配置组id',
  `mount_path` varchar(100) DEFAULT NULL COMMENT '挂载路径',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1存在 0删除',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for configuration_group
-- ----------------------------
DROP TABLE IF EXISTS `configuration_group`;
CREATE TABLE `configuration_group` (
  `group_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `group_name` varchar(30) DEFAULT NULL COMMENT '配置分组名称',
  `group_file_sum` int(5) DEFAULT NULL COMMENT '配置分组内配置文件数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `env_id` bigint(11) DEFAULT NULL COMMENT '环境ID',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1存在 0删除',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for instance_core
-- ----------------------------
DROP TABLE IF EXISTS `instance_core`;
CREATE TABLE `instance_core` (
  `instance_core_id` int(11) NOT NULL AUTO_INCREMENT,
  `instance_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `instance_rename` varchar(100) COLLATE utf8_bin NOT NULL,
  `instance_proerties` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `instance_namespace` varchar(20) COLLATE utf8_bin NOT NULL,
  `instance_type` varchar(20) COLLATE utf8_bin NOT NULL,
  `instance_create_datetime` datetime DEFAULT NULL,
  `instance_delete_datetime` datetime DEFAULT NULL,
  `instance_current_status` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `instance_image` varchar(200) COLLATE utf8_bin NOT NULL,
  `instance_image_tag` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `instance_cpu` varchar(20) COLLATE utf8_bin NOT NULL,
  `instance_memory` varchar(20) COLLATE utf8_bin NOT NULL,
  `instance_request_cpu` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `instance_request_memory` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `instance_network` varchar(20) COLLATE utf8_bin NOT NULL,
  `instance_image_pull_secret` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `app_id` int(11) DEFAULT NULL,
  `app_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cluster_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `container_status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `container_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `image_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cmd` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `application_runtime_id` int(11) DEFAULT NULL,
  `application_tenant_id` int(11) DEFAULT NULL,
  `component_id` int(11) DEFAULT NULL,
  `master_port` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `master_type` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `instance_replica` int(11) DEFAULT NULL,
  PRIMARY KEY (`instance_core_id`),
  UNIQUE KEY `unique_name` (`instance_rename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for instance_envs
-- ----------------------------
DROP TABLE IF EXISTS `instance_envs`;
CREATE TABLE `instance_envs` (
  `instance_envs_id` int(11) NOT NULL AUTO_INCREMENT,
  `instance_core_id` int(11) NOT NULL,
  `instance_envs_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `instance_envs_value` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `instance_envs_description` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `instance_envs_create_datetime` datetime DEFAULT NULL,
  `instance_envs_delete_datetime` datetime DEFAULT NULL,
  `instance_envs_current_status` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`instance_envs_id`),
  KEY `envs_core` (`instance_core_id`),
  CONSTRAINT `envs_core` FOREIGN KEY (`instance_core_id`) REFERENCES `instance_core` (`instance_core_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for instance_events
-- ----------------------------
DROP TABLE IF EXISTS `instance_events`;
CREATE TABLE `instance_events` (
  `instance_events_id` int(11) NOT NULL AUTO_INCREMENT,
  `instance_core_id` int(11) DEFAULT NULL,
  `instance_events_source` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `instance_events_type` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `instance_events_datetime` datetime DEFAULT NULL,
  `instance_events_original` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `instance_events_content` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `instance_events_comment` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `instance_events_create_datetime` datetime DEFAULT NULL,
  `instance_events_delete_datetime` datetime DEFAULT NULL,
  `instance_events_current_status` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`instance_events_id`),
  KEY `events_core` (`instance_core_id`),
  CONSTRAINT `events_core` FOREIGN KEY (`instance_core_id`) REFERENCES `instance_core` (`instance_core_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for instance_ports
-- ----------------------------
DROP TABLE IF EXISTS `instance_ports`;
CREATE TABLE `instance_ports` (
  `instance_ports_id` int(11) NOT NULL AUTO_INCREMENT,
  `instance_core_id` int(11) DEFAULT NULL,
  `instance_ports_port` int(11) DEFAULT NULL,
  `instance_ports_target_port` int(11) DEFAULT NULL,
  `instance_ports_node_port` int(11) DEFAULT NULL,
  `instance_ports_cluster_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `instance_ports_description` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `instance_ports_create_datetime` datetime DEFAULT NULL,
  `instance_ports_delete_datetime` datetime DEFAULT NULL,
  `instance_ports_current_status` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`instance_ports_id`),
  KEY `ports_core` (`instance_core_id`),
  CONSTRAINT `ports_core` FOREIGN KEY (`instance_core_id`) REFERENCES `instance_core` (`instance_core_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for instance_volumes
-- ----------------------------
DROP TABLE IF EXISTS `instance_volumes`;
CREATE TABLE `instance_volumes` (
  `instance_volumes_id` int(11) NOT NULL AUTO_INCREMENT,
  `instance_core_id` int(11) DEFAULT NULL,
  `instance_volumes_type` varchar(20) COLLATE utf8_bin NOT NULL,
  `instance_volumes_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `instance_volumes_mount_path` varchar(400) COLLATE utf8_bin DEFAULT NULL,
  `instance_volumes_server` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `instance_volumes_path` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `instance_volumes_create_datetime` datetime DEFAULT NULL,
  `instance_volumes_delete_datetime` datetime DEFAULT NULL,
  `instance_volumes_current_status` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`instance_volumes_id`),
  KEY `volumes_core` (`instance_core_id`),
  CONSTRAINT `volumes_core` FOREIGN KEY (`instance_core_id`) REFERENCES `instance_core` (`instance_core_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for project_core
-- ----------------------------
DROP TABLE IF EXISTS `project_core`;
CREATE TABLE `project_core` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `project_description` text COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `project_runtime_id` int(11) DEFAULT NULL,
  `project_comments` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `project_create_datetime` datetime DEFAULT NULL,
  `project_delete_datetime` datetime DEFAULT NULL,
  `project_current_status` varchar(255) COLLATE utf8_bin DEFAULT 'created',
  `project_namespace` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cluster_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  UNIQUE KEY `project_index` (`project_name`,`cluster_id`,`project_create_datetime`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_application
-- ----------------------------
DROP TABLE IF EXISTS `v2_application`;
CREATE TABLE `v2_application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `application_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `real_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `platform_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `affair_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `module_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_time` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `application_index` (`project_id`,`application_name`,`platform_type`,`real_name`,`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=719 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_args
-- ----------------------------
DROP TABLE IF EXISTS `v2_args`;
CREATE TABLE `v2_args` (
  `id` int(11) NOT NULL COMMENT 'id',
  `image_id` int(11) DEFAULT NULL,
  `parameter` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '参数',
  `create_time` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_container
-- ----------------------------
DROP TABLE IF EXISTS `v2_container`;
CREATE TABLE `v2_container` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pod_id` int(11) DEFAULT NULL,
  `image_group_id` int(11) DEFAULT NULL,
  `version_id` int(11) DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `env_id` int(11) DEFAULT NULL,
  `pod_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `real_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `container_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `image_name_tag` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `limit_cpu` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `limit_memory` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `request_cpu` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `request_memory` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `start_datetime` datetime DEFAULT NULL,
  `project_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `uuid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `container_index` (`pod_id`,`image_group_id`,`version_id`,`application_id`,`project_id`,`env_id`,`real_name`,`create_datetime`)
) ENGINE=InnoDB AUTO_INCREMENT=5092 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_deployment
-- ----------------------------
DROP TABLE IF EXISTS `v2_deployment`;
CREATE TABLE `v2_deployment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `uid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `resource_version` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `namespace` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_envs
-- ----------------------------
DROP TABLE IF EXISTS `v2_envs`;
CREATE TABLE `v2_envs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_id` int(11) DEFAULT NULL COMMENT '容器id',
  `envs_key` varchar(2000) COLLATE utf8_bin DEFAULT '' COMMENT 'key',
  `envs_value` varchar(2000) COLLATE utf8_bin DEFAULT '' COMMENT 'value',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2406 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_event
-- ----------------------------
DROP TABLE IF EXISTS `v2_event`;
CREATE TABLE `v2_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `api_version` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `version_id` int(11) DEFAULT NULL,
  `first_timestamp` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `kind` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `message` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `namespace` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `resource_version` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `self_link` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `uid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `host` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `v2_event_resource_version_index` (`resource_version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_image
-- ----------------------------
DROP TABLE IF EXISTS `v2_image`;
CREATE TABLE `v2_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_group_id` int(11) DEFAULT NULL COMMENT '实例id',
  `container_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `image_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '镜像名称',
  `image_tag` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '标签',
  `image_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pull_secret` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '私有镜像库需要',
  `storage_time` datetime DEFAULT NULL,
  `build_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2855 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_image_group
-- ----------------------------
DROP TABLE IF EXISTS `v2_image_group`;
CREATE TABLE `v2_image_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version_id` int(11) DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `env_id` int(11) DEFAULT NULL,
  `version_group_id` int(11) DEFAULT NULL,
  `real_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `uuid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `image_group_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `namespace` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'default',
  `api_version` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `kind` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'rc,rs,deploy,pod',
  `replica` int(11) DEFAULT NULL,
  `parent_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'rc,rs,deploy',
  `master_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `master_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `master_port` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `delete_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `image_group_strategy` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `shrinkage_image_group_id` int(11) DEFAULT NULL,
  `target_replica` int(11) DEFAULT NULL,
  `old_replica` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `img_index` (`version_id`,`application_id`,`project_id`,`env_id`,`real_name`,`image_group_name`,`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=50002352 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_labels
-- ----------------------------
DROP TABLE IF EXISTS `v2_labels`;
CREATE TABLE `v2_labels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_group_id` int(11) DEFAULT NULL,
  `label_type` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'labels,selector,pod-labels,nodeselector',
  `label_key` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'key',
  `label_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'value',
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9659 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_network_labels
-- ----------------------------
DROP TABLE IF EXISTS `v2_network_labels`;
CREATE TABLE `v2_network_labels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `network_policy_id` int(11) DEFAULT NULL,
  `label_type` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'labels,selector,pod-labels,nodeselector',
  `label_key` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'key',
  `label_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'value',
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_network_policy
-- ----------------------------
DROP TABLE IF EXISTS `v2_network_policy`;
CREATE TABLE `v2_network_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `application_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `env_id` int(11) DEFAULT NULL,
  `apiversion` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `kind` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `object_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `namespace` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `master_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `master_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `master_port` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_dateime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_network_ports
-- ----------------------------
DROP TABLE IF EXISTS `v2_network_ports`;
CREATE TABLE `v2_network_ports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `network_id` int(11) DEFAULT NULL,
  `port_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `protocol` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `port_value` int(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_pod
-- ----------------------------
DROP TABLE IF EXISTS `v2_pod`;
CREATE TABLE `v2_pod` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `image_group_id` int(11) DEFAULT NULL,
  `file_path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `kind` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `replace_pod_id` int(11) DEFAULT NULL,
  `replace_old_parent_id` int(11) DEFAULT NULL,
  `if_delete_parent` int(11) DEFAULT NULL,
  `parent_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `real_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `namespace` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `api_version` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `resource_version` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pod_index` (`image_group_id`,`parent_name`,`real_name`,`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=5111 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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

-- ----------------------------
-- Table structure for v2_ports
-- ----------------------------
DROP TABLE IF EXISTS `v2_ports`;
CREATE TABLE `v2_ports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_id` int(11) DEFAULT NULL,
  `port_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `protocol` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `port_value` int(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10999 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_probe
-- ----------------------------
DROP TABLE IF EXISTS `v2_probe`;
CREATE TABLE `v2_probe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_id` int(11) DEFAULT NULL,
  `probe_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `request_type` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT 'liveless or readless',
  `probe_path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `probe_port` int(11) DEFAULT NULL,
  `scheme` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `initial_delay_seconds` int(11) DEFAULT NULL,
  `timeout_seconds` int(11) DEFAULT NULL,
  `success_threshold` int(11) DEFAULT NULL,
  `failure_threshold` int(11) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=234 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_resources
-- ----------------------------
DROP TABLE IF EXISTS `v2_resources`;
CREATE TABLE `v2_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_id` int(11) DEFAULT NULL,
  `max_cpu` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `max_mem` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `min_cpu` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `min_mem` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `network` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `max_cpu_value` double(10,1) DEFAULT NULL,
  `max_mem_value` double(10,1) DEFAULT NULL,
  `min_cpu_value` double(10,1) DEFAULT NULL,
  `min_mem_value` double(10,1) DEFAULT NULL,
  `image_group_id` int(11) DEFAULT NULL,
  `version_id` int(11) DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `env_id` int(11) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2660 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_svc
-- ----------------------------
DROP TABLE IF EXISTS `v2_svc`;
CREATE TABLE `v2_svc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `application_id` int(11) DEFAULT NULL,
  `apiversion` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `kind` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `svc_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `svc_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `namespace` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cluster_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `master_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `master_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `master_port` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_dateime` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `version_id` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_svc_labels
-- ----------------------------
DROP TABLE IF EXISTS `v2_svc_labels`;
CREATE TABLE `v2_svc_labels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `svc_id` int(11) DEFAULT NULL,
  `label_type` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'labels,selector,pod-labels,nodeselector',
  `label_key` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `label_value` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=193 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_svc_ports
-- ----------------------------
DROP TABLE IF EXISTS `v2_svc_ports`;
CREATE TABLE `v2_svc_ports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `svc_id` int(11) DEFAULT NULL,
  `port_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `protocol` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `port_value` int(11) DEFAULT NULL,
  `target_port` int(11) DEFAULT NULL,
  `node_port` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=767 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_version
-- ----------------------------
DROP TABLE IF EXISTS `v2_version`;
CREATE TABLE `v2_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `application_id` int(11) DEFAULT NULL,
  `version_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `version_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `replica` int(11) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `delete_datetime` datetime DEFAULT NULL,
  `old_version_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `version_index` (`application_id`,`version_name`,`version_type`,`create_datetime`)
) ENGINE=InnoDB AUTO_INCREMENT=50004048 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_volumes
-- ----------------------------
DROP TABLE IF EXISTS `v2_volumes`;
CREATE TABLE `v2_volumes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_group_id` int(11) DEFAULT NULL,
  `host_path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `volume_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `volume_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `version_id` int(11) DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `env_id` int(11) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18851 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for v2_volume_mounts
-- ----------------------------
DROP TABLE IF EXISTS `v2_volume_mounts`;
CREATE TABLE `v2_volume_mounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_id` int(11) DEFAULT NULL,
  `mount_path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `volume_mount_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `image_group_id` int(11) DEFAULT NULL,
  `version_id` int(11) DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `env_id` int(11) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=557 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Procedure structure for checkProjectCore
-- ----------------------------
DROP PROCEDURE IF EXISTS `checkProjectCore`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `checkProjectCore`(IN namespace varchar(200),IN cluster_id int(11), OUT project_id int(11),OUT env_id int(11))
BEGIN
 SET env_id = NULL;
 set project_id = NULL;
 SELECT t.project_id,t.project_runtime_id INTO project_id,env_id
 FROM project_core t WHERE t.project_name = namespace AND t.cluster_id = cluster_id and t.project_delete_datetime IS NULL;

 IF project_id is NULL THEN
		SELECT t.project_id,t.project_runtime_id INTO project_id,env_id
		FROM project_core t WHERE t.project_name = namespace AND t.cluster_id = cluster_id and t.project_delete_datetime IS NOT NULL
		ORDER BY t.project_delete_datetime DESC LIMIT 0,1;
 END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for deleteAll
-- ----------------------------
DROP PROCEDURE IF EXISTS `deleteAll`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `deleteAll`()
BEGIN
	delete from project_core;
delete from v2_application;
delete from v2_version;
delete from v2_image_group;
delete from v2_image;
delete from v2_envs;
delete from v2_ports;
delete from v2_probe;
delete from v2_volumes;
delete from v2_volume_mounts;
delete from v2_container;
delete from v2_labels;
delete from v2_resources;
delete from v2_pod;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for deleteApplication
-- ----------------------------
DROP PROCEDURE IF EXISTS `deleteApplication`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `deleteApplication`(IN application_id int(11),OUT result tinyint(1))
BEGIN
	UPDATE v2_application SET v2_application.`status` = "deleted", v2_application.delete_time = NOW()
		WHERE v2_application.id = application_id;
	SET result = true;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for deleteDirtyInstanceCore
-- ----------------------------
DROP PROCEDURE IF EXISTS `deleteDirtyInstanceCore`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `deleteDirtyInstanceCore`(appName varchar(255))
BEGIN
DELETE FROM instance_volumes WHERE instance_volumes.instance_core_id = instaceCoreId;
DELETE FROM instance_ports WHERE instance_ports.instance_core_id = instaceCoreId;
DELETE FROM instance_envs WHERE instance_envs.instance_core_id = instaceCoreId;
DELETE FROM instance_events WHERE instance_events.instance_core_id = instaceCoreId;
DELETE FROM instance_core WHERE instance_core.instance_core_id = instaceCoreId;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for deleteDirtyInstanceCoreBatch
-- ----------------------------
DROP PROCEDURE IF EXISTS `deleteDirtyInstanceCoreBatch`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `deleteDirtyInstanceCoreBatch`(appName varchar(255))
BEGIN
DECLARE badId INT DEFAULT 0;
DECLARE done INT DEFAULT 0;
DECLARE cur1 CURSOR FOR SELECT instance_core.instance_core_id FROM instance_core WHERE instance_core.app_name = appName;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

OPEN cur1;

REPEAT

FETCH cur1 INTO badId;

IF NOT done THEN
	CALL deleteDirtyInstanceCore(badId);
END IF;


UNTIL done END REPEAT;

CLOSE cur1;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for deletePodAndContainers
-- ----------------------------
DROP PROCEDURE IF EXISTS `deletePodAndContainers`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `deletePodAndContainers`(IN image_group_id int(11),IN real_name varchar(255),OUT pod_id int(11),OUT container_count int(11))
BEGIN
	set pod_id = NULL;
	SELECT t.id INTO pod_id FROM v2_pod t WHERE t.image_group_id = image_group_id and t.real_name = real_name;

	IF pod_id IS NOT NULL THEN
		SELECT count(*) into container_count FROM v2_container WHERE v2_container.pod_id = pod_id;
		DELETE FROM v2_container WHERE v2_container.pod_id = pod_id;
		DELETE FROM v2_pod WHERE v2_pod.id = pod_id;
	END IF;

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
-- Procedure structure for deleteProjectCore
-- ----------------------------
DROP PROCEDURE IF EXISTS `deleteProjectCore`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `deleteProjectCore`(IN namespace varchar(200),IN cluster_id int(11),OUT result tinyint(1))
BEGIN
	UPDATE project_core SET project_core.project_current_status = "deleted", project_core.project_delete_datetime = NOW()
		WHERE project_core.project_name = namespace and project_core.cluster_id = cluster_id AND project_core.project_delete_datetime IS NULL;
	SET result = true;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for deleteVersionAndImageGroup
-- ----------------------------
DROP PROCEDURE IF EXISTS `deleteVersionAndImageGroup`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `deleteVersionAndImageGroup`(IN version_id int(11),OUT ig_id int(11))
BEGIN
	SELECT t.id INTO ig_id FROM v2_image_group t WHERE t.version_id = version_id ;
	IF ig_id IS NOT NULL THEN
		UPDATE v2_image_group SET v2_image_group.delete_time = NOW() WHERE v2_image_group.id = ig_id;
		UPDATE v2_version SET v2_version.delete_datetime = NOW() WHERE v2_version.id = version_id;
	END IF;
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

-- ----------------------------
-- Procedure structure for getImageGroup
-- ----------------------------
DROP PROCEDURE IF EXISTS `getImageGroup`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `getImageGroup`(IN version_id int(11),IN application_id int(11),IN project_id int(11),IN env_id int(11),IN base_name varchar(200),IN real_name varchar(200),IN uuid varchar(200),IN namespace varchar(200),IN apiversion varchar(200),IN kind varchar(200),IN replica int(11),IN app_name varchar(200),IN ip_host varchar(200),IN if_http varchar(20),IN api_port varchar(20),IN createtime datetime,OUT ig_id int(11))
BEGIN
	set ig_id = NULL;
	select t.id into ig_id FROM v2_image_group t where t.version_id = version_id AND t.application_id = application_id and t.project_id = project_id and t.env_id = env_id and t.delete_time IS NULL;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for getVersion
-- ----------------------------
DROP PROCEDURE IF EXISTS `getVersion`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `getVersion`(IN application_id int(11),IN vname varchar(200),OUT version_id int(11))
BEGIN
	set version_id = NULL;
	SELECT t.id into version_id from v2_version t WHERE t.application_id = application_id and t.version_name = vname and t.delete_datetime IS NULL;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insertOrGetEnvs
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetEnvs`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetEnvs`(IN image_id int(11),IN ekey varchar(2000),IN evalue varchar(2000),OUT env_id int(11))
BEGIN
	SET env_id = NULL;
	SELECT t.id INTO env_id from v2_envs t WHERE t.image_id = image_id and t.envs_key = ekey;
	if env_id IS NULL THEN
		INSERT INTO v2_envs VALUES(NULL,image_id,ekey,evalue,NOW(),"created");
		SELECT LAST_INSERT_ID() INTO env_id;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insertOrGetImage
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetImage`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetImage`(IN ig_id int(11),IN container_name varchar(255),IN image_name varchar(255),IN image_tag varchar(255),IN pull_secret varchar(255),OUT image_id int(11))
BEGIN
	SELECT t.id INTO image_id FROM v2_image t WHERE t.image_group_id =  ig_id and t.image_name = image_name and t.image_tag = image_tag;
	IF image_id is NULL THEN
		INSERT INTO v2_image VALUES(NULL,ig_id,container_name,image_name,image_tag,NULL,pull_secret,NULL,NULL,"created");
		SELECT LAST_INSERT_ID() INTO image_id;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insertOrGetLabel
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetLabel`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetLabel`(IN ig_id int(11),IN type varchar(200),IN lkey varchar(200),IN lvalue varchar(200),OUT label_id int(11))
BEGIN
	SELECT t.id INTO label_id from v2_labels t where t.image_group_id = ig_id and t.label_type = type and t.label_key = lkey;
	IF label_id is NULL THEN
		INSERT INTO v2_labels values(NULL,ig_id,type,lkey,lvalue,"created");
		SELECT LAST_INSERT_ID() INTO label_id;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insertOrGetMount
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetMount`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetMount`(IN image_id int(11),IN image_group_id int(11),IN version_id int(11),IN application_id int(11),IN project_id int(11),IN env_id int(11),IN mount_path varchar(255),IN mount_name varchar(255),OUT mount_id int(11))
BEGIN
	SET mount_id = NULL;
	SELECT t.id into mount_id FROM v2_volume_mounts t WHERE t.image_id = image_id and t.volume_mount_name = mount_name ;
	IF mount_id is NULL THEN
		INSERT INTO v2_volume_mounts VALUES(NULL,image_id,mount_path,mount_name,NOW(),image_group_id,version_id,application_id,project_id,env_id,"created");
		SELECT LAST_INSERT_ID() INTO mount_id;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insertOrGetPorts
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetPorts`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetPorts`(IN image_id int(11),IN pname varchar(255),IN protocol varchar(255),IN port_value int(11),OUT port_id int(11))
BEGIN
	set port_id = NULL;
	SELECT t.id into port_id FROM v2_ports t WHERE t.image_id = image_id and t.port_name = pname ;
	IF port_id is NULL THEN
		INSERT INTO v2_ports VALUES(NULL,image_id,pname,protocol,port_value,NOW(),"created");
		SELECT LAST_INSERT_ID() INTO port_id;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insertOrGetProbes
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetProbes`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetProbes`(IN image_id int(11),IN probe_type varchar(200),IN request_type varchar(200),IN probe_path varchar(200),IN probe_port int(11),IN scheme varchar(200),IN initial_delay_seconds int(11), IN timeout_seconds int(11), IN success_threshold int(11),IN failure_threshold int(11),OUT probe_id int(11) )
BEGIN
	SELECT LAST_INSERT_ID() INTO probe_id;
	SELECT t.id into probe_id FROM v2_probe t WHERE t.image_id = image_id and t.probe_type = probe_type ;
	IF probe_id IS NULL THEN
		INSERT INTO v2_probe VALUES(NULL,image_id,probe_type,request_type,probe_path,probe_port,scheme,initial_delay_seconds,timeout_seconds,success_threshold,failure_threshold,"created");
		SELECT LAST_INSERT_ID() INTO probe_id;
	END IF;
END
;;
DELIMITER ;

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
-- Procedure structure for insertOrGetResource
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetResource`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetResource`(IN max_cpu varchar(255),IN max_mem varchar(255),IN min_cpu varchar(255),IN min_mem varchar(255),IN image_id int(11),IN image_group_id int(11),IN version_id int(11),IN application_id int(11),IN project_id int(11),IN env_id int(11),OUT resource_id int(11))
BEGIN
	set resource_id = NULL;

	SELECT t.id INTO resource_id from v2_resources t WHERE t.image_id = image_id;
	IF resource_id is NULL THEN
		INSERT INTO v2_resources VALUES(NULL,image_id,max_cpu,max_mem,min_cpu,min_mem,NULL,NOW(),NULL,NULL,NULL,NULL,image_group_id,version_id,application_id,project_id,env_id,"created");
		SELECT LAST_INSERT_ID() INTO resource_id;
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

-- ----------------------------
-- Procedure structure for insertOrGetVolume
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrGetVolume`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrGetVolume`(IN path_or_config_name  varchar(255), IN volume_name varchar(255),IN type varchar(255), IN createtime datetime,IN project_id int(11),IN application_id int(11),IN version_id int(11),IN ig_id int(11),IN env_id int(11), OUT vol_id int(11))
BEGIN
	set vol_id = NULL;
	SELECT t.id into vol_id from v2_volumes t WHERE t.image_group_id = ig_id and t.version_id = version_id and t.application_id = application_id and t.project_id = project_id and t.env_id = env_id and t.volume_name = volume_name;
	IF vol_id is NULL THEN
		INSERT INTO v2_volumes VALUES(NULL,ig_id,path_or_config_name,volume_name,type,createtime,version_id,application_id,project_id,env_id,"created");
		SELECT LAST_INSERT_ID() INTO vol_id;
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
-- Procedure structure for insertOrUpdatePod
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertOrUpdatePod`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `insertOrUpdatePod`(IN image_group_id int(11),IN uuid varchar(255),IN kind varchar(255),IN parent_name varchar(255),IN real_name varchar(255),IN namespace varchar(255),IN api_version varchar(255),IN resource_version varchar(255),IN pod_status varchar(255),OUT pod_id int(11))
BEGIN
	DECLARE old_version varchar(255) DEFAULT "";
	DECLARE com_res int(11) DEFAULT 0;
	set pod_id = NULL;
	SELECT t.id,t.resource_version into pod_id,old_version from v2_pod t WHERE t.image_group_id = image_group_id  and t.real_name = real_name;
	SELECT STRCMP(resource_version,old_version) INTO com_res;
	if pod_id IS NOT NULL AND com_res = 1 THEN
		UPDATE v2_pod set v2_pod.`status` = pod_status,v2_pod.resource_version = resource_version WHERE v2_pod.id = pod_id;
	END IF;

	if pod_id IS NULL THEN
		INSERT INTO v2_pod VALUES(NULL,uuid,image_group_id ,NULL,kind ,NULL,NULL,NULL,parent_name ,real_name,namespace,api_version,resource_version,NOW(),pod_status);
		SELECT LAST_INSERT_ID() INTO pod_id ;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for strings_to_array
-- ----------------------------
DROP PROCEDURE IF EXISTS `strings_to_array`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `strings_to_array`(s_str varchar(500), s_split varchar(3))
begin
  DECLARE i int;
	DECLARE left_str varchar(500);
	DECLARE sub_str varchar(500);
	DECLARE n int;
  set i = length(s_str) - length(replace(s_str,s_split,''));
  set left_str = s_str;
  while i>0
  do
  set sub_str = substr(left_str,1,instr(left_str,s_split)-1);
  set left_str = substr(left_str,length(sub_str)+length(s_split)+1);
  set n = trim(sub_str);
  insert into list(id) values(n);
  set i = i - 1;
  end while;
  -- set n = trim(left_str);
  -- insert into list(id) values(n);
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for upgradeEnvs
-- ----------------------------
DROP PROCEDURE IF EXISTS `upgradeEnvs`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `upgradeEnvs`(str varchar(500),imageId int)
begin

DECLARE done INT DEFAULT 0;
DECLARE configEnvId int;
DECLARE configKey VARCHAR(255);
DECLARE configValue VARCHAR(255);

DECLARE cur1 CURSOR FOR select id from list;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
drop table if exists list;
create temporary table list(id INT not NULL);

CALL strings_to_array(str,';');

OPEN cur1;

REPEAT
FETCH cur1 INTO configEnvId;
IF NOT done THEN
	SET configKey = NULL;
	SET configValue = NULL;
	SELECT nacha_application.config_envs.config_envs_name,nacha_application.config_envs.config_envs_value
	INTO configKey,configValue FROM nacha_application.config_envs WHERE nacha_application.config_envs.config_envs_self_id = configEnvId;
	if configKey IS NOT NULL AND configValue IS NOT NULL THEN
		INSERT INTO nacha_deployer.v2_envs VALUES(NULL,imageId,configKey,configValue,NOW(),NULL);
	END IF;
END IF;


UNTIL done END REPEAT;
CLOSE cur1;

DROP TEMPORARY TABLE IF EXISTS list;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for upgradeInstanceConfig
-- ----------------------------
DROP PROCEDURE IF EXISTS `upgradeInstanceConfig`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `upgradeInstanceConfig`()
BEGIN

DECLARE igId INTEGER;
DECLARE newVersionId INTEGER;
DECLARE newApplicationId INTEGER;
DECLARE newProjectId INTEGER;
DECLARE newEnvId INTEGER;
DECLARE newPodId INTEGER;
DECLARE realName VARCHAR(255);
DECLARE imageGroupName VARCHAR(255);
DECLARE igNamespace VARCHAR(255) DEFAULT "default";
DECLARE apiVersion VARCHAR(255) DEFAULT "v1";
DECLARE kind VARCHAR(255) DEFAULT "rc";
DECLARE podReplica INTEGER DEFAULT 1;
DECLARE parentName VARCHAR(255);
DECLARE masterIp VARCHAR(255);
DECLARE masterType VARCHAR(255) DEFAULT "http";
DECLARE masterPort VARCHAR(255) DEFAULT "8080";
DECLARE createTime datetime;
DECLARE updateTime datetime DEFAULT NULL;
DECLARE igStatus VARCHAR(255) DEFAULT "created";

DECLARE minCpu VARCHAR(255);
DECLARE minMemeory VARCHAR(255);
DECLARE maxCpu VARCHAR(255);
DECLARE maxMemeory VARCHAR(255);

DECLARE v2ImageId INTEGER;
DECLARE newImageName VARCHAR(255);
DECLARE newImageTag VARCHAR(255);
DECLARE igExists INTEGER DEFAULT 0;

DECLARE oldConfigCoreId INTEGER;
DECLARE envsString VARCHAR(500);
DECLARE portsString VARCHAR(500);
DECLARE volumesString VARCHAR(500);

DECLARE done INT DEFAULT 0;
DECLARE cur1 CURSOR FOR SELECT instance_core_id,instance_name,instance_rename,instance_namespace,instance_create_datetime,
												instance_cpu,instance_memory,instance_request_cpu,instance_request_memory,
												app_id,app_name,ip,application_runtime_id,instance_create_datetime
												from nacha_deployer.instance_core
												where nacha_deployer.instance_core.instance_delete_datetime IS NOT NULL and component_id is NULL;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

OPEN cur1;

REPEAT

set newVersionId = NULL;
set oldConfigCoreId = NULL;

FETCH cur1 INTO igId,imageGroupName,realName,igNamespace,createTime,maxCpu,maxMemeory,minCpu,minMemeory,newApplicationId,parentName,masterIp,newEnvId,createTime;

SELECT t.application_project_id INTO newProjectId FROM nacha_application.application_core t WHERE t.application_core_id = newApplicationId;

SELECT t.image_id,t.config_core_id INTO newVersionId,oldConfigCoreId from nacha_application.application_instance_relation t WHERE t.application_core_id = newApplicationId and t.instance_core_id = igId;
SELECT t.config_image_name,t.config_image_tag INTO newImageName,newImageTag from nacha_application.instance_image t WHERE t.config_image_id = newVersionId;
SELECT t.config_envs_ids,t.config_ports_ids,t.config_volumes_ids INTO envsString,portsString,volumesString from nacha_application.config_core t WHERE t.application_core_id = newApplicationId and t.config_core_id = oldConfigCoreId;

IF NOT done THEN
	SELECT count(*) INTO igExists FROM nacha_deployer.v2_image_group WHERE nacha_deployer.v2_image_group.id = igId;
	if igExists = 0 and newVersionId is NOT NULL and oldConfigCoreId is NOT NULL  THEN
		INSERT INTO nacha_deployer.v2_image_group VALUES(
			igId,newVersionId,newApplicationId,newProjectId,newEnvId,NULL,realName,NULL,imageGroupName,NULL,
			igNamespace,apiVersion,kind,podReplica,parentName,masterIp,masterType,masterPort,createTime,NULL,NOW(),igStatus
		);
		INSERT INTO nacha_deployer.v2_image VALUES(NULL,igId,NULL,newImageName,newImageTag,NULL,NULL,NULL,NOW(),NULL);
		select LAST_INSERT_ID() INTO v2ImageId;
		if envsString is NOT NULL and LENGTH(envsString) > 1 THEN
			CALL upgradeEnvs(envsString,v2ImageId);

		END IF;

		if portsString is NOT NULL and LENGTH(portsString) > 1 THEN
			CALL upgradePorts(portsString,v2ImageId);

		END IF;

		if volumesString is NOT NULL and LENGTH(volumesString) > 1 THEN
			CALL upgradeVolumes(volumesString,v2ImageId,igId,newVersionId,newApplicationId,newProjectId,newEnvId);

		END IF;

		INSERT INTO v2_resources VALUES(NULL,v2ImageId,maxCpu,maxMemeory,minCpu,minMemeory,NULL,NOW(),NULL,NULL,NULL,NULL,
		igId,newVersionId,newApplicationId,newProjectId,newEnvId,"created");

		INSERT INTO v2_pod VALUES(NULL,NULL,igId,NULL,"rc",NULL,NULL,NULL,realName,NULL,igNamespace,apiVersion,NULL,NOW(),"Running");
		select LAST_INSERT_ID() INTO newPodId;
		INSERT INTO v2_container VALUES(NULL,newPodId,igId,newVersionId,newApplicationId,newProjectId,newEnvId,NULL,NULL,NULL,CONCAT(newImageName,":",newImageTag),"old",maxCpu,maxMemeory,NULL,NULL,createTime,createTime);

	END IF;
END IF;

UNTIL done END REPEAT;
CLOSE cur1;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for upgradePorts
-- ----------------------------
DROP PROCEDURE IF EXISTS `upgradePorts`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `upgradePorts`(str varchar(500),imageId int)
begin

DECLARE done INT DEFAULT 0;
DECLARE configPortId int;
DECLARE portsPort int;
DECLARE cur1 CURSOR FOR select id from list;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;


drop table if exists list;
create temporary table list(id INT not NULL);

CALL strings_to_array(str,';');

OPEN cur1;

REPEAT
FETCH cur1 INTO configPortId;
IF NOT done THEN
	SET portsPort = NULL;
	SELECT config_ports_port INTO portsPort FROM nacha_application.config_ports WHERE nacha_application.config_ports.config_ports_self_id = configPortId;
	if portsPort IS NOT NULL THEN
		INSERT INTO nacha_deployer.v2_ports VALUES(NULL,imageId,NULL,NULL,portsPort,NULL,"created");
	END IF;
END IF;


UNTIL done END REPEAT;
CLOSE cur1;

DROP TEMPORARY TABLE IF EXISTS list;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for upgradeVolumes
-- ----------------------------
DROP PROCEDURE IF EXISTS `upgradeVolumes`;
DELIMITER ;;
CREATE DEFINER=`bocloud`@`%` PROCEDURE `upgradeVolumes`(str varchar(500),imageId int,imageGroupId int,versionId int,applicationId int,projectId int,envId int)
begin

DECLARE done INT DEFAULT 0;
DECLARE everyOne INT;
DECLARE volumePath VARCHAR(300);
DECLARE volumeMountPath VARCHAR(500);
DECLARE volumeName VARCHAR(255);
DECLARE newVName VARCHAR(255);
DECLARE newVNameIndex int;
DECLARE volumeType VARCHAR(255);
DECLARE volumeRealType VARCHAR(255);
DECLARE cur1 CURSOR FOR select id from list;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;


drop table if exists list;
create temporary table list(id INT not NULL);

CALL strings_to_array(str,';');
set newVNameIndex = 0;
OPEN cur1;

REPEAT
FETCH cur1 INTO everyOne;
IF NOT done THEN
	SELECT config_volumes_path,config_volumes_mount_path,config_volumes_name,config_volumes_type INTO
	volumePath,volumeMountPath,volumeName,volumeType FROM nacha_application.config_volumes
	WHERE nacha_application.config_volumes.config_volumes_self_id = everyOne;
	IF volumeType = "share" THEN
		SET volumeRealType = "configFilePath";
	END IF;

	IF volumeType = "appData" THEN
		SET volumeRealType = "applicationDataPath";
	END IF;

	IF volumeType = "local" and volumeMountPath <> "localFileSystem" THEN
		SET volumeRealType = "hostPath";
	END IF;

	IF volumeType = "local" and volumeMountPath = "localFileSystem" THEN
		SET volumeRealType = "logPath";
		SET volumeMountPath = "/abcs/data/local/localFileSystem";
	END IF;

	INSERT INTO v2_volumes VALUES(NULL,imageGroupId,volumeMountPath,CONCAT("volume-",newVNameIndex),volumeRealType,NOW(),versionId,applicationId,projectId,envId,"created");
	INSERT INTO v2_volume_mounts VALUES(NULL,imageId,volumePath,CONCAT("volume-",newVNameIndex),NOW(),imageGroupId,versionId,applicationId,projectId,envId,"created");

	set newVNameIndex = newVNameIndex+1;
	-- SELECT res;
END IF;

UNTIL done END REPEAT;
CLOSE cur1;

DROP TEMPORARY TABLE IF EXISTS list;

END
;;
DELIMITER ;
