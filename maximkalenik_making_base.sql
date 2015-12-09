-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema maximkalenik
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema maximkalenik
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `maximkalenik` DEFAULT CHARACTER SET utf8 ;
USE `maximkalenik` ;

-- -----------------------------------------------------
-- Table `maximkalenik`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `maximkalenik`.`contact` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `patronymic` VARCHAR(45) NULL DEFAULT NULL,
  `date_of_birth` DATE NULL DEFAULT NULL,
  `sex_is_male` TINYINT(1) NULL DEFAULT b'1',
  `nationality` VARCHAR(45) NULL DEFAULT NULL,
  `family_status` VARCHAR(45) NULL DEFAULT NULL,
  `web_site` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `current_workplace` VARCHAR(45) NULL DEFAULT NULL,
  `photo_url` VARCHAR(255) NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  `date_deleted` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `maximkalenik`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `maximkalenik`.`address` (
  `contact_id` INT(11) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `street` VARCHAR(45) NULL DEFAULT NULL,
  `house` VARCHAR(45) NULL DEFAULT NULL,
  `apartment` INT NULL DEFAULT NULL,
  `postcode` INT NULL DEFAULT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  `date_deleted` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`contact_id`, `contact_id`),
  UNIQUE INDEX `contact_id_UNIQUE` (`contact_id` ASC),
  INDEX `fk_address_contact1_idx` (`contact_id` ASC),
  CONSTRAINT `fk_address_contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `maximkalenik`.`contact` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `maximkalenik`.`attachments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `maximkalenik`.`attachments` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `filename` VARCHAR(45) NULL DEFAULT NULL,
  `date_upload` DATETIME NULL DEFAULT NULL,
  `comment` VARCHAR(100) NULL DEFAULT NULL,
  `contact_id` INT(11) NOT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  `date_deleted` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `contact_id`),
  INDEX `fk_attachments_contact_idx` (`contact_id` ASC),
  CONSTRAINT `fk_attachments_contact`
    FOREIGN KEY (`contact_id`)
    REFERENCES `maximkalenik`.`contact` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `maximkalenik`.`telephone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `maximkalenik`.`telephone` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `country_code` VARCHAR(10) NULL DEFAULT NULL,
  `number` INT(11) NOT NULL,
  `type` VARCHAR(5) NULL DEFAULT NULL,
  `comment` VARCHAR(100) NULL DEFAULT NULL,
  `contact_id` INT(11) NOT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT '0',
  `date_deleted` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `contact_id`),
  INDEX `fk_telephone_contact1_idx` (`contact_id` ASC),
  CONSTRAINT `fk_telephone_contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `maximkalenik`.`contact` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE USER 'mkalenik'@'localhost' IDENTIFIED BY 'mkalenik';
GRANT UPDATE,SELECT,INSERT,EXECUTE ON maximkalenik.* TO 'mkalenik'@'localhost';
FLUSH PRIVILEGES;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
