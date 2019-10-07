/*
 Navicat Premium Data Transfer

 Source Server         : localhost root&123456789
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : zhulong

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 07/10/2019 20:22:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for all_tables
-- ----------------------------
DROP TABLE IF EXISTS `all_tables`;
CREATE TABLE `all_tables`  (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '表创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '表更新时间',
  `DESCRIPTION` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表注释',
  `REMARK` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据库中各表说明' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of all_tables
-- ----------------------------
INSERT INTO `all_tables` VALUES (1, 'IMAGES', '2019-09-28 11:21:20', '2019-09-28 11:21:22', '图片', '图片表', NULL, NULL);
INSERT INTO `all_tables` VALUES (2, 'VIDEOS', '2019-09-28 11:22:13', '2019-09-28 11:22:15', '视频', '视频表', NULL, NULL);
INSERT INTO `all_tables` VALUES (3, 'SHORT_VIDEOS', '2019-09-28 11:22:55', '2019-09-28 11:22:59', '短视频', '短视频表', NULL, NULL);
INSERT INTO `all_tables` VALUES (4, 'AUDIOS', '2019-09-28 11:23:26', '2019-09-28 11:23:28', '音频', '音频表', NULL, NULL);
INSERT INTO `all_tables` VALUES (5, 'OPERATE_LOGS', '2019-09-28 11:25:01', '2019-09-28 11:25:03', '日志', '日志表', NULL, NULL);
INSERT INTO `all_tables` VALUES (6, 'DOCUMENTS', '2019-09-28 11:25:51', '2019-09-28 11:25:53', '文档', '文档表', NULL, NULL);

-- ----------------------------
-- Table structure for audios
-- ----------------------------
DROP TABLE IF EXISTS `audios`;
CREATE TABLE `audios`  (
  `ID` int(10) NOT NULL DEFAULT 0 COMMENT '音频ID',
  `TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音频名称',
  `NEW_TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音频重命名',
  `TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音频类型',
  `SIZE` int(50) NULL DEFAULT NULL COMMENT '音频大小',
  `POSITION` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音频位置',
  `UUID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音频唯一ID',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `UPLOADER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音频上传者',
  `DESCRIPTION` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表注释',
  `REMARK` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '音频相关' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of audios
-- ----------------------------
INSERT INTO `audios` VALUES (1, '音频test1', 'new音频test1', 'mp3', 2000, 'd:/abcd/', '41654313213', '2019-09-28 12:23:29', '2019-09-28 12:23:29', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `audios` VALUES (2, '音频test2', 'new音频test2', 'wav', 2000, 'd:/abcd/', '41654313213', '2019-09-28 12:23:29', '2019-09-28 12:23:29', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');

-- ----------------------------
-- Table structure for documents
-- ----------------------------
DROP TABLE IF EXISTS `documents`;
CREATE TABLE `documents`  (
  `ID` int(10) NOT NULL DEFAULT 0 COMMENT '文档ID',
  `TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档名称',
  `NEW_TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档重命名',
  `TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档类型',
  `SIZE` int(50) NULL DEFAULT NULL COMMENT '文档大小',
  `POSITION` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档位置',
  `UUID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档唯一ID',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `UPLOADER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档上传者',
  `DESCRIPTION` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表注释',
  `REMARK` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文档相关' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of documents
-- ----------------------------
INSERT INTO `documents` VALUES (1, '文档test1', 'new文档test1', 'pdf', 2000, 'd:/abcd/', '41654313213', '2019-09-28 12:25:26', '2019-09-28 12:25:26', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `documents` VALUES (2, '文档test2', 'new文档test2', 'doc', 2000, 'd:/abcd/', '41654313213', '2019-09-28 12:25:27', '2019-09-28 12:25:27', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images`  (
  `ID` int(10) NOT NULL DEFAULT 0 COMMENT '图片ID',
  `TITLE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `NEW_TITLE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片重命名',
  `TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片类型',
  `SIZE` int(20) NULL DEFAULT NULL COMMENT '图片大小',
  `POSITION` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片位置',
  `UUID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片唯一ID',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `UPLOADER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片上传者',
  `DESCRIPTION` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表注释',
  `REMARK` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图片相关' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES (0, '图片test', 'new图片test', 'jpg', 200, 'd:/abc/', 'JLJASDF341JJ', '2019-09-28 11:36:46', '2019-09-28 11:36:49', '上传者a', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `images` VALUES (1, '图片test2', 'new图片test2', 'png', 20, 'd:/abcd/', 'DANLCSAJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `images` VALUES (3, '图片test3', 'new图片test3', 'png', 20, 'd:/abcd/', 'DANLCSAJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `images` VALUES (4, '图片test4', 'new图片test4', 'png', 20, 'd:/abcd/', 'DANLCSAJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `images` VALUES (5, '图片test5', 'new图片test5', 'png', 20, 'd:/abcd/', 'DANLCSAJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `images` VALUES (6, '图片test6', 'new图片test6', 'png', 20, 'd:/abcd/', 'DANLCSAJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `images` VALUES (7, '图片test77', 'new图片test7', 'png', 20, 'd:/abcd/', 'DANLCSAJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `images` VALUES (8, '图片test8', 'new图片test8', 'png', 20, 'd:/abcd/', 'DANLCSAJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `images` VALUES (9, '图片test9', 'new图片test9', 'png', 20, 'd:/abcd/', 'DANLCSAJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `images` VALUES (10, '图片test10', 'new图片test10', 'png', 20, 'd:/abcd/', 'DANLCSAJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `images` VALUES (11, '图片test11', 'new图片test11', 'png', 20, 'd:/abcd/', 'DANLCSAJDL', '2019-09-28 11:39:32', '2019-09-28 11:39:32', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');

-- ----------------------------
-- Table structure for operate_logs
-- ----------------------------
DROP TABLE IF EXISTS `operate_logs`;
CREATE TABLE `operate_logs`  (
  `ID` int(10) NOT NULL DEFAULT 0 COMMENT '日志ID',
  `TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `NEW_TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志重命名',
  `TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志类型',
  `SIZE` int(50) NULL DEFAULT NULL COMMENT '日志大小',
  `POSITION` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志位置',
  `UUID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志唯一ID',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `UPLOADER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志上传者',
  `DESCRIPTION` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表注释',
  `REMARK` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志相关' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operate_logs
-- ----------------------------
INSERT INTO `operate_logs` VALUES (1, '日志test1', 'new日志test1', 'log', 2000, 'd:/abcd/', '41654313213', '2019-09-28 12:26:34', '2019-09-28 12:26:34', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `operate_logs` VALUES (2, '日志test2', 'new日志test2', 'log', 2000, 'd:/abcd/', '41654313213', '2019-09-28 12:26:34', '2019-09-28 12:26:34', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');

-- ----------------------------
-- Table structure for short_videos
-- ----------------------------
DROP TABLE IF EXISTS `short_videos`;
CREATE TABLE `short_videos`  (
  `ID` int(10) NOT NULL DEFAULT 0 COMMENT '短视频ID',
  `TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短视频名称',
  `NEW_TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短视频重命名',
  `TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短视频类型',
  `SIZE` int(50) NULL DEFAULT NULL COMMENT '短视频大小',
  `POSITION` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短视频位置',
  `UUID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短视频唯一ID',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `UPLOADER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短视频上传者',
  `DESCRIPTION` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表注释',
  `REMARK` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短视频相关' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of short_videos
-- ----------------------------
INSERT INTO `short_videos` VALUES (1, '短视频test1', 'new短视频test1', 'avi', 2000, 'd:/abcd/', '41654313213', '2019-09-28 12:21:53', '2019-09-28 12:21:53', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `short_videos` VALUES (2, '短视频test2', 'new短视频test2', 'avi', 2000, 'd:/abcd/', '41654313213', '2019-09-28 12:21:54', '2019-09-28 12:21:54', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');

-- ----------------------------
-- Table structure for videos
-- ----------------------------
DROP TABLE IF EXISTS `videos`;
CREATE TABLE `videos`  (
  `ID` int(10) NOT NULL DEFAULT 0 COMMENT '视频ID',
  `TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频名称',
  `NEW_TITLE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频重命名',
  `TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频类型',
  `SIZE` int(50) NULL DEFAULT NULL COMMENT '视频大小',
  `POSITION` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频位置',
  `UUID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频唯一ID',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `UPLOADER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频上传者',
  `DESCRIPTION` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表注释',
  `REMARK` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表备注',
  `NOTE1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '视频相关' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of videos
-- ----------------------------
INSERT INTO `videos` VALUES (1, '视频test2', 'new视频test2', 'avi', 2000, 'd:/abcd/', 'JLJASDF341JJ', '2019-09-28 12:19:09', '2019-09-28 12:19:09', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');
INSERT INTO `videos` VALUES (2, '视频test1', 'new视频test1', 'mp4', 2000, 'd:/abcd/', 'JASDJKSDFLJ', '2019-09-28 12:19:55', '2019-09-28 12:19:55', '上传者b', '注释', ' 备注', '扩展字段1', ' 扩展字段2');

SET FOREIGN_KEY_CHECKS = 1;
