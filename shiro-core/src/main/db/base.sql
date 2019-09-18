/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : localhost:3306
 Source Schema         : project_manager

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 18/09/2019 16:12:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cli_staff_project
-- ----------------------------
DROP TABLE IF EXISTS `cli_staff_project`;
CREATE TABLE `cli_staff_project`  (
                                    `client_id` int(10) DEFAULT NULL COMMENT '客户id',
                                    `staff_id` int(10) DEFAULT NULL COMMENT '员工id',
                                    `project_id` int(10) DEFAULT NULL COMMENT '项目id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户-员工-项目表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
                             `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `dict_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典名',
                             `dict_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典类型/代码',
                             `dict_value` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典值',
                             `dict_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典描述',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for log_info
-- ----------------------------
DROP TABLE IF EXISTS `log_info`;
CREATE TABLE `log_info`  (
                           `id` int(8) NOT NULL AUTO_INCREMENT COMMENT 'id',
                           `user_id` int(8) DEFAULT NULL COMMENT '操作者id',
                           `result_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '结果状态(1成功,0失败)',
                           `log_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志类型',
                           `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
                           `ip_address` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ip地址',
                           `operatordesc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作描述',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
                             `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `per_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限名',
                             `per_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限路径',
                             `type` int(2) DEFAULT NULL COMMENT '资源类型（0：菜单，1：按钮）',
                             `parent_id` int(10) DEFAULT NULL COMMENT '父级id',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
                          `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
                          `pro_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项目名称',
                          `pro_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '项目描述',
                          `start_time` bigint(13) DEFAULT NULL COMMENT '开发开始时间',
                          `end_time` bigint(13) DEFAULT NULL COMMENT '开发结束时间',
                          `rel_end_time` bigint(13) DEFAULT NULL COMMENT '实际开发结束时间',
                          `over_time_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '开发超时完成说明',
                          `schedule` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项目开发进度',
                          `parent_id` int(10) DEFAULT NULL COMMENT '父级模块id',
                          `client_status` int(2) DEFAULT NULL COMMENT '客户验收状态',
                          `staff_status` int(2) DEFAULT NULL COMMENT '项目负责人验收状态',
                          `status` int(2) DEFAULT NULL COMMENT '状态',
                          `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
                          `create_opr` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
                          `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
                          `update_opr` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for project_log
-- ----------------------------
DROP TABLE IF EXISTS `project_log`;
CREATE TABLE `project_log`  (
                              `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `project_id` int(10) DEFAULT NULL COMMENT '项目id',
                              `staff_id` int(10) DEFAULT NULL COMMENT '员工id',
                              `problem` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '问题描述',
                              `status` int(2) DEFAULT NULL COMMENT '状态',
                              `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
                              `create_opr` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
                              `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
                              `update_opr` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目-日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
                       `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
                       `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名',
                       `role_desc` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色描述',
                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
                                  `role_id` int(10) DEFAULT NULL COMMENT '角色id',
                                  `per_id` int(10) DEFAULT NULL COMMENT '权限id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for staff_client
-- ----------------------------
DROP TABLE IF EXISTS `staff_client`;
CREATE TABLE `staff_client`  (
                               `staff_id` int(10) DEFAULT NULL COMMENT '员工id',
                               `client_id` int(10) DEFAULT NULL COMMENT '客户id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '员工-客户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                       `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
                       `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名/账号',
                       `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
                       `rel_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实姓名',
                       `job_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工号',
                       `sex` int(2) DEFAULT NULL COMMENT '性别',
                       `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
                       `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
                       `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系地址',
                       `type` int(2) DEFAULT 1 COMMENT '是否客户（0：否，1：是，默认1）',
                       `status` int(2) DEFAULT 1 COMMENT '状态',
                       `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
                       `create_opr` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
                       `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
                       `update_opr` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '928bfd2577490322a6e19b793691467e', '超级管理员', '001', 1, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
                            `user_id` int(10) NOT NULL COMMENT '用户id',
                            `role_id` int(10) DEFAULT NULL COMMENT '角色id',
                            PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-角色表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
