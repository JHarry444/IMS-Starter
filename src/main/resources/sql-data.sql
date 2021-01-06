INSERT INTO `ims`.`customers` (`customer_id`,`first_name`, `last_name`, `email_address`, `address_line_1`, `postcode`) VALUES (`Claude`,`Duvalier`,`cd1990@gmail.com`,`65 Hudson Street`, `DG6 6PZ`);
INSERT INTO `ims`.`customers` (`customer_id`,`first_name`, `last_name`, `email_address`, `address_line_1`, `postcode`) VALUES (`Tasha`,`Lee`,`tasha.lee@outlook.com`,`35 Crown Street`, `W12 4WW`);
INSERT INTO `ims`.`customers` (`customer_id`,`first_name`, `last_name`, `email_address`, `address_line_1`, `postcode`) VALUES (`Erwin`,`Smith`,`esmith@gmail.com`,`2 Guild Street`, `NW8 8YU`);
INSERT INTO `ims`.`customers` (`customer_id`,`first_name`, `last_name`, `email_address`, `address_line_1`, `postcode`) VALUES (`Levi`,`Ackerman`,`l.ackerman@hotmail.co.uk`,`89 Great Western Road`,`HP27 5DD`);
INSERT INTO `ims`.`customers` (`customer_id`,`first_name`, `last_name`, `email_address`, `address_line_1`, `postcode`) VALUES (`Sasha`,`Braus`,`sasha@outlook.com`,`57 Winchester Road`, `PO20 8HN`);

INSERT INTO `ims`.`products` (`product_id`, `product_name`, `artist`, `release_date` , `product_price`, `product_qty`) VALUES (`SAWAYAMA (Deluxe Edition)`,`Rina Sawayama`, 20200417, 9.99, 500);
INSERT INTO `ims`.`products` (`product_id`, `product_name`, `artist`, `release_date` , `product_price`, `product_qty`) VALUES (`BE (Deuluxe Edition)`,`BTS`, 20201120, 49.63, 850);
INSERT INTO `ims`.`products` (`product_id`, `product_name`, `artist`, `release_date` , `product_price`, `product_qty`) VALUES (`PHASES`,`Chase Atlantic`, 20190629, 11.79, 300);
INSERT INTO `ims`.`products` (`product_id`, `product_name`, `artist`, `release_date` , `product_price`, `product_qty`) VALUES (`POST HUMANS: SURVIVAL HORROR`,`Bring Me The Horizon`, 20201030, 10.99, 745);
INSERT INTO `ims`.`products` (`product_id`, `product_name`, `artist`, `release_date` , `product_price`, `product_qty`) VALUES (`Head in the Clouds`,`88Rising`, 20180720, 10.40, 100);

INSERT INTO `ims`.`orders` (`order_id`, `customer_id`, `product_id`, `order_qty`, `total_price`, `order_status`) VALUES (1, 5, 1, 10.40, TRUE);
INSERT INTO `ims`.`orders` (`order_id`, `customer_id`, `product_id`, `order_qty`, `total_price`, `order_status`) VALUES (1, 4, 1, 10.99, FALSE);
INSERT INTO `ims`.`orders` (`order_id`, `customer_id`, `product_id`, `order_qty`, `total_price`, `order_status`) VALUES (3, 3, 1, 11.79, FALSE);

INSERT INTO `ims`.`orderline` (`order_id`, `product_id`) VALUES (1, 5);
