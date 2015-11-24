-- MySQL dump 10.13  Distrib 5.5.35, for debian-linux-gnu (x86_64)
--
-- Host: sarimner.imcode.com    Database: ivis_dev
-- ------------------------------------------------------
-- Server version	5.5.35-0ubuntu0.12.04.2-log

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
-- Table structure for table `dbo_academic_year`
--

DROP TABLE IF EXISTS `dbo_academic_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_academic_year` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_academic_year`
--

LOCK TABLES `dbo_academic_year` WRITE;
/*!40000 ALTER TABLE `dbo_academic_year` DISABLE KEYS */;
INSERT INTO `dbo_academic_year` VALUES (1,'2014-2015');
/*!40000 ALTER TABLE `dbo_academic_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_after_school_center_section`
--

DROP TABLE IF EXISTS `dbo_after_school_center_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_after_school_center_section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_swedish_ci DEFAULT NULL,
  `schoolId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dk82lrao3ya11l6e0sier9kdk` (`schoolId`),
  CONSTRAINT `FK_dk82lrao3ya11l6e0sier9kdk` FOREIGN KEY (`schoolId`) REFERENCES `dbo_school` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_after_school_center_section`
--

LOCK TABLES `dbo_after_school_center_section` WRITE;
/*!40000 ALTER TABLE `dbo_after_school_center_section` DISABLE KEYS */;
INSERT INTO `dbo_after_school_center_section` VALUES (11,'Norrbacka fritids',11),(22,'S√∂derg√•rdens fritids',22),(33,'Knutteskolan',11),(44,'Knattegymnasiet',44);
/*!40000 ALTER TABLE `dbo_after_school_center_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_app_role`
--

DROP TABLE IF EXISTS `dbo_app_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_app_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_app_role`
--

LOCK TABLES `dbo_app_role` WRITE;
/*!40000 ALTER TABLE `dbo_app_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_app_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_application`
--

DROP TABLE IF EXISTS `dbo_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_application` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `registrationNumber` bigint(20) DEFAULT NULL,
  `applicationFormId` bigint(20) DEFAULT NULL,
  `handledUserId` bigint(20) DEFAULT NULL,
  `regardingUserId` bigint(20) DEFAULT NULL,
  `submittedUserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_q22a4xjrjyyrgf7btu9t9xbyr` (`applicationFormId`),
  KEY `FK_ggftw0m6gnoqo9tcu3g4ycjub` (`handledUserId`),
  KEY `FK_dd4ihsg3xv5h7m3g22a7cjbh4` (`regardingUserId`),
  KEY `FK_8eajjatk2h6pvj2kj6hn39unh` (`submittedUserId`),
  CONSTRAINT `FK_8eajjatk2h6pvj2kj6hn39unh` FOREIGN KEY (`submittedUserId`) REFERENCES `dbo_user` (`id`),
  CONSTRAINT `FK_dd4ihsg3xv5h7m3g22a7cjbh4` FOREIGN KEY (`regardingUserId`) REFERENCES `dbo_user` (`id`),
  CONSTRAINT `FK_ggftw0m6gnoqo9tcu3g4ycjub` FOREIGN KEY (`handledUserId`) REFERENCES `dbo_person` (`id`),
  CONSTRAINT `FK_q22a4xjrjyyrgf7btu9t9xbyr` FOREIGN KEY (`applicationFormId`) REFERENCES `dbo_application_form` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dbo_application_form`
--

DROP TABLE IF EXISTS `dbo_application_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_application_form` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_swedish_ci DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `dbo_application_form_question`
--

