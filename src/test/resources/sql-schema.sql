DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
)ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS `items` (
`itemid` INT(11) NOT NULL AUTO_INCREMENT,
`product` VARCHAR(40) DEFAULT NULL,
`price` DOUBLE DEFAULT NULL,
PRIMARY KEY(`itemid`)
)ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS `orders` (
`orderid` int(11) NOT NULL AUTO_INCREMENT,
`id` int(11),
PRIMARY KEY (`orderid`),
FOREIGN KEY (`id`) REFERENCES `customers`(`id`)
)ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS `orderline`(
`orderlineid` int(11) NOT NULL AUTO_INCREMENT,
`orderid` int(11),
`itemid` int(11),
`itemquant` int(11),
PRIMARY KEY(`orderlineid`),
FOREIGN KEY (`orderid`) REFERENCES `orders`(`orderid`)
ON DELETE CASCADE,
FOREIGN KEY (`itemid`) REFERENCES `items` (`itemid`)
)ENGINE = INNODB;
