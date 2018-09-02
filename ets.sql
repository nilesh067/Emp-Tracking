/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.1.61-community : Database - ets
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ets` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ets`;

/*Table structure for table `rfiddata` */

DROP TABLE IF EXISTS `rfiddata`;

CREATE TABLE `rfiddata` (
  `slno` int(10) NOT NULL AUTO_INCREMENT,
  `RfidNo` varchar(20) NOT NULL,
  PRIMARY KEY (`slno`)
) ENGINE=MyISAM AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

/*Data for the table `rfiddata` */

insert  into `rfiddata`(`slno`,`RfidNo`) values (45,'00086558870008655887');

/*Table structure for table `tb_emp_login_details` */

DROP TABLE IF EXISTS `tb_emp_login_details`;

CREATE TABLE `tb_emp_login_details` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `emp_id` int(10) DEFAULT NULL,
  `emp_dept` varchar(100) DEFAULT NULL,
  `emp_type` varchar(100) DEFAULT NULL,
  `emp_deg` varchar(100) DEFAULT NULL,
  `emp_email` varchar(100) DEFAULT NULL,
  `status` int(2) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `tb_emp_login_details` */

insert  into `tb_emp_login_details`(`id`,`user_name`,`password`,`emp_id`,`emp_dept`,`emp_type`,`emp_deg`,`emp_email`,`status`) values (1,'Nilesh','9457240028',NULL,'1','2','Software developer',NULL,1),(2,'shyam','9027896720',NULL,'5','1','Tester',NULL,1),(3,'Nilesh Singh','9457240028',2,'1','2','tranee','nilesh.kumar@jlife.in',1),(4,'sujeet kumar','8755044046',11,'4','3','tranee','sujeetkr755@gmail.com',1),(5,'ram kumar','1111111111',4,'4','3','tranee','nileshjee000@gmail.com',1),(6,'abc xyz','123456789',5,'5','1','tranee','nileshjee000@gmail.com',1),(7,'shyam singh','55555555555',6,'5','1','tranee','nileshjee000@gmail.com',1),(8,'nitin chaudhARY','77777777777',7,'4','3','tranee','nileshjee000@gmail.com',1),(9,'rahul  tyagi','9457240028',8,'3','2','tranee','nileshjee000@gmail.com',1),(10,'Manpreet Singh','8171185567',10,'1','2','tranee','nileshjee000@gmail.com',1),(11,'akash mishra','9536918997',12,'5','1','tranee','nileshjee000@gmail.com',1),(12,'Manish Jaiswal','9808704095',13,'2','2','tranee','manishjas25@gmail.com',1),(13,'Nishikant Tyagi','8791653784',14,'1','2','trenee','tyagi.nishikant45@gmail.com',1),(14,'nagina saw','9634823997',15,'3','2','tranee','rajnagina53@gmail.com',1);

/*Table structure for table `tb_emp_type` */

DROP TABLE IF EXISTS `tb_emp_type`;

CREATE TABLE `tb_emp_type` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `emp_type` varchar(50) DEFAULT NULL,
  `status` int(10) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `tb_emp_type` */

insert  into `tb_emp_type`(`id`,`emp_type`,`status`) values (1,'Management',1),(2,'It/Softwares',1),(3,'Product',1),(4,'Business',1),(5,'Account',1),(6,'Security',1),(7,'Others',1);

/*Table structure for table `tb_ets_assigned_task` */

DROP TABLE IF EXISTS `tb_ets_assigned_task`;

CREATE TABLE `tb_ets_assigned_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_type` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL,
  `task_name` varchar(200) DEFAULT NULL,
  `task_descp` varchar(500) DEFAULT NULL,
  `assigned_by` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `assigned_time` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `employee_msg` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

/*Data for the table `tb_ets_assigned_task` */

