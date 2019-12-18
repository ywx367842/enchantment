drop table if exists `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '直属上级id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `manager_fk` (`manager_id`),
  CONSTRAINT `manager_fk` FOREIGN KEY (`manager_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 外键约束增删
alter table t_user drop foreign key manager_fk;

alter table t_user add constraint manager_fk foreign key(`manager_id`) references `t_user` (id) on delete restrict on update restrict;


-- 主键
alter table t_user change column id id BIGINT(20) auto_increment;

alter table t_user change column id id varchar(32);


