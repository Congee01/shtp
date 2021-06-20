drop database if exists shtp;
create database if not exists `shtp` default charset utf8;
use `shtp`;
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
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(255) NOT NULL,
                          `intro` varchar(255) DEFAULT NULL,
                          `picture` varchar(255) DEFAULT NULL,
                          `create_time` datetime NOT NULL,
                          `delete_time` datetime DEFAULT NULL,
                          `cost` int NOT NULL,
                          `manager_id` int NOT NULL,
                          `manager_name` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `fk_user_course` (`manager_id`),
                          CONSTRAINT `fk_user_course` FOREIGN KEY (`manager_id`) REFERENCES `user_info` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `product` VALUES ('1', '微积分', '好东西', 'defaultImg', '2021-05-29 10:00:00',null,10,1,'小明');

-- ----------------------------
-- Table structure for `course_order`
-- ----------------------------
DROP TABLE IF EXISTS `product_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_order` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `cost` int NOT NULL,
                                `product_id` int NOT NULL,
                                `product_name` varchar(255) NOT NULL,
                                `create_time` datetime NOT NULL,
                                `user_id` int NOT NULL,
                                `status` int NOT NULL,
                                PRIMARY KEY (`id`),
                                KEY `fk_course_order` (`product_id`),
                                CONSTRAINT `fk_course_order` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
                                KEY `fk_user_order` (`user_id`),
                                CONSTRAINT `fk_user_order` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `uname` varchar(255) NOT NULL,
                             `phone` varchar(255) NOT NULL,
                             `password` varchar(255) NOT NULL,
                             `picture` varchar(255) DEFAULT NULL,
                             `balance` int NOT NULL,
                             `create_time` datetime NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_ware
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '小明', '123456789', '123456', null, 0, '2021-05-29 10:00:00');

-- ----------------------------
-- Table structure for `recharge_order`
-- ----------------------------

DROP TABLE IF EXISTS `recharge_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recharge_order` (
                                  `order_id` int NOT NULL AUTO_INCREMENT,
                                  `user_id` int NOT NULL,
                                  `value` int NOT NULL,
                                  `create_time` datetime NOT NULL,
                                  PRIMARY KEY (`order_id`),
                                  KEY `fk_user_recharge` (`user_id`),
                                  CONSTRAINT `fk_user_recharge` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS=1;