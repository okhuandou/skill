-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE seckill;
--  使用数据库
use seckill;
-- 创建秒杀库存秒
CREATE TABLE seckill(
	seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
	name varchar(120) NOT NULL COMMENT '名称',
	number int NOT NULL COMMENT '数量',
	start_time timestamp NOT NULL COMMENT '秒杀开始时间',
	end_time timestamp NOT NULL COMMENT '秒杀结束时间',
	create_time timestamp NOT NULL   COMMENT '创建时间',
	PRIMARY KEY (seckill_id),
	key idx_seckill_start_time(start_time),
	key idx_seckill_end_time(end_time),
	key idx_seckill_create_time(create_time)

)ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- 初始化数据
insert into seckill (name,number,start_time,create_time,end_time)
values
	('200元秒杀mac',100,'2016-06-12 00:00:00','2016-06-12 00:00:00','2016-06-12 00:00:00'),
	('200元秒杀mac',100,'2016-06-12 00:00:00','2016-06-12 00:00:00','2016-06-12 00:00:00');
	
create table success_kill(
	seckill_id bigint NOT NULL,
	user_phone bigint NOT NULL,
	state tinyint NOT NULL DEFAULT -1  COMMENT '-1 无效 0 成功 1 已付款',
	create_time  timestamp NOT NULL ,
	PRIMARY KEY (seckill_id,user_phone),
	key idx_success_kill_create_time (create_time)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;
;

-- show create table seckill