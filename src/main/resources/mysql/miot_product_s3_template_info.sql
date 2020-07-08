# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: iot-dev-2.cwnahze7oixf.ap-south-1.rds.amazonaws.com (MySQL 5.7.26-log)
# Database: imdb_1
# Generation Time: 2020-07-08 05:56:44 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table miot_product_s3_template_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `miot_product_s3_template_info`;

CREATE TABLE `miot_product_s3_template_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `third_party_name` varchar(128) DEFAULT '' COMMENT '第三方接入服务名称，如google、alexa',
  `category_name` varchar(128) DEFAULT '' COMMENT '品类名称，如AC、 PW',
  `product_key` varchar(128) DEFAULT '' COMMENT '产品ID',
  `template_type` varchar(128) DEFAULT '' COMMENT 'template类型：commands、actions、status',
  `s3_version_id` varchar(128) DEFAULT '' COMMENT '文件存储到s3中的版本信息',
  `s3_addr` varchar(11) DEFAULT '' COMMENT 's3地址',
  `available` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否可用，默认1（可用）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新时间',
  `third_part_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品转换template存储到s3后到相关存储、版本信息';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
