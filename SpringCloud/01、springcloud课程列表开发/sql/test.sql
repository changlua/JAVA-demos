/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 05/10/2021 10:51:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL,
  `course_id` int(11) NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `valid` int(1) NULL DEFAULT NULL COMMENT '是否上架，0不上架，1上架\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 362, 'Java并发核心知识体系靖江', 1);
INSERT INTO `course` VALUES (2, 409, '玩转Java并发工具，精通JUC', 1);
INSERT INTO `course` VALUES (3, 121, 'Nginx入门到实践', 0);

-- ----------------------------
-- Table structure for course_price
-- ----------------------------
DROP TABLE IF EXISTS `course_price`;
CREATE TABLE `course_price`  (
  `id` int(11) NOT NULL,
  `course_id` int(11) NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_price
-- ----------------------------
INSERT INTO `course_price` VALUES (1, 362, 348.50);
INSERT INTO `course_price` VALUES (2, 409, 399.68);
INSERT INTO `course_price` VALUES (3, 121, 266.78);

SET FOREIGN_KEY_CHECKS = 1;
