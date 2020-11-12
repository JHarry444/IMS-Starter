CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims` ;
CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `surname` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `items`(
`ID` INT(11) NOT NULL AUTO_INCREMENT,
`title` varchar(11) NULL DEFAULT NULL,
`price` float NULL DEFAULT NULL,
`stock` int default 0,
Primary key (`ID`));

 create table if not exists `orders` (
`OrderID` int NOT NULL AUTO_INCREMENT,
`CustomerID` int NOT NULL,
Primary Key (`OrderID`),
foreign key(`CustomerID`) References customers(`id`));

create table if not exists `order_item`(
`OI_ID` int NOT NULL AUTO_INCREMENT,
`OrderID` int not null,
`ItemID` int not null,
primary key (`OI_ID`),
foreign key(`OrderID`)references orders(`OrderID`),
foreign key(`ItemID`)references items(`ID`));
