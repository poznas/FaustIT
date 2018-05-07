DROP SCHEMA IF EXISTS `faust-it-db`;

CREATE SCHEMA `faust-it-db`;

use `faust-it-db`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL,
    `password` char(60) NOT NULL,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name` varchar(45) DEFAULT NULL,
    `birth_date` date DEFAULT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY `USERNAME_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `group_table`; 

CREATE TABLE `group_table` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `group_name` varchar(50) NOT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY `NAME_UNIQUE` (`group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_group`;

CREATE TABLE `user_group` (
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`group_id`),
  
  KEY `FK_USER_idx` (`user_id`),
  
  CONSTRAINT `FK_GROUP` FOREIGN KEY (`group_id`) 
  REFERENCES `group_table` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;