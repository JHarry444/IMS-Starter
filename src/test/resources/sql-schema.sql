DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers`
(
    `id`         INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname`    VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `item`;

CREATE TABLE IF NOT EXISTS `item`
(
    `iditem` INT         NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(45) NOT NULL,
    `price`  DOUBLE      NOT NULL,
    PRIMARY KEY (`iditem`)
);