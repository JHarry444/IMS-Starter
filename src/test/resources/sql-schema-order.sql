DROP TABLE IF EXISTS `orderdetails`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `orders` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`customerid` INT(11) NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`customerid`) REFERENCES customers(`id`)
); 
