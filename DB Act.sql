create database Act_NoN;
use Act_NoN;
SET FOREIGN_KEY_CHECKS = 0;
create table roles(
role_id int primary key auto_increment,
role_name varchar(25) not null
);
create table account_users(
account_id int primary key auto_increment,
user_email varchar(50) unique not null,
user_password varchar(50) not null
);
create table user_role(
role_id int,
account_id int,
primary key(role_id, account_id),
foreign key (role_id) references roles(role_id),
foreign key (account_id) references account_users(account_id)
);
create table customers(
customer_id int primary key auto_increment,
customer_name varchar(50) not null,
phone_number varchar(15),
address text,
account_id int,
foreign key (account_id) references account_users(account_id)
);


create table product_type(
product_type_id int primary key auto_increment,
product_type_name varchar(25)
);
create table products(
product_id int primary key auto_increment,
product_name varchar(100) not null,
product_description text not null,
price double not null,
image text not null,
product_type_id int,
foreign key (product_type_id) references product_type(product_type_id)
);
-- cart details
create table cart_details(
id int primary key auto_increment,
customer_id int,
product_id int,
product_name varchar(100),
price double,
image text,
quantity int,
foreign key (customer_id) references customers(customer_id),
foreign key (product_id) references products(product_id)
);

create table orders(
order_id int primary key auto_increment,
order_date timestamp default current_timestamp,
customer_id int,
order_status varchar(25) default("processing"), -- "complete"
foreign key (customer_id) references customers(customer_id)
);

create table order_details(
order_details_id int primary key auto_increment,
order_id int not null,
product_id int,
quantity int,
foreign key (order_id) references orders(order_id),
foreign key (product_id) references products(product_id)
);