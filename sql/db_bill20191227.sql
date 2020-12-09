-- MySQL dump 10.13  Distrib 5.7.32, for Win64 (x86_64)
--
-- Host: localhost    Database: db_bill
-- ------------------------------------------------------
-- Server version	5.7.32-log

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
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(50) DEFAULT NULL COMMENT '系统账户',
  `password` varchar(200) DEFAULT NULL COMMENT '系统密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐值',
  `is_lock` int(255) DEFAULT NULL COMMENT '状态:1正常  0禁用',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','eec71ed7609af4d76588f8ce8c1f08deaf14e631340ee78cb32905672a87faad','Q.SGK66842ASLPZQBFSAKLZUI',1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_bill`
--

DROP TABLE IF EXISTS `tbl_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_bill` (
  `bid` int(10) NOT NULL AUTO_INCREMENT,
  `bill_code` varchar(25) DEFAULT NULL,
  `bill_name` varchar(25) DEFAULT NULL,
  `bill_com` int(25) DEFAULT NULL COMMENT '续费,0：年，1：月，2：日，3：时',
  `bill_num` int(8) DEFAULT NULL,
  `money` double(8,2) DEFAULT NULL,
  `pay` int(2) DEFAULT NULL COMMENT '是否付款 0 未付款， 1已付款',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_bill`
--

LOCK TABLES `tbl_bill` WRITE;
/*!40000 ALTER TABLE `tbl_bill` DISABLE KEYS */;
INSERT INTO `tbl_bill` VALUES (1,'B_001','ESC包年云服务',0,39,400000.00,1,'2018-11-17 15:22:03','2019-12-27 12:00:14'),(2,'b_002','ESC包月云服务',1,20,26000.00,1,'2028-11-12 11:58:00','2019-12-27 12:00:17'),(3,'b_003','com域名',0,33,15000.00,0,'2028-11-12 11:58:00','2019-12-27 12:00:19'),(4,'b_004','cn域名',3,10,7000.00,1,'2028-11-12 11:58:00','2019-12-27 12:00:20'),(5,'b_005','团购小程序',0,150,60000.00,0,'2028-11-12 11:58:00','2019-12-27 12:00:37'),(8,'b_006','ESC包日',3,225,3000.00,1,'2019-12-27 11:07:26','2019-12-27 12:00:32'),(9,'b_006','阿里云git版',1,150,1500.00,0,'2019-12-27 11:09:48','2019-12-27 12:00:48'),(10,'b_007','腾讯云ESC',1,60,5000.00,1,'2019-12-27 11:11:03','2019-12-27 12:00:56'),(11,'b_008','华为云ESC',1,1,3000.00,0,'2019-12-27 12:05:58','2019-12-27 14:44:31'),(12,'b_009','华为云ESC',2,600,5000.00,1,'2019-12-27 14:40:18','2019-12-27 14:43:35');
/*!40000 ALTER TABLE `tbl_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_customer`
--

DROP TABLE IF EXISTS `tbl_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) DEFAULT NULL COMMENT '客户名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `gender` int(2) DEFAULT NULL COMMENT '性别，1：男  0：女',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `state` int(2) DEFAULT NULL COMMENT '状态，1：已激活  0：未激活',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_customer`
--

