CREATE SCHEMA IF NOT EXISTS TRANSACTIONS;

CREATE TABLE TRANSACTIONS.TRANSACTION(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    ACCOUNT_ID INT NOT NULL,
    TYPE VARCHAR(255) NOT NULL,
    TIMESTAMP TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    AMOUNT FLOAT NOT NULL
);
