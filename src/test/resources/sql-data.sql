insert into `ims`.`customers` (`first_name`,`surname`) values ("tom","smith");
insert into `ims`.`customers` (`first_name`,`surname`) values ("jordan","harrison");
INSERT INTO `ims`.`items`(`title`,`price`)VALUES('cheese',10.98);
INSERT INTO `ims`.`items`(`title`,`price`)VALUES('chips',98.0);
INSERT INTO `ims`.`orders`(`CustomerID`)VALUES(1);
INSERT INTO `ims`.`orders`(`CustomerID`)VALUES(2);
INSERT INTO `ims`.`order_item`(`OrderID`,`ItemID`) VALUES(1,1);
INSERT INTO `ims`.`order_item`(`OrderID`,`ItemID`) VALUES(1,1);
INSERT INTO `ims`.`order_item`(`OrderID`,`ItemID`) VALUES(2,1);