drop schema `ims`;
CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims`;
CREATE TABLE IF NOT EXISTS `ims`.`customers`(
    `CustomerID` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `surname` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`CustomerID`));

create table if not exists `ims`.`items`(
`ItemID` int not null auto_increment,
`title` varchar(40) not NUll,
`price` float not null,
primary key (`ItemID`));


create table if not exists `ims`.`orders`(
`OrderID` int NOT NULL AUTO_INCREMENT,
`CustomerID` int NOT NULL,
Primary Key (`OrderID`),
foreign key(`CustomerID`) References customers(`CustomerID`));

create table if not exists `ims`.`order_item`(
`OI_ID` int NOT NULL AUTO_INCREMENT,
`OrderID` int not null,
`ItemID`int not null,
primary key (`OI_ID`),
foreign key(`OrderID`)references orders(`OrderID`),
foreign key(`ItemID`)references items(`ItemID`));
 
