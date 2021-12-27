/*
 Navicat Premium Data Transfer

 Source Server         : DoughIt
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : bbs_mall

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 27/12/2021 15:45:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tab_commodity
-- ----------------------------
DROP TABLE IF EXISTS `tab_commodity`;
CREATE TABLE `tab_commodity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) DEFAULT NULL,
  `lesson_id` varchar(100) DEFAULT NULL,
  `teacher_id` bigint(20) unsigned DEFAULT NULL,
  `seller_id` bigint(20) unsigned DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `cover_percentage` varchar(100) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `single_print` tinyint(1) DEFAULT NULL,
  `deal_method` int(11) DEFAULT NULL,
  `commodity_id` bigint(20) unsigned DEFAULT NULL,
  `chapters` int(11) DEFAULT NULL,
  `paper_size` varchar(100) DEFAULT NULL,
  `new_degree` varchar(100) DEFAULT NULL,
  `unit` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `image_url` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `seller_id` (`seller_id`),
  CONSTRAINT `tab_commodity_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `tab_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_commodity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tab_favorite
-- ----------------------------
DROP TABLE IF EXISTS `tab_favorite`;
CREATE TABLE `tab_favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id` bigint(20) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tab_favorite_ibfk_1` (`user_id`),
  CONSTRAINT `tab_favorite_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tab_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_favorite
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tab_favorite_lesson
-- ----------------------------
DROP TABLE IF EXISTS `tab_favorite_lesson`;
CREATE TABLE `tab_favorite_lesson` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `lesson_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tab_favorite_lesson_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tab_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tab_favorite_lesson
-- ----------------------------
BEGIN;
INSERT INTO `tab_favorite_lesson` VALUES (2, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for tab_lesson
-- ----------------------------
DROP TABLE IF EXISTS `tab_lesson`;
CREATE TABLE `tab_lesson` (
  `lesson_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lesson_number` varchar(14) NOT NULL,
  `lesson_name` varchar(255) NOT NULL,
  `credit` decimal(10,0) NOT NULL,
  `teacher_id` bigint(20) NOT NULL,
  `school_id` bigint(20) NOT NULL,
  `semester` varchar(15) NOT NULL,
  `score` decimal(10,0) DEFAULT '0',
  PRIMARY KEY (`lesson_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tab_lesson
-- ----------------------------
BEGIN;
INSERT INTO `tab_lesson` VALUES (1, 'SOFT110012.01', '严肃游戏应用与设计', 2, 1, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (2, 'SOFT110014.01', '计算思维与信息素养', 3, 2, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (3, 'SOFT130007.01', '概率统计', 3, 3, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (4, 'SOFT130010.01', '项目管理', 3, 4, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (5, 'SOFT130010h.01', '项目管理（H）', 4, 4, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (6, 'SOFT130011.01', '计算机前沿讲座(上)', 0, 5, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (7, 'SOFT130013.01', '专业实践与生产实习(上)', 3, 1, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (8, 'SOFT130015.01', '数据库设计', 3, 7, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (9, 'SOFT130017.01', '面向对象分析和设计', 3, 8, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (10, 'SOFT130021.01', '数字部件设计', 4, 9, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (11, 'SOFT130023.01', '计算机网络', 3, 10, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (12, 'SOFT130039.01', '离散数学(上)', 3, 11, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (13, 'SOFT130047.01', '商务智能', 2, 12, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (14, 'SOFT130049.01', '智能系统原理与开发', 4, 13, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (15, 'SOFT130051.01', '多媒体技术基础', 3, 14, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (16, 'SOFT130058.01', '计算机系统工程', 4, 15, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (17, 'SOFT130058h.01', '计算机系统工程（H）', 5, 16, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (18, 'SOFT130061.01', '编译原理', 3, 17, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (19, 'SOFT130063.01', '人机交互', 2, 18, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (20, 'SOFT130068.01', '管理信息系统', 2, 19, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (21, 'SOFT130070.01', '并行计算与性能优化', 2, 20, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (22, 'SOFT130072.01', '软件需求工程', 2, 6, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (23, 'SOFT130075.01', '工程能力素养', 1, 1, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (24, 'SOFT130077.01', '系统安全攻防技术', 2, 21, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (25, 'SOFT130082.01', '分布式系统概论', 2, 16, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (26, 'SOFT130083.01', '大数据核心技术与应用', 2, 12, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (27, 'SOFT130084.01', '大数据系统软件实践', 3, 22, 1, '秋季学期', 0);
INSERT INTO `tab_lesson` VALUES (28, 'SOFT130088.01', '工业互联网软件技术', 3, 23, 1, '秋季学期', 0);
COMMIT;

-- ----------------------------
-- Table structure for tab_lesson_popularity
-- ----------------------------
DROP TABLE IF EXISTS `tab_lesson_popularity`;
CREATE TABLE `tab_lesson_popularity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lesson_id` bigint(20) NOT NULL,
  `popularity` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tab_lesson_popularity
-- ----------------------------
BEGIN;
INSERT INTO `tab_lesson_popularity` VALUES (1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for tab_lesson_tag
-- ----------------------------
DROP TABLE IF EXISTS `tab_lesson_tag`;
CREATE TABLE `tab_lesson_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(20) NOT NULL,
  `lesson_id` bigint(20) NOT NULL,
  `positive` bigint(20) DEFAULT '0',
  `negative` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tab_lesson_tag
-- ----------------------------
BEGIN;
INSERT INTO `tab_lesson_tag` VALUES (1, 1, 1, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for tab_lesson_user_tag
-- ----------------------------
DROP TABLE IF EXISTS `tab_lesson_user_tag`;
CREATE TABLE `tab_lesson_user_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(20) NOT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `lesson_id` bigint(20) NOT NULL,
  `positiveSelected` tinyint(1) DEFAULT '0',
  `negetiveSelected` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tab_lesson_user_tag_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tab_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tab_lesson_user_tag
-- ----------------------------
BEGIN;
INSERT INTO `tab_lesson_user_tag` VALUES (1, 1, 1, 1, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for tab_message
-- ----------------------------
DROP TABLE IF EXISTS `tab_message`;
CREATE TABLE `tab_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sender_id` bigint(20) unsigned DEFAULT NULL COMMENT '发信人id',
  `receiver_id` bigint(20) unsigned DEFAULT NULL COMMENT '收信人id',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `issue_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发信时间',
  `read_time` datetime DEFAULT NULL COMMENT '收信时间',
  `read_status` bit(1) DEFAULT b'0' COMMENT '是否已读，0->未读，1->已读',
  PRIMARY KEY (`id`),
  KEY `tab_message_ibfk_1` (`sender_id`),
  KEY `tab_message_ibfk_2` (`receiver_id`),
  CONSTRAINT `tab_message_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `tab_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `tab_message_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `tab_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_message
-- ----------------------------
BEGIN;
INSERT INTO `tab_message` VALUES (1, 2, 1, 'ddd', '2021-12-07 10:26:26', '2021-12-07 10:48:10', b'1');
INSERT INTO `tab_message` VALUES (2, 1, 2, 'fdadf', '2021-12-07 23:21:45', NULL, b'0');
INSERT INTO `tab_message` VALUES (3, 1, 2, '阿凡达舒服', '2021-12-15 22:32:35', NULL, b'0');
COMMIT;

-- ----------------------------
-- Table structure for tab_mini_program
-- ----------------------------
DROP TABLE IF EXISTS `tab_mini_program`;
CREATE TABLE `tab_mini_program` (
  `app_id` varchar(255) NOT NULL,
  `app_secret` varchar(255) NOT NULL,
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_mini_program
-- ----------------------------
BEGIN;
INSERT INTO `tab_mini_program` VALUES ('wx15a76872afe18c08', '75327653f424be686e42e522cc4e001a');
COMMIT;

-- ----------------------------
-- Table structure for tab_school
-- ----------------------------
DROP TABLE IF EXISTS `tab_school`;
CREATE TABLE `tab_school` (
  `school_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `school_name` varchar(255) NOT NULL,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tab_school
-- ----------------------------
BEGIN;
INSERT INTO `tab_school` VALUES (1, '软件学院');
COMMIT;

-- ----------------------------
-- Table structure for tab_sold_commodity
-- ----------------------------
DROP TABLE IF EXISTS `tab_sold_commodity`;
CREATE TABLE `tab_sold_commodity` (
  `sold_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `lesson_id` varchar(100) DEFAULT NULL,
  `teacher_id` bigint(20) unsigned DEFAULT NULL,
  `seller_id` bigint(20) unsigned DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `cover_percentage` varchar(100) DEFAULT NULL,
  `image_id` varchar(100) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `single_print` tinyint(1) DEFAULT NULL,
  `deal_method` int(11) DEFAULT NULL,
  `commodity_id` bigint(20) unsigned DEFAULT NULL,
  `chapters` int(11) DEFAULT NULL,
  `paper_size` varchar(100) DEFAULT NULL,
  `new_degree` varchar(100) DEFAULT NULL,
  `unit` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `picture` blob,
  `id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`sold_id`),
  KEY `seller_id` (`seller_id`),
  CONSTRAINT `tab_sold_commodity_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `tab_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_sold_commodity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tab_tag
-- ----------------------------
DROP TABLE IF EXISTS `tab_tag`;
CREATE TABLE `tab_tag` (
  `tag_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag` varchar(255) NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tab_tag
-- ----------------------------
BEGIN;
INSERT INTO `tab_tag` VALUES (1, '轻松');
COMMIT;

-- ----------------------------
-- Table structure for tab_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tab_teacher`;
CREATE TABLE `tab_teacher` (
  `teacher_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(255) NOT NULL,
  `school_id` bigint(20) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tab_teacher
-- ----------------------------
BEGIN;
INSERT INTO `tab_teacher` VALUES (1, '徐迎晓', 1);
INSERT INTO `tab_teacher` VALUES (2, '戴开宇', 1);
INSERT INTO `tab_teacher` VALUES (3, '金玲飞', 1);
INSERT INTO `tab_teacher` VALUES (4, '高晓桐', 1);
INSERT INTO `tab_teacher` VALUES (5, '吴杰', 1);
INSERT INTO `tab_teacher` VALUES (6, '沈立炜', 1);
INSERT INTO `tab_teacher` VALUES (7, '吴毅坚', 1);
INSERT INTO `tab_teacher` VALUES (8, '张天戈', 1);
INSERT INTO `tab_teacher` VALUES (9, '张睿', 1);
INSERT INTO `tab_teacher` VALUES (10, '李景涛', 1);
INSERT INTO `tab_teacher` VALUES (11, '赵一鸣', 1);
INSERT INTO `tab_teacher` VALUES (12, '赵卫东', 1);
INSERT INTO `tab_teacher` VALUES (13, '郑骁庆', 1);
INSERT INTO `tab_teacher` VALUES (14, '刘新', 1);
INSERT INTO `tab_teacher` VALUES (15, '李旻', 1);
INSERT INTO `tab_teacher` VALUES (16, '冯红伟', 1);
INSERT INTO `tab_teacher` VALUES (17, '徐辉', 1);
INSERT INTO `tab_teacher` VALUES (18, '姜忠鼎', 1);
INSERT INTO `tab_teacher` VALUES (19, '朱东来', 1);
INSERT INTO `tab_teacher` VALUES (20, '唐渊', 1);
INSERT INTO `tab_teacher` VALUES (21, '杨珉', 1);
INSERT INTO `tab_teacher` VALUES (22, '李弋', 1);
INSERT INTO `tab_teacher` VALUES (23, '李敏波', 1);
COMMIT;

-- ----------------------------
-- Table structure for tab_topic
-- ----------------------------
DROP TABLE IF EXISTS `tab_topic`;
CREATE TABLE `tab_topic` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父贴id',
  `lesson_id` bigint(20) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  `related_type` int(11) DEFAULT '0' COMMENT '帖子所属模块，0->公共模块，1->课程模块，2->商品模块',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '发帖人',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `issue_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发帖时间',
  `state` int(11) DEFAULT '1' COMMENT '帖子状态，0->正常，1->已删除，2->已屏蔽',
  PRIMARY KEY (`id`),
  KEY `tab_topic_ibfk_1` (`user_id`),
  CONSTRAINT `tab_topic_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tab_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_topic
-- ----------------------------
BEGIN;
INSERT INTO `tab_topic` VALUES (1, NULL, NULL, 222, 2, 2, 'hh', 'balabala', '2021-12-07 10:52:51', 1);
INSERT INTO `tab_topic` VALUES (2, 1, NULL, 222, 2, 3, NULL, 'dfadfda', '2021-12-07 22:18:21', 1);
INSERT INTO `tab_topic` VALUES (3, 2, NULL, 222, 2, 3, NULL, NULL, '2021-12-07 22:33:39', 1);
INSERT INTO `tab_topic` VALUES (4, 2, NULL, NULL, 0, 1, NULL, 'balabala', NULL, NULL);
INSERT INTO `tab_topic` VALUES (5, NULL, 22, NULL, 1, 1, NULL, 'balabala', NULL, NULL);
INSERT INTO `tab_topic` VALUES (6, NULL, NULL, NULL, 0, 1, NULL, 'balabala', NULL, NULL);
INSERT INTO `tab_topic` VALUES (7, NULL, NULL, 123, 2, 1, NULL, 'balabala', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tab_user
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `student_id` varchar(64) DEFAULT NULL COMMENT '学号',
  `open_id` varchar(64) DEFAULT NULL COMMENT 'wechat小程序用户id',
  `username` varchar(64) DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `avatar` varchar(1024) DEFAULT NULL COMMENT '头像URL',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `unthorized` int(11) NOT NULL DEFAULT '0' COMMENT '学邮认证情况，0未认证，1认证成功，2等待确认',
  `status` bit(1) NOT NULL DEFAULT b'1' COMMENT '账号状态，0禁用，1启用',
  `nav` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '多账号时指向主账号',
  PRIMARY KEY (`id`,`nav`) USING BTREE,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_user
-- ----------------------------
BEGIN;
INSERT INTO `tab_user` VALUES (1, '16302010059', NULL, NULL, '$2a$10$nLpdFqP44VT0ZOqPBWNYFe3uXdy0umeX5uuNfsk7B5lmDCbxbBGIm', 'https://doughit.oss-cn-shanghai.aliyuncs.com/bbs/images/20211216/工具.png', NULL, NULL, '2021-12-10 18:08:39', NULL, 1, b'1', 0);
INSERT INTO `tab_user` VALUES (2, NULL, 'doughit', NULL, '$2a$10$nLpdFqP44VT0ZOqPBWNYFe3uXdy0umeX5uuNfsk7B5lmDCbxbBGIm', NULL, NULL, NULL, '2021-12-10 18:09:52', '2021-12-10 18:10:26', 0, b'1', 0);
INSERT INTO `tab_user` VALUES (3, '16302010010', NULL, NULL, '$2a$10$bt3CvXkjEFQrq6tO2oGzlO0AHYBZK/Wvh7cx8BzhrYrzKlaoURI4S', NULL, NULL, NULL, '2021-12-13 22:09:13', '2021-12-14 08:26:15', 1, b'1', 0);
INSERT INTO `tab_user` VALUES (4, NULL, NULL, NULL, '$2a$10$n1EcPdIPPV3pMkx52uvf0exM9RSto0Vx01IExaxAtxiDHlDKa45Wa', NULL, NULL, NULL, '2021-12-27 15:29:19', '2021-12-27 15:29:19', 0, b'1', 0);
INSERT INTO `tab_user` VALUES (5, NULL, NULL, NULL, '$2a$10$KdEv4pUIbFuGb4UFY0ojqu9MqFcnwcrvAPwc/CuQwEQyDK16mdi8G', NULL, NULL, NULL, '2021-12-27 15:32:53', '2021-12-27 15:32:53', 0, b'1', 0);
COMMIT;

-- ----------------------------
-- Triggers structure for table tab_message
-- ----------------------------
DROP TRIGGER IF EXISTS `update_read_status`;
delimiter ;;
CREATE TRIGGER `update_read_status` BEFORE UPDATE ON `tab_message` FOR EACH ROW IF NEW.read_time IS NOT NULL THEN
SET NEW.read_status=1;
ELSE
SET NEW.read_status=0;
END IF
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table tab_topic
-- ----------------------------
DROP TRIGGER IF EXISTS `insert_message`;
delimiter ;;
CREATE TRIGGER `insert_message` BEFORE INSERT ON `tab_topic` FOR EACH ROW IF NEW.lesson_id IS NOT NULL THEN
SET NEW.related_type=1;
ELSEIF NEW.goods_id IS NOT NULL THEN
SET NEW.related_type=2;
ELSE
SET NEW.related_type=0;
END IF
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table tab_topic
-- ----------------------------
DROP TRIGGER IF EXISTS `update_message`;
delimiter ;;
CREATE TRIGGER `update_message` BEFORE UPDATE ON `tab_topic` FOR EACH ROW IF NEW.lesson_id IS NOT NULL THEN
SET NEW.related_type=1;
ELSEIF NEW.goods_id IS NOT NULL THEN
SET NEW.related_type=2;
ELSE
SET NEW.related_type=0;
END IF
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table tab_user
-- ----------------------------
DROP TRIGGER IF EXISTS `merge_user`;
delimiter ;;
CREATE TRIGGER `merge_user` BEFORE UPDATE ON `tab_user` FOR EACH ROW if old.student_id is null and new.student_id is not null then
set new.nav = (select tab_user.id from tab_user where tab_user.student_id=new.student_id LIMIT 1);
end if
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
