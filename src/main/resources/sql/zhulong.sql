/*
Navicat MySQL Data Transfer

Source Server         : local host
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : zhulong

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-12-11 09:12:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for all_tables
-- ----------------------------
DROP TABLE IF EXISTS `all_tables`;
CREATE TABLE `all_tables` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `NAME` varchar(20) DEFAULT NULL COMMENT '表名',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '表创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '表更新时间',
  `DESCRIPTION` varchar(30) DEFAULT NULL COMMENT '表注释',
  `REMARK` varchar(30) DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(30) DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(30) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='数据库中各表说明';

-- ----------------------------
-- Records of all_tables
-- ----------------------------
INSERT INTO `all_tables` VALUES ('1', 'IMAGES', '2019-09-28 11:21:20', '2019-09-28 11:21:22', '图片', '图片表', null, null);
INSERT INTO `all_tables` VALUES ('2', 'VIDEOS', '2019-09-28 11:22:13', '2019-09-28 11:22:15', '视频', '视频表', null, null);
INSERT INTO `all_tables` VALUES ('3', 'SHORT_VIDEOS', '2019-09-28 11:22:55', '2019-09-28 11:22:59', '短视频', '短视频表', null, null);
INSERT INTO `all_tables` VALUES ('4', 'AUDIOS', '2019-09-28 11:23:26', '2019-09-28 11:23:28', '音频', '音频表', null, null);
INSERT INTO `all_tables` VALUES ('5', 'OPERATE_LOGS', '2019-09-28 11:25:01', '2019-09-28 11:25:03', '日志', '日志表', null, null);
INSERT INTO `all_tables` VALUES ('6', 'DOCUMENTS', '2019-09-28 11:25:51', '2019-09-28 11:25:53', '文档', '文档表', null, null);

-- ----------------------------
-- Table structure for audios
-- ----------------------------
DROP TABLE IF EXISTS `audios`;
CREATE TABLE `audios` (
  `ID` int(10) NOT NULL DEFAULT '0' COMMENT '音频ID',
  `TITLE` varchar(50) DEFAULT NULL COMMENT '音频名称',
  `NEW_TITLE` varchar(50) DEFAULT NULL COMMENT '音频重命名',
  `TYPE` varchar(10) DEFAULT NULL COMMENT '音频类型',
  `SIZE` int(50) DEFAULT NULL COMMENT '音频大小',
  `POSITION` varchar(50) DEFAULT NULL COMMENT '音频位置',
  `UUID` varchar(30) DEFAULT NULL COMMENT '音频唯一ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `UPLOADER` varchar(20) DEFAULT NULL COMMENT '音频上传者',
  `DESCRIPTION` varchar(20) DEFAULT NULL COMMENT '表注释',
  `REMARK` varchar(20) DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(20) DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(20) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='音频相关';

-- ----------------------------
-- Records of audios
-- ----------------------------
INSERT INTO `audios` VALUES ('1', '音频test1', 'new音频test1', 'mp3', '2000', 'd:/abcd/', '41654313213', '2019-09-28 12:23:29', '2019-09-28 12:23:29', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `audios` VALUES ('2', '音频test2', 'new音频test2', 'wav', '2000', 'd:/abcd/', '41654313213', '2019-09-28 12:23:29', '2019-09-28 12:23:29', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');

-- ----------------------------
-- Table structure for documents
-- ----------------------------
DROP TABLE IF EXISTS `documents`;
CREATE TABLE `documents` (
  `ID` int(10) NOT NULL DEFAULT '0' COMMENT '文档ID',
  `TITLE` varchar(50) DEFAULT NULL COMMENT '文档名称',
  `NEW_TITLE` varchar(50) DEFAULT NULL COMMENT '文档重命名',
  `TYPE` varchar(10) DEFAULT NULL COMMENT '文档类型',
  `SIZE` int(50) DEFAULT NULL COMMENT '文档大小',
  `POSITION` varchar(50) DEFAULT NULL COMMENT '文档位置',
  `UUID` varchar(30) DEFAULT NULL COMMENT '文档唯一ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `UPLOADER` varchar(20) DEFAULT NULL COMMENT '文档上传者',
  `DESCRIPTION` varchar(20) DEFAULT NULL COMMENT '表注释',
  `REMARK` varchar(20) DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(20) DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(20) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='文档相关';

-- ----------------------------
-- Records of documents
-- ----------------------------
INSERT INTO `documents` VALUES ('1', '文档test1', 'new文档test1', 'pdf', '2000', 'd:/abcd/', '41654313213', '2019-09-28 12:25:26', '2019-09-28 12:25:26', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `documents` VALUES ('2', '文档test2', 'new文档test2', 'doc', '2000', 'd:/abcd/', '41654313213', '2019-09-28 12:25:27', '2019-09-28 12:25:27', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
  `TITLE` varchar(100) NOT NULL COMMENT '图片名称',
  `TYPE` varchar(10) NOT NULL COMMENT '图片类型',
  `SIZE` bigint(50) NOT NULL COMMENT '图片大小',
  `SERVER_POSITION` varchar(100) DEFAULT NULL COMMENT '服务器中的图片位置',
  `HDFS_POSITION` varchar(100) DEFAULT NULL COMMENT 'HDFS 中的图片位置',
  `UUID` varchar(100) NOT NULL COMMENT '图片唯一ID',
  `UPLOAD_TITLE` varchar(100) NOT NULL COMMENT '文件的上传名称',
  `UPLOADER` varchar(100) DEFAULT NULL COMMENT '图片上传者',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '表备注',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `NOTE1` varchar(100) DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(100) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='图片相关';

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES ('1', '2.PNG', 'PNG', '3421', 'images/upload/c30a90eb-0acc-4b73-935d-ec52976e45fb_2.PNG', '/user/lxj/input/testfile2/', 'c30a90eb-0acc-4b73-935d-ec52976e45fb', 'c30a90eb-0acc-4b73-935d-ec52976e45fb_2.PNG', '上传者a26444466666666666', '555511', '2019-12-10 11:32:58', '2019-12-10 11:32:58', null, null);

-- ----------------------------
-- Table structure for operate_logs
-- ----------------------------
DROP TABLE IF EXISTS `operate_logs`;
CREATE TABLE `operate_logs` (
  `ID` int(10) NOT NULL DEFAULT '0' COMMENT '日志ID',
  `TITLE` varchar(50) DEFAULT NULL COMMENT '日志名称',
  `NEW_TITLE` varchar(50) DEFAULT NULL COMMENT '日志重命名',
  `TYPE` varchar(10) DEFAULT NULL COMMENT '日志类型',
  `SIZE` int(50) DEFAULT NULL COMMENT '日志大小',
  `POSITION` varchar(50) DEFAULT NULL COMMENT '日志位置',
  `UUID` varchar(30) DEFAULT NULL COMMENT '日志唯一ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `UPLOADER` varchar(20) DEFAULT NULL COMMENT '日志上传者',
  `DESCRIPTION` varchar(20) DEFAULT NULL COMMENT '表注释',
  `REMARK` varchar(20) DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(20) DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(20) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='日志相关';

-- ----------------------------
-- Records of operate_logs
-- ----------------------------
INSERT INTO `operate_logs` VALUES ('1', '日志test1', 'new日志test1', 'log', '2000', 'd:/abcd/', '41654313213', '2019-09-28 12:26:34', '2019-09-28 12:26:34', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `operate_logs` VALUES ('2', '日志test2', 'new日志test2', 'log', '2000', 'd:/abcd/', '41654313213', '2019-09-28 12:26:34', '2019-09-28 12:26:34', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');

-- ----------------------------
-- Table structure for short_videos
-- ----------------------------
DROP TABLE IF EXISTS `short_videos`;
CREATE TABLE `short_videos` (
  `ID` int(10) NOT NULL DEFAULT '0' COMMENT '短视频ID',
  `TITLE` varchar(50) DEFAULT NULL COMMENT '短视频名称',
  `NEW_TITLE` varchar(50) DEFAULT NULL COMMENT '短视频重命名',
  `TYPE` varchar(10) DEFAULT NULL COMMENT '短视频类型',
  `SIZE` int(50) DEFAULT NULL COMMENT '短视频大小',
  `POSITION` varchar(50) DEFAULT NULL COMMENT '短视频位置',
  `UUID` varchar(30) DEFAULT NULL COMMENT '短视频唯一ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `UPLOADER` varchar(20) DEFAULT NULL COMMENT '短视频上传者',
  `DESCRIPTION` varchar(20) DEFAULT NULL COMMENT '表注释',
  `REMARK` varchar(20) DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(20) DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(20) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='短视频相关';

-- ----------------------------
-- Records of short_videos
-- ----------------------------
INSERT INTO `short_videos` VALUES ('1', '短视频test1', 'new短视频test1', 'avi', '2000', 'd:/abcd/', '41654313213', '2019-09-28 12:21:53', '2019-09-28 12:21:53', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `short_videos` VALUES ('2', '短视频test2', 'new短视频test2', 'avi', '2000', 'd:/abcd/', '41654313213', '2019-09-28 12:21:54', '2019-09-28 12:21:54', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');

-- ----------------------------
-- Table structure for videos
-- ----------------------------
DROP TABLE IF EXISTS `videos`;
CREATE TABLE `videos` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '视频ID',
  `TITLE` varchar(100) NOT NULL COMMENT '视频名称',
  `TYPE` varchar(10) NOT NULL COMMENT '视频类型',
  `SIZE` bigint(100) NOT NULL COMMENT '视频大小',
  `POSITION` varchar(100) DEFAULT NULL COMMENT 'HDFS 中的视频位置',
  `UUID` varchar(100) NOT NULL COMMENT '视频唯一ID',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `UPLOADER` varchar(100) DEFAULT NULL COMMENT '视频上传者',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(100) DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(100) DEFAULT NULL COMMENT '扩展字段2',
  `UPLOAD_TITLE` varchar(100) NOT NULL COMMENT '文件的上传名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='视频相关';

-- ----------------------------
-- Records of videos
-- ----------------------------
INSERT INTO `videos` VALUES ('1', '测试.mp4', 'mp4', '342123432', '/user/hadoop/input/', 'c30a90eb-0acc-4b73-935d-ec52976e45fb', '2019-12-04 17:31:45', '2019-12-04 17:31:45', '上传者', '555511', null, null, 'c30a90eb-0acc-4b73-935d-ec52976e45fb_2.PNG');