LOCK TABLES `tbl_customer` WRITE;
/*!40000 ALTER TABLE `tbl_customer` DISABLE KEYS */;
INSERT INTO `tbl_customer` VALUES (1,'罗涛',62,1,'l.awld@gotfag.gm','2019-12-18','广东省深圳市龙岗区龙岗大道1号',0,'2019-03-18','2019-12-25 16:23:25.000000'),(2,'姜勇',20,1,'v.fgwm@ulxsixp.int','2014-06-20','广东省广州市天河区珠江新城',1,'2019-08-19','2019-12-25 14:20:04.000000'),(3,'胡平',23,1,'g.sgxux@cndoqsjus.cy','2019-12-08','贵州省毕节市纳雍县小村',1,'2019-09-06','2019-12-27 17:21:55.000000'),(4,'赵明',29,1,'q.ogthso@yllf.ca','2019-12-02','广东省深圳市罗湖区深南大道117号',1,'2019-12-05','2019-12-27 17:06:12.000000'),(5,'汤静',15,0,'z.koyls@ldsxmn.do','2018-05-20','广东省广州市华农师范大学225班',0,'2019-02-19','2019-12-25 14:01:43.000000'),(6,'石伟',43,0,'y.fzgqute@prnteaoami.pw','2019-12-04','广东省广州市白云区107号',1,'2019-05-18','2019-12-25 14:25:40.000000'),(7,'韩秀兰',26,1,'u.wud@bxiocaamh.cm','2019-12-05','广东省深圳市南山区蛇口海上世界155号',1,'2019-12-22','2019-12-25 13:44:37.000000'),(8,'段娜',24,0,'j.zotz@rmuqqcmu.is','2019-12-11','广东省深圳市罗湖区深南大道101号',0,'2019-09-10','2019-12-27 17:20:04.000000'),(9,'熊超',25,1,'d.wdn@hrgaymd.kg','2019-12-12','广东省广州市天河区珠江新城',1,'2019-06-10','2019-12-27 17:06:26.000000'),(10,'萧秀英',27,0,'c.dfuo@begpv.gf','2019-12-27','广东省深圳市龙岗区龙岗大道05号',1,'2019-07-26','2019-12-26 14:53:38.000000'),(11,'孟刚',23,1,'j.pidkkgqm@qegfdsxt.nu','2019-12-19','广东省深圳市罗湖区深南大道',0,'2019-05-04','2019-12-27 20:31:20.000000'),(12,'吴丽',23,0,'u.dvjpkfdw@crmkfctpcq.am','2019-12-06','广东省深圳市罗湖区深南大道',1,'2019-11-28','2019-12-27 20:31:50.000000'),(13,'黎勇',32,1,'w.bwpnxm@pqcyy.mh','2019-12-13','山东省新泰市75号',0,'2019-06-12','2019-12-19 14:28:23.000000'),(14,'冯静',25,0,'s.rka@ifknsbmex.kw','2019-12-05','徐州市云龙区亚东小区4--2--502',1,'2019-09-04','2019-12-25 14:32:28.000000'),(15,'小丘',18,1,'h.wjox@zryybrw.sh','2019-12-20','江苏省南京市新福路107号',0,'2019-06-25','2019-12-25 20:25:30.000000'),(16,'白丽',22,0,'q.dmi@rsg.de','2016-02-11','山东省济南市安平路227号',1,'2019-04-25','2019-12-25 20:32:21.000000'),(17,'程秀兰',19,0,'q.uerfnjnt@olhwexjge.mr','2019-12-21','北京市鸟巢附近',1,'2019-12-25','2019-12-26 13:56:09.000000'),(18,'刘勇',20,1,'w.lryydsh@dnnemm.gr','2019-12-06','福建省嵩山227号',0,'2019-01-25','2019-12-26 13:56:11.000000'),(19,'廖杰',25,1,'b.yhvzfelmuv@ksjuiu.ma','2019-12-11','广东省深圳市罗湖区深南大道',1,'2019-12-25','2019-12-27 17:06:32.000000'),(20,'谢明',31,1,'h.wjox@zryybrw.sh','2019-12-20','广东省深圳市罗湖区深南大道117号',1,'2019-12-25','2019-12-26 13:56:16.000000'),(21,'小梦',18,1,'361321851@163.com','2019-12-14','未知',1,'2019-11-26','2019-12-26 13:56:19.000000'),(22,'小明',18,1,'dsfkhnaskbd@qq.com','2019-11-07','未知',0,'2019-06-26','2019-12-26 14:25:29.000000'),(23,'陈平',18,1,'askhdjklsnd@qq.com','2019-12-12','未知',1,'2019-03-26','2019-12-26 14:54:22.000000'),(24,'李辉',6,1,'lksdmvnm@126.com','2019-12-13','山东省新泰市75号',1,'2019-10-26','2019-12-26 14:46:07.000000');
/*!40000 ALTER TABLE `tbl_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_logs`
--

DROP TABLE IF EXISTS `tbl_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_logs` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(2) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_logs`
--

LOCK TABLES `tbl_logs` WRITE;
/*!40000 ALTER TABLE `tbl_logs` DISABLE KEYS */;
INSERT INTO `tbl_logs` VALUES (1,1,'登录','2019-12-27 14:24:10'),(2,1,'登录','2019-12-27 14:32:40'),(3,1,'登录','2019-12-27 14:34:09'),(4,1,'修改密码成功','2019-12-27 14:34:19'),(5,1,'修改密码成功','2019-12-27 14:34:37'),(6,1,'登录','2019-12-27 14:38:40'),(7,1,'新增客户成功','2019-12-27 14:39:04'),(8,1,'修改客户成功','2019-12-27 14:39:26'),(9,1,'修改客户成功','2019-12-27 14:39:34'),(10,1,'删除客户成功','2019-12-27 14:39:36'),(11,1,'新增账单成功','2019-12-27 14:40:19'),(12,1,'修改账单成功','2019-12-27 14:43:32'),(13,1,'修改账单成功','2019-12-27 14:43:35'),(14,1,'导出账单成功','2019-12-27 14:43:39'),(15,1,'导出供应商信息成功','2019-12-27 14:43:54'),(16,1,'修改供应商成功','2019-12-27 14:44:12'),(17,1,'修改账单成功','2019-12-27 14:44:31'),(18,1,'登录','2019-12-27 14:56:23'),(19,1,'登录','2019-12-27 17:00:36'),(20,1,'修改客户成功','2019-12-27 17:06:12'),(21,1,'修改客户成功','2019-12-27 17:06:26'),(22,1,'修改客户成功','2019-12-27 17:06:32'),(23,1,'修改客户成功','2019-12-27 17:20:04'),(24,1,'修改客户成功','2019-12-27 17:21:43'),(25,1,'修改客户成功','2019-12-27 17:21:55'),(26,1,'登录','2019-12-27 18:18:52'),(27,1,'登录','2020-12-10 01:01:18');
/*!40000 ALTER TABLE `tbl_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_provider`
--

DROP TABLE IF EXISTS `tbl_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_provider` (
  `pid` int(10) NOT NULL AUTO_INCREMENT,
  `provider_code` varchar(25) DEFAULT NULL,
  `providerName` varchar(25) DEFAULT NULL,
  `people` varchar(25) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `address` varchar(60) DEFAULT NULL,
  `fax` varchar(25) DEFAULT NULL,
  `describe` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_provider`
--

LOCK TABLES `tbl_provider` WRITE;
/*!40000 ALTER TABLE `tbl_provider` DISABLE KEYS */;
INSERT INTO `tbl_provider` VALUES (1,'P_111','A货云服务供应商11','小码','1888888888','广州白云区','020-123456','专业ESC服务商','2018-11-17 12:21:54','2019-12-27 12:25:06'),(2,'P_222','B货域名供应商111','小域','1888886666','北京朝阳区','020-112312123','专业提供域名','2019-01-07 15:58:26','2019-12-27 12:25:10'),(3,'P_333','B货小程序企鹅','小企鹅','1888666666','深圳南山区','020-123123','专业小程序提供','2028-11-12 11:52:17','2019-12-27 12:25:27'),(4,'P_444','其他渠道供应商','无名','11111111','北京','010-1000','专业渠道来源','2018-11-12 12:07:02','2019-12-27 12:25:33'),(9,'P_444','流量卡供应商','小明','12345678901','未知','0755-112257','深圳市罗湖区东门101号','2019-12-27 12:52:49','2019-12-27 14:44:12');
/*!40000 ALTER TABLE `tbl_provider` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-10  1:11:42
