/*
SQLyog Ultimate v8.71 
MySQL - 5.6.27-log : Database - sys
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sys`;

/*Table structure for table `autorizacao` */

DROP TABLE IF EXISTS `autorizacao`;

CREATE TABLE `autorizacao` (
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `autorizacao` */

/*Table structure for table `grupo` */

DROP TABLE IF EXISTS `grupo`;

CREATE TABLE `grupo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(80) NOT NULL,
  `nome` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `grupo` */

insert  into `grupo`(`id`,`descricao`,`nome`) values (1,'ADMINISTRADORES','ADMINISTRADORES'),(2,'USUARIOS','USUARIOS');

/*Table structure for table `lancamento` */

DROP TABLE IF EXISTS `lancamento`;

CREATE TABLE `lancamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_pagamento` date DEFAULT NULL,
  `data_vencimento` date NOT NULL,
  `descricao` varchar(80) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `pessoa_id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ax5wcm3fcn1xss5lkmlbt68pu` (`pessoa_id`),
  KEY `FK_hx0605tv9uetbnuye3bk1cc6s` (`usuario_id`),
  CONSTRAINT `FK_ax5wcm3fcn1xss5lkmlbt68pu` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `FK_hx0605tv9uetbnuye3bk1cc6s` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

/*Data for the table `lancamento` */

insert  into `lancamento`(`id`,`data_pagamento`,`data_vencimento`,`descricao`,`tipo`,`valor`,`pessoa_id`,`usuario_id`) values (14,NULL,'2015-11-25','testes','DESPESA','123.00',2,1),(20,NULL,'2015-11-26','test2','RECEITA','50.02',6,2),(21,NULL,'2015-11-26','testes','DESPESA','60.00',5,2),(23,'2015-11-26','2015-11-26','tetes','DESPESA','14.00',2,2),(24,NULL,'2015-11-26','testes taxa cond.','RECEITA','180.00',8,2),(25,'2015-12-22','2015-12-22','troca do anel (vedação anel da bobina (iginição))','DESPESA','20.00',18,1);

/*Table structure for table `pessoa` */

DROP TABLE IF EXISTS `pessoa`;

CREATE TABLE `pessoa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `pessoa` */

insert  into `pessoa`(`id`,`nome`) values (1,'BV FINANCEIRA'),(2,'TESTES'),(3,'sandro magno'),(4,'katarina Nogueira'),(5,'Capital Cursos s/s'),(6,'eletrobrás'),(7,'Megavia'),(8,'Taxa do Condomínio PIKUSA'),(9,'cartão banco bb'),(10,'cartão hiper'),(11,'pagamento do profº Jon'),(12,'camisas do EDSON'),(13,'testes '),(14,'riachuelo midlay'),(15,'posto de lavagem'),(16,'Katarina Nogueira '),(17,'WYSTAN FREITAS '),(18,'Auto Peças Só Fiat');

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `nome` varchar(80) NOT NULL,
  `senha` varchar(20) NOT NULL,
  `username` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5171l57faosmj8myawaucatdw` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

insert  into `usuario`(`id`,`email`,`nome`,`senha`,`username`) values (1,'igor@mail.com','igor','igor','IGORCS'),(2,'user@user.com','user','user','Usuários');

/*Table structure for table `usuario_grupo` */

DROP TABLE IF EXISTS `usuario_grupo`;

CREATE TABLE `usuario_grupo` (
  `usuario_id` bigint(20) NOT NULL,
  `grupo_id` bigint(20) NOT NULL,
  KEY `FK_5p20y5panoea7wc040qm6eemd` (`grupo_id`),
  KEY `FK_m32it4c8rkf6t8nno481k43q4` (`usuario_id`),
  CONSTRAINT `FK_5p20y5panoea7wc040qm6eemd` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`),
  CONSTRAINT `FK_m32it4c8rkf6t8nno481k43q4` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `usuario_grupo` */

insert  into `usuario_grupo`(`usuario_id`,`grupo_id`) values (1,1),(2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