insert  into `tb_ets_assigned_task`(`id`,`emp_type`,`dept_id`,`emp_id`,`task_name`,`task_descp`,`assigned_by`,`status`,`assigned_time`,`last_update`,`employee_msg`) values (1,2,1,3,'New project','You work in this project AS a project Manager and  lead operation team also','Admin',1,'2016-04-16 22:53:08','2016-04-16 22:53:08',''),(2,3,4,7,'product delivery','you must deliver this project after testing compleated','Admin',1,'2016-04-16 23:00:52','2016-04-16 23:00:52',''),(16,2,3,8,'nnnnnnnn','nnnnnnnnn','Admin',1,'2016-04-16 23:47:21','2016-04-16 23:47:21',''),(17,2,3,8,'nnnnnnnn','nnnnnnnnn','Admin',1,'2016-04-16 23:52:20','2016-04-16 23:52:20',''),(43,2,3,15,'new project','vas','Admin',1,'2016-04-24 23:17:09','2016-04-24 23:17:09','');

/*Table structure for table `tb_ets_attendance` */

DROP TABLE IF EXISTS `tb_ets_attendance`;

CREATE TABLE `tb_ets_attendance` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `emp_id` int(10) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `emp_entry_time` datetime DEFAULT NULL,
  `emp_exit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_ets_attendance` */

/*Table structure for table `tb_ets_dept` */

DROP TABLE IF EXISTS `tb_ets_dept`;

