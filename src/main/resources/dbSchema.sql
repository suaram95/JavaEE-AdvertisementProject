/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.18-log : Database - project_advertisementservletjsp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`project_advertisementservletjsp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `project_advertisementservletjsp`;

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `category` enum('CAR','HOUSES','OTHER') NOT NULL,
  `created_date` date NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `item_ibfk_1` (`user_id`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `item` */

insert  into `item`(`id`,`title`,`description`,`price`,`category`,`created_date`,`user_id`) values (9,'dsfsdfsdf','sdfsdfds',2513,'CAR','2021-01-20',7),(10,'sdfsdf','sdfdsf',2513,'HOUSES','2021-01-20',7);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` enum('USER','ADMIN') NOT NULL,
  `picture_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`surname`,`phone_number`,`username`,`password`,`user_type`,`picture_url`) values (1,'admin','admin','0000','admin','admin','ADMIN',NULL),(7,'user','user','user','user','user','USER','161114816764020161101111253.jpg'),(9,'dsfsdfsdf','sdfsdfsdf','sdfsdf','sdfsdfsdf','sdfsdfsdf','USER','161114792184720181108_202808.jpg'),(10,'ghjghjghghj','ghjgjgh','ghjghjg','ghjgjghj','ghjghjghj','USER','161114793594820190826_231042.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
