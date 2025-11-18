-- データベースの使用
USE `lunchclerkDX`;

-- --- テーブル定義 ---

-- 1. 部署 (departments) テーブル
CREATE TABLE `departments` (
  `department_id` INT NOT NULL AUTO_INCREMENT COMMENT '部署ID',
  `department_name` VARCHAR(64) NOT NULL COMMENT '部署名',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='部署情報';

-- 2. 従業員 (employees) テーブル
CREATE TABLE `employees` (
  -- PK/ID の名前を employee_id に変更
  `employee_id` INT NOT NULL AUTO_INCREMENT COMMENT '従業員ID', 
  -- パスワードハッシュ用にVARCHARの長さを255に変更
  `password_hash` VARCHAR(255) NOT NULL COMMENT 'パスワードハッシュ', 
  `user_name` VARCHAR(64) NOT NULL COMMENT '氏名',
  -- NOT NULL と DEFAULT 値を追加
  `is_admin` BOOLEAN NOT NULL DEFAULT 0 COMMENT '管理者フラグ (1:管理者)', 
  `delete_flag` BOOLEAN NOT NULL DEFAULT 0 COMMENT '削除フラグ (1:削除済)', 
  `department_id` INT DEFAULT NULL COMMENT '部署ID (FK)',
  PRIMARY KEY (`employee_id`),
  KEY `fk_employee_department` (`department_id`),
  CONSTRAINT `fk_employee_department` FOREIGN KEY (`department_id`) REFERENCES `departments` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='従業員情報';

-- 3. 商品 (products) テーブル
CREATE TABLE `products` (
  `product_id` INT NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `price` INT NOT NULL COMMENT '価格',
  `item_name` VARCHAR(64) NOT NULL COMMENT '商品名', -- itemname を item_name に変更
  `calories` INT NOT NULL COMMENT 'カロリー', -- cal を calories に変更
  `display_flag` BOOLEAN NOT NULL DEFAULT 1 COMMENT '表示フラグ (1:表示)',
  `delete_flag` BOOLEAN NOT NULL DEFAULT 0 COMMENT '削除フラグ (1:削除済)',
  `image_path` VARCHAR(255) NOT NULL COMMENT '画像ファイルパス', -- image を image_path に変更
  `image_rename` VARCHAR(255) NOT NULL COMMENT 'リネーム後の画像名',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品情報';

-- 4. ログイン履歴 (login_history) テーブル
CREATE TABLE `login_history` (
  `login_history_id` INT NOT NULL AUTO_INCREMENT COMMENT '履歴ID',
  `login_time` DATETIME NOT NULL COMMENT 'ログイン時刻',
  `employee_id` INT NOT NULL COMMENT '従業員ID (FK)',
  PRIMARY KEY (`login_history_id`),
  KEY `fk_login_employee` (`employee_id`),
  CONSTRAINT `fk_login_employee` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='ログイン履歴';

-- 5. 注文 (orders) テーブル
CREATE TABLE `orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT COMMENT '注文ID',
  `employee_id` INT NOT NULL COMMENT '従業員ID (FK)',
  `quantity` INT NOT NULL COMMENT '数量',
  `order_date` DATETIME NOT NULL COMMENT '注文日時', -- orderdate を order_date に変更
  `product_id` INT NOT NULL COMMENT '商品ID (FK)',
  `order_flag` BOOLEAN NOT NULL DEFAULT 0 COMMENT '注文状態フラグ (0:未確定/1:確定など)',
  PRIMARY KEY (`order_id`),
  KEY `fk_order_employee` (`employee_id`),
  KEY `fk_order_product` (`product_id`),
  CONSTRAINT `fk_order_employee` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`),
  CONSTRAINT `fk_order_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='注文情報';

-- 6. 注文締め切り (order_deadline) テーブル
CREATE TABLE `order_deadline` (
  `deadline_time` TIME NOT NULL COMMENT '締め切り時刻',
  PRIMARY KEY (`deadline_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='注文締め切り時刻';


-- --- 初期データ挿入 ---

-- DEPARTMENTS (テーブル名を変更)
INSERT INTO `departments` (`department_id`,`department_name`)
VALUES (1,'総務部'), (2,'営業部'), (3,'商品開発部'), (4,'研究開発部'), (5,'技術部'), (6,'情報システム部');

-- EMPLOYEES (カラム名、テーブル名を変更)
-- パスワードは必ずハッシュ化して保存してください（例: 'superuser_hash'）
INSERT INTO `employees` (`employee_id`, `password_hash`, `user_name`, `is_admin`, `delete_flag`, `department_id`) 
VALUES (1001, 'superuser', '紫式部', 1, 0, 1);

-- PRODUCTS (カラム名、テーブル名を変更)
INSERT INTO `products` (`product_id`, `price`, `item_name`, `calories`, `display_flag`, `delete_flag`, `image_path`, `image_rename`)
VALUES (1, 450, 'のり弁当', 500, 1, 0, 'images/bentou1.jpg', 'images/bentou1.jpg'),
(2, 500, '唐揚げ弁当', 550, 1, 0, 'images/bentou2.jpg', 'images/bentou2.jpg'),
(3, 500, '幕の内弁当', 500, 1, 0, 'images/bentou3.jpg', 'images/bentou3.jpg'),
(4, 550, '牛タン弁当', 500, 1, 0, 'images/bentou4.jpg', 'images/bentou4.jpg'),
(5, 300, 'フライドポテト', 450, 1, 0, 'images/potato.jpg', 'images/potato.jpg'),
(6, 100, '飲み物', 0, 1, 0, 'images/drink.jpg', 'images/drink.jpg');

-- LOGIN_HISTORY (テーブル名を変更)
INSERT INTO `login_history` (`login_history_id`, `login_time`, `employee_id`) VALUES (1, '2025-09-11 11:00:00', 1001);

-- ORDERS (カラム名、テーブル名を変更)
INSERT INTO `orders` (`order_id`, `employee_id`, `quantity`, `order_date`, `product_id`, `order_flag`) VALUES (1, 1001, 1, '2025-09-11 11:00:00', 1, 0);

-- ORDER_DEADLINE (テーブル名を変更)
INSERT INTO `order_deadline` (`deadline_time`) VALUES ('18:30:00');
