SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+08:00";

CREATE TABLE `user` (
  `open_id` int(10) NOT NULL,
  `student_id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nickname` varchar(15) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `reg_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `lesson` (
  `lesson_id` varchar(14) NOT NULL,
  `lesson_name` varchar(255) NOT NULL,
  `credit` int(2) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `school_id` varchar(15) NOT NULL,
  `semester_id` varchar(15) NOT NULL,
  `score` decimal DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL,
  `teacher_name` varchar(255) NOT NULL,
  `school_id` varchar(15) NOT NULL,
  `school_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `tag` (
  `tag_id` varchar(14) NOT NULL,
  `tag` varchar(255) NOT NULL,
  `positive` int(10) DEFAULT 0,
  `negative` int(10) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `goods` (
  `goods_id` int(15) NOT NULL,
  `goods_name` varchar(255) NOT NULL,
  `lesson_id` varchar(14) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `goods_image_id` varchar(255) DEFAULT NULL,
  `goods_detail` varchar(255) DEFAULT NULL,
  `goods_quality` int(1) DEFAULT NULL CHECK (`goods_quality` > 0 and `goods_quality` < 6),
  `deal_method` int(1) DEFAULT NULL CHECK (`deal_method` > 0 and `deal_method` < 6),
  `create_time` datetime NOT NULL,
  `modify_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `topis` (
  `lesson_id` varchar(14) NOT NULL,
  `topic_id` int(14) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `open_id` int(10) NOT NULL,
  `issue_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `post` (
  `post_id` int(14) NOT NULL,
  `topic_id` int(14) NOT NULL,
  `content` varchar(255) NOT NULL,
  `open_id` int(10) NOT NULL,
  `issue_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;