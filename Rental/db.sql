/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 8.0.22 : Database - rental_app
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rental_app` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `rental_app`;

/*Table structure for table `auth_group` */

DROP TABLE IF EXISTS `auth_group`;

CREATE TABLE `auth_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_group` */

/*Table structure for table `auth_group_permissions` */

DROP TABLE IF EXISTS `auth_group_permissions`;

CREATE TABLE `auth_group_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_group_permissions` */

/*Table structure for table `auth_permission` */

DROP TABLE IF EXISTS `auth_permission`;

CREATE TABLE `auth_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_permission` */

insert  into `auth_permission`(`id`,`name`,`content_type_id`,`codename`) values 
(1,'Can add login',1,'add_login'),
(2,'Can change login',1,'change_login'),
(3,'Can delete login',1,'delete_login'),
(4,'Can view login',1,'view_login'),
(5,'Can add product',2,'add_product'),
(6,'Can change product',2,'change_product'),
(7,'Can delete product',2,'delete_product'),
(8,'Can view product',2,'view_product'),
(9,'Can add user',3,'add_user'),
(10,'Can change user',3,'change_user'),
(11,'Can delete user',3,'delete_user'),
(12,'Can view user',3,'view_user'),
(13,'Can add request',4,'add_request'),
(14,'Can change request',4,'change_request'),
(15,'Can delete request',4,'delete_request'),
(16,'Can view request',4,'view_request'),
(17,'Can add complaint',5,'add_complaint'),
(18,'Can change complaint',5,'change_complaint'),
(19,'Can delete complaint',5,'delete_complaint'),
(20,'Can view complaint',5,'view_complaint'),
(21,'Can add chat',6,'add_chat'),
(22,'Can change chat',6,'change_chat'),
(23,'Can delete chat',6,'delete_chat'),
(24,'Can view chat',6,'view_chat'),
(25,'Can add log entry',7,'add_logentry'),
(26,'Can change log entry',7,'change_logentry'),
(27,'Can delete log entry',7,'delete_logentry'),
(28,'Can view log entry',7,'view_logentry'),
(29,'Can add permission',8,'add_permission'),
(30,'Can change permission',8,'change_permission'),
(31,'Can delete permission',8,'delete_permission'),
(32,'Can view permission',8,'view_permission'),
(33,'Can add group',9,'add_group'),
(34,'Can change group',9,'change_group'),
(35,'Can delete group',9,'delete_group'),
(36,'Can view group',9,'view_group'),
(37,'Can add user',10,'add_user'),
(38,'Can change user',10,'change_user'),
(39,'Can delete user',10,'delete_user'),
(40,'Can view user',10,'view_user'),
(41,'Can add content type',11,'add_contenttype'),
(42,'Can change content type',11,'change_contenttype'),
(43,'Can delete content type',11,'delete_contenttype'),
(44,'Can view content type',11,'view_contenttype'),
(45,'Can add session',12,'add_session'),
(46,'Can change session',12,'change_session'),
(47,'Can delete session',12,'delete_session'),
(48,'Can view session',12,'view_session');

/*Table structure for table `auth_user` */

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user` */

insert  into `auth_user`(`id`,`password`,`last_login`,`is_superuser`,`username`,`first_name`,`last_name`,`email`,`is_staff`,`is_active`,`date_joined`) values 
(1,'pbkdf2_sha256$260000$occid4IYiXZzf1YQpGPvnk$t1TYrWKw7J+H4nnq96gM6UUrc0uDh5YTMRsZ3YK5L2o=','2023-10-25 07:07:34.143404',1,'admin','','','admin@gmail.com',1,1,'2023-10-25 06:25:06.725213');

/*Table structure for table `auth_user_groups` */

DROP TABLE IF EXISTS `auth_user_groups`;

CREATE TABLE `auth_user_groups` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`),
  CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user_groups` */

/*Table structure for table `auth_user_user_permissions` */

DROP TABLE IF EXISTS `auth_user_user_permissions`;

CREATE TABLE `auth_user_user_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user_user_permissions` */

/*Table structure for table `django_admin_log` */

DROP TABLE IF EXISTS `django_admin_log`;

CREATE TABLE `django_admin_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `django_admin_log_chk_1` CHECK ((`action_flag` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_admin_log` */

/*Table structure for table `django_content_type` */