DROP TABLE IF EXISTS `dbo_application_form_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_application_form_question` (
  `applicationFormId` bigint(20) NOT NULL,
  `sortOrder` int(11) DEFAULT NULL,
  `text` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `value` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `xsdElementName` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  UNIQUE KEY `UK_ixt9ckfam7urdab9yfynwbsmd` (`applicationFormId`,`xsdElementName`),
  CONSTRAINT `FK_g6uls7o02g7mbc4uewu84jtni` FOREIGN KEY (`applicationFormId`) REFERENCES `dbo_application_form` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `dbo_guardian`
--

DROP TABLE IF EXISTS `dbo_guardian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_guardian` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `personId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9dg4iklty1mhyqyi6cprsj3m9` (`personId`),
  CONSTRAINT `FK_9dg4iklty1mhyqyi6cprsj3m9` FOREIGN KEY (`personId`) REFERENCES `dbo_person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_guardian`
--

LOCK TABLES `dbo_guardian` WRITE;
/*!40000 ALTER TABLE `dbo_guardian` DISABLE KEYS */;
INSERT INTO `dbo_guardian` VALUES (11,11),(44,44),(77,77),(88,88);
/*!40000 ALTER TABLE `dbo_guardian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_oauth_access_token`
--

DROP TABLE IF EXISTS `dbo_oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_oauth_access_token` (
  `id` varchar(36) COLLATE utf8_swedish_ci NOT NULL,
  `expiration` datetime DEFAULT NULL,
  `refreshToken` tinyblob,
  `tokenType` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_oauth_access_token`
--

LOCK TABLES `dbo_oauth_access_token` WRITE;
/*!40000 ALTER TABLE `dbo_oauth_access_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_oauth_client_additional_info`
--

DROP TABLE IF EXISTS `dbo_oauth_client_additional_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_oauth_client_additional_info` (
  `clientId` varchar(36) COLLATE utf8_swedish_ci NOT NULL,
  `value` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`clientId`,`name`),
  CONSTRAINT `FK_qxqrox0txwsybi68nkybu0il1` FOREIGN KEY (`clientId`) REFERENCES `dbo_oauth_client_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_oauth_client_additional_info`
--

LOCK TABLES `dbo_oauth_client_additional_info` WRITE;
/*!40000 ALTER TABLE `dbo_oauth_client_additional_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_oauth_client_additional_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_oauth_client_details`
--

DROP TABLE IF EXISTS `dbo_oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_oauth_client_details` (
  `id` varchar(36) COLLATE utf8_swedish_ci NOT NULL,
  `accessTokenValiditySeconds` int(11) DEFAULT NULL,
  `clientSecret` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `refreshTokenValiditySeconds` int(11) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8h378oqeeddhikgaujblo8rjo` (`name`),
  KEY `FK_1psav7lymmafx2r2dknf7a3l7` (`ownerId`),
  CONSTRAINT `FK_1psav7lymmafx2r2dknf7a3l7` FOREIGN KEY (`ownerId`) REFERENCES `dbo_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_oauth_client_details`
--

LOCK TABLES `dbo_oauth_client_details` WRITE;
/*!40000 ALTER TABLE `dbo_oauth_client_details` DISABLE KEYS */;
INSERT INTO `dbo_oauth_client_details` VALUES ('08d32c33-91cf-4452-8be8-4d120fbc504e',60,'secret','ivis',600,3),('ff11397c-3e3b-4398-80a9-feba203f1928',6000000,'secret','admin',6000000,1);
/*!40000 ALTER TABLE `dbo_oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_oauth_client_garant_types`
--

DROP TABLE IF EXISTS `dbo_oauth_client_garant_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_oauth_client_garant_types` (
  `clientId` varchar(36) COLLATE utf8_swedish_ci NOT NULL,
  `authorizedGrantType` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  KEY `FK_4re3lppybvgx5odiv6c4j876i` (`clientId`),
  CONSTRAINT `FK_4re3lppybvgx5odiv6c4j876i` FOREIGN KEY (`clientId`) REFERENCES `dbo_oauth_client_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_oauth_client_garant_types`
--

LOCK TABLES `dbo_oauth_client_garant_types` WRITE;
/*!40000 ALTER TABLE `dbo_oauth_client_garant_types` DISABLE KEYS */;
INSERT INTO `dbo_oauth_client_garant_types` VALUES ('08d32c33-91cf-4452-8be8-4d120fbc504e','password'),('08d32c33-91cf-4452-8be8-4d120fbc504e','authorization_code'),('ff11397c-3e3b-4398-80a9-feba203f1928','authorization_code'),('ff11397c-3e3b-4398-80a9-feba203f1928','implicit'),('ff11397c-3e3b-4398-80a9-feba203f1928','refresh_token'),('ff11397c-3e3b-4398-80a9-feba203f1928','client_credentials'),('ff11397c-3e3b-4398-80a9-feba203f1928','password');
/*!40000 ALTER TABLE `dbo_oauth_client_garant_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_oauth_client_redirect_uris`
--

DROP TABLE IF EXISTS `dbo_oauth_client_redirect_uris`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_oauth_client_redirect_uris` (
  `clientId` varchar(36) COLLATE utf8_swedish_ci NOT NULL,
  `registeredRedirectUris` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  KEY `FK_9ikiyalo5ql0ioqad10h95k7o` (`clientId`),
  CONSTRAINT `FK_9ikiyalo5ql0ioqad10h95k7o` FOREIGN KEY (`clientId`) REFERENCES `dbo_oauth_client_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_oauth_client_redirect_uris`
--

LOCK TABLES `dbo_oauth_client_redirect_uris` WRITE;
/*!40000 ALTER TABLE `dbo_oauth_client_redirect_uris` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_oauth_client_redirect_uris` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_oauth_client_resources`
--

DROP TABLE IF EXISTS `dbo_oauth_client_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_oauth_client_resources` (
  `clientId` varchar(36) COLLATE utf8_swedish_ci NOT NULL,
  `resourceId` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  KEY `FK_hrr8deo3qdi34aq95dryf2cp9` (`clientId`),
  CONSTRAINT `FK_hrr8deo3qdi34aq95dryf2cp9` FOREIGN KEY (`clientId`) REFERENCES `dbo_oauth_client_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_oauth_client_resources`
--

LOCK TABLES `dbo_oauth_client_resources` WRITE;
/*!40000 ALTER TABLE `dbo_oauth_client_resources` DISABLE KEYS */;
INSERT INTO `dbo_oauth_client_resources` VALUES ('08d32c33-91cf-4452-8be8-4d120fbc504e','ivis'),('ff11397c-3e3b-4398-80a9-feba203f1928','ivis');
/*!40000 ALTER TABLE `dbo_oauth_client_resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_oauth_client_role`
--

DROP TABLE IF EXISTS `dbo_oauth_client_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_oauth_client_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(100) COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bfiw4332tg0s2s3p6p61nxppv` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_oauth_client_role`
--

LOCK TABLES `dbo_oauth_client_role` WRITE;
/*!40000 ALTER TABLE `dbo_oauth_client_role` DISABLE KEYS */;
INSERT INTO `dbo_oauth_client_role` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_USER');
/*!40000 ALTER TABLE `dbo_oauth_client_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_oauth_client_roles_cross`
--

DROP TABLE IF EXISTS `dbo_oauth_client_roles_cross`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_oauth_client_roles_cross` (
  `clientId` varchar(36) COLLATE utf8_swedish_ci NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`clientId`,`roleId`),
  KEY `FK_nha1sbynjgxhx3xq87rghvpvg` (`roleId`),
  CONSTRAINT `FK_9qrwga2b2ogdqckspabwfphal` FOREIGN KEY (`clientId`) REFERENCES `dbo_oauth_client_details` (`id`),
  CONSTRAINT `FK_nha1sbynjgxhx3xq87rghvpvg` FOREIGN KEY (`roleId`) REFERENCES `dbo_oauth_client_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_oauth_client_roles_cross`
--

LOCK TABLES `dbo_oauth_client_roles_cross` WRITE;
/*!40000 ALTER TABLE `dbo_oauth_client_roles_cross` DISABLE KEYS */;
INSERT INTO `dbo_oauth_client_roles_cross` VALUES ('08d32c33-91cf-4452-8be8-4d120fbc504e',1),('ff11397c-3e3b-4398-80a9-feba203f1928',1),('08d32c33-91cf-4452-8be8-4d120fbc504e',3),('ff11397c-3e3b-4398-80a9-feba203f1928',3);
/*!40000 ALTER TABLE `dbo_oauth_client_roles_cross` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_oauth_client_scope`
--

DROP TABLE IF EXISTS `dbo_oauth_client_scope`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_oauth_client_scope` (
  `clientId` varchar(36) COLLATE utf8_swedish_ci NOT NULL,
  `scope` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  KEY `FK_c2jhxcfilx9xeqnxfe27s55hk` (`clientId`),
  CONSTRAINT `FK_c2jhxcfilx9xeqnxfe27s55hk` FOREIGN KEY (`clientId`) REFERENCES `dbo_oauth_client_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_oauth_client_scope`
--

LOCK TABLES `dbo_oauth_client_scope` WRITE;
/*!40000 ALTER TABLE `dbo_oauth_client_scope` DISABLE KEYS */;
INSERT INTO `dbo_oauth_client_scope` VALUES ('08d32c33-91cf-4452-8be8-4d120fbc504e','read'),('08d32c33-91cf-4452-8be8-4d120fbc504e','write'),('08d32c33-91cf-4452-8be8-4d120fbc504e','execute'),('ff11397c-3e3b-4398-80a9-feba203f1928','read'),('ff11397c-3e3b-4398-80a9-feba203f1928','write'),('ff11397c-3e3b-4398-80a9-feba203f1928','execute');
/*!40000 ALTER TABLE `dbo_oauth_client_scope` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_oauth_token_scope`
--

DROP TABLE IF EXISTS `dbo_oauth_token_scope`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_oauth_token_scope` (
  `tokenId` varchar(36) COLLATE utf8_swedish_ci NOT NULL,
  `scope` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  KEY `FK_897heqdostkufbivio6t50yaq` (`tokenId`),
  CONSTRAINT `FK_897heqdostkufbivio6t50yaq` FOREIGN KEY (`tokenId`) REFERENCES `dbo_oauth_access_token` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_oauth_token_scope`
--

LOCK TABLES `dbo_oauth_token_scope` WRITE;
/*!40000 ALTER TABLE `dbo_oauth_token_scope` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_oauth_token_scope` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_person`
--

DROP TABLE IF EXISTS `dbo_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `lastName` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `personalId` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_person`
--

LOCK TABLES `dbo_person` WRITE;
/*!40000 ALTER TABLE `dbo_person` DISABLE KEYS */;
INSERT INTO `dbo_person` VALUES
(1,'Vitaliy','Sereda','850717-5019'),
(3,'Henry','Ivis','2222'),
(5,'Sergey','User','3333'),
(7,'Vitaly','Orlov','4444'),
(11,'Kalle','Karlsson','690202-4733'),
(22,'Lotta','Svensson','740126-2014'),
(33,'Anna','Jansson','740126-2014'),
(44,'Ulla','Larsson','890420-5781'),
(55,'Camilla','Olsson','540524-4780'),
(66,'Claes','Larsson','710603-7349'),
(77,'Lars Olof','Larsson','930201-0849'),
(88,'Anna','Pettersson','670618-4667'),
(99,'Knut','Johansen','510930-2165');
/*!40000 ALTER TABLE `dbo_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_person_address`
--

DROP TABLE IF EXISTS `dbo_person_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_person_address` (
  `ownerId` bigint(20) NOT NULL,
  `addressType` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `careOf` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `city` varchar(50) COLLATE utf8_swedish_ci DEFAULT NULL,
  `municipalityCode` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `postalCode` int(11) DEFAULT NULL,
  `propertyDescription` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `street` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `street2` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `typeKey` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`ownerId`,`typeKey`),
  CONSTRAINT `FK_81ybvpdpwwh396c5h5hfj5125` FOREIGN KEY (`ownerId`) REFERENCES `dbo_person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_person_address`
--

LOCK TABLES `dbo_person_address` WRITE;
/*!40000 ALTER TABLE `dbo_person_address` DISABLE KEYS */;
INSERT INTO `dbo_person_address` VALUES (11,'REGISTERED','Adr√©n','VISBY',NULL,62157,NULL,'Storgatan 3',NULL,'REGISTERED'),(22,'REGISTERED',NULL,'VISBY',NULL,62157,NULL,'√Ögatan 12',NULL,'REGISTERED'),(33,'REGISTERED',NULL,'VISBY',NULL,62134,NULL,'√ñsterv√§g 14',NULL,'REGISTERED'),(44,'REGISTERED',NULL,'LJUGARN',NULL,62116,NULL,'Lilla gr√§nd 15',NULL,'REGISTERED'),(55,'REGISTERED','Lagergren','VISBY',NULL,62121,NULL,'S√∂derv√§g 56',NULL,'REGISTERED'),(66,'REGISTERED',NULL,'VISBY',NULL,62117,NULL,'V√§stra tr√§dg√•rdsgatan 23',NULL,'REGISTERED'),(77,'REGISTERED',NULL,'VISBY',NULL,62157,NULL,'√Ögatan 35',NULL,'REGISTERED'),(77,'RESIDENTIAL',NULL,'VISBY',NULL,62157,NULL,'√Ögatan 35',NULL,'RESIDENTIAL'),(88,'REGISTERED',NULL,'VISBY',NULL,62157,NULL,'√Ögatan 12',NULL,'REGISTERED'),(99,'REGISTERED',NULL,'VISBY',NULL,62115,NULL,'√ñstra J√§rnv√§gsgatan 12',NULL,'REGISTERED');
/*!40000 ALTER TABLE `dbo_person_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_person_email`
--

DROP TABLE IF EXISTS `dbo_person_email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_person_email` (
  `ownerId` bigint(20) NOT NULL,
  `address` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `communicationType` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `typeKey` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`ownerId`,`typeKey`),
  CONSTRAINT `FK_jre2js05ybb8wv7e96a4x1b6l` FOREIGN KEY (`ownerId`) REFERENCES `dbo_person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_person_email`
--

LOCK TABLES `dbo_person_email` WRITE;
/*!40000 ALTER TABLE `dbo_person_email` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_person_email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_person_phone`
--

DROP TABLE IF EXISTS `dbo_person_phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_person_phone` (
  `ownerId` bigint(20) NOT NULL,
  `communicationType` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `number` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `typeKey` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`ownerId`,`typeKey`),
  CONSTRAINT `FK_tka3tn7mmn0thxvqfr3gdpe6q` FOREIGN KEY (`ownerId`) REFERENCES `dbo_person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_person_phone`
--

LOCK TABLES `dbo_person_phone` WRITE;
/*!40000 ALTER TABLE `dbo_person_phone` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_person_phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_pupil`
--

DROP TABLE IF EXISTS `dbo_pupil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_pupil` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `classPlacementFrom` date DEFAULT NULL,
  `classPlacementTo` date DEFAULT NULL,
  `academicYearId` bigint(20) DEFAULT NULL,
  `afterSchoolCenterSectionId` bigint(20) DEFAULT NULL,
  `contactPersonId` bigint(20) DEFAULT NULL,
  `personId` bigint(20) DEFAULT NULL,
  `schoolId` bigint(20) DEFAULT NULL,
  `schoolClassId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7psuch8mc1ohi0y6lwjhdolo7` (`academicYearId`),
  KEY `FK_jcudqw97opdbi6typiptbvlk` (`afterSchoolCenterSectionId`),
  KEY `FK_q7cae5m34wlkjhuwbnsnak5ib` (`contactPersonId`),
  KEY `FK_3g63j1dy7l237hjl1ltordpcn` (`personId`),
  KEY `FK_5dc0rg0lbvwewmduyh8sp6drf` (`schoolId`),
  KEY `FK_q3pyo122boh4kx620uocksusj` (`schoolClassId`),
  CONSTRAINT `FK_q3pyo122boh4kx620uocksusj` FOREIGN KEY (`schoolClassId`) REFERENCES `dbo_school_class` (`id`),
  CONSTRAINT `FK_3g63j1dy7l237hjl1ltordpcn` FOREIGN KEY (`personId`) REFERENCES `dbo_person` (`id`),
  CONSTRAINT `FK_5dc0rg0lbvwewmduyh8sp6drf` FOREIGN KEY (`schoolId`) REFERENCES `dbo_school` (`id`),
  CONSTRAINT `FK_7psuch8mc1ohi0y6lwjhdolo7` FOREIGN KEY (`academicYearId`) REFERENCES `dbo_academic_year` (`id`),
  CONSTRAINT `FK_jcudqw97opdbi6typiptbvlk` FOREIGN KEY (`afterSchoolCenterSectionId`) REFERENCES `dbo_after_school_center_section` (`id`),
  CONSTRAINT `FK_q7cae5m34wlkjhuwbnsnak5ib` FOREIGN KEY (`contactPersonId`) REFERENCES `dbo_person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_pupil`
--

LOCK TABLES `dbo_pupil` WRITE;
/*!40000 ALTER TABLE `dbo_pupil` DISABLE KEYS */;
INSERT INTO `dbo_pupil` VALUES (1,'2015-11-06 11:14:34',NULL,NULL,NULL,1,11,NULL,22,NULL,11),(22,NULL,NULL,NULL,NULL,NULL,11,NULL,22,NULL,11),(33,NULL,NULL,NULL,NULL,NULL,22,NULL,33,NULL,33),(55,NULL,NULL,NULL,NULL,NULL,44,NULL,55,NULL,33),(66,NULL,NULL,NULL,NULL,NULL,11,NULL,22,NULL,11),(99,NULL,NULL,NULL,NULL,NULL,11,NULL,22,NULL,11);
/*!40000 ALTER TABLE `dbo_pupil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_pupil_after_school_center_schema`
--

DROP TABLE IF EXISTS `dbo_pupil_after_school_center_schema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_pupil_after_school_center_schema` (
  `ownerId` bigint(20) NOT NULL,
  `afrerSchoolSectionId` bigint(20) DEFAULT NULL,
  `dayOfWeek` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `useAfterSchool` bit(1) DEFAULT NULL,
  `useBeforeSchool` bit(1) DEFAULT NULL,
  UNIQUE KEY `UK_gpdfl0moqbphh4j5rovjiqbdh` (`ownerId`,`afrerSchoolSectionId`,`dayOfWeek`),
  KEY `FK_oruyhrfxyn4bmyqvsjrufux02` (`afrerSchoolSectionId`),
  CONSTRAINT `FK_nwyxq3c4q9b7xv0sdq6t4po15` FOREIGN KEY (`ownerId`) REFERENCES `dbo_pupil` (`id`),
  CONSTRAINT `FK_oruyhrfxyn4bmyqvsjrufux02` FOREIGN KEY (`afrerSchoolSectionId`) REFERENCES `dbo_after_school_center_section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_pupil_after_school_center_schema`
--

LOCK TABLES `dbo_pupil_after_school_center_schema` WRITE;
/*!40000 ALTER TABLE `dbo_pupil_after_school_center_schema` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_pupil_after_school_center_schema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_pupil_guardians_cross`
--

DROP TABLE IF EXISTS `dbo_pupil_guardians_cross`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_pupil_guardians_cross` (
  `pupilId` bigint(20) NOT NULL,
  `guardianId` bigint(20) NOT NULL,
  PRIMARY KEY (`pupilId`,`guardianId`),
  KEY `FK_n5tak46th78b0qsp3agp74tds` (`guardianId`),
  CONSTRAINT `FK_jtlquwt8i9u3wiw0vvt6fmu5a` FOREIGN KEY (`pupilId`) REFERENCES `dbo_pupil` (`id`),
  CONSTRAINT `FK_n5tak46th78b0qsp3agp74tds` FOREIGN KEY (`guardianId`) REFERENCES `dbo_guardian` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_pupil_guardians_cross`
--

LOCK TABLES `dbo_pupil_guardians_cross` WRITE;
/*!40000 ALTER TABLE `dbo_pupil_guardians_cross` DISABLE KEYS */;
INSERT INTO `dbo_pupil_guardians_cross` VALUES (33,11),(55,11),(99,11),(99,44),(66,77),(22,88),(66,88);
/*!40000 ALTER TABLE `dbo_pupil_guardians_cross` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_role`
--

DROP TABLE IF EXISTS `dbo_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(100) COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_atgkhdmwxgw0cva332mo6p6mn` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_role`
--

LOCK TABLES `dbo_role` WRITE;
/*!40000 ALTER TABLE `dbo_role` DISABLE KEYS */;
INSERT INTO `dbo_role` VALUES (3,'ROLE_ADMIN'),(1,'ROLE_ANONYMOUS'),(7,'ROLE_DEVELOPER'),(5,'ROLE_USER');
/*!40000 ALTER TABLE `dbo_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_school`
--

DROP TABLE IF EXISTS `dbo_school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_school` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_swedish_ci DEFAULT NULL,
  `schoolId` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_school`
--

LOCK TABLES `dbo_school` WRITE;
/*!40000 ALTER TABLE `dbo_school` DISABLE KEYS */;
INSERT INTO `dbo_school` VALUES (1,'Norrbackaskolan','A 12345'),(3,'Gr√•boskolan','123'),(11,'Norrbackaskolan','A 12345'),(22,'S√∂derv√§rnsskolan','B 23456'),(33,'Knutteskolan','C 34578'),(44,'Knattegymnasiet','D 67893');
/*!40000 ALTER TABLE `dbo_school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_school_class`
--

DROP TABLE IF EXISTS `dbo_school_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_school_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_swedish_ci DEFAULT NULL,
  `schoolDayEnd` time DEFAULT NULL,
  `schoolDayStart` time DEFAULT NULL,
  `school` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_901ivo58fpmt9s29wbtcgrc8m` (`school`),
  CONSTRAINT `FK_901ivo58fpmt9s29wbtcgrc8m` FOREIGN KEY (`school`) REFERENCES `dbo_school` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_school_class`
--

LOCK TABLES `dbo_school_class` WRITE;
/*!40000 ALTER TABLE `dbo_school_class` DISABLE KEYS */;
INSERT INTO `dbo_school_class` VALUES (1,'A1-1','15:00:00','08:00:00',1),(3,'A2-1','18:00:00','12:00:00',1),(11,'7C','15:00:00','08:00:00',11),(22,'7S','15:00:00','08:00:00',11),(33,'Hum2','15:00:00','08:00:00',22),(44,'Nat2','15:00:00','08:00:00',33),(55,'Nat5','15:00:00','08:00:00',44),(66,'9A','15:00:00','08:00:00',44);
/*!40000 ALTER TABLE `dbo_school_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_school_class_diaries`
--

DROP TABLE IF EXISTS `dbo_school_class_diaries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_school_class_diaries` (
  `ownerId` bigint(20) NOT NULL,
  `dayOfWeek` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  `endTime` time DEFAULT NULL,
  `startTime` time DEFAULT NULL,
  UNIQUE KEY `UK_e6rmu86m85i981un711ybkicn` (`dayOfWeek`),
  KEY `FK_cmhhaqi97ay2c3nedtjj9xjfh` (`ownerId`),
  CONSTRAINT `FK_cmhhaqi97ay2c3nedtjj9xjfh` FOREIGN KEY (`ownerId`) REFERENCES `dbo_school_class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_school_class_diaries`
--

LOCK TABLES `dbo_school_class_diaries` WRITE;
/*!40000 ALTER TABLE `dbo_school_class_diaries` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_school_class_diaries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_school_service_cross`
--

DROP TABLE IF EXISTS `dbo_school_service_cross`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_school_service_cross` (
  `School_id` bigint(20) NOT NULL,
  `services` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  KEY `FK_fifctstm9xlbbfa4wqum2vk1w` (`School_id`),
  CONSTRAINT `FK_fifctstm9xlbbfa4wqum2vk1w` FOREIGN KEY (`School_id`) REFERENCES `dbo_school` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_school_service_cross`
--

LOCK TABLES `dbo_school_service_cross` WRITE;
/*!40000 ALTER TABLE `dbo_school_service_cross` DISABLE KEYS */;
INSERT INTO `dbo_school_service_cross` VALUES (1,'ELEMENTARY_SCHOOL'),(1,'SECONDARY_SCHOOL'),(1,'AFTER_SCHOOL_CENTER'),(3,'ELEMENTARY_SCHOOL'),(3,'SECONDARY_SCHOOL');
/*!40000 ALTER TABLE `dbo_school_service_cross` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_school_transport`
--

DROP TABLE IF EXISTS `dbo_school_transport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_school_transport` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_school_transport`
--

LOCK TABLES `dbo_school_transport` WRITE;
/*!40000 ALTER TABLE `dbo_school_transport` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_school_transport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_semester`
--

DROP TABLE IF EXISTS `dbo_semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_semester` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_swedish_ci DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `academicYearId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1vb9g33hq6kd4afakta8t1pbp` (`academicYearId`),
  CONSTRAINT `FK_1vb9g33hq6kd4afakta8t1pbp` FOREIGN KEY (`academicYearId`) REFERENCES `dbo_academic_year` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_semester`
--

LOCK TABLES `dbo_semester` WRITE;
/*!40000 ALTER TABLE `dbo_semester` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_statement_form`
--

DROP TABLE IF EXISTS `dbo_statement_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_statement_form` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_swedish_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_statement_form`
--

LOCK TABLES `dbo_statement_form` WRITE;
/*!40000 ALTER TABLE `dbo_statement_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_statement_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_truancy`
--

DROP TABLE IF EXISTS `dbo_truancy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_truancy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `endDate` date DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `pupilId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8utfmaswe2gyxldeexi3sn33` (`pupilId`),
  CONSTRAINT `FK_8utfmaswe2gyxldeexi3sn33` FOREIGN KEY (`pupilId`) REFERENCES `dbo_pupil` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_truancy`
--

LOCK TABLES `dbo_truancy` WRITE;
/*!40000 ALTER TABLE `dbo_truancy` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_truancy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_user`
--

DROP TABLE IF EXISTS `dbo_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) COLLATE utf8_swedish_ci NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `personId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b7urn18yrfy3lrcqxql0c6jqh` (`name`),
  KEY `FK_kv4fjp8lptfr8bloo9cpbb0mm` (`personId`),
  CONSTRAINT `FK_kv4fjp8lptfr8bloo9cpbb0mm` FOREIGN KEY (`personId`) REFERENCES `dbo_person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_user`
--

LOCK TABLES `dbo_user` WRITE;
/*!40000 ALTER TABLE `dbo_user` DISABLE KEYS */;
INSERT INTO `dbo_user` VALUES (1,'admin','','pass',1),(3,'ivis','','111',3),(5,'user','','111',5),(7,'vitaly','','',7);
/*!40000 ALTER TABLE `dbo_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_user_roles_cross`
--

DROP TABLE IF EXISTS `dbo_user_roles_cross`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbo_user_roles_cross` (
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `FK_kk478dbe79wodb13sbpa9emj7` (`roleId`),
  CONSTRAINT `FK_drmncao8lmw32t5k5eeplcx0d` FOREIGN KEY (`userId`) REFERENCES `dbo_user` (`id`),
  CONSTRAINT `FK_kk478dbe79wodb13sbpa9emj7` FOREIGN KEY (`roleId`) REFERENCES `dbo_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_user_roles_cross`
--

LOCK TABLES `dbo_user_roles_cross` WRITE;
/*!40000 ALTER TABLE `dbo_user_roles_cross` DISABLE KEYS */;
INSERT INTO `dbo_user_roles_cross` VALUES (1,3),(1,5),(3,5),(5,5),(1,7),(3,7),(7,7);
/*!40000 ALTER TABLE `dbo_user_roles_cross` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(36) COLLATE utf8_swedish_ci NOT NULL,
  `authentication` longblob,
  `authentication_id` varchar(36) COLLATE utf8_swedish_ci DEFAULT NULL,
  `client_id` varchar(36) COLLATE utf8_swedish_ci DEFAULT NULL,
  `refresh_token` varchar(36) COLLATE utf8_swedish_ci DEFAULT NULL,
  `token` longblob,
  `user_name` varchar(255) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_access_token`
--

LOCK TABLES `oauth_access_token` WRITE;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
INSERT INTO `oauth_access_token` VALUES ('18f47b04b97d1381bf9009b324c4e19d','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0com.imcode.entities.Role8q÷/s}\\‡\0\0xr\04com.imcode.entities.superclasses.AbstractNamedEntityÉMñé(Õx\0L\0namet\0Ljava/lang/String;xr\01com.imcode.entities.superclasses.AbstractIdEntityß@ˆ¨p% X\0L\0idt\0Ljava/io/Serializable;xpsr\0java.lang.Long;ã‰êÃè#ﬂ\0J\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0\0\0\0\0t\0\nROLE_ADMINsq\0~\0\rsq\0~\0\0\0\0\0\0\0\0t\0	ROLE_USERsq\0~\0\rsq\0~\0\0\0\0\0\0\0\0t\0ROLE_DEVELOPERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0$ff11397c-3e3b-4398-80a9-feba203f1928sr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0admint\0scopet\0readxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0q\0~\0,xsq\0~\00w\0\0\0?@\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xpsr\0/org.hibernate.collection.internal.PersistentSetÜ‰˝°ÚÓ‡Ø\0L\0setq\0~\0xr\0>org.hibernate.collection.internal.AbstractPersistentCollectionÑJ~wÆç\0	Z\0allowLoadOutsideTransactionI\0\ncachedSizeZ\0dirtyZ\0initializedL\0keyq\0~\0L\0ownerq\0~\0L\0roleq\0~\0L\0sessionFactoryUuidq\0~\0L\0storedSnapshotq\0~\0xp\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928sr\0+com.imcode.entities.oauth2.JpaClientDetails…Sç∑¸^;È\0\rL\0\ZaccessTokenValiditySecondst\0Ljava/lang/Integer;L\0additionalInformationq\0~\0L\0authoritiesq\0~\0L\0authorizedGrantTypesq\0~\0L\0autoApproveScopesq\0~\0L\0clientIdq\0~\0L\0clientSecretq\0~\0L\0nameq\0~\0L\0ownert\0\ZLcom/imcode/entities/User;L\0refreshTokenValiditySecondsq\0~\09L\0registeredRedirectUrisq\0~\0L\0resourceIdsq\0~\0L\0scopeq\0~\0xpsr\0java.lang.Integer‚†§˜Åá8\0I\0valuexq\0~\0\0\0Xsr\0/org.hibernate.collection.internal.PersistentMapˇâ‘e¯Ï!\0L\0mapq\0~\0xq\0~\05\0ˇˇˇˇ\0q\0~\0\"q\0~\0;t\0Acom.imcode.entities.oauth2.JpaClientDetails.additionalInformationpsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\04\0ˇˇˇˇ\0q\0~\0\"q\0~\0;t\07com.imcode.entities.oauth2.JpaClientDetails.authoritiespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\00w\0\0\0?@\0\0\0\0\0\0xsq\0~\04\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928q\0~\0;t\0@com.imcode.entities.oauth2.JpaClientDetails.authorizedGrantTypespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0implicitq\0~\0Kt\0\rrefresh_tokenq\0~\0Lt\0client_credentialsq\0~\0Mt\0passwordq\0~\0Nt\0authorization_codeq\0~\0Oxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0Kq\0~\0Lq\0~\0Mq\0~\0Nq\0~\0Oxpq\0~\0\"t\0secrett\0adminsr\0com.imcode.entities.User3ÏET\\\0L\0confirmPasswordq\0~\0L\0enabledt\0Ljava/lang/Boolean;L\0passwordq\0~\0L\0persont\0Lcom/imcode/entities/Person;L\0rolesq\0~\0xq\0~\0sq\0~\0\0\0\0\0\0\0\0t\0admint\0\0sr\0java.lang.BooleanÕ rÄ’ú˙Ó\0Z\0valuexpt\0passsr\0\Zcom.imcode.entities.PersonR›±–ˆÊ&\0L\0	addressesq\0~\0L\0emailsq\0~\0L\0	firstNameq\0~\0L\0lastNameq\0~\0L\0\npersonalIdq\0~\0L\0phonesq\0~\0xq\0~\0q\0~\0Wsq\0~\0>\0ˇˇˇˇ\0q\0~\0Wq\0~\0^t\0$com.imcode.entities.Person.addressespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0>\0ˇˇˇˇ\0q\0~\0Wq\0~\0^t\0!com.imcode.entities.Person.emailspsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0Vitaliyt\0Seredat\0850717-5019sq\0~\0>\0ˇˇˇˇ\0q\0~\0Wq\0~\0^t\0!com.imcode.entities.Person.phonespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\04\0ˇˇˇˇ\0q\0~\0Wq\0~\0Vt\0com.imcode.entities.User.rolespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0q\0~\0\Zq\0~\0\Zxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0q\0~\0q\0~\0\Zxsq\0~\0<\0\0psq\0~\04\0ˇˇˇˇ\0q\0~\0\"q\0~\0;t\0Bcom.imcode.entities.oauth2.JpaClientDetails.registeredRedirectUrispsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\00w\0\0\0?@\0\0\0\0\0\0xq\0~\06sq\0~\04\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928q\0~\0;t\01com.imcode.entities.oauth2.JpaClientDetails.scopepsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0writeq\0~\0{t\0executeq\0~\0|t\0readq\0~\0}xsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0}q\0~\0{q\0~\0|xt\07com.imcode.entities.oauth2.JpaClientDetails.resourceIdspsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0ivisq\0~\0Åxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0Åxsq\0~\00w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0@\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0q\0~\0q\0~\0\Zxq\0~\0ásr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0\'q\0~\0(q\0~\0)q\0~\0*q\0~\0+q\0~\0,x\0pq\0~\0V','e1779cc0d364b7c5a8398cfe341281dd','ff11397c-3e3b-4398-80a9-feba203f1928','d4505eab8d2ef6049394905b66c62211','¨Ì\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken≤û6$˙Œ\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0	client_idt\0$ff11397c-3e3b-4398-80a9-feba203f1928x\0sr\0java.util.DatehjÅKYt\0\0xpw\0\0Q¯€êxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ﬂGcù–…∑\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens·\ncT‘^\0L\0valueq\0~\0xpt\0$8b82aa6e-8679-4f46-9c06-a10fe3051ec9sq\0~\0w\0\0QKAPxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readxt\0bearert\0$913506cc-fe2b-49d2-9685-460a6a833e49','admin'),('3a77330ff57dd3a2973bc2679386afad','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0com.imcode.entities.Role8q÷/s}\\‡\0\0xr\04com.imcode.entities.superclasses.AbstractNamedEntityÉMñé(Õx\0L\0namet\0Ljava/lang/String;xr\01com.imcode.entities.superclasses.AbstractIdEntityß@ˆ¨p% X\0L\0idt\0Ljava/io/Serializable;xpsr\0java.lang.Long;ã‰êÃè#ﬂ\0J\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0\0\0\0\0t\0\nROLE_ADMINsq\0~\0\rsq\0~\0\0\0\0\0\0\0\0t\0	ROLE_USERsq\0~\0\rsq\0~\0\0\0\0\0\0\0\0t\0ROLE_DEVELOPERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0$ff11397c-3e3b-4398-80a9-feba203f1928sr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0admint\0scopet\0\nread writexsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\00w\0\0\0?@\0\0\0\0\0sr\0%com.imcode.entities.oauth2.ClientRoleÂl§°Ç¢ôZ\0\0xq\0~\0sq\0~\0\0\0\0\0\0\0\0t\0\nROLE_ADMINsq\0~\05q\0~\0t\0	ROLE_USERxsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xpsr\0/org.hibernate.collection.internal.PersistentSetÜ‰˝°ÚÓ‡Ø\0L\0setq\0~\0xr\0>org.hibernate.collection.internal.AbstractPersistentCollectionÑJ~wÆç\0	Z\0allowLoadOutsideTransactionI\0\ncachedSizeZ\0dirtyZ\0initializedL\0keyq\0~\0L\0ownerq\0~\0L\0roleq\0~\0L\0sessionFactoryUuidq\0~\0L\0storedSnapshotq\0~\0xp\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928sr\0+com.imcode.entities.oauth2.JpaClientDetails…Sç∑¸^;È\0\rL\0\ZaccessTokenValiditySecondst\0Ljava/lang/Integer;L\0additionalInformationq\0~\0L\0authoritiesq\0~\0L\0authorizedGrantTypesq\0~\0L\0autoApproveScopesq\0~\0L\0clientIdq\0~\0L\0clientSecretq\0~\0L\0nameq\0~\0L\0ownert\0\ZLcom/imcode/entities/User;L\0refreshTokenValiditySecondsq\0~\0AL\0registeredRedirectUrisq\0~\0L\0resourceIdsq\0~\0L\0scopeq\0~\0xpsr\0java.lang.Integer‚†§˜Åá8\0I\0valuexq\0~\0\0[çÄsr\0/org.hibernate.collection.internal.PersistentMapˇâ‘e¯Ï!\0L\0mapq\0~\0xq\0~\0=\0ˇˇˇˇ\0q\0~\0\"q\0~\0Ct\0Acom.imcode.entities.oauth2.JpaClientDetails.additionalInformationpsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0<\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928q\0~\0Ct\07com.imcode.entities.oauth2.JpaClientDetails.authoritiespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\06q\0~\06q\0~\09q\0~\09xsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\06q\0~\09xsq\0~\0<\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928q\0~\0Ct\0@com.imcode.entities.oauth2.JpaClientDetails.authorizedGrantTypespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0implicitq\0~\0Tt\0\rrefresh_tokenq\0~\0Ut\0client_credentialsq\0~\0Vt\0passwordq\0~\0Wt\0authorization_codeq\0~\0Xxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0Tq\0~\0Uq\0~\0Vq\0~\0Wq\0~\0Xxpq\0~\0\"t\0secrett\0adminsr\0com.imcode.entities.User3ÏET\\\0L\0confirmPasswordq\0~\0L\0enabledt\0Ljava/lang/Boolean;L\0passwordq\0~\0L\0persont\0Lcom/imcode/entities/Person;L\0rolesq\0~\0xq\0~\0q\0~\07t\0admint\0\0sr\0java.lang.BooleanÕ rÄ’ú˙Ó\0Z\0valuexpt\0passsr\0\Zcom.imcode.entities.PersonR›±–ˆÊ&\0L\0	addressesq\0~\0L\0emailsq\0~\0L\0	firstNameq\0~\0L\0lastNameq\0~\0L\0\npersonalIdq\0~\0L\0phonesq\0~\0xq\0~\0q\0~\07sq\0~\0F\0ˇˇˇˇ\0q\0~\07q\0~\0ft\0$com.imcode.entities.Person.addressespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0F\0ˇˇˇˇ\0q\0~\07q\0~\0ft\0!com.imcode.entities.Person.emailspsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0Vitaliyt\0Seredat\0850717-5019sq\0~\0F\0ˇˇˇˇ\0q\0~\07q\0~\0ft\0!com.imcode.entities.Person.phonespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0<\0ˇˇˇˇ\0q\0~\07q\0~\0_t\0com.imcode.entities.User.rolespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0q\0~\0\Zq\0~\0\Zxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0q\0~\0q\0~\0\Zxsq\0~\0D\0[çÄsq\0~\0<\0ˇˇˇˇ\0q\0~\0\"q\0~\0Ct\0Bcom.imcode.entities.oauth2.JpaClientDetails.registeredRedirectUrispsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\00w\0\0\0?@\0\0\0\0\0\0xq\0~\0>sq\0~\0<\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928q\0~\0Ct\01com.imcode.entities.oauth2.JpaClientDetails.scopepsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0writeq\0~\0Ét\0executeq\0~\0Ñt\0readq\0~\0Öxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0Öq\0~\0Éq\0~\0Ñxt\07com.imcode.entities.oauth2.JpaClientDetails.resourceIdspsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0ivisq\0~\0âxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0âxsq\0~\00w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0@\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0q\0~\0q\0~\0\Zxq\0~\0èsr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0\'q\0~\0(q\0~\0)q\0~\0*q\0~\0+q\0~\0,x\0pq\0~\0_','22d8d4fa892a498050cb225bb2c3774f','ff11397c-3e3b-4398-80a9-feba203f1928','57492ee23a9e679e6d691302ce9b35b5','¨Ì\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken≤û6$˙Œ\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0	client_idt\0$ff11397c-3e3b-4398-80a9-feba203f1928x\0sr\0java.util.DatehjÅKYt\0\0xpw\0\0Rû1cxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ﬂGcù–…∑\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens·\ncT‘^\0L\0valueq\0~\0xpt\0$398e6d7e-116e-4d91-8df7-c223e4798a3asq\0~\0w\0\0Rû1bxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writext\0bearert\0$f1e3feda-891b-4800-af9f-7d3607b547ec','admin'),('de1a301a1a1594a00eba000aeb95800d','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0com.imcode.entities.Role8q÷/s}\\‡\0\0xr\04com.imcode.entities.superclasses.AbstractNamedEntityÉMñé(Õx\0L\0namet\0Ljava/lang/String;xr\01com.imcode.entities.superclasses.AbstractIdEntityß@ˆ¨p% X\0L\0idt\0Ljava/io/Serializable;xpsr\0java.lang.Long;ã‰êÃè#ﬂ\0J\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0\0\0\0\0t\0\nROLE_ADMINsq\0~\0\rsq\0~\0\0\0\0\0\0\0\0t\0	ROLE_USERsq\0~\0\rsq\0~\0\0\0\0\0\0\0\0t\0ROLE_DEVELOPERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0$08d32c33-91cf-4452-8be8-4d120fbc504esr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0admint\0scopet\0readxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0q\0~\0,xsq\0~\00w\0\0\0?@\0\0\0\0\0sr\0%com.imcode.entities.oauth2.ClientRoleÂl§°Ç¢ôZ\0\0xq\0~\0sq\0~\0\0\0\0\0\0\0\0t\0\nROLE_ADMINsq\0~\03q\0~\0t\0	ROLE_USERxsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xpsr\0/org.hibernate.collection.internal.PersistentSetÜ‰˝°ÚÓ‡Ø\0L\0setq\0~\0xr\0>org.hibernate.collection.internal.AbstractPersistentCollectionÑJ~wÆç\0	Z\0allowLoadOutsideTransactionI\0\ncachedSizeZ\0dirtyZ\0initializedL\0keyq\0~\0L\0ownerq\0~\0L\0roleq\0~\0L\0sessionFactoryUuidq\0~\0L\0storedSnapshotq\0~\0xp\0ˇˇˇˇ\0t\0$08d32c33-91cf-4452-8be8-4d120fbc504esr\0+com.imcode.entities.oauth2.JpaClientDetails…Sç∑¸^;È\0\rL\0\ZaccessTokenValiditySecondst\0Ljava/lang/Integer;L\0additionalInformationq\0~\0L\0authoritiesq\0~\0L\0authorizedGrantTypesq\0~\0L\0autoApproveScopesq\0~\0L\0clientIdq\0~\0L\0clientSecretq\0~\0L\0nameq\0~\0L\0ownert\0\ZLcom/imcode/entities/User;L\0refreshTokenValiditySecondsq\0~\0?L\0registeredRedirectUrisq\0~\0L\0resourceIdsq\0~\0L\0scopeq\0~\0xpsr\0java.lang.Integer‚†§˜Åá8\0I\0valuexq\0~\0\0\0\0<sr\0/org.hibernate.collection.internal.PersistentMapˇâ‘e¯Ï!\0L\0mapq\0~\0xq\0~\0;\0ˇˇˇˇ\0q\0~\0\"q\0~\0At\0Acom.imcode.entities.oauth2.JpaClientDetails.additionalInformationpsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0:\0ˇˇˇˇ\0t\0$08d32c33-91cf-4452-8be8-4d120fbc504eq\0~\0At\07com.imcode.entities.oauth2.JpaClientDetails.authoritiespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\04q\0~\04q\0~\07q\0~\07xsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\04q\0~\07xsq\0~\0:\0ˇˇˇˇ\0t\0$08d32c33-91cf-4452-8be8-4d120fbc504eq\0~\0At\0@com.imcode.entities.oauth2.JpaClientDetails.authorizedGrantTypespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0passwordq\0~\0Rt\0authorization_codeq\0~\0Sxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0Rq\0~\0Sxpq\0~\0\"t\0secrett\0ivissr\0com.imcode.entities.User3ÏET\\\0L\0confirmPasswordq\0~\0L\0enabledt\0Ljava/lang/Boolean;L\0passwordq\0~\0L\0persont\0Lcom/imcode/entities/Person;L\0rolesq\0~\0xq\0~\0q\0~\0t\0ivist\0\0sr\0java.lang.BooleanÕ rÄ’ú˙Ó\0Z\0valuexpt\0111sr\0\Zcom.imcode.entities.PersonR›±–ˆÊ&\0L\0	addressesq\0~\0L\0emailsq\0~\0L\0	firstNameq\0~\0L\0lastNameq\0~\0L\0\npersonalIdq\0~\0L\0phonesq\0~\0xq\0~\0q\0~\0sq\0~\0D\0ˇˇˇˇ\0q\0~\0q\0~\0at\0$com.imcode.entities.Person.addressespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0D\0ˇˇˇˇ\0q\0~\0q\0~\0at\0!com.imcode.entities.Person.emailspsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0Henryt\0Ivist\02222sq\0~\0D\0ˇˇˇˇ\0q\0~\0q\0~\0at\0!com.imcode.entities.Person.phonespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0:\0ˇˇˇˇ\0q\0~\0q\0~\0Zt\0com.imcode.entities.User.rolespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0\Zq\0~\0\Zxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0q\0~\0\Zxsq\0~\0B\0\0Xsq\0~\0:\0ˇˇˇˇ\0q\0~\0\"q\0~\0At\0Bcom.imcode.entities.oauth2.JpaClientDetails.registeredRedirectUrispsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\00w\0\0\0?@\0\0\0\0\0\0xq\0~\0<sq\0~\0:\0ˇˇˇˇ\0t\0$08d32c33-91cf-4452-8be8-4d120fbc504eq\0~\0At\01com.imcode.entities.oauth2.JpaClientDetails.scopepsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0writeq\0~\0~t\0executeq\0~\0t\0readq\0~\0Äxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0Äq\0~\0~q\0~\0xt\07com.imcode.entities.oauth2.JpaClientDetails.resourceIdspsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0ivisq\0~\0Ñxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0Ñxsq\0~\00w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0@\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0q\0~\0q\0~\0\Zxq\0~\0äsr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0\'q\0~\0(q\0~\0)q\0~\0*q\0~\0+q\0~\0,x\0psq\0~\0Wq\0~\05t\0adminq\0~\0\\q\0~\0^t\0passsq\0~\0`q\0~\05sq\0~\0D\0ˇˇˇˇ\0q\0~\05q\0~\0êq\0~\0cpsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0D\0ˇˇˇˇ\0q\0~\05q\0~\0êq\0~\0gpsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0Vitaliyt\0Seredat\0850717-5019sq\0~\0D\0ˇˇˇˇ\0q\0~\05q\0~\0êq\0~\0npsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0:\0ˇˇˇˇ\0q\0~\05q\0~\0çq\0~\0rpsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0q\0~\0\Zq\0~\0\Zxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0q\0~\0q\0~\0\Zx','323c969b659cc37fc695762fbd0e9de6','08d32c33-91cf-4452-8be8-4d120fbc504e',NULL,'¨Ì\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken≤û6$˙Œ\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0	client_idt\0$08d32c33-91cf-4452-8be8-4d120fbc504ex\0sr\0java.util.DatehjÅKYt\0\0xpw\0\0Q3˚¡˜xpsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readxt\0bearert\0$ae5590bb-1c8d-47e5-aae5-c4eedfd1ce62','admin');
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_refresh_token`
--

DROP TABLE IF EXISTS `oauth_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(36) COLLATE utf8_swedish_ci NOT NULL,
  `authentication` longblob,
  `token` longblob,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_refresh_token`
--

LOCK TABLES `oauth_refresh_token` WRITE;
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;
INSERT INTO `oauth_refresh_token` VALUES ('57492ee23a9e679e6d691302ce9b35b5','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0com.imcode.entities.Role8q÷/s}\\‡\0\0xr\04com.imcode.entities.superclasses.AbstractNamedEntityÉMñé(Õx\0L\0namet\0Ljava/lang/String;xr\01com.imcode.entities.superclasses.AbstractIdEntityß@ˆ¨p% X\0L\0idt\0Ljava/io/Serializable;xpsr\0java.lang.Long;ã‰êÃè#ﬂ\0J\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0\0\0\0\0t\0\nROLE_ADMINsq\0~\0\rsq\0~\0\0\0\0\0\0\0\0t\0	ROLE_USERsq\0~\0\rsq\0~\0\0\0\0\0\0\0\0t\0ROLE_DEVELOPERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0$ff11397c-3e3b-4398-80a9-feba203f1928sr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0codet\0nLAIQ6t\0\ngrant_typet\0authorization_codet\0scopet\0\nread writet\0\rresponse_typet\0codet\0redirect_urit\0@https://oep-ivis.dev.imcode.com/core/login?redirect=%2Fflowadmint\0statet\0qs44LGt\0	client_idq\0~\0\"xsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\07w\0\0\0?@\0\0\0\0\0sr\0%com.imcode.entities.oauth2.ClientRoleÂl§°Ç¢ôZ\0\0xq\0~\0sq\0~\0\0\0\0\0\0\0\0t\0\nROLE_ADMINsq\0~\0<q\0~\0t\0	ROLE_USERxsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0@https://oep-ivis.dev.imcode.com/core/login?redirect=%2Fflowadminsr\0/org.hibernate.collection.internal.PersistentSetÜ‰˝°ÚÓ‡Ø\0L\0setq\0~\0xr\0>org.hibernate.collection.internal.AbstractPersistentCollectionÑJ~wÆç\0	Z\0allowLoadOutsideTransactionI\0\ncachedSizeZ\0dirtyZ\0initializedL\0keyq\0~\0L\0ownerq\0~\0L\0roleq\0~\0L\0sessionFactoryUuidq\0~\0L\0storedSnapshotq\0~\0xp\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928sr\0+com.imcode.entities.oauth2.JpaClientDetails…Sç∑¸^;È\0\rL\0\ZaccessTokenValiditySecondst\0Ljava/lang/Integer;L\0additionalInformationq\0~\0L\0authoritiesq\0~\0L\0authorizedGrantTypesq\0~\0L\0autoApproveScopesq\0~\0L\0clientIdq\0~\0L\0clientSecretq\0~\0L\0nameq\0~\0L\0ownert\0\ZLcom/imcode/entities/User;L\0refreshTokenValiditySecondsq\0~\0IL\0registeredRedirectUrisq\0~\0L\0resourceIdsq\0~\0L\0scopeq\0~\0xpsr\0java.lang.Integer‚†§˜Åá8\0I\0valuexq\0~\0\0[çÄsr\0/org.hibernate.collection.internal.PersistentMapˇâ‘e¯Ï!\0L\0mapq\0~\0xq\0~\0E\0ˇˇˇˇ\0q\0~\0\"q\0~\0Kt\0Acom.imcode.entities.oauth2.JpaClientDetails.additionalInformationpsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0D\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928q\0~\0Kt\07com.imcode.entities.oauth2.JpaClientDetails.authoritiespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0=q\0~\0=q\0~\0@q\0~\0@xsq\0~\07w\0\0\0?@\0\0\0\0\0q\0~\0=q\0~\0@xsq\0~\0D\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928q\0~\0Kt\0@com.imcode.entities.oauth2.JpaClientDetails.authorizedGrantTypespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0implicitq\0~\0\\t\0\rrefresh_tokenq\0~\0]t\0client_credentialsq\0~\0^t\0passwordq\0~\0_t\0authorization_codeq\0~\0`xsq\0~\07w\0\0\0?@\0\0\0\0\0q\0~\0\\q\0~\0]q\0~\0^q\0~\0_q\0~\0`xpq\0~\0\"t\0secrett\0adminsr\0com.imcode.entities.User3ÏET\\\0L\0confirmPasswordq\0~\0L\0enabledt\0Ljava/lang/Boolean;L\0passwordq\0~\0L\0persont\0Lcom/imcode/entities/Person;L\0rolesq\0~\0xq\0~\0q\0~\0>t\0admint\0\0sr\0java.lang.BooleanÕ rÄ’ú˙Ó\0Z\0valuexpt\0passsr\0\Zcom.imcode.entities.PersonR›±–ˆÊ&\0L\0	addressesq\0~\0L\0emailsq\0~\0L\0	firstNameq\0~\0L\0lastNameq\0~\0L\0\npersonalIdq\0~\0L\0phonesq\0~\0xq\0~\0q\0~\0>sq\0~\0N\0ˇˇˇˇ\0q\0~\0>q\0~\0nt\0$com.imcode.entities.Person.addressespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0N\0ˇˇˇˇ\0q\0~\0>q\0~\0nt\0!com.imcode.entities.Person.emailspsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0Vitaliyt\0Seredat\0850717-5019sq\0~\0N\0ˇˇˇˇ\0q\0~\0>q\0~\0nt\0!com.imcode.entities.Person.phonespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0D\0ˇˇˇˇ\0q\0~\0>q\0~\0gt\0com.imcode.entities.User.rolespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0sq\0~\0\rq\0~\0t\0	ROLE_USERq\0~\0Åsq\0~\0\rq\0~\0t\0\nROLE_ADMINq\0~\0Ésq\0~\0\rq\0~\0t\0ROLE_DEVELOPERq\0~\0Öxsq\0~\07w\0\0\0?@\0\0\0\0\0q\0~\0Éq\0~\0Åq\0~\0Öxsq\0~\0L\0[çÄsq\0~\0D\0ˇˇˇˇ\0q\0~\0\"q\0~\0Kt\0Bcom.imcode.entities.oauth2.JpaClientDetails.registeredRedirectUrispsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\07w\0\0\0?@\0\0\0\0\0\0xq\0~\0Fsq\0~\0D\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928q\0~\0Kt\01com.imcode.entities.oauth2.JpaClientDetails.scopepsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0writeq\0~\0ët\0executeq\0~\0ít\0readq\0~\0ìxsq\0~\07w\0\0\0?@\0\0\0\0\0q\0~\0ìq\0~\0ëq\0~\0íxt\07com.imcode.entities.oauth2.JpaClientDetails.resourceIdspsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0ivisq\0~\0óxsq\0~\07w\0\0\0?@\0\0\0\0\0q\0~\0óxsq\0~\07w\0\0\0?@\0\0\0\0\0q\0~\0.xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0@\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0q\0~\0q\0~\0\Zxq\0~\0ùsr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0@\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\095.195.207.165t\0 25C02D9921669EA011D1A0FC9262B6D0psq\0~\0dq\0~\0>t\0adminq\0~\0iq\0~\0kt\0passsq\0~\0mq\0~\0>sq\0~\0N\0ˇˇˇˇ\0q\0~\0>q\0~\0•q\0~\0ppsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0N\0ˇˇˇˇ\0q\0~\0>q\0~\0•q\0~\0tpsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0Vitaliyt\0Seredat\0850717-5019sq\0~\0N\0ˇˇˇˇ\0q\0~\0>q\0~\0•q\0~\0{psq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0D\0ˇˇˇˇ\0q\0~\0>q\0~\0¢q\0~\0psq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0q\0~\0\Zq\0~\0\Zxsq\0~\07w\0\0\0?@\0\0\0\0\0q\0~\0q\0~\0q\0~\0\Zx','¨Ì\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ﬂGcù–…∑\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens·\ncT‘^\0L\0valuet\0Ljava/lang/String;xpt\0$398e6d7e-116e-4d91-8df7-c223e4798a3asr\0java.util.DatehjÅKYt\0\0xpw\0\0Rû1bx'),('d4505eab8d2ef6049394905b66c62211','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0com.imcode.entities.Role8q÷/s}\\‡\0\0xr\04com.imcode.entities.superclasses.AbstractNamedEntityÉMñé(Õx\0L\0namet\0Ljava/lang/String;xr\01com.imcode.entities.superclasses.AbstractIdEntityß@ˆ¨p% X\0L\0idt\0Ljava/io/Serializable;xpsr\0java.lang.Long;ã‰êÃè#ﬂ\0J\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0\0\0\0\0t\0\nROLE_ADMINsq\0~\0\rsq\0~\0\0\0\0\0\0\0\0t\0	ROLE_USERsq\0~\0\rsq\0~\0\0\0\0\0\0\0\0t\0ROLE_DEVELOPERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0$ff11397c-3e3b-4398-80a9-feba203f1928sr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0admint\0scopet\0readxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0q\0~\0,xsq\0~\00w\0\0\0?@\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xpsr\0/org.hibernate.collection.internal.PersistentSetÜ‰˝°ÚÓ‡Ø\0L\0setq\0~\0xr\0>org.hibernate.collection.internal.AbstractPersistentCollectionÑJ~wÆç\0	Z\0allowLoadOutsideTransactionI\0\ncachedSizeZ\0dirtyZ\0initializedL\0keyq\0~\0L\0ownerq\0~\0L\0roleq\0~\0L\0sessionFactoryUuidq\0~\0L\0storedSnapshotq\0~\0xp\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928sr\0+com.imcode.entities.oauth2.JpaClientDetails…Sç∑¸^;È\0\rL\0\ZaccessTokenValiditySecondst\0Ljava/lang/Integer;L\0additionalInformationq\0~\0L\0authoritiesq\0~\0L\0authorizedGrantTypesq\0~\0L\0autoApproveScopesq\0~\0L\0clientIdq\0~\0L\0clientSecretq\0~\0L\0nameq\0~\0L\0ownert\0\ZLcom/imcode/entities/User;L\0refreshTokenValiditySecondsq\0~\09L\0registeredRedirectUrisq\0~\0L\0resourceIdsq\0~\0L\0scopeq\0~\0xpsr\0java.lang.Integer‚†§˜Åá8\0I\0valuexq\0~\0\0\0Xsr\0/org.hibernate.collection.internal.PersistentMapˇâ‘e¯Ï!\0L\0mapq\0~\0xq\0~\05\0ˇˇˇˇ\0q\0~\0\"q\0~\0;t\0Acom.imcode.entities.oauth2.JpaClientDetails.additionalInformationpsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\04\0ˇˇˇˇ\0q\0~\0\"q\0~\0;t\07com.imcode.entities.oauth2.JpaClientDetails.authoritiespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\00w\0\0\0?@\0\0\0\0\0\0xsq\0~\04\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928q\0~\0;t\0@com.imcode.entities.oauth2.JpaClientDetails.authorizedGrantTypespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0implicitq\0~\0Kt\0\rrefresh_tokenq\0~\0Lt\0client_credentialsq\0~\0Mt\0passwordq\0~\0Nt\0authorization_codeq\0~\0Oxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0Kq\0~\0Lq\0~\0Mq\0~\0Nq\0~\0Oxpq\0~\0\"t\0secrett\0adminsr\0com.imcode.entities.User3ÏET\\\0L\0confirmPasswordq\0~\0L\0enabledt\0Ljava/lang/Boolean;L\0passwordq\0~\0L\0persont\0Lcom/imcode/entities/Person;L\0rolesq\0~\0xq\0~\0sq\0~\0\0\0\0\0\0\0\0t\0admint\0\0sr\0java.lang.BooleanÕ rÄ’ú˙Ó\0Z\0valuexpt\0passsr\0\Zcom.imcode.entities.PersonR›±–ˆÊ&\0L\0	addressesq\0~\0L\0emailsq\0~\0L\0	firstNameq\0~\0L\0lastNameq\0~\0L\0\npersonalIdq\0~\0L\0phonesq\0~\0xq\0~\0q\0~\0Wsq\0~\0>\0ˇˇˇˇ\0q\0~\0Wq\0~\0^t\0$com.imcode.entities.Person.addressespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0>\0ˇˇˇˇ\0q\0~\0Wq\0~\0^t\0!com.imcode.entities.Person.emailspsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0Vitaliyt\0Seredat\0850717-5019sq\0~\0>\0ˇˇˇˇ\0q\0~\0Wq\0~\0^t\0!com.imcode.entities.Person.phonespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\0%?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\04\0ˇˇˇˇ\0q\0~\0Wq\0~\0Vt\0com.imcode.entities.User.rolespsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0q\0~\0\Zq\0~\0\Zxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0q\0~\0q\0~\0\Zxsq\0~\0<\0\0psq\0~\04\0ˇˇˇˇ\0q\0~\0\"q\0~\0;t\0Bcom.imcode.entities.oauth2.JpaClientDetails.registeredRedirectUrispsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0\0xsq\0~\00w\0\0\0?@\0\0\0\0\0\0xq\0~\06sq\0~\04\0ˇˇˇˇ\0t\0$ff11397c-3e3b-4398-80a9-feba203f1928q\0~\0;t\01com.imcode.entities.oauth2.JpaClientDetails.scopepsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0writeq\0~\0{t\0executeq\0~\0|t\0readq\0~\0}xsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0}q\0~\0{q\0~\0|xt\07com.imcode.entities.oauth2.JpaClientDetails.resourceIdspsq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0t\0ivisq\0~\0Åxsq\0~\00w\0\0\0?@\0\0\0\0\0q\0~\0Åxsq\0~\00w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0@\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0q\0~\0q\0~\0\Zxq\0~\0ásr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxq\0~\0%?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0\'q\0~\0(q\0~\0)q\0~\0*q\0~\0+q\0~\0,x\0pq\0~\0V','¨Ì\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ﬂGcù–…∑\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens·\ncT‘^\0L\0valuet\0Ljava/lang/String;xpt\0$8b82aa6e-8679-4f46-9c06-a10fe3051ec9sr\0java.util.DatehjÅKYt\0\0xpw\0\0QKAPx');
/*!40000 ALTER TABLE `oauth_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-24 11:08:03
