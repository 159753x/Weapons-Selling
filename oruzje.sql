/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oruzje` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `oruzje`;



DROP TABLE IF EXISTS `Administrator`;

CREATE TABLE `Administrator` (
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Administrator` VALUES 
(1,'Lazar','Stanojevic','lazar','lazar123'),
(2,'Ivan','Vukosavljevic','ivan','ivan123');



DROP TABLE IF EXISTS `Kompanija`;

CREATE TABLE `Kompanija` (
  `KompanijaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `PIB` VARCHAR(20) NOT NULL,
  `NazivKompanije` VARCHAR(30) NOT NULL,
  `Adresa` VARCHAR(70) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`KompanijaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Kompanija` VALUES 
(1,'72837283', 'Ministarstvo odbrane Srbije', 'Milutina Milankovica 321', 'mosrb@gmail.com'),
(2,'92837253', 'Ministarstvo odbrane Crne Gore', 'Niksicka 84', 'mocg@gmail.com'),
(3,'17283927', 'Ministarstvo odbrane Hrvatske', 'Dalmatinska 23', 'mohr@gmail.com');


DROP TABLE IF EXISTS `TipOruzja`;

CREATE TABLE `TipOruzja` (
  `TipOruzjaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivTipaOruzja` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`TipOruzjaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `TipOruzja` VALUES 
(1,'Pistolj'),
(2,'Puska'),
(3,'Automatsko oruzje');



DROP TABLE IF EXISTS `Oruzje`;

CREATE TABLE `Oruzje` (
  `OruzjeID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivOruzja` VARCHAR(50) NOT NULL,
  `Opis` VARCHAR(200) NOT NULL,
  `KapacitetMetkova` INT(7) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `TipOruzjaID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`OruzjeID`),
  CONSTRAINT `fk_tipOruzja_id` FOREIGN KEY (`TipOruzjaID`) REFERENCES `TipOruzja` (`TipOruzjaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Oruzje` VALUES 
(1,'CZ 75', 'Pistolj kalibra 9mm', 16, 250, 1),
(2,'Glok 17', 'Pistolj kalibra 9mm', 17, 300, 1),
(3,'Marlin 336C', 'Kalibar 30-30 Winchesterje', 6, 600, 2),
(4,'Nosler M48 Patriot', 'Kalibar 26 Nosler', 10, 1500, 2),
(5,'AK-47', '7.62×39mm', 30, 3000, 3),
(6,'Uzi', 'Kalibra 9mm', 100, 2000, 3);



DROP TABLE IF EXISTS `Narudzbina`;

CREATE TABLE `Narudzbina` (
  `NarudzbinaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVreme` DATETIME NOT NULL,
  `DatumIsporuke` DATE NOT NULL,
  `UkupnaCena` DECIMAL(10,2) NOT NULL,
  `KompanijaID` BIGINT(10) UNSIGNED NOT NULL,
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`NarudzbinaID`),
  CONSTRAINT `fk_komp_id` FOREIGN KEY (`KompanijaID`) REFERENCES `Kompanija` (`KompanijaID`),
  CONSTRAINT `fk_admin_id` FOREIGN KEY (`AdministratorID`) REFERENCES `Administrator` (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT  INTO `Narudzbina` VALUES 
(1,'2023-05-10 20:05:03','2023-05-30',330000,1,1);


DROP TABLE IF EXISTS `StavkaNarudzbine`;

CREATE TABLE `StavkaNarudzbine` (
  `NarudzbinaID` BIGINT(10) UNSIGNED NOT NULL,
  `RbStavke` INT(7) NOT NULL,
  `Kolicina` INT(7) NOT NULL,
  `CenaStavke` DECIMAL(10,2) NOT NULL,
  `OruzjeID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`NarudzbinaID`,`RbStavke`),
  CONSTRAINT `fk_narudzbina_id` FOREIGN KEY (`NarudzbinaID`) REFERENCES `Narudzbina` (`NarudzbinaID`) ON DELETE CASCADE,
  CONSTRAINT `fk_oruzje_id` FOREIGN KEY (`OruzjeID`) REFERENCES `Oruzje` (`OruzjeID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `StavkaNarudzbine` VALUES 
(1,1,100,30000,2),
(1,2,100,300000,5);




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
