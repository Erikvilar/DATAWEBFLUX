CREATE DATABASE  IF NOT EXISTS `ltadcrm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ltadcrm`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ltadcrm
-- ------------------------------------------------------
-- Server version	8.0.39
-- Dump completed on 2024-11-18 14:13:58
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `account_chk_1` CHECK ((`role` between 0 and 1))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `account` VALUES (1,'erik','$2a$10$mjmojsrZbVQxo/2bANGXFOwHfCveT8scWL0PJZ8xoExeKYgU5fw9a',0),(2,'Carol@ltad','$2a$10$wplc0bjJTlZuVV.8Ozt.DuXrXT54iG4VUuGCAfvCUL0uS0Yhg9euu',0),(3,'Caroll@ltad','$2a$10$qqrFAcPXNLinsr6vChfAfequUNBW9ciXuSX19TZZSP7bRjEiUY0Lm',0),(4,'Caroll','$2a$10$NrkVf86tn4DYl8SyPyjJYuf2Sc/Qgzp9ltqDZVJneyc9BkSnbYqPq',0),(5,'matheus@heitor','$2a$10$jmTrUv5gDMmv7UKcFrY6meSkVljpS0F8Fao8qMrgphSaOS541vC7S',0);


DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users` (
  `id_users` bigint NOT NULL AUTO_INCREMENT,
  `last_modify` varchar(255) DEFAULT NULL,
  `name_users` varchar(255) DEFAULT NULL,
  `type_users` varchar(255) DEFAULT NULL,
  `update_in` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id_users`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `tb_users` (  
`last_modify`,
  `name_users`,
  `type_users` ,
  `update_in` 
  )VALUES (NULL,'Erik','Estagiario','2024-11-14 14:06:15.395663'),
(NULL,'testeasd','professorasdsa','2024-11-18 13:57:08.725780');




