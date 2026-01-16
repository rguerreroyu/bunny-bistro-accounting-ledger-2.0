USE sys;

# ---------------------------------------------------------------------- #
# Target DBMS:           MySQL                                           #
# Project name:          bunnybistroledger                                      #
# ---------------------------------------------------------------------- #
DROP DATABASE IF EXISTS bunnybistroledger;

CREATE DATABASE IF NOT EXISTS bunnybistroledger;

USE bunnybistroledger;

# ---------------------------------------------------------------------- #
# Tables                                                                 #
# ---------------------------------------------------------------------- #

DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions (
    key_id INT NOT NULL AUTO_INCREMENT,
    transaction_time DATETIME NOT NULL,
    description VARCHAR(255) NOT NULL,
    vendor VARCHAR(50) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    is_deposit BOOLEAN NOT NULL,
    PRIMARY KEY (key_id)
);

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    user_id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    hashed_password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id)
);
-- new tables

/* INSERT Transactions */
INSERT INTO transactions
(transaction_time, description, vendor, amount, is_deposit)
VALUES
	('2025-07-27 10:21:41', 'July Sales Deposit', 'Bank of Bunnica', 5170.62, TRUE),
	('2025-08-27 13:15:14', 'August Sales Deposit', 'Bank of Bunnica', 3503.75, TRUE),
	('2025-09-08 19:14:53', 'Utility Payment', 'Flower Power Energy', 225.50, FALSE),
	('2025-09-27 07:08:32', 'September Sales Deposit', 'Bank of Bunnica', 4370.29, TRUE),
	('2025-09-30 17:32:15', 'Refund Customer', 'Judy Hopps', 12.57, FALSE),
	('2025-10-10 09:45:50', 'Event Pop-Up Sales', 'Bites & Brews Market', 561.48, TRUE),
	('2025-10-14 12:52:38', 'Utility Payment', 'Flower Power Energy', 225.50, FALSE),
	('2025-10-14 16:32:00', 'Inventory Restock (Matcha Powder)', 'Marukyu Koyamaen', 310.00, FALSE),
	('2025-10-15 09:15:55', 'Barista Wages', 'Bunny Bistro Staff', 3500.00, FALSE),
	('2025-10-16 10:32:25', 'Repair Espresso Machine', 'B.I.T.', 85.00, FALSE),
	('2025-10-17 11:06:10', 'October Sales Deposit', 'Bank of Bunnica', 6000.00, TRUE),
	('2025-10-17 11:06:39', 'Barista Wages', 'Bunny Bistro Staff', 2500.00, FALSE);


/*  INSERT Users  */
INSERT INTO users (user_id, username, hashed_password, role) 
VALUES  (1, 'user','$2a$10$NkufUPF3V8dEPSZeo1fzHe9ScBu.LOay9S3N32M84yuUM2OJYEJ/.','ROLE_USER'),
        (2, 'admin','$2a$10$lfQi9jSfhZZhfS6/Kyzv3u3418IgnWXWDQDk7IbcwlCFPgxg9Iud2','ROLE_ADMIN'),
        (3, 'george','$2a$10$lfQi9jSfhZZhfS6/Kyzv3u3418IgnWXWDQDk7IbcwlCFPgxg9Iud2','ROLE_USER');