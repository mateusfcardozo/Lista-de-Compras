-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: db_lista_de_compras
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `lista_has_itens`
--

DROP TABLE IF EXISTS `lista_has_itens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lista_has_itens` (
  `Lista_idLista` int NOT NULL,
  `Itens_idItens` int NOT NULL,
  PRIMARY KEY (`Lista_idLista`,`Itens_idItens`),
  KEY `fk_Lista_has_Itens_Itens1_idx` (`Itens_idItens`),
  KEY `fk_Lista_has_Itens_Lista1_idx` (`Lista_idLista`),
  CONSTRAINT `fk_Lista_has_Itens_Itens1` FOREIGN KEY (`Itens_idItens`) REFERENCES `itens` (`idItens`),
  CONSTRAINT `fk_Lista_has_Itens_Lista1` FOREIGN KEY (`Lista_idLista`) REFERENCES `lista` (`idLista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lista_has_itens`
--

LOCK TABLES `lista_has_itens` WRITE;
/*!40000 ALTER TABLE `lista_has_itens` DISABLE KEYS */;
INSERT INTO `lista_has_itens` VALUES (15,49),(15,50),(16,51),(16,52),(17,53),(17,55);
/*!40000 ALTER TABLE `lista_has_itens` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-16 21:43:33
