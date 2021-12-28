
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