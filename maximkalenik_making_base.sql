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
-- Table `maximkalenik`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `maximkalenik`.`location` (
  `postcode` VARCHAR(10) NOT NULL,
  `country` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`postcode`),
  UNIQUE INDEX `postcode_UNIQUE` (`postcode` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `maximkalenik`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `maximkalenik`.`contact` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `patronymic` VARCHAR(45) NULL DEFAULT NULL,
  `date_of_birth` DATE NULL DEFAULT NULL,
  `sex_is_male` TINYINT(1) NULL DEFAULT '1',
  `nationality` VARCHAR(45) NULL DEFAULT NULL,
  `family_status` VARCHAR(45) NULL DEFAULT NULL,
  `web_site` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `current_workplace` VARCHAR(45) NULL DEFAULT NULL,
  `photo_url` VARCHAR(255) NULL DEFAULT NULL,
  `street` VARCHAR(45) NULL DEFAULT NULL,
  `house` VARCHAR(45) NULL DEFAULT NULL,
  `apartment` VARCHAR(45) NULL DEFAULT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT '0',
  `date_deleted` DATETIME NULL DEFAULT NULL,
  `location_postcode` VARCHAR(10) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_contact_location1_idx` (`location_postcode` ASC),
  CONSTRAINT `fk_contact_location1`
  FOREIGN KEY (`location_postcode`)
  REFERENCES `maximkalenik`.`location` (`postcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `maximkalenik`.`attachment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `maximkalenik`.`attachment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `filename` VARCHAR(45) NULL DEFAULT NULL,
  `date_upload` DATETIME NULL DEFAULT NULL,
  `comment` VARCHAR(100) NULL DEFAULT NULL,
  `contact_id` INT(11) NOT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT '0',
  `date_deleted` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_attachment_contact_idx` (`contact_id` ASC),
  CONSTRAINT `fk_attachment_contact`
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
  `operator_code` VARCHAR(4) NULL DEFAULT NULL,
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
  AUTO_INCREMENT = 3
  DEFAULT CHARACTER SET = utf8;


CREATE USER 'mkalenik'@'localhost' IDENTIFIED BY 'mkalenik';
GRANT UPDATE,SELECT,INSERT,EXECUTE ON maximkalenik.* TO 'mkalenik'@'localhost';
FLUSH PRIVILEGES;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
