/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.11-MariaDB : Database - eng_project
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`eng_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `eng_project`;

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `city_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(255) NOT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE KEY `UK_djnk44fptegbsu6drhc9xvlfj` (`city_name`) USING HASH
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*Data for the table `city` */

insert  into `city`(`city_id`,`city_name`) values 
(1,'Beograd'),
(2,'Novi Sad'),
(3,'Arandjelovac'),
(4,'Nis'),
(5,'Kragujevac'),
(6,'Jagodina'),
(7,'Cacak'),
(8,'Valjevo');

/*Table structure for table `exam` */

DROP TABLE IF EXISTS `exam`;

CREATE TABLE `exam` (
  `exam_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `period` datetime NOT NULL,
  `professor_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`exam_id`),
  KEY `FKeglmy3nn07nhivwykhftddfwe` (`professor_id`),
  KEY `FKos7g6kn2748ll3ofc3w163gxh` (`subject_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `exam` */

insert  into `exam`(`exam_id`,`period`,`professor_id`,`subject_id`) values 
(1,'2021-04-17 02:00:00',4,8),
(2,'2021-04-13 02:00:00',1,2),
(3,'2021-04-11 02:00:00',6,6),
(4,'2021-04-11 02:00:00',5,7),
(5,'2021-04-27 02:00:00',6,1);

/*Table structure for table `exam_registration` */

DROP TABLE IF EXISTS `exam_registration`;

CREATE TABLE `exam_registration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbhjcqx7ncvnjb8qqptu7l4pn0` (`exam_id`),
  KEY `FKb5p9qj59e46m5nptnofeh61ii` (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `exam_registration` */

insert  into `exam_registration`(`id`,`exam_id`,`student_id`) values 
(1,2,2),
(2,1,4);

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values 
(1);

/*Table structure for table `professor` */

DROP TABLE IF EXISTS `professor`;

CREATE TABLE `professor` (
  `professor_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `professor_email` varchar(255) DEFAULT NULL,
  `professor_first_name` varchar(255) NOT NULL,
  `professor_last_name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `reelection_date` datetime NOT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `title_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`professor_id`),
  UNIQUE KEY `UK_dnhtaw2stfqyilrlu4oir948p` (`professor_email`) USING HASH,
  KEY `FK3mfrqpi8vavgb0hoe9mei6hl1` (`city_id`),
  KEY `FK91aghu9vk7ljkqdemcv1sfxqm` (`title_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `professor` */

insert  into `professor`(`professor_id`,`address`,`professor_email`,`professor_first_name`,`professor_last_name`,`phone`,`reelection_date`,`city_id`,`title_id`) values 
(1,'address','petar@gmail.com','Petar','Petrovic','066111222','2021-04-17 02:00:00',3,1),
(2,'Tresnjar','nikola@gmail.com','Nikola','Petkovic','065686321','2021-04-24 02:00:00',8,2),
(3,'adress1','aleksadarkostic@gmail.com','Aleksandar','Kostic','06523412','2021-04-10 02:00:00',3,5),
(4,'adress4','jovana@gmail.com','Jovana','Jovanovic','06954321','2021-04-28 02:00:00',6,4),
(5,'adress8','lazic@gmail.com','Miroslav','Lazic','063854123','2021-04-12 02:00:00',5,1),
(6,'adress6','zoran@gmail.com','Zoran','Lukovic','06123875','2021-04-04 02:00:00',2,3);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(50) DEFAULT NULL,
  `current_year_of_study` int(11) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `first_name` varchar(30) NOT NULL,
  `index_number` varchar(5) NOT NULL,
  `index_year` varchar(4) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `UK_1clrouh0iqmfd9tbcu50eib2` (`index_number`),
  UNIQUE KEY `UK_fe0i52si7ybu0wjedj6motiim` (`email`),
  KEY `FKmdu7cs20x42nuntbcbhgr9l2c` (`city_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `student` */

insert  into `student`(`student_id`,`address`,`current_year_of_study`,`email`,`first_name`,`index_number`,`index_year`,`last_name`,`city_id`) values 
(1,'Jurija Gagarina',3,'aleksandar@gmail.com','Aleksandar','137','2017','Vukovic',1),
(2,'adresa1',2,'pera@gmail.com','Petar','323','2020','Knezevic',6),
(3,'adress6',3,'nikola@gmail.com','Nikola','567','2016','Nikolic',5),
(4,'address',3,'uros@gmail.com','Uros','904','2019','Ilic',3),
(5,'address22',4,'marko@gmail.com','Marko','777','2019','Markovic',5),
(6,'address99',2,'jelena@gmail.com','Jelena','022','2017','Jocic',6);

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `subject_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  `esp` bigint(20) NOT NULL,
  `subject_name` varchar(30) NOT NULL,
  `semester` varchar(10) DEFAULT NULL,
  `year_of_study` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `subject` */

insert  into `subject`(`subject_id`,`description`,`esp`,`subject_name`,`semester`,`year_of_study`) values 
(1,'Biznis planovi',8,'Marketing i Internet marketing','sedmi',4),
(2,'opis',9,'Veb programiranje','treci',2),
(3,'nemacki',6,'Nemacki jezik','osmi',4),
(4,'opis',6,'Veb dizajn','cetvrti',2),
(6,'opis',7,'Menadzment','osmi',4),
(7,'opis',9,'Algoritmi i strukture podataka','peti',3),
(8,'opis',7,'Informacioni sistemi','cetvrti',2),
(9,'opis',8,'Internet programerski alati','osmi',4),
(10,'opis',6,'OOP','drugi',1);

/*Table structure for table `title` */

DROP TABLE IF EXISTS `title`;

CREATE TABLE `title` (
  `title_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title_name` varchar(255) NOT NULL,
  PRIMARY KEY (`title_id`),
  UNIQUE KEY `UK_hfgvsaifir2hws6pgqoexg1yi` (`title_name`) USING HASH
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `title` */

insert  into `title`(`title_id`,`title_name`) values 
(1,'Asistent'),
(2,'Docent'),
(3,'Vanredni profesor'),
(4,'Redovni profesor'),
(5,'Saradnik u nastavi');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
