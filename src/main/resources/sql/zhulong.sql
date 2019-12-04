/*
Navicat MySQL Data Transfer

Source Server         : local host
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : zhulong

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-12-04 17:37:18
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
  `POSITION` varchar(100) DEFAULT NULL COMMENT 'HDFS 中的图片位置',
  `UUID` varchar(100) NOT NULL COMMENT '图片唯一ID',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `UPLOADER` varchar(100) DEFAULT NULL COMMENT '图片上传者',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(100) DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(100) DEFAULT NULL COMMENT '扩展字段2',
  `UPLOAD_TITLE` varchar(100) NOT NULL COMMENT '文件的上传名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='图片相关';

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES ('1', '图片', 'jpg', '200', 'd:/abc/', 'JLJASDF341JJ', '2019-12-04 15:42:02', '2019-12-04 15:42:02', '上传者a', ' 备注', '扩展字段1', ' 扩展字段2', '');
INSERT INTO `images` VALUES ('2', '图片test2', 'png', '20', 'd:/abcd/', 'DANLCSAJDL1', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', ' 备注', '扩展字段1', ' 扩展字段2', '');
INSERT INTO `images` VALUES ('3', '图片test3', 'png', '20', 'd:/abcd/', 'DANLCSAJDL2', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', ' 备注', '扩展字段1', ' 扩展字段2', '');
INSERT INTO `images` VALUES ('4', '图片test4', 'png', '20', 'd:/abcd/', 'DANLCSAJDL3', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', ' 备注', '扩展字段1', ' 扩展字段2', '');
INSERT INTO `images` VALUES ('5', '图片test5', 'png', '20', 'd:/abcd/', 'DANLCS4AJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', ' 备注', '扩展字段1', ' 扩展字段2', '');
INSERT INTO `images` VALUES ('6', '图片test6', 'png', '20', 'd:/abcd/', 'DANLCS5AJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', ' 备注', '扩展字段1', ' 扩展字段2', '');
INSERT INTO `images` VALUES ('7', '图片test77', 'png', '20', 'd:/abcd/', 'DANLC6SAJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', ' 备注', '扩展字段1', ' 扩展字段2', '');
INSERT INTO `images` VALUES ('8', '图片test8', 'png', '20', 'd:/abcd/', 'DANLCS7JDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', ' 备注', '扩展字段1', ' 扩展字段2', '');
INSERT INTO `images` VALUES ('9', '图片test9', 'png', '20', 'd:/abcd/', 'DANLCSA8JDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', ' 备注', '扩展字段1', ' 扩展字段2', '');
INSERT INTO `images` VALUES ('10', '图片test10', 'png', '20', 'd:/abcd/', 'DANLC9SAJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', ' 备注', '扩展字段1', ' 扩展字段2', '');
INSERT INTO `images` VALUES ('11', '图片test11', 'png', '20', 'd:/abcd/', 'DANLCS9A0JDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', ' 备注', '扩展字段1', ' 扩展字段2', '');
INSERT INTO `images` VALUES ('12', 'lxj', 'png', '123456', '位置', '444444', null, null, null, '44', null, null, '');
INSERT INTO `images` VALUES ('13', '1.PNG', 'PNG', '13794', '/user/lxj/input/testfile2/', '665b9e14-3db8-4700-a027-05030c65dac7', null, null, null, '备注', null, null, '');
INSERT INTO `images` VALUES ('14', '1.PNG', 'PNG', '13794', '/user/lxj/input/testfile2/', 'f7dad5b2-a2e8-40d6-8da0-9dd30bb5c5d7', '2019-12-04 17:08:17', '2019-12-04 17:08:17', null, '备注', null, null, '');
INSERT INTO `images` VALUES ('15', '3.PNG', 'PNG', '115920', '/user/lxj/input/testfile2/', '75f70fd0-1c2b-4a10-afb9-a9ce191c9bde', '2019-12-04 17:09:58', '2019-12-04 17:09:58', null, '555555555', null, null, '');
INSERT INTO `images` VALUES ('16', '2.PNG', 'PNG', '3421', '/user/lxj/input/testfile2/', '15e98ed2-b406-45cf-8f7a-a24786394b8d', '2019-12-04 17:22:55', '2019-12-04 17:22:55', '上传者a26', '88888888888888', null, null, '15e98ed2-b406-45cf-8f7a-a24786394b8d_2.PNG');
INSERT INTO `images` VALUES ('17', '2.PNG', 'PNG', '3421', '/user/lxj/input/testfile2/', '2960fa31-aabc-4592-8ba8-9b9d55eaa9e8', '2019-12-04 17:24:45', '2019-12-04 17:24:45', '上传者a26', '88888888888888', null, null, '2960fa31-aabc-4592-8ba8-9b9d55eaa9e8_2.PNG');
INSERT INTO `images` VALUES ('18', '2.PNG', 'PNG', '3421', '/user/lxj/input/testfile2/', '0b44d310-cf6b-47a8-b3ac-9d3f3d4e8459', '2019-12-04 17:27:05', '2019-12-04 17:27:05', '上传者b2', '44444444', null, null, '0b44d310-cf6b-47a8-b3ac-9d3f3d4e8459_2.PNG');
INSERT INTO `images` VALUES ('19', '1.PNG', 'PNG', '13794', '/user/lxj/input/testfile2/', 'de7995c5-0b5d-409b-ba79-e03a284831f6', '2019-12-04 17:27:55', '2019-12-04 17:27:55', '上传者a264444', '5555555555', null, null, 'de7995c5-0b5d-409b-ba79-e03a284831f6_1.PNG');
INSERT INTO `images` VALUES ('20', '2.PNG', 'PNG', '3421', '/user/lxj/input/testfile2/', 'bd3a2ce2-1c1f-4ddd-8e4f-84c6f4a1e716', '2019-12-04 17:30:32', '2019-12-04 17:30:32', '上传者a26444466666666666', '44', null, null, 'bd3a2ce2-1c1f-4ddd-8e4f-84c6f4a1e716_2.PNG');
INSERT INTO `images` VALUES ('21', '2.PNG', 'PNG', '3421', '/user/lxj/input/testfile2/', 'c30a90eb-0acc-4b73-935d-ec52976e45fb', '2019-12-04 17:31:45', '2019-12-04 17:31:45', '上传者a26444466666666666', '555511', null, null, 'c30a90eb-0acc-4b73-935d-ec52976e45fb_2.PNG');

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
  `ID` int(10) NOT NULL DEFAULT '0' COMMENT '视频ID',
  `TITLE` varchar(50) DEFAULT NULL COMMENT '视频名称',
  `NEW_TITLE` varchar(50) DEFAULT NULL COMMENT '视频重命名',
  `TYPE` varchar(10) DEFAULT NULL COMMENT '视频类型',
  `SIZE` int(50) DEFAULT NULL COMMENT '视频大小',
  `POSITION` varchar(50) DEFAULT NULL COMMENT '视频位置',
  `UUID` varchar(30) DEFAULT NULL COMMENT '视频唯一ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `UPLOADER` varchar(20) DEFAULT NULL COMMENT '视频上传者',
  `DESCRIPTION` varchar(20) DEFAULT NULL COMMENT '表注释',
  `REMARK` varchar(20) DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(20) DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(20) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='视频相关';

-- ----------------------------
-- Records of videos
-- ----------------------------
INSERT INTO `videos` VALUES ('1', '视频test2', 'new视频test2', 'avi', '2000', 'd:/abcd/', 'JLJASDF341JJ', '2019-09-28 12:19:09', '2019-09-28 12:19:09', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `videos` VALUES ('2', '视频test1', 'new视频test1', 'mp4', '2000', 'd:/abcd/', 'JASDJKSDFLJ', '2019-09-28 12:19:55', '2019-09-28 12:19:55', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
