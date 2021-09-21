-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.13-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for paymentproject
CREATE DATABASE IF NOT EXISTS `paymentproject` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `paymentproject`;

-- Dumping structure for table paymentproject.bank
CREATE TABLE IF NOT EXISTS `bank` (
  `bic` char(11) NOT NULL,
  `bankname` varchar(100) NOT NULL,
  PRIMARY KEY (`bic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table paymentproject.bank: ~3 rows (approximately)
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` (`bic`, `bankname`) VALUES
	('ABBLINBBXXX', 'AB BANK LIMITED'),
	('ABNAINBBAHM', 'ABN AMRO BANK N.V.'),
	('ACBLINBBXXX', 'ABHYUDAYA CO-OPERATIVE BANK LTD.');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;

-- Dumping structure for table paymentproject.currency
CREATE TABLE IF NOT EXISTS `currency` (
  `currencycode` char(3) NOT NULL,
  `currencyname` varchar(100) NOT NULL,
  `conversionrate` decimal(10,0) NOT NULL,
  PRIMARY KEY (`currencycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table paymentproject.currency: ~5 rows (approximately)
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` (`currencycode`, `currencyname`, `conversionrate`) VALUES
	('EUR', 'Euro', 84),
	('GBP', 'Great British Pound', 102),
	('INR', 'Indian Rupees', 1),
	('JPY', 'Japanese Yen', 1),
	('USD', 'US Dollar', 74);
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;

-- Dumping structure for table paymentproject.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `customerid` char(14) NOT NULL,
  `accountholdername` varchar(50) NOT NULL,
  `overdraftflag` tinyint(1) NOT NULL DEFAULT 0,
  `clearbalance` decimal(10,0) NOT NULL,
  `customeraddress` varchar(100) NOT NULL,
  `customercity` varchar(100) NOT NULL,
  `customertype` char(1) NOT NULL,
  PRIMARY KEY (`customerid`),
  UNIQUE KEY `accountholdername` (`accountholdername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table paymentproject.customer: ~4 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customerid`, `accountholdername`, `overdraftflag`, `clearbalance`, `customeraddress`, `customercity`, `customertype`) VALUES
	('42895235807723', 'HDFC BANK -(DELHI)', 0, 1125005, ' ', ' ', 'B'),
	('69652133523248', 'HDFC BANK -(CHENNAI)', 1, 217747, '', '', 'B'),
	('71319440983198', 'A M MAYANNA', 0, 218258, '', '', 'I'),
	('83020817828620', 'A KRISHNA MOHAN', 1, 1317, '', '', 'I');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table paymentproject.customeruser
CREATE TABLE IF NOT EXISTS `customeruser` (
  `userid` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `customerid` char(14) NOT NULL,
  `userpassword` varchar(100) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username` (`username`),
  KEY `customerid` (`customerid`),
  CONSTRAINT `customeruser_ibfk_1` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table paymentproject.customeruser: ~2 rows (approximately)
/*!40000 ALTER TABLE `customeruser` DISABLE KEYS */;
INSERT INTO `customeruser` (`userid`, `username`, `customerid`, `userpassword`) VALUES
	(1, 'A M MAYANNA', '71319440983198', 'mayanna'),
	(2, 'Krishna_Mohan', '83020817828620', 'krishna');
/*!40000 ALTER TABLE `customeruser` ENABLE KEYS */;

-- Dumping structure for table paymentproject.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `employeeid` int(11) NOT NULL,
  `employeename` varchar(100) NOT NULL,
  `employeepassword` varchar(100) NOT NULL,
  PRIMARY KEY (`employeeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table paymentproject.employee: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

-- Dumping structure for table paymentproject.logger
CREATE TABLE IF NOT EXISTS `logger` (
  `loggerid` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` char(14) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `employeeid` int(11) DEFAULT NULL,
  `screename` varchar(100) DEFAULT NULL,
  `action` varchar(100) DEFAULT NULL,
  `ipaddress` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`loggerid`),
  KEY `employeeid` (`employeeid`),
  KEY `userid` (`userid`),
  KEY `logger_ibfk_1` (`customerid`),
  CONSTRAINT `logger_ibfk_1` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`),
  CONSTRAINT `logger_ibfk_2` FOREIGN KEY (`employeeid`) REFERENCES `employee` (`employeeid`),
  CONSTRAINT `logger_ibfk_3` FOREIGN KEY (`userid`) REFERENCES `customeruser` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table paymentproject.logger: ~1 rows (approximately)
/*!40000 ALTER TABLE `logger` DISABLE KEYS */;
INSERT INTO `logger` (`loggerid`, `customerid`, `userid`, `employeeid`, `screename`, `action`, `ipaddress`) VALUES
	(1, '71319440983198', NULL, NULL, 'Initiate Payment', 'Payment Transfer', '192.168.1.1');
/*!40000 ALTER TABLE `logger` ENABLE KEYS */;

-- Dumping structure for table paymentproject.message
CREATE TABLE IF NOT EXISTS `message` (
  `messagecode` char(4) NOT NULL,
  `instruction` varchar(200) NOT NULL,
  PRIMARY KEY (`messagecode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table paymentproject.message: ~9 rows (approximately)
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` (`messagecode`, `instruction`) VALUES
	('CHQB', 'beneficiary customer must be paid by cheque only.'),
	('CORT', 'Payment is made in settlement for a trade.'),
	('HOLD', 'Beneficiary customer or claimant will call upon identification.'),
	('INTC', 'Payment between two companies that belongs to the same group.'),
	('PHOB', 'Please advise the intermediary institution by phone.'),
	('PHOI', 'Please advise the intermediary by phone.'),
	('PHON', 'Please advise the account with institution by phone.'),
	('REPA', 'Payments has a related e-Payments reference.'),
	('SDVA', 'Payment must be executed with same day value to the');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;

-- Dumping structure for table paymentproject.transaction
CREATE TABLE IF NOT EXISTS `transaction` (
  `transactionid` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` char(14) NOT NULL,
  `currencycode` char(3) NOT NULL DEFAULT 'INR',
  `senderBIC` char(11) NOT NULL,
  `receiverBIC` char(11) DEFAULT NULL,
  `receiveraccountholdernumber` char(14) NOT NULL,
  `receiveraccountholdername` varchar(100) NOT NULL,
  `transfertypecode` char(1) NOT NULL,
  `messagecode` char(4) NOT NULL,
  `currencyamount` decimal(10,0) NOT NULL DEFAULT 0,
  `transferfees` decimal(10,0) NOT NULL,
  `inramount` decimal(10,0) NOT NULL,
  `transferdate` date NOT NULL,
  PRIMARY KEY (`transactionid`),
  KEY `currencycode` (`currencycode`),
  KEY `messagecode` (`messagecode`),
  KEY `receiverBIC` (`receiverBIC`),
  KEY `senderBIC` (`senderBIC`),
  KEY `transfertypecode` (`transfertypecode`),
  KEY `transaction_ibfk_2` (`customerid`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`currencycode`) REFERENCES `currency` (`currencycode`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`),
  CONSTRAINT `transaction_ibfk_3` FOREIGN KEY (`messagecode`) REFERENCES `message` (`messagecode`),
  CONSTRAINT `transaction_ibfk_4` FOREIGN KEY (`receiverBIC`) REFERENCES `bank` (`bic`),
  CONSTRAINT `transaction_ibfk_5` FOREIGN KEY (`senderBIC`) REFERENCES `bank` (`bic`),
  CONSTRAINT `transaction_ibfk_6` FOREIGN KEY (`transfertypecode`) REFERENCES `transfertypes` (`transfertypecode`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- Dumping data for table paymentproject.transaction: ~51 rows (approximately)
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` (`transactionid`, `customerid`, `currencycode`, `senderBIC`, `receiverBIC`, `receiveraccountholdernumber`, `receiveraccountholdername`, `transfertypecode`, `messagecode`, `currencyamount`, `transferfees`, `inramount`, `transferdate`) VALUES
	(1, '42895235807723', 'INR', 'ACBLINBBXXX', NULL, '69652133523248', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(5, '71319440983198', 'INR', 'ACBLINBBXXX', NULL, '9999999999', 'shalini', 'C', 'REPA', 1, 30, 2500, '2021-06-16'),
	(6, '71319440983198', 'INR', 'ACBLINBBXXX', NULL, '123456', 'trial', 'C', 'PHOI', 1, 368, 1470, '2021-08-28'),
	(7, '71319440983198', 'INR', 'ACBLINBBXXX', NULL, '123456', 'trial', 'C', 'PHOI', 1, 250, 1000, '2021-08-29'),
	(8, '69652133523248', 'INR', 'ACBLINBBXXX', NULL, '42895235807723', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 750000, 3000000, '2021-08-30'),
	(9, '69652133523248', 'INR', 'ACBLINBBXXX', NULL, '42895235807723', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 750000, 3000000, '2021-08-30'),
	(10, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 15000, 60000, '2021-08-30'),
	(11, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 15000, 60000, '2021-08-30'),
	(12, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 15000, 60000, '2021-08-30'),
	(13, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 15000, 60000, '2021-08-30'),
	(14, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 15000, 60000, '2021-08-30'),
	(15, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 15000, 60000, '2021-08-30'),
	(16, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(17, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(18, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(19, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(20, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(21, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(22, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(23, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(24, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(25, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(26, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(27, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(28, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 250, 1000, '2021-08-30'),
	(29, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'HDFC BANK -(CHENNAI)', 'C', 'PHOI', 1, 1, 2, '2021-08-30'),
	(30, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'FAWAZ AKHRAS', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(31, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', ' FAWAZ AKHRAS', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(32, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'FAWAZ AKHRAS', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(33, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', ' FAWAZ AKHRAS', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(34, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', ' FAWAZ AKHRAS', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(35, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', ' FAWAZ AKHRAS', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(36, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'FAWAZ AKHRAS', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(37, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'FAWAZ AKHRAS', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(38, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'FAWAZ AKHRAS', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(39, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', ' FAWAZ AKHRAS', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(40, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', '2ND ACADEMY OF NATURAL SCIENCES', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(41, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'Pakistan', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(42, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'Hatim Ahmad', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(43, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'Hatim Ahmad', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(44, '83020817828620', 'INR', 'ACBLINBBXXX', NULL, '71319440983198', 'Hatim Ahmad', 'C', 'PHOI', 1, 1, 2, '2021-08-31'),
	(45, '83020817828620', 'INR', 'ACBLINBBXXX', 'ABBLINBBXXX', '123', 'ABC', 'C', 'CHQB', 1, 25, 100, '2021-09-10'),
	(46, '83020817828620', 'INR', 'ACBLINBBXXX', 'ABBLINBBXXX', '123', 'ABC', 'C', 'HOLD', 1, 25, 100, '2021-09-11'),
	(47, '83020817828620', 'INR', 'ACBLINBBXXX', 'ABBLINBBXXX', '123', 'ABC', 'C', 'HOLD', 1, 25, 100, '2021-09-11'),
	(48, '83020817828620', 'INR', 'ACBLINBBXXX', 'ABBLINBBXXX', '123', 'ABC', 'C', 'HOLD', 1, 3, 10, '2021-09-11'),
	(49, '83020817828620', 'INR', 'ACBLINBBXXX', 'ABBLINBBXXX', '123', 'ABC', 'C', 'HOLD', 1, 25, 100, '2021-09-11'),
	(50, '83020817828620', 'INR', 'ACBLINBBXXX', 'ABBLINBBXXX', '123', 'ABC', 'C', 'INTC', 1, 0, 1, '2021-09-12'),
	(51, '83020817828620', 'INR', 'ACBLINBBXXX', 'ABBLINBBXXX', '123', 'ABC', 'C', 'HOLD', 1, 0, 1, '2021-09-12'),
	(52, '83020817828620', 'INR', 'ACBLINBBXXX', 'ABBLINBBXXX', '123', 'ABC', 'B', 'HOLD', 1, 25, 100, '2021-09-12'),
	(53, '83020817828620', 'INR', 'ACBLINBBXXX', 'ABBLINBBXXX', '123', 'ABC', 'B', 'PHOI', 1, 3, 10, '2021-09-12'),
	(54, '71319440983198', 'INR', 'ACBLINBBXXX', 'ABBLINBBXXX', '123', 'ABC', 'C', 'CHQB', 1, 25, 100, '2021-09-13');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;

-- Dumping structure for table paymentproject.transfertypes
CREATE TABLE IF NOT EXISTS `transfertypes` (
  `transfertypecode` char(1) NOT NULL,
  `transfertypedescription` varchar(100) NOT NULL,
  PRIMARY KEY (`transfertypecode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table paymentproject.transfertypes: ~3 rows (approximately)
/*!40000 ALTER TABLE `transfertypes` DISABLE KEYS */;
INSERT INTO `transfertypes` (`transfertypecode`, `transfertypedescription`) VALUES
	('B', 'Bank Transfer'),
	('C', 'Customer Transfer'),
	('O', 'Bank Transfer for Own Account');
/*!40000 ALTER TABLE `transfertypes` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
