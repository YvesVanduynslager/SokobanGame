-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sokobandatabase
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sokobandatabase` ;

-- -----------------------------------------------------
-- Schema sokobandatabase
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sokobandatabase` DEFAULT CHARACTER SET utf8 ;
USE `sokobandatabase` ;

-- -----------------------------------------------------
-- Table `sokobandatabase`.`spel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sokobandatabase`.`spel` (
  `spelID` INT(3) NOT NULL AUTO_INCREMENT,
  `spelNaam` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`spelID`),
  UNIQUE INDEX `spelNaam_UNIQUE` (`spelNaam` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sokobandatabase`.`spelbord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sokobandatabase`.`spelbord` (
  `spelbordID` INT(3) NOT NULL AUTO_INCREMENT,
  `spelbordNaam` VARCHAR(45) NOT NULL,
  `spel_spelID` INT(3) NOT NULL,
  PRIMARY KEY (`spelbordID`),
  INDEX `fk_Spelbord_Spel1_idx` (`spel_spelID` ASC),
  CONSTRAINT `fk_Spelbord_Spel1`
    FOREIGN KEY (`spel_spelID`)
    REFERENCES `sokobandatabase`.`spel` (`spelID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sokobandatabase`.`doel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sokobandatabase`.`doel` (
  `doelID` INT(3) NOT NULL AUTO_INCREMENT,
  `positieX` INT(1) NOT NULL,
  `positieY` INT(1) NOT NULL,
  `Spelbord_spelbordID` INT(3) NOT NULL,
  INDEX `fk_Doel_Spelbord1_idx` (`Spelbord_spelbordID` ASC),
  PRIMARY KEY (`doelID`),
  CONSTRAINT `fk_Doel_Spelbord1`
    FOREIGN KEY (`Spelbord_spelbordID`)
    REFERENCES `sokobandatabase`.`spelbord` (`spelbordID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sokobandatabase`.`kist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sokobandatabase`.`kist` (
  `kistID` INT(3) NOT NULL AUTO_INCREMENT,
  `positieX` INT(1) NOT NULL,
  `positieY` INT(1) NOT NULL,
  `Spelbord_spelbordID` INT(3) NOT NULL,
  INDEX `fk_Kist_Spelbord1_idx` (`Spelbord_spelbordID` ASC),
  PRIMARY KEY (`kistID`),
  CONSTRAINT `fk_Kist_Spelbord1`
    FOREIGN KEY (`Spelbord_spelbordID`)
    REFERENCES `sokobandatabase`.`spelbord` (`spelbordID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sokobandatabase`.`mannetje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sokobandatabase`.`mannetje` (
  `mannetjeID` INT(3) NOT NULL AUTO_INCREMENT,
  `positieX` INT(1) NOT NULL,
  `positieY` INT(1) NOT NULL,
  `Spelbord_spelbordID` INT(3) NOT NULL,
  INDEX `fk_Mannetje_Spelbord1_idx` (`Spelbord_spelbordID` ASC),
  PRIMARY KEY (`mannetjeID`),
  CONSTRAINT `fk_Mannetje_Spelbord1`
    FOREIGN KEY (`Spelbord_spelbordID`)
    REFERENCES `sokobandatabase`.`spelbord` (`spelbordID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sokobandatabase`.`speler`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sokobandatabase`.`speler` (
  `spelerID` INT(3) NOT NULL AUTO_INCREMENT,
  `gebruikernaam` VARCHAR(30) NOT NULL,
  `wachtwoord` VARCHAR(60) NOT NULL,
  `isAdmin` TINYINT(1) NOT NULL,
  `achternaam` VARCHAR(45) NULL DEFAULT NULL,
  `voornaam` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`spelerID`),
  UNIQUE INDEX `gebruikernaam_UNIQUE` (`gebruikernaam` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sokobandatabase`.`veld`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sokobandatabase`.`veld` (
  `veldID` INT(3) NOT NULL AUTO_INCREMENT,
  `positieX` INT(1) NOT NULL,
  `positieY` INT(1) NOT NULL,
  `Spelbord_spelbordID` INT(3) NOT NULL,
  INDEX `fk_veld_spelbord_idx` (`Spelbord_spelbordID` ASC),
  PRIMARY KEY (`veldID`),
  CONSTRAINT `fk_Veld_Spelbord1`
    FOREIGN KEY (`Spelbord_spelbordID`)
    REFERENCES `sokobandatabase`.`spelbord` (`spelbordID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