CREATE TABLE `tb_ets_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(50) DEFAULT NULL,
  `dept_loc` varchar(50) DEFAULT NULL,
  `sept_mgr` varchar(50) DEFAULT NULL,
  `status` int(10) DEFAULT '1',
  `emp_type` int(10) DEFAULT NULL COMMENT '1=Management,2=It,3=Product,4=Business,5=Account,6=Security,7=Other',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `tb_ets_dept` */

insert  into `tb_ets_dept`(`id`,`dept_name`,`dept_loc`,`sept_mgr`,`status`,`emp_type`) values (1,'Engineering','GF','mr.xxxx',1,2),(2,'Operation l1','ff','mr.yyy',1,2),(3,'Operation l2','2nd','mr.ccc',1,2),(4,'Product and Service delivery','3rd','mr.vvv',1,3),(5,'Human Resources',NULL,'mr.sss',1,1);

/*Table structure for table `tb_ets_emp_reg` */

DROP TABLE IF EXISTS `tb_ets_emp_reg`;

CREATE TABLE `tb_ets_emp_reg` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `empName` varchar(100) DEFAULT NULL,
  `empDept` varchar(100) DEFAULT NULL,
  `empGender` varchar(100) DEFAULT NULL,
  `empSsn` varchar(100) DEFAULT '00000',
  `empDeptLoc` varchar(100) DEFAULT 'gf',
  `empDOB` datetime DEFAULT NULL,
  `empDOJ` datetime DEFAULT NULL,
  `empDesignation` varchar(100) DEFAULT NULL,
  `emptype` varchar(100) DEFAULT NULL,
  `empMobile` varchar(12) DEFAULT NULL,
  `empEmail` varchar(100) DEFAULT NULL,
  `empMeritorial` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `tb_ets_emp_reg` */

insert  into `tb_ets_emp_reg`(`id`,`empName`,`empDept`,`empGender`,`empSsn`,`empDeptLoc`,`empDOB`,`empDOJ`,`empDesignation`,`emptype`,`empMobile`,`empEmail`,`empMeritorial`) values (2,'Nilesh Singh','1','   Male','0000','gf','2016-04-11 00:24:26','2016-04-11 00:24:26','tranee','2','9457240028','nilesh.kumar@jlife.in','   Unmarried'),(3,'Nilesh Singh','1','   Male','0000','gf','2016-04-11 00:24:33','2016-04-11 00:24:33','tranee','2','9457240028','nilesh.kumar@jlife.in','   Unmarried'),(4,'ram kumar','4','   Male','0000','gf','2016-04-11 00:31:28','2016-04-11 00:31:28','tranee','3','1111111111','nileshjee000@gmail.com','   Unmarried'),(5,'abc xyz','5','   Male','0000','gf','2016-04-12 11:05:55','2016-04-12 11:05:55','tranee','1','123456789','nileshjee000@gmail.com','   Unmarried'),(6,'shyam singh','5','   Male','0000','gf','2016-04-12 11:27:10','2016-04-12 11:27:10','tranee','1','55555555555','nileshjee000@gmail.com','   Unmarried'),(7,'nitin chaudhARY','4','   Male','0000','gf','2016-04-12 13:12:14','2016-04-12 13:12:14','tranee','3','77777777777','nileshjee000@gmail.com','   Unmarried'),(8,'rahul  tyagi','3','   Male','0000','gf','2016-04-12 13:30:13','2016-04-12 13:30:13','tranee','2','9457240028','nileshjee000@gmail.com','   Unmarried'),(9,'Ravi  tyagi','1','   Male','0000','gf','2016-04-01 00:00:00','2016-04-02 00:00:00','Manager','2','7895713713','ravi.tyagi@jiffysoftwares.in','   Unmarried'),(10,'Manpreet Singh','1','   Male','0000','gf','2016-04-01 00:00:00','2015-11-04 07:55:20','tranee','2','8171185567','nileshjee000@gmail.com','   Unmarried'),(11,'sujeet kumar','4','   Male','0000','gf','2014-06-07 00:00:00','2016-04-17 00:00:00','tranee','3','8755044046','sujeetkr755@gmail.com','   Unmarried'),(12,'akash mishra','5','   Male','0000','gf','2015-10-01 00:00:00','2016-04-17 00:00:00','tranee','1','9536918997','nileshjee000@gmail.com','   Unmarried'),(13,'Manish Jaiswal','2','   Male','0000','gf','2015-07-01 00:00:00','2016-04-17 00:00:00','tranee','2','9808704095','manishjas25@gmail.com','   Unmarried'),(14,'Nishikant Tyagi','1','   Male','0000','gf','2016-04-04 00:00:00','2016-04-18 00:00:00','trenee','2','8791653784','tyagi.nishikant45@gmail.com','   Unmarried'),(15,'nagina saw','3','   Male','0000','gf','2015-12-01 00:00:00','2016-06-01 00:00:00','tranee','2','9634823997','rajnagina53@gmail.com','   Unmarried');

/*Table structure for table `tb_ets_tracking_info` */

DROP TABLE IF EXISTS `tb_ets_tracking_info`;

CREATE TABLE `tb_ets_tracking_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `emp _id` int(10) DEFAULT NULL,
  `tag_no` varchar(50) DEFAULT NULL,
  `status` int(10) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_ets_tracking_info` */

/*Table structure for table `tb_menu_access` */

DROP TABLE IF EXISTS `tb_menu_access`;

CREATE TABLE `tb_menu_access` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_menu` (`menu_id`,`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;

/*Data for the table `tb_menu_access` */

insert  into `tb_menu_access`(`id`,`menu_id`,`role_id`,`status`,`created_on`,`last_update`) values (1,1,2,1,'2015-08-15 13:04:08','2015-08-15 13:04:08'),(2,2,2,1,'2015-08-15 13:04:08','2015-08-15 13:04:08'),(3,3,2,1,'2015-08-15 13:04:08','2015-08-15 13:04:08'),(4,9,2,1,'2015-08-15 13:04:08','2015-08-15 13:04:08'),(62,31,2,1,'2016-04-15 23:01:00','2016-04-10 23:11:01'),(9,21,2,1,'2015-08-15 13:04:08','2015-08-15 13:04:08'),(22,22,2,1,'2015-08-15 13:04:08','2015-08-15 13:04:08'),(65,51,2,1,'2016-04-15 23:04:21','2016-04-15 23:04:23'),(64,5,2,1,'2016-04-15 22:59:25','2016-04-15 22:59:30'),(63,4,2,1,'2016-04-15 23:00:58','2016-04-10 23:11:08');

/*Table structure for table `tb_menu_details` */

DROP TABLE IF EXISTS `tb_menu_details`;

CREATE TABLE `tb_menu_details` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(50) DEFAULT NULL,
  `target_page` varchar(200) DEFAULT NULL,
  `icon_image` varchar(200) DEFAULT NULL,
  `menu_description` varchar(100) DEFAULT NULL,
  `hint_text` varchar(100) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `show_order` int(11) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `parent_menu` int(11) DEFAULT '0',
  `system_menu` tinyint(4) DEFAULT '0',
  `has_submenu` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `unq_menu` (`menu_name`)
) ENGINE=MyISAM AUTO_INCREMENT=437 DEFAULT CHARSET=latin1;

/*Data for the table `tb_menu_details` */

insert  into `tb_menu_details`(`menu_id`,`menu_name`,`target_page`,`icon_image`,`menu_description`,`hint_text`,`status`,`show_order`,`created_on`,`last_update`,`parent_menu`,`system_menu`,`has_submenu`) values (1,'home','#','','','',1,1,'2016-04-06 00:33:13','2016-04-07 00:33:18',0,0,0),(22,'User Maneger','#','','','',1,2,'2016-04-06 00:33:10','2016-04-07 00:33:22',2,0,0),(3,'Track Employee','/ETS/ui/Manage/usermgr.xhtml','','','',1,3,'2016-04-06 00:33:08','2016-04-06 00:33:26',0,0,1),(5,'Dashboard','#','','','',1,5,'2016-04-15 23:02:03','2016-04-15 23:02:06',0,0,1),(51,'Admin Dashboard','/ETS/ui/Manage/admindash.xhtml','','','',1,1,'2016-04-15 23:03:52','2016-04-15 23:03:55',5,0,0),(9,'Logout','/ETS/index.xhtml','','Logout Button. Remove Login Credentials from Session.','Click to Logout and clear Session Cache.',1,99,'2016-04-06 00:33:04','2016-04-06 00:33:28',0,0,0),(21,'Employee Manager','/ETS/ui/Manage/empManager.xhtml','','','',1,1,'2016-04-06 00:33:06','2016-04-06 00:33:30',2,0,0),(2,'Manage','#','','','',1,2,'2016-04-06 00:33:00','2016-04-06 00:33:02',0,0,1),(31,'Track All Employee ','/ETS/ui/Tracker/trackall.xhtml','','',NULL,1,1,'2016-04-06 23:17:37','2016-04-06 23:17:40',3,0,0),(4,'Reports','#','','',NULL,1,4,'2016-04-06 00:50:23','2016-04-06 00:32:58',0,0,0);

/*Table structure for table `tb_user_roles` */

DROP TABLE IF EXISTS `tb_user_roles`;

CREATE TABLE `tb_user_roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `role_descp` varchar(200) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `homepage` varchar(100) DEFAULT 'loginsuccess',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `uniq_role_name` (`role_name`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `tb_user_roles` */

insert  into `tb_user_roles`(`role_id`,`role_name`,`role_descp`,`status`,`created_on`,`last_update`,`homepage`) values (1,'superuser','superuser',1,'2014-09-05 18:40:13','2014-09-05 18:40:38','uimainmenu'),(2,'administrator','admin',1,'2014-09-05 18:40:16','2014-09-05 18:40:40','uimainmenu');

/*Table structure for table `tb_users` */

DROP TABLE IF EXISTS `tb_users`;

CREATE TABLE `tb_users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unq_user` (`user_name`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `tb_users` */

insert  into `tb_users`(`user_id`,`role_id`,`user_name`,`password`,`email`,`mobile`,`status`,`created_on`,`last_update`,`company_id`) values (1,2,'admin','admin@!@#','admin@abc.com','919999631990',1,'0000-00-00 00:00:00','0000-00-00 00:00:00',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
