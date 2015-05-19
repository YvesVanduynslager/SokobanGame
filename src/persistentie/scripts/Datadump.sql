CREATE DATABASE  IF NOT EXISTS `sokobandatabase` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sokobandatabase`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: sokobandatabase
-- ------------------------------------------------------
-- Server version	5.6.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `doel`
--

DROP TABLE IF EXISTS `doel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doel` (
  `doelID` int(3) NOT NULL AUTO_INCREMENT,
  `positieX` int(1) NOT NULL,
  `positieY` int(1) NOT NULL,
  `Spelbord_spelbordID` int(3) NOT NULL,
  PRIMARY KEY (`doelID`),
  KEY `fk_Doel_Spelbord1_idx` (`Spelbord_spelbordID`),
  CONSTRAINT `fk_Doel_Spelbord1` FOREIGN KEY (`Spelbord_spelbordID`) REFERENCES `spelbord` (`spelbordID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doel`
--

LOCK TABLES `doel` WRITE;
/*!40000 ALTER TABLE `doel` DISABLE KEYS */;
INSERT INTO `doel` VALUES (1,2,4,1),(2,1,3,2),(3,3,6,2),(4,4,1,2),(5,6,4,2),(6,1,1,3),(7,1,2,3),(8,3,1,4),(9,3,2,4),(10,3,3,4),(11,3,4,4),(12,3,5,4),(13,1,3,5),(14,1,4,5),(15,2,4,5),(16,3,5,5),(17,2,4,6),(18,3,4,6),(19,5,2,6);
/*!40000 ALTER TABLE `doel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kist`
--

DROP TABLE IF EXISTS `kist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kist` (
  `kistID` int(3) NOT NULL AUTO_INCREMENT,
  `positieX` int(1) NOT NULL,
  `positieY` int(1) NOT NULL,
  `Spelbord_spelbordID` int(3) NOT NULL,
  PRIMARY KEY (`kistID`),
  KEY `fk_Kist_Spelbord1_idx` (`Spelbord_spelbordID`),
  CONSTRAINT `fk_Kist_Spelbord1` FOREIGN KEY (`Spelbord_spelbordID`) REFERENCES `spelbord` (`spelbordID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kist`
--

LOCK TABLES `kist` WRITE;
/*!40000 ALTER TABLE `kist` DISABLE KEYS */;
INSERT INTO `kist` VALUES (1,3,4,1),(2,3,3,2),(3,3,5,2),(4,4,3,2),(5,5,4,2),(6,3,1,3),(7,4,3,3),(8,2,3,4),(9,2,4,4),(10,2,5,4),(11,4,2,4),(12,4,3,4),(13,3,4,5),(14,4,3,5),(15,5,4,5),(16,5,5,5),(17,4,5,6),(18,5,3,6),(19,5,4,6);
/*!40000 ALTER TABLE `kist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mannetje`
--

DROP TABLE IF EXISTS `mannetje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mannetje` (
  `mannetjeID` int(3) NOT NULL AUTO_INCREMENT,
  `positieX` int(1) NOT NULL,
  `positieY` int(1) NOT NULL,
  `Spelbord_spelbordID` int(3) NOT NULL,
  PRIMARY KEY (`mannetjeID`),
  KEY `fk_Mannetje_Spelbord1_idx` (`Spelbord_spelbordID`),
  CONSTRAINT `fk_Mannetje_Spelbord1` FOREIGN KEY (`Spelbord_spelbordID`) REFERENCES `spelbord` (`spelbordID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mannetje`
--

LOCK TABLES `mannetje` WRITE;
/*!40000 ALTER TABLE `mannetje` DISABLE KEYS */;
INSERT INTO `mannetje` VALUES (1,5,4,1),(2,4,4,2),(3,3,2,3),(4,2,2,4),(5,6,3,5),(6,2,5,6);
/*!40000 ALTER TABLE `mannetje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spel`
--

DROP TABLE IF EXISTS `spel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spel` (
  `spelID` int(3) NOT NULL AUTO_INCREMENT,
  `spelNaam` varchar(45) NOT NULL,
  PRIMARY KEY (`spelID`),
  UNIQUE KEY `spelNaam_UNIQUE` (`spelNaam`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spel`
--

