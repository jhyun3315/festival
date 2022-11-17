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
-- Table `ssafyfestival`.`favor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyfestival`.`favor` (
  `user_id` VARCHAR(100) NOT NULL,
  `festival_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`user_id`, `festival_id`),
  CONSTRAINT `favor_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafyfestival`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyfestival`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyfestival`.`board` (
  `board_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `content` VARCHAR(2000) NULL,
  `regist_date` TIMESTAMP NULL,
  `user_id` VARCHAR(100) NULL,
  `festival_id` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`board_id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `board_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafyfestival`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyfestival`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyfestival`.`comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `board_id` INT NULL,
  `user_id` VARCHAR(100) NULL,
  `content` VARCHAR(1000) NULL,
  `regist_date` TIMESTAMP NULL,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `board_id_idx` (`board_id` ASC) VISIBLE,
  PRIMARY KEY (`comment_id`),
  CONSTRAINT `com_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafyfestival`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `com_board_id`
    FOREIGN KEY (`board_id`)
    REFERENCES `ssafyfestival`.`board` (`board_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
