drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
`itemid` INT(11) NOT NULL AUTO_INCREMENT,
`product` VARCHAR(40) DEFAULT NULL,
`price` DOUBLE DEFAULT NULL,
PRIMARY KEY(`itemid`)
);

CREATE TABLE IF NOT EXISTS `orders` (
`orderid` int(11) NOT NULL AUTO_INCREMENT,
`id` int(11),
`total` DOUBLE DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `orderline`(
`orderlineid` int(11) NOT NULL AUTO_INCREMENT,
`orderid` int(11),
`itemid` int(11),
`itemquant` int(11),
PRIMARY KEY(`orderlineid`)
);