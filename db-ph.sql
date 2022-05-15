/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.28 : Database - db_ph
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_ph` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `db_ph`;

/*Table structure for table `t_pet` */

DROP TABLE IF EXISTS `t_pet`;

CREATE TABLE `t_pet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `brithdate` varchar(16) NOT NULL,
  `photo` varchar(64) NOT NULL,
  `ownerId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_pet` (`ownerId`),
  CONSTRAINT `FK_t_pet` FOREIGN KEY (`ownerId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_pet` */

insert  into `t_pet`(`id`,`name`,`brithdate`,`photo`,`ownerId`) values 
(1,'tom','20150325','photo/default.jpg',3),
(2,'tony','20160217','photo/default.jpg',3),
(3,'jerry','20160315','photo/jerry.jpg',4),
(4,'哈哈哈','20220301','photo/1651021552799.png',7);

/*Table structure for table `t_speciality` */

DROP TABLE IF EXISTS `t_speciality`;

CREATE TABLE `t_speciality` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_speciality` */

insert  into `t_speciality`(`id`,`name`) values 
(2,'猫科'),
(3,'鸟类'),
(4,'鱼类'),
(6,'爬行类');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(8) NOT NULL,
  `name` varchar(32) NOT NULL,
  `pwd` varchar(32) NOT NULL,
  `tel` varchar(16) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`role`,`name`,`pwd`,`tel`,`address`) values 
(1,'admin','admin','1234','',NULL),
(2,'admin','张三','zhangsan',NULL,NULL),
(3,'customer','汤姆','123456','13812345678','武汉市洪山区'),
(4,'customer','杰瑞','123456','13915247419','武汉市江夏区'),
(5,'customer','张思','123456','15145789521','武汉市武昌区'),
(6,'customer','赵唯','123456','15821475890','武汉'),
(7,'customer','小葛','123456','15685232356','甘肃');

/*Table structure for table `t_vet` */

DROP TABLE IF EXISTS `t_vet`;

CREATE TABLE `t_vet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_vet` */

insert  into `t_vet`(`id`,`name`) values 
(1,'刘松'),
(2,'李斯'),
(3,'王明');

/*Table structure for table `t_vet_speciality` */

DROP TABLE IF EXISTS `t_vet_speciality`;

CREATE TABLE `t_vet_speciality` (
  `vetId` int NOT NULL,
  `specId` int NOT NULL,
  KEY `FK_t_vet_specality` (`vetId`),
  KEY `FK_t_vet_specality_2` (`specId`),
  CONSTRAINT `FK_t_vet_specality` FOREIGN KEY (`vetId`) REFERENCES `t_vet` (`id`),
  CONSTRAINT `FK_t_vet_specality_2` FOREIGN KEY (`specId`) REFERENCES `t_speciality` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_vet_speciality` */

insert  into `t_vet_speciality`(`vetId`,`specId`) values 
(3,2),
(2,2),
(1,2),
(1,3);

/*Table structure for table `t_visit` */

DROP TABLE IF EXISTS `t_visit`;

CREATE TABLE `t_visit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `petId` int NOT NULL,
  `vetId` int NOT NULL,
  `visitdate` varchar(10) NOT NULL,
  `description` text NOT NULL,
  `treatment` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_visit` (`petId`),
  CONSTRAINT `FK_t_visit` FOREIGN KEY (`petId`) REFERENCES `t_pet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_visit` */

insert  into `t_visit`(`id`,`petId`,`vetId`,`visitdate`,`description`,`treatment`) values 
(2,1,3,'2019-01-18','皮肤炎症','局部涂抹药膏，涂3天'),
(3,1,2,'2019-01-18','肠胃不适','健胃消食片，一天2次，一次1片'),
(4,4,1,'2022-04-27','失眠多梦','跑武装越野5公里');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
