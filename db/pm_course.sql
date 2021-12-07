/*
 Navicat Premium Data Transfer

 Source Server         : DoughIt
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : pm_course

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 07/12/2021 13:46:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `tab_commodity`;
CREATE TABLE tab_commodity
(
    id               bigint unsigned auto_increment                          NOT NULL COMMENT '自增主键',
    name             varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
    lesson_id        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
    teacher_id       bigint unsigned                                         NULL,
    seller_id        bigint unsigned                                         NULL,
    `type`           bigint                                                  NULL,
    author           varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
    publisher        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
    cover_percentage varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
    image_id         varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
    content          varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
    price            double                                                  NULL,
    single_print     tinyint(1)                                              NULL,
    deal_method      int                                                     NULL,
    create_time      datetime                                                NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COLLATE = utf8_general_ci
    COMMENT ='';
-- ----------------------------
-- Table structure for tab_message
-- ----------------------------
DROP TABLE IF EXISTS `tab_message`;
CREATE TABLE `tab_message`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `sender_id`   bigint(20) unsigned DEFAULT NULL COMMENT '发信人id',
    `receiver_id` bigint(20) unsigned DEFAULT NULL COMMENT '收信人id',
    `content`     varchar(255)        DEFAULT NULL COMMENT '内容',
    `issue_time`  datetime            DEFAULT CURRENT_TIMESTAMP COMMENT '发信时间',
    `read_time`   datetime            DEFAULT NULL COMMENT '收信时间',
    `read`        bit(1)              DEFAULT b'0' COMMENT '是否已读，0->未读，1->已读',
    PRIMARY KEY (`id`),
    KEY `sender_id` (`sender_id`),
    KEY `receiver_id` (`receiver_id`),
    CONSTRAINT `tab_message_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `tab_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT `tab_message_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `tab_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of tab_message
-- ----------------------------
BEGIN;
INSERT INTO `tab_message`
VALUES (1, 1, 2, 'ddd', '2021-12-07 10:26:26', '2021-12-07 10:48:10', b'1');
COMMIT;

-- ----------------------------
-- Table structure for tab_topic
-- ----------------------------
DROP TABLE IF EXISTS `tab_topic`;
CREATE TABLE `tab_topic`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `parent_id`    bigint(20)          DEFAULT NULL COMMENT '父贴id',
    `lesson_id`    bigint(20)          DEFAULT NULL,
    `goods_id`     bigint(20)          DEFAULT NULL,
    `related_type` int(11)             DEFAULT '0' COMMENT '帖子所属模块，0->公共模块，1->课程模块，2->商品模块',
    `user_id`      bigint(20) unsigned DEFAULT NULL COMMENT '发帖人',
    `title`        varchar(255)        DEFAULT NULL COMMENT '标题',
    `content`      varchar(255)        DEFAULT NULL COMMENT '内容',
    `issue_time`   datetime            DEFAULT CURRENT_TIMESTAMP COMMENT '发帖时间',
    `state`        int(11)             DEFAULT '1' COMMENT '帖子状态，0->正常，1->已删除，2->已屏蔽',
    PRIMARY KEY (`id`),
    KEY `tab_topic_ibfk_1` (`user_id`),
    CONSTRAINT `tab_topic_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tab_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of tab_topic
-- ----------------------------
BEGIN;
INSERT INTO `tab_topic`
VALUES (1, NULL, NULL, 222, 2, 1, 'hh', 'balabala', '2021-12-07 10:52:51', 1);
COMMIT;

-- ----------------------------
-- Table structure for tab_user
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user`
(
    `id`            bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `student_id`    varchar(64)                  DEFAULT NULL COMMENT '学号',
    `open_id`       varchar(64)                  DEFAULT NULL COMMENT 'wechat小程序用户id',
    `username`      varchar(64)                  DEFAULT NULL COMMENT '昵称',
    `password`      varchar(255)                 DEFAULT NULL COMMENT '密码',
    `avatar`        varchar(128)                 DEFAULT NULL COMMENT '头像URL',
    `phone`         varchar(32)                  DEFAULT NULL COMMENT '手机号',
    `description`   varchar(255)                 DEFAULT NULL COMMENT '简介',
    `register_time` datetime                     DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    `login_time`    datetime                     DEFAULT NULL COMMENT '上次登录时间',
    `unthorized`    int(11)             NOT NULL DEFAULT '0' COMMENT '学邮认证情况，0未认证，1认证成功，2等待确认',
    `status`        bit(1)              NOT NULL DEFAULT b'1' COMMENT '账号状态，0禁用，1启用',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of tab_user
-- ----------------------------
BEGIN;
INSERT INTO `tab_user`
VALUES (1, '', 'AzJHedakj8d2dfDf', '嘿哈', NULL, 'https://cdn.pixabay.com/photo/2021/01/25/22/45/leaves-5949884_1280.png',
        NULL, '这是测试号', '2021-11-30 09:47:54', NULL, 1, b'1');
INSERT INTO `tab_user`
VALUES (2, '16302010059', NULL, NULL, '$2a$10$JUKqK0FYovN7K8/1ehGiX.43O3iGsYUqY9I0mWsl/GhFdOJWIgZ4G', NULL, NULL, NULL,
        '2021-12-05 11:09:03', NULL, 0, b'1');
COMMIT;

-- ----------------------------
-- Triggers structure for table tab_message
-- ----------------------------
DROP TRIGGER IF EXISTS `update_read_status`;
delimiter ;;
CREATE TRIGGER `update_read_status`
    BEFORE UPDATE
    ON `tab_message`
    FOR EACH ROW IF NEW.read_time IS NOT NULL THEN
    SET NEW.read = 1;
ELSE
    SET NEW.read = 0;
END IF
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table tab_topic
-- ----------------------------
DROP TRIGGER IF EXISTS `insert_message`;
delimiter ;;
CREATE TRIGGER `insert_message`
    BEFORE INSERT
    ON `tab_topic`
    FOR EACH ROW IF NEW.lesson_id IS NOT NULL THEN
    SET NEW.related_type = 1;
ELSEIF NEW.goods_id IS NOT NULL THEN
    SET NEW.related_type = 2;
ELSE
    SET NEW.related_type = 0;
END IF
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table tab_topic
-- ----------------------------
DROP TRIGGER IF EXISTS `update_message`;
delimiter ;;
CREATE TRIGGER `update_message`
    BEFORE UPDATE
    ON `tab_topic`
    FOR EACH ROW IF NEW.lesson_id IS NOT NULL THEN
    SET NEW.related_type = 1;
ELSEIF NEW.goods_id IS NOT NULL THEN
    SET NEW.related_type = 2;
ELSE
    SET NEW.related_type = 0;
END IF
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
