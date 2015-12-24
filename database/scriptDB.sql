-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema proyecto_final
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `proyecto_final` ;

-- -----------------------------------------------------
-- Schema proyecto_final
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `proyecto_final` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `proyecto_final` ;

-- -----------------------------------------------------
-- Table `proyecto_final`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto_final`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `paternal_name` VARCHAR(45) NOT NULL,
  `maternal_name` VARCHAR(45) NOT NULL,
  `role_id` INT NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role_idx` (`role_id` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `proyecto_final`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto_final`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(450) NOT NULL,
  `image` VARCHAR(100) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto_final`.`summary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`summary` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(100) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto_final`.`office`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`office` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `street` VARCHAR(250) NOT NULL,
  `int_number` VARCHAR(10) NOT NULL,
  `ext_number` VARCHAR(10) NOT NULL,
  `neighborhood` VARCHAR(250) NOT NULL,
  `city` VARCHAR(250) NOT NULL,
  `state` VARCHAR(150) NOT NULL,
  `zip_code` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto_final`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `summary_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `office_id` INT NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_summary1_idx` (`summary_id` ASC),
  INDEX `fk_order_user1_idx` (`user_id` ASC),
  INDEX `fk_order_office1_idx` (`office_id` ASC),
  CONSTRAINT `fk_order_summary1`
    FOREIGN KEY (`summary_id`)
    REFERENCES `proyecto_final`.`summary` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `proyecto_final`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_office1`
    FOREIGN KEY (`office_id`)
    REFERENCES `proyecto_final`.`office` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyecto_final`.`book_has_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto_final`.`book_has_order` (
  `book_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`book_id`, `order_id`),
  INDEX `fk_book_has_order_order1_idx` (`order_id` ASC),
  INDEX `fk_book_has_order_book1_idx` (`book_id` ASC),
  CONSTRAINT `fk_book_has_order_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `proyecto_final`.`book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_has_order_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `proyecto_final`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
