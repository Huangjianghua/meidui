/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.29-log : Database - membersystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

/*Table structure for table `ms_members_paypwd` */

DROP TABLE IF EXISTS `ms_members_paypwd`;

CREATE TABLE `ms_members_paypwd` (
  `mem_id` char(36) NOT NULL COMMENT '会员ID',
  `pwd` varchar(70) DEFAULT NULL COMMENT '原始支付密码',
  `md5_pwd` varchar(32) DEFAULT NULL COMMENT '设置时间',
  `enable` varchar(1) DEFAULT 'Y' COMMENT '是否启用',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`mem_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `ms_members_paypwd_record` */

DROP TABLE IF EXISTS `ms_members_paypwd_record`;

CREATE TABLE `ms_members_paypwd_record` (
  `mem_id` char(36) NOT NULL COMMENT '会员ID',
  `update_pwd1` varchar(32) DEFAULT NULL COMMENT '修改的密码1',
  `update_pwd2` varchar(32) DEFAULT NULL COMMENT '修改的密码2',
  `update_pwd3` varchar(32) DEFAULT NULL COMMENT '修改的密码3',
  `update_pwd4` varchar(32) DEFAULT NULL COMMENT '修改的密码4',
  `update_pwd5` varchar(32) DEFAULT NULL COMMENT '修改的密码5',
  `update_index` int(11) DEFAULT NULL COMMENT '修改密码的索引,该值为下一次修改的操作',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`mem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
