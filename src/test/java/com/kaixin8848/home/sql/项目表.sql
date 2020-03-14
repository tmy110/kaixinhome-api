-- auto-generated definition
create table dept
(
    id        bigint                  not null comment '编码'
        primary key,
    dept_name varchar(255) default '' not null comment '部门名称'
)
    comment '部门表';
-- auto-generated definition
create table employee
(
    id        bigint                  not null comment '编码'
        primary key,
    last_name varchar(255) default '' not null comment '姓名',
    gender    char                    null comment '性别',
    email     varchar(255) default '' not null comment '邮箱'
)
    comment '员工表';

-- auto-generated definition
create table favorites
(
    id             bigint                           not null comment '编码'
        primary key,
    user_id        bigint       default 0                 not null comment '用户编码',
    favorites_name varchar(255) default ''                not null comment '收藏夹名称',
    url            varchar(255) default ''                not null comment '连接',
    sort           int(10)      default 0                 not null comment '排序',
    picture        varchar(255) default ''                not null comment '图片',
    create_time    datetime     default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '收藏夹表';

-- auto-generated definition
create table user
(
    id          bigint                                 not null comment '编码'
        primary key,
    phone       varchar(50)  default ''                not null comment '手机号',
    password    varchar(50)                            not null comment '密码',
    name        varchar(100) default ''                not null comment '姓名',
    gender      tinyint(1)   default 0                 not null comment '性别 默认0 0:男 1:女',
    email       varchar(50)  default ''                not null comment '邮箱',
    address     varchar(255) default ''                not null comment '地址',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '用户表';



