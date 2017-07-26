/*
Navicat MySQL Data Transfer

Source Server         : hello
Source Server Version : 50552
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2017-07-26 18:35:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO admin VALUES ('1', 'admin', 'admin123');
-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `lastModified` varchar(30) NOT NULL,
  `size` int(11) NOT NULL,
  `hits` int(11) NOT NULL,
  `type` char(1) NOT NULL,
  `description` mediumtext,
  `filePath` varchar(50) DEFAULT NULL,
  `fileName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of files
-- ----------------------------
INSERT INTO files VALUES ('6', '测试文件', '2016-03-22 09:33:57', '14857', '0', '5', 'only 测试', '/software/1458610437657.docx', '测试.docx');
INSERT INTO files VALUES ('7', 'Pretty Girl', '2016-03-22 09:34:36', '9892', '1', '1', '美图', '/software/1458610476153.gif', 'pretty_girl.gif');
INSERT INTO files VALUES ('8', '植物', '2016-03-22 09:35:13', '25210', '2', '2', '动态植物', '/software/1458610513901.swf', 'flash4378.swf');
INSERT INTO files VALUES ('9', '匆匆那年', '2016-03-22 09:35:47', '373369', '3', '3', '好听的歌曲', '/software/1458610547589.wma', '彭于晏-匆匆那年.wma');
INSERT INTO files VALUES ('10', 'Pretty', '2016-03-22 09:42:05', '9892', '0', '1', '美图图', '/software/1458610925173.gif', 'pretty_girl.gif');
