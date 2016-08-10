-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: sarimner.imcode.com    Database: iVIS-imCMS-Client-Demo_dev
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
-- Table structure for table `archive_category_roles`
--

DROP TABLE IF EXISTS `archive_category_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive_category_roles` (
  `category_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `created_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `canUse` tinyint(1) NOT NULL,
  `canChange` tinyint(1) NOT NULL,
  PRIMARY KEY (`category_id`,`role_id`),
  KEY `archive_category_roles_role_id_fk` (`role_id`),
  CONSTRAINT `archive_category_roles_category_id_fk` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
  CONSTRAINT `archive_category_roles_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive_category_roles`
--

LOCK TABLES `archive_category_roles` WRITE;
/*!40000 ALTER TABLE `archive_category_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `archive_category_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archive_exif`
--

DROP TABLE IF EXISTS `archive_exif`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive_exif` (
  `image_id` bigint(20) NOT NULL,
  `exif_type` smallint(6) NOT NULL DEFAULT '0' COMMENT '0-original, 1-changed',
  `x_resolution` int(11) DEFAULT NULL,
  `y_resolution` int(11) DEFAULT NULL,
  `description` varchar(255) NOT NULL DEFAULT '',
  `artist` varchar(255) NOT NULL DEFAULT '',
  `copyright` varchar(255) NOT NULL DEFAULT '',
  `created_dt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `manufacturer` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `compression` varchar(255) DEFAULT NULL,
  `exposure` double DEFAULT NULL,
  `exposure_program` varchar(255) DEFAULT NULL,
  `fstop` float DEFAULT NULL,
  `flash` int(11) DEFAULT NULL,
  `focal_length` float DEFAULT NULL,
  `color_space` varchar(255) DEFAULT NULL,
  `resolution_unit` smallint(1) DEFAULT NULL,
  `pixel_x_dimension` int(11) DEFAULT NULL,
  `pixel_y_dimension` int(11) DEFAULT NULL,
  `date_original` timestamp NULL DEFAULT NULL,
  `date_digitized` timestamp NULL DEFAULT NULL,
  `ISO` int(11) DEFAULT NULL,
  PRIMARY KEY (`image_id`,`exif_type`),
  CONSTRAINT `archive_image_id_fk` FOREIGN KEY (`image_id`) REFERENCES `archive_images` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive_exif`
--

LOCK TABLES `archive_exif` WRITE;
/*!40000 ALTER TABLE `archive_exif` DISABLE KEYS */;
/*!40000 ALTER TABLE `archive_exif` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archive_image_categories`
--

DROP TABLE IF EXISTS `archive_image_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive_image_categories` (
  `image_id` bigint(20) NOT NULL,
  `category_id` int(11) NOT NULL,
  `created_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`image_id`,`category_id`),
  KEY `archive_image_categories_category_id_fk` (`category_id`),
  CONSTRAINT `archive_image_categories_category_id_fk` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
  CONSTRAINT `archive_image_categories_image_id_fk` FOREIGN KEY (`image_id`) REFERENCES `archive_images` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive_image_categories`
--

LOCK TABLES `archive_image_categories` WRITE;
/*!40000 ALTER TABLE `archive_image_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `archive_image_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archive_image_keywords`
--

DROP TABLE IF EXISTS `archive_image_keywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive_image_keywords` (
  `image_id` bigint(20) NOT NULL,
  `keyword_id` bigint(20) NOT NULL,
  `created_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`image_id`,`keyword_id`),
  KEY `archive_image_keywords_keyword_id_fk` (`keyword_id`),
  CONSTRAINT `archive_image_keywords_image_id_fk` FOREIGN KEY (`image_id`) REFERENCES `archive_images` (`id`),
  CONSTRAINT `archive_image_keywords_keyword_id_fk` FOREIGN KEY (`keyword_id`) REFERENCES `archive_keywords` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive_image_keywords`
--

LOCK TABLES `archive_image_keywords` WRITE;
/*!40000 ALTER TABLE `archive_image_keywords` DISABLE KEYS */;
/*!40000 ALTER TABLE `archive_image_keywords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archive_images`
--

DROP TABLE IF EXISTS `archive_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive_images` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_nm` varchar(255) NOT NULL DEFAULT '',
  `format` smallint(6) NOT NULL COMMENT '1-BMP, 2-GIF, 3-JPEG, 4-PNG, 5-PSD, 6-SVG, 7-TIFF, 8-XCF, 9-PICT, 10-PDF, 11-PS',
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `file_size` int(11) NOT NULL COMMENT 'in bytes',
  `uploaded_by` varchar(130) NOT NULL DEFAULT '',
  `users_id` int(11) NOT NULL,
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '0-uploaded, 1-active, 2-archived',
  `created_dt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `license_dt` date DEFAULT NULL,
  `license_end_dt` date DEFAULT NULL,
  `alt_text` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `archive_images_users_id_fk` (`users_id`),
  CONSTRAINT `archive_images_users_id_fk` FOREIGN KEY (`users_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive_images`
--

LOCK TABLES `archive_images` WRITE;
/*!40000 ALTER TABLE `archive_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `archive_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archive_keywords`
--

DROP TABLE IF EXISTS `archive_keywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive_keywords` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `keyword_nm` varchar(50) NOT NULL,
  `created_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive_keywords`
--

LOCK TABLES `archive_keywords` WRITE;
/*!40000 ALTER TABLE `archive_keywords` DISABLE KEYS */;
/*!40000 ALTER TABLE `archive_keywords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archive_libraries`
--

DROP TABLE IF EXISTS `archive_libraries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive_libraries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `library_nm` varchar(120) NOT NULL,
  `folder_nm` varchar(255) NOT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `library_type` smallint(6) NOT NULL DEFAULT '0' COMMENT '0-standard, 1-old library',
  `created_dt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `archive_libraries_folder_nm_filepath_unq` (`folder_nm`,`filepath`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive_libraries`
--

LOCK TABLES `archive_libraries` WRITE;
/*!40000 ALTER TABLE `archive_libraries` DISABLE KEYS */;
/*!40000 ALTER TABLE `archive_libraries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archive_library_roles`
--

DROP TABLE IF EXISTS `archive_library_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive_library_roles` (
  `library_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `permissions` int(11) NOT NULL,
  `created_dt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `canUse` tinyint(1) NOT NULL,
  `canChange` tinyint(1) NOT NULL,
  PRIMARY KEY (`library_id`,`role_id`),
  KEY `archive_library_roles_role_id_fk` (`role_id`),
  CONSTRAINT `archive_library_roles_library_id_fk` FOREIGN KEY (`library_id`) REFERENCES `archive_libraries` (`id`),
  CONSTRAINT `archive_library_roles_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive_library_roles`
--

LOCK TABLES `archive_library_roles` WRITE;
/*!40000 ALTER TABLE `archive_library_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `archive_library_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_type_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`),
  KEY `categories_FK_category_type_id_category_types` (`category_type_id`),
  CONSTRAINT `categories_FK_category_type_id_category_types` FOREIGN KEY (`category_type_id`) REFERENCES `category_types` (`category_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_types`
--

DROP TABLE IF EXISTS `category_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_types` (
  `category_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `max_choices` int(11) NOT NULL DEFAULT '0',
  `inherited` tinyint(1) NOT NULL,
  `is_image_archive` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'is image category type, can be used in image archive',
  PRIMARY KEY (`category_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_types`
--

LOCK TABLES `category_types` WRITE;
/*!40000 ALTER TABLE `category_types` DISABLE KEYS */;
INSERT INTO `category_types` VALUES (1,'Images',0,1,1);
/*!40000 ALTER TABLE `category_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `childs`
--

DROP TABLE IF EXISTS `childs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `childs` (
  `to_meta_id` int(11) NOT NULL,
  `manual_sort_order` int(11) NOT NULL,
  `tree_sort_index` varchar(64) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`to_meta_id`,`menu_id`),
  KEY `childs_FK_menu_id_menus` (`menu_id`),
  CONSTRAINT `childs_FK_menu_id_menus` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`menu_id`),
  CONSTRAINT `childs_FK_to_meta_id_meta` FOREIGN KEY (`to_meta_id`) REFERENCES `meta` (`meta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `childs`
--

LOCK TABLES `childs` WRITE;
/*!40000 ALTER TABLE `childs` DISABLE KEYS */;
/*!40000 ALTER TABLE `childs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `childs_history`
--

DROP TABLE IF EXISTS `childs_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `childs_history` (
  `menu_id` int(11) NOT NULL,
  `to_meta_id` int(11) NOT NULL,
  `manual_sort_order` int(11) NOT NULL,
  `tree_sort_index` varchar(64) NOT NULL,
  PRIMARY KEY (`menu_id`,`to_meta_id`),
  KEY `childs_history_FK_to_meta_id_meta` (`to_meta_id`),
  CONSTRAINT `childs_history_FK_menu_id_menus_history` FOREIGN KEY (`menu_id`) REFERENCES `menus_history` (`menu_id`),
  CONSTRAINT `childs_history_FK_to_meta_id_meta` FOREIGN KEY (`to_meta_id`) REFERENCES `meta` (`meta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `childs_history`
--

LOCK TABLES `childs_history` WRITE;
/*!40000 ALTER TABLE `childs_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `childs_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classification`
--

DROP TABLE IF EXISTS `classification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classification` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(128) NOT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classification`
--

LOCK TABLES `classification` WRITE;
/*!40000 ALTER TABLE `classification` DISABLE KEYS */;
/*!40000 ALTER TABLE `classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `database_version`
--

DROP TABLE IF EXISTS `database_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `database_version` (
  `major` int(11) NOT NULL,
  `minor` int(11) NOT NULL,
  PRIMARY KEY (`major`,`minor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `database_version`
--

LOCK TABLES `database_version` WRITE;
/*!40000 ALTER TABLE `database_version` DISABLE KEYS */;
INSERT INTO `database_version` VALUES (6,17);
/*!40000 ALTER TABLE `database_version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doc_permission_sets`
--

DROP TABLE IF EXISTS `doc_permission_sets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc_permission_sets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meta_id` int(11) NOT NULL,
  `set_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__doc_permission_sets__meta_id__set_id` (`meta_id`,`set_id`),
  KEY `fk__doc_permission_sets__permission_sets` (`set_id`),
  CONSTRAINT `fk__doc_permission_sets__meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`) ON DELETE CASCADE,
  CONSTRAINT `fk__doc_permission_sets__permission_sets` FOREIGN KEY (`set_id`) REFERENCES `permission_sets` (`set_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_permission_sets`
--

LOCK TABLES `doc_permission_sets` WRITE;
/*!40000 ALTER TABLE `doc_permission_sets` DISABLE KEYS */;
INSERT INTO `doc_permission_sets` VALUES (1,1001,1,0),(3,1001,2,0),(5,1003,1,0),(7,1003,2,0),(9,1005,1,0),(11,1005,2,0),(13,1007,1,0),(15,1007,2,0),(17,1009,1,0),(19,1009,2,0),(21,1011,1,0),(23,1011,2,0),(25,1013,1,0),(27,1013,2,0),(29,1014,1,0),(31,1014,2,0);
/*!40000 ALTER TABLE `doc_permission_sets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doc_permission_sets_ex`
--

DROP TABLE IF EXISTS `doc_permission_sets_ex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc_permission_sets_ex` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meta_id` int(11) NOT NULL,
  `set_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `permission_data` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__doc_permission_sets_ex__1` (`meta_id`,`set_id`,`permission_id`,`permission_data`),
  KEY `fk__doc_permission_sets_ex__permission_sets` (`set_id`),
  CONSTRAINT `fk__doc_permission_sets_ex__meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`) ON DELETE CASCADE,
  CONSTRAINT `fk__doc_permission_sets_ex__permission_sets` FOREIGN KEY (`set_id`) REFERENCES `permission_sets` (`set_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_permission_sets_ex`
--

LOCK TABLES `doc_permission_sets_ex` WRITE;
/*!40000 ALTER TABLE `doc_permission_sets_ex` DISABLE KEYS */;
INSERT INTO `doc_permission_sets_ex` VALUES (51,1003,1,8,2),(55,1003,1,8,5),(59,1003,1,8,7),(41,1003,1,8,8),(47,1003,1,524288,0),(53,1003,2,8,2),(57,1003,2,8,5),(43,1003,2,8,7),(45,1003,2,8,8),(49,1003,2,524288,0);
/*!40000 ALTER TABLE `doc_permission_sets_ex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doc_permissions`
--

DROP TABLE IF EXISTS `doc_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc_permissions` (
  `permission_id` int(11) NOT NULL,
  `doc_type` int(11) NOT NULL,
  `lang_prefix` varchar(3) NOT NULL,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`permission_id`,`doc_type`,`lang_prefix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_permissions`
--

LOCK TABLES `doc_permissions` WRITE;
/*!40000 ALTER TABLE `doc_permissions` DISABLE KEYS */;
INSERT INTO `doc_permissions` VALUES (65536,2,'eng','Edit texts'),(65536,2,'swe','�ndra text'),(65536,5,'eng','Edit'),(65536,5,'swe','Redigera'),(65536,7,'eng','Edit'),(65536,7,'swe','Redigera'),(65536,8,'eng','Edit'),(65536,8,'swe','Redigera'),(131072,2,'eng','Edit pictures'),(131072,2,'swe','�ndra bild'),(262144,2,'eng','Edit menus'),(262144,2,'swe','�ndra meny'),(524288,2,'eng','Change template'),(524288,2,'swe','�ndra utseende'),(1048576,2,'eng','Change include'),(1048576,2,'swe','�ndra include');
/*!40000 ALTER TABLE `doc_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doc_types`
--

DROP TABLE IF EXISTS `doc_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc_types` (
  `doc_type` int(11) NOT NULL,
  `lang_prefix` varchar(3) NOT NULL DEFAULT 'swe',
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`doc_type`,`lang_prefix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_types`
--

LOCK TABLES `doc_types` WRITE;
/*!40000 ALTER TABLE `doc_types` DISABLE KEYS */;
INSERT INTO `doc_types` VALUES (2,'eng','Text page'),(2,'swe','Textsida'),(5,'eng','External link'),(5,'swe','Extern l�nk'),(7,'eng','HTML-document'),(7,'swe','HTML-dokument'),(8,'eng','File'),(8,'swe','Fil');
/*!40000 ALTER TABLE `doc_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_categories`
--

DROP TABLE IF EXISTS `document_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_categories` (
  `meta_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`meta_id`,`category_id`),
  KEY `document_categories_FK_category_id_categories` (`category_id`),
  CONSTRAINT `document_categories_FK_category_id_categories` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
  CONSTRAINT `document_categories_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_categories`
--

LOCK TABLES `document_categories` WRITE;
/*!40000 ALTER TABLE `document_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `document_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_properties`
--

DROP TABLE IF EXISTS `document_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_properties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meta_id` int(11) NOT NULL,
  `key_name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_document_properties__meta_id__key_name` (`meta_id`,`key_name`),
  CONSTRAINT `document_properties_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_properties`
--

LOCK TABLES `document_properties` WRITE;
/*!40000 ALTER TABLE `document_properties` DISABLE KEYS */;
INSERT INTO `document_properties` VALUES (3,1005,'imcms.document.alias','applications/import'),(5,1001,'imcms.document.alias',''),(7,1007,'imcms.document.alias','pupils'),(9,1009,'imcms.document.alias','pupils/edit'),(11,1011,'imcms.document.alias','applications/edit'),(13,1013,'imcms.document.alias','applications/show'),(15,1014,'imcms.document.alias','applications/version');
/*!40000 ALTER TABLE `document_properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_search_log`
--

DROP TABLE IF EXISTS `document_search_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_search_log` (
  `datetime` datetime NOT NULL,
  `term` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_search_log`
--

LOCK TABLES `document_search_log` WRITE;
/*!40000 ALTER TABLE `document_search_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `document_search_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fileupload_docs`
--

DROP TABLE IF EXISTS `fileupload_docs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fileupload_docs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) NOT NULL,
  `doc_version_no` int(11) NOT NULL,
  `variant_name` varchar(100) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `mime` varchar(50) NOT NULL,
  `created_as_image` int(11) NOT NULL,
  `default_variant` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__fileupload_docs__1` (`doc_id`,`doc_version_no`,`variant_name`),
  CONSTRAINT `fk__fileupload_docs__doc_version` FOREIGN KEY (`doc_id`, `doc_version_no`) REFERENCES `imcms_doc_versions` (`doc_id`, `no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fileupload_docs`
--

LOCK TABLES `fileupload_docs` WRITE;
/*!40000 ALTER TABLE `fileupload_docs` DISABLE KEYS */;
/*!40000 ALTER TABLE `fileupload_docs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frameset_docs`
--

DROP TABLE IF EXISTS `frameset_docs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frameset_docs` (
  `meta_id` int(11) NOT NULL,
  `frame_set` longtext,
  PRIMARY KEY (`meta_id`),
  CONSTRAINT `frameset_docs_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frameset_docs`
--

LOCK TABLES `frameset_docs` WRITE;
/*!40000 ALTER TABLE `frameset_docs` DISABLE KEYS */;
/*!40000 ALTER TABLE `frameset_docs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `images` (
  `meta_id` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `border` int(11) NOT NULL,
  `v_space` int(11) NOT NULL,
  `h_space` int(11) NOT NULL,
  `name` int(11) NOT NULL,
  `image_name` varchar(40) NOT NULL,
  `target` varchar(15) NOT NULL,
  `align` varchar(15) NOT NULL,
  `alt_text` varchar(255) NOT NULL,
  `low_scr` varchar(255) NOT NULL,
  `imgurl` varchar(255) NOT NULL,
  `linkurl` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `format` smallint(6) NOT NULL,
  `rotate_angle` smallint(6) NOT NULL,
  `crop_x1` int(11) NOT NULL,
  `crop_y1` int(11) NOT NULL,
  `crop_x2` int(11) NOT NULL,
  `crop_y2` int(11) NOT NULL,
  `archive_image_id` bigint(20) DEFAULT NULL,
  `gen_file` varchar(255) DEFAULT NULL,
  `resize` int(11) NOT NULL,
  PRIMARY KEY (`meta_id`,`name`),
  CONSTRAINT `images_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (1001,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,0,0,-1,-1,-1,-1,NULL,NULL,0);
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images_history`
--

DROP TABLE IF EXISTS `images_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `images_history` (
  `meta_id` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `border` int(11) NOT NULL,
  `v_space` int(11) NOT NULL,
  `h_space` int(11) NOT NULL,
  `name` int(11) NOT NULL,
  `image_name` varchar(40) NOT NULL,
  `target` varchar(15) NOT NULL,
  `align` varchar(15) NOT NULL,
  `alt_text` varchar(255) NOT NULL,
  `low_scr` varchar(255) NOT NULL,
  `imgurl` varchar(255) NOT NULL,
  `linkurl` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `modified_datetime` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `counter` int(11) NOT NULL AUTO_INCREMENT,
  `format` smallint(6) NOT NULL,
  `rotate_angle` smallint(6) NOT NULL,
  `crop_x1` int(11) NOT NULL,
  `crop_y1` int(11) NOT NULL,
  `crop_x2` int(11) NOT NULL,
  `crop_y2` int(11) NOT NULL,
  `archive_image_id` bigint(20) DEFAULT NULL,
  `gen_file` varchar(255) DEFAULT NULL,
  `resize` int(11) NOT NULL,
  PRIMARY KEY (`counter`),
  KEY `images_history_FK_meta_id_meta` (`meta_id`),
  KEY `images_history_FK_user_id_users` (`user_id`),
  CONSTRAINT `images_history_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`),
  CONSTRAINT `images_history_FK_user_id_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images_history`
--

LOCK TABLES `images_history` WRITE;
/*!40000 ALTER TABLE `images_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `images_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_doc_i18n_meta`
--

DROP TABLE IF EXISTS `imcms_doc_i18n_meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_doc_i18n_meta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `headline` varchar(256) DEFAULT NULL,
  `menu_image_url` varchar(256) DEFAULT NULL,
  `menu_text` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__imcms_doc_i18n_meta__doc_id__language_id` (`doc_id`,`language_id`),
  KEY `fk__imcms_doc_i18n_meta__languages` (`language_id`),
  CONSTRAINT `fk__imcms_doc_i18n_meta__languages` FOREIGN KEY (`language_id`) REFERENCES `imcms_languages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_doc_i18n_meta`
--

LOCK TABLES `imcms_doc_i18n_meta` WRITE;
/*!40000 ALTER TABLE `imcms_doc_i18n_meta` DISABLE KEYS */;
INSERT INTO `imcms_doc_i18n_meta` VALUES (1,1001,1,'Start page','',''),(3,1003,1,'Imp','',''),(5,1005,1,'Import','',''),(7,1007,1,'Pupils','',''),(9,1009,1,'Pupil','',''),(11,1011,1,'Application','',''),(13,1013,1,'ApplicationShow','',''),(15,1014,1,'ApplicationVersion','','');
/*!40000 ALTER TABLE `imcms_doc_i18n_meta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_doc_keywords`
--

DROP TABLE IF EXISTS `imcms_doc_keywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_doc_keywords` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) NOT NULL,
  `value` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__imcms_doc_keywords__doc_id__value` (`doc_id`,`value`),
  CONSTRAINT `fk__imcms_doc_keywords__meta` FOREIGN KEY (`doc_id`) REFERENCES `meta` (`meta_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_doc_keywords`
--

LOCK TABLES `imcms_doc_keywords` WRITE;
/*!40000 ALTER TABLE `imcms_doc_keywords` DISABLE KEYS */;
/*!40000 ALTER TABLE `imcms_doc_keywords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_doc_languages`
--

DROP TABLE IF EXISTS `imcms_doc_languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_doc_languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__imcms_doc_languages__doc_id__language_id` (`doc_id`,`language_id`),
  KEY `fk__imcms_doc_languages__languages` (`language_id`),
  CONSTRAINT `fk__imcms_doc_languages__languages` FOREIGN KEY (`language_id`) REFERENCES `imcms_languages` (`id`),
  CONSTRAINT `fk__imcms_doc_languages__meta` FOREIGN KEY (`doc_id`) REFERENCES `meta` (`meta_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_doc_languages`
--

LOCK TABLES `imcms_doc_languages` WRITE;
/*!40000 ALTER TABLE `imcms_doc_languages` DISABLE KEYS */;
INSERT INTO `imcms_doc_languages` VALUES (1,1001,1),(3,1003,1),(5,1005,1),(7,1007,1),(9,1009,1),(11,1011,1),(13,1013,1),(15,1014,1);
/*!40000 ALTER TABLE `imcms_doc_languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_doc_versions`
--

DROP TABLE IF EXISTS `imcms_doc_versions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_doc_versions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) NOT NULL,
  `no` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_dt` datetime NOT NULL,
  `modified_by` int(11) NOT NULL,
  `modified_dt` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__imcms_doc_versions__doc_id__no` (`doc_id`,`no`),
  KEY `fk__imcms_doc_versions__user1` (`created_by`),
  KEY `fk__imcms_doc_versions__user2` (`modified_by`),
  CONSTRAINT `fk__imcms_doc_versions__meta` FOREIGN KEY (`doc_id`) REFERENCES `meta` (`meta_id`) ON DELETE CASCADE,
  CONSTRAINT `fk__imcms_doc_versions__user1` FOREIGN KEY (`created_by`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk__imcms_doc_versions__user2` FOREIGN KEY (`modified_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_doc_versions`
--

LOCK TABLES `imcms_doc_versions` WRITE;
/*!40000 ALTER TABLE `imcms_doc_versions` DISABLE KEYS */;
INSERT INTO `imcms_doc_versions` VALUES (1,1001,0,1,'2010-04-07 16:25:53',1,'2015-06-16 15:46:57'),(3,1003,0,1,'2015-06-08 16:10:03',1,'2015-06-08 16:12:37'),(5,1005,0,1,'2015-06-08 16:10:07',1,'2015-06-08 16:12:40'),(7,1007,0,1,'2015-06-16 15:45:44',1,'2015-06-16 15:45:44'),(9,1009,0,1,'2015-06-16 15:46:39',1,'2015-06-16 15:46:39'),(11,1011,0,1,'2015-07-08 11:39:50',1,'2015-07-08 11:39:50'),(13,1013,0,1,'2015-04-01 15:11:50',1,'2015-04-01 15:19:16'),(15,1014,0,1,'2015-04-01 15:11:50',1,'2015-04-01 15:19:16');
/*!40000 ALTER TABLE `imcms_doc_versions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_html_docs`
--

DROP TABLE IF EXISTS `imcms_html_docs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_html_docs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) NOT NULL,
  `doc_version_no` int(11) NOT NULL,
  `html` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__imcms_html_docs__doc_id__doc_version_no` (`doc_id`,`doc_version_no`),
  CONSTRAINT `fk__imcms_html_docs__doc_version` FOREIGN KEY (`doc_id`, `doc_version_no`) REFERENCES `imcms_doc_versions` (`doc_id`, `no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_html_docs`
--

LOCK TABLES `imcms_html_docs` WRITE;
/*!40000 ALTER TABLE `imcms_html_docs` DISABLE KEYS */;
/*!40000 ALTER TABLE `imcms_html_docs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_languages`
--

DROP TABLE IF EXISTS `imcms_languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(3) NOT NULL COMMENT 'Language code.',
  `name` varchar(128) NOT NULL COMMENT 'Language name in english.',
  `native_name` varchar(128) DEFAULT NULL COMMENT 'Language native name e.g Svenska, Suomi, etc.',
  `enabled` tinyint(4) NOT NULL DEFAULT '1' COMMENT 'Language enabled status.',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__imcms_languages__code` (`code`),
  UNIQUE KEY `uk__imcms_languages__name` (`name`),
  UNIQUE KEY `uk__imcms_languages__native_name` (`native_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_languages`
--

LOCK TABLES `imcms_languages` WRITE;
/*!40000 ALTER TABLE `imcms_languages` DISABLE KEYS */;
INSERT INTO `imcms_languages` VALUES (1,'en','English','English',1),(2,'sv','Swedish','Svenska',1);
/*!40000 ALTER TABLE `imcms_languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_text_doc_content_loops`
--

DROP TABLE IF EXISTS `imcms_text_doc_content_loops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_text_doc_content_loops` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) NOT NULL,
  `doc_version_no` int(11) NOT NULL,
  `no` int(11) NOT NULL,
  `next_content_no` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__imcms_text_doc_content_loops` (`doc_id`,`doc_version_no`,`no`),
  CONSTRAINT `fk__imcms_text_doc_content_loops__imcms_doc_versions` FOREIGN KEY (`doc_id`, `doc_version_no`) REFERENCES `imcms_doc_versions` (`doc_id`, `no`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_text_doc_content_loops`
--

LOCK TABLES `imcms_text_doc_content_loops` WRITE;
/*!40000 ALTER TABLE `imcms_text_doc_content_loops` DISABLE KEYS */;
INSERT INTO `imcms_text_doc_content_loops` VALUES (1,1001,0,1,2),(3,1001,0,100,2);
/*!40000 ALTER TABLE `imcms_text_doc_content_loops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_text_doc_contents`
--

DROP TABLE IF EXISTS `imcms_text_doc_contents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_text_doc_contents` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `loop_id` int(11) NOT NULL,
  `no` int(11) NOT NULL,
  `ix` int(11) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  UNIQUE KEY `id` (`id`),
  KEY `fk__content_loop` (`loop_id`),
  KEY `ix__content_order` (`ix`),
  CONSTRAINT `fk__content_loop` FOREIGN KEY (`loop_id`) REFERENCES `imcms_text_doc_content_loops` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_text_doc_contents`
--

LOCK TABLES `imcms_text_doc_contents` WRITE;
/*!40000 ALTER TABLE `imcms_text_doc_contents` DISABLE KEYS */;
INSERT INTO `imcms_text_doc_contents` VALUES (1,1,1,0,1),(3,3,1,0,1);
/*!40000 ALTER TABLE `imcms_text_doc_contents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `imcms_text_doc_contents_v`
--

DROP TABLE IF EXISTS `imcms_text_doc_contents_v`;
/*!50001 DROP VIEW IF EXISTS `imcms_text_doc_contents_v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `imcms_text_doc_contents_v` (
  `doc_id` tinyint NOT NULL,
  `doc_version_no` tinyint NOT NULL,
  `loop_no` tinyint NOT NULL,
  `content_no` tinyint NOT NULL,
  `content_ix` tinyint NOT NULL,
  `content_enabled` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `imcms_text_doc_images`
--

DROP TABLE IF EXISTS `imcms_text_doc_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_text_doc_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) DEFAULT NULL,
  `doc_version_no` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `border` int(11) NOT NULL,
  `v_space` int(11) NOT NULL,
  `h_space` int(11) NOT NULL,
  `no` int(11) NOT NULL,
  `image_name` varchar(40) NOT NULL DEFAULT '',
  `target` varchar(15) NOT NULL,
  `align` varchar(15) NOT NULL,
  `alt_text` varchar(255) NOT NULL,
  `low_scr` varchar(255) NOT NULL,
  `imgurl` varchar(255) NOT NULL,
  `linkurl` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `archive_image_id` bigint(20) DEFAULT NULL,
  `format` smallint(6) NOT NULL,
  `rotate_angle` smallint(6) NOT NULL,
  `crop_x1` int(11) NOT NULL,
  `crop_y1` int(11) NOT NULL,
  `crop_x2` int(11) NOT NULL,
  `crop_y2` int(11) NOT NULL,
  `content_loop_no` int(11) DEFAULT NULL,
  `content_no` int(11) DEFAULT NULL,
  `gen_file` varchar(255) DEFAULT NULL,
  `resize` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__imcms_text_doc_images__image` (`doc_id`,`doc_version_no`,`no`,`language_id`,`content_loop_no`,`content_no`),
  KEY `fk__imcms_text_doc_images__languages` (`language_id`),
  KEY `fk__imcms_text_doc_images__content` (`doc_id`,`doc_version_no`,`content_loop_no`,`content_no`),
  CONSTRAINT `fk__imcms_text_doc_images__doc_version` FOREIGN KEY (`doc_id`, `doc_version_no`) REFERENCES `imcms_doc_versions` (`doc_id`, `no`) ON DELETE CASCADE,
  CONSTRAINT `fk__imcms_text_doc_images__languages` FOREIGN KEY (`language_id`) REFERENCES `imcms_languages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_text_doc_images`
--

LOCK TABLES `imcms_text_doc_images` WRITE;
/*!40000 ALTER TABLE `imcms_text_doc_images` DISABLE KEYS */;
INSERT INTO `imcms_text_doc_images` VALUES (15,1003,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,NULL,0),(17,1005,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,NULL,0),(27,1001,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,NULL,0),(29,1001,0,0,0,0,0,0,33,'','','','','','','',-1,1,NULL,0,0,-1,-1,-1,-1,1,1,NULL,0),(31,1001,0,0,0,0,0,0,3,'','','','','','','',-1,1,NULL,0,0,-1,-1,-1,-1,100,1,NULL,0);
/*!40000 ALTER TABLE `imcms_text_doc_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_text_doc_images_cache`
--

DROP TABLE IF EXISTS `imcms_text_doc_images_cache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_text_doc_images_cache` (
  `id` varchar(40) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `cache_type` smallint(6) NOT NULL,
  `file_size` int(11) NOT NULL,
  `frequency` int(11) NOT NULL,
  `format` smallint(6) NOT NULL,
  `rotate_angle` smallint(6) NOT NULL,
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `crop_x1` int(11) NOT NULL,
  `crop_y1` int(11) NOT NULL,
  `crop_x2` int(11) NOT NULL,
  `crop_y2` int(11) NOT NULL,
  `created_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_text_doc_images_cache`
--

LOCK TABLES `imcms_text_doc_images_cache` WRITE;
/*!40000 ALTER TABLE `imcms_text_doc_images_cache` DISABLE KEYS */;
INSERT INTO `imcms_text_doc_images_cache` VALUES ('dd4fe3cea0bdfd5bc0790614cb05f836baa99b42','/images/imCMSpower.gif',1,1834,18,0,0,0,0,-1,-1,-1,-1,'2015-06-16 08:57:20');
/*!40000 ALTER TABLE `imcms_text_doc_images_cache` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_text_doc_images_history`
--

DROP TABLE IF EXISTS `imcms_text_doc_images_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_text_doc_images_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) DEFAULT NULL,
  `doc_version_no` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `border` int(11) NOT NULL,
  `v_space` int(11) NOT NULL,
  `h_space` int(11) NOT NULL,
  `no` int(11) NOT NULL,
  `image_name` varchar(40) NOT NULL DEFAULT '',
  `target` varchar(15) NOT NULL,
  `align` varchar(15) NOT NULL,
  `alt_text` varchar(255) NOT NULL,
  `low_scr` varchar(255) NOT NULL,
  `imgurl` varchar(255) NOT NULL,
  `linkurl` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `archive_image_id` bigint(20) DEFAULT NULL,
  `format` smallint(6) NOT NULL,
  `rotate_angle` smallint(6) NOT NULL,
  `crop_x1` int(11) NOT NULL,
  `crop_y1` int(11) NOT NULL,
  `crop_x2` int(11) NOT NULL,
  `crop_y2` int(11) NOT NULL,
  `content_loop_no` int(11) DEFAULT NULL,
  `content_no` int(11) DEFAULT NULL,
  `modified_dt` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `gen_file` varchar(255) DEFAULT NULL,
  `resize` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk__imcms_text_doc_images_history__languages` (`language_id`),
  KEY `fk__imcms_text_doc_images_history__content` (`doc_id`,`doc_version_no`,`content_loop_no`,`content_no`),
  KEY `fk__imcms_text_doc_images_history__users` (`user_id`),
  CONSTRAINT `fk__imcms_text_doc_images_history__doc_version` FOREIGN KEY (`doc_id`, `doc_version_no`) REFERENCES `imcms_doc_versions` (`doc_id`, `no`) ON DELETE CASCADE,
  CONSTRAINT `fk__imcms_text_doc_images_history__languages` FOREIGN KEY (`language_id`) REFERENCES `imcms_languages` (`id`),
  CONSTRAINT `fk__imcms_text_doc_images_history__users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_text_doc_images_history`
--

LOCK TABLES `imcms_text_doc_images_history` WRITE;
/*!40000 ALTER TABLE `imcms_text_doc_images_history` DISABLE KEYS */;
INSERT INTO `imcms_text_doc_images_history` VALUES (1,1001,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,'2015-06-08 16:07:13',1,NULL,0),(3,1001,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,'2015-06-08 16:07:51',1,NULL,0),(5,1003,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,'2015-06-08 16:10:03',1,NULL,0),(7,1005,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,'2015-06-08 16:10:07',1,NULL,0),(9,1003,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,'2015-06-08 16:11:13',1,NULL,0),(11,1003,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,'2015-06-08 16:12:24',1,NULL,0),(13,1003,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,'2015-06-08 16:12:37',1,NULL,0),(15,1005,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,'2015-06-08 16:13:10',1,NULL,0),(17,1001,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,'2015-06-08 16:13:39',1,NULL,0),(19,1001,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,'2015-06-16 15:39:10',1,NULL,0),(21,1001,0,0,0,0,0,0,33,'','','','','','','',-1,1,NULL,0,0,-1,-1,-1,-1,1,1,'2015-06-16 15:39:10',1,NULL,0),(23,1001,0,0,0,0,0,0,3,'','','','','','','',-1,1,NULL,0,0,-1,-1,-1,-1,100,1,'2015-06-16 15:39:10',1,NULL,0),(25,1001,0,0,0,0,0,0,3,'','_blank','top','','','imCMSpower.gif','http://www.imcms.net',0,1,NULL,0,0,-1,-1,-1,-1,NULL,NULL,'2015-06-16 15:46:57',1,NULL,0),(27,1001,0,0,0,0,0,0,33,'','','','','','','',-1,1,NULL,0,0,-1,-1,-1,-1,1,1,'2015-06-16 15:46:57',1,NULL,0),(29,1001,0,0,0,0,0,0,3,'','','','','','','',-1,1,NULL,0,0,-1,-1,-1,-1,100,1,'2015-06-16 15:46:57',1,NULL,0);
/*!40000 ALTER TABLE `imcms_text_doc_images_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_text_doc_menu_items`
--

DROP TABLE IF EXISTS `imcms_text_doc_menu_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_text_doc_menu_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL,
  `to_doc_id` int(11) NOT NULL,
  `manual_sort_order` int(11) NOT NULL,
  `tree_sort_index` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__imcms_text_doc_menu_items__menu_id__doc_id` (`menu_id`,`to_doc_id`),
  CONSTRAINT `fk__imcms_text_doc_menu_items__menu` FOREIGN KEY (`menu_id`) REFERENCES `imcms_text_doc_menus` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_text_doc_menu_items`
--

LOCK TABLES `imcms_text_doc_menu_items` WRITE;
/*!40000 ALTER TABLE `imcms_text_doc_menu_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `imcms_text_doc_menu_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_text_doc_menu_items_history`
--

DROP TABLE IF EXISTS `imcms_text_doc_menu_items_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_text_doc_menu_items_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `to_doc_id` int(11) NOT NULL,
  `manual_sort_order` int(11) NOT NULL,
  `tree_sort_index` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_text_doc_menu_items_history`
--

LOCK TABLES `imcms_text_doc_menu_items_history` WRITE;
/*!40000 ALTER TABLE `imcms_text_doc_menu_items_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `imcms_text_doc_menu_items_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_text_doc_menus`
--

DROP TABLE IF EXISTS `imcms_text_doc_menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_text_doc_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) NOT NULL,
  `doc_version_no` int(11) NOT NULL,
  `no` int(11) NOT NULL,
  `sort_order` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__imcms_text_doc_menus__doc_id__doc_version_no__no` (`doc_id`,`doc_version_no`,`no`),
  CONSTRAINT `fk__imcms_text_doc_menus__doc_version` FOREIGN KEY (`doc_id`, `doc_version_no`) REFERENCES `imcms_doc_versions` (`doc_id`, `no`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_text_doc_menus`
--

LOCK TABLES `imcms_text_doc_menus` WRITE;
/*!40000 ALTER TABLE `imcms_text_doc_menus` DISABLE KEYS */;
INSERT INTO `imcms_text_doc_menus` VALUES (5,1001,0,1,4);
/*!40000 ALTER TABLE `imcms_text_doc_menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_text_doc_menus_history`
--

DROP TABLE IF EXISTS `imcms_text_doc_menus_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_text_doc_menus_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL,
  `doc_id` int(11) NOT NULL,
  `doc_version_no` int(11) NOT NULL,
  `no` int(11) NOT NULL,
  `sort_order` int(11) NOT NULL,
  `modified_dt` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk__imcms_text_doc_menus_history__doc_versions` (`doc_id`,`doc_version_no`),
  CONSTRAINT `fk__imcms_text_doc_menus_history__doc_versions` FOREIGN KEY (`doc_id`, `doc_version_no`) REFERENCES `imcms_doc_versions` (`doc_id`, `no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_text_doc_menus_history`
--

LOCK TABLES `imcms_text_doc_menus_history` WRITE;
/*!40000 ALTER TABLE `imcms_text_doc_menus_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `imcms_text_doc_menus_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_text_doc_texts`
--

DROP TABLE IF EXISTS `imcms_text_doc_texts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_text_doc_texts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) DEFAULT NULL,
  `doc_version_no` int(11) NOT NULL,
  `no` int(11) NOT NULL,
  `text` longtext NOT NULL,
  `type` int(11) DEFAULT NULL,
  `language_id` int(11) NOT NULL,
  `content_loop_no` int(11) DEFAULT NULL,
  `content_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__imcms_text_doc_texts__text` (`doc_id`,`doc_version_no`,`no`,`language_id`,`content_loop_no`,`content_no`),
  KEY `fk__imcms_text_doc_texts__content` (`doc_id`,`doc_version_no`,`content_loop_no`,`content_no`),
  KEY `fk__imcms_text_doc_texts__languages` (`language_id`),
  CONSTRAINT `fk__imcms_text_doc_texts__doc_version` FOREIGN KEY (`doc_id`, `doc_version_no`) REFERENCES `imcms_doc_versions` (`doc_id`, `no`) ON DELETE CASCADE,
  CONSTRAINT `fk__imcms_text_doc_texts__languages` FOREIGN KEY (`language_id`) REFERENCES `imcms_languages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_text_doc_texts`
--

LOCK TABLES `imcms_text_doc_texts` WRITE;
/*!40000 ALTER TABLE `imcms_text_doc_texts` DISABLE KEYS */;
INSERT INTO `imcms_text_doc_texts` VALUES (31,1003,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL),(33,1003,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL),(35,1005,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL),(37,1005,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL),(47,1001,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL),(49,1001,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL);
/*!40000 ALTER TABLE `imcms_text_doc_texts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_text_doc_texts_history`
--

DROP TABLE IF EXISTS `imcms_text_doc_texts_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_text_doc_texts_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) DEFAULT NULL,
  `doc_version_no` int(11) DEFAULT NULL,
  `no` int(11) NOT NULL,
  `text` longtext NOT NULL,
  `type` int(11) DEFAULT NULL,
  `language_id` int(11) NOT NULL,
  `content_loop_no` int(11) DEFAULT NULL,
  `content_no` int(11) DEFAULT NULL,
  `modified_dt` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk__imcms_text_doc_texts_history__languages` (`language_id`),
  KEY `fk__imcms_text_doc_texts_history__content` (`doc_id`,`doc_version_no`,`content_loop_no`,`content_no`),
  KEY `fk__imcms_text_doc_texts_history__users` (`user_id`),
  CONSTRAINT `fk__imcms_text_doc_texts_history__doc_versions` FOREIGN KEY (`doc_id`, `doc_version_no`) REFERENCES `imcms_doc_versions` (`doc_id`, `no`) ON DELETE CASCADE,
  CONSTRAINT `fk__imcms_text_doc_texts_history__languages` FOREIGN KEY (`language_id`) REFERENCES `imcms_languages` (`id`),
  CONSTRAINT `fk__imcms_text_doc_texts_history__users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_text_doc_texts_history`
--

LOCK TABLES `imcms_text_doc_texts_history` WRITE;
/*!40000 ALTER TABLE `imcms_text_doc_texts_history` DISABLE KEYS */;
INSERT INTO `imcms_text_doc_texts_history` VALUES (1,1001,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL,'2010-04-07 16:25:53',1),(3,1001,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL,'2010-04-07 16:25:53',1),(7,1001,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL,'2015-06-08 16:07:13',1),(9,1001,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL,'2015-06-08 16:07:13',1),(11,1001,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL,'2015-06-08 16:07:51',1),(13,1001,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL,'2015-06-08 16:07:51',1),(15,1003,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL,'2015-06-08 16:10:03',1),(17,1003,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL,'2015-06-08 16:10:03',1),(19,1005,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL,'2015-06-08 16:10:07',1),(21,1005,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL,'2015-06-08 16:10:07',1),(23,1003,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL,'2015-06-08 16:11:13',1),(25,1003,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL,'2015-06-08 16:11:13',1),(27,1003,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL,'2015-06-08 16:12:24',1),(29,1003,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL,'2015-06-08 16:12:24',1),(31,1003,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL,'2015-06-08 16:12:37',1),(33,1003,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL,'2015-06-08 16:12:37',1),(35,1005,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL,'2015-06-08 16:13:09',1),(37,1005,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL,'2015-06-08 16:13:10',1),(39,1001,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL,'2015-06-08 16:13:39',1),(41,1001,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL,'2015-06-08 16:13:39',1),(43,1001,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL,'2015-06-16 15:39:10',1),(45,1001,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL,'2015-06-16 15:39:10',1),(47,1001,0,1,'<h3>Welcome to imCMS</h3>',1,1,NULL,NULL,'2015-06-16 15:46:57',1),(49,1001,0,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,1,NULL,NULL,'2015-06-16 15:46:57',1);
/*!40000 ALTER TABLE `imcms_text_doc_texts_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imcms_url_docs`
--

DROP TABLE IF EXISTS `imcms_url_docs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imcms_url_docs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) NOT NULL,
  `doc_version_no` int(11) NOT NULL,
  `frame_name` varchar(80) NOT NULL,
  `target` varchar(15) NOT NULL,
  `url_ref` varchar(255) NOT NULL,
  `url_txt` varchar(255) NOT NULL,
  `lang_prefix` varchar(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__imcms_url_docs__doc_id__doc_version_no` (`doc_id`,`doc_version_no`),
  CONSTRAINT `fk__imcms_url_docs__doc_version` FOREIGN KEY (`doc_id`, `doc_version_no`) REFERENCES `imcms_doc_versions` (`doc_id`, `no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imcms_url_docs`
--

LOCK TABLES `imcms_url_docs` WRITE;
/*!40000 ALTER TABLE `imcms_url_docs` DISABLE KEYS */;
/*!40000 ALTER TABLE `imcms_url_docs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `includes`
--

DROP TABLE IF EXISTS `includes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `includes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meta_id` int(11) DEFAULT NULL,
  `include_id` int(11) NOT NULL,
  `included_meta_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__includes__meta_id__include_id` (`meta_id`,`include_id`),
  CONSTRAINT `fk__includes__included_document` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`),
  CONSTRAINT `fk__includes__meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `includes`
--

LOCK TABLES `includes` WRITE;
/*!40000 ALTER TABLE `includes` DISABLE KEYS */;
/*!40000 ALTER TABLE `includes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_accesses`
--

DROP TABLE IF EXISTS `ip_accesses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_accesses` (
  `ip_access_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `ip_start` decimal(18,0) NOT NULL,
  `ip_end` decimal(18,0) NOT NULL,
  PRIMARY KEY (`ip_access_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_accesses`
--

LOCK TABLES `ip_accesses` WRITE;
/*!40000 ALTER TABLE `ip_accesses` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_accesses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lang_prefixes`
--

DROP TABLE IF EXISTS `lang_prefixes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lang_prefixes` (
  `lang_id` int(11) NOT NULL,
  `lang_prefix` char(3) DEFAULT NULL,
  PRIMARY KEY (`lang_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lang_prefixes`
--

LOCK TABLES `lang_prefixes` WRITE;
/*!40000 ALTER TABLE `lang_prefixes` DISABLE KEYS */;
INSERT INTO `lang_prefixes` VALUES (1,'swe'),(2,'eng');
/*!40000 ALTER TABLE `lang_prefixes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `languages` (
  `lang_prefix` varchar(3) NOT NULL,
  `user_prefix` varchar(3) NOT NULL,
  `language` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`lang_prefix`,`user_prefix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES ('eng','eng','English'),('eng','swe','Engelska'),('swe','eng','Swedish'),('swe','swe','Svenska');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menus` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `meta_id` int(11) NOT NULL,
  `menu_index` int(11) NOT NULL,
  `sort_order` int(11) NOT NULL,
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `UQ_menus__meta_id__menu_index` (`meta_id`,`menu_index`),
  CONSTRAINT `menus_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus_history`
--

DROP TABLE IF EXISTS `menus_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menus_history` (
  `menu_id` int(11) NOT NULL,
  `meta_id` int(11) NOT NULL,
  `menu_index` int(11) NOT NULL,
  `sort_order` int(11) NOT NULL,
  `modified_datetime` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `menus_history_FK_meta_id_meta` (`meta_id`),
  CONSTRAINT `menus_history_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus_history`
--

LOCK TABLES `menus_history` WRITE;
/*!40000 ALTER TABLE `menus_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `menus_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta`
--

DROP TABLE IF EXISTS `meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta` (
  `meta_id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_type` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `permissions` int(11) NOT NULL,
  `shared` int(11) NOT NULL,
  `show_meta` int(11) NOT NULL,
  `lang_prefix` varchar(3) NOT NULL,
  `date_created` datetime NOT NULL,
  `date_modified` datetime NOT NULL,
  `disable_search` int(11) NOT NULL,
  `target` varchar(10) NOT NULL,
  `activate` int(11) NOT NULL,
  `archived_datetime` datetime DEFAULT NULL,
  `publisher_id` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `publication_start_datetime` datetime DEFAULT NULL,
  `publication_end_datetime` datetime DEFAULT NULL,
  `disabled_language_show_rule` varchar(32) NOT NULL DEFAULT 'DO_NOT_SHOW',
  `default_version_no` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`meta_id`),
  KEY `meta_FK_owner_id_users` (`owner_id`),
  KEY `meta_FK_publisher_id_users` (`publisher_id`),
  CONSTRAINT `meta_FK_owner_id_users` FOREIGN KEY (`owner_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `meta_FK_publisher_id_users` FOREIGN KEY (`publisher_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1015 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta`
--

LOCK TABLES `meta` WRITE;
/*!40000 ALTER TABLE `meta` DISABLE KEYS */;
INSERT INTO `meta` VALUES (1001,2,1,0,0,0,'','2015-06-08 16:13:32','2015-06-16 15:46:57',0,'_self',1,NULL,1,2,'2010-04-07 16:25:53',NULL,'DO_NOT_SHOW',0),(1003,2,1,0,0,0,'','2015-06-08 16:12:29','2015-06-08 16:12:37',0,'_self',1,NULL,1,2,'2015-06-08 16:10:02',NULL,'DO_NOT_SHOW',0),(1005,2,1,0,0,0,'','2015-06-08 16:12:40','2015-06-08 16:12:40',0,'_self',1,NULL,1,2,'2015-06-08 16:10:07',NULL,'DO_NOT_SHOW',0),(1007,2,1,0,0,0,'','2015-06-16 15:45:44','2015-06-16 15:45:44',0,'_self',1,NULL,1,2,'2015-06-16 15:45:44',NULL,'DO_NOT_SHOW',0),(1009,2,1,0,0,0,'','2015-06-16 15:46:39','2015-06-16 15:46:39',0,'_self',1,NULL,1,2,'2015-06-16 15:46:39',NULL,'DO_NOT_SHOW',0),(1011,2,1,0,0,0,'','2015-07-08 11:39:50','2015-07-08 11:39:50',0,'_self',1,NULL,1,2,'2015-07-08 11:39:50',NULL,'DO_NOT_SHOW',0),(1013,2,1,0,0,0,'','2015-04-01 15:19:16','2015-04-01 15:19:16',0,'_self',1,NULL,1,2,'2015-04-01 15:11:50',NULL,'DO_NOT_SHOW',0),(1014,2,1,0,0,0,'','2015-04-01 15:19:16','2015-04-01 15:19:16',0,'_self',1,NULL,1,2,'2015-04-01 15:11:50',NULL,'DO_NOT_SHOW',0);
/*!40000 ALTER TABLE `meta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta_classification`
--

DROP TABLE IF EXISTS `meta_classification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta_classification` (
  `meta_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  PRIMARY KEY (`meta_id`,`class_id`),
  KEY `meta_classification_FK_class_id_classification` (`class_id`),
  CONSTRAINT `meta_classification_FK_class_id_classification` FOREIGN KEY (`class_id`) REFERENCES `classification` (`class_id`),
  CONSTRAINT `meta_classification_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta_classification`
--

LOCK TABLES `meta_classification` WRITE;
/*!40000 ALTER TABLE `meta_classification` DISABLE KEYS */;
/*!40000 ALTER TABLE `meta_classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta_section`
--

DROP TABLE IF EXISTS `meta_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta_section` (
  `meta_id` int(11) NOT NULL,
  `section_id` int(11) NOT NULL,
  PRIMARY KEY (`meta_id`,`section_id`),
  KEY `meta_section_FK_section_id_sections` (`section_id`),
  CONSTRAINT `meta_section_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`),
  CONSTRAINT `meta_section_FK_section_id_sections` FOREIGN KEY (`section_id`) REFERENCES `sections` (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta_section`
--

LOCK TABLES `meta_section` WRITE;
/*!40000 ALTER TABLE `meta_section` DISABLE KEYS */;
/*!40000 ALTER TABLE `meta_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mime_types`
--

DROP TABLE IF EXISTS `mime_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mime_types` (
  `mime_id` int(11) NOT NULL,
  `mime_name` varchar(50) NOT NULL,
  `mime` varchar(50) NOT NULL,
  `lang_prefix` varchar(3) NOT NULL DEFAULT 'swe',
  PRIMARY KEY (`mime_id`,`lang_prefix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mime_types`
--

LOCK TABLES `mime_types` WRITE;
/*!40000 ALTER TABLE `mime_types` DISABLE KEYS */;
INSERT INTO `mime_types` VALUES (0,'Other...','other','eng'),(0,'Annan...','other','swe'),(1,'Plain text','text/plain','eng'),(1,'Vanlig text','text/plain','swe'),(2,'HTML-document','text/html','eng'),(2,'HTML-dokument','text/html','swe'),(3,'Binary file','application/octet-stream','eng'),(3,'Bin�rfil','application/octet-stream','swe'),(4,'Shockwave Flash','application/x-shockwave-flash','eng'),(4,'Shockwave Flash','application/x-shockwave-flash','swe'),(5,'Shockwave Director','application/x-director','eng'),(5,'Shockwave Director','application/x-director','swe'),(6,'PNG-image','image/png','eng'),(6,'PNG-bild','image/png','swe'),(7,'GIF-image','image/gif','eng'),(7,'GIF-bild','image/gif','swe'),(8,'JPEG-image','image/jpeg','eng'),(8,'JPEG-bild','image/jpeg','swe'),(9,'Adobe Acrobat-document','application/pdf','eng'),(9,'Adobe Acrobat-dokument','application/pdf','swe'),(10,'Wav-sound','audio/x-wav','eng'),(10,'Wav-ljud','audio/x-wav','swe'),(11,'Zip-file','application/zip','eng'),(11,'Zip-fil','application/zip','swe'),(12,'AVI-movie','video/x-msvideo','eng'),(12,'AVI-film','video/x-msvideo','swe'),(13,'Quicktime-movie','video/quicktime','eng'),(13,'Quicktime-film','video/quicktime','swe'),(14,'MPEG-movie','video/mpeg','eng'),(14,'MPEG-film','video/mpeg','swe'),(15,'MS Word-document','application/msword','eng'),(15,'MS Word-dokument','application/msword','swe'),(16,'MS Excel-document','application/vnd.ms-excel','eng'),(16,'MS Excel-dokument','application/vnd.ms-excel','swe'),(17,'MS Powerpoint-document','application/vnd.ms-powerpoint','eng'),(17,'MS Powerpoint-dokument','application/vnd.ms-powerpoint','swe');
/*!40000 ALTER TABLE `mime_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_doc_permission_sets`
--

DROP TABLE IF EXISTS `new_doc_permission_sets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `new_doc_permission_sets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meta_id` int(11) NOT NULL,
  `set_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__new_doc_permission_sets__meta_id__set_id` (`meta_id`,`set_id`),
  KEY `fk__new_doc_permission_sets__permission_sets` (`set_id`),
  CONSTRAINT `fk__new_doc_permission_sets__meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`) ON DELETE CASCADE,
  CONSTRAINT `fk__new_doc_permission_sets__permission_sets` FOREIGN KEY (`set_id`) REFERENCES `permission_sets` (`set_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_doc_permission_sets`
--

LOCK TABLES `new_doc_permission_sets` WRITE;
/*!40000 ALTER TABLE `new_doc_permission_sets` DISABLE KEYS */;
INSERT INTO `new_doc_permission_sets` VALUES (1,1001,1,0),(3,1001,2,0),(5,1003,1,0),(7,1003,2,0),(9,1005,1,0),(11,1005,2,0),(13,1007,1,0),(15,1007,2,0),(17,1009,1,0),(19,1009,2,0),(21,1011,1,0),(23,1011,2,0),(25,1013,1,0),(27,1013,2,0),(29,1014,1,0),(31,1014,2,0);
/*!40000 ALTER TABLE `new_doc_permission_sets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_doc_permission_sets_ex`
--

DROP TABLE IF EXISTS `new_doc_permission_sets_ex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `new_doc_permission_sets_ex` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meta_id` int(11) NOT NULL,
  `set_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `permission_data` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__new_doc_permission_sets_ex__1` (`meta_id`,`set_id`,`permission_id`,`permission_data`),
  KEY `fk__new_doc_permission_sets_ex__permission_sets` (`set_id`),
  CONSTRAINT `fk__new_doc_permission_sets_ex__meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`) ON DELETE CASCADE,
  CONSTRAINT `fk__new_doc_permission_sets_ex__permission_sets` FOREIGN KEY (`set_id`) REFERENCES `permission_sets` (`set_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_doc_permission_sets_ex`
--

LOCK TABLES `new_doc_permission_sets_ex` WRITE;
/*!40000 ALTER TABLE `new_doc_permission_sets_ex` DISABLE KEYS */;
/*!40000 ALTER TABLE `new_doc_permission_sets_ex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_sets`
--

DROP TABLE IF EXISTS `permission_sets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_sets` (
  `set_id` int(11) NOT NULL,
  `description` varchar(30) NOT NULL,
  PRIMARY KEY (`set_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_sets`
--

LOCK TABLES `permission_sets` WRITE;
/*!40000 ALTER TABLE `permission_sets` DISABLE KEYS */;
INSERT INTO `permission_sets` VALUES (0,'Full'),(1,'Begr�nsad 1'),(2,'Begr�nsad 2'),(3,'L�s');
/*!40000 ALTER TABLE `permission_sets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `permission_id` smallint(6) NOT NULL,
  `lang_prefix` varchar(3) NOT NULL DEFAULT 'swe',
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`permission_id`,`lang_prefix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,'eng','Edit headline'),(1,'swe','�ndra rubrik'),(2,'eng','Edit docinfo'),(2,'swe','�ndra dokinfo'),(4,'eng','Edit permissions'),(4,'swe','�ndra r�ttigheter f�r roller'),(8,'eng','Create document'),(8,'swe','Skapa dokument');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phones`
--

DROP TABLE IF EXISTS `phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phones` (
  `phone_id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(25) NOT NULL,
  `user_id` int(11) NOT NULL,
  `phonetype_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`phone_id`,`user_id`),
  KEY `phones_FK_user_id_users` (`user_id`),
  CONSTRAINT `phones_FK_user_id_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phones`
--

LOCK TABLES `phones` WRITE;
/*!40000 ALTER TABLE `phones` DISABLE KEYS */;
/*!40000 ALTER TABLE `phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phonetypes`
--

DROP TABLE IF EXISTS `phonetypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phonetypes` (
  `phonetype_id` int(11) NOT NULL,
  `typename` varchar(12) NOT NULL,
  `lang_id` int(11) NOT NULL,
  PRIMARY KEY (`phonetype_id`,`lang_id`),
  KEY `phonetypes_FK_lang_id_lang_prefixes` (`lang_id`),
  CONSTRAINT `phonetypes_FK_lang_id_lang_prefixes` FOREIGN KEY (`lang_id`) REFERENCES `lang_prefixes` (`lang_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phonetypes`
--

LOCK TABLES `phonetypes` WRITE;
/*!40000 ALTER TABLE `phonetypes` DISABLE KEYS */;
INSERT INTO `phonetypes` VALUES (0,'Annat',1),(0,'Other',2),(1,'Bostad',1),(1,'Home',2),(2,'Arbete',1),(2,'Work',2),(3,'Mobil',1),(3,'Mobile',2),(4,'Fax',1),(4,'Fax',2);
/*!40000 ALTER TABLE `phonetypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profiles` (
  `profile_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `document_name` varchar(255) NOT NULL,
  PRIMARY KEY (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(60) NOT NULL,
  `permissions` int(11) NOT NULL DEFAULT '0',
  `admin_role` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UQ_roles__role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (0,'Superadmin',0,1),(1,'Useradmin',0,2),(2,'Users',1,0);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_rights`
--

DROP TABLE IF EXISTS `roles_rights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_rights` (
  `role_id` int(11) NOT NULL,
  `meta_id` int(11) NOT NULL,
  `set_id` smallint(6) NOT NULL,
  PRIMARY KEY (`role_id`,`meta_id`),
  KEY `roles_rights_FK_meta_id_meta` (`meta_id`),
  CONSTRAINT `roles_rights_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`),
  CONSTRAINT `roles_rights_FK_role_id_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_rights`
--

LOCK TABLES `roles_rights` WRITE;
/*!40000 ALTER TABLE `roles_rights` DISABLE KEYS */;
INSERT INTO `roles_rights` VALUES (2,1001,3),(2,1003,3),(2,1005,3),(2,1007,3),(2,1009,3),(2,1011,3),(2,1013,3),(2,1014,3);
/*!40000 ALTER TABLE `roles_rights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sections`
--

DROP TABLE IF EXISTS `sections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sections` (
  `section_id` int(11) NOT NULL AUTO_INCREMENT,
  `section_name` varchar(50) NOT NULL,
  PRIMARY KEY (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sections`
--

LOCK TABLES `sections` WRITE;
/*!40000 ALTER TABLE `sections` DISABLE KEYS */;
/*!40000 ALTER TABLE `sections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stats`
--

DROP TABLE IF EXISTS `stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stats` (
  `name` varchar(120) NOT NULL,
  `num` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stats`
--

LOCK TABLES `stats` WRITE;
/*!40000 ALTER TABLE `stats` DISABLE KEYS */;
/*!40000 ALTER TABLE `stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_data`
--

DROP TABLE IF EXISTS `sys_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_data` (
  `sys_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `type_id` smallint(6) NOT NULL,
  `value` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`sys_id`,`type_id`),
  KEY `sys_data_FK_type_id_sys_types` (`type_id`),
  CONSTRAINT `sys_data_FK_type_id_sys_types` FOREIGN KEY (`type_id`) REFERENCES `sys_types` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data`
--

LOCK TABLES `sys_data` WRITE;
/*!40000 ALTER TABLE `sys_data` DISABLE KEYS */;
INSERT INTO `sys_data` VALUES (0,0,'1001'),(1,1,'1256'),(2,2,'2010-04-07 16:25:53'),(3,3,''),(4,4,''),(5,5,''),(6,6,''),(7,7,''),(9,9,'24'),(11,8,'1');
/*!40000 ALTER TABLE `sys_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_types`
--

DROP TABLE IF EXISTS `sys_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_types` (
  `type_id` smallint(6) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_types`
--

LOCK TABLES `sys_types` WRITE;
/*!40000 ALTER TABLE `sys_types` DISABLE KEYS */;
INSERT INTO `sys_types` VALUES (0,'StartDocument'),(1,'SessionCounter'),(2,'SessionCounterDate'),(3,'SystemMessage'),(4,'ServerMaster'),(5,'ServerMasterAddress'),(6,'WebMaster'),(7,'WebMasterAddress'),(8,'DefaultLanguageId'),(9,'UserLoginPasswordResetExpirationInterval');
/*!40000 ALTER TABLE `sys_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `template`
--

DROP TABLE IF EXISTS `template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `template_name` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `is_hidden` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template`
--

LOCK TABLES `template` WRITE;
/*!40000 ALTER TABLE `template` DISABLE KEYS */;
INSERT INTO `template` VALUES (1,'applications_list',0),(3,'applications_show',0),(5,'applications_edit',0),(7,'applications_version',0),(9,'pupils_list',0),(11,'ivis_footer',0),(13,'applications_xml_import',0),(15,'ivis_header',0),(17,'demoold',0),(19,'pupils_edit',0),(21,'demo',0),(23,'OLD_applications_edit',0),(25,'imageArchive',0);
/*!40000 ALTER TABLE `template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `templategroups`
--

DROP TABLE IF EXISTS `templategroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `templategroups` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `UQ_templategroups__group_name` (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `templategroups`
--

LOCK TABLES `templategroups` WRITE;
/*!40000 ALTER TABLE `templategroups` DISABLE KEYS */;
INSERT INTO `templategroups` VALUES (0,'normal');
/*!40000 ALTER TABLE `templategroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `templates_cref`
--

DROP TABLE IF EXISTS `templates_cref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `templates_cref` (
  `group_id` int(11) NOT NULL,
  `template_name` varchar(255) NOT NULL,
  PRIMARY KEY (`group_id`,`template_name`),
  CONSTRAINT `templates_cref_FK_group_id_templategroups` FOREIGN KEY (`group_id`) REFERENCES `templategroups` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `templates_cref`
--

LOCK TABLES `templates_cref` WRITE;
/*!40000 ALTER TABLE `templates_cref` DISABLE KEYS */;
INSERT INTO `templates_cref` VALUES (0,'demo');
/*!40000 ALTER TABLE `templates_cref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `text_docs`
--

DROP TABLE IF EXISTS `text_docs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `text_docs` (
  `meta_id` int(11) NOT NULL,
  `template_name` varchar(255) NOT NULL,
  `group_id` int(11) NOT NULL DEFAULT '1',
  `default_template_1` varchar(255) DEFAULT NULL,
  `default_template_2` varchar(255) DEFAULT NULL,
  `default_template` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`meta_id`),
  CONSTRAINT `text_docs_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `text_docs`
--

LOCK TABLES `text_docs` WRITE;
/*!40000 ALTER TABLE `text_docs` DISABLE KEYS */;
INSERT INTO `text_docs` VALUES (1001,'applications_list',0,NULL,NULL,'demo'),(1003,'import',0,NULL,NULL,'import'),(1005,'import',0,NULL,NULL,'import'),(1007,'pupils_list',0,NULL,NULL,'demo'),(1009,'pupils_edit',0,NULL,NULL,'demo'),(1011,'applications_edit',0,NULL,NULL,'demo'),(1013,'applications_show',0,NULL,NULL,'applications_show'),(1014,'applications_version',0,NULL,NULL,'applications_version');
/*!40000 ALTER TABLE `text_docs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `texts`
--

DROP TABLE IF EXISTS `texts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `texts` (
  `meta_id` int(11) NOT NULL,
  `name` int(11) NOT NULL,
  `text` longtext NOT NULL,
  `type` int(11) DEFAULT NULL,
  `counter` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`counter`),
  KEY `texts_FK_meta_id_meta` (`meta_id`),
  CONSTRAINT `texts_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `texts`
--

LOCK TABLES `texts` WRITE;
/*!40000 ALTER TABLE `texts` DISABLE KEYS */;
INSERT INTO `texts` VALUES (1001,1,'<h3>Welcome to imCMS</h3>',1,1),(1001,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,2);
/*!40000 ALTER TABLE `texts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `texts_history`
--

DROP TABLE IF EXISTS `texts_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `texts_history` (
  `meta_id` int(11) NOT NULL,
  `name` int(11) NOT NULL,
  `text` longtext NOT NULL,
  `type` int(11) DEFAULT NULL,
  `modified_datetime` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `counter` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`counter`),
  KEY `texts_history_FK_meta_id_meta` (`meta_id`),
  KEY `texts_history_FK_user_id_users` (`user_id`),
  CONSTRAINT `texts_history_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`),
  CONSTRAINT `texts_history_FK_user_id_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `texts_history`
--

LOCK TABLES `texts_history` WRITE;
/*!40000 ALTER TABLE `texts_history` DISABLE KEYS */;
INSERT INTO `texts_history` VALUES (1001,1,'<h3>Welcome to imCMS</h3>',1,'2010-04-07 16:25:53',1,1),(1001,2,'<a href=\"<?imcms:contextpath?>/login/\" title=\"Link to login-page\"><b>Log in!</b></a><br><br><br><a href=\"<?imcms:contextpath?>/servlet/SearchDocuments\" target=\"_blank\" title=\"Link to search-page\"><b>Search-page</b></a><br>Opens in new window.<br><br><a href=\"http://doc.imcms.net//\" target=\"_blank\" title=\"Link to documentation-site\"><b>Documentation</b></a><br>External site, opens in new window.<br><br><a href=\"http://www.imcms.net\" target=\"_blank\" title=\"Link to www.imcms.net\"><b>More about imCMS</b></a><br>imCMS Product-site. External site, opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/\" target=\"_blank\" title=\"Link to included documentation.\"><b>Included documentation.</b></a><br>For administrators and developers, in english. Opens in new window.<br><br><a href=\"<?imcms:contextpath?>/imcms/docs/apisamples/\" target=\"_blank\" title=\"Link to API-samples\"><b>API examples</b></a><br>Only for developers, in english. Opens in new window.<br>Note! Only to be used in test environment - not sites for public use.',1,'2010-04-07 16:25:53',1,2);
/*!40000 ALTER TABLE `texts_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `url_docs`
--

DROP TABLE IF EXISTS `url_docs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `url_docs` (
  `meta_id` int(11) NOT NULL,
  `frame_name` varchar(80) NOT NULL,
  `target` varchar(15) NOT NULL,
  `url_ref` varchar(255) NOT NULL,
  `url_txt` varchar(255) NOT NULL,
  `lang_prefix` varchar(3) NOT NULL,
  PRIMARY KEY (`meta_id`,`lang_prefix`),
  CONSTRAINT `url_docs_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `url_docs`
--

LOCK TABLES `url_docs` WRITE;
/*!40000 ALTER TABLE `url_docs` DISABLE KEYS */;
/*!40000 ALTER TABLE `url_docs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_rights`
--

DROP TABLE IF EXISTS `user_rights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_rights` (
  `user_id` int(11) NOT NULL,
  `meta_id` int(11) NOT NULL,
  `permission_id` smallint(6) NOT NULL,
  PRIMARY KEY (`user_id`,`meta_id`,`permission_id`),
  KEY `user_rights_FK_meta_id_meta` (`meta_id`),
  CONSTRAINT `user_rights_FK_meta_id_meta` FOREIGN KEY (`meta_id`) REFERENCES `meta` (`meta_id`),
  CONSTRAINT `user_rights_FK_user_id_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_rights`
--

LOCK TABLES `user_rights` WRITE;
/*!40000 ALTER TABLE `user_rights` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_rights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles_crossref`
--

DROP TABLE IF EXISTS `user_roles_crossref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles_crossref` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `user_roles_crossref_FK_role_id_roles` (`role_id`),
  CONSTRAINT `user_roles_crossref_FK_role_id_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `user_roles_crossref_FK_user_id_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles_crossref`
--

LOCK TABLES `user_roles_crossref` WRITE;
/*!40000 ALTER TABLE `user_roles_crossref` DISABLE KEYS */;
INSERT INTO `user_roles_crossref` VALUES (1,0),(2,2);
/*!40000 ALTER TABLE `user_roles_crossref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useradmin_role_crossref`
--

DROP TABLE IF EXISTS `useradmin_role_crossref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useradmin_role_crossref` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `useradmin_role_crossref_FK_role_id_roles` (`role_id`),
  CONSTRAINT `useradmin_role_crossref_FK_role_id_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `useradmin_role_crossref_FK_user_id_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useradmin_role_crossref`
--

LOCK TABLES `useradmin_role_crossref` WRITE;
/*!40000 ALTER TABLE `useradmin_role_crossref` DISABLE KEYS */;
/*!40000 ALTER TABLE `useradmin_role_crossref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(128) NOT NULL,
  `login_password` varchar(128) NOT NULL,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `title` varchar(64) NOT NULL,
  `company` varchar(64) NOT NULL,
  `address` varchar(128) NOT NULL,
  `city` varchar(64) NOT NULL,
  `zip` varchar(64) NOT NULL,
  `country` varchar(64) NOT NULL,
  `county_council` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `external` int(11) NOT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  `create_date` datetime NOT NULL,
  `language` varchar(3) NOT NULL,
  `session_id` varchar(128) DEFAULT NULL,
  `remember_cd` varchar(40) DEFAULT NULL,
  `login_password_is_encrypted` tinyint(1) DEFAULT '0',
  `login_password_reset_id` varchar(36) DEFAULT NULL,
  `login_password_reset_ts` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UQ__users__login_name` (`login_name`),
  UNIQUE KEY `ux__users__email` (`email`),
  UNIQUE KEY `users_remember_cd_unq` (`remember_cd`),
  UNIQUE KEY `ux__users__login_password_reset_id` (`login_password_reset_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','Admin','Super','','','','','','','','#1',0,1,'2010-04-07 16:25:53','eng',NULL,NULL,0,NULL,NULL),(2,'user','user','User','Extern','','','','','','','','#2',0,1,'2010-04-07 16:25:53','eng',NULL,NULL,0,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `imcms_text_doc_contents_v`
--

/*!50001 DROP TABLE IF EXISTS `imcms_text_doc_contents_v`*/;
/*!50001 DROP VIEW IF EXISTS `imcms_text_doc_contents_v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`iVIS-imCMS_dev`@`172.16.97.%` SQL SECURITY DEFINER */
/*!50001 VIEW `imcms_text_doc_contents_v` AS select `l`.`doc_id` AS `doc_id`,`l`.`doc_version_no` AS `doc_version_no`,`l`.`no` AS `loop_no`,`c`.`no` AS `content_no`,`c`.`ix` AS `content_ix`,`c`.`enabled` AS `content_enabled` from (`imcms_text_doc_content_loops` `l` join `imcms_text_doc_contents` `c` on((`l`.`id` = `c`.`loop_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-10  9:37:05
