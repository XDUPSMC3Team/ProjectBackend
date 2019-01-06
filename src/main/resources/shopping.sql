-- MySQL dump 10.13  Distrib 5.6.39, for Linux (x86_64)
--
-- Host: localhost    Database: shopping
-- ------------------------------------------------------
-- Server version	5.6.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_administrator`
--

DROP TABLE IF EXISTS `t_administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_administrator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lxrhr44s1992q8jrqjgir9rix` (`email`),
  UNIQUE KEY `UK_hdgjm27gj1cud304td1chi6ng` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_administrator`
--

LOCK TABLES `t_administrator` WRITE;
/*!40000 ALTER TABLE `t_administrator` DISABLE KEYS */;
INSERT INTO `t_administrator` VALUES (1,'2018-12-12 15:44:01','845202636@qq.com','admin',NULL,NULL,'2018-12-12 15:44:01','admin'),(2,'2018-12-17 05:48:29','845202636','123',NULL,NULL,'2018-12-17 05:48:29','cw');
/*!40000 ALTER TABLE `t_administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_advertisement`
--

DROP TABLE IF EXISTS `t_advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_advertisement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` int(11) DEFAULT '0',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_advertisement`
--

LOCK TABLES `t_advertisement` WRITE;
/*!40000 ALTER TABLE `t_advertisement` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_attr_key`
--

DROP TABLE IF EXISTS `t_attr_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_attr_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attribute_key` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_attr_key`
--

LOCK TABLES `t_attr_key` WRITE;
/*!40000 ALTER TABLE `t_attr_key` DISABLE KEYS */;
INSERT INTO `t_attr_key` VALUES (1,'Color',3),(2,'Memory',3),(3,'Payment',3),(4,'Size',1),(5,'Factory',1),(6,'Memory',6),(7,'Color',6),(8,'Size',6),(9,'Memory',5),(10,'Color',5),(11,'Size',5),(12,'Color',4),(13,'Pic',4),(14,'Size',2),(15,'Power',2),(16,'Size',12),(17,'Color',12),(18,'Quality',12),(19,'Size',11),(20,'Color',11),(21,'Quality',11),(22,'Color',8),(23,'Memory',8),(24,'Size',8),(25,'Color',7),(26,'Quality',7),(27,'Color',9),(28,'Quality',9),(29,'Memory',9),(30,'Quality',10),(31,'Memory',10);
/*!40000 ALTER TABLE `t_attr_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_attr_value`
--

DROP TABLE IF EXISTS `t_attr_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_attr_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attribute_key_id` int(11) DEFAULT NULL,
  `attribute_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_attr_value`
--

LOCK TABLES `t_attr_value` WRITE;
/*!40000 ALTER TABLE `t_attr_value` DISABLE KEYS */;
INSERT INTO `t_attr_value` VALUES (1,3,'\"Wechat\"'),(2,3,'\"Alipay\"'),(10,1,'\"black\"'),(38,1,'\"blue\"'),(41,2,'\"512G\"'),(54,2,'\"1pic\"'),(69,2,'\"104key\"'),(70,2,'\"89key\"'),(75,2,'\"2pic\"'),(79,2,'\"32inch\"'),(80,2,'\"48inch\"'),(105,1,'\"silver\"'),(110,2,'\"1TB\"'),(123,1,'\"gold\"'),(158,4,'\"40inch\"'),(159,5,'\"Sony\"'),(164,2,'\"64G\"'),(170,2,'\"128G\"'),(171,1,'\"red\"'),(172,1,'\"white\"'),(175,1,'\"green\"'),(176,2,'\"64\"'),(177,1,'\"greem\"'),(184,9,'\"128G\"'),(185,9,'\"512G\"'),(186,9,'\"1TB\"'),(187,11,'\"13inch\"'),(188,11,'\"15inch\"'),(189,10,'\"silver\"'),(190,6,'\"64G\"'),(191,6,'\"128G\"'),(192,8,'\"13inch\"'),(193,7,'\"silver\"'),(194,7,'\"red\"'),(195,7,'\"gold\"'),(196,26,'\"64G\"'),(197,26,'\"128G\"'),(198,25,'\"red\"'),(199,25,'\"gold\"'),(200,25,'\"silver\"'),(201,30,'\"pro\"'),(202,30,'\"silm\"'),(203,31,'\"512G\"'),(204,31,'\"1T\"'),(205,26,'\"HiFi\"'),(206,25,'\"blue\"'),(207,25,'\"black\"'),(208,12,'\"black\"'),(209,12,'\"white\"'),(210,13,'\"1pic\"'),(211,13,'\"2pic\"'),(212,10,'\"black\"'),(213,16,'\"104key\"'),(214,18,'\"Green axis\"'),(215,17,'\"black\"'),(216,13,'\"1 pic\"'),(217,13,'\"2 pic\"'),(218,1,'\"yellow\"'),(219,14,'\"32mm\"'),(220,14,'\"48mm\"'),(221,15,'\"500W\"'),(222,31,'\"white\"'),(223,4,'\"42 inch\"'),(224,4,'\"48 inch\"'),(225,5,'\"4K\"'),(226,4,'\"38 inch\"'),(227,4,'\"32 inch\"'),(228,11,'\"15 inch\"'),(229,8,'\"9 inch\"'),(230,7,'\"black\"'),(231,30,'\"NS\"'),(232,31,'\"1TB\"'),(233,30,'\"Xbox one\"'),(234,30,'\"normal\"'),(235,31,'\"128\"'),(236,31,'\"256\"'),(237,31,'\"512\"');
/*!40000 ALTER TABLE `t_attr_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_balance`
--

DROP TABLE IF EXISTS `t_balance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_balance`
--

LOCK TABLES `t_balance` WRITE;
/*!40000 ALTER TABLE `t_balance` DISABLE KEYS */;
INSERT INTO `t_balance` VALUES (3,300);
/*!40000 ALTER TABLE `t_balance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_buyer`
--

DROP TABLE IF EXISTS `t_buyer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_buyer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_eu17e65vuobt6dy28pymvf23r` (`email`),
  UNIQUE KEY `UK_gltencs0j00qidn0sgu99ed25` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_buyer`
--

LOCK TABLES `t_buyer` WRITE;
/*!40000 ALTER TABLE `t_buyer` DISABLE KEYS */;
INSERT INTO `t_buyer` VALUES (1,NULL,'2018-12-21 03:40:16','18700678660@163.com','123456',NULL,NULL,0,'2018-12-12 15:18:32','游猪猪'),(2,'Road XiFenglu','2018-12-24 05:28:14',NULL,'cw','18292039753','HelloWorld',0,'2018-12-12 16:26:49','cw'),(3,'123','2018-12-21 03:40:14',NULL,'123','123','lgd',0,'2018-12-13 02:07:47','lgd'),(4,'America','2018-12-26 03:34:01','331242812@qq.com','123456','1383838383','Billy Hilington',0,'2018-12-19 09:40:30','wxj'),(5,'1111','2018-12-26 09:34:38',NULL,'123','111','111',0,'2018-12-26 09:34:38','1234'),(6,'XiDian','2019-01-04 13:15:12',NULL,'w1w2w3w4','15723317403','ChaoYang，You',0,'2019-01-04 13:15:12','acery');
/*!40000 ALTER TABLE `t_buyer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_category`
--

DROP TABLE IF EXISTS `t_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_category`
--

LOCK TABLES `t_category` WRITE;
/*!40000 ALTER TABLE `t_category` DISABLE KEYS */;
INSERT INTO `t_category` VALUES (1,'TV',0),(2,'Home Theater',0),(3,'Cell Phones',0),(4,'Cameras',0),(5,'Computers',0),(6,'Tablets',0),(7,'Audio',0),(8,'Car Eletronics',0),(9,'Video',0),(10,'Games',0),(11,'Home',0),(12,'Office',0);
/*!40000 ALTER TABLE `t_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `order_master_id` int(11) DEFAULT NULL,
  `order_detail_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
INSERT INTO `t_comment` VALUES (2,2,21,38,38,'Shit','2019-01-04 02:41:57','2019-01-04 02:41:57'),(3,2,25,40,40,'Test','2019-01-04 02:50:11','2019-01-04 02:50:11'),(4,2,25,39,39,'Shit','2019-01-04 03:08:29','2019-01-04 03:08:29'),(5,2,59,55,55,'This is a fucking good stuff','2019-01-04 13:06:06','2019-01-04 13:06:06'),(6,6,25,75,77,'I’ve never see such a wonderful cellphone！it\'s great!','2019-01-04 13:24:23','2019-01-04 13:24:23'),(7,6,86,78,80,'Awesome Game this year!','2019-01-04 14:04:21','2019-01-04 14:04:21');
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_exchange_rate`
--

DROP TABLE IF EXISTS `t_exchange_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_exchange_rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exchange_rate` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_exchange_rate`
--

LOCK TABLES `t_exchange_rate` WRITE;
/*!40000 ALTER TABLE `t_exchange_rate` DISABLE KEYS */;
INSERT INTO `t_exchange_rate` VALUES (1,'2%');
/*!40000 ALTER TABLE `t_exchange_rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order_detail`
--

DROP TABLE IF EXISTS `t_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) DEFAULT NULL,
  `master_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `specs_id` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order_detail`
--

LOCK TABLES `t_order_detail` WRITE;
/*!40000 ALTER TABLE `t_order_detail` DISABLE KEYS */;
INSERT INTO `t_order_detail` VALUES (1,'iPhone 8',1,25,64,8000,3,'2018-12-20 13:57:25','2018-12-20 13:57:25'),(33,'iPod Touch',33,21,51,2300,1,'2018-12-23 23:58:18','2018-12-23 23:58:18'),(34,'iPad',34,20,50,7000,1,'2018-12-24 13:16:23','2018-12-24 13:16:23'),(35,'MacBook Pro',35,23,57,13000,1,'2018-12-24 13:22:23','2018-12-24 13:22:23'),(36,'MacBook Pro',36,23,57,13000,1,'2018-12-24 16:22:54','2018-12-24 16:22:54'),(37,'MacBook Pro',37,23,57,13000,1,'2018-12-26 08:45:54','2018-12-26 08:45:54'),(38,'iPod Touch',38,21,51,2300,1,'2018-12-26 08:49:48','2018-12-26 08:49:48'),(39,'iPhone 8',39,25,64,8000,1,'2018-12-26 16:00:08','2018-12-26 16:00:08'),(40,'iPhone 8',40,25,64,8000,1,'2018-12-26 16:05:27','2018-12-26 16:05:27'),(41,'iPod Touch',41,21,51,2300,1,'2018-12-26 16:06:04','2018-12-26 16:06:04'),(42,'iPod Touch',42,21,51,2300,1,'2018-12-26 16:11:21','2018-12-26 16:11:21'),(43,'iPod Touch',43,21,51,2300,1,'2018-12-26 16:12:20','2018-12-26 16:12:20'),(44,'iPhone 8',44,25,64,8000,1,'2018-12-26 17:34:06','2018-12-26 17:34:06'),(45,'iPod Touch',45,21,51,2300,1,'2018-12-26 17:37:29','2018-12-26 17:37:29'),(46,'MacBook Pro',46,23,57,13000,1,'2018-12-26 17:37:41','2018-12-26 17:37:41'),(47,'iPhone 8',47,25,64,8000,1,'2018-12-26 17:38:11','2018-12-26 17:38:11'),(48,'MacBook Pro',48,23,57,13000,1,'2018-12-26 17:50:39','2018-12-26 17:50:39'),(49,'MacBook Pro',49,23,57,13000,1,'2018-12-26 17:50:51','2018-12-26 17:50:51'),(50,'MacBook Pro',50,23,57,13000,1,'2018-12-26 17:51:23','2018-12-26 17:51:23'),(51,'MacBook Pro',51,23,57,13000,1,'2018-12-26 17:51:56','2018-12-26 17:51:56'),(52,'iPod Touch',52,21,51,2300,1,'2018-12-26 17:52:14','2018-12-26 17:52:14'),(53,'Razer Blade',53,65,153,21000,1,'2018-12-26 22:30:28','2018-12-26 22:30:28'),(54,'HUAWEI',54,70,176,2700,1,'2018-12-27 00:08:54','2018-12-27 00:08:54'),(55,'iPhone X',55,59,100,10000,1,'2018-12-27 00:14:30','2018-12-27 00:14:30'),(56,'iPhone X',56,59,100,10000,1,'2018-12-27 08:32:38','2018-12-27 08:32:38'),(57,'Acer',57,79,194,8000,1,'2018-12-27 08:36:08','2018-12-27 08:36:08'),(58,'Amazon Tablet',58,80,195,4000,1,'2018-12-27 08:38:33','2018-12-27 08:38:33'),(59,'Acer',59,79,194,8000,1,'2018-12-27 08:39:33','2018-12-27 08:39:33'),(60,'Wheel Stand Pro',60,74,189,20000,1,'2018-12-27 09:34:25','2018-12-27 09:34:25'),(61,'Acer',61,79,194,8000,1,'2018-12-27 10:04:03','2018-12-27 10:04:03'),(62,'Shenzhou',62,78,193,8000,1,'2018-12-27 20:47:17','2018-12-27 20:47:17'),(63,'iPad Pro',63,58,88,9000,1,'2018-12-28 15:40:53','2018-12-28 15:40:53'),(66,'iPad',66,20,50,7000,1,'2019-01-03 21:06:29','2019-01-03 21:06:29'),(67,'PS4',67,14,25,2600,2,'2019-01-03 21:06:31','2019-01-03 21:06:31'),(68,'Amazon Tablet',68,80,195,4000,1,'2019-01-04 09:52:38','2019-01-04 09:52:38'),(69,'Acer',68,79,194,8000,1,'2019-01-04 09:52:38','2019-01-04 09:52:38'),(70,'Shenzhou',68,78,193,8000,1,'2019-01-04 09:52:38','2019-01-04 09:52:38'),(71,'iPad Pro',69,58,88,9000,1,'2019-01-04 09:54:39','2019-01-04 09:54:39'),(72,'Honor',70,69,168,4000,1,'2019-01-04 09:54:40','2019-01-04 09:54:40'),(73,'MacBook Pro',71,57,78,13000,1,'2019-01-04 09:58:32','2019-01-04 09:58:32'),(74,'Sony micro single camera',72,73,188,3500,1,'2019-01-04 09:58:32','2019-01-04 09:58:32'),(75,'HUAWEI',73,70,176,2700,1,'2019-01-04 09:58:32','2019-01-04 09:58:32'),(76,'HUAWEI',74,70,176,2700,1,'2019-01-04 14:47:11','2019-01-04 14:47:11'),(77,'iPhone 8',75,25,64,8000,1,'2019-01-04 21:19:40','2019-01-04 21:19:40'),(78,'iPad',76,20,50,7000,1,'2019-01-04 21:20:20','2019-01-04 21:20:20'),(79,'PS4',77,61,130,4000,1,'2019-01-04 21:59:07','2019-01-04 21:59:07'),(80,'Call Of Duty: Modern Warfare',78,86,199,349,1,'2019-01-04 21:59:07','2019-01-04 21:59:07'),(81,'Acer',79,79,194,8000,1,'2019-01-06 13:09:53','2019-01-06 13:09:53'),(82,'Nintendo Switch',79,83,197,2300,2,'2019-01-06 13:09:53','2019-01-06 13:09:53'),(83,'HUAWEI',79,70,181,4000,1,'2019-01-06 13:09:53','2019-01-06 13:09:53'),(84,'HUAWEI',80,70,176,2700,1,'2019-01-06 13:10:23','2019-01-06 13:10:23'),(85,'iPhone X',81,59,100,10000,1,'2019-01-06 18:48:34','2019-01-06 18:48:34'),(86,'Shenzhou',82,78,193,8000,1,'2019-01-06 18:48:34','2019-01-06 18:48:34'),(87,'Nikon Camera',82,68,164,3500,1,'2019-01-06 18:48:34','2019-01-06 18:48:34');
/*!40000 ALTER TABLE `t_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order_master`
--

DROP TABLE IF EXISTS `t_order_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `pay_status` int(11) DEFAULT NULL,
  `receive_time` datetime DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order_master`
--

LOCK TABLES `t_order_master` WRITE;
/*!40000 ALTER TABLE `t_order_master` DISABLE KEYS */;
INSERT INTO `t_order_master` VALUES (1,4,1,24000,1,'2018-12-27 23:00:18','wxj','America',0,'2018-12-20 13:57:25','2018-12-20 13:57:25'),(33,2,1,2300,0,NULL,'HelloWorld','Pornhub',-1,'2018-12-23 23:58:18','2018-12-26 10:42:53'),(34,2,1,7000,0,NULL,'HelloWorld','Pornhub',-1,'2018-12-24 13:16:23','2018-12-26 10:42:54'),(35,3,1,13000,0,NULL,'lgd','123',-1,'2018-12-24 13:22:23','2018-12-26 17:36:17'),(36,3,1,13000,0,NULL,'lgd','123',-1,'2018-12-24 16:22:54','2018-12-26 17:36:30'),(37,2,1,13000,0,NULL,'HelloWorld','Pornhub',-1,'2018-12-26 08:45:54','2018-12-26 10:42:55'),(38,2,1,2300,1,'2018-12-27 23:00:21','HelloWorld','Pornhub',3,'2018-12-26 08:49:48','2018-12-26 16:39:45'),(39,2,1,8000,1,'2018-12-27 23:00:23','HelloWorld','Road XiFenglu',3,'2018-12-26 16:00:08','2018-12-26 16:35:24'),(40,2,1,8000,1,'2018-12-27 23:00:22','HelloWorld','Road XiFenglu',3,'2018-12-26 16:05:27','2019-01-04 09:07:24'),(41,2,1,2300,1,'2018-12-27 23:00:24','HelloWorld','Road XiFenglu',0,'2018-12-26 16:06:04','2018-12-26 16:06:10'),(42,2,1,2300,1,'2018-12-27 23:00:27','HelloWorld','Road XiFenglu',0,'2018-12-26 16:11:21','2018-12-26 16:11:28'),(43,2,1,2300,1,'2018-12-27 23:00:26','HelloWorld','Road XiFenglu',2,'2018-12-26 16:12:20','2019-01-04 21:04:59'),(44,3,1,8000,1,'2018-12-27 23:00:28','lgd','123',3,'2018-12-26 17:34:06','2018-12-26 17:40:26'),(45,5,1,2300,0,NULL,'111','1111',0,'2018-12-26 17:37:29','2018-12-26 17:37:29'),(46,2,1,13000,0,NULL,'HelloWorld','Road XiFenglu',-1,'2018-12-26 17:37:41','2018-12-26 17:54:27'),(47,3,1,8000,0,NULL,'lgd','123',-1,'2018-12-26 17:38:11','2018-12-26 22:30:45'),(48,2,1,13000,0,NULL,'HelloWorld','Road XiFenglu',-1,'2018-12-26 17:50:39','2018-12-26 17:54:28'),(49,2,1,13000,0,NULL,'HelloWorld','Road XiFenglu',-1,'2018-12-26 17:50:51','2018-12-26 17:54:29'),(50,2,1,13000,0,NULL,'HelloWorld','Road XiFenglu',-1,'2018-12-26 17:51:23','2018-12-26 17:54:29'),(51,2,1,13000,1,'2018-12-27 23:00:30','HelloWorld','Road XiFenglu',0,'2018-12-26 17:51:56','2018-12-26 17:52:02'),(52,2,1,2300,0,NULL,'HelloWorld','Road XiFenglu',-1,'2018-12-26 17:52:14','2018-12-26 17:54:30'),(53,3,7,21000,1,'2018-12-27 23:00:31','lgd','123',3,'2018-12-26 22:30:27','2018-12-26 22:33:22'),(54,2,4,2700,1,'2018-12-27 23:00:34','HelloWorld','Road XiFenglu',0,'2018-12-27 00:08:54','2018-12-27 00:09:06'),(55,2,1,10000,1,'2018-12-27 23:00:35','HelloWorld','Road XiFenglu',4,'2018-12-27 00:14:30','2019-01-04 21:05:48'),(56,3,1,10000,0,NULL,'lgd','123',-1,'2018-12-27 08:32:38','2018-12-27 10:05:47'),(57,2,4,8000,1,'2018-12-27 23:00:36','HelloWorld','Road XiFenglu',0,'2018-12-27 08:36:08','2018-12-27 08:37:59'),(58,2,4,4000,1,'2018-12-27 23:00:37','HelloWorld','Road XiFenglu',0,'2018-12-27 08:38:33','2018-12-27 09:34:44'),(59,3,4,8000,0,NULL,'lgd','123',-1,'2018-12-27 08:39:33','2018-12-27 08:40:17'),(60,2,2,20000,1,'2018-12-27 23:00:39','HelloWorld','Road XiFenglu',0,'2018-12-27 09:34:25','2018-12-27 09:34:28'),(61,3,4,8000,1,'2018-12-27 23:00:39','lgd','123',0,'2018-12-27 10:04:03','2018-12-27 10:04:41'),(62,2,4,8000,1,'2018-12-27 23:00:41','HelloWorld','Road XiFenglu',0,'2018-12-27 20:47:17','2018-12-27 20:47:27'),(63,3,1,9000,0,NULL,'lgd','123',-1,'2018-12-28 15:40:53','2019-01-06 13:10:08'),(66,4,1,7000,0,NULL,'Billy Hilington','America',0,'2019-01-03 21:06:29','2019-01-03 21:06:29'),(67,4,2,5200,0,NULL,'Billy Hilington','America',0,'2019-01-03 21:06:32','2019-01-03 21:06:32'),(68,2,4,20000,0,NULL,'HelloWorld','Road XiFenglu',-1,'2019-01-04 09:52:38','2019-01-04 09:55:17'),(69,2,1,9000,0,NULL,'HelloWorld','Road XiFenglu',-1,'2019-01-04 09:54:40','2019-01-04 09:55:18'),(70,2,4,4000,0,NULL,'HelloWorld','Road XiFenglu',-1,'2019-01-04 09:54:40','2019-01-04 09:55:19'),(71,2,1,13000,1,NULL,'HelloWorld','Road XiFenglu',0,'2019-01-04 09:58:32','2019-01-04 17:07:26'),(72,2,2,3500,1,NULL,'HelloWorld','Road XiFenglu',0,'2019-01-04 09:58:32','2019-01-04 17:11:41'),(73,2,4,2700,1,NULL,'HelloWorld','Road XiFenglu',0,'2019-01-04 09:58:32','2019-01-04 21:03:17'),(74,2,4,2700,1,NULL,'HelloWorld','Road XiFenglu',0,'2019-01-04 14:47:11','2019-01-04 17:01:28'),(75,6,1,8000,1,NULL,'ChaoYang，You','XiDian',4,'2019-01-04 21:19:40','2019-01-04 21:23:25'),(76,6,1,7000,1,NULL,'ChaoYang，You','XiDian',2,'2019-01-04 21:20:20','2019-01-05 10:48:10'),(77,6,2,4000,0,NULL,'ChaoYang，You','XiDian',0,'2019-01-04 21:59:07','2019-01-04 21:59:07'),(78,6,13,349,1,NULL,'ChaoYang，You','XiDian',4,'2019-01-04 21:59:07','2019-01-04 22:04:08'),(79,3,4,16600,0,NULL,'lgd','123',-1,'2019-01-06 13:09:53','2019-01-06 13:10:10'),(80,3,4,2700,0,NULL,'lgd','123',0,'2019-01-06 13:10:23','2019-01-06 13:10:23'),(81,3,1,10000,0,NULL,'lgd','123',0,'2019-01-06 18:48:34','2019-01-06 18:48:34'),(82,3,4,11500,0,NULL,'lgd','123',0,'2019-01-06 18:48:34','2019-01-06 18:48:34');
/*!40000 ALTER TABLE `t_order_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product`
--

DROP TABLE IF EXISTS `t_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attribute_list` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `ad_money` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product`
--

LOCK TABLES `t_product` WRITE;
/*!40000 ALTER TABLE `t_product` DISABLE KEYS */;
INSERT INTO `t_product` VALUES (1,'{\"Color\":[\"black\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 00:00:08','Razer Phone 2','Razer Phone','http://119.23.75.180/2018/12/13/b8ce7c70-5058-416f-a042-57c4b6b750a6.png',7,1,'2018-12-13 00:00:08',3000),(3,'{\"Color\":[\"black\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 00:05:09','Razer Phone 2','Razer Phone ','http://119.23.75.180/2018/12/13/d90cf796-feb8-438a-8410-70ef968c3965.png',7,1,'2018-12-13 00:05:09',NULL),(4,'{\"Color\":[\"black\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 00:07:24','RazerPhone2','RazerPhone','http://119.23.75.180/2018/12/13/d47f6efd-6110-4691-9256-8a57f4eed298.png',7,0,'2018-12-13 00:07:24',NULL),(5,'{\"Color\":[\"black\",\"red\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 00:09:25','iPhone X','iPhone X','http://119.23.75.180/2018/12/13/dba30766-ba58-4064-a6f4-47ac5c6db945.png',1,1,'2018-12-13 00:09:25',NULL),(6,'{\"Color\":[\"red\",\"blue\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 00:14:22','iPod nano','iPod nano','http://119.23.75.180/2018/12/13/b7d74d1e-8f92-405f-9013-0166f713e0e7.png',1,1,'2018-12-13 00:14:22',NULL),(8,'{\"Color\":[\"black\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 00:19:10','Sony Phone','Sony Phone','http://119.23.75.180/2018/12/13/1ed98038-b3f9-4eda-aff6-e78fc55f0e92.png',2,0,'2018-12-13 00:19:10',NULL),(9,'{\"Color\":[\"red\",\"blue\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 00:37:42','iPod nano','iPod nano','http://119.23.75.180/2018/12/13/d6a84232-18b9-4c99-9586-f91173ff6551.png',1,1,'2018-12-13 00:37:42',NULL),(10,'{\"Color\":[\"black\"],\"Memory\":[\"1TB\",\"512G\"],\"Payment\":[\"Wechat\"]}',3,'2018-12-13 01:08:32','Razer Blade','Razer Blade','http://119.23.75.180/2018/12/13/ac092f14-1111-4359-9e8f-cc0ee8eab304.png',7,1,'2018-12-13 01:08:32',NULL),(11,'{\"Color\":[\"black\"],\"Memory\":[\"512G\",\"1TB\"],\"Payment\":[\"Wechat\"]}',3,'2018-12-13 01:09:21','PS4','PS4','http://119.23.75.180/2018/12/13/bc2509fb-9145-41f9-a04a-b902d4f44629.png',2,1,'2018-12-13 01:09:21',NULL),(12,'{\"Color\":[\"black\"],\"Memory\":[\"1pic\",\"2pic\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 01:13:45','Canon Camera','Canon Camera','http://119.23.75.180/2018/12/13/827e167e-4aef-4e4c-87de-8370cb725f3d.png',4,1,'2018-12-13 01:13:45',NULL),(13,'{\"Color\":[\"black\",\"white\"],\"Memory\":[\"1pic\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 01:15:23','Home Theater','Home Theater','http://119.23.75.180/2018/12/13/055b574f-adfd-469c-aaf2-cfe72d5208a1.png',4,1,'2018-12-13 01:15:23',NULL),(14,'{\"Color\":[\"black\"],\"Memory\":[\"1TB\",\"512G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 01:18:25','PS4','PS4','http://119.23.75.180/2018/12/13/c74a2aae-de21-4639-8cd2-4ee807b593cc.png',2,0,'2018-12-13 01:18:25',NULL),(15,'{\"Color\":[\"black\"],\"Memory\":[\"1TB\",\"512G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 01:20:23','Razer Blade','Razer Blade','http://119.23.75.180/2018/12/13/b8d7a3f2-94ac-4f02-a4bb-6eb73f33fa5c.png',7,1,'2018-12-13 01:20:23',NULL),(16,'{\"Color\":[\"black\"],\"Memory\":[\"104key\",\"89key\"],\"Payment\":[\"Wechat\",\" Alipay\"]}',3,'2018-12-13 01:21:57','Razer Keyboard','Razer Keyboard','http://119.23.75.180/2018/12/13/a02b81b0-4316-4c86-a9bb-af8aca2720b2.png',7,1,'2018-12-13 01:21:57',NULL),(17,'{\"Color\":[\"black\"],\"Memory\":[\"1pic\",\"2pic\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 01:24:02','Nikon Camera','Nikon Camera','http://119.23.75.180/2018/12/13/758a7583-d991-4023-a942-680f85037513.png',4,1,'2018-12-13 01:24:02',NULL),(18,'{\"Color\":[\"black\"],\"Memory\":[\"32inch\",\"48inch\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 01:26:03','Xiaomi TV','Xiaomi TV','http://119.23.75.180/2018/12/13/8150f6b5-0f3e-4e73-b304-c5999b181183.png',4,1,'2018-12-13 01:26:03',NULL),(19,'{\"Color\":[\"blue\",\"black\"],\"Memory\":[\"1pic\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 01:27:22','UP','UP','http://119.23.75.180/2018/12/13/fdc98e0a-3c6b-4882-866c-9dcda3603b5b.png',4,1,'2018-12-13 01:27:22',NULL),(20,'{\"Color\":[\"gold\",\"black\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 01:29:22','iPad','iPad','http://119.23.75.180/2018/12/13/11c35cd7-411d-4e18-85e1-1a9f3b3c6576.png',1,0,'2018-12-13 01:29:22',NULL),(21,'{\"Color\":[\"gold\",\"blue\",\"red\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 01:30:41','iPod Touch','iPod Touch','http://119.23.75.180/2018/12/13/1793d936-ba88-40dd-b6f4-4117ef07dbe9.png',1,1,'2018-12-13 01:30:41',NULL),(22,'{\"Color\":[\"silver\"],\"Memory\":[\"128G\",\"512G\",\"1TB\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 01:32:12','MacBook Pro','MacBook Pro','http://119.23.75.180/2018/12/13/c107f1a7-128b-4f41-8afc-388ab9c1abce.png',1,1,'2018-12-13 01:32:12',NULL),(23,'{\"Color\":[\"silver\"],\"Memory\":[\"128G\",\"512G\",\"1TB\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 01:33:32','MacBook Pro','MacBook Pro','http://119.23.75.180/2018/12/13/9ac4fffa-e7fc-4a9b-b702-8c9627be3938.png',1,1,'2018-12-13 01:33:32',NULL),(24,'{\"Color\":[\"red\",\"black\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-13 10:10:06','iPhone 8s','iPhone 8s','http://119.23.75.180/2018/12/13/cbb0005a-8bd2-4481-994a-a4caa0738cc6.png',1,1,'2018-12-13 10:10:06',NULL),(25,'{\"Color\":[\"red\",\"gold\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"WeChat\",\"Alipay\"]}',3,'2018-12-18 22:22:01','iPhone 8','iPhone 8','http://119.23.75.180/2018/12/18/40b57cea-7f3d-4fbe-a414-6d4e85cc5f6b.png',1,0,'2018-12-18 22:22:01',4000),(26,'{\"Color\":[\"red\",\"blue\"],\"Memory\":[\"64G\",\"128G\"]}',3,'2018-12-20 20:57:33','this is iphonex','iphonex','http://119.23.75.180/2018/12/20/0649a5f3-5d46-49a4-9bf2-6e21fddb012c.jpg',4,1,'2018-12-20 20:57:33',NULL),(27,'{\"Color\":[\"red\",\"blue\"],\"Memory\":[\"64G\",\"128G\"]}',3,'2018-12-20 21:15:59','iphonex','iphonex','http://119.23.75.180/2018/12/20/f523e015-0abf-41c2-95ef-00a2cf45baaa.jpg',4,1,'2018-12-20 21:15:59',NULL),(28,'{\"Color\":[\"red\",\"blue\"],\"Memory\":[\"64G\",\"128G\"]}',3,'2018-12-20 21:36:35','iphonex','iphonex','http://119.23.75.180/2018/12/20/59f4c4ab-241a-497e-a0eb-c3a4c2846a8b.jpg',4,1,'2018-12-20 21:36:35',NULL),(30,'{\"Color\":[\"red\",\"blue\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-22 13:18:38','oppo','oppo','http://119.23.75.180/2018/12/22/dda06147-eda2-4d04-8172-a74dba08efb1.jpg',4,1,'2018-12-22 13:18:38',NULL),(34,'{\"Size\":[\"40inch\"],\"Factory\":[\"Sony\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',1,'2018-12-22 15:51:48','This is SAO NI','Sony','http://119.23.75.180/2018/12/22/2eee61f9-1c31-479c-b786-8904f93f3d2b.png',4,1,'2018-12-22 15:51:48',NULL),(44,'{\"Color\":[\"red\",\"blue\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-24 13:25:51','iphone','iphone','http://119.23.75.180/2018/12/24/8d13c25e-7193-4ba3-b3f9-740a0adf05c1.jpg',7,1,'2018-12-24 13:25:51',NULL),(48,'{\"Color\":[\"red\",\"blue\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-24 14:52:48','1','1','http://119.23.75.180/2018/12/24/14214b27-b3bb-48d5-a61e-faaee8993f09.jpg',4,1,'2018-12-24 14:52:48',NULL),(50,'{\"Color\":[\"red\",\"green\"],\"Memory\":[\"64G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-26 17:55:33','cw','cw','http://119.23.75.180/2018/12/26/50f25486-f0eb-49a2-b06e-371e6467383b.47',1,1,'2018-12-26 17:55:33',NULL),(51,'{\"Color\":[\"red\",\"greem\"],\"Memory\":[\"64\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-26 17:56:50','cw','cw','http://119.23.75.180/2018/12/26/d4fa4ef1-0916-451f-8ff7-0f633613f51b.47',1,1,'2018-12-26 17:56:50',NULL),(57,'{\"Memory\":[\"128G\",\"512G\",\"1TB\"],\"Color\":[\"silver\"],\"Size\":[\"13inch\",\"15inch\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',5,'2018-12-26 22:01:52','MacBook Pro 2018','MacBook Pro','http://119.23.75.180/2018/12/26/65600e36-49e9-4b18-b52d-a0fd2eed1d25.png',1,0,'2018-12-26 22:01:52',NULL),(58,'{\"Memory\":[\"64G\",\"128G\"],\"Color\":[\"silver\",\"red\",\"gold\"],\"Size\":[\"13inch\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',6,'2018-12-26 22:04:04','iPad Pro 2018','iPad Pro','http://119.23.75.180/2018/12/26/6c2c5542-52aa-40cf-838a-cae7dff64ec8.png',1,0,'2018-12-26 22:04:04',2000),(59,'{\"Color\":[\"silver\",\"gold\",\"black\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-26 22:06:23','iPhone X','iPhone X','http://119.23.75.180/2018/12/26/038cf220-832d-42f2-bbc4-2633bc78123b.png',1,0,'2018-12-26 22:06:23',NULL),(60,'{\"Color\":[\"red\",\"gold\",\"silver\"],\"Quality\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',7,'2018-12-26 22:08:36','iPod nano','iPod nano','http://119.23.75.180/2018/12/26/9a030c8d-dede-4a69-a6ab-da3573909efe.png',1,0,'2018-12-26 22:08:36',3000),(61,'{\"Quality\":[\"pro\",\"silm\"],\"Memory\":[\"512G\",\"1T\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',10,'2018-12-26 22:12:16','PS4','PS4','http://119.23.75.180/2018/12/26/3d9ee736-8ade-4183-b0f9-599c24d7c656.png',2,0,'2018-12-26 22:12:16',NULL),(62,'{\"Color\":[\"red\",\"blue\",\"black\"],\"Quality\":[\"HiFi\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',7,'2018-12-26 22:14:25','Sony Sound','Sony Sound','http://119.23.75.180/2018/12/26/eb5a0e79-f4c8-4903-b270-cc712406c689.png',2,0,'2018-12-26 22:14:25',NULL),(63,'{\"Color\":[\"black\"],\"Quality\":[\"HiFi\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',7,'2018-12-26 22:15:44','Sony Headset','Sony Headset','http://119.23.75.180/2018/12/26/25223c33-62f0-49f7-bea6-838808f4a19c.png',2,0,'2018-12-26 22:15:44',NULL),(64,'{\"Color\":[\"black\",\"white\"],\"Pic\":[\"1pic\",\"2pic\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',4,'2018-12-26 22:16:43','Sony Camera','Sony Camera','http://119.23.75.180/2018/12/26/75de2808-644e-4d22-b386-207f2e9ce456.png',2,0,'2018-12-26 22:16:43',NULL),(65,'{\"Memory\":[\"512G\",\"1TB\"],\"Color\":[\"black\"],\"Size\":[\"13inch\",\"15inch\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',5,'2018-12-26 22:18:17','Razer Blade 2018','Razer Blade','http://119.23.75.180/2018/12/26/910e4e37-db2c-41d4-b832-82ea415a5a9d.png',7,0,'2018-12-26 22:18:17',NULL),(66,'{\"Size\":[\"104key\"],\"Color\":[\"black\"],\"Quality\":[\"Green axis\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',12,'2018-12-26 22:20:00','Razer Keyboard','Razer Keyboard','http://119.23.75.180/2018/12/26/e4ba3e44-bb4d-4162-8c25-8572a3da3e41.png',7,0,'2018-12-26 22:20:00',NULL),(67,'{\"Color\":[\"black\"],\"Pic\":[\"1 pic\",\"2 pic\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',4,'2018-12-26 22:21:04','Canon Camera','Canon Camera','http://119.23.75.180/2018/12/26/f33b480c-32e1-42bf-85e0-e39d3e04f2b3.png',4,0,'2018-12-26 22:21:04',NULL),(68,'{\"Color\":[\"black\"],\"Pic\":[\"1 pic\",\"2 pic\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',4,'2018-12-26 22:21:55','Nikon Camera','Nikon Camera','http://119.23.75.180/2018/12/26/4205d330-5e50-4ab9-8b41-1566f01d4688.png',4,0,'2018-12-26 22:21:55',NULL),(69,'{\"Color\":[\"black\",\"green\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-26 22:22:48','Honor 10','Honor','http://119.23.75.180/2018/12/26/37a3030c-9d93-4257-af6f-44dd7b9575a7.png',4,0,'2018-12-26 22:22:48',NULL),(70,'{\"Color\":[\"black\",\"yellow\"],\"Memory\":[\"64G\",\"128G\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',3,'2018-12-26 22:24:01','HUAWEI Nova3','HUAWEI','http://119.23.75.180/2018/12/26/830d158e-1fab-4401-8986-5bb48343183b.png',4,0,'2018-12-26 22:24:01',NULL),(71,'{\"Size\":[\"32mm\",\"48mm\"],\"Power\":[\"500W\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',2,'2018-12-26 22:25:35','Home Theater','Home Theater','http://119.23.75.180/2018/12/26/32aad5de-24d2-4c86-87c5-a8e594cb567d.png',4,0,'2018-12-26 22:25:35',NULL),(72,'{\"Color\":[\"black\",\"white\"],\"Pic\":[\"1pic\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',4,'2018-12-26 22:44:35','Sony micro single camera','Sony micro single camera','http://119.23.75.180/2018/12/26/800fcff6-9fa5-4501-afb8-4e6143e470b9.png',2,1,'2018-12-26 22:44:35',NULL),(73,'{\"Color\":[\"black\",\"white\"],\"Pic\":[\"1pic\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',4,'2018-12-26 22:45:25','Sony micro single camera','Sony micro single camera','http://119.23.75.180/2018/12/26/bc8c6b56-ccb0-43c4-b4c7-76f533e8b625.png',2,0,'2018-12-26 22:45:25',NULL),(74,'{\"Quality\":[\"pro\"],\"Memory\":[\"white\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',10,'2018-12-26 22:46:16','Wheel Stand Pro','Wheel Stand Pro','http://119.23.75.180/2018/12/26/f362c3c1-f4fa-4abe-aa9b-ea731b01bfd7.png',2,0,'2018-12-26 22:46:16',NULL),(75,'{\"Size\":[\"42 inch\",\"48 inch\"],\"Factory\":[\"4K\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',1,'2018-12-26 22:47:09','Sony TV','Sony TV','http://119.23.75.180/2018/12/26/370eec93-dfb1-4217-a054-1daf27770521.png',2,0,'2018-12-26 22:47:09',NULL),(76,'{\"Size\":[\"38 inch\",\"42 inch\"],\"Factory\":[\"4K\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',1,'2018-12-26 22:48:15','TCL TV','TCL TV','http://119.23.75.180/2018/12/26/9595b0c4-e9fa-46a3-ac42-fa7bb3d976d1.png',4,0,'2018-12-26 22:48:15',NULL),(77,'{\"Size\":[\"32 inch\",\"48 inch\"],\"Factory\":[\"4K\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',1,'2018-12-26 22:49:15','Toshiba TV','Toshiba TV','http://119.23.75.180/2018/12/26/84dcc5fb-4dc1-4b66-a235-1006b8ff7035.png',4,0,'2018-12-26 22:49:15',NULL),(78,'{\"Memory\":[\"1TB\"],\"Color\":[\"black\"],\"Size\":[\"15 inch\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',5,'2018-12-26 22:50:16','Shenzhou computer','Shenzhou','http://119.23.75.180/2018/12/26/aa823f50-3d12-475d-b9a7-9eeec4556c2a.png',4,0,'2018-12-26 22:50:16',NULL),(79,'{\"Memory\":[\"1TB\"],\"Color\":[\"black\"],\"Size\":[\"15 inch\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',5,'2018-12-26 22:50:57','Acer Computer','Acer','http://119.23.75.180/2018/12/26/8475eb7b-e307-4bb3-bc70-66c05823a602.png',4,0,'2018-12-26 22:50:57',NULL),(80,'{\"Memory\":[\"64G\",\"128G\"],\"Color\":[\"black\"],\"Size\":[\"9 inch\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',6,'2018-12-26 22:51:58','Amazon Tablet','Amazon Tablet','http://119.23.75.180/2018/12/26/793d3f5b-7930-4c27-a4f8-9de5561cbd51.png',4,0,'2018-12-26 22:51:58',NULL),(81,'{\"Memory\":[\"64G\"],\"Color\":[\"black\"],\"Size\":[\"9 inch\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',6,'2018-12-26 22:52:46','Samsung Tablet','Samsung','http://119.23.75.180/2018/12/26/1c62e37e-b222-471c-bb0f-f33bf34ed8bb.png',4,0,'2018-12-26 22:52:46',NULL),(82,'{\"Memory\":[\"64G\"],\"Color\":[\"black\"],\"Size\":[\"9 inch\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',6,'2018-12-26 22:53:24','Lenovo Tablet','Lenovo','http://119.23.75.180/2018/12/26/843e2b41-36fa-48a1-aab0-bccda053d76d.png',4,0,'2018-12-26 22:53:24',NULL),(83,'{\"Quality\":[\"NS\"],\"Memory\":[\"1TB\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',10,'2018-12-26 22:54:16','Nintendo Switch','Nintendo Switch','http://119.23.75.180/2018/12/26/04e9f662-db41-4fe7-a0e3-1b9acc8261a0.png',4,0,'2018-12-26 22:54:16',NULL),(84,'{\"Quality\":[\"Xbox one\"],\"Memory\":[\"1TB\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',10,'2018-12-26 22:55:01','Xbox','Xbox','http://119.23.75.180/2018/12/26/5973664c-0ae9-43e0-b7a6-bb3e3fdbb49a.png',4,0,'2018-12-26 22:55:01',NULL),(85,'{\"Size\":[\"38 inch\",\"42 inch\"],\"Factory\":[\"4K\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',1,'2018-12-26 22:56:06','MI TV','MI','http://119.23.75.180/2018/12/26/ff01a325-c112-4fb5-819f-58bbaca6353e.png',4,0,'2018-12-26 22:56:06',NULL),(86,'{\"Quality\":[\"normal\",\"pro\"],\"Memory\":[\"128\",\"256\",\"512\"],\"Payment\":[\"Wechat\",\"Alipay\"]}',10,'2019-01-04 21:51:44','FPS Game, Captain Price,Soilder Soap.','Call Of Duty: Modern Warfare','http://119.23.75.180/2019/01/04/360d9394-7ad6-4abc-a644-9260bfc3ad04.png',13,0,'2019-01-04 21:51:44',1000);
/*!40000 ALTER TABLE `t_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product_collect`
--

DROP TABLE IF EXISTS `t_product_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product_collect`
--

LOCK TABLES `t_product_collect` WRITE;
/*!40000 ALTER TABLE `t_product_collect` DISABLE KEYS */;
INSERT INTO `t_product_collect` VALUES (24,4,'2018-12-22 16:45:13',28,'2018-12-22 16:45:13'),(33,2,'2018-12-24 13:16:21',20,'2018-12-24 13:16:21'),(34,5,'2018-12-26 17:36:09',21,'2018-12-26 17:36:09'),(35,3,'2018-12-26 17:44:32',25,'2018-12-26 17:44:32'),(36,2,'2018-12-26 23:50:27',79,'2018-12-26 23:50:27'),(37,3,'2018-12-28 15:42:08',64,'2018-12-28 15:42:08'),(38,3,'2018-12-28 15:42:15',65,'2018-12-28 15:42:15'),(39,3,'2018-12-28 15:42:26',62,'2018-12-28 15:42:26'),(40,6,'2019-01-04 21:18:24',25,'2019-01-04 21:18:24');
/*!40000 ALTER TABLE `t_product_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product_specs`
--

DROP TABLE IF EXISTS `t_product_specs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product_specs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product_specs`
--

LOCK TABLES `t_product_specs` WRITE;
/*!40000 ALTER TABLE `t_product_specs` DISABLE KEYS */;
INSERT INTO `t_product_specs` VALUES (1,'2018-12-13 00:00:29','{\"Color\":\"black\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',6000,1,22,'2018-12-13 00:00:29'),(2,'2018-12-13 00:00:43','{\"Color\":\"black\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',8000,1,30,'2018-12-13 00:00:43'),(3,'2018-12-13 00:05:29','{\"Color\":\"black\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',6000,3,22,'2018-12-13 00:05:29'),(4,'2018-12-13 00:06:23','{\"Color\":\"black\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',8000,3,30,'2018-12-13 00:06:23'),(5,'2018-12-13 00:09:45','{\"Color\":\"red\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',8000,5,30,'2018-12-13 00:09:45'),(6,'2018-12-13 00:10:00','{\"Color\":\"black\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',8000,5,30,'2018-12-13 00:10:00'),(7,'2018-12-13 00:10:10','{\"Color\":\"black\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',10000,5,22,'2018-12-13 00:10:10'),(8,'2018-12-13 00:10:16','{\"Color\":\"red\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',10000,5,22,'2018-12-13 00:10:16'),(9,'2018-12-13 00:15:02','{\"Color\":\"red\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',1500,6,20,'2018-12-13 00:15:02'),(10,'2018-12-13 00:15:42','{\"Color\":\"blue\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',1500,6,33,'2018-12-13 00:15:42'),(11,'2018-12-13 00:18:15','{}',3000,7,40,'2018-12-13 00:18:15'),(12,'2018-12-13 00:19:33','{\"Color\":\"black\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',5000,8,40,'2018-12-13 00:19:33'),(13,'2018-12-13 00:20:00','{\"Color\":\"black\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',8000,8,40,'2018-12-13 00:20:00'),(14,'2018-12-13 00:38:08','{\"Color\":\"red\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',1500,9,20,'2018-12-13 00:38:08'),(15,'2018-12-13 00:55:56','{\"Color\":\"blue\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',1800,9,30,'2018-12-13 00:55:56'),(16,'2018-12-13 00:58:17','{\"Color\":\"black\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',6000,4,22,'2018-12-13 00:58:17'),(17,'2018-12-13 00:58:29','{\"Color\":\"black\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',8000,4,30,'2018-12-13 00:58:29'),(18,'2018-12-13 01:09:39','{\"Color\":\"black\",\"Memory\":\"1TB\",\"Payment\":\"Wechat\"}',3000,11,49,'2018-12-13 01:09:39'),(19,'2018-12-13 01:10:43','{\"Color\":\"black\",\"Memory\":\"1TB\",\"Payment\":\"Wechat\"}',20000,10,67,'2018-12-13 01:10:43'),(20,'2018-12-13 01:14:01','{\"Color\":\"black\",\"Memory\":\"1pic\",\"Payment\":\"Wechat\"}',17000,12,13,'2018-12-13 01:14:01'),(21,'2018-12-13 01:14:15','{\"Color\":\"black\",\"Memory\":\"2pic\",\"Payment\":\"Wechat\"}',23000,12,7,'2018-12-13 01:14:15'),(22,'2018-12-13 01:15:42','{\"Color\":\"black\",\"Memory\":\"1pic\",\"Payment\":\"Alipay\"}',6100,13,20,'2018-12-23 18:06:45'),(23,'2018-12-13 01:18:50','{\"Color\":\"black\",\"Memory\":\"1TB\",\"Payment\":\"Wechat\"}',3000,14,89,'2018-12-13 01:18:50'),(24,'2018-12-13 01:19:00','{\"Color\":\"black\",\"Memory\":\"512GB\",\"Payment\":\"Wechat\"}',2600,14,90,'2018-12-13 01:19:00'),(25,'2018-12-13 01:19:08','{\"Color\":\"black\",\"Memory\":\"512GB\",\"Payment\":\"Alipay\"}',2600,14,86,'2018-12-13 01:19:08'),(26,'2018-12-13 01:19:21','{\"Color\":\"black\",\"Memory\":\"1TB\",\"Payment\":\"Alipay\"}',3000,14,90,'2018-12-13 01:19:21'),(27,'2018-12-13 01:20:40','{\"Color\":\"black\",\"Memory\":\"512G\",\"Payment\":\"Wechat\"}',17000,15,13,'2018-12-13 01:20:40'),(28,'2018-12-13 01:20:44','{\"Color\":\"black\",\"Memory\":\"512G\",\"Payment\":\"Alipay\"}',17000,15,13,'2018-12-13 01:20:44'),(29,'2018-12-13 01:20:53','{\"Color\":\"black\",\"Memory\":\"1TB\",\"Payment\":\"Alipay\"}',20000,15,7,'2018-12-13 01:20:53'),(30,'2018-12-13 01:20:59','{\"Color\":\"black\",\"Memory\":\"1TB\",\"Payment\":\"Wechat\"}',20000,15,7,'2018-12-13 01:20:59'),(31,'2018-12-13 01:22:16','{\"Color\":\"black\",\"Memory\":\"104key\",\"Payment\":\"Wechat\"}',700,16,37,'2018-12-13 01:22:16'),(32,'2018-12-13 01:22:28','{\"Color\":\"black\",\"Memory\":\"89key\",\"Payment\":\"Wechat\"}',600,16,37,'2018-12-13 01:22:28'),(33,'2018-12-13 01:22:34','{\"Color\":\"black\",\"Memory\":\"89key\",\"Payment\":\"Alipay\"}',600,16,37,'2018-12-13 01:22:34'),(34,'2018-12-13 01:22:43','{\"Color\":\"black\",\"Memory\":\"104key\",\"Payment\":\"Alipay\"}',700,16,37,'2018-12-13 01:22:43'),(35,'2018-12-13 01:24:24','{\"Color\":\"black\",\"Memory\":\"1pic\",\"Payment\":\"Alipay\"}',18000,17,18,'2018-12-23 18:10:06'),(36,'2018-12-13 01:24:29','{\"Color\":\"black\",\"Memory\":\"1pic\",\"Payment\":\"Alipay\"}',19000,17,19,'2018-12-13 01:24:29'),(37,'2018-12-13 01:24:42','{\"Color\":\"black\",\"Memory\":\"2pic\",\"Payment\":\"Alipay\"}',25000,17,21,'2018-12-13 01:24:42'),(38,'2018-12-13 01:24:48','{\"Color\":\"black\",\"Memory\":\"2pic\",\"Payment\":\"Wechat\"}',25000,17,21,'2018-12-13 01:24:48'),(39,'2018-12-13 01:26:21','{\"Color\":\"black\",\"Memory\":\"32inch\",\"Payment\":\"Wechat\"}',8000,18,49,'2018-12-13 01:26:21'),(40,'2018-12-13 01:26:23','{\"Color\":\"black\",\"Memory\":\"32inch\",\"Payment\":\"Alipay\"}',8000,18,48,'2018-12-13 01:26:23'),(41,'2018-12-13 01:26:35','{\"Color\":\"black\",\"Memory\":\"48inch\",\"Payment\":\"Alipay\"}',12000,18,29,'2018-12-13 01:26:35'),(42,'2018-12-13 01:26:40','{\"Color\":\"black\",\"Memory\":\"48inch\",\"Payment\":\"Alipay\"}',12000,18,30,'2018-12-22 14:57:37'),(43,'2018-12-13 01:27:43','{\"Color\":\"blue\",\"Memory\":\"1pic\",\"Payment\":\"Wechat\"}',5000,19,78,'2018-12-13 01:27:43'),(44,'2018-12-13 01:27:46','{\"Color\":\"blue\",\"Memory\":\"1pic\",\"Payment\":\"Alipay\"}',5000,19,79,'2018-12-13 01:27:46'),(45,'2018-12-13 01:27:55','{\"Color\":\"black\",\"Memory\":\"1pic\",\"Payment\":\"Alipay\"}',5000,19,60,'2018-12-13 01:27:55'),(46,'2018-12-13 01:28:02','{\"Color\":\"black\",\"Memory\":\"1pic\",\"Payment\":\"Wechat\"}',5000,19,60,'2018-12-13 01:28:02'),(47,'2018-12-13 01:29:42','{\"Color\":\"gold\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',8000,20,104,'2018-12-13 01:29:42'),(48,'2018-12-13 01:29:45','{\"Color\":\"gold\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',8000,20,104,'2018-12-13 01:29:45'),(49,'2018-12-13 01:29:53','{\"Color\":\"gold\",\"Memory\":\"64G\",\"Payment\":\"Alipay\"}',7000,20,104,'2018-12-13 01:29:53'),(50,'2018-12-13 01:29:57','{\"Color\":\"gold\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',7000,20,90,'2018-12-13 01:29:57'),(51,'2018-12-13 01:31:03','{\"Color\":\"gold\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',2300,21,61,'2018-12-13 01:31:03'),(52,'2018-12-13 01:31:07','{\"Color\":\"blue\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',2300,21,77,'2018-12-13 01:31:07'),(53,'2018-12-13 01:31:10','{\"Color\":\"red\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',2300,21,77,'2018-12-13 01:31:10'),(54,'2018-12-13 01:32:36','{\"Color\":\"silver\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',1300,22,44,'2018-12-13 01:32:36'),(55,'2018-12-13 01:32:38','{\"Color\":\"silver\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',1300,22,44,'2018-12-13 01:32:38'),(56,'2018-12-13 01:32:40','{\"Color\":\"silver\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',1300,22,44,'2018-12-13 01:32:40'),(57,'2018-12-13 01:33:49','{\"Color\":\"silver\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',13000,23,30,'2018-12-13 01:33:49'),(58,'2018-12-13 01:33:57','{\"Color\":\"silver\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',13000,23,44,'2018-12-13 01:33:57'),(59,'2018-12-13 01:34:14','{\"Color\":\"silver\",\"Memory\":\"512G\",\"Payment\":\"Alipay\"}',19000,23,39,'2018-12-13 01:34:14'),(60,'2018-12-13 01:34:24','{\"Color\":\"silver\",\"Memory\":\"512G\",\"Payment\":\"Wechat\"}',19000,23,39,'2018-12-13 01:34:24'),(61,'2018-12-13 01:34:37','{\"Color\":\"silver\",\"Memory\":\"1TB\",\"Payment\":\"Wechat\"}',24000,23,27,'2018-12-13 01:34:37'),(62,'2018-12-13 01:34:41','{\"Color\":\"silver\",\"Memory\":\"1TB\",\"Payment\":\"Alipay\"}',24000,23,27,'2018-12-13 01:34:41'),(63,'2018-12-13 10:10:31','{\"Color\":\"red\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',8000,24,30,'2018-12-13 10:10:31'),(64,'2018-12-18 22:22:33','{\"Color\":\"red\",\"Memory\":\"64G\",\"Payment\":\"WeChat\"}',8000,25,21,'2018-12-18 22:22:33'),(65,'2018-12-20 17:41:44','{\"payment\":\"\",\"Color\":\"black\",\"Memory\":\"1pic\",\"Payment\":\"Wechat\"}',5999,13,15,'2018-12-23 18:06:22'),(71,'2018-12-20 20:58:03','{\"payment\":\"Wechat\",\"Color\":\"blue\",\"Memory\":\"128G\"}',1999,26,99,'2018-12-20 22:14:00'),(74,'2018-12-22 13:20:39','{\"Color\":\"red\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',3299,30,20,'2018-12-22 14:55:10'),(75,'2018-12-22 14:30:55','{\"Color\":\"red\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',2999,30,20,'2018-12-22 14:44:07'),(76,'2018-12-22 14:54:58','{\"Color\":\"blue\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',3399,30,15,'2018-12-22 14:54:58'),(77,'2018-12-24 14:53:03','{\"Color\":\"red\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',23,48,2,'2018-12-24 14:53:11'),(78,'2018-12-26 22:02:08','{\"Memory\":\"128G\",\"Color\":\"silver\",\"Size\":\"13inch\",\"Payment\":\"Wechat\"}',13000,57,44,'2018-12-26 22:02:08'),(79,'2018-12-26 22:02:11','{\"Memory\":\"128G\",\"Color\":\"silver\",\"Size\":\"13inch\",\"Payment\":\"Alipay\"}',13000,57,45,'2018-12-26 22:02:11'),(80,'2018-12-26 22:02:23','{\"Memory\":\"512G\",\"Color\":\"silver\",\"Size\":\"13inch\",\"Payment\":\"Wechat\"}',19000,57,70,'2018-12-26 22:02:23'),(81,'2018-12-26 22:02:26','{\"Memory\":\"512G\",\"Color\":\"silver\",\"Size\":\"13inch\",\"Payment\":\"Alipay\"}',19000,57,70,'2018-12-26 22:02:26'),(82,'2018-12-26 22:02:34','{\"Memory\":\"512G\",\"Color\":\"silver\",\"Size\":\"15inch\",\"Payment\":\"Alipay\"}',21000,57,70,'2018-12-26 22:02:34'),(83,'2018-12-26 22:02:36','{\"Memory\":\"512G\",\"Color\":\"silver\",\"Size\":\"15inch\",\"Payment\":\"Wechat\"}',21000,57,70,'2018-12-26 22:02:36'),(84,'2018-12-26 22:02:47','{\"Memory\":\"128G\",\"Color\":\"silver\",\"Size\":\"15inch\",\"Payment\":\"Wechat\"}',15000,57,69,'2018-12-26 22:02:47'),(85,'2018-12-26 22:02:49','{\"Memory\":\"128G\",\"Color\":\"silver\",\"Size\":\"15inch\",\"Payment\":\"Alipay\"}',15000,57,69,'2018-12-26 22:02:49'),(86,'2018-12-26 22:03:01','{\"Memory\":\"1TB\",\"Color\":\"silver\",\"Size\":\"15inch\",\"Payment\":\"Alipay\"}',25000,57,24,'2018-12-26 22:03:01'),(87,'2018-12-26 22:03:04','{\"Memory\":\"1TB\",\"Color\":\"silver\",\"Size\":\"15inch\",\"Payment\":\"Wechat\"}',25000,57,24,'2018-12-26 22:03:04'),(88,'2018-12-26 22:04:37','{\"Memory\":\"64G\",\"Color\":\"silver\",\"Size\":\"13inch\",\"Payment\":\"Wechat\"}',9000,58,76,'2018-12-26 22:04:37'),(89,'2018-12-26 22:04:39','{\"Memory\":\"64G\",\"Color\":\"silver\",\"Size\":\"13inch\",\"Payment\":\"Alipay\"}',9000,58,78,'2018-12-26 22:04:39'),(90,'2018-12-26 22:04:42','{\"Memory\":\"64G\",\"Color\":\"red\",\"Size\":\"13inch\",\"Payment\":\"Alipay\"}',9000,58,78,'2018-12-26 22:04:42'),(91,'2018-12-26 22:04:45','{\"Memory\":\"64G\",\"Color\":\"red\",\"Size\":\"13inch\",\"Payment\":\"Wechat\"}',9000,58,78,'2018-12-26 22:04:45'),(92,'2018-12-26 22:04:49','{\"Memory\":\"64G\",\"Color\":\"gold\",\"Size\":\"13inch\",\"Payment\":\"Wechat\"}',9000,58,46,'2018-12-26 22:04:49'),(93,'2018-12-26 22:04:52','{\"Memory\":\"64G\",\"Color\":\"gold\",\"Size\":\"13inch\",\"Payment\":\"Alipay\"}',9000,58,46,'2018-12-26 22:04:52'),(94,'2018-12-26 22:05:02','{\"Memory\":\"128G\",\"Color\":\"silver\",\"Size\":\"13inch\",\"Payment\":\"Alipay\"}',12000,58,90,'2018-12-26 22:05:02'),(95,'2018-12-26 22:05:03','{\"Memory\":\"128G\",\"Color\":\"silver\",\"Size\":\"13inch\",\"Payment\":\"Wechat\"}',12000,58,90,'2018-12-26 22:05:03'),(96,'2018-12-26 22:05:06','{\"Memory\":\"128G\",\"Color\":\"red\",\"Size\":\"13inch\",\"Payment\":\"Wechat\"}',12000,58,90,'2018-12-26 22:05:06'),(97,'2018-12-26 22:05:09','{\"Memory\":\"128G\",\"Color\":\"red\",\"Size\":\"13inch\",\"Payment\":\"Alipay\"}',12000,58,90,'2018-12-26 22:05:09'),(98,'2018-12-26 22:05:12','{\"Memory\":\"128G\",\"Color\":\"gold\",\"Size\":\"13inch\",\"Payment\":\"Alipay\"}',12000,58,90,'2018-12-26 22:05:12'),(99,'2018-12-26 22:05:17','{\"Memory\":\"128G\",\"Color\":\"gold\",\"Size\":\"13inch\",\"Payment\":\"Wechat\"}',12000,58,90,'2018-12-26 22:05:17'),(100,'2018-12-26 22:06:34','{\"Color\":\"silver\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',10000,59,43,'2018-12-26 22:06:37'),(101,'2018-12-26 22:06:47','{\"Color\":\"gold\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',10000,59,46,'2018-12-26 22:06:47'),(102,'2018-12-26 22:06:50','{\"Color\":\"gold\",\"Memory\":\"64G\",\"Payment\":\"Alipay\"}',10000,59,46,'2018-12-26 22:06:50'),(103,'2018-12-26 22:06:53','{\"Color\":\"silver\",\"Memory\":\"64G\",\"Payment\":\"Alipay\"}',10000,59,46,'2019-01-04 22:31:59'),(104,'2018-12-26 22:06:56','{\"Color\":\"black\",\"Memory\":\"64G\",\"Payment\":\"Alipay\"}',10000,59,46,'2018-12-26 22:06:56'),(105,'2018-12-26 22:06:59','{\"Color\":\"black\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',10000,59,46,'2018-12-26 22:06:59'),(106,'2018-12-26 22:07:10','{\"Color\":\"silver\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',18000,59,46,'2018-12-26 22:07:10'),(107,'2018-12-26 22:07:12','{\"Color\":\"gold\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',18000,59,46,'2018-12-26 22:07:12'),(108,'2018-12-26 22:07:15','{\"Color\":\"black\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',18000,59,46,'2018-12-26 22:07:15'),(109,'2018-12-26 22:07:18','{\"Color\":\"black\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',18000,59,46,'2018-12-26 22:07:18'),(110,'2018-12-26 22:07:19','{\"Color\":\"gold\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',18000,59,46,'2018-12-26 22:07:41'),(111,'2018-12-26 22:07:52','{\"Color\":\"silver\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',18000,59,46,'2018-12-26 22:07:52'),(112,'2018-12-26 22:08:51','{\"Color\":\"red\",\"Quality\":\"64G\",\"Payment\":\"Wechat\"}',3000,60,78,'2018-12-26 22:08:51'),(113,'2018-12-26 22:08:53','{\"Color\":\"red\",\"Quality\":\"64G\",\"Payment\":\"Alipay\"}',3000,60,78,'2018-12-26 22:08:53'),(114,'2018-12-26 22:08:58','{\"Color\":\"gold\",\"Quality\":\"64G\",\"Payment\":\"Alipay\"}',3000,60,78,'2018-12-26 22:08:58'),(115,'2018-12-26 22:08:59','{\"Color\":\"gold\",\"Quality\":\"64G\",\"Payment\":\"Wechat\"}',3000,60,78,'2018-12-26 22:08:59'),(116,'2018-12-26 22:09:02','{\"Color\":\"silver\",\"Quality\":\"64G\",\"Payment\":\"Wechat\"}',3000,60,78,'2018-12-26 22:09:02'),(117,'2018-12-26 22:09:04','{\"Color\":\"silver\",\"Quality\":\"64G\",\"Payment\":\"Alipay\"}',3000,60,78,'2018-12-26 22:09:04'),(118,'2018-12-26 22:09:09','{\"Color\":\"silver\",\"Quality\":\"128G\",\"Payment\":\"Alipay\"}',5000,60,78,'2018-12-26 22:09:09'),(119,'2018-12-26 22:09:11','{\"Color\":\"silver\",\"Quality\":\"128G\",\"Payment\":\"Wechat\"}',5000,60,78,'2018-12-26 22:09:11'),(120,'2018-12-26 22:09:14','{\"Color\":\"gold\",\"Quality\":\"128G\",\"Payment\":\"Wechat\"}',5000,60,78,'2018-12-26 22:09:14'),(121,'2018-12-26 22:09:17','{\"Color\":\"gold\",\"Quality\":\"128G\",\"Payment\":\"Alipay\"}',5000,60,78,'2018-12-26 22:09:17'),(122,'2018-12-26 22:09:19','{\"Color\":\"red\",\"Quality\":\"128G\",\"Payment\":\"Alipay\"}',5000,60,78,'2018-12-26 22:09:19'),(123,'2018-12-26 22:09:21','{\"Color\":\"red\",\"Quality\":\"128G\",\"Payment\":\"Wechat\"}',5000,60,78,'2018-12-26 22:09:21'),(124,'2018-12-26 22:11:19','{\"Color\":\"black\",\"Memory\":\"64G\",\"Payment\":\"Alipay\"}',5000,8,36,'2018-12-26 22:11:19'),(125,'2018-12-26 22:11:29','{\"Color\":\"black\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',8000,8,36,'2018-12-26 22:11:29'),(126,'2018-12-26 22:12:28','{\"Quality\":\"silm\",\"Memory\":\"512G\",\"Payment\":\"Wechat\"}',3000,61,120,'2018-12-26 22:12:28'),(127,'2018-12-26 22:12:30','{\"Quality\":\"silm\",\"Memory\":\"512G\",\"Payment\":\"Alipay\"}',3000,61,120,'2018-12-26 22:12:30'),(128,'2018-12-26 22:12:38','{\"Quality\":\"silm\",\"Memory\":\"1T\",\"Payment\":\"Alipay\"}',5000,61,120,'2018-12-26 22:12:38'),(129,'2018-12-26 22:12:40','{\"Quality\":\"silm\",\"Memory\":\"1T\",\"Payment\":\"Wechat\"}',5000,61,120,'2018-12-26 22:12:40'),(130,'2018-12-26 22:12:50','{\"Quality\":\"pro\",\"Memory\":\"512G\",\"Payment\":\"Wechat\"}',4000,61,98,'2018-12-26 22:12:50'),(131,'2018-12-26 22:12:52','{\"Quality\":\"pro\",\"Memory\":\"512G\",\"Payment\":\"Alipay\"}',4000,61,99,'2018-12-26 22:12:52'),(132,'2018-12-26 22:12:58','{\"Quality\":\"pro\",\"Memory\":\"1T\",\"Payment\":\"Alipay\"}',8000,61,99,'2018-12-26 22:12:58'),(133,'2018-12-26 22:13:00','{\"Quality\":\"pro\",\"Memory\":\"1T\",\"Payment\":\"Wechat\"}',8000,61,99,'2018-12-26 22:13:00'),(134,'2018-12-26 22:14:36','{\"Color\":\"red\",\"Quality\":\"HiFi\",\"Payment\":\"Wechat\"}',4500,62,36,'2018-12-26 22:14:36'),(135,'2018-12-26 22:14:40','{\"Color\":\"red\",\"Quality\":\"HiFi\",\"Payment\":\"Alipay\"}',4500,62,36,'2018-12-26 22:14:40'),(136,'2018-12-26 22:14:42','{\"Color\":\"blue\",\"Quality\":\"HiFi\",\"Payment\":\"Alipay\"}',4500,62,36,'2018-12-26 22:14:42'),(137,'2018-12-26 22:14:44','{\"Color\":\"blue\",\"Quality\":\"HiFi\",\"Payment\":\"Wechat\"}',4500,62,36,'2018-12-26 22:14:44'),(138,'2018-12-26 22:14:46','{\"Color\":\"black\",\"Quality\":\"HiFi\",\"Payment\":\"Wechat\"}',4500,62,36,'2018-12-26 22:14:46'),(139,'2018-12-26 22:14:48','{\"Color\":\"black\",\"Quality\":\"HiFi\",\"Payment\":\"Alipay\"}',4500,62,36,'2018-12-26 22:14:48'),(140,'2018-12-26 22:15:53','{\"Color\":\"black\",\"Quality\":\"HiFi\",\"Payment\":\"Wechat\"}',2000,63,77,'2018-12-26 22:15:53'),(141,'2018-12-26 22:15:55','{\"Color\":\"black\",\"Quality\":\"HiFi\",\"Payment\":\"Alipay\"}',2000,63,77,'2018-12-26 22:15:55'),(142,'2018-12-26 22:16:55','{\"Color\":\"black\",\"Pic\":\"1pic\",\"Payment\":\"Wechat\"}',5000,64,27,'2018-12-26 22:16:55'),(143,'2018-12-26 22:16:58','{\"Color\":\"black\",\"Pic\":\"1pic\",\"Payment\":\"Alipay\"}',5000,64,27,'2018-12-26 22:16:58'),(144,'2018-12-26 22:17:03','{\"Color\":\"white\",\"Pic\":\"1pic\",\"Payment\":\"Alipay\"}',5000,64,34,'2018-12-26 22:17:03'),(145,'2018-12-26 22:17:05','{\"Color\":\"white\",\"Pic\":\"1pic\",\"Payment\":\"Wechat\"}',5000,64,34,'2018-12-26 22:17:05'),(146,'2018-12-26 22:17:13','{\"Color\":\"white\",\"Pic\":\"2pic\",\"Payment\":\"Wechat\"}',8000,64,17,'2018-12-26 22:17:13'),(147,'2018-12-26 22:17:15','{\"Color\":\"white\",\"Pic\":\"2pic\",\"Payment\":\"Alipay\"}',8000,64,17,'2018-12-26 22:17:15'),(148,'2018-12-26 22:17:17','{\"Color\":\"black\",\"Pic\":\"2pic\",\"Payment\":\"Alipay\"}',8000,64,17,'2018-12-26 22:17:17'),(149,'2018-12-26 22:17:19','{\"Color\":\"black\",\"Pic\":\"2pic\",\"Payment\":\"Wechat\"}',8000,64,17,'2018-12-26 22:17:19'),(150,'2018-12-26 22:18:31','{\"Memory\":\"512G\",\"Color\":\"black\",\"Size\":\"13inch\",\"Payment\":\"Wechat\"}',17000,65,14,'2018-12-26 22:18:31'),(151,'2018-12-26 22:18:34','{\"Memory\":\"512G\",\"Color\":\"black\",\"Size\":\"13inch\",\"Payment\":\"Alipay\"}',17000,65,14,'2018-12-26 22:18:34'),(152,'2018-12-26 22:18:42','{\"Memory\":\"1TB\",\"Color\":\"black\",\"Size\":\"13inch\",\"Payment\":\"Alipay\"}',21000,65,22,'2018-12-26 22:18:42'),(153,'2018-12-26 22:18:44','{\"Memory\":\"1TB\",\"Color\":\"black\",\"Size\":\"13inch\",\"Payment\":\"Wechat\"}',21000,65,21,'2018-12-26 22:18:44'),(154,'2018-12-26 22:18:55','{\"Memory\":\"512G\",\"Color\":\"black\",\"Size\":\"15inch\",\"Payment\":\"Wechat\"}',19000,65,28,'2018-12-26 22:18:55'),(155,'2018-12-26 22:18:58','{\"Memory\":\"512G\",\"Color\":\"black\",\"Size\":\"15inch\",\"Payment\":\"Alipay\"}',19000,65,28,'2018-12-26 22:18:58'),(156,'2018-12-26 22:19:04','{\"Memory\":\"1TB\",\"Color\":\"black\",\"Size\":\"15inch\",\"Payment\":\"Alipay\"}',24000,65,28,'2018-12-26 22:19:04'),(157,'2018-12-26 22:19:06','{\"Memory\":\"1TB\",\"Color\":\"black\",\"Size\":\"15inch\",\"Payment\":\"Wechat\"}',24000,65,28,'2018-12-26 22:19:06'),(158,'2018-12-26 22:20:10','{\"Size\":\"104key\",\"Color\":\"black\",\"Quality\":\"Green axis\",\"Payment\":\"Wechat\"}',1700,66,45,'2018-12-26 22:20:10'),(159,'2018-12-26 22:20:12','{\"Size\":\"104key\",\"Color\":\"black\",\"Quality\":\"Green axis\",\"Payment\":\"Alipay\"}',1700,66,45,'2018-12-26 22:20:12'),(160,'2018-12-26 22:21:13','{\"Color\":\"black\",\"Pic\":\"1 pic\",\"Payment\":\"Wechat\"}',4000,67,45,'2018-12-26 22:21:13'),(161,'2018-12-26 22:21:17','{\"Color\":\"black\",\"Pic\":\"1 pic\",\"Payment\":\"Alipay\"}',4000,67,45,'2018-12-26 22:21:17'),(162,'2018-12-26 22:21:25','{\"Color\":\"black\",\"Pic\":\"2 pic\",\"Payment\":\"Alipay\"}',6000,67,37,'2018-12-26 22:21:25'),(163,'2018-12-26 22:21:27','{\"Color\":\"black\",\"Pic\":\"2 pic\",\"Payment\":\"Wechat\"}',6000,67,37,'2018-12-26 22:21:27'),(164,'2018-12-26 22:22:04','{\"Color\":\"black\",\"Pic\":\"1 pic\",\"Payment\":\"Wechat\"}',3500,68,66,'2018-12-26 22:22:04'),(165,'2018-12-26 22:22:11','{\"Color\":\"black\",\"Pic\":\"2 pic\",\"Payment\":\"Wechat\"}',6000,68,67,'2018-12-26 22:22:11'),(166,'2018-12-26 22:22:13','{\"Color\":\"black\",\"Pic\":\"2 pic\",\"Payment\":\"Alipay\"}',6000,68,67,'2018-12-26 22:22:13'),(167,'2018-12-26 22:22:19','{\"Color\":\"black\",\"Pic\":\"1 pic\",\"Payment\":\"Alipay\"}',3500,68,67,'2018-12-26 22:22:19'),(168,'2018-12-26 22:22:56','{\"Color\":\"black\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',4000,69,56,'2018-12-26 22:22:56'),(169,'2018-12-26 22:22:58','{\"Color\":\"black\",\"Memory\":\"64G\",\"Payment\":\"Alipay\"}',4000,69,57,'2018-12-26 22:22:58'),(170,'2018-12-26 22:23:07','{\"Color\":\"black\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',6500,69,57,'2018-12-26 22:23:07'),(171,'2018-12-26 22:23:09','{\"Color\":\"black\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',6500,69,57,'2018-12-26 22:23:09'),(172,'2018-12-26 22:23:12','{\"Color\":\"green\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',6500,69,57,'2018-12-26 22:23:12'),(173,'2018-12-26 22:23:14','{\"Color\":\"green\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',6500,69,57,'2018-12-26 22:23:14'),(174,'2018-12-26 22:23:19','{\"Color\":\"green\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',4000,69,57,'2018-12-26 22:23:19'),(175,'2018-12-26 22:23:22','{\"Color\":\"green\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',4000,69,57,'2018-12-26 22:23:22'),(176,'2018-12-26 22:24:15','{\"Color\":\"black\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',2700,70,53,'2018-12-26 22:24:15'),(177,'2018-12-26 22:24:17','{\"Color\":\"black\",\"Memory\":\"64G\",\"Payment\":\"Alipay\"}',2700,70,57,'2018-12-26 22:24:17'),(178,'2018-12-26 22:24:20','{\"Color\":\"yellow\",\"Memory\":\"64G\",\"Payment\":\"Alipay\"}',2700,70,57,'2018-12-26 22:24:20'),(179,'2018-12-26 22:24:23','{\"Color\":\"yellow\",\"Memory\":\"64G\",\"Payment\":\"Wechat\"}',2700,70,57,'2018-12-26 22:24:23'),(180,'2018-12-26 22:24:28','{\"Color\":\"yellow\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',4000,70,57,'2018-12-26 22:24:28'),(181,'2018-12-26 22:24:30','{\"Color\":\"yellow\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',4000,70,56,'2018-12-26 22:24:30'),(182,'2018-12-26 22:24:32','{\"Color\":\"black\",\"Memory\":\"128G\",\"Payment\":\"Alipay\"}',4000,70,57,'2018-12-26 22:24:32'),(183,'2018-12-26 22:24:34','{\"Color\":\"black\",\"Memory\":\"128G\",\"Payment\":\"Wechat\"}',4000,70,57,'2018-12-26 22:24:34'),(184,'2018-12-26 22:25:44','{\"Size\":\"32mm\",\"Power\":\"500W\",\"Payment\":\"Wechat\"}',7000,71,67,'2018-12-26 22:25:44'),(185,'2018-12-26 22:25:46','{\"Size\":\"32mm\",\"Power\":\"500W\",\"Payment\":\"Alipay\"}',7000,71,67,'2018-12-26 22:25:46'),(186,'2018-12-26 22:25:53','{\"Size\":\"48mm\",\"Power\":\"500W\",\"Payment\":\"Alipay\"}',9000,71,32,'2018-12-26 22:25:53'),(187,'2018-12-26 22:25:55','{\"Size\":\"48mm\",\"Power\":\"500W\",\"Payment\":\"Wechat\"}',9000,71,32,'2018-12-26 22:25:55'),(188,'2018-12-26 22:45:37','{\"Color\":\"black\",\"Pic\":\"1pic\",\"Payment\":\"Wechat\"}',3500,73,55,'2018-12-26 22:45:37'),(189,'2018-12-26 22:46:25','{\"Quality\":\"pro\",\"Memory\":\"white\",\"Payment\":\"Wechat\"}',20000,74,12,'2018-12-26 22:46:25'),(190,'2018-12-26 22:47:22','{\"Size\":\"42 inch\",\"Factory\":\"4K\",\"Payment\":\"Wechat\"}',23000,75,20,'2018-12-26 22:47:22'),(191,'2018-12-26 22:48:26','{\"Size\":\"38 inch\",\"Factory\":\"4K\",\"Payment\":\"Wechat\"}',19000,76,33,'2018-12-26 22:48:26'),(192,'2018-12-26 22:49:26','{\"Size\":\"48 inch\",\"Factory\":\"4K\",\"Payment\":\"Wechat\"}',20000,77,23,'2018-12-26 22:49:26'),(193,'2018-12-26 22:50:27','{\"Memory\":\"1TB\",\"Color\":\"black\",\"Size\":\"15 inch\",\"Payment\":\"Wechat\"}',8000,78,8,'2018-12-26 22:50:27'),(194,'2018-12-26 22:51:06','{\"Memory\":\"1TB\",\"Color\":\"black\",\"Size\":\"15 inch\",\"Payment\":\"Wechat\"}',8000,79,28,'2018-12-26 22:51:06'),(195,'2018-12-26 22:52:07','{\"Memory\":\"64G\",\"Color\":\"black\",\"Size\":\"9 inch\",\"Payment\":\"Wechat\"}',4000,80,53,'2018-12-26 22:52:07'),(196,'2018-12-26 22:53:34','{\"Memory\":\"64G\",\"Color\":\"black\",\"Size\":\"9 inch\",\"Payment\":\"Wechat\"}',2700,82,44,'2018-12-26 22:53:34'),(197,'2018-12-26 22:54:28','{\"Quality\":\"NS\",\"Memory\":\"1TB\",\"Payment\":\"Wechat\"}',2300,83,42,'2018-12-26 22:54:28'),(198,'2018-12-26 22:56:14','{\"Size\":\"38 inch\",\"Factory\":\"4K\",\"Payment\":\"Wechat\"}',10000,85,55,'2018-12-26 22:56:17'),(199,'2019-01-04 21:52:13','{\"Quality\":\"normal\",\"Memory\":\"128\",\"Payment\":\"Wechat\"}',349,86,99,'2019-01-04 21:52:13'),(200,'2019-01-04 21:52:20','{\"Quality\":\"normal\",\"Memory\":\"256\",\"Payment\":\"Wechat\"}',549,86,100,'2019-01-04 21:52:20'),(201,'2019-01-04 21:52:26','{\"Quality\":\"normal\",\"Memory\":\"512\",\"Payment\":\"Wechat\"}',849,86,100,'2019-01-04 21:52:26');
/*!40000 ALTER TABLE `t_product_specs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_seller`
--

DROP TABLE IF EXISTS `t_seller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_seller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `shop_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_j6wunbrfqnagf0vfay0ym7m4m` (`email`),
  UNIQUE KEY `UK_3xk7aufwrayapvrpflty2toj5` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_seller`
--

LOCK TABLES `t_seller` WRITE;
/*!40000 ALTER TABLE `t_seller` DISABLE KEYS */;
INSERT INTO `t_seller` VALUES (2,'America','2018-12-12 15:42:39','1187697635@qq.com','apple',NULL,'Apple',NULL,0,'2018-12-12 15:42:38','Apple'),(3,'Japan','2018-12-12 15:50:50','1329176648@qq.com','sony',NULL,'sony',NULL,0,'2018-12-12 15:50:50','sony'),(4,'China','2018-12-12 15:51:47','1430612146@qq.com','huawei',NULL,'huawei',NULL,0,'2018-12-12 15:51:47','huawei'),(5,'cw','2018-12-12 15:53:02','331242812@qq.com','cw',NULL,'cw',NULL,0,'2018-12-12 15:53:01','cw'),(6,'China','2018-12-12 15:54:06','772043237@qq.com','oppo',NULL,'oppo',NULL,0,'2018-12-12 15:54:06','oppo'),(7,'Samsung','2018-12-12 15:55:12','NeXT-XLC@outlook.com','samsung',NULL,'samsung',NULL,0,'2018-12-12 15:55:12','samsung'),(8,'razer','2018-12-12 15:57:57','595357438@qq.com','razer',NULL,'razer',NULL,0,'2018-12-12 15:57:56','razer'),(9,'123','2018-12-18 14:24:58','123@163.com','vivo',NULL,'vivo',NULL,0,'2018-12-18 14:24:57','vivo'),(10,'Road Linkin','2018-12-26 08:52:16','841034081@qq.com','RockStar',NULL,'RockStar',NULL,0,'2018-12-26 08:52:15','RockStar'),(11,'KiltonStreet','2019-01-04 13:45:10','y841034081@163.com','w1w2w3w4',NULL,'BobSmith',NULL,0,'2019-01-04 13:45:10','GameLoft');
/*!40000 ALTER TABLE `t_seller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_shop`
--

DROP TABLE IF EXISTS `t_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `seller_id` int(11) NOT NULL,
  `shop_desc` varchar(255) DEFAULT NULL,
  `shop_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ad_money` double DEFAULT NULL,
  `account` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_73ef2i1spccpqxsx7xifylxpf` (`shop_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_shop`
--

LOCK TABLES `t_shop` WRITE;
/*!40000 ALTER TABLE `t_shop` DISABLE KEYS */;
INSERT INTO `t_shop` VALUES (1,'2019-01-05 02:48:09','1187697635@qq.com','4006668800',2,'Apple','Apple',1,'2018-12-12 15:43:33',5000,10000),(2,'2018-12-12 15:51:17','1329176648@qq.com','123456',3,'Sony','Sony',1,'2018-12-12 15:51:17',NULL,NULL),(3,'2018-12-12 15:52:03','1430612146@qq.com','123456',4,'HUAWEI','HUAWEI',1,'2018-12-12 15:52:03',NULL,NULL),(4,'2019-01-04 12:25:17','331242812@qq.com','123',5,'Electronic product','Electronic product',1,'2018-12-12 15:53:40',4000,4000),(5,'2018-12-12 15:54:22','772043237@qq.com','123',6,'OPPO','OPPO',3,'2018-12-12 15:54:22',NULL,NULL),(6,'2018-12-12 15:55:32','NeXT-XLC@outlook.com','123455',7,'Samsung','Samsung',1,'2018-12-12 15:55:32',NULL,NULL),(7,'2018-12-12 15:58:17','595357438@qq.com','12345555667',8,'Razer','Razer',1,'2018-12-12 15:58:17',NULL,NULL),(8,'2018-12-18 14:25:45','12314','1234',9,'Vivo','Vivo',2,'2018-12-18 14:25:45',NULL,NULL),(12,'2018-12-26 08:56:08','841034801@qq.com','18292039753',10,'Rockstar Games, Rockstar','RockStar',1,'2018-12-26 08:56:08',NULL,NULL),(13,'2019-01-04 13:45:53','y841034801@163.com','1110',11,'I Like Game','GameLoft',1,'2019-01-04 13:45:53',NULL,NULL);
/*!40000 ALTER TABLE `t_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_shop_cart`
--

DROP TABLE IF EXISTS `t_shop_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_shop_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `specs_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_shop_cart`
--

LOCK TABLES `t_shop_cart` WRITE;
/*!40000 ALTER TABLE `t_shop_cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_shop_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_shop_collect`
--

DROP TABLE IF EXISTS `t_shop_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_shop_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_shop_collect`
--

LOCK TABLES `t_shop_collect` WRITE;
/*!40000 ALTER TABLE `t_shop_collect` DISABLE KEYS */;
INSERT INTO `t_shop_collect` VALUES (1,4,'2018-12-22 16:46:31',1,'2018-12-22 16:46:31'),(12,2,'2018-12-22 20:10:45',4,'2018-12-22 20:10:45'),(13,2,'2018-12-22 20:11:33',1,'2018-12-22 20:11:33'),(15,3,'2018-12-28 15:42:28',2,'2018-12-28 15:42:28'),(16,3,'2018-12-28 15:42:35',1,'2018-12-28 15:42:35'),(17,6,'2019-01-04 21:18:05',1,'2019-01-04 21:18:05');
/*!40000 ALTER TABLE `t_shop_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_withdraw_history`
--

DROP TABLE IF EXISTS `t_withdraw_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_withdraw_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alipay_id` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `admin_name` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_withdraw_history`
--

LOCK TABLES `t_withdraw_history` WRITE;
/*!40000 ALTER TABLE `t_withdraw_history` DISABLE KEYS */;
INSERT INTO `t_withdraw_history` VALUES (2,'18291865981',300,'cw','2019-01-04 12:13:57'),(3,'13994353536',200,'cw','2019-01-04 12:14:13'),(4,'18291865981',146,'cw','2019-01-05 14:41:17');
/*!40000 ALTER TABLE `t_withdraw_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_withdrawal_record`
--

DROP TABLE IF EXISTS `t_withdrawal_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_withdrawal_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `money` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_withdrawal_record`
--

LOCK TABLES `t_withdrawal_record` WRITE;
/*!40000 ALTER TABLE `t_withdrawal_record` DISABLE KEYS */;
INSERT INTO `t_withdrawal_record` VALUES (5,'18291865981','2019-01-04 14:19:15',16300,'Apple'),(6,'18291865981','2019-01-04 14:19:41',5000,'Apple'),(7,'13994353536','2019-01-05 14:37:16',2000,'Apple');
/*!40000 ALTER TABLE `t_withdrawal_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-06 19:33:57
