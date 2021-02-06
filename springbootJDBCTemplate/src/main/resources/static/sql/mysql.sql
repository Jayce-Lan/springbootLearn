create database springbootlearn;
use springbootlearn;

create table book (
    id int not null auto_increment,
    name varchar(128) default null,
    author varchar(64) default null,
    primary key (id)
);

insert into book (name, author)
values('围城', '钱钟书'),
('三国演义', '罗贯中');