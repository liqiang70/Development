DROP TABLE IF EXISTS t_user;

CREATE TABLE t_user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    create_time VARCHAR(50) DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (id)
);

