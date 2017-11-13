drop table if exists `t_user`;
create table `t_user`(
`u_id` bigint(10) not null auto_increment comment '用户编号',
`user_name` varchar(20) default null comment '用户姓名',
`password` varchar(20) default null comment '用户密码',
`user_sex` char(1) default null comment '用户性别',
`user_age` bigint(10) default null comment '用户年龄',
`education` varchar(20) default null comment '用户学历',
`address` varchar(20) default null comment '用户地址',
primary key(`u_id`)
)engine=innodb auto_increment=11 default charset=utf8mb4 comment='用户信息表';