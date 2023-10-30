create database act_non;
use act_non;
SET FOREIGN_KEY_CHECKS = 0;

create table customer(
id bigint primary key auto_increment,
`name` varchar(50) not null,
phone_number varchar(15),
email varchar(255),
address text,
flag_deleted bit(1)
);
create table roles(
id bigint primary key auto_increment,
`name` varchar(50) not null
);
create table users(
id bigint primary key auto_increment,
username varchar(50) unique not null,
`password` varchar(255) not null,
flag_delete bit(1),
customer_id bigint,
foreign key(customer_id) references customer(id)
);
create table users_roles(
users_id bigint,
role_id bigint,
primary key(users_id, role_id),
foreign key (role_id) references roles(id),
foreign key (users_id) references users(id)
);



create table product_type(
id bigint primary key auto_increment,
flag_detele bit(1),
`name` varchar(25)
);
create table product(
id bigint primary key auto_increment,
`name` varchar(100) ,
`description` text ,
price double ,
img varchar(255),
flag_delete bit(1),
product_type_id bigint,
foreign key (product_type_id) references product_type(id)
);
-- cart details
create table cart_detail(
id bigint primary key auto_increment,
customer_id bigint,
product_id bigint,
quantity bigint,
foreign key (customer_id) references customer(id),
foreign key (product_id) references product(id)
);

create table orders(
id bigint primary key auto_increment,
date_created datetime,
flag_deleted bit(1),
note varchar(255),
total_price double,
customer_id bigint,
foreign key (customer_id) references customer(id)
);

create table order_detail(
id bigint primary key auto_increment,
current_price double,
flag_delete bit(1),
quantity bigint,
orders_id bigint,
product_id bigint,
foreign key (orders_id) references orders(id),
foreign key (product_id) references product(id)
);

-- customer
INSERT INTO customer (address, email, flag_deleted, name, phone_number) VALUES ('Da Nang', 'admin@gmail.com', false, 'admin', '0369852147');
INSERT INTO customer (address, email, flag_deleted, name, phone_number) VALUES ('Da Nang', 'hongvan@gmail.com', false, 'hongvan', '0361478522');
INSERT INTO customer (address, email, flag_deleted, name, phone_number) VALUES ('Da Nang', 'leloi@gmail.com', false, 'leloi', '0345632333');
INSERT INTO customer (address, email, flag_deleted, name, phone_number) VALUES ('Da Nang', 'tranhoang@gmail.com', false, 'tranhoang', '0359632585');
INSERT INTO customer (address, email, flag_deleted, name, phone_number) VALUES ('Da Nang', 'nguyenhai@gmail.com', false, 'nguyenhai', '0332256989');

-- product type

INSERT INTO product_type ( flag_detele, name) VALUES ( false, 'Combo');
INSERT INTO product_type ( flag_detele, name) VALUES ( false, 'Mark');
INSERT INTO product_type ( flag_detele, name) VALUES ( false, 'Gun');
INSERT INTO product_type ( flag_detele, name) VALUES ( false, 'Armor');
INSERT INTO product_type ( flag_detele, name) VALUES ( false, 'Glove');
INSERT INTO product_type ( flag_detele, name) VALUES ( false, 'Knee pads');
INSERT INTO product_type ( flag_detele, name) VALUES ( false, 'Elbow pads');

