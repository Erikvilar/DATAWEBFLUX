-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ltadcrm
-- ------------------------------------------------------
-- Server version	8.0.39
--
-- Table structure for table `tb_users`
--
DROP TABLE IF EXISTS `tb_users`;
CREATE DATABASE ltadcrm;
USE ltadcrm;
CREATE TABLE `tb_users` (
  `id_users` bigint NOT NULL AUTO_INCREMENT,
  `name_users` varchar(255) DEFAULT NULL,
  `type_users` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_users`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_items` (
  `id_items` bigint NOT NULL AUTO_INCREMENT,
  `nf_invoice_item` varchar(255) DEFAULT NULL,
  `code_item` varchar(255) DEFAULT NULL,
  `observation_item` varchar(255) DEFAULT NULL,
  `path_image_item` varchar(255) DEFAULT NULL,
  `order_origin_item` varchar(255) DEFAULT NULL,
  `sde_item` bigint DEFAULT NULL,
  `status_item` varchar(255) DEFAULT NULL,
  `value_item` double DEFAULT NULL,
  `id_cost_center` bigint DEFAULT NULL,
  `id_descriptions` bigint DEFAULT NULL,
  `id_users` bigint DEFAULT NULL,
  PRIMARY KEY (`id_items`),
  KEY `FKom8si4n7bdprwnevv1ke2xov2` (`id_cost_center`),
  KEY `FKr45hggsx50h59xe3yin4uw67c` (`id_descriptions`),
  KEY `FKj2k19khybjpypv2wme3et5gdp` (`id_users`),
  CONSTRAINT `FKj2k19khybjpypv2wme3et5gdp` FOREIGN KEY (`id_users`) REFERENCES `tb_users` (`id_users`),
  CONSTRAINT `FKom8si4n7bdprwnevv1ke2xov2` FOREIGN KEY (`id_cost_center`) REFERENCES `tb_cost_center` (`id_cost_center`),
  CONSTRAINT `FKr45hggsx50h59xe3yin4uw67c` FOREIGN KEY (`id_descriptions`) REFERENCES `tb_description` (`id_description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_description` (
  `id_description` bigint NOT NULL AUTO_INCREMENT,
  `brand_description` varchar(255) DEFAULT NULL,
  `description_description` varchar(255) DEFAULT NULL,
  `local_description` varchar(255) DEFAULT NULL,
  `model_description` varchar(255) DEFAULT NULL,
  `serie_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_cost_center` (
  `id_cost_center` bigint NOT NULL AUTO_INCREMENT,
  `end_date_cost_center` date DEFAULT NULL,
  `identification_cost_center` varchar(255) DEFAULT NULL,
  `initial_date_cost_center` date DEFAULT NULL,
  `name_cost_center` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cost_center`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_contact` (
  `id_contact` bigint NOT NULL AUTO_INCREMENT,
  `email_contact` varchar(255) DEFAULT NULL,
  `occupation_contact` varchar(255) DEFAULT NULL,
  `phone_contact` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_contact`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

select * from tb_description;
-- consults 

SELECT 
    u.id_users,
    u.name_users,
    u.type_users ,
    
    i.id_items ,
    i.nf_invoice_item ,
    i.code_item ,
    i.observation_item,
    i.path_image_item ,
    i.order_origin_item ,
    i.sde_item ,
    i.status_item ,
    i.value_item ,
    
    d.id_description,
    d.brand_description ,
    d.description_description,
    d.local_description ,
    d.model_description ,
    d.serie_description,
    
    c.id_cost_center ,
    c.end_date_cost_center ,
    c.identification_cost_center ,
    c.initial_date_cost_center ,
    c.name_cost_center ,
    
    co.id_contact ,
    co.email_contact ,
    co.occupation_contact ,
    co.phone_contact

FROM 
    tb_items i
JOIN 
    tb_users u ON i.id_users = u.id_users
JOIN 
    tb_description d ON i.id_description = d.id_description
JOIN 
    tb_cost_center c ON i.id_cost_center = c.id_cost_center
JOIN 
    tb_contact co ON co.id_contact = i.id_users; 
    
-- Construção de procedure call

DELIMITER $$
CREATE PROCEDURE GetAllItems()
BEGIN
    -- Consulta SQL para retornar os dados de todas as tabelas
  SELECT 
    u.id_users ,
    u.name_users,
    u.type_users ,
    
    i.id_items ,
    i.nf_invoice_item ,
    i.code_item ,
    i.observation_item,
    i.path_image_item ,
    i.order_origin_item ,
    i.sde_item ,
    i.status_item ,
    i.value_item ,
    
    d.id_description,
    d.brand_description ,
    d.description_description,
    d.local_description ,
    d.model_description ,
    d.serie_description,
    
    c.id_cost_center ,
    c.end_date_cost_center ,
    c.identification_cost_center ,
    c.initial_date_cost_center ,
    c.name_cost_center ,
    
    co.id_contact ,
    co.email_contact ,
    co.occupation_contact ,
    co.phone_contact

FROM 
    tb_items i
JOIN 
    tb_users u ON i.id_users = u.id_users
JOIN 
    tb_description d ON i.id_description = d.id_description
JOIN 
    tb_cost_center c ON i.id_cost_center = c.id_cost_center
JOIN 
    tb_contact co ON co.id_contact = i.id_users; 
END 
DELIMITER ;
call GetAllItems();

-- Create insertions
INSERT INTO `tb_users` (`name_users`, `type_users`) 
VALUES
    ('João Silva', 'Administrador'),
    ('Maria Oliveira', 'Funcionário'),
    ('Carlos Souza', 'Gerente');


select * from tb_description;

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
    ('NF12347', 'ITEM003', 'MacBook Pro', '/images/items/item003.jpg', 'Pedido003', 8, 'Ativo', 8000.00, 3, 3, 3),  -- Item relacionado ao usuário 'Carlos Souza'
    ('NF12347', 'ITEM003', 'Iphone Pro Max', '/images/items/item003.jpg', 'Pedido003', 8, 'Ativo', 8000.00, 1, 1, 1);  -- Item relacionado ao usuário 'Carlos Souza'
SELECT * FROM tb_description;






  SELECT 
    u.id_users ,
    u.name_users,
    u.type_users ,
    
    i.id_items ,
    i.nf_invoice_item ,
    i.code_item ,
    i.observation_item,
    i.path_image_item ,
    i.order_origin_item ,
    i.sde_item ,
    i.status_item ,
    i.value_item ,
    
    d.id_description,
    d.brand_description ,
    d.description_description,
    d.local_description ,
    d.model_description ,
    d.serie_description,
    
    c.id_cost_center ,
    c.end_date_cost_center ,
    c.identification_cost_center ,
    c.initial_date_cost_center ,
    c.name_cost_center ,
    
    co.id_contact ,
    co.email_contact ,
    co.occupation_contact ,
    co.phone_contact

FROM 
    tb_items i
JOIN 
    tb_users u ON i.id_users = u.id_users
JOIN 
    tb_description d ON i.id_description = d.id_description
JOIN 
    tb_cost_center c ON i.id_cost_center = c.id_cost_center
JOIN 
    tb_contact co ON co.id_contact = i.id_users; 