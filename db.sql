-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bhermanos
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
-- Table structure for table `historial_pagos`
--

DROP TABLE IF EXISTS `historial_pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historial_pagos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdPago` int(11) DEFAULT NULL,
  `IdCliente` int(11) DEFAULT NULL,
  `FormaPago` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `MontoPagado` decimal(7,2) NOT NULL DEFAULT '0.00',
  `InteresesPagados` decimal(7,2) NOT NULL DEFAULT '0.00',
  `FechaPago` datetime NOT NULL,
  `Comentarios` varchar(1000) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FechaCreacion` varchar(45) COLLATE latin1_spanish_ci NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `FechaActualizacion` varchar(45) COLLATE latin1_spanish_ci DEFAULT 'ON UPDATE CURRENT_TIMESTAMP',
  PRIMARY KEY (`Id`),
  KEY `IdClienteHistorialPagoFk_idx` (`IdCliente`),
  KEY `IdPagoHistorialPagoFk_idx` (`IdPago`),
  CONSTRAINT `IdClienteHistorialPagoFk` FOREIGN KEY (`IdCliente`) REFERENCES `clientes` (`Id`),
  CONSTRAINT `IdPagoHistorialPagoFk` FOREIGN KEY (`IdPago`) REFERENCES `pagos` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial_pagos`
--