DROP TABLE IF EXISTS `django_content_type`;

CREATE TABLE `django_content_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_content_type` */

insert  into `django_content_type`(`id`,`app_label`,`model`) values 
(7,'admin','logentry'),
(9,'auth','group'),
(8,'auth','permission'),
(10,'auth','user'),
(11,'contenttypes','contenttype'),
(6,'RentalApp','chat'),
(5,'RentalApp','complaint'),
(1,'RentalApp','login'),
(2,'RentalApp','product'),
(4,'RentalApp','request'),
(3,'RentalApp','user'),
(12,'sessions','session');

/*Table structure for table `django_migrations` */

DROP TABLE IF EXISTS `django_migrations`;

CREATE TABLE `django_migrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_migrations` */

insert  into `django_migrations`(`id`,`app`,`name`,`applied`) values 
(1,'RentalApp','0001_initial','2023-09-02 10:40:09.511452'),
(2,'RentalApp','0002_user_lid','2023-09-02 10:40:21.261429'),
(3,'contenttypes','0001_initial','2023-09-02 10:40:24.136422'),
(4,'auth','0001_initial','2023-09-02 10:41:26.950232'),
(5,'admin','0001_initial','2023-09-02 10:41:42.418951'),
(6,'admin','0002_logentry_remove_auto_add','2023-09-02 10:41:42.747078'),
(7,'admin','0003_logentry_add_action_flag_choices','2023-09-02 10:41:43.028294'),
(8,'contenttypes','0002_remove_content_type_name','2023-09-02 10:41:50.731423'),
(9,'auth','0002_alter_permission_name_max_length','2023-09-02 10:41:56.981393'),
(10,'auth','0003_alter_user_email_max_length','2023-09-02 10:41:57.559516'),
(11,'auth','0004_alter_user_username_opts','2023-09-02 10:41:57.637637'),
(12,'auth','0005_alter_user_last_login_null','2023-09-02 10:42:06.168888'),
(13,'auth','0006_require_contenttypes_0002','2023-09-02 10:42:06.731403'),
(14,'auth','0007_alter_validators_add_error_messages','2023-09-02 10:42:07.121997'),
(15,'auth','0008_alter_user_username_max_length','2023-09-02 10:42:13.512609'),
(16,'auth','0009_alter_user_last_name_max_length','2023-09-02 10:42:20.059506'),
(17,'auth','0010_alter_group_name_max_length','2023-09-02 10:42:21.559502'),
(18,'auth','0011_update_proxy_permissions','2023-09-02 10:42:21.903227'),
(19,'auth','0012_alter_user_first_name_max_length','2023-09-02 10:42:26.418864'),
(20,'sessions','0001_initial','2023-09-02 10:42:33.012602'),
(21,'RentalApp','0003_alter_user_proof','2023-09-11 04:03:21.323278'),
(22,'RentalApp','0004_rename_chat_chat_chat1','2023-10-25 05:38:22.844630'),
(23,'RentalApp','0005_rename_complaint_complaint_complaint1','2023-10-25 07:02:45.992833'),
(24,'RentalApp','0006_alter_request_returndate','2023-10-25 08:35:40.297809'),
(25,'RentalApp','0007_alter_request_returndate','2023-10-25 08:37:58.962602');

/*Table structure for table `django_session` */

