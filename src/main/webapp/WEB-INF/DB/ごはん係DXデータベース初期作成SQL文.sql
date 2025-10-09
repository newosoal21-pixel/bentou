-- データベースの作成
CREATE DATABASE `lunchclerkDX` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- データベースの使用
USE `lunchclerkDX`;

-- DEPARTMENT テーブル
CREATE TABLE `department` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- EMPLOYEES テーブル
CREATE TABLE `employees` (
  `employees_id` int NOT NULL,
  `password` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_admin` boolean,
  `delete_flag` boolean,
  `department_id` int DEFAULT NULL,
  PRIMARY KEY (`employees_id`),
  KEY `employees_ibfk_1` (`department_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- PRODUCTS テーブル
CREATE TABLE `products` (
  `products_id` int NOT NULL AUTO_INCREMENT,
  `price` int NOT NULL,
  `itemname` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cal` int NOT NULL,
  `display_flag` tinyint(1) NOT NULL,
  `delete_flag` tinyint(1) NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image_rename` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`products_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- LOGIN_HISTORY テーブル
CREATE TABLE `login_history` (
  `login_history_id` int NOT NULL AUTO_INCREMENT,
  `login_time` datetime NOT NULL,
  `employees_id` int NOT NULL,
  PRIMARY KEY (`login_history_id`),
  KEY `employees_id` (`employees_id`),
  CONSTRAINT `login_history_ibfk_1` FOREIGN KEY (`employees_id`) REFERENCES `employees` (`employees_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ORDERS テーブル
CREATE TABLE `orders` (
  `orders_id` int NOT NULL AUTO_INCREMENT,
  `employees_id` int NOT NULL,
  `quantity` int NOT NULL,
  `orderdate` datetime NOT NULL,
  `products_id` int NOT NULL,
  `order_flag` boolean,
  PRIMARY KEY (`orders_id`),
  KEY `employees_id` (`employees_id`),
  KEY `orders_ibfk_2` (`products_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`employees_id`) REFERENCES `employees` (`employees_id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`products_id`) REFERENCES `products` (`products_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ORDER_DEADLINE テーブル (追加)
CREATE TABLE `order_deadline` (
  `deadline_time` time NOT NULL,
  PRIMARY KEY (`deadline_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




-- DEPARTMENT
INSERT INTO DEPARTMENT(DEPARTMENT_ID,DEPARTMENT_NAME)
 VALUES  (1,'総務部'),
 (2,'営業部'),
 (3,'商品開発部'),
 (4,'研究開発部'),
 (5,'技術部'),
 (6,'情報システム部');

-- EMPLOYEES
INSERT INTO `EMPLOYEES` (`employees_id`, `password`, `user_name`, `is_admin`, `delete_flag`,`department_id`) VALUES (1001, 'superuser', '紫式部', 1, 0, 1);

-- PRODUCTS
INSERT INTO PRODUCTS (products_id, price, itemname, cal, display_flag, delete_flag, image, image_rename)
VALUES (1, 450, 'のり弁当', 500, 1, 0, 'images/bentou1.jpg', 'images/bentou1.jpg'),
(2, 500, '唐揚げ弁当', 550, 1, 0, 'images/bentou2.jpg', 'images/bentou2.jpg'),
(3, 500, '幕の内弁当', 500, 1, 0, 'images/bentou3.jpg', 'images/bentou3.jpg'),
(4, 550, '牛タン弁当', 500, 1, 0, 'images/bentou4.jpg', 'images/bentou4.jpg'),
(5, 300, 'フライドポテト', 450, 1, 0, 'images/potato.jpg', 'images/potato.jpg'),
(6, 100, '飲み物', 0, 1, 0, 'images/drink.jpg', 'images/drink.jpg');

-- LOGIN_HISTORY
INSERT INTO `LOGIN_HISTORY` (`login_history_id`, `login_time`, `employees_id`) VALUES (1, '2025-09-11 11:00:00', 1001);

-- ORDERS
INSERT INTO `ORDERS` (`orders_id`, `employees_id`, `quantity`, `orderdate`, `products_id`, `order_flag`) VALUES (1, 1001, 1, '2025-09-11 11:00:00', 1, 0);

-- ORDER_DEADLINE
INSERT INTO `ORDER_DEADLINE` (`deadline_time`) VALUES ('10:30:00');

ALTER TABLE orders MODIFY orders_id INT AUTO_INCREMENT;