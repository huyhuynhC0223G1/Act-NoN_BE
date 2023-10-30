create database act_non;
use act_non;
SET FOREIGN_KEY_CHECKS = 0;

create table roles(
id bigint primary key auto_increment,
name varchar(25) not null
);
create table users(
id bigint primary key auto_increment,
username varchar(50) unique not null,
user_password varchar(50) not null
);
create table users_roles(
users_id bigint,
role_id bigint,
primary key(users_id, role_id),
foreign key (role_id) references roles(id),
foreign key (users_id) references users(id)
);
create table customers(
id bigint primary key auto_increment,
name varchar(50) not null,
phone_number varchar(15),
email varchar(255),
address text,
users_id bigint,
foreign key (users_id) references users(id)
);


create table product_type(
id bigint primary key auto_increment,
name varchar(25),
flag_delete bit(1)
);
create table products(
id bigint primary key auto_increment,
name varchar(100) not null,
description text not null,
price double not null,
img text not null,
product_type_id bigint,
foreign key (product_type_id) references product_type(id)
);
-- cart details
create table cart_details(
id bigint primary key auto_increment,
customer_id bigint,
product_id bigint,
quantity bigint,
foreign key (customer_id) references customers(id),
foreign key (product_id) references products(id)
);

create table orders(
id bigint primary key auto_increment,
date_created datetime,
flag_deleted bit(1),
total_price double,
customer_id bigint,
foreign key (customer_id) references customers(id)
);

create table order_details(
id bigint primary key auto_increment,
order_id bigint not null,
product_id bigint,
current_price double,
flag_delete bit(1),
quantity bigint,
foreign key (order_id) references orders(id),
foreign key (product_id) references products(id)
);