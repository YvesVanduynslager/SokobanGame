SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `dobbelsteen` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `dobbelsteen` ;

-- -----------------------------------------------------
-- Table `dobbelsteen`.`Speler`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dobbelsteen`.`Speler` (
  `naam` VARCHAR(255) NOT NULL,
  `voornaam` VARCHAR(255) NOT NULL,
  `emailadres` VARCHAR(255) NOT NULL,
  `geboortedatum` BIGINT(20) NOT NULL,
  `wachtwoord` VARCHAR(255) NOT NULL,
  `beheerder` BIT(1) NOT NULL,
  `krediet` DECIMAL(10,1) NULL DEFAULT NULL,
  PRIMARY KEY (`emailadres`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
