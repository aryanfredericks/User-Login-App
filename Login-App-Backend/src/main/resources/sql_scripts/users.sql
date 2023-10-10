create database user_management;

use user_management;

create table users(
    id int primary key not null auto_increment,
    name varchar(20) not null,
    email varchar(40) not null unique,
    course varchar(40) not null,
    password varchar(40) not null
);