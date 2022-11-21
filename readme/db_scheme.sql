-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ssafyfestival
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ssafyfestival` ;

-- -----------------------------------------------------
-- Schema ssafyfestival
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafyfestival` DEFAULT CHARACTER SET utf8 ;
USE `ssafyfestival` ;

-- -----------------------------------------------------
-- Table `ssafyfestival`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyfestival`.`user` ;

CREATE TABLE IF NOT EXISTS `ssafyfestival`.`user` (
  `id` VARCHAR(100) NOT NULL,
  `pw` VARCHAR(100) NULL,
  `name` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `phone_number` VARCHAR(45) NULL,
  `token` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyfestival`.`festival`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyfestival`.`festival` ;

CREATE TABLE IF NOT EXISTS `ssafyfestival`.`festival` (
  `festival_id` VARCHAR(100) NOT NULL,
  `festival_name` VARCHAR(1000) NULL,
  `festival_content` VARCHAR(2000) NULL,
  `start_date` TIMESTAMP NULL,
  `end_date` TIMESTAMP NULL,
  `phone_number` VARCHAR(1000) NULL,
  `homepage` VARCHAR(1000) NULL,
  `lat` VARCHAR(45) NULL,
  `lng` VARCHAR(45) NULL,
  `address` VARCHAR(1000) NULL,
  `origin_image` VARCHAR(1000) NULL,
  `thumb_image` VARCHAR(1000) NULL,
  `org_name` VARCHAR(1000) NULL,
  PRIMARY KEY (`festival_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyfestival`.`favor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyfestival`.`favor` ;

CREATE TABLE IF NOT EXISTS `ssafyfestival`.`favor` (
  `user_id` VARCHAR(100) NOT NULL,
  `festival_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`user_id`, `festival_id`),
  INDEX `favor_festival_id_idx` (`festival_id` ASC) VISIBLE,
  CONSTRAINT `favor_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafyfestival`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `favor_festival_id`
    FOREIGN KEY (`festival_id`)
    REFERENCES `ssafyfestival`.`festival` (`festival_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyfestival`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyfestival`.`board` ;

CREATE TABLE IF NOT EXISTS `ssafyfestival`.`board` (
  `board_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `content` VARCHAR(2000) NULL,
  `regist_date` TIMESTAMP NULL,
  `user_id` VARCHAR(100) NOT NULL,
  `festival_id` VARCHAR(100) NOT NULL,
  `cate` VARCHAR(45) NULL,
  `file_ori_name` VARCHAR(1000) NULL,
  `file_path` VARCHAR(4000) NULL,
  `file_save_name` VARCHAR(1000) NULL,
  PRIMARY KEY (`board_id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `board_festival_id_idx` (`festival_id` ASC) VISIBLE,
  CONSTRAINT `board_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafyfestival`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `board_festival_id`
    FOREIGN KEY (`festival_id`)
    REFERENCES `ssafyfestival`.`festival` (`festival_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyfestival`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyfestival`.`comment` ;

CREATE TABLE IF NOT EXISTS `ssafyfestival`.`comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `board_id` INT NOT NULL,
  `user_id` VARCHAR(100) NOT NULL,
  `content` VARCHAR(1000) NULL,
  `regist_date` TIMESTAMP NULL,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `board_id_idx` (`board_id` ASC) VISIBLE,
  PRIMARY KEY (`comment_id`),
  CONSTRAINT `com_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafyfestival`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `com_board_id`
    FOREIGN KEY (`board_id`)
    REFERENCES `ssafyfestival`.`board` (`board_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
