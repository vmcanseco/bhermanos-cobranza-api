-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: bhermanos
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` int(11) NOT NULL,
  `Nombre` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `ApellidoPaterno` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `ApellidoMaterno` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `Calle` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `NumeroExt` varchar(10) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `NumInt` varchar(5) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `Colonia` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `CP` varchar(10) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `Estado` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `Municipio` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `Ciudad` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `Zona` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `INE` varchar(15) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `TelCasa` varchar(20) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `Movil1` varchar(20) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `Movil2` varchar(20) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `Movil3` varchar(20) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `CorreoElectronico` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Facebook` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `FechaAlta` datetime NOT NULL,
  `Foto` varchar(500) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `IdExterno` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `Activo` varchar(1) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL DEFAULT 'Y',
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaModificacion` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  UNIQUE KEY `Numero_UNIQUE` (`Numero`),
  UNIQUE KEY `INE_UNIQUE` (`INE`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,10001,'VICTOR MANUEL','CANSECO','LOPEZ','UNO','1001','2','CENTRO','91729','VERACRUZ','VERACRUZ','VERACRUZ','CENTRO','11111','2291127855','2291127855',NULL,NULL,'vmcanseco1@gmail.com',NULL,'2020-01-01 00:00:00',NULL,NULL,'Y','2019-01-02 15:57:59','2020-01-03 12:13:24'),(3,10002,'JUAN MANUEL','MENDOZA','REYES','DOS','2001','2','COYOL','91729','VERACRUZ','VERACRUZ','VERACRUZ','CENTRO','11112','2291127855','2291127855',NULL,NULL,'vmcanseco2@gmail.com',NULL,'2020-01-01 00:00:00',NULL,NULL,'Y','2018-01-02 15:59:09','2020-01-07 14:36:21'),(4,10003,'JOSE MIGUEL','VIDAL','VIDAL','TRES','3001','2','CHIVERIA','91729','VERACRUZ','VERACRUZ','VERACRUZ','SUR','11113','2291127855','2291127855',NULL,NULL,'vmcanseco3@gmail.com',NULL,'2020-01-29 18:00:00',NULL,NULL,'Y','2017-01-02 16:01:04','2020-01-03 12:13:24'),(5,10004,'EDUARDO','GUTIERREZ','ZAMORA','CUATRO','104','2','POCITOS Y RIVERA','91729','VERACRUZ','VERACRUZ','VERACRUZ','NORTE','11114','2291127855','2291127855',NULL,NULL,'vmcanseco4@gmail.com',NULL,'2020-01-01 02:00:00',NULL,NULL,'Y','2016-01-02 16:06:26','2020-01-03 12:13:24'),(6,10005,'LUISA','MENDEZ','ROJAS','CINCO','2500','2','GALENA','91729','VERACRUZ','VERACRUZ','VERACRUZ','NORTE','11115','2291127855','2291127855',NULL,NULL,'vmcanseco5@gmail.com',NULL,'2020-01-02 16:09:00',NULL,NULL,'Y','2015-01-02 16:10:01','2020-01-03 12:13:24'),(7,10006,'LUIS','LUIS','LUIS','SANCHEZ TAGLE','200','1','POCITOS Y RIVERA','91729','VERACRUZ','VERACRUZ','VERACRUZ','CENTRO','12345','91729','2291127855',NULL,NULL,'VMCANSECO@GMAIL.COM',NULL,'2020-01-07 00:00:00',NULL,'10006','Y','2020-01-07 09:18:19',NULL),(8,10007,'JORGE','JORGE','JORGE','SANCHEZ TAGLE','8000','2','CENTRO','1900','VERACRUZ','VERACRUZ','VERACRUZ','CENTRO','9999','2291127855','2291127855',NULL,NULL,'aprog5@icave.com.mx',NULL,'2020-01-07 00:00:00',NULL,'10007','Y','2020-01-07 12:05:37',NULL),(9,10008,'MARIA','MARIA','MARIA','CONOCIDA','1','1','CENTRO','7000','VERACRUZ','VERACRUZ','VERACRUZ','CENTRO','88888','2291127855','2291127855','122222',NULL,'aprog5@icave.com.mx',NULL,'2020-01-06 18:00:00',NULL,'10008','Y','2020-01-07 12:10:18','2020-01-07 17:02:47'),(10,10009,'ARIANA','ARIANA','ARIANA','CONOCIDA','1','2','ZARAGOZA','91700','VERACRUZ','VERACRUZ','ORIZABA','CENTRO','22222','2299010101','2291858585',NULL,NULL,'aprog5@icave.com.mx',NULL,'2020-01-07 00:00:00',NULL,'10009','Y','2020-01-07 14:38:44',NULL);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distribuidor`
--

DROP TABLE IF EXISTS `distribuidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `distribuidor` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` varchar(30) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `Nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `FechaAlta` datetime NOT NULL,
  `Activo` varchar(1) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL DEFAULT 'Y',
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaModificacion` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Numero_UNIQUE` (`Numero`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distribuidor`
--

LOCK TABLES `distribuidor` WRITE;
/*!40000 ALTER TABLE `distribuidor` DISABLE KEYS */;
INSERT INTO `distribuidor` VALUES (1,'054-00464','JOSE ALBERTO','2020-01-08 00:00:00','Y','2020-01-08 08:35:11',NULL);
/*!40000 ALTER TABLE `distribuidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vales`
--

DROP TABLE IF EXISTS `vales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vales` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdCliente` int(11) NOT NULL,
  `IdDistribuidor` int(11) NOT NULL,
  `Folio` varchar(45) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `Tipo` varchar(1) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `Monto` decimal(7,2) NOT NULL,
  `Fecha` datetime NOT NULL,
  `MontoDisponible` decimal(7,2) DEFAULT NULL,
  `MontoPagado` decimal(7,2) DEFAULT '0.00',
  `UltimoPago` datetime DEFAULT NULL,
  `Pagado` varchar(1) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL DEFAULT 'N',
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaModificacion` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Folio_UNIQUE` (`Folio`),
  KEY `IdDistribuidorFk_idx` (`IdDistribuidor`),
  KEY `IdClienteFk_idx` (`IdCliente`),
  CONSTRAINT `IdClienteFk` FOREIGN KEY (`IdCliente`) REFERENCES `clientes` (`Id`),
  CONSTRAINT `IdDistribuidorFk` FOREIGN KEY (`IdDistribuidor`) REFERENCES `distribuidor` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vales`
--

LOCK TABLES `vales` WRITE;
/*!40000 ALTER TABLE `vales` DISABLE KEYS */;
INSERT INTO `vales` VALUES (1,1,1,'0000551-0','V',15000.00,'2020-01-01 00:00:00',15000.00,0.00,NULL,'N','2020-01-08 08:38:41',NULL);
/*!40000 ALTER TABLE `vales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdVale` int(11) NOT NULL,
  `Monto` decimal(7,2) NOT NULL DEFAULT '0.00',
  `Plazo` int(11) NOT NULL,
  `Fecha` datetime NOT NULL,
  `MontoPagado` decimal(7,2) NOT NULL DEFAULT '0.00',
  `MontoDisponible` decimal(7,2) NOT NULL DEFAULT '0.00',
  `Pagada` varchar(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Intereses` decimal(7,2) DEFAULT '0.00',
  `UltimoPago` datetime DEFAULT NULL,
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaModificacion` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `IdValeFk_idx` (`IdVale`),
  CONSTRAINT `IdValeFk` FOREIGN KEY (`IdVale`) REFERENCES `vales` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-10 16:44:23
