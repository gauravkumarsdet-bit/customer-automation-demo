-- Database Creation Script for Customer Automation

-- Create Database
CREATE DATABASE IF NOT EXISTS customer_automation;
USE customer_automation;

-- Create Transactions Table
CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    amount DOUBLE NOT NULL,
    date DATE NOT NULL,
    month VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_month (month),
    INDEX idx_date (date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Sample Data Insertion (Optional - app will auto-insert)
-- INSERT INTO transactions (description, amount, date, month) VALUES
-- ('Office Supplies', 150.00, '2026-01-05', 'January'),
-- ('Client Meeting', 75.50, '2026-01-10', 'January'),
-- ('Software License', 299.99, '2026-01-15', 'January');