DROP TABLE IF EXISTS `tb_contact`;
CREATE TABLE `tb_contact` (
  `id_contact` bigint NOT NULL AUTO_INCREMENT,
  `email_contact` varchar(255) DEFAULT NULL,
  `last_modify` varchar(255) DEFAULT NULL,
  `occupation_contact` varchar(255) DEFAULT NULL,
  `phone_contact` varchar(255) DEFAULT NULL,
  `update_in` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id_contact`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `tb_contact`(

  `email_contact` ,
  `last_modify` ,
  `occupation_contact`,
  `phone_contact` ,
  `update_in`
) VALUES ('joao.silva@example.com',NULL,'Analista de TI','11987654321',NULL),
('maria.oliveira@example.com',NULL,'Assistente de RH','21987654322',NULL),
('carlos.souza@example.com',NULL,'Gerente Financeiro','31987654323',NULL),
('joao.silva@example.com',NULL,'Analista de TI','11987654321',NULL),
('maria.oliveira@example.com',NULL,'Assistente de RH','21987654322',NULL)
,('carlos.souza@example.com',NULL,'Gerente Financeiro','31987654323',NULL),
('joao.silva@example.com',NULL,'Analista de TI','11987654321',NULL),
('maria.oliveira@example.com',NULL,'Assistente de RH','21987654322',NULL),
('carlos.souza@example.com',NULL,'Gerente Financeiro','31987654323',NULL);


DROP TABLE IF EXISTS `tb_cost_center`;
CREATE TABLE `tb_cost_center` (
  `id_cost_center` bigint NOT NULL AUTO_INCREMENT,
  `end_date_cost_center` date DEFAULT NULL,
  `identification_cost_center` varchar(255) DEFAULT NULL,
  `initial_date_cost_center` date DEFAULT NULL,
  `name_cost_center` varchar(255) DEFAULT NULL,
  `last_modify` varchar(255) DEFAULT NULL,
  `update_in`  datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id_cost_center`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `tb_cost_center`(
`end_date_cost_center`,
  `identification_cost_center`,
  `initial_date_cost_center` ,
  `name_cost_center`,
  `last_modify`,
  `update_in`
) VALUES ('2025-12-27','CC001','2019-12-28','TI - Suport',NULL,'2024-11-14 10:17:31.292740'),
('2025-11-29','CC002','2021-04-30','RH - Recrutamentods',NULL,'2024-11-18 14:07:33.240880'),
('2025-12-07','CC001','2019-12-08','Tsdsa',NULL,'2024-11-14 15:35:14.760035');

SELECT * FROM tb_cost_center;
DROP TABLE IF EXISTS `tb_description`;
CREATE TABLE `tb_description` (
  `id_description` bigint NOT NULL AUTO_INCREMENT,
  `brand_description` varchar(255) DEFAULT NULL,
  `description_description` varchar(255) DEFAULT NULL,
  `last_modify` varchar(255) DEFAULT NULL,
  `local_description` varchar(255) DEFAULT NULL,
  `model_description` varchar(255) DEFAULT NULL,
  `serie_description` varchar(255) DEFAULT NULL,
  `update_in` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id_description`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `tb_description` (
  `brand_description` ,
  `description_description` ,
  `last_modify` ,
  `local_description` ,
  `model_description`, 
  `serie_description`, 
  `update_in` 
) VALUES ('Dell','Notebook Dell Inspiron',NULL,'Escritório','Inspiron 5000','SN123456',NULL),
('HP','Desktop HP Elite',NULL,'Fábrica','lll','SN987654','2024-11-18 14:07:33.229880'),
('Apple','MacBook Prof',NULL,'Home Office','ll','SN111222','2024-11-14 15:32:12.027135'),
('Dell','Notebook Dell Inspiron',NULL,'Escritório','Inspiron 5000','SN123456',NULL),
('HP','Desktop HP Elite',NULL,'Fábrica','HP ProDesk 400','SN987654',NULL),
('Apple','MacBook Pro',NULL,'Home Office','MacBook 2020','SN111222',NULL),
('Dell','Notebook Dell Inspiron',NULL,'Escritório','Inspiron 5000','SN123456',NULL),
('HP','Desktop HP Elite',NULL,'Fábrica','HP ProDesk 400','SN987654',NULL),
('Apple','MacBook Pro',NULL,'Home Office','MacBook 2020','SN111222',NULL);


DROP TABLE IF EXISTS `tb_items`;
CREATE TABLE `tb_items` (
  `id_items` bigint NOT NULL AUTO_INCREMENT,
  `last_modify` varchar(255) DEFAULT NULL,
  `nf_invoice_item` varchar(255) DEFAULT NULL,
  `code_item` varchar(255) DEFAULT NULL,
  `observation_item` varchar(255) DEFAULT NULL,
  `order_origin_item` varchar(255) DEFAULT NULL,
  `path_image_item` varchar(255) DEFAULT NULL,
  `sde_item` bigint DEFAULT NULL,
  `status_item` varchar(255) DEFAULT NULL,
  `update_in` datetime(6) DEFAULT NULL,
  `value_item` double DEFAULT NULL,
  `id_cost_center` bigint DEFAULT NULL,
  `id_description` bigint DEFAULT NULL,
  `id_users` bigint DEFAULT NULL,
  PRIMARY KEY (`id_items`),
  KEY `FKom8si4n7bdprwnevv1ke2xov2` (`id_cost_center`),
  KEY `FKjs91yr38i1cdej2lbboc4g0rj` (`id_description`),
  KEY `FKj2k19khybjpypv2wme3et5gdp` (`id_users`),
  CONSTRAINT `FKj2k19khybjpypv2wme3et5gdp` FOREIGN KEY (`id_users`) REFERENCES `tb_users` (`id_users`),
  CONSTRAINT `FKjs91yr38i1cdej2lbboc4g0rj` FOREIGN KEY (`id_description`) REFERENCES `tb_description` (`id_description`),
  CONSTRAINT `FKom8si4n7bdprwnevv1ke2xov2` FOREIGN KEY (`id_cost_center`) REFERENCES `tb_cost_center` (`id_cost_center`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `tb_items`(
 `last_modify`,
  `nf_invoice_item`,
  `code_item` ,
  `observation_item`,
  `order_origin_item` ,
  `path_image_item` ,
  `sde_item`,
  `status_item`,
  `update_in`,
  `value_item` ,
  `id_cost_center`,
  `id_description`,
  `id_users` 
)
VALUES (NULL,'NF12345','ITEM001','Notebook Dell Inspiron','Pedido001','/images/items/item001.jpg',10,'Ativo',NULL,2500.5,5,5,5),
(NULL,'NF12346','ITEM001','cpuasdas','Pedido002','/images/items/item002.jpg',5,'Inativo','2024-11-18 13:58:24.492959',3000,6,6,6),
(NULL,'NF12347','ITEM003','CPUl','Pedido003','/images/items/item003.jpg',8,'Ativo','2024-11-14 15:35:14.735991',8000,7,7,7);

SELECT * FROM tb_users;


-- --> Tools routines
-- --> Procedures()
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllItems`()
BEGIN
SELECT 
    u.id_users AS id_usuario,
    u.name_users AS nome_usuario,
    u.type_users AS tipo_usuario,

    i.id_items AS id_item,
    i.nf_invoice_item AS nf_invoice_item,
    i.code_item AS codigo_item,
    i.observation_item AS observacao_item,
    i.path_image_item AS caminho_imagem_item,
    i.order_origin_item AS pedido_origem,
    i.sde_item AS sde_item,
    i.status_item AS status_item,
    i.value_item AS valor_item,
    i.last_modify AS lastModify,
    i.update_in AS updateIn,

    d.id_details AS id_descricao,
    d.brand_description AS marca_descricao,
    d.description_description AS descricao_item,
    d.local_description AS localizacao_descricao,
    d.model_description AS modelo_descricao,
    d.serie_description AS serie_descricao,

    c.id_cost_center AS id_centro_custo,
    c.end_date_cost_center AS data_fim_centro_custo,
    c.identification_cost_center AS identificacao_centro_custo,
    c.initial_date_cost_center AS data_inicio_centro_custo,
    c.name_cost_center AS nome_centro_custo,

    co.id_contact AS id_contato,
    co.email_contact AS email_contato,
    co.occupation_contact AS ocupacao_contato,
    co.phone_contact AS telefone_contato

FROM 
    tb_items i
JOIN 
    tb_users u ON i.id_users = u.id_users
JOIN 
    tb_details d ON i.id_details = d.id_details
JOIN 
    tb_cost_center c ON i.id_cost_center = c.id_cost_center
JOIN 
    tb_contact co ON co.id_contact = i.id_users;

END ;;
DELIMITER ;
call GetAllItems();
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertDemoValues`()
BEGIN

INSERT INTO `tb_cost_center` (`end_date_cost_center`, `identification_cost_center`, `initial_date_cost_center`, `name_cost_center`) 
VALUES
    ('2025-12-31', 'CC001', '2020-01-01', 'TI - Suporte'),
    ('2025-12-31', 'CC002', '2021-06-01', 'RH - Recrutamento'),
    ('2025-12-31', 'CC003', '2022-02-01', 'Financeiro - Contabilidade');

INSERT INTO `tb_contact` (`email_contact`, `occupation_contact`, `phone_contact`) 
VALUES
    ('joao.silva@example.com', 'Analista de TI', '11987654321'),
    ('maria.oliveira@example.com', 'Assistente de RH', '21987654322'),
    ('carlos.souza@example.com', 'Gerente Financeiro', '31987654323');
INSERT INTO `tb_description` (`brand_description`, `description_description`, `local_description`, `model_description`, `serie_description`) 
VALUES
    ('Dell', 'Notebook Dell Inspiron', 'Escritório', 'Inspiron 5000', 'SN123456'),
    ('HP', 'Desktop HP Elite', 'Fábrica', 'HP ProDesk 400', 'SN987654'),
    ('Apple', 'MacBook Pro', 'Home Office', 'MacBook 2020', 'SN111222');
INSERT INTO `tb_items` 
(`nf_invoice_item`, `code_item`, `observation_item`, `path_image_item`, `order_origin_item`, `sde_item`, `status_item`, `value_item`, `id_cost_center`, `id_description`, `id_users`) 
VALUES
    ('NF12345', 'ITEM001', 'Notebook Dell Inspiron', '/images/items/item001.jpg', 'Pedido001', 10, 'Ativo', 2500.50, 1, 1, 1),  -- Item relacionado ao usuário 'João Silva'
    ('NF12346', 'ITEM002', 'Desktop HP Elite', '/images/items/item002.jpg', 'Pedido002', 5, 'Inativo', 3000.00, 2, 2, 2),  -- Item relacionado ao usuário 'Maria Oliveira'
    ('NF12347', 'ITEM003', 'MacBook Pro', '/images/items/item003.jpg', 'Pedido003', 8, 'Ativo', 8000.00, 3, 3, 3);  -- Item relacionado ao usuário 'Carlos Souza'
END ;;
DELIMITER ;

DROP TABLE IF EXISTS `audit_log`;
CREATE TABLE `audit_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_item` bigint DEFAULT NULL,
  `name_item_old` varchar(255) DEFAULT NULL,
  `name_item_new` varchar(255) DEFAULT NULL,
  `email_user` varchar(255) DEFAULT NULL,
  `create_in` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_in` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type_action` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- --> Triggers()
DELIMITER $
CREATE TRIGGER handle_update_on_tb_description  AFTER UPDATE ON `tb_description`
FOR EACH ROW
BEGIN
	IF NEW.description_description <> OLD.description_description THEN
	INSERT INTO `audit_log`(
	name_item_old,
	name_item_new ,
	email_user,
	type_action 
	) VALUES (
    OLD.description_description ,
    NEW.description_description,
    'email@teste','UPDATE');	
	END IF;
    
END $
DELIMITER ;

DROP TRIGGER handle_update_on_tb_items;

SHOW TRIGGERS;

SELECT * FROM audit_log;
SELECT * FROM tb_description;