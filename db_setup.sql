-- ============================================================
--  KLEF FSAD EXAM – Hibernate HQL Service Project
--  Run this in MySQL Workbench before running the Java project
-- ============================================================

CREATE DATABASE IF NOT EXISTS fsadexam;
USE fsadexam;

CREATE TABLE IF NOT EXISTS service (
    service_id       INT          NOT NULL AUTO_INCREMENT,
    service_name     VARCHAR(100) NOT NULL,
    service_date     DATE,
    service_status   VARCHAR(50),
    service_type     VARCHAR(100),
    service_cost     DOUBLE       DEFAULT 0.0,
    service_duration VARCHAR(50),
    service_provider VARCHAR(100),
    service_location VARCHAR(150),
    PRIMARY KEY (service_id)
);

SHOW TABLES;
DESCRIBE service;