DROP TABLE IF EXISTS `django_session`;

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_session` */

insert  into `django_session`(`session_key`,`session_data`,`expire_date`) values 
('083g6t3gjpkupmpqftuu8d2mlr7s2czk','eyJ1c2VyX2lkIjoyfQ:1qvVRo:iGOdlf8c3IboDXlVyrsoI3Zz8p4XRijiuxeZJVSCse0','2023-11-08 04:24:48.124892'),
('0m1ssadl7bkmbwllfkrfnv4tjd0g0840','.eJxVjEsOgkAQRO_SazJJMyMjLt17BtJfQQ0kDKyMdzcoG3aVV6_qDdQNChesYP6FXEFH69J3a7E_AYQDY5KnjVuhDxrvU5BpXOaBw6aEvS3hNqm9rrt7OOip9NuaPXkUzxGztRTPFF3JNGUXw8YdxbgWklPr7KRJG0zMMUqNKZrD5wuxDz7w:1qvYn1:c7FeMl44mZOQDoeWkt21_7hXa_hTfX2HJn9ewASbYgI','2023-11-08 07:58:55.395530'),
('1iggnprorfydts8cndi45y0b4kor0d7s','eyJ1c2VyX2lkIjoyfQ:1qvVVi:MCvcaPkAjS4TFQMb17Nq-mV3AcTU7vB_D0moiK6_gq8','2023-11-08 04:28:50.828634'),
('1mwfo0tjx86d0qd2i2rdl0wjppe0vbo4','eyJ1c2VyX2lkIjoyfQ:1qvWtd:9HkhEQ8VoiH-YwiP9a1gs3g3wugDiRDTcRl1dyu_FVU','2023-11-08 05:57:37.249311'),
('275jdc4on65vl958ngpe3mg2mx1ncch9','eyJ1c2VyX2lkIjoyfQ:1qvEhZ:VEMpqf5_bwK7B-ltpueMtyvEirMjoV1J59EZt77l8pw','2023-11-07 10:31:57.105455'),
('37otv3w480u7s6v7iqdtnp18mx6rc28l','eyJ1c2VyX2lkIjoyfQ:1qvcLd:gYU8EQ2ABrbY_TpRB_QxzoDHqlqkp4B0hW3Xiis1GVE','2023-11-08 11:46:53.694257'),
('89ywtsnmlz76a7qhr3ikx3nqznjhrkcy','eyJ1c2VyX2lkIjo0fQ:1qvYJI:IIhGnF4cCDFKIV2eYVq1byPm8sCkQPO1y8tRt5ddfbQ','2023-11-08 07:28:12.695658'),
('8vrd7qfrlnr7dgb52hmonzbl5nqnmp0l','eyJ1c2VyX2lkIjo0fQ:1qvb8S:iDeQSw4d6kBDEsHncZxvpobenB7V_lqGAwlqSAeYjnM','2023-11-08 10:29:12.910901'),
('afnyjbg85a8r53u6fa267nyq3odyashy','eyJ1c2VyX2lkIjo0fQ:1qvXAO:pi-UIdbCZkhajTugO0tc-aHmLNFi1JkTwNVb74-J3KY','2023-11-08 06:14:56.649813'),
('d76xincgmqomohndo25pae5jdt1svc0o','eyJ1c2VyX2lkIjoyfQ:1qffOF:knQWyrF00nT8UcKeuEjKqGx3IYWep_Or5TgTJgyTdWM','2023-09-25 11:47:39.218805'),
('d7pn9qwapcazzgciu6hbtcvkkvntrf8x','eyJ1c2VyX2lkIjo0fQ:1qvbTZ:GhlJuY1HRhk_1cKLQU_3x11VPakHbX-et1JUmjznND0','2023-11-08 10:51:01.683120'),
('dofc0sb5qiyc2q0vmdiknf0bjckkw8ez','eyJ1c2VyX2lkIjo0fQ:1qvYJP:0gCKiT6XR-94HbgDpxVuuLLFCqSwUI_4_lUh3eOh37I','2023-11-08 07:28:19.578942'),
('e66iwccnnrcdbpikl8s1no2ajlic5v3d','.eJxVjjsOwjAQRO_iGlnarIkJJT1niPZLAsiR8qki7g5BKaCdN_M0a2hpmbt2mWxsew3nAOHwmzHJw8oG9E7lNkQZyjz2HLdK3OkUr4Pa87J3_wQdTd22Zk-O4hkhW0N4InQl05RdDGp3EONKSI6Ns5MmrSExI0oFCc0_UvoehNcbSx08cQ:1qvXxr:8jE7gw1dzJuBt3JkK0hP4_Ld3DJu_A_ztYV7DvOsS9o','2023-11-08 07:06:03.592307'),
('e9fin9th8c6houlxohd62ga157jgfvcg','eyJ1c2VyX2lkIjoyfQ:1qvVXd:st-iDpFxPdSso9f3HLIhRn3LQbKj47R6Mg7xct2is40','2023-11-08 04:30:49.705682'),
('hoijk9k4yda0a68hqv2qc5vcbvddg45v','eyJ1c2VyX2lkIjoyfQ:1qvYIO:S1hjBkbIO2eC2Ok7Prbjfpbd8RBB6ARaIFggjhrNP3g','2023-11-08 07:27:16.873502'),
('izqvywl1ucub0gztlaeshof9t93eyqai','eyJ1c2VyX2lkIjo0fQ:1qvYDv:OPChXEPGHHuGYiW99Cv45WnJtg3vWHwjG83WLxCfL2A','2023-11-08 07:22:39.814192'),
('izwby6qxbu4bpvlv0j3kdqfvibay3l6g','eyJ1c2VyX2lkIjo0fQ:1qvZ2Q:ujEr_im3wnexPXXkSmsDK1ymWksN7Y3lvTWJB2uKNtM','2023-11-08 08:14:50.085830'),
('jd9smgenmq7jf88x1j89n1du4boudyho','eyJ1c2VyX2lkIjo2fQ:1qvYQN:BBuQ0-MDe176t0SMegimJkTIk06qFISumTN97bywx8I','2023-11-08 07:35:31.812818'),
('k6l2pgxd18hud4av79lbmw6jd4ppf6kn','eyJ1c2VyX2lkIjo2fQ:1qvYoR:ESgjC5xTav0_6VFKYXXGkhV-c66hAJ94s1smZadY9rs','2023-11-08 08:00:23.536486'),
('l8gjncdcwqqcypkhtbdvlvcwefzm67oc','eyJ1c2VyX2lkIjoyfQ:1qvEfe:wYI4ZUvDQ5aeSME827XZzCnNPRDQ-ffB1fy__aPhEPE','2023-11-07 10:29:58.663871'),
('mtzb2sdcq7egskbxy1aqivizwj48fk7t','eyJ1c2VyX2lkIjoyfQ:1qffMe:eTz8POwUU4DyQ59stjmNOrlNbGYVSIIUXcKIk6J30sA','2023-09-25 11:46:00.219428'),
('pme2hljmyw15ug9jqj7z1zb9uyu7y1qd','eyJ1c2VyX2lkIjo0fQ:1qvblP:iVN0GdCve2qYIZFGuLVnTs63Xkm8YSjODuIU9JxvuBo','2023-11-08 11:09:27.193310'),
('px1h15mgdrdna75ybvh6xcphfl7ekubi','eyJ1c2VyX2lkIjoyfQ:1qvcAS:gT17owksoxSeGywPzB29hE-2W_0zEr5rEClMi5XRUTs','2023-11-08 11:35:20.918265'),
('qb6uqgak64nluqtoagyu3uxfrrapi23v','eyJ1c2VyX2lkIjoyfQ:1qvVUl:1AbSstQXObviQRW_CLC87cqG569reZ-igGKKPAON5lc','2023-11-08 04:27:51.522401'),
('r70ocebohzm3pq6g4xhssd1958i9pjgc','eyJ1c2VyX2lkIjoyfQ:1qvVNj:PaefrQKLDn76pFg1AntKBXRKYEs7pF_5QZl4lvIYwFo','2023-11-08 04:20:35.755464'),
('rsrxdcn793h0gwmiwrt2tyt6epgmbveg','eyJ1c2VyX2lkIjoyfQ:1qfeNS:RSi2BRZfquZurBEQ5xhba44JhbgIYmSOg7mx9IbfCCA','2023-09-25 10:42:46.333290'),
('t0n7br1eeh87ybjxyw0nbyofcyresk84','eyJ1c2VyX2lkIjo0fQ:1qvYE4:yl_h2ClflJoOeiq9Y36VVgcEPBlZlNTbkmeioGBVpaE','2023-11-08 07:22:48.606180'),
('uoucz3tmbzqze8kjl1dd93og8bciec32','eyJ1c2VyX2lkIjo0fQ:1qvZJd:QX1fbl2NXqJV-V0yVmMSegrtOPfeQsWXoSm_kD4SIDM','2023-11-08 08:32:37.095588'),
('wk7m8eeqnhn2p5er64zyc0uymk2irstm','eyJ1c2VyX2lkIjo0fQ:1qvan4:fXC1NaSQoYDBTfitPM9HfthMuJC6LuF5UuJe_sp2IDg','2023-11-08 10:07:06.458617'),
('x3fy5uykkxt7p0t8wvqgjelito22huq6','eyJ1c2VyX2lkIjo0fQ:1qvXHx:28yOocBH2oIq0_3p5uFom6LOViO-3t89hfr4gbK4HdE','2023-11-08 06:22:45.957610'),
('zhwhb6xiufpq5k4iciux4w3kjpbjnvxe','eyJ1c2VyX2lkIjoyfQ:1qvahE:4Qds_a66J1vAjHEbFb4mOO7-yjQirfski3bpgtfFw8g','2023-11-08 10:01:04.026285');

/*Table structure for table `rentalapp_chat` */

DROP TABLE IF EXISTS `rentalapp_chat`;

CREATE TABLE `rentalapp_chat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Chat1` varchar(300) NOT NULL,
  `Date` varchar(20) NOT NULL,
  `Time` varchar(20) NOT NULL,
  `From_id_id` bigint NOT NULL,
  `To_id_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `RentalApp_chat_From_id_id_e89eafcf_fk_RentalApp_user_id` (`From_id_id`),
  KEY `RentalApp_chat_To_id_id_b6be0eb5_fk_RentalApp_user_id` (`To_id_id`),
  CONSTRAINT `RentalApp_chat_From_id_id_e89eafcf_fk_RentalApp_user_id` FOREIGN KEY (`From_id_id`) REFERENCES `rentalapp_user` (`id`),
  CONSTRAINT `RentalApp_chat_To_id_id_b6be0eb5_fk_RentalApp_user_id` FOREIGN KEY (`To_id_id`) REFERENCES `rentalapp_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `rentalapp_chat` */

insert  into `rentalapp_chat`(`id`,`Chat1`,`Date`,`Time`,`From_id_id`,`To_id_id`) values 
(1,'hi','25-10-23','10:19:55',1,3),
(2,'hello','25-10-23','10:20:19',1,3),
(3,'hi','25-10-23','10:31:21',1,4),
(4,'hi','25-10-23','10:33:53',1,3),
(5,'hii','25-10-23','10:40:42',1,4),
(6,'hello ','25-10-23','10:40:46',1,4),
(7,'hi','25-10-23','10:50:40',1,3),
(8,'hhh','25-10-23','10:57:47',1,3),
(9,'hi','25-10-23','11:25:32',1,3),
(10,'k','25-10-23','11:26:40',1,3),
(11,'ff','25-10-23','11:26:55',1,4),
(12,'tt','25-10-23','11:27:02',1,1),
(13,'u','25-10-23','11:53:02',3,3),
(14,'hi','25-10-23','11:53:09',3,3),
(15,'hello','25-10-23','11:53:17',3,4),
(16,'hello','25-10-23','11:53:32',3,1),
(17,'fff','25-10-23','12:54:28',4,3);

/*Table structure for table `rentalapp_complaint` */

DROP TABLE IF EXISTS `rentalapp_complaint`;

CREATE TABLE `rentalapp_complaint` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Complaint1` varchar(300) NOT NULL,
  `Date` varchar(20) NOT NULL,
  `Reply` varchar(300) NOT NULL,
  `UID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `RentalApp_complaint_UID_id_513e14a5_fk_RentalApp_user_id` (`UID_id`),
  CONSTRAINT `RentalApp_complaint_UID_id_513e14a5_fk_RentalApp_user_id` FOREIGN KEY (`UID_id`) REFERENCES `rentalapp_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `rentalapp_complaint` */

