-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (arm64)
--
-- Host: 127.0.0.1    Database: drsport
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,NULL,'Phụ Kiện'),(2,NULL,'Cầu Lông'),(3,NULL,'Thiết bị thể thao'),(4,NULL,'Tennis');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (1,1600000,1,1,1),(2,1600000,1,2,1),(3,1999000,1,3,2),(4,1600000,1,4,1),(5,1600000,2,5,1),(6,1999000,1,5,2),(7,1999000,1,6,2),(8,1600000,1,7,1),(9,1999000,1,8,2),(10,1999000,1,9,2),(11,1999000,1,10,2),(12,1600000,1,11,1),(13,1999000,1,11,2),(14,1600000,1,12,1),(15,3300000,1,13,3),(16,300000,1,13,4),(17,1600000,1,14,1),(18,1600000,1,15,1),(19,300000,1,16,4),(20,250000,1,17,10),(21,1999000,1,18,2),(22,1600000,1,19,1),(23,250000,1,19,10),(24,1600000,1,21,1),(25,1600000,1,22,1),(26,1600000,1,23,1),(27,1600000,1,24,1),(28,1600000,1,25,1),(29,1600000,1,26,1),(30,1600000,2,27,1),(31,1999000,1,27,2),(32,1600000,1,28,1),(33,300000,1,28,4),(34,1600000,1,30,1);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'a',1600000,'2024-09-06','0377792002',3,2),(2,'Bắc Giang',1600000,'2024-09-07','0377792002',2,3),(3,'Bắc Giang',1999000,'2024-09-08','0377792002',2,2),(4,'Bắc Giang',1600000,'2024-09-10','0377792002',2,2),(5,'Bắc Giang',5199000,'2024-09-10','0377792002',2,2),(6,'Yên Dũng',1999000,'2024-09-10','0377792002',1,2),(7,'Yên Dũng',1600000,'2024-09-10','0377792002',1,2),(8,'Bắc Giang',1999000,'2024-09-10','0377792002',1,2),(9,'Bắc Giang',1999000,'2024-09-10','0377792002',1,2),(10,'Bắc Giang',1999000,'2024-09-10','0377792002',1,2),(11,'Văn Sơn',3599000,'2024-09-10','0377792002',0,2),(12,'Phú Đa',1600000,'2024-09-10','0377792002',0,2),(13,'Bắc Giang',3045000,'2024-09-14','0377792002',0,2),(14,'Văn Sơn',1600000,'2024-09-17','0377792002',0,2),(15,'Văn Sơn',1600000,'2024-09-17','0377792002',0,2),(16,'Văn Sơn',240000,'2024-09-17','0377792002',3,2),(17,'Bắc Giang',250000,'2024-09-17','0377792002',0,2),(18,'Bắc Giang',1999000,'2024-09-17','0377792002',0,2),(19,'Yên Dũng',1850000,'2024-09-17','0377792002',0,2),(20,'Yên Dũng',0,'2024-09-17','0377792002',0,2),(21,'Yên Dũng',1600000,'2024-09-17','0377792002',0,2),(22,'aa',1600000,'2024-09-17','0377792002',0,2),(23,'Yên Dũng',1600000,'2024-09-17','0377792002',0,2),(24,'a',1600000,'2024-09-17','0377792002',0,2),(25,'a',1600000,'2024-09-17','0377792002',0,2),(26,'Văn Sơn',1600000,'2024-09-17','0377792002',0,NULL),(27,'Văn Sơn',5199000,'2024-09-17','0377792002',0,NULL),(28,'Yên Dũng',1840000,'2024-09-17','0377792002',0,NULL),(29,NULL,0,'2024-09-19',NULL,0,NULL),(30,'Yên Dũng',1600000,'2024-09-23','0377792002',0,2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Quả bóng có một thiết kế nổi bật với các họa tiết màu sắc tươi sáng và sống động, phản ánh sự năng động và sức sống của giải đấu. Các màu sắc thường bao gồm các gam màu tươi sáng và tương phản, như hồng, xanh dương, và vàng.',0,'2024-09-02',_binary '\0',1600000,'bong.jpeg','BÓNG ADIAS FIFA WOMEN WORD CUP 2023',16,NULL,3),(2,'Thiết kế hiện đại: Yonex NanoFlare 370 Speed có màu xanh bắt mắt, nổi bật trên sân đấu. Đường nét vợt được thiết kế tinh tế với công nghệ hiện đại giúp tăng cường tốc độ và sức mạnh trong từng cú đánh.',0,'2024-09-02',_binary '\0',1999000,'01.jpg','Vợt cầu lông Yonex NanoFlare 370 Speed (Blue) chính hãng',3,NULL,2),(3,'Họa tiết ngôi sao nổi tiếng của Champions League vẫn là trung tâm của thiết kế, tượng trưng cho truyền thống và sự xuất sắc của giải đấu.',15,'2024-09-02',_binary '\0',3300000,'bong1.jpg','ADIDAS UCL PRO LONDON 2024',6,NULL,3),(4,'',20,'2024-09-02',_binary '\0',300000,'cl03.jpg','Ống Cầu Lông Nhựa Yonex MAV 300',15,NULL,3),(5,'',5,'2024-09-02',_binary '\0',130000,'tn2.jpg','Bóng Tennis Dunlop ATP Championship (hộp 4 Quả)',6,NULL,3),(6,'',0,'2024-09-02',_binary '\0',1990000,'bong4.jpg','BÓNG NIKE GIẢI NGOẠI HẠNG ANH',6,NULL,3),(7,'',0,'2024-09-02',_binary '\0',1984000,'bong5.jpg','BÓNG PUMA ORBITA SERIE A(2022-2023)',6,NULL,3),(8,'Vợt cầu lông Yonex Nanoray Z Speed có điểm cân bằng ở mức cân bằng, vợt được dành cho lối chơi công thủ toàn diện, linh hoạt.  Độ cứng thân siêu cứng với trọng lượng nặng 3U phù hợp cho người chơi có lực cổ tay khỏe, trình độ khá trở lên.',10,'2024-09-02',_binary '\0',4000000,'caulong1.jpg','Vợt cầu lông Yonex Nanoray Z Speed',7,NULL,2),(9,'Được thiết kế thông số cân bằng và hơi nặng đầu, thích hợp cho những người chơi có lối đánh toàn diện, linh hoạt nhưng vẫn có những đường đập tấn công cắm và uy lực.',0,'2024-09-07',_binary '\0',3990000,'cl.jpg','Vợt Cầu Lông Yonex Arcsaber 11 Pro China Limited',7,NULL,2),(10,'',0,'2024-09-02',_binary '\0',250000,'pk.jpg','Băng gối Lining AQAH222-1',6,NULL,1),(11,'',2,'2024-09-05',_binary '\0',120000,'pk2.jpg','Băng gối Lining AQAH164-1',5,NULL,1),(12,'',0,'2024-09-05',_binary '\0',130000,'pk3.jpg','Băng dán cơ Lining LQAK100',50,NULL,1),(13,'',0,'2024-09-05',_binary '\0',120000,'pk4.jpg','Băng Cổ Tay Lining LDES194-2',5,NULL,1),(14,'',0,'2024-09-05',_binary '\0',85000,'pk6.jpg','Băng dán cơ Kawasaki B3102',15,NULL,1),(15,'',0,'2024-09-05',_binary '\0',125000,'bs01.jpg','Bình xịt lạnh siêu tốc Ligpro (200ml)',14,NULL,1),(16,'',12,'2024-09-05',_binary '\0',135000,'bs02.jpg','Bình xịt nóng Ligpro (200ml)',15,NULL,1),(17,'',0,'2024-09-05',_binary '\0',630000,'bl.jpg','Balo cầu lông Yonex 229BP005U (GC)',5,NULL,1),(18,'',0,'2024-09-05',_binary '\0',119000,'tn.jpg','Bóng Tennis Head Penn Championship (Lon 4 Bóng)',10,NULL,3),(19,'',0,'2024-09-05',_binary '\0',1050000,'cl05.jpg','Ống cầu lông Yonex AS40',12,NULL,3),(20,'',0,'2024-09-05',_binary '\0',5089000,'tn1.jpg','Vợt tennis Yonex Vcore 98L 2024 (285g) chính hãng',5,NULL,4),(21,'',12,'2024-08-05',_binary '\0',5190000,'tn02.jpg','Vợt Tennis Yonex Frame Vcore 98 Plus (305gr) chính hãng',10,NULL,4),(22,'',10,'2024-09-05',_binary '\0',5290000,'tn03.jpg','Vợt tennis Yonex Frame Percept 100 (300gr) chính hãng',14,NULL,4),(23,'',35,'2024-09-05',_binary '\0',4890000,'tn04.jpg','Vợt Tennis Yonex EZONE 98 2022 (305gr) Made In Japan',12,NULL,4),(24,'',15,'2024-09-05',_binary '\0',4999000,'tn05.jpg','Vợt Tennis Yonex VCORE 2021 98L (285g) chính hãng',15,NULL,4),(25,'',5,'2024-09-05',_binary '\0',3150000,'tn06.jpg','Vợt Tennis Yonex Ezone Sonic SKY BLUE (280gr)',4,NULL,4),(26,'',12,'2024-09-04',_binary '\0',2200000,'tnpk.jpg','Túi Tennis Adidas 360 B7 Xám/Trắng/Xanh Chuối',5,NULL,1),(27,'',0,'2024-09-05',_binary '\0',189000,'qc.jpg','Quấn cốt cán tennis Babolat Syntec Evo X1 chính hãng (670067)',20,NULL,1),(28,'',0,'2024-09-05',_binary '\0',130000,'qc01.jpg','Quấn lót cán Yonex AC010CR',50,NULL,1),(29,'',5,'2024-09-06',_binary '\0',4140000,'y01.jpg','Vợt cầu lông Yonex Astrox 99 Pro Trắng',12,NULL,2),(30,'',5,'2024-09-06',_binary '\0',4069000,'y02.jpg','Vợt cầu lông Yonex Arcsaber 7 Pro',12,NULL,2),(31,'',3,'2024-09-06',_binary '\0',4469000,'y03.jpg','Vợt cầu lông Yonex Nanoflare 1000Z',12,NULL,2);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER'),(5,'ROLE_USER'),(6,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user.png','nvh672002@gmail.com','Nguyễn Văn Hùng','$2a$10$/3agy1CbEnpxLAorF6ac2Oovc3XCKZpQTuKo2/rtgSVHO/TAbj7Ka','2024-09-06',_binary ''),(2,'user.png','nguyenvahung672002@gmail.com','Hùng','$2a$10$yIRvJv/hsHvKElHiDZzKi.orG422wVhEUl3Kp5FWl9P/IWj2T8I4q','2024-09-06',_binary ''),(3,'user.png','nvhung672002@gmail.com','Nguyễn Văn Hùng','$2a$10$JXbLYMtpz/aOVN9oSAL2sO0N4Xul90gxVixW0VvHsgAQ9c9ER4Z6C','2024-09-07',_binary ''),(4,'user.png','hung672002@gmail.com','Nguyen Van Hung','$2a$10$UnkNyBawYiDZmlB0yxrY0uVtMVaR8mlB.V8Y5MyJxNa9nL.O2GJ8.','2024-09-23',_binary ''),(5,'user.png','abc@ggmail.com','abc','$2a$10$bhbcva0K3IjpwaArVVrtPuOtAJdTybYcmx19ofmb86uChcHQMIylG','2024-09-23',_binary ''),(6,'user.png','demo@gmail.com','demo','$2a$10$vjM6JltDJIybkhPx1sCZLOxtQA.nm5Ih0wfmFmmE4EYvmjgAKfbEi','2024-09-28',_binary '');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-01 16:31:35
