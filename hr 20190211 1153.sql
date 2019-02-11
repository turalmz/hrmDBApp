﻿--
-- Script was generated by Devart dbForge Studio for MySQL, Version 8.0.124.0
-- Product home page: http://www.devart.com/dbforge/mysql/studio
-- Script date 2/11/2019 11:53:05 AM
-- Server version: 5.6.41-log
-- Client version: 4.1
--

-- 
-- Disable foreign keys
-- 
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

-- 
-- Set SQL mode
-- 
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 
-- Set character set the client will use to send SQL statements to the server
--
SET NAMES 'utf8';

--
-- Set default database
--
USE hr;

--
-- Drop table `job_history`
--
DROP TABLE IF EXISTS job_history;

--
-- Drop table `employees`
--
DROP TABLE IF EXISTS employees;

--
-- Drop table `jobs`
--
DROP TABLE IF EXISTS jobs;

--
-- Drop table `departments`
--
DROP TABLE IF EXISTS departments;

--
-- Drop table `locations`
--
DROP TABLE IF EXISTS locations;

--
-- Drop table `countries`
--
DROP TABLE IF EXISTS countries;

--
-- Drop table `regions`
--
DROP TABLE IF EXISTS regions;

--
-- Set default database
--
USE hr;

--
-- Create table `regions`
--
CREATE TABLE regions (
  id VARCHAR(2) NOT NULL,
  NAME VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_unicode_ci;

--
-- Create table `countries`
--
CREATE TABLE countries (
  ID VARCHAR(2) NOT NULL,
  NAME VARCHAR(50) DEFAULT NULL,
  REGION_ID VARCHAR(2) DEFAULT NULL,
  PRIMARY KEY (ID)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_unicode_ci;

--
-- Create foreign key
--
ALTER TABLE countries 
  ADD CONSTRAINT FK_countries_REGION_ID FOREIGN KEY (REGION_ID)
    REFERENCES regions(id) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Create table `locations`
--
CREATE TABLE locations (
  id INT(11) NOT NULL AUTO_INCREMENT,
  STREET_ADDRESS VARCHAR(255) DEFAULT NULL,
  POSTAL_CODE VARCHAR(255) DEFAULT NULL,
  CITY VARCHAR(255) DEFAULT NULL,
  STATE_PROVINCE VARCHAR(255) DEFAULT NULL,
  COUNTRY_ID VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_unicode_ci;

--
-- Create foreign key
--
ALTER TABLE locations 
  ADD CONSTRAINT FK_locations_COUNTRY_ID FOREIGN KEY (COUNTRY_ID)
    REFERENCES countries(ID) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Create table `departments`
--
CREATE TABLE departments (
  ID INT(11) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(50) DEFAULT NULL,
  MANAGER_ID INT(11) DEFAULT NULL,
  LOCATION_ID INT(11) DEFAULT NULL,
  PRIMARY KEY (ID)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_unicode_ci;

--
-- Create foreign key
--
ALTER TABLE departments 
  ADD CONSTRAINT FK_departments_LOCATION_ID FOREIGN KEY (LOCATION_ID)
    REFERENCES locations(id) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Create table `jobs`
--
CREATE TABLE jobs (
  ID INT(11) NOT NULL AUTO_INCREMENT,
  JOB_TITLE VARCHAR(50) DEFAULT NULL,
  MIN_SALARY DECIMAL(8, 2) DEFAULT NULL,
  MAX_SALARY DECIMAL(8, 2) DEFAULT NULL,
  PRIMARY KEY (ID)
)
ENGINE = INNODB,
AUTO_INCREMENT = 4,
AVG_ROW_LENGTH = 8192,
CHARACTER SET utf8,
COLLATE utf8_unicode_ci;

--
-- Create table `employees`
--
CREATE TABLE employees (
  ID INT(11) NOT NULL AUTO_INCREMENT,
  FIRSTNAME VARCHAR(100) DEFAULT NULL,
  EMAIL VARCHAR(100) DEFAULT NULL,
  PHONE_NUMBER VARCHAR(20) DEFAULT NULL,
  HIRE_DATE DATE DEFAULT NULL,
  JOB_ID INT(11) DEFAULT NULL,
  SALARY DECIMAL(8, 2) DEFAULT NULL,
  COMMISSION_PCT DECIMAL(2, 2) DEFAULT NULL,
  MANAGER_ID INT(11) DEFAULT NULL,
  DEPARTMENT_ID INT(11) DEFAULT NULL,
  LASTNAME VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (ID)
)
ENGINE = INNODB,
AUTO_INCREMENT = 17,
AVG_ROW_LENGTH = 5461,
CHARACTER SET utf8,
COLLATE utf8_unicode_ci;

--
-- Create foreign key
--
ALTER TABLE employees 
  ADD CONSTRAINT FK_employees_DEPARTMENT_ID FOREIGN KEY (DEPARTMENT_ID)
    REFERENCES departments(ID) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Create foreign key
--
ALTER TABLE employees 
  ADD CONSTRAINT FK_employees_JOB_ID FOREIGN KEY (JOB_ID)
    REFERENCES jobs(ID) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Create foreign key
--
ALTER TABLE employees 
  ADD CONSTRAINT FK_employees_MANAGER_ID FOREIGN KEY (MANAGER_ID)
    REFERENCES employees(ID) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Create foreign key
--
ALTER TABLE departments 
  ADD CONSTRAINT FK_departments_MANAGER_ID FOREIGN KEY (MANAGER_ID)
    REFERENCES employees(ID) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Create table `job_history`
--
CREATE TABLE job_history (
  ID INT(11) NOT NULL AUTO_INCREMENT,
  EMPLOYEE_ID INT(11) DEFAULT NULL,
  START_DATE DATE DEFAULT NULL,
  END_DATE DATE DEFAULT NULL,
  JOB_ID INT(11) DEFAULT NULL,
  DEPARTMENT_ID INT(11) DEFAULT NULL,
  PRIMARY KEY (ID)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_unicode_ci;

--
-- Create foreign key
--
ALTER TABLE job_history 
  ADD CONSTRAINT FK_job_history_DEPARTMENT_ID FOREIGN KEY (DEPARTMENT_ID)
    REFERENCES departments(ID) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Create foreign key
--
ALTER TABLE job_history 
  ADD CONSTRAINT FK_job_history_EMPLOYEE_ID FOREIGN KEY (EMPLOYEE_ID)
    REFERENCES employees(ID) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Create foreign key
--
ALTER TABLE job_history 
  ADD CONSTRAINT FK_job_history_JOB_ID FOREIGN KEY (JOB_ID)
    REFERENCES jobs(ID) ON DELETE CASCADE ON UPDATE CASCADE;

-- 
-- Dumping data for table regions
--
-- Table hr.regions does not contain any data (it is empty)

-- 
-- Dumping data for table countries
--
-- Table hr.countries does not contain any data (it is empty)

-- 
-- Dumping data for table locations
--
-- Table hr.locations does not contain any data (it is empty)

-- 
-- Dumping data for table jobs
--
INSERT INTO jobs VALUES
(1, 'IT project manager', 800.00, 3000.00),
(2, 'SQL Developer', 500.00, 1500.00),
(3, 'PL/SQL Developer', 500.00, 1800.00);

-- 
-- Dumping data for table employees
--
INSERT INTO employees VALUES
(1, 'Alim Cavadov', 'alim@gmail.com', '+994553293403', '2019-01-28', 1, 1850.00, 0.12, NULL, NULL, NULL),
(2, 'Kamil Demirov', 'kamil@gmail.com', '+994703293403', '2019-01-28', 2, 750.00, 0.12, 1, NULL, NULL),
(16, 'Alim', '', '', NULL, NULL, 0.00, 0.00, NULL, NULL, '');

-- 
-- Dumping data for table departments
--
-- Table hr.departments does not contain any data (it is empty)

-- 
-- Dumping data for table job_history
--
-- Table hr.job_history does not contain any data (it is empty)

-- 
-- Restore previous SQL mode
-- 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

-- 
-- Enable foreign keys
-- 
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;