use seckill;

create table user_info(
    id int auto_increment primary key comment '用户id',
    name varchar(64) default '' comment '用户名',
    gender tinyint default 0 comment '用户性别 1.男性 2.女性',
    age int default 0 comment '用户年龄',
    telphone varchar(64) default '' comment '用户手机号',
    register_mode varchar(64) default '' comment '注册方式',
    thied_party_id varchar(128) default '' comment '用户其它方式注册id'
)comment '用户表';

show tables ;

create table user_password(
    id int not null auto_increment primary key,
    encrpt_password varchar(128) default '',
    user_id int default 0,
    constraint fk_passwd_info foreign key (user_id) references user_info(id)
);

insert into user_info(name, gender, age, telphone, register_mode, thied_party_id)
VALUES ('第一个用户', 1, 20, '13888888888', '手机注册', '');