USE `db_ivis`;
--
-- Dumping data for table `dbo_appTablesPermissions`
--

LOCK TABLES `dbo_appTablesPermissions` WRITE;
/*!40000 ALTER TABLE `dbo_appTablesPermissions` DISABLE KEYS */;
INSERT INTO `dbo_appTablesPermissions` VALUES (1,'dbo_role',1),(1,'dbo_user',7),(2,'dbo_user',1);
/*!40000 ALTER TABLE `dbo_appTablesPermissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dbo_application`
--

LOCK TABLES `dbo_application` WRITE;
/*!40000 ALTER TABLE `dbo_application` DISABLE KEYS */;
INSERT INTO `dbo_application` VALUES (1,'Android1'),(2,'Android2');
/*!40000 ALTER TABLE `dbo_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dbo_role`
--

LOCK TABLES `dbo_role` WRITE;
/*!40000 ALTER TABLE `dbo_role` DISABLE KEYS */;
INSERT INTO `dbo_role` VALUES (4,'Administrator'),(1,'Ananimus'),(3,'Guardian'),(6,'qwe'),(7,'qweeeee'),(2,'Student'),(5,'wer'),(8,'werwerwerq');
/*!40000 ALTER TABLE `dbo_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dbo_school`
--

LOCK TABLES `dbo_school` WRITE;
/*!40000 ALTER TABLE `dbo_school` DISABLE KEYS */;
INSERT INTO `dbo_school` VALUES (1,'School 1'),(2,'School 1'),(3,'School 2'),(4,'School 3'),(5,'School 9'),(6,'School 10'),(7,'My School'),(8,'School 9');
/*!40000 ALTER TABLE `dbo_school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dbo_schoolClass`
--

LOCK TABLES `dbo_schoolClass` WRITE;
/*!40000 ALTER TABLE `dbo_schoolClass` DISABLE KEYS */;
INSERT INTO `dbo_schoolClass` VALUES (1,'1A',1),(2,'1B',1),(3,'2B',1),(4,'1A',2),(5,'1B',2),(6,'1B',3),(7,'2B',3),(8,'3B',3);
/*!40000 ALTER TABLE `dbo_schoolClass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dbo_transport`
--

LOCK TABLES `dbo_transport` WRITE;
/*!40000 ALTER TABLE `dbo_transport` DISABLE KEYS */;
INSERT INTO `dbo_transport` VALUES (1,'School Bus #1',1),(2,'School Bus #2',1),(3,'School Bus #3',1),(4,'School Car #1',1),(5,'School Train #1',1);
/*!40000 ALTER TABLE `dbo_transport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dbo_transportType`
--

LOCK TABLES `dbo_transportType` WRITE;
/*!40000 ALTER TABLE `dbo_transportType` DISABLE KEYS */;
INSERT INTO `dbo_transportType` VALUES (1,'Bus'),(2,'Train'),(3,'Car');
/*!40000 ALTER TABLE `dbo_transportType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dbo_user`
--

LOCK TABLES `dbo_user` WRITE;
/*!40000 ALTER TABLE `dbo_user` DISABLE KEYS */;
INSERT INTO `dbo_user` VALUES (1,'1986-08-13 00:00:00','Kiev','tifoha@gmail.com','Vitalii','Sereda','v.sereda','+380971396134','0001','123','12, Polupanova str.',1,2),(2,'1986-08-13 00:00:00','Kiev','pupil@gmail.com','Student','','pupil','+380971396134','0001','123','12, Polupanova str.',1,1),(3,'1986-08-13 00:00:00','Kiev','guardian@gmail.com','Guardian','','guardian','+380971396134','0001','123','12, Polupanova str.',1,1),(4,'1986-08-13 00:00:00','Kiev','admin@gmail.com','Administrator','','admin','+380971396134','0001','123','12, Polupanova str.',NULL,NULL);
/*!40000 ALTER TABLE `dbo_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dbo_usersInRoles`
--

LOCK TABLES `dbo_usersInRoles` WRITE;
/*!40000 ALTER TABLE `dbo_usersInRoles` DISABLE KEYS */;
INSERT INTO `dbo_usersInRoles` VALUES (1,1),(1,2),(1,3),(1,4),(2,1),(2,2),(3,1),(3,3),(4,4);
/*!40000 ALTER TABLE `dbo_usersInRoles` ENABLE KEYS */;
UNLOCK TABLES;