insert  into `rentalapp_complaint`(`id`,`Complaint1`,`Date`,`Reply`,`UID_id`) values 
(6,'','25/10/23','hghg',3),
(7,'','25/10/23','cfghjkl',3),
(8,'my cim','25/10/23','waiting',5),
(9,'jjjj','25/10/23','waiting',5),
(10,'hhcfghh','25/10/23','waiting',1);

/*Table structure for table `rentalapp_login` */

DROP TABLE IF EXISTS `rentalapp_login`;

CREATE TABLE `rentalapp_login` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `rentalapp_login` */

insert  into `rentalapp_login`(`id`,`Username`,`Password`,`Type`) values 
(1,'mru','123','admin'),
(2,'kt','kt','user'),
(4,'mrudu','mru','user'),
(5,'naveen','naveen','pending'),
(6,'dil','dil','user');

/*Table structure for table `rentalapp_product` */

DROP TABLE IF EXISTS `rentalapp_product`;

CREATE TABLE `rentalapp_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Product_name` varchar(100) NOT NULL,
  `Product_type` varchar(100) NOT NULL,
  `Details` varchar(100) NOT NULL,
  `Price_per_day` varchar(50) NOT NULL,
  `image` varchar(100) NOT NULL,
  `UID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `RentalApp_product_UID_id_cbf0caa2_fk_RentalApp_user_id` (`UID_id`),
  CONSTRAINT `RentalApp_product_UID_id_cbf0caa2_fk_RentalApp_user_id` FOREIGN KEY (`UID_id`) REFERENCES `rentalapp_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `rentalapp_product` */

insert  into `rentalapp_product`(`id`,`Product_name`,`Product_type`,`Details`,`Price_per_day`,`image`,`UID_id`) values 
(2,'Driller1','Electrical','230v/50Hz','150','Screenshot_2023-09-06-08-32-05-53_6012fa4d4ddec268fc5c7112cbb265e7_8J5hKao.jpg',1),
(3,'Cutter','Electronical ','230v','100','IMG20230907091358.jpg',4),
(5,'hand drill','hardware','12v','50','Screenshot_2023-08-05-15-25-15-61_4336b74596784d9a2aa81f87c2016f50.jpg',3),
(7,'gfg','ghg','yhbd','123','IMG20231025093335.jpg',3);

/*Table structure for table `rentalapp_request` */

DROP TABLE IF EXISTS `rentalapp_request`;

CREATE TABLE `rentalapp_request` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Date` varchar(20) NOT NULL,
  `Status` varchar(100) NOT NULL,
  `ReturnDate` char(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `PID_id` bigint NOT NULL,
  `UID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `RentalApp_request_PID_id_b3df6bb5_fk_RentalApp_product_id` (`PID_id`),
  KEY `RentalApp_request_UID_id_a171f870_fk_RentalApp_user_id` (`UID_id`),
  CONSTRAINT `RentalApp_request_PID_id_b3df6bb5_fk_RentalApp_product_id` FOREIGN KEY (`PID_id`) REFERENCES `rentalapp_product` (`id`),
  CONSTRAINT `RentalApp_request_UID_id_a171f870_fk_RentalApp_user_id` FOREIGN KEY (`UID_id`) REFERENCES `rentalapp_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `rentalapp_request` */

insert  into `rentalapp_request`(`id`,`Date`,`Status`,`ReturnDate`,`PID_id`,`UID_id`) values 
(17,'25/10/23','Pending','waiting...',3,1),
(18,'25/10/23','accept','2023-10-27',5,1),
(19,'25/10/23','Pending','waiting...',5,1);

/*Table structure for table `rentalapp_user` */

DROP TABLE IF EXISTS `rentalapp_user`;

CREATE TABLE `rentalapp_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Firstname` varchar(50) NOT NULL,
  `Lastname` varchar(50) NOT NULL,
  `Place` varchar(50) NOT NULL,
  `Post` varchar(50) NOT NULL,
  `Pin` int NOT NULL,
  `Phone` bigint NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Proof` varchar(100) NOT NULL,
  `LID_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `RentalApp_user_LID_id_3b9b8ab0_fk_RentalApp_login_id` (`LID_id`),
  CONSTRAINT `RentalApp_user_LID_id_3b9b8ab0_fk_RentalApp_login_id` FOREIGN KEY (`LID_id`) REFERENCES `rentalapp_login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `rentalapp_user` */

insert  into `rentalapp_user`(`id`,`Firstname`,`Lastname`,`Place`,`Post`,`Pin`,`Phone`,`Email`,`Proof`,`LID_id`) values 
(1,'Said','hashim','Westhill','Kanakalaya',673611,8129068162,'kt@gmail.com','ktProof.jpg',2),
(3,'mrudul','krish','kakkodi','kizhakk',673611,8129068160,'m@gmail.com','Screenshot_2023-09-06-08-32-05-53_6012fa4d4ddec268fc5c7112cbb265e7.jpg',4),
(4,'naveen','john','kkm','kkm',123456,9876543210,'naveen@gmaol.com','IMG20230910164300.jpg',5),
(5,'Mrudul','Krishna','kakkodi','kakkodi ',673411,8129068160,'di@gmail.com','Screenshot_2023-10-15-13-35-21-99_4336b74596784d9a2aa81f87c2016f50.jpg',6);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