-- product
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'ALL-IN-ONE PAINTBALL GUN PACKAGE
Tippmann Cronus TACTICAL .68
Maddog\'s Silver CO2 Paintball Gun
Silver CO2 Accessory Starter Kit Includes
200 Round Capacity Paintball Loader
Single Pane Anti-Fog Paintball Mask', false, 'https://tiimg.tistatic.com/fp/1/007/387/ready-play-paintball-equipment-package-kit-297.jpg', 'Maddog Tippmann', 33, 1);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'ALL-IN-ONE PAINTBALL GUN PACKAGE
Tippmann Cronus TACTICAL .68
Maddog\'s Silver CO2 Paintball Gun
Silver CO2 Accessory Starter Kit Includes
200 Round Capacity Paintball Loader
Single Pane Anti-Fog Paintball Mask', false, 'https://m.media-amazon.com/images/I/51LDn3CzMCL._AC_SY1000_.jpg', 'Maddog JT ', 45, 1);
INSERT INTO product (description, flag_delete, img, name, price, product_type_id) VALUES ('ALL-IN-ONE PAINTBALL GUN PACKAGE
Tippmann Cronus TACTICAL .68
Maddog\'s Silver CO2 Paintball Gun
Silver CO2 Accessory Starter Kit Includes
200 Round Capacity Paintball Loader
Single Pane Anti-Fog Paintball Mask', false, 'https://m.media-amazon.com/images/I/61s6l77J98L._AC_UF1000,1000_QL80_.jpg', 'Tippmann Cronus', 60, 1);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ('ALL-IN-ONE PAINTBALL GUN PACKAGE
Tippmann Cronus TACTICAL .68
Maddog\'s Silver CO2 Paintball Gun
Silver CO2 Accessory Starter Kit Includes
200 Round Capacity Paintball Loader
Single Pane Anti-Fog Paintball Mask', false, 'https://eastcoastpaintball.com/cdn/shop/products/Frame788_1200x.png?v=1680489364', 'Cronus Tactical', 120, 1);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'ALL-IN-ONE PAINTBALL GUN PACKAGE
Tippmann Cronus TACTICAL .68
Maddog\'s Silver CO2 Paintball Gun
Silver CO2 Accessory Starter Kit Includes
200 Round Capacity Paintball Loader
Single Pane Anti-Fog Paintball Mask', false, 'https://i.pinimg.com/474x/69/16/6b/69166b4dc8e8bddb3e78b2978dde0c3d.jpg', 'Kit De Pistola', 170, 1);
INSERT INTO product (description, flag_delete, img, name, price, product_type_id) VALUES ('Polycarbonate lens Imported Frame Material Lens material Breathable Ajustable non-slip strap', false, 'https://cdn11.bigcommerce.com/s-utijnwwvdm/images/stencil/1280x1280/products/103396/233648/990c08ded97a816232b39322734fa45727953d5e2a84e4e7de9a63968d0ceddf__01983.1687799697.jpg?c=1', 'Airsoft Helmet', 60, 2);
INSERT INTO product (description, flag_delete, img, name, price, product_type_id) VALUES ('Polycarbonate lens Imported Frame Material Lens material Breathable Ajustable non-slip strap', false, 'https://i.redd.it/96df8i5tolsa1.jpg', 'Tactical Mask', 120, 2);
INSERT INTO product (description, flag_delete, img, name, price, product_type_id) VALUES ('Polycarbonate lens Imported Frame Material Lens material Breathable Ajustable non-slip strap', false, 'https://i5.walmartimages.com/seo/JT-FLEX-8-Thermal-Paintball-Goggle-Mask_ee8ae8e1-d8db-4194-b717-f58ed247c5d3_1.305dabf30e12f27769c10d07ff982b9c.jpeg?odnHeight=2000&odnWidth=2000&odnBg=FFFFFF', 'Goggles Ballistic', 170, 2);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Semi - Automatic .68 Cal Paintball
Velocity is Adjustable 
Barrel Thread
Pull - 1 shot
Marker - 1 trigger 
Includes ~9.5
Tactical Versions Include', false, 'https://impactfull-9512.kxcdn.com/wp-content/uploads/2017/02/CRONUS-OD-2-min.jpg', 'TIPPMANN', 33, 3);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Semi - Automatic .68 Cal Paintball
Velocity is Adjustable 
Barrel Thread
Pull - 1 shot
Marker - 1 trigger 
Includes ~9.5
Tactical Versions Include', false, 'https://www.scvillage.com/cdn/shop/files/IMG_4820.jpg?v=1685161334', 'Marker Gun', 45, 3);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Semi - Automatic .68 Cal Paintball
Velocity is Adjustable 
Barrel Thread
Pull - 1 shot
Marker - 1 trigger 
Includes ~9.5
Tactical Versions Include', false, 'https://eastcoastpaintball.com/cdn/shop/products/Tippmann_Sierra_One_Black_Paintball_Gun_Front_Oblique_View__73719_1200x1200_crop_center.jpg?v=1669895894', 'Caliber', 60, 3);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Semi - Automatic .68 Cal Paintball
Velocity is Adjustable 
Barrel Thread
Pull - 1 shot
Marker - 1 trigger 
Includes ~9.5
Tactical Versions Include', false, 'https://i.ebayimg.com/images/g/7ioAAOSwQndgM1nn/s-l600.jpg', 'Umarex T4E', 120, 3);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Semi - Automatic .68 Cal Paintball
Velocity is Adjustable 
Barrel Thread
Pull - 1 shot
Marker - 1 trigger 
Includes ~9.5
Tactical Versions Include', false, 'https://cdn11.bigcommerce.com/s-utijnwwvdm/images/stencil/1280x1280/products/93486/146925/TIPPMANNSIERRAONELRKIT-3__52839.1654632065.jpg?c=1', 'Cronus', 170, 3);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Neoprene
Buckle closure
Hand Wash Only
Durable and comfortable Material
Adjustable shoulder and waist design
Adjustable and user-friendly-The shoulder straps', false, 'https://i.ebayimg.com/images/g/4dYAAOSw3opbbWGa/s-l1200.jpg', 'CS Field', 210, 4);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Neoprene
Buckle closure
Hand Wash Only
Durable and comfortable Material
Adjustable shoulder and waist design
Adjustable and user-friendly-The shoulder straps', false, 'https://cdn-amz.woka.io/images/I/61NIJ9CuChL.jpg', 'Tactical Armor', 45, 4);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Neoprene
Buckle closure
Hand Wash Only
Durable and comfortable Material
Adjustable shoulder and waist design
Adjustable and user-friendly-The shoulder straps', false, 'https://m.media-amazon.com/images/I/51IuvysgmJL._AC_UF894,1000_QL80_.jpg', 'NC Airsoft', 60, 4);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Neoprene
Buckle closure
Hand Wash Only
Durable and comfortable Material
Adjustable shoulder and waist design
Adjustable and user-friendly-The shoulder straps', false, 'https://m.media-amazon.com/images/I/614tpPHKfUL._AC_UF894,1000_QL80_.jpg', 'Tactical Vest', 120, 4);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Neoprene
Buckle closure
Hand Wash Only
Durable and comfortable Material
Adjustable shoulder and waist design
Adjustable and user-friendly-The shoulder straps', false, 'https://i.pinimg.com/originals/49/13/99/4913992f127b95da4a2ce64fa7c86731.jpg', 'Military Uniform', 170, 4);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Neoprene
Hook,Loop,Velcro closure
Enhanced comfort and durability
Contour, dual-layered, high-impact padding
Neoprene upper leg support
Lightweight, breathable 4-way stretch sleeve
Adjustable velcro strap', false, 'https://www.hkarmy.com/cdn/shop/products/new-crashpads_1200x.jpg?v=1653343677', 'HK Army', 210, 6);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'High tech materials for the highest demands
Good ventilation thanks to various mesh zones
Silicone coated inside for perfect grip
Additional EVA pad 
Full Finger design for even more protection
', false, 'https://shop.osgpaintball.com/cdn/shop/products/gloves.webp?v=1662810437', 'Gloves HK', 210, 5);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'High tech materials for the highest demands
Very good protection thanks 
Good ventilation thanks to various mesh zones
Silicone coated inside for perfect grip
Full Finger design 
Lightweight design with optimal protection', false, 'https://proedgepb.com/cdn/shop/products/hk-army-hardline-armored-glove-slate-354160_700x700.jpg?v=1621647357', 'Tactical', 45, 5);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'High tech materials for the highest demands
Very good protection 
Good ventilation thanks to various mesh zones
Silicone coated inside for perfect grip
Full Finger design 
Lightweight design with optimal protection', false, 'https://mx.headshotpb.com/cdn/shop/products/HKArmyHardlineArmoredGloves_1200x1200.png?v=1628134115', 'Army Armored', 60, 5);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'High tech materials for the highest demands
Good ventilation thanks to various mesh zones
Silicone coated inside for perfect grip
Additional EVA pad protects 
Full Finger design for even more protection
Lightweight design with optimal protection', false, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGxnUJH_OVxtNI5lE_y_g52QYoaaFY7csZxubmP6Lzulsj_y4PO9zmeIFZk8zzRCpuYt4&usqp=CAU', 'ENERGY', 120, 5);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'High tech materials for the highest demands
Good ventilation 
Silicone coated inside for perfect grip
Additional EVA pad 
Full Finger design for even more protection
Lightweight design with optimal protection', false, 'https://www.hkarmy.com/cdn/shop/products/Fire-HardlineGlove-Default.gif?v=1620944284', 'Bunkerkings', 170, 5);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Neoprene
Hook,Loop,Velcro closure
Enhanced comfort and durability
Contour, dual-layered, high-impact padding
Neoprene upper leg support
Lightweight, breathable 4-way stretch sleeve
Adjustable velcro strap', false, 'https://cdn11.bigcommerce.com/s-utijnwwvdm/images/stencil/original/products/71641/305932/2116b521d5ab5e80dd975dd24d1eb9c3fc2ea88b1f22948a5d4135520ec49cb7__53938.1693505539.jpg?c=1', 'NoCry Professional', 45, 6);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Neoprene
Hook,Loop,Velcro closure
Enhanced comfort and durability
Contour, dual-layered, high-impact padding
Neoprene upper leg support
Lightweight, breathable 4-way stretch sleeve
Adjustable velcro strap', false, 'https://cdn11.bigcommerce.com/s-utijnwwvdm/images/stencil/1280x1280/products/71643/305956/bc81ede11c2b4206123122d27b1063c929df6816ed3ee0abfbf2fe469f610e1b__65637.1693505565.jpg?c=1', 'Cushion', 60, 6);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Neoprene
Hook,Loop,Velcro closure
Enhanced comfort and durability
Contour, dual-layered, high-impact padding
Neoprene upper leg support
Lightweight, breathable 4-way stretch sleeve
Adjustable velcro strap', false, 'https://i.ebayimg.com/images/g/CGkAAOSwXWhi-pB4/s-l1200.webp', 'Adjustable', 120, 6);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Neoprene
Hook,Loop,Velcro closure
Enhanced comfort and durability
Contour, dual-layered, high-impact padding
Neoprene upper leg support
Lightweight, breathable 4-way stretch sleeve
Adjustable velcro strap', false, 'https://cdn11.bigcommerce.com/s-utijnwwvdm/images/stencil/1280x1280/products/69809/305827/fb0b201d662d0d0e008db14e5d666918ef704d43ea791b10f2de6db8fd3ada7e__27612.1693505422.jpg?c=1', 'Klein Tools', 170, 6);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Enhanced comfort and durability
Contour, dual-layered, high-impact padding
Neoprene arm support
Lightweight, breathable 4-way stretch sleeve
Adjustable velcro strap 
Built-in thermoformed protective glove
Custom HK Army trim', false, 'https://www.hkarmy.com/cdn/shop/products/new-crashpadsarm.jpg?v=1653343937', 'CRASH ARM', 170, 7);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Enhanced comfort and durability
Contour, dual-layered, high-impact padding
Neoprene arm support
Lightweight, breathable 4-way stretch sleeve
Adjustable velcro strap 
Built-in thermoformed protective glove
Custom HK Army trim', false, 'https://m.media-amazon.com/images/I/6186GgaPCNL._AC_UF1000,1000_QL80_.jpg', 'Performance', 45, 7);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Enhanced comfort and durability
Contour, dual-layered, high-impact padding
Neoprene arm support
Lightweight, breathable 4-way stretch sleeve
Adjustable velcro strap 
Built-in thermoformed protective glove
Custom HK Army trim', false, 'https://www.hkarmy.com/cdn/shop/products/HK_HSTL_Pads_Front_Back_01.jpg?v=1535936317', 'EXALT T3', 60, 7);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Enhanced comfort and durability
Contour, dual-layered, high-impact padding
Neoprene arm support
Lightweight, breathable 4-way stretch sleeve
Adjustable velcro strap 
Built-in thermoformed protective glove
Custom HK Army trim', false, 'https://www.lonewolfpaintball.com/cdn/shop/products/image_56466370-548a-47e8-a247-7cfc551c7b9d.jpg?v=1639919056', 'LTD TT', 120, 7);
INSERT INTO product ( description, flag_delete, img, name, price, product_type_id) VALUES ( 'Enhanced comfort and durability
Contour, dual-layered, high-impact padding
Neoprene arm support
Lightweight, breathable 4-way stretch sleeve
Adjustable velcro strap 
Built-in thermoformed protective glove
Custom HK Army trim', false, 'https://eastcoastpaintball.com/cdn/shop/products/HK_Crash_Camo_Arm_Pad_1_1200x.jpg?v=1653141164', 'CTX Arm', 170, 7);



-- role
INSERT INTO roles ( name) VALUES ( 'ADMIN');
INSERT INTO roles ( name) VALUES ('USER');

-- users
INSERT INTO users ( flag_delete, password, username, customer_id) VALUES ( false, '$2a$12$cVd.LbSS/jhfP0ygnkI9bezpNK3uD8LZtjV7RNoUlGECBPqtq1hyO', 'Admin', 1);
INSERT INTO users ( flag_delete, password, username, customer_id) VALUES ( false, '$2a$12$cVd.LbSS/jhfP0ygnkI9bezpNK3uD8LZtjV7RNoUlGECBPqtq1hyO', 'hongvan', 2);
INSERT INTO users ( flag_delete, password, username, customer_id) VALUES ( false, '$2a$12$cVd.LbSS/jhfP0ygnkI9bezpNK3uD8LZtjV7RNoUlGECBPqtq1hyO', 'leloi', 3);
INSERT INTO users ( flag_delete, password, username, customer_id) VALUES ( false, '$2a$12$cVd.LbSS/jhfP0ygnkI9bezpNK3uD8LZtjV7RNoUlGECBPqtq1hyO', 'tranhoang', 4);
INSERT INTO users ( flag_delete, password, username, customer_id) VALUES ( false, '$2a$12$cVd.LbSS/jhfP0ygnkI9bezpNK3uD8LZtjV7RNoUlGECBPqtq1hyO', 'nguyen hai', 5);

-- users_role
INSERT INTO users_roles (users_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (users_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (users_id, role_id) VALUES (3, 2);
INSERT INTO users_roles (users_id, role_id) VALUES (4, 2);
INSERT INTO users_roles (users_id, role_id) VALUES (5, 2);