LOCK TABLES `spel` WRITE;
/*!40000 ALTER TABLE `spel` DISABLE KEYS */;
INSERT INTO `spel` VALUES (1,'easy'),(3,'hard'),(2,'medium');
/*!40000 ALTER TABLE `spel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spelbord`
--

DROP TABLE IF EXISTS `spelbord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spelbord` (
  `spelbordID` int(3) NOT NULL AUTO_INCREMENT,
  `spel_spelID` int(3) NOT NULL,
  PRIMARY KEY (`spelbordID`),
  KEY `fk_Spelbord_Spel1_idx` (`spel_spelID`),
  CONSTRAINT `fk_Spelbord_Spel1` FOREIGN KEY (`spel_spelID`) REFERENCES `spel` (`spelID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spelbord`
--

LOCK TABLES `spelbord` WRITE;
/*!40000 ALTER TABLE `spelbord` DISABLE KEYS */;
INSERT INTO `spelbord` VALUES (1,1),(2,1),(3,1),(4,2),(5,2),(6,3);
/*!40000 ALTER TABLE `spelbord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speler`
--

DROP TABLE IF EXISTS `speler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `speler` (
  `spelerID` int(3) NOT NULL AUTO_INCREMENT,
  `gebruikernaam` varchar(30) NOT NULL,
  `wachtwoord` varchar(100) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL,
  `achternaam` varchar(45) DEFAULT NULL,
  `voornaam` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`spelerID`),
  UNIQUE KEY `gebruikernaam_UNIQUE` (`gebruikernaam`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speler`
--

LOCK TABLES `speler` WRITE;
/*!40000 ALTER TABLE `speler` DISABLE KEYS */;
INSERT INTO `speler` VALUES (1,'Yves','$2a$10$RV4IhXXJFyL3EmzvS4sqHug6mxVjM2pf8kpaFUkMM5.5yoMzXRXzK',1,'Vanduynslager','Yves'),(2,'Michiel','$2a$10$RV4IhXXJFyL3EmzvS4sqHug6mxVjM2pf8kpaFUkMM5.5yoMzXRXzK',1,'Mertens','Michiel'),(3,'FrankyFishstick','$2a$10$RV4IhXXJFyL3EmzvS4sqHug6mxVjM2pf8kpaFUkMM5.5yoMzXRXzK',0,'Fishstick','Franky'),(4,'RodneyRolmops','$2a$10$RV4IhXXJFyL3EmzvS4sqHug6mxVjM2pf8kpaFUkMM5.5yoMzXRXzK',0,'Rolmops','Rodney'),(5,'PascalPaskotgluurder','$2a$10$RV4IhXXJFyL3EmzvS4sqHug6mxVjM2pf8kpaFUkMM5.5yoMzXRXzK',0,'Paskotgluurder','Pascal'),(6,'BeatriceBokaalhoofd','$2a$10$RV4IhXXJFyL3EmzvS4sqHug6mxVjM2pf8kpaFUkMM5.5yoMzXRXzK',0,'Bokaalhoofd','Beatrice');
/*!40000 ALTER TABLE `speler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veld`
--

DROP TABLE IF EXISTS `veld`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `veld` (
  `veldID` int(3) NOT NULL AUTO_INCREMENT,
  `positieX` int(1) NOT NULL,
  `positieY` int(1) NOT NULL,
  `Spelbord_spelbordID` int(3) NOT NULL,
  PRIMARY KEY (`veldID`),
  KEY `fk_veld_spelbord_idx` (`Spelbord_spelbordID`),
  CONSTRAINT `fk_Veld_Spelbord1` FOREIGN KEY (`Spelbord_spelbordID`) REFERENCES `spelbord` (`spelbordID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veld`
--

LOCK TABLES `veld` WRITE;
/*!40000 ALTER TABLE `veld` DISABLE KEYS */;
INSERT INTO `veld` VALUES (1,4,4,1),(2,2,3,2),(3,3,4,2),(4,4,2,2),(5,2,1,3),(6,2,2,3),(7,3,3,3),(8,3,4,3),(9,4,1,3),(10,4,2,3),(11,4,4,3),(12,5,1,3),(13,5,2,3),(14,1,2,4),(15,1,3,4),(16,1,4,4),(17,1,5,4),(18,2,1,4),(19,2,6,4),(20,4,1,4),(21,4,4,4),(22,4,6,4),(23,5,2,4),(24,5,3,4),(25,5,4,4),(26,5,5,4),(27,3,6,4),(28,4,5,4),(29,2,3,5),(30,3,1,5),(31,3,2,5),(32,3,3,5),(33,4,1,5),(34,4,2,5),(35,4,4,5),(36,4,5,5),(37,5,1,5),(38,5,2,5),(39,5,6,5),(40,6,1,5),(41,6,2,5),(42,6,4,5),(43,6,5,5),(44,6,6,5),(45,2,2,6),(46,2,3,6),(47,2,5,6),(48,2,6,6),(49,3,2,6),(50,3,6,6),(51,4,2,6),(52,4,3,6),(53,4,4,6),(54,4,6,6),(55,5,5,6),(56,6,2,6),(57,6,3,6),(58,3,3,6);
/*!40000 ALTER TABLE `veld` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-19 10:57:19
