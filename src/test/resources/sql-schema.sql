drop table `orderline`;
drop table `orders`;
drop table `customers`;
drop table `items`; 

CREATE TABLE IF NOT EXISTS `customers` (
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
PRIMARY KEY (`orderid`),
FOREIGN KEY (`id`) REFERENCES `customers`(`id`)
);

CREATE TABLE IF NOT EXISTS `orderline`(
`orderlineid` int(11) NOT NULL AUTO_INCREMENT,
`orderid` int(11),
`itemid` int(11),
`itemquant` int(11),
PRIMARY KEY(`orderlineid`),

FOREIGN KEY (`itemid`) REFERENCES `items` (`itemid`)
);