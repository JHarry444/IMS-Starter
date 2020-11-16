drop database `ims`;
create database if not exists `ims`; 

use `ims`; 
create table `ims`.`customers`(
customer_id INT unique not null auto_increment,
first_name VARCHAR(60), 
last_name VARCHAR(60),
email VARCHAR(60),
PRIMARY KEY (customer_id));


