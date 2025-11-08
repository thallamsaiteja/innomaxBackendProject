-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: innomaxdb
-- ------------------------------------------------------
-- Server version	9.5.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'adf98bb0-ba49-11f0-a561-24b2b985b1b6:1-140';

--
-- Table structure for table `client_goals`
--

DROP TABLE IF EXISTS `client_goals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_goals` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `achieved_amount` double NOT NULL,
  `goal_name` varchar(255) NOT NULL,
  `progress` double NOT NULL,
  `target_amount` double NOT NULL,
  `timeline` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7u3jkjdc3megky7rodftce7mt` (`user_id`),
  CONSTRAINT `FK7u3jkjdc3megky7rodftce7mt` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_goals`
--

LOCK TABLES `client_goals` WRITE;
/*!40000 ALTER TABLE `client_goals` DISABLE KEYS */;
INSERT INTO `client_goals` VALUES (1,200000,'Buy a Car',20,1000000,'2 years',6,NULL),(2,7678675,'house',10.014460350331317,76675874,'4 years',10,NULL),(3,200000,'Bike',66.66666666666666,300000,'4 years',10,NULL);
/*!40000 ALTER TABLE `client_goals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_liabilities`
--

DROP TABLE IF EXISTS `client_liabilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_liabilities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `liability_type` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  `due_date` varchar(255) DEFAULT NULL,
  `lender` varchar(255) DEFAULT NULL,
  `monthlyemi` double NOT NULL,
  `remaining_amount` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK98iyvk6i53xysmr73en9ve31j` (`user_id`),
  CONSTRAINT `FK98iyvk6i53xysmr73en9ve31j` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_liabilities`
--

LOCK TABLES `client_liabilities` WRITE;
/*!40000 ALTER TABLE `client_liabilities` DISABLE KEYS */;
INSERT INTO `client_liabilities` VALUES (1,98327498,'home loan',10,'2025-11-20','icci',82,82398),(2,42000,'Laptop',10,'2025-12-02','Indian Bank',200,2000);
/*!40000 ALTER TABLE `client_liabilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_profile`
--

DROP TABLE IF EXISTS `client_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_profile` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `annual_income` double DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `pan` varchar(255) NOT NULL,
  `total_assets` double DEFAULT NULL,
  `total_liabilities` double DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `annual_salary` double DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `occupation_type` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `profile_image_url` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `years_of_experience` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK21y1tik7mg5nkn4j88htbh4ei` (`user_id`),
  CONSTRAINT `FK21y1tik7mg5nkn4j88htbh4ei` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_profile`
--

LOCK TABLES `client_profile` WRITE;
/*!40000 ALTER TABLE `client_profile` DISABLE KEYS */;
INSERT INTO `client_profile` VALUES (1,'Hyderabad, India',850000,'1999-05-14','Software Developer','ABCDE1234F',1200000,400000,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,NULL,NULL,'2000-05-21',NULL,'ABCDE1234F',NULL,NULL,6,'Flat 202, Sai Towers','Kukatpally',600000,'Hyderabad','Sharkify AI','India','Technology','Full Stack Developer','Sai','Teja','Salaried','500072','https://example.com/profile.jpg','Telangana',2),(5,NULL,NULL,'2008-01-08',NULL,'bsvpt8763r',NULL,NULL,10,'KPHB colony','dsdasd',1457,'asdasd','kjkj','gigiu','kkj','bjbkj','ProjectDemo','Thallam',NULL,'777777',NULL,'hiohoih',6);
/*!40000 ALTER TABLE `client_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_sips`
--

DROP TABLE IF EXISTS `client_sips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_sips` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `duration_months` int NOT NULL,
  `is_active` bit(1) NOT NULL,
  `monthly_amount` double NOT NULL,
  `next_due_date` date DEFAULT NULL,
  `plan_name` varchar(255) NOT NULL,
  `returns_expected` double NOT NULL,
  `start_date` date DEFAULT NULL,
  `total_invested` double NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3d85vapymrbctvn86m03uxwlo` (`user_id`),
  CONSTRAINT `FK3d85vapymrbctvn86m03uxwlo` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_sips`
--

LOCK TABLES `client_sips` WRITE;
/*!40000 ALTER TABLE `client_sips` DISABLE KEYS */;
INSERT INTO `client_sips` VALUES (1,444,_binary '',45677,'2025-12-08','stock',4,'2025-11-08',20280588,10),(2,121,_binary '',1111,'2025-12-13','stock',9,'2025-11-13',134431,10),(3,13,_binary '',20000,'2025-12-08','Paraj',20,'2025-11-08',260000,10);
/*!40000 ALTER TABLE `client_sips` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `expiry_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
INSERT INTO `password_reset_token` VALUES (1,'john@example.com','2025-11-07 11:10:12.817000','5fa82c11-23c1-4446-8f29-5369de4da9d3');
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `risk_profile_results`
--

DROP TABLE IF EXISTS `risk_profile_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `risk_profile_results` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `risk_type` enum('AGGRESSIVE','CONSERVATIVE','MODERATE') NOT NULL,
  `score` int NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhkcqn182tslqhgrlfgpwtpbqd` (`user_id`),
  CONSTRAINT `FKhkcqn182tslqhgrlfgpwtpbqd` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `risk_profile_results`
--

LOCK TABLES `risk_profile_results` WRITE;
/*!40000 ALTER TABLE `risk_profile_results` DISABLE KEYS */;
/*!40000 ALTER TABLE `risk_profile_results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sips`
--

DROP TABLE IF EXISTS `sips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sips` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `duration_in_months` int NOT NULL,
  `expected_return_rate` double NOT NULL,
  `maturity_amount` double NOT NULL,
  `maturity_date` date NOT NULL,
  `monthly_amount` double NOT NULL,
  `start_date` date NOT NULL,
  `goal_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9qjvjwwleyblyht2s726m9smm` (`goal_id`),
  KEY `FK1119tls4ffd3rblljopqimpb0` (`user_id`),
  CONSTRAINT `FK1119tls4ffd3rblljopqimpb0` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FK9qjvjwwleyblyht2s726m9smm` FOREIGN KEY (`goal_id`) REFERENCES `client_goals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sips`
--

LOCK TABLES `sips` WRITE;
/*!40000 ALTER TABLE `sips` DISABLE KEYS */;
/*!40000 ALTER TABLE `sips` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `assigned_manager_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `mobile` (`mobile`),
  KEY `FK76nct68jqy5ye02ibmj4fpmr8` (`assigned_manager_id`),
  CONSTRAINT `FK76nct68jqy5ye02ibmj4fpmr8` FOREIGN KEY (`assigned_manager_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'john@example.com','John Doe','$2a$10$VXM7vZeMv5r2hUoj2rmm7OKE7HXttZQvTAc9TdydCruXygVJtIMum','CLIENT','1111111111',NULL),(2,'sai@gmail.com','Sai Teja','$2a$10$qAhww2Kddhy/BN8nXirjPuRCbp8Zyere7t10ermh9ysnCDCaC4GMy','CLIENT','1111112111',NULL),(3,'admin@example.com','Admin User','$2a$10$yDSqCV6d61DMNJzhPmBTuewr.VqOlqDlsFRBmHpyCLN0CoUGDFoBq','ADMIN','1111411111',NULL),(5,'admin1@example.com','Admin User','$2a$10$EQAuq.srJdmOrfqTe0bYw.Ij0pAZl2GPvTTdK2EFZ32W99Bb1/dFK','ADMIN','1111115111',NULL),(6,'demo@gmail.com','demo','$2a$10$E0TmIkmXG2Ow86tIc4lbdOXOSGS5bKcnVvx6GPuf5zVDaOsbwSEze','CLIENT','1114411111',NULL),(7,'admin@gmail.com','admindemo','$2a$10$v5uN0m21GAOzObXbepkl.ujcAD88sSOX2YQkK62GhN6QDROWaEos.','ADMIN','9111111111',NULL),(8,'saiteja@gmail.com','saiteja','$2a$10$J6XUkx/5OxUaPvmccUNUCuJNH.W4QzrhZlZKprtdXgClbjMDxbKPe','CLIENT','6767787879',NULL),(9,'teja@gmail.com','sai','$2a$10$2c2wJ/Mc.uqx8n202WgYX.oPmNnOr6DcR29FSoMrazmQxv24.50Mq','CLIENT','6767890987',NULL),(10,'project@gmail.com','project','$2a$10$5Rf3Rc1L.fDEcD8VlZeyzeCKgJfn60nonp.fqbb8Tg9WAkl09AiTO','CLIENT','5678567850',NULL),(11,'admin3@gmail.com','Jane Doe','$2a$10$vFHypblNlYVOssm3kD/Ao.dDhLkqIDvDzX0skymGryux9PbuHJ24S','ADMIN','9876543210',NULL),(12,'adminsai@gmail.com','adminSai','$2a$10$OP.zBPoWX9GIwGoSAT1uQuet1XxSuwYNhpdXsiKoYvYRDd98xaPsm','ADMIN','2345678745',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-08 16:38:07