LOCK TABLES `historial_pagos` WRITE;
/*!40000 ALTER TABLE `historial_pagos` DISABLE KEYS */;
/*!40000 ALTER TABLE `historial_pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdVenta` int(11) NOT NULL,
  `NumPago` int(2) NOT NULL,
  `Monto` decimal(7,2) NOT NULL,
  `TipoPago` int(2) NOT NULL COMMENT '0 - Extraordinario\n1 - Quincenal\n2 - Mensual',
  `Intereses` decimal(7,2) NOT NULL DEFAULT '0.00',
  `Pagado` varchar(1) COLLATE latin1_spanish_ci DEFAULT 'N',
  `MontoPagado` decimal(7,2) NOT NULL DEFAULT '0.00',
  `InteresesPagados` decimal(7,2) NOT NULL DEFAULT '0.00',
  `FechaProgramada` datetime NOT NULL,
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaActualizacion` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  KEY `IdVentaFk_idx` (`IdVenta`),
  CONSTRAINT `IdVentaPagoFk` FOREIGN KEY (`IdVenta`) REFERENCES `ventas` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
INSERT INTO `pagos` VALUES (1,1,6,595.00,1,0.00,'N',0.00,0.00,'2020-05-31 00:00:00','2020-02-12 11:53:59',NULL),(2,1,11,595.00,1,0.00,'N',0.00,0.00,'2020-08-15 00:00:00','2020-02-12 11:53:59',NULL),(3,1,12,605.00,1,0.00,'N',0.00,0.00,'2020-08-31 00:00:00','2020-02-12 11:53:59',NULL),(4,1,8,595.00,1,0.00,'N',0.00,0.00,'2020-06-30 00:00:00','2020-02-12 11:53:59',NULL),(5,1,5,595.00,1,0.00,'N',0.00,0.00,'2020-05-15 00:00:00','2020-02-12 11:53:59',NULL),(6,1,10,595.00,1,0.00,'N',0.00,0.00,'2020-07-31 00:00:00','2020-02-12 11:53:59',NULL),(7,1,7,595.00,1,0.00,'N',0.00,0.00,'2020-06-15 00:00:00','2020-02-12 11:53:59',NULL),(8,1,3,595.00,1,0.00,'N',0.00,0.00,'2020-04-15 00:00:00','2020-02-12 11:53:59',NULL),(9,1,9,595.00,1,0.00,'N',0.00,0.00,'2020-07-15 00:00:00','2020-02-12 11:53:59',NULL),(10,1,1,595.00,1,0.00,'N',0.00,0.00,'2020-03-15 00:00:00','2020-02-12 11:53:59','2020-02-12 12:01:27'),(11,1,2,595.00,1,0.00,'N',0.00,0.00,'2020-03-31 00:00:00','2020-02-12 11:53:59',NULL),(12,1,4,595.00,1,0.00,'N',0.00,0.00,'2020-04-30 00:00:00','2020-02-12 11:53:59',NULL),(13,2,4,384.00,2,0.00,'N',0.00,0.00,'2020-06-15 00:00:00','2020-02-12 11:59:25',NULL),(14,2,12,384.00,2,0.00,'N',0.00,0.00,'2020-10-15 00:00:00','2020-02-12 11:59:25',NULL),(15,2,22,384.00,2,0.00,'N',0.00,0.00,'2021-03-15 00:00:00','2020-02-12 11:59:25',NULL),(16,2,19,384.00,2,0.00,'N',0.00,0.00,'2021-01-31 00:00:00','2020-02-12 11:59:25',NULL),(17,2,7,384.00,2,0.00,'N',0.00,0.00,'2020-07-31 00:00:00','2020-02-12 11:59:25',NULL),(18,2,11,384.00,2,0.00,'N',0.00,0.00,'2020-09-30 00:00:00','2020-02-12 11:59:25',NULL),(19,2,3,384.00,2,0.00,'N',0.00,0.00,'2020-05-31 00:00:00','2020-02-12 11:59:25',NULL),(20,2,13,384.00,2,0.00,'N',0.00,0.00,'2020-10-31 00:00:00','2020-02-12 11:59:25',NULL),(21,2,18,384.00,2,0.00,'N',0.00,0.00,'2021-01-15 00:00:00','2020-02-12 11:59:25',NULL),(22,2,8,384.00,2,0.00,'N',0.00,0.00,'2020-08-15 00:00:00','2020-02-12 11:59:25',NULL),(23,2,9,384.00,2,0.00,'N',0.00,0.00,'2020-08-31 00:00:00','2020-02-12 11:59:25',NULL),(24,2,2,384.00,2,0.00,'N',0.00,0.00,'2020-05-15 00:00:00','2020-02-12 11:59:25',NULL),(25,2,17,384.00,2,0.00,'N',0.00,0.00,'2020-12-31 00:00:00','2020-02-12 11:59:25',NULL),(26,2,23,384.00,2,0.00,'N',0.00,0.00,'2021-03-31 00:00:00','2020-02-12 11:59:25',NULL),(27,2,24,384.00,2,0.00,'N',0.00,0.00,'2021-04-15 00:00:00','2020-02-12 11:59:25',NULL),(28,2,20,384.00,2,0.00,'N',0.00,0.00,'2021-02-15 00:00:00','2020-02-12 11:59:25',NULL),(29,2,21,384.00,2,0.00,'N',0.00,0.00,'2021-02-28 00:00:00','2020-02-12 11:59:25',NULL),(30,2,1,384.00,2,0.00,'N',0.00,0.00,'2020-04-30 00:00:00','2020-02-12 11:59:25',NULL),(31,2,25,384.00,2,0.00,'N',0.00,0.00,'2021-04-30 00:00:00','2020-02-12 11:59:25',NULL),(32,2,5,384.00,2,0.00,'N',0.00,0.00,'2020-06-30 00:00:00','2020-02-12 11:59:25',NULL),(33,2,14,384.00,2,0.00,'N',0.00,0.00,'2020-11-15 00:00:00','2020-02-12 11:59:25',NULL),(34,2,15,384.00,2,0.00,'N',0.00,0.00,'2020-11-30 00:00:00','2020-02-12 11:59:25',NULL),(35,2,6,384.00,2,0.00,'N',0.00,0.00,'2020-07-15 00:00:00','2020-02-12 11:59:25',NULL),(36,2,16,384.00,2,0.00,'N',0.00,0.00,'2020-12-15 00:00:00','2020-02-12 11:59:25',NULL),(37,2,26,400.00,2,0.00,'N',0.00,0.00,'2021-05-15 00:00:00','2020-02-12 11:59:25',NULL),(38,2,10,384.00,2,0.00,'N',0.00,0.00,'2020-09-15 00:00:00','2020-02-12 11:59:25',NULL),(39,3,11,375.00,1,0.00,'N',0.00,0.00,'2020-10-15 00:00:00','2020-02-12 12:00:10',NULL),(40,3,2,375.00,1,0.00,'N',0.00,0.00,'2020-05-31 00:00:00','2020-02-12 12:00:10',NULL),(41,3,10,375.00,1,0.00,'N',0.00,0.00,'2020-09-30 00:00:00','2020-02-12 12:00:10',NULL),(42,3,6,375.00,1,0.00,'N',0.00,0.00,'2020-07-31 00:00:00','2020-02-12 12:00:10',NULL),(43,3,3,375.00,1,0.00,'N',0.00,0.00,'2020-06-15 00:00:00','2020-02-12 12:00:10',NULL),(44,3,5,375.00,1,0.00,'N',0.00,0.00,'2020-07-15 00:00:00','2020-02-12 12:00:10',NULL),(45,3,9,375.00,1,0.00,'N',0.00,0.00,'2020-09-15 00:00:00','2020-02-12 12:00:10',NULL),(46,3,8,375.00,1,0.00,'N',0.00,0.00,'2020-08-31 00:00:00','2020-02-12 12:00:10',NULL),(47,3,4,375.00,1,0.00,'N',0.00,0.00,'2020-06-30 00:00:00','2020-02-12 12:00:10',NULL),(48,3,1,375.00,1,0.00,'N',0.00,0.00,'2020-05-15 00:00:00','2020-02-12 12:00:10','2020-02-12 12:04:03'),(49,3,7,375.00,1,0.00,'N',0.00,0.00,'2020-08-15 00:00:00','2020-02-12 12:00:10',NULL),(50,3,12,375.00,1,0.00,'N',0.00,0.00,'2020-10-31 00:00:00','2020-02-12 12:00:10',NULL),(51,4,2,706.00,1,0.00,'N',0.00,0.00,'2020-04-30 00:00:00','2020-02-12 12:12:01',NULL),(52,4,12,706.00,1,0.00,'N',0.00,0.00,'2020-09-30 00:00:00','2020-02-12 12:12:01',NULL),(53,4,3,706.00,1,0.00,'N',0.00,0.00,'2020-05-15 00:00:00','2020-02-12 12:12:01',NULL),(54,4,13,706.00,1,0.00,'N',0.00,0.00,'2020-10-15 00:00:00','2020-02-12 12:12:01',NULL),(55,4,15,706.00,1,0.00,'N',0.00,0.00,'2020-11-15 00:00:00','2020-02-12 12:12:01',NULL),(56,4,14,706.00,1,0.00,'N',0.00,0.00,'2020-10-31 00:00:00','2020-02-12 12:12:01',NULL),(57,4,7,706.00,1,0.00,'N',0.00,0.00,'2020-07-15 00:00:00','2020-02-12 12:12:01',NULL),(58,4,6,706.00,1,0.00,'N',0.00,0.00,'2020-06-30 00:00:00','2020-02-12 12:12:01',NULL),(59,4,11,706.00,1,0.00,'N',0.00,0.00,'2020-09-15 00:00:00','2020-02-12 12:12:01',NULL),(60,4,9,706.00,1,0.00,'N',0.00,0.00,'2020-08-15 00:00:00','2020-02-12 12:12:01',NULL),(61,4,8,706.00,1,0.00,'N',0.00,0.00,'2020-07-31 00:00:00','2020-02-12 12:12:01',NULL),(62,4,4,706.00,1,0.00,'N',0.00,0.00,'2020-05-31 00:00:00','2020-02-12 12:12:01',NULL),(63,4,16,710.00,1,0.00,'N',0.00,0.00,'2020-11-30 00:00:00','2020-02-12 12:12:01',NULL),(64,4,5,706.00,1,0.00,'N',0.00,0.00,'2020-06-15 00:00:00','2020-02-12 12:12:01',NULL),(65,4,1,706.00,1,0.00,'N',0.00,0.00,'2020-04-15 00:00:00','2020-02-12 12:12:01',NULL),(66,4,10,706.00,1,0.00,'N',0.00,0.00,'2020-08-31 00:00:00','2020-02-12 12:12:01',NULL);
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vales`
--

LOCK TABLES `vales` WRITE;
/*!40000 ALTER TABLE `vales` DISABLE KEYS */;
INSERT INTO `vales` VALUES (1,1,1,'00001-01','V',10000.00,'2020-02-11 18:00:00',0.00,0.00,NULL,'N','2020-02-12 11:52:18','2020-02-12 11:59:25'),(2,3,1,'00001-02','V',5000.00,'2020-02-11 18:00:00',500.00,0.00,NULL,'N','2020-02-12 11:52:44','2020-02-12 12:00:10'),(3,5,1,'00001-03','V',8000.00,'2020-02-11 18:00:00',850.00,0.00,NULL,'N','2020-02-12 11:53:08','2020-02-12 11:53:58'),(4,6,1,'00001-04','V',12000.00,'2020-02-11 18:00:00',700.00,0.00,NULL,'N','2020-02-12 12:09:41','2020-02-12 12:12:00');
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
  `DiaPago` int(2) NOT NULL DEFAULT '1',
  `MesInicioPago` int(2) NOT NULL DEFAULT '1',
  `AnioInicioPago` int(4) NOT NULL,
  `PagoMinimo` decimal(7,2) NOT NULL DEFAULT '0.00',
  `PagoFinal` decimal(7,2) NOT NULL DEFAULT '0.00',
  `Fecha` datetime NOT NULL,
  `MontoPagado` decimal(7,2) DEFAULT '0.00',
  `MontoDisponible` decimal(7,2) NOT NULL DEFAULT '0.00',
  `Pagada` varchar(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Intereses` decimal(7,2) DEFAULT '0.00',
  `TasaInteres` int(2) NOT NULL DEFAULT '0',
  `DiasInteres` int(5) NOT NULL DEFAULT '0',
  `PrimerPago` datetime DEFAULT NULL,
  `UltimoPago` datetime DEFAULT NULL,
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaModificacion` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  KEY `IdValeFk_idx` (`IdVale`),
  CONSTRAINT `IdValeFk` FOREIGN KEY (`IdVale`) REFERENCES `vales` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (1,3,7150.00,12,1,3,2020,595.00,605.00,'2020-02-11 18:00:00',0.00,7150.00,'N',0.00,20,14,'2020-03-15 00:00:00','2020-08-31 00:00:00','2020-02-12 11:53:58','2020-02-12 12:03:47'),(2,1,10000.00,26,2,4,2020,384.00,400.00,'2020-02-11 18:00:00',0.00,10000.00,'N',0.00,20,14,'2020-04-30 00:00:00','2021-05-15 00:00:00','2020-02-12 11:59:25',NULL),(3,2,4500.00,12,1,5,2020,375.00,375.00,'2020-02-11 18:00:00',0.00,4500.00,'N',0.00,20,14,'2020-05-15 00:00:00','2020-10-31 00:00:00','2020-02-12 12:00:10','2020-02-12 12:03:47'),(4,4,11300.00,16,1,4,2020,706.00,710.00,'2020-02-11 18:00:00',0.00,11300.00,'N',0.00,20,14,'2020-04-15 00:00:00','2020-11-30 00:00:00','2020-02-12 12:12:00',NULL);
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

-- Dump completed on 2020-02-12 17:54:00
