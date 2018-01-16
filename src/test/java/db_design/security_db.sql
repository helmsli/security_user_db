SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS security_allAction;
DROP TABLE IF EXISTS security_role;
DROP TABLE IF EXISTS security_roleAction;
DROP TABLE IF EXISTS security_user;
DROP TABLE IF EXISTS security_user_email;
DROP TABLE IF EXISTS security_user_idNo;
DROP TABLE IF EXISTS security_user_phone;




/* Create Tables */

CREATE TABLE security_allAction
(
	actionId bigint(8),
	description varchar(128),
	action varchar(128),
	-- 指明是哪个系统的权限
	system varchar(32) COMMENT '指明是哪个系统的权限'
);


CREATE TABLE security_role
(
	roleId bigint(8),
	name varchar(64),
	description varchar(128),
	createTime datetime,
	updateTime datetime,
	-- 填写创建来源
	createSource varchar(128) COMMENT '填写创建来源'
);


CREATE TABLE security_roleAction
(
	actionId bigint(8),
	roleId bigint(8),
	description varchar(128),
	action varchar(128),
	-- 0--可见，可用
	-- 1--不可见
	-- 2--可见不可用
	authority tinyint DEFAULT 0 COMMENT '0--可见，可用
1--不可见
2--可见不可用',
	createTime datetime,
	-- 填写创建来源
	createSource varchar(128) COMMENT '填写创建来源',
	updateTime datetime
);


CREATE TABLE security_user
(
	userId bigint(8) unsigned,
	loginName varchar(64),
	-- 姓
	lastName varchar(32) COMMENT '姓',
	-- 名字
	firstName varchar(32) COMMENT '名字',
	displayName varchar(32),
	password varchar(256),
	-- 密码版本
	passwordExt varchar(128) DEFAULT '“1.0”' COMMENT '密码版本',
	email varchar(64),
	-- 0--审核过
	-- 1--没有审核
	-- 2--审核不过需要修改
	-- 
	emailVerified tinyint DEFAULT 1 COMMENT '0--审核过
1--没有审核
2--审核不过需要修改
',
	phone varchar(32),
	-- 电话号码国家码，00开头
	phoneCode varchar(8) COMMENT '电话号码国家码，00开头',
	-- 0--审核过
	-- 1--没有审核
	-- 2--审核不过需要修改
	-- 
	phoneVerified tinyint DEFAULT 1 COMMENT '0--审核过
1--没有审核
2--审核不过需要修改
',
	-- 0--男
	-- 1--女
	sex tinyint COMMENT '0--男
1--女',
	birthday datetime,
	avatar varchar(128),
	homeAddress varchar(256),
	businessAddress varchar(256),
	idNo varchar(128),
	-- 0--身份证
	-- 1--护照
	idType tinyint COMMENT '0--身份证
1--护照',
	-- 0--审核过
	-- 1--没有审核
	-- 2--审核不过需要修改
	-- 
	idVerified tinyint DEFAULT 1 COMMENT '0--审核过
1--没有审核
2--审核不过需要修改
',
	-- 状态(0:有效 1：无效)
	-- 0--有效
	-- 1--无效
	-- 2--禁用
	-- 3--审核不过
	-- 
	status tinyint DEFAULT 0 COMMENT '状态(0:有效 1：无效)
0--有效
1--无效
2--禁用
3--审核不过
',
	-- 拥有的角色，以，分割
	roles varchar(128) COMMENT '拥有的角色，以，分割',
	-- 保存扩展数据json
	extDate varchar(512) COMMENT '保存扩展数据json',
	createTime datetime,
	-- 填写创建来源
	createSource varchar(128) COMMENT '填写创建来源',
	updateTime datetime,
	CONSTRAINT idx_user_ UNIQUE (userId)
)
PARTITION BY KEY(userId)
PARTITIONS 2;


CREATE TABLE security_user_email
(
	email varchar(64),
	userId bigint(8) unsigned,
	CONSTRAINT idx_email UNIQUE (email)
)
PARTITION BY KEY(email)
PARTITIONS 2;


CREATE TABLE security_user_idNo
(
	-- 可能包括，证件国家码，证件类型和证件号码
	idTotalNo varchar(128) COMMENT '可能包括，证件国家码，证件类型和证件号码',
	userId bigint(8) unsigned,
	CONSTRAINT idx_idNo UNIQUE (idTotalNo)
)
PARTITION BY KEY(idTotalNo)
PARTITIONS 2;


CREATE TABLE security_user_phone
(
	phone varchar(32),
	userId bigint(8) unsigned,
	CONSTRAINT idx_phone UNIQUE (phone)
)
PARTITION BY KEY(phone)
PARTITIONS 2;



