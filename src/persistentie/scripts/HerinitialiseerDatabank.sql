/* MaakSokobandatabase */
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
  `wachtwoord` VARCHAR(100) NOT NULL,
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

/*VoegAdminsToe */
/* Deze instructie vult de tabel Spelers op met admins */
INSERT INTO sokobandatabase.speler(gebruikernaam, wachtwoord, isAdmin, voornaam, achternaam)
VALUES ('Yves', '$2a$10$RV4IhXXJFyL3EmzvS4sqHug6mxVjM2pf8kpaFUkMM5.5yoMzXRXzK', 1, 'Yves', 'Vanduynslager'),
('Michiel', '$2a$10$RV4IhXXJFyL3EmzvS4sqHug6mxVjM2pf8kpaFUkMM5.5yoMzXRXzK', 1, 'Michiel', 'Mertens');

/* VoegSpelersToe */
/* Deze instructie vult de tabel Spelers op met spelers */
INSERT INTO sokobandatabase.speler(gebruikernaam, wachtwoord, isAdmin, voornaam, achternaam)
VALUES ('FrankyFishstick', '$2a$10$RV4IhXXJFyL3EmzvS4sqHug6mxVjM2pf8kpaFUkMM5.5yoMzXRXzK', 0, 'Franky', 'Fishstick'),
('RodneyRolmops', '$2a$10$RV4IhXXJFyL3EmzvS4sqHug6mxVjM2pf8kpaFUkMM5.5yoMzXRXzK', 0, 'Rodney', 'Rolmops'),
('PascalPaskotgluurder', '$2a$10$RV4IhXXJFyL3EmzvS4sqHug6mxVjM2pf8kpaFUkMM5.5yoMzXRXzK', 0, 'Pascal', 'Paskotgluurder'),
('BeatriceBokaalhoofd', '$2a$10$RV4IhXXJFyL3EmzvS4sqHug6mxVjM2pf8kpaFUkMM5.5yoMzXRXzK', 0, 'Beatrice', 'Bokaalhoofd');

/* VoegSpellenToe */
INSERT INTO sokobandatabase.spel(spelNaam) VALUES ('easy');
INSERT INTO sokobandatabase.spel(spelNaam) VALUES ('medium');
INSERT INTO sokobandatabase.spel(spelNaam) VALUES ('hard');
/* spelbordnamen voor spellen */
/*INSERT INTO sokobandatabase.spelbord (spelbordNaam, Spel_spelID) VALUES
('1-1', 1), ('1-2', 1), ('1-3', 1),
('2-1', 2), ('2-2', 2), ('2-3', 2),
('3-1', 3);*/
INSERT INTO sokobandatabase.spelbord (Spel_spelID) VALUES
(1), (1), (1),
(2), (2), (2),
(3);

/* SPEL: easy */
/* SPELBORD 1-1 */
INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(4,4,1);
INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(2,4,1);
INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) values
(3,4,1);
INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(5,4,1);
/* EINDE SPELBORD 1-1 */
/* SPELBORD 1-2 */
INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(2,3,2),(3,4,2),(4,2,2);
INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(1,3,2),(3,6,2),(4,1,2),(6,4,2);
INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) values
(3,3,2),(3,5,2),(4,3,2),(5,4,2);
INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(4,4,2);
/* EINDE SPELBORD 1-2 */
/* SPELBORD 1-3 */
INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(2,1,3),(2,2,3),(3,3,3),(3,4,3),(4,1,3),(4,2,3),(4,4,3),(5,1,3),(5,2,3);
INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(1,1,3),(1,2,3);
INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) VALUES
(3,1,3),(4,3,3);
INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(3,2,3);
/*EINDE SPELBORD 1-3 */
/* EINDE SPEL: easy */

/* SPEL: gemiddeld */
/* SPELBORD 2-1 */
INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(1,2,4),(1,3,4),(1,4,4),(1,5,4),(2,1,4),(2,6,4),(4,1,4),(4,4,4),(4,6,4),(5,2,4),(5,3,4),(5,4,4),(5,5,4),(3,6,4),(4,5,4);

INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(3,1,4),(3,2,4),(3,3,4),(3,4,4),(3,5,4);

INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) VALUES
(2,3,4),(2,4,4),(2,5,4),(4,2,4),(4,3,4);

INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(2,2,4);
/* EINDE SPELBORD 2-1 */

/* SPELBORD 2-2 */ /*VERPLAATSTEN NAAR MOEILIJK*/
INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(2,3,5),(3,2,5),(3,3,5),(4,2,5),(4,4,5),(4,5,5),(5,1,5),(5,2,5),(5,6,5),(6,1,5),(6,2,5),(6,4,5),(6,5,5),(6,6,5);
INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(1,3,5),(1,4,5),(2,4,5),(3,5,5);
INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) VALUES
(3,4,5),(4,3,5),(5,4,5),(5,5,5);
INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(6,3,5);
/* EINDE SPELBORD 2-2 */

/* SPELBORD 2-3 */
INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(1,3,6),(1,4,6),(1,5,6),(1,6,6),(2,6,6),(3,6,6),(3,2,6),(4,1,6),(5,4,6),(5,5,6);
INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(3,4,6),(3,5,6),(4,3,6),(4,4,6),(4,5,6);
INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) VALUES
(2,3,6),(2,4,6),(2,5,6),(3,3,6),(4,2,6);
INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(3,1,6);
/* EINDE SPELBORD 2-3 */
/* EINDE SPEL: gemiddeld */

/* SPEL: moeilijk */
/* SPELBORD 3-1 */
INSERT INTO sokobandatabase.veld (positieX, positieY, Spelbord_spelbordID) VALUES
(2,2,7),(2,3,7),(2,5,7),(2,6,7),(3,2,7),(3,6,7),(4,2,7),(4,3,7),(4,4,7),(4,6,7),(5,5,7),(6,2,7),(6,3,7),(3,3,7);
INSERT INTO sokobandatabase.doel (positieX, positieY, Spelbord_spelbordID) VALUES
(2,4,7),(3,4,7),(5,2,7);
INSERT INTO sokobandatabase.kist (positieX, positieY, Spelbord_spelbordID) VALUES
(4,5,7),(5,3,7),(5,4,7);
INSERT INTO sokobandatabase.mannetje (positieX, positieY, Spelbord_spelbordID) VALUES
(2,5,7);
/* EINDE SPELBORD 3-1 */

/* SPELBORD 3-2 */
/* EINDE SPELBORD 3-2 */

/* SPELBORD 3-3 */
/* EINDE SPELBORD 3-3 */
/* EINDE SPEL: moeilijk */
