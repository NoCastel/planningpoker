-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: planningpoker
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `story`
--

DROP TABLE IF EXISTS `story`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `story` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `date` datetime DEFAULT NULL,
  `estimate` int NOT NULL,
  `room_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_story_room_idx` (`room_id`),
  CONSTRAINT `FK_story_room` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story`
--

LOCK TABLES `story` WRITE;
/*!40000 ALTER TABLE `story` DISABLE KEYS */;
INSERT INTO `story` VALUES (1,'Create CRUD for Book','2023-05-07 00:00:00','5',28),(2,'Create Room design','2023-05-07 00:00:00','7',28),(3,'TestStory','2023-05-07 00:00:00','3',28),(4,'story4','2023-05-06 00:00:00','5',46),(5,'story4','2023-05-06 00:00:00','5',47),(6,'aa','2022-11-11 00:00:00','6',47),(7,'aas','2022-11-11 00:00:00','8',47),(8,'aaa','2022-11-11 00:00:00','8',47),(9,'ww','2022-11-11 00:00:00','4',47),(10,'21213','2023-05-18 00:00:00','4',47),(11,'asd','2023-05-18 00:00:00','4',47),(12,'asss','2023-05-18 00:00:00','14',47),(13,'qw','2023-05-18 00:00:00','6',47),(14,'asdds','2023-05-18 00:00:00','6',47),(15,'ad','2023-05-18 00:00:00','6',47),(16,'ass','2023-05-18 00:00:00','4',47),(17,'qwewq','2023-05-18 07:23:06','8',47),(18,'qweqw','2023-05-18 08:17:38','12',47),(19,'geras','2023-05-18 08:44:54','6',47),(20,'zzzz','2023-05-18 08:46:23','10',47),(21,'zzzz','2023-05-18 08:55:01','4',47),(22,'assd','2023-05-18 08:56:49','8',48),(23,'mamba','2023-05-18 08:59:00','8',48),(24,'qww','2023-05-18 09:26:11','10',48),(25,'aaa','2023-05-18 09:26:59','10',48),(26,'aasdd','2023-05-18 09:53:11','14',48),(27,'dddsa','2023-05-18 09:53:34','14',48),(28,'My main story','2023-05-18 09:54:56','2',48),(29,'ssssa','2023-05-18 09:56:05','10',48),(30,'My main story','2023-05-18 10:01:12','8',48),(31,'sss','2023-05-18 10:03:46','6',48),(32,'111','2023-05-18 11:43:59','12',48),(33,'vcxc','2023-05-18 11:45:42','8',48),(34,'dddddddddddd','2023-05-18 11:46:44','6',48),(35,'asdsad','2023-05-18 12:10:20','8',48),(36,'asdsad','2023-05-18 12:10:28','8',48),(37,'My main story','2023-05-18 12:11:03','8',48);
/*!40000 ALTER TABLE `story` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-18 12:16:37
