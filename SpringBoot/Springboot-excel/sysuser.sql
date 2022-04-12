/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 04/04/2022 12:52:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                             `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
                             `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
                             `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
                             `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
                             `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
                             `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
                             `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
                             `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
                             `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
                             `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
                             `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
                             `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
                             `real_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '真实姓名',
                             `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '个人描述(用于官网展示)',
                             `per_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人照片',
                             `major_id` bigint(20) NULL DEFAULT NULL COMMENT '专业id',
                             `grade_id` bigint(20) NULL DEFAULT NULL COMMENT '年级id',
                             PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '管理员', '00', 'cl@163.com', '15888888888', '1', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/zhifeng/b24c537d-7342-46ee-9e10-53ae0411b860.png', '{noop}123', '0', '0', '127.0.0.1', '2022-03-28 19:44:08', '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (2, 'manage1', '负责人1', '00', 'cs@163.com', '', '0', '', '{noop}123', '0', '0', '', NULL, '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (3, 'member1', '成员1', '00', 'cs@163.com', '', '0', '', '{noop}123', '0', '0', '', NULL, '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (6, 'changlu', 'changlu', '00', '939974883@qq.com', '17798581602', '1', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/zhifeng/070164fa-03b5-41bb-984a-cf560850f188.png', '{bcrypt}$2a$10$NeteZp.5ReynOqtJ/fKlnOUzjCuZk.NLsJ04jJvz1en1pV.pbhcqi', '0', '0', '', NULL, '茅津菁', '热爱生活，热爱coding...', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/zhifeng/8c7d93a0-d61d-41e1-87ca-3382a78fa9b1.jpg', 2, 1);
INSERT INTO `sys_user` VALUES (8, 'liner', 'liner', '00', '', '', '0', '', '{bcrypt}$2a$10$kPvF9CsJVKeuWU9EmA6WludxT9jXznMguEwlgHSta.NVQCAepZQhW', '0', '0', '', NULL, '', '', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (9, 'liner2', 'liner2', '00', '', '', '0', '', '{bcrypt}$2a$10$j5DHazMD5r/Sh7Wy2.49re0qBpl1bt2IUw7mm3pckomTWCkQ7kt2u', '0', '0', '', NULL, '', '', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10, 'liner3', 'liner3', '00', '', '', '0', '', '{bcrypt}$2a$10$u034wacgH40FmLgNhJBmiuwDnwhQoJuxLO3w0wa4VrfVGBRLm3pCe', '0', '0', '', NULL, '', '', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
