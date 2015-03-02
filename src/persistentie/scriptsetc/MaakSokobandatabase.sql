-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sokobandatabase
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sokobandatabase
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sokobandatabase` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `sokobandatabase` ;

-- -----------------------------------------------------
-- Table `sokobandatabase`.`Speler`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sokobandatabase`.`Speler` ;

CREATE TABLE IF NOT EXISTS `sokobandatabase`.`Speler` (
  `spelerID` INT NOT NULL AUTO_INCREMENT,
  `gebruikernaam` VARCHAR(30) NOT NULL,
  `wachtwoord` VARCHAR(45) NOT NULL,
  `isAdmin` TINYINT(1) NOT NULL,
  `achternaam` VARCHAR(45) NULL,
  `voornaam` VARCHAR(45) NULL,
  PRIMARY KEY (`spelerID`),
  UNIQUE INDEX `gebruikernaam_UNIQUE` (`gebruikernaam` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sokobandatabase`.`Spel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sokobandatabase`.`Spel` ;

CREATE TABLE IF NOT EXISTS `sokobandatabase`.`Spel` (
  `spelID` INT NOT NULL AUTO_INCREMENT,
  `spelNaam` VARCHAR(45) NOT NULL,
  `Speler_spelerID` INT NULL,
  PRIMARY KEY (`spelID`, `Speler_spelerID`),
  UNIQUE INDEX `spelNaam_UNIQUE` (`spelNaam` ASC),
  INDEX `fk_Spel_Speler1_idx` (`Speler_spelerID` ASC),
  CONSTRAINT `fk_Spel_Speler1`
    FOREIGN KEY (`Speler_spelerID`)
    REFERENCES `sokobandatabase`.`Speler` (`spelerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sokobandatabase`.`Spelbord`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sokobandatabase`.`Spelbord` ;

CREATE TABLE IF NOT EXISTS `sokobandatabase`.`Spelbord` (
  `spelbordID` INT NOT NULL AUTO_INCREMENT,
  `spelbordNaam` VARCHAR(45) NOT NULL,
  `Spel_spelID` INT NOT NULL,
  PRIMARY KEY (`spelbordID`, `Spel_spelID`),
  INDEX `fk_Spelbord_Spel1_idx` (`Spel_spelID` ASC),
  CONSTRAINT `fk_Spelbord_Spel1`
    FOREIGN KEY (`Spel_spelID`)
    REFERENCES `sokobandatabase`.`Spel` (`spelID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sokobandatabase`.`Muur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sokobandatabase`.`Muur` ;

CREATE TABLE IF NOT EXISTS `sokobandatabase`.`Muur` (
  `muurID` INT NOT NULL AUTO_INCREMENT,
  `PositieX` INT NOT NULL,
  `PositieY` INT NOT NULL,
  `Spelbord_spelbordID` INT NOT NULL,
  PRIMARY KEY (`muurID`, `Spelbord_spelbordID`),
  INDEX `fk_Muur_Spelbord1_idx` (`Spelbord_spelbordID` ASC),
  CONSTRAINT `fk_Muur_Spelbord1`
    FOREIGN KEY (`Spelbord_spelbordID`)
    REFERENCES `sokobandatabase`.`Spelbord` (`spelbordID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sokobandatabase`.`Veld`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sokobandatabase`.`Veld` ;

CREATE TABLE IF NOT EXISTS `sokobandatabase`.`Veld` (
  `VeldID` INT NOT NULL AUTO_INCREMENT,
  `positieX` INT NOT NULL,
  `positieY` INT NOT NULL,
  `IsDoel` TINYINT(1) NOT NULL,
  `Spelbord_spelbordID` INT NOT NULL,
  PRIMARY KEY (`VeldID`, `Spelbord_spelbordID`),
  INDEX `fk_Veld_Spelbord1_idx` (`Spelbord_spelbordID` ASC),
  CONSTRAINT `fk_Veld_Spelbord1`
    FOREIGN KEY (`Spelbord_spelbordID`)
    REFERENCES `sokobandatabase`.`Spelbord` (`spelbordID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sokobandatabase`.`Kist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sokobandatabase`.`Kist` ;

CREATE TABLE IF NOT EXISTS `sokobandatabase`.`Kist` (
  `kistID` INT NOT NULL AUTO_INCREMENT,
  `positieX` INT NOT NULL,
  `positieY` INT NOT NULL,
  `Spelbord_spelbordID` INT NOT NULL,
  PRIMARY KEY (`kistID`, `Spelbord_spelbordID`),
  INDEX `fk_Kist_Spelbord1_idx` (`Spelbord_spelbordID` ASC),
  CONSTRAINT `fk_Kist_Spelbord1`
    FOREIGN KEY (`Spelbord_spelbordID`)
    REFERENCES `sokobandatabase`.`Spelbord` (`spelbordID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sokobandatabase`.`Mannetje`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sokobandatabase`.`Mannetje` ;

CREATE TABLE IF NOT EXISTS `sokobandatabase`.`Mannetje` (
  `mannetjeID` INT NOT NULL AUTO_INCREMENT,
  `PositieX` INT NOT NULL,
  `PositieY` INT NOT NULL,
  `Spelbord_spelbordID` INT NOT NULL,
  PRIMARY KEY (`mannetjeID`, `Spelbord_spelbordID`),
  INDEX `fk_Mannetje_Spelbord1_idx` (`Spelbord_spelbordID` ASC),
  CONSTRAINT `fk_Mannetje_Spelbord1`
    FOREIGN KEY (`Spelbord_spelbordID`)
    REFERENCES `sokobandatabase`.`Spelbord` (`spelbordID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
