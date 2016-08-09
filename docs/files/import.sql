--
-- Dumping data for table `dbo_person`
--

LOCK TABLES `dbo_person` WRITE;
INSERT INTO `dbo_person` VALUES (1, 'ADMIN', 'ADMIN', 'ADMIN-ID');
UNLOCK TABLES;

--
-- Dumping data for table `dbo_user`
--

LOCK TABLES `dbo_user` WRITE;
INSERT INTO `dbo_user` VALUES (1,'admin',1,'$2a$04$Iu27qEkQCvr3K8RS3GmAEeRNe91h6PnZ5OAQqRexJ/gLwzbu7emX2',NULL,1);
UNLOCK TABLES;

--
-- Dumping data for table `dbo_role`
--

LOCK TABLES `dbo_role` WRITE;
INSERT INTO `dbo_role` VALUES (1, 'ROLE_ADMIN'),(2, 'ROLE_USER'),(3, 'ROLE_DEVELOPER'),(4, 'ROLE_GUARDIAN'),(5, 'ROLE_STUDENT'),(6, 'ROLE_ANONYMOUS');
UNLOCK TABLES;

--
-- Dumping data for table `dbo_oauth_client_details`
--

LOCK TABLES `dbo_oauth_client_details` WRITE;
INSERT INTO `dbo_oauth_client_details` () VALUES ('ff11397c-3e3b-4398-80a9-feba203f1928',60000,'secret','admin',600000,1);
UNLOCK TABLES;

--
-- Dumping data for table `dbo_oauth_client_grant_types`
--

LOCK TABLES `dbo_oauth_client_grant_types` WRITE;
INSERT INTO `dbo_oauth_client_grant_types` VALUES ('ff11397c-3e3b-4398-80a9-feba203f1928','authorization_code'),('ff11397c-3e3b-4398-80a9-feba203f1928','implicit'),('ff11397c-3e3b-4398-80a9-feba203f1928','refresh_token'),('ff11397c-3e3b-4398-80a9-feba203f1928','client_credentials'),('ff11397c-3e3b-4398-80a9-feba203f1928','password');
UNLOCK TABLES;

--
-- Dumping data for table `dbo_oauth_client_redirect_uris`
--

LOCK TABLES `dbo_oauth_client_redirect_uris` WRITE;
INSERT INTO `dbo_oauth_client_redirect_uris` VALUES ('ff11397c-3e3b-4398-80a9-feba203f1928','http://ivis.dev.imcode.com');
UNLOCK TABLES;

--
-- Dumping data for table `dbo_oauth_client_resources`
--

LOCK TABLES `dbo_oauth_client_resources` WRITE;
INSERT INTO `dbo_oauth_client_resources` VALUES ('ff11397c-3e3b-4398-80a9-feba203f1928','ivis');
UNLOCK TABLES;

--
-- Dumping data for table `dbo_oauth_client_role`
--

LOCK TABLES `dbo_oauth_client_role` WRITE;;
INSERT INTO `dbo_oauth_client_role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
UNLOCK TABLES;

--
-- Dumping data for table `dbo_oauth_client_roles_cross`
--

LOCK TABLES `dbo_oauth_client_roles_cross` WRITE;
INSERT INTO `dbo_oauth_client_roles_cross` VALUES ('ff11397c-3e3b-4398-80a9-feba203f1928',1),('ff11397c-3e3b-4398-80a9-feba203f1928',2);
UNLOCK TABLES;

--
-- Dumping data for table `dbo_oauth_client_scope`
--

LOCK TABLES `dbo_oauth_client_scope` WRITE;
INSERT INTO `dbo_oauth_client_scope` VALUES ('ff11397c-3e3b-4398-80a9-feba203f1928','read'),('ff11397c-3e3b-4398-80a9-feba203f1928','write'),('ff11397c-3e3b-4398-80a9-feba203f1928','execute');
UNLOCK TABLES;

--
-- Dumping data for table `dbo_user_roles_cross`
--

LOCK TABLES `dbo_user_roles_cross` WRITE;

INSERT INTO `dbo_user_roles_cross` VALUES (1,1),(1,2),(1,3),(1,4),(1,5);

UNLOCK TABLES;

