DROP DATABASE IF EXISTS ltadcrm;
CREATE DATABASE IF NOT EXISTS ltadcrm;
USE ltadcrm;
-- Enunciado Guia;
-- tb_ptr = tabela geral de patrimonios;
-- tb_descr = Tabela normalizada
-- tb_coord = tabela de coodernadores

CREATE TABLE patrimonio (
    idPtr INT AUTO_INCREMENT PRIMARY KEY NOT NULL UNIQUE,
    nPtr INT NOT NULL,          -- Nº do patrimônio UFU
    observPtr varchar(255),     -- Observações do item
    urlPtr varchar(255),        -- Lista com as imagens e/ou fotos do patrimônio
    sdePtr varchar(255),        -- Nº da SDE
    pedidoPtr varchar(255),     -- N° do pedido de compra
    statusPtr enum ("Ativo","Pendente","Inativo","Baixa solciitada","PSM"), -- Situação do item
        -- Ativo            →   -- Não existe problemas relacionados a esse item
        -- Pendente         →   -- Existe alguma não-conformidade a ser resolvida. A descrição deve estar no campo "Observação" do item
        -- Inativo          →   -- Item foi baixado do sistema UFU e não faz parte do patrimônio
        -- Baixa solicitada →   -- Criado processo SEI para remover o item do patrimônio
        -- PSM              →   -- Item em manutenção. Os detalhes deverão ser adicionados no campo "Observação" do item
    NF_INVOICE varchar(255),    -- N° da Nota Fiscal (item nacional) ou Invoice (item importado)
    valorPtr decimal(10,2),     -- Preço de custo
    
    -- definição do identificação das chaves estrangeiras
    idDescr INT,                -- ID Descrição completa
    idUsuario INT,              -- ID Usuário que cadastrou ou modificou o item
    idCdc INT,                  -- ID Centro de Custo (projeto, prestação serviço ou laboratório)

    -- importação da referencias das tabelas[descrição, usuario]
    CONSTRAINT fk_descricao FOREIGN KEY (idDescr)
        REFERENCES descricao (idDescr),
    CONSTRAINT fk_usuario FOREIGN KEY (idUsuario)
        REFERENCES usuario (idUsuario),
	CONSTRAINT fk_cdc FOREIGN KEY (idCdc)
        REFERENCES cdc (idCdc)
);

-- tabela de normalizada 
CREATE TABLE descricao (
    idDescr INT AUTO_INCREMENT PRIMARY KEY NOT NULL UNIQUE,
    categoria VARCHAR(255),             -- Informática, Escritório, Equipamento, etc.
    modeloPtr VARCHAR(255),             -- Modelo do item. Ex.: OptiPlex 7070
    marcaPtr VARCHAR(255),              -- Marca ou fabricante do item. Ex.: Dell
    --seriePtr VARCHAR(255),            -- Podemos retirar
    descriPtr VARCHAR(255) NOT NULL,    -- Descrição completa do item com as informações mais relevantes (VEJA EXEMPLOS ABAIXO)
    locPtr vaRCHAR(255)                 -- Localização. Ex.: "5F 302", "1F armário RVEST"
);
-- CADEIRA GIRATORIA ALTA PARA BANCADA SEM BRAÇOS C/ ASSENTO/ENCOSTO C/ REGULAGEM DE ALTURA A GAS E REVESTIMENTO EM POLIPROPILENO COR PRETA MODELO:ISO MARCA-FRISOKAR
-- Modelo: ISO
-- Marca: Frisokar
-- Serie: (vazio)
-- Descrição: CADEIRA GIRATORIA ALTA PARA BANCADA SEM BRAÇOS C/ ASSENTO/ENCOSTO C/ REGULAGEM DE ALTURA A GAS E REVESTIMENTO EM POLIPROPILENO COR PRETA
-- Localização: 5F 302

-- ESPECTROFOTOMETRO ULTRAVIOLETA-VISIVEL COM COM TELA DE ALTA VISIBILIDADE/COLORIDA/TOUCH CONTROLADO POR PC E/OU INTERFACE USB ACOMPANHA ACESSÓRIOS MODELO: UV-1900 MARCA SHIMADZU
-- Modelo:      UV-1900
-- Marca:       Shimadzu
-- Série:       (vazio)
-- Descrição:   ESPECTROFOTOMETRO ULTRAVIOLETA-VISIVEL COM COM TELA DE ALTA VISIBILIDADE/COLORIDA/TOUCH CONTROLADO POR PC E/OU INTERFACE USB ACOMPANHA ACESSÓRIOS

-- CLP Marca: Simens S71200 CPU1215C. DC/DC/DC. Código: 6ES7215-1AG40-0XB0
-- Modelo:      CPU1215C DC/DC/DC
-- Marca:       Siemens
-- Série:       (vazio)
-- Descrição:   CLP S7-1200 6ES7215-1AG40-0XB0

-- tabela de atribuição de responsabilidade aos usuarios
CREATE TABLE usuario (
    IdUsuario INT AUTO_INCREMENT PRIMARY KEY NOT NULL UNIQUE,
    nomeUsuario VARCHAR (255),          -- Nome Sobrenome (preferência igual ao e-mail)
    tipo ENUM("coordenador","responsavel","outro")
        -- Coordernador = Professor responsável pelo projeto
        -- Responsável  = Colaborador responsável pelo uso e conservação do item
        -- Outro        = Utilizado nos demais casos (se necessário)
);
-- tabela de centro de custo
CREATE TABLE cdc (
	idCdc INT AUTO_INCREMENT PRIMARY KEY NOT NULL UNIQUE,
    nomeCdc varchar(255),       -- Nome do projeto, prestação de serviço ou laboratório
    siglaCdc varchar(15),
    dataIniCdc DATE,
    dataFimCdc DATE
);
-- tabela de atribuição de contatos gerais
CREATE TABLE contato(
	idContato INT AUTO_INCREMENT PRIMARY KEY NOT NULL UNIQUE,
    Email VARCHAR(255),
    Telefone VARCHAR (255),
    Ocupacao VARCHAR(255)
);
-- tabela para fornecedores
CREATE TABLE fornecedor(
	idFornecedor INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nomeFornecedor varchar(255),
    empresa varchar(255),
    idContato int,
	CONSTRAINT fk_contato FOREIGN KEY (idContato)
	REFERENCES contato (idContato)
    
);

-- Campo de inserções de dados e testes de balançeamento de carga
-- insert na tabela contato 
-- primeiro insert teste para validação de tabela
INSERT INTO  contato VALUES (1,'erikvilar@gmail.com','34999-2832','Estagiario');
-- --> insert demonstrativo com 199 inserções
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (2, 'maria@example.com', '2345-6789', 'Médica');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (3, 'carlos@example.com', '3456-7890', 'Professor');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (4, 'ana@example.com', '4567-8901', 'Designer');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (5, 'paula@example.com', '5678-9012', 'Arquiteta');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (6, 'luiz@example.com', '6789-0123', 'Advogado');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (7, 'fernando@example.com', '7890-1234', 'Programador');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (8, 'silvia@example.com', '8901-2345', 'Contadora');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (9, 'rafael@example.com', '9012-3456', 'Jornalista');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (10, 'juliana@example.com', '0123-4567', 'Gerente');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (11, 'marcio@example.com', '1234-5678', 'Consultor');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (12, 'tatiane@example.com', '2345-6789', 'Veterinária');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (13, 'jose@example.com', '3456-7890', 'Cientista');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (14, 'beatriz@example.com', '4567-8901', 'Psicóloga');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (15, 'andre@example.com', '5678-9012', 'Músico');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (16, 'karla@example.com', '6789-0123', 'Fotógrafa');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (17, 'leandro@example.com', '7890-1234', 'Ator');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (18, 'vanessa@example.com', '8901-2345', 'Chef');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (19, 'mariana@example.com', '9012-3456', 'Estudante');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (20, 'roberto@example.com', '0123-4567', 'Motorista');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (21, 'daniela@example.com', '1234-5678', 'Engenheira de Software');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (22, 'gustavo@example.com', '2345-6789', 'Analista de Sistemas');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (23, 'patricia@example.com', '3456-7890', 'Consultora de Vendas');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (24, 'ricardo@example.com', '4567-8901', 'Gerente de Projetos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (25, 'marcio@example.com', '5678-9012', 'Desenvolvedor Web');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (26, 'luana@example.com', '6789-0123', 'Psicóloga');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (27, 'vitor@example.com', '7890-1234', 'Químico');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (28, 'roberta@example.com', '8901-2345', 'Bióloga');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (29, 'nicolas@example.com', '9012-3456', 'Economista');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (30, 'isabel@example.com', '0123-4567', 'Tradutora');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (31, 'felipe@example.com', '1234-5678', 'Gestor de Recursos Humanos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (32, 'larissa@example.com', '2345-6789', 'Analista Financeiro');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (33, 'marcel@example.com', '3456-7890', 'Mestre de Cerimônias');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (34, 'alessandra@example.com', '4567-8901', 'Publicitária');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (35, 'andreia@example.com', '5678-9012', 'Estudante de Medicina');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (36, 'ronaldo@example.com', '6789-0123', 'Técnico de Informática');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (37, 'natalia@example.com', '7890-1234', 'Designer Gráfico');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (38, 'tiago@example.com', '8901-2345', 'Arquiteto de Soluções');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (39, 'elisabeth@example.com', '9012-3456', 'Professora de Matemática');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (40, 'michele@example.com', '0123-4567', 'Assistente Administrativo');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (41, 'adriana@example.com', '1234-5678', 'Diretora de Marketing');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (42, 'roberto2@example.com', '2345-6789', 'Assistente de Vendas');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (43, 'fernanda@example.com', '3456-7890', 'Engenheira Civil');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (44, 'victor@example.com', '4567-8901', 'Programador de Jogos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (45, 'lucas@example.com', '5678-9012', 'Contabilista');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (46, 'mirella@example.com', '6789-0123', 'Gestora de Projetos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (47, 'cintia@example.com', '7890-1234', 'Coordenadora de Eventos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (48, 'david@example.com', '8901-2345', 'Estudante de Engenharia');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (49, 'aline@example.com', '9012-3456', 'Chef de Cozinha');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (50, 'rogerio@example.com', '0123-4567', 'Editor de Vídeo');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (51, 'marta@example.com', '1234-5678', 'Padeiro');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (52, 'carlos2@example.com', '2345-6789', 'Estudante de Direito');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (53, 'fabiana@example.com', '3456-7890', 'Coordenadora Pedagógica');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (54, 'marcus@example.com', '4567-8901', 'Artista Plástico');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (55, 'samantha@example.com', '5678-9012', 'Especialista em SEO');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (56, 'roberto3@example.com', '6789-0123', 'Gerente de TI');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (57, 'renata@example.com', '7890-1234', 'Veterinário');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (58, 'paulinho@example.com', '8901-2345', 'Estudante de Arquitetura');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (59, 'veronica@example.com', '9012-3456', 'Fisioterapeuta');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (60, 'jose2@example.com', '0123-4567', 'Técnico de Enfermagem');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (61, 'isabella@example.com', '1234-5678', 'Estudante de Psicologia');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (62, 'nicolas2@example.com', '2345-6789', 'Designer de Interiores');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (63, 'kelly@example.com', '3456-7890', 'Engenheira Eletrônica');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (64, 'andre3@example.com', '4567-8901', 'Gerente de Operações');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (65, 'marcio2@example.com', '5678-9012', 'Arquiteto paisagista');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (66, 'leticia@example.com', '6789-0123', 'Artista Digital');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (67, 'leonardo@example.com', '7890-1234', 'Operador de Máquinas');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (68, 'sabrina@example.com', '8901-2345', 'Médica Veterinária');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (69, 'rafael2@example.com', '9012-3456', 'Gestor de Qualidade');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (70, 'eliana@example.com', '0123-4567', 'Secretária Executiva');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (71, 'antonio@example.com', '1234-5678', 'Web Designer');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (72, 'gabriela@example.com', '2345-6789', 'Estudante de Artes');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (73, 'paulom@example.com', '3456-7890', 'Analista de Marketing');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (74, 'tiago2@example.com', '4567-8901', 'Professor de Educação Física');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (75, 'marcelam@example.com', '5678-9012', 'Biomédica');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (76, 'sandro@example.com', '6789-0123', 'Gerente de Vendas');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (77, 'karla2@example.com', '7890-1234', 'Cineasta');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (78, 'andre4@example.com', '8901-2345', 'Agente de Viagens');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (79, 'vinicius@example.com', '9012-3456', 'Chef Pâtissier');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (80, 'celia@example.com', '0123-4567', 'Artesã');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (81, 'henrique@example.com', '1234-5678', 'Funcionário Público');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (82, 'aline2@example.com', '2345-6789', 'Estudante de Direito');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (83, 'luiz2@example.com', '3456-7890', 'Designer Gráfico');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (84, 'tatiane2@example.com', '4567-8901', 'Analista de Recursos Humanos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (85, 'julia@example.com', '5678-9012', 'Professor de História');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (86, 'bruno@example.com', '6789-0123', 'Cientista de Dados');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (87, 'marcio3@example.com', '7890-1234', 'Especialista em Marketing Digital');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (88, 'clara@example.com', '8901-2345', 'Biologista');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (89, 'rafaela@example.com', '9012-3456', 'Engenheira de Produção');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (90, 'roberto4@example.com', '0123-4567', 'Analista de Pesquisa');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (91, 'livia@example.com', '1234-5678', 'Consultora de Gestão');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (92, 'ricardo2@example.com', '2345-6789', 'Estudante de Filosofia');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (93, 'marcelo@example.com', '3456-7890', 'Gestor de Produto');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (94, 'viviane@example.com', '4567-8901', 'Fisioterapeuta');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (95, 'aline3@example.com', '5678-9012', 'Professor de Literatura');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (96, 'claudio@example.com', '6789-0123', 'Desenvolvedor de Software');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (97, 'roberto5@example.com', '7890-1234', 'Analista de Qualidade');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (98, 'samantha2@example.com', '8901-2345', 'Arquiteta de Informação');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (99, 'roberto6@example.com', '9012-3456', 'Produtor Cultural');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (100, 'patricia2@example.com', '0123-4567', 'Engenheira Ambiental');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (101, 'jose3@example.com', '1234-5678', 'Estudante de Biologia');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (102, 'fernando2@example.com', '2345-6789', 'Assistente de Marketing');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (103, 'elaine@example.com', '3456-7890', 'Arquiteta');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (104, 'edgar@example.com', '4567-8901', 'Gerente de Tecnologia');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (105, 'renato@example.com', '5678-9012', 'Professor de Química');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (106, 'claudia@example.com', '6789-0123', 'Analista de Dados');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (107, 'marcos@example.com', '7890-1234', 'Designer de Moda');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (108, 'roberto7@example.com', '8901-2345', 'Médico');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (109, 'larissa2@example.com', '9012-3456', 'Engenheira de Software');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (110, 'benjamin@example.com', '0123-4567', 'Contador');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (111, 'andreia2@example.com', '1234-5678', 'Estudante de Filosofia');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (112, 'carmen@example.com', '2345-6789', 'Terapeuta Ocupacional');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (113, 'hugo@example.com', '3456-7890', 'Engenheiro Mecânico');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (114, 'mariana2@example.com', '4567-8901', 'Estudante de História');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (115, 'edna@example.com', '5678-9012', 'Analista Financeiro');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (116, 'bruna@example.com', '6789-0123', 'Gerente de Produto');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (117, 'cintia2@example.com', '7890-1234', 'Médico Cirurgião');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (118, 'ricardo3@example.com', '8901-2345', 'Consultor Ambiental');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (119, 'rafaela2@example.com', '9012-3456', 'Designer Gráfico');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (120, 'joana@example.com', '0123-4567', 'Estudante de Engenharia');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (121, 'tania@example.com', '1234-5678', 'Assistente Social');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (122, 'marcelo2@example.com', '2345-6789', 'Enfermeiro');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (123, 'carla@example.com', '3456-7890', 'Nutricionista');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (124, 'tatiane3@example.com', '4567-8901', 'Analista de Recursos Humanos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (125, 'hugo2@example.com', '5678-9012', 'Músico');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (126, 'samira@example.com', '6789-0123', 'Tradutora');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (127, 'luan@example.com', '7890-1234', 'Coordenador de Eventos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (128, 'monica@example.com', '8901-2345', 'Consultora de Imagem');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (129, 'vivi@example.com', '9012-3456', 'Engenheiro de Alimentos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (130, 'roberto8@example.com', '0123-4567', 'Fisioterapeuta');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (131, 'karla4@example.com', '1234-5678', 'Desenvolvedora Frontend');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (132, 'frederico@example.com', '2345-6789', 'Médico Oncologista');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (133, 'andre5@example.com', '3456-7890', 'Estudante de Engenharia Civil');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (134, 'rebecca@example.com', '4567-8901', 'Gerente de Comunicações');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (135, 'roberto9@example.com', '5678-9012', 'Agente de Seguros');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (136, 'silas@example.com', '6789-0123', 'Analista de Sistemas');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (137, 'patricia3@example.com', '7890-1234', 'Engenheira Química');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (138, 'jaime@example.com', '8901-2345', 'Professor de Filosofia');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (139, 'mariana3@example.com', '9012-3456', 'Redator');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (140, 'roger@example.com', '0123-4567', 'Especialista em Segurança');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (141, 'vitor2@example.com', '1234-5678', 'Terapeuta');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (142, 'aline4@example.com', '2345-6789', 'Coordenadora de Projetos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (143, 'fernando3@example.com', '3456-7890', 'Designer de Produtos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (144, 'lucas3@example.com', '4567-8901', 'Médico Cardiologista');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (145, 'joel@example.com', '5678-9012', 'Estudante de Engenharia de Software');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (146, 'miranda@example.com', '6789-0123', 'Professora de Biologia');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (147, 'leandro2@example.com', '7890-1234', 'Especialista em Recursos Humanos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (148, 'rafael3@example.com', '8901-2345', 'Consultor de TI');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (149, 'tatiane4@example.com', '9012-3456', 'Engenheira de Materiais');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (150, 'jose4@example.com', '0123-4567', 'Estudante de Arquitetura');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (151, 'mario@example.com', '1234-5678', 'Mestre de Cerimônias');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (152, 'renan@example.com', '2345-6789', 'Diretor de Cinema');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (153, 'vivi2@example.com', '3456-7890', 'Analista de SEO');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (154, 'glauco@example.com', '4567-8901', 'Desenvolvedor de Aplicativos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (155, 'milena@example.com', '5678-9012', 'Fotógrafa');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (156, 'guilherme@example.com', '6789-0123', 'Estudante de Psicologia');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (157, 'catarina@example.com', '7890-1234', 'Assistente Administrativa');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (158, 'mauricio@example.com', '8901-2345', 'Coordenador de Treinamento');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (159, 'tamires@example.com', '9012-3456', 'Biólogo Marinho');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (160, 'roberto10@example.com', '0123-4567', 'Professor de Artes Plásticas');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (161, 'estela@example.com', '1234-5678', 'Gestora de Recursos Humanos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (162, 'ivan@example.com', '2345-6789', 'Analista de Suporte Técnico');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (163, 'yara@example.com', '3456-7890', 'Consultora de Vendas');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (164, 'francisco@example.com', '4567-8901', 'Engenheiro de Segurança do Trabalho');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (165, 'zuleica@example.com', '5678-9012', 'Pesquisadora');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (166, 'rogerio2@example.com', '6789-0123', 'Gestor de Marketing');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (167, 'tatiane5@example.com', '7890-1234', 'Desenvolvedora de Software');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (168, 'sandro2@example.com', '8901-2345', 'Gerente de Contabilidade');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (169, 'leonardo2@example.com', '9012-3456', 'Professora de Educação Física');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (170, 'cynthia@example.com', '0123-4567', 'Estudante de Medicina');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (171, 'junior@example.com', '1234-5678', 'Pediatra');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (172, 'marcio4@example.com', '2345-6789', 'Consultor de TI');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (173, 'jose5@example.com', '3456-7890', 'Designer de Interiores');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (174, 'flavia@example.com', '4567-8901', 'Editor de Revista');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (175, 'thiago2@example.com', '5678-9012', 'Geólogo');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (176, 'alessandro@example.com', '6789-0123', 'Desenvolvedora Web');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (177, 'monique@example.com', '7890-1234', 'Estudante de Enfermagem');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (178, 'paula@example.com', '8901-2345', 'Técnica em Radiologia');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (179, 'marcio5@example.com', '9012-3456', 'Analista de Processos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (180, 'roberto11@example.com', '0123-4567', 'Designer de Jogos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (181, 'vitoria@example.com', '1234-5678', 'Estudante de Ciências Sociais');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (182, 'rafael4@example.com', '2345-6789', 'Psicopedagoga');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (183, 'caroline@example.com', '3456-7890', 'Agrônoma');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (184, 'marcio6@example.com', '4567-8901', 'Engenheiro Agrônomo');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (185, 'bianca@example.com', '5678-9012', 'Recepcionista');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (186, 'tania2@example.com', '6789-0123', 'Estudante de Nutrição');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (187, 'alberto@example.com', '7890-1234', 'Consultor de Negócios');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (188, 'sergio@example.com', '8901-2345', 'Arquiteto');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (189, 'katia@example.com', '9012-3456', 'Coordenadora de RH');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (190, 'rafael5@example.com', '0123-4567', 'Diretor Financeiro');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (191, 'laura@example.com', '1234-5678', 'Gerente de Projetos');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (192, 'marcio7@example.com', '2345-6789', 'Assistente de Pesquisa');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (193, 'roberto12@example.com', '3456-7890', 'Engenheiro Eletricista');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (194, 'renata2@example.com', '4567-8901', 'Diretora de Operações');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (195, 'joao2@example.com', '5678-9012', 'Especialista em Logística');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (196, 'natasha@example.com', '6789-0123', 'Psicóloga');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (197, 'marcio8@example.com', '7890-1234', 'Técnico em Telecomunicações');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (198, 'fernando4@example.com', '8901-2345', 'Analista de Marketing Digital');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (199, 'cristina@example.com', '9012-3456', 'Engenheira de Produção');
INSERT INTO contato (idContato, Email, Telefone, Ocupacao) VALUES (200, 'andre6@example.com', '0123-4567', 'Professor de História');

-- avaliação final de contatos --> tms de resposta maximo 0.016s (aceitavel)

-- teste de avaliação de balanceamento de carga
-- insert na tabela contato 
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (1, 'user1', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (2, 'user2', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (3, 'user3', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (4, 'user4', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (5, 'user5', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (6, 'user6', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (7, 'user7', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (8, 'user8', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (9, 'user9', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (10, 'user10', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (11, 'user11', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (12, 'user12', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (13, 'user13', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (14, 'user14', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (15, 'user15', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (16, 'user16', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (17, 'user17', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (18, 'user18', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (19, 'user19', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (20, 'user20', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (21, 'user21', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (22, 'user22', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (23, 'user23', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (24, 'user24', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (25, 'user25', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (26, 'user26', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (27, 'user27', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (28, 'user28', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (29, 'user29', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (30, 'user30', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (31, 'user31', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (32, 'user32', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (33, 'user33', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (34, 'user34', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (35, 'user35', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (36, 'user36', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (37, 'user37', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (38, 'user38', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (39, 'user39', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (40, 'user40', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (41, 'user41', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (42, 'user42', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (43, 'user43', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (44, 'user44', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (45, 'user45', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (46, 'user46', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (47, 'user47', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (48, 'user48', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (49, 'user49', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (50, 'user50', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (51, 'user51', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (52, 'user52', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (53, 'user53', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (54, 'user54', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (55, 'user55', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (56, 'user56', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (57, 'user57', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (58, 'user58', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (59, 'user59', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (60, 'user60', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (61, 'user61', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (62, 'user62', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (63, 'user63', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (64, 'user64', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (65, 'user65', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (66, 'user66', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (67, 'user67', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (68, 'user68', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (69, 'user69', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (70, 'user70', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (71, 'user71', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (72, 'user72', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (73, 'user73', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (74, 'user74', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (75, 'user75', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (76, 'user76', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (77, 'user77', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (78, 'user78', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (79, 'user79', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (80, 'user80', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (81, 'user81', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (82, 'user82', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (83, 'user83', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (84, 'user84', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (85, 'user85', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (86, 'user86', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (87, 'user87', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (88, 'user88', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (89, 'user89', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (90, 'user90', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (91, 'user91', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (92, 'user92', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (93, 'user93', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (94, 'user94', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (95, 'user95', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (96, 'user96', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (97, 'user97', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (98, 'user98', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (99, 'user99', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (100, 'user100', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (101, 'user101', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (102, 'user102', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (103, 'user103', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (104, 'user104', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (105, 'user105', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (106, 'user106', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (107, 'user107', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (108, 'user108', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (109, 'user109', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (110, 'user110', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (111, 'user111', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (112, 'user112', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (113, 'user113', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (114, 'user114', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (115, 'user115', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (116, 'user116', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (117, 'user117', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (118, 'user118', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (119, 'user119', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (120, 'user120', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (121, 'user121', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (122, 'user122', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (123, 'user123', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (124, 'user124', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (125, 'user125', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (126, 'user126', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (127, 'user127', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (128, 'user128', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (129, 'user129', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (130, 'user130', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (131, 'user131', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (132, 'user132', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (133, 'user133', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (134, 'user134', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (135, 'user135', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (136, 'user136', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (137, 'user137', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (138, 'user138', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (139, 'user139', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (140, 'user140', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (141, 'user141', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (142, 'user142', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (143, 'user143', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (144, 'user144', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (145, 'user145', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (146, 'user146', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (147, 'user147', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (148, 'user148', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (149, 'user149', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (150, 'user150', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (151, 'user151', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (152, 'user152', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (153, 'user153', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (154, 'user154', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (155, 'user155', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (156, 'user156', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (157, 'user157', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (158, 'user158', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (159, 'user159', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (160, 'user160', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (161, 'user161', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (162, 'user162', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (163, 'user163', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (164, 'user164', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (165, 'user165', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (166, 'user166', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (167, 'user167', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (168, 'user168', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (169, 'user169', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (170, 'user170', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (171, 'user171', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (172, 'user172', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (173, 'user173', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (174, 'user174', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (175, 'user175', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (176, 'user176', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (177, 'user177', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (178, 'user178', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (179, 'user179', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (180, 'user180', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (181, 'user181', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (182, 'user182', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (183, 'user183', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (184, 'user184', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (185, 'user185', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (186, 'user186', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (187, 'user187', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (188, 'user188', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (189, 'user189', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (190, 'user190', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (191, 'user191', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (192, 'user192', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (193, 'user193', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (194, 'user194', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (195, 'user195', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (196, 'user196', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (197, 'user197', 'responsavel');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (198, 'user198', 'comum');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (199, 'user199', 'coordenador');
INSERT INTO usuario (IdUsuario, Usuario, tipo) VALUES (200, 'user200', 'responsavel');

-- avaliação final de usuario --> tms de resposta maximo 0.016s (aceitavel)


select * from usuario;

-- teste de avaliação de balanceamento de carga
-- insert na tabela centro de custo

INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (1, 'Centro de Custo 1', 'CC1', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (2, 'Centro de Custo 2', 'CC2', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (3, 'Centro de Custo 3', 'CC3', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (4, 'Centro de Custo 4', 'CC4', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (5, 'Centro de Custo 5', 'CC5', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (6, 'Centro de Custo 6', 'CC6', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (7, 'Centro de Custo 7', 'CC7', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (8, 'Centro de Custo 8', 'CC8', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (9, 'Centro de Custo 9', 'CC9', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (10, 'Centro de Custo 10', 'CC10', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (11, 'Centro de Custo 11', 'CC11', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (12, 'Centro de Custo 12', 'CC12', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (13, 'Centro de Custo 13', 'CC13', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (14, 'Centro de Custo 14', 'CC14', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (15, 'Centro de Custo 15', 'CC15', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (16, 'Centro de Custo 16', 'CC16', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (17, 'Centro de Custo 17', 'CC17', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (18, 'Centro de Custo 18', 'CC18', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (19, 'Centro de Custo 19', 'CC19', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (20, 'Centro de Custo 20', 'CC20', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (21, 'Centro de Custo 21', 'CC21', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (22, 'Centro de Custo 22', 'CC22', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (23, 'Centro de Custo 23', 'CC23', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (24, 'Centro de Custo 24', 'CC24', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (25, 'Centro de Custo 25', 'CC25', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (26, 'Centro de Custo 26', 'CC26', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (27, 'Centro de Custo 27', 'CC27', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (28, 'Centro de Custo 28', 'CC28', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (29, 'Centro de Custo 29', 'CC29', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (30, 'Centro de Custo 30', 'CC30', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (31, 'Centro de Custo 31', 'CC31', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (32, 'Centro de Custo 32', 'CC32', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (33, 'Centro de Custo 33', 'CC33', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (34, 'Centro de Custo 34', 'CC34', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (35, 'Centro de Custo 35', 'CC35', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (36, 'Centro de Custo 36', 'CC36', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (37, 'Centro de Custo 37', 'CC37', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (38, 'Centro de Custo 38', 'CC38', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (39, 'Centro de Custo 39', 'CC39', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (40, 'Centro de Custo 40', 'CC40', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (41, 'Centro de Custo 41', 'CC41', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (42, 'Centro de Custo 42', 'CC42', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (43, 'Centro de Custo 43', 'CC43', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (44, 'Centro de Custo 44', 'CC44', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (45, 'Centro de Custo 45', 'CC45', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (46, 'Centro de Custo 46', 'CC46', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (47, 'Centro de Custo 47', 'CC47', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (48, 'Centro de Custo 48', 'CC48', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (49, 'Centro de Custo 49', 'CC49', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (50, 'Centro de Custo 50', 'CC50', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (51, 'Centro de Custo 51', 'CC51', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (52, 'Centro de Custo 52', 'CC52', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (53, 'Centro de Custo 53', 'CC53', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (54, 'Centro de Custo 54', 'CC54', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (55, 'Centro de Custo 55', 'CC55', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (56, 'Centro de Custo 56', 'CC56', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (57, 'Centro de Custo 57', 'CC57', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (58, 'Centro de Custo 58', 'CC58', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (59, 'Centro de Custo 59', 'CC59', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (60, 'Centro de Custo 60', 'CC60', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (61, 'Centro de Custo 61', 'CC61', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (62, 'Centro de Custo 62', 'CC62', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (63, 'Centro de Custo 63', 'CC63', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (64, 'Centro de Custo 64', 'CC64', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (65, 'Centro de Custo 65', 'CC65', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (66, 'Centro de Custo 66', 'CC66', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (67, 'Centro de Custo 67', 'CC67', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (68, 'Centro de Custo 68', 'CC68', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (69, 'Centro de Custo 69', 'CC69', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (70, 'Centro de Custo 70', 'CC70', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (71, 'Centro de Custo 71', 'CC71', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (72, 'Centro de Custo 72', 'CC72', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (73, 'Centro de Custo 73', 'CC73', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (74, 'Centro de Custo 74', 'CC74', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (75, 'Centro de Custo 75', 'CC75', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (76, 'Centro de Custo 76', 'CC76', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (77, 'Centro de Custo 77', 'CC77', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (78, 'Centro de Custo 78', 'CC78', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (79, 'Centro de Custo 79', 'CC79', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (80, 'Centro de Custo 80', 'CC80', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (81, 'Centro de Custo 81', 'CC81', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (82, 'Centro de Custo 82', 'CC82', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (83, 'Centro de Custo 83', 'CC83', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (84, 'Centro de Custo 84', 'CC84', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (85, 'Centro de Custo 85', 'CC85', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (86, 'Centro de Custo 86', 'CC86', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (87, 'Centro de Custo 87', 'CC87', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (88, 'Centro de Custo 88', 'CC88', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (89, 'Centro de Custo 89', 'CC89', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (90, 'Centro de Custo 90', 'CC90', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (91, 'Centro de Custo 91', 'CC91', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (92, 'Centro de Custo 92', 'CC92', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (93, 'Centro de Custo 93', 'CC93', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (94, 'Centro de Custo 94', 'CC94', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (95, 'Centro de Custo 95', 'CC95', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (96, 'Centro de Custo 96', 'CC96', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (97, 'Centro de Custo 97', 'CC97', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (98, 'Centro de Custo 98', 'CC98', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (99, 'Centro de Custo 99', 'CC99', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (100, 'Centro de Custo 100', 'CC100', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (101, 'Centro de Custo 101', 'CC101', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (102, 'Centro de Custo 102', 'CC102', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (103, 'Centro de Custo 103', 'CC103', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (104, 'Centro de Custo 104', 'CC104', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (105, 'Centro de Custo 105', 'CC105', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (106, 'Centro de Custo 106', 'CC106', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (107, 'Centro de Custo 107', 'CC107', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (108, 'Centro de Custo 108', 'CC108', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (109, 'Centro de Custo 109', 'CC109', '2023-01-01', '2023-12-31');
INSERT INTO cdc (idCdc, nomeCdc, siglaCdc, dataIniCdc, dataFimCdc) VALUES (110, 'Centro de Custo 110', 'CC110', '2023-01-01', '2023-12-31');

select * from cdc;

-- avaliação final de cdc--> tms de resposta maximo 0.016s (aceitavel)

-- teste de avaliação de balanceamento de carga
-- insert na tabela descrição
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (1, 'Modelo 1', 'Marca A', 'Série 001', 'Descrição do item 1', 'Local 1');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (2, 'Modelo 2', 'Marca B', 'Série 002', 'Descrição do item 2', 'Local 2');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (3, 'Modelo 3', 'Marca C', 'Série 003', 'Descrição do item 3', 'Local 3');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (4, 'Modelo 4', 'Marca D', 'Série 004', 'Descrição do item 4', 'Local 4');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (5, 'Modelo 5', 'Marca E', 'Série 005', 'Descrição do item 5', 'Local 5');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (6, 'Modelo 6', 'Marca F', 'Série 006', 'Descrição do item 6', 'Local 6');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (7, 'Modelo 7', 'Marca G', 'Série 007', 'Descrição do item 7', 'Local 7');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (8, 'Modelo 8', 'Marca H', 'Série 008', 'Descrição do item 8', 'Local 8');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (9, 'Modelo 9', 'Marca I', 'Série 009', 'Descrição do item 9', 'Local 9');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (10, 'Modelo 10', 'Marca J', 'Série 010', 'Descrição do item 10', 'Local 10');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (11, 'Modelo 11', 'Marca K', 'Série 011', 'Descrição do item 11', 'Local 11');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (12, 'Modelo 12', 'Marca L', 'Série 012', 'Descrição do item 12', 'Local 12');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (13, 'Modelo 13', 'Marca M', 'Série 013', 'Descrição do item 13', 'Local 13');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (14, 'Modelo 14', 'Marca N', 'Série 014', 'Descrição do item 14', 'Local 14');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (15, 'Modelo 15', 'Marca O', 'Série 015', 'Descrição do item 15', 'Local 15');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (16, 'Modelo 16', 'Marca P', 'Série 016', 'Descrição do item 16', 'Local 16');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (17, 'Modelo 17', 'Marca Q', 'Série 017', 'Descrição do item 17', 'Local 17');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (18, 'Modelo 18', 'Marca R', 'Série 018', 'Descrição do item 18', 'Local 18');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (19, 'Modelo 19', 'Marca S', 'Série 019', 'Descrição do item 19', 'Local 19');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (20, 'Modelo 20', 'Marca T', 'Série 020', 'Descrição do item 20', 'Local 20');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (21, 'Modelo 21', 'Marca U', 'Série 021', 'Descrição do item 21', 'Local 21');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (22, 'Modelo 22', 'Marca V', 'Série 022', 'Descrição do item 22', 'Local 22');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (23, 'Modelo 23', 'Marca W', 'Série 023', 'Descrição do item 23', 'Local 23');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (24, 'Modelo 24', 'Marca X', 'Série 024', 'Descrição do item 24', 'Local 24');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (25, 'Modelo 25', 'Marca Y', 'Série 025', 'Descrição do item 25', 'Local 25');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (26, 'Modelo 26', 'Marca Z', 'Série 026', 'Descrição do item 26', 'Local 26');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (27, 'Modelo 27', 'Marca AA', 'Série 027', 'Descrição do item 27', 'Local 27');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (28, 'Modelo 28', 'Marca AB', 'Série 028', 'Descrição do item 28', 'Local 28');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (29, 'Modelo 29', 'Marca AC', 'Série 029', 'Descrição do item 29', 'Local 29');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (30, 'Modelo 30', 'Marca AD', 'Série 030', 'Descrição do item 30', 'Local 30');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (31, 'Modelo 31', 'Marca AE', 'Série 031', 'Descrição do item 31', 'Local 31');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (32, 'Modelo 32', 'Marca AF', 'Série 032', 'Descrição do item 32', 'Local 32');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (33, 'Modelo 33', 'Marca AG', 'Série 033', 'Descrição do item 33', 'Local 33');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (34, 'Modelo 34', 'Marca AH', 'Série 034', 'Descrição do item 34', 'Local 34');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (35, 'Modelo 35', 'Marca AI', 'Série 035', 'Descrição do item 35', 'Local 35');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (36, 'Modelo 36', 'Marca AJ', 'Série 036', 'Descrição do item 36', 'Local 36');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (37, 'Modelo 37', 'Marca AK', 'Série 037', 'Descrição do item 37', 'Local 37');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (38, 'Modelo 38', 'Marca AL', 'Série 038', 'Descrição do item 38', 'Local 38');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (39, 'Modelo 39', 'Marca AM', 'Série 039', 'Descrição do item 39', 'Local 39');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (40, 'Modelo 40', 'Marca AN', 'Série 040', 'Descrição do item 40', 'Local 40');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (41, 'Modelo 41', 'Marca AO', 'Série 041', 'Descrição do item 41', 'Local 41');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (42, 'Modelo 42', 'Marca AP', 'Série 042', 'Descrição do item 42', 'Local 42');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (43, 'Modelo 43', 'Marca AQ', 'Série 043', 'Descrição do item 43', 'Local 43');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (44, 'Modelo 44', 'Marca AR', 'Série 044', 'Descrição do item 44', 'Local 44');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (45, 'Modelo 45', 'Marca AS', 'Série 045', 'Descrição do item 45', 'Local 45');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (46, 'Modelo 46', 'Marca AT', 'Série 046', 'Descrição do item 46', 'Local 46');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (47, 'Modelo 47', 'Marca AU', 'Série 047', 'Descrição do item 47', 'Local 47');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (48, 'Modelo 48', 'Marca AV', 'Série 048', 'Descrição do item 48', 'Local 48');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (49, 'Modelo 49', 'Marca AW', 'Série 049', 'Descrição do item 49', 'Local 49');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (50, 'Modelo 50', 'Marca AX', 'Série 050', 'Descrição do item 50', 'Local 50');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (51, 'Modelo 51', 'Marca AY', 'Série 051', 'Descrição do item 51', 'Local 51');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (52, 'Modelo 52', 'Marca AZ', 'Série 052', 'Descrição do item 52', 'Local 52');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (53, 'Modelo 53', 'Marca BA', 'Série 053', 'Descrição do item 53', 'Local 53');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (54, 'Modelo 54', 'Marca BB', 'Série 054', 'Descrição do item 54', 'Local 54');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (55, 'Modelo 55', 'Marca BC', 'Série 055', 'Descrição do item 55', 'Local 55');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (56, 'Modelo 56', 'Marca BD', 'Série 056', 'Descrição do item 56', 'Local 56');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (57, 'Modelo 57', 'Marca BE', 'Série 057', 'Descrição do item 57', 'Local 57');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (58, 'Modelo 58', 'Marca BF', 'Série 058', 'Descrição do item 58', 'Local 58');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (59, 'Modelo 59', 'Marca BG', 'Série 059', 'Descrição do item 59', 'Local 59');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (60, 'Modelo 60', 'Marca BH', 'Série 060', 'Descrição do item 60', 'Local 60');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (61, 'Modelo 61', 'Marca BI', 'Série 061', 'Descrição do item 61', 'Local 61');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (62, 'Modelo 62', 'Marca BJ', 'Série 062', 'Descrição do item 62', 'Local 62');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (63, 'Modelo 63', 'Marca BK', 'Série 063', 'Descrição do item 63', 'Local 63');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (64, 'Modelo 64', 'Marca BL', 'Série 064', 'Descrição do item 64', 'Local 64');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (65, 'Modelo 65', 'Marca BM', 'Série 065', 'Descrição do item 65', 'Local 65');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (66, 'Modelo 66', 'Marca BN', 'Série 066', 'Descrição do item 66', 'Local 66');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (67, 'Modelo 67', 'Marca BO', 'Série 067', 'Descrição do item 67', 'Local 67');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (68, 'Modelo 68', 'Marca BP', 'Série 068', 'Descrição do item 68', 'Local 68');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (69, 'Modelo 69', 'Marca BQ', 'Série 069', 'Descrição do item 69', 'Local 69');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (70, 'Modelo 70', 'Marca BR', 'Série 070', 'Descrição do item 70', 'Local 70');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (71, 'Modelo 71', 'Marca BS', 'Série 071', 'Descrição do item 71', 'Local 71');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (72, 'Modelo 72', 'Marca BT', 'Série 072', 'Descrição do item 72', 'Local 72');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (73, 'Modelo 73', 'Marca BU', 'Série 073', 'Descrição do item 73', 'Local 73');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (74, 'Modelo 74', 'Marca BV', 'Série 074', 'Descrição do item 74', 'Local 74');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (75, 'Modelo 75', 'Marca BW', 'Série 075', 'Descrição do item 75', 'Local 75');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (76, 'Modelo 76', 'Marca BX', 'Série 076', 'Descrição do item 76', 'Local 76');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (77, 'Modelo 77', 'Marca BY', 'Série 077', 'Descrição do item 77', 'Local 77');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (78, 'Modelo 78', 'Marca BZ', 'Série 078', 'Descrição do item 78', 'Local 78');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (79, 'Modelo 79', 'Marca CA', 'Série 079', 'Descrição do item 79', 'Local 79');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (80, 'Modelo 80', 'Marca CB', 'Série 080', 'Descrição do item 80', 'Local 80');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (81, 'Modelo 81', 'Marca CC', 'Série 081', 'Descrição do item 81', 'Local 81');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (82, 'Modelo 82', 'Marca CD', 'Série 082', 'Descrição do item 82', 'Local 82');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (83, 'Modelo 83', 'Marca CE', 'Série 083', 'Descrição do item 83', 'Local 83');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (84, 'Modelo 84', 'Marca CF', 'Série 084', 'Descrição do item 84', 'Local 84');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (85, 'Modelo 85', 'Marca CG', 'Série 085', 'Descrição do item 85', 'Local 85');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (86, 'Modelo 86', 'Marca CH', 'Série 086', 'Descrição do item 86', 'Local 86');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (87, 'Modelo 87', 'Marca CI', 'Série 087', 'Descrição do item 87', 'Local 87');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (88, 'Modelo 88', 'Marca CJ', 'Série 088', 'Descrição do item 88', 'Local 88');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (89, 'Modelo 89', 'Marca CK', 'Série 089', 'Descrição do item 89', 'Local 89');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (90, 'Modelo 90', 'Marca CL', 'Série 090', 'Descrição do item 90', 'Local 90');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (91, 'Modelo 91', 'Marca CM', 'Série 091', 'Descrição do item 91', 'Local 91');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (92, 'Modelo 92', 'Marca CN', 'Série 092', 'Descrição do item 92', 'Local 92');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (93, 'Modelo 93', 'Marca CO', 'Série 093', 'Descrição do item 93', 'Local 93');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (94, 'Modelo 94', 'Marca CP', 'Série 094', 'Descrição do item 94', 'Local 94');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (95, 'Modelo 95', 'Marca CQ', 'Série 095', 'Descrição do item 95', 'Local 95');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (96, 'Modelo 96', 'Marca CR', 'Série 096', 'Descrição do item 96', 'Local 96');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (97, 'Modelo 97', 'Marca CS', 'Série 097', 'Descrição do item 97', 'Local 97');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (98, 'Modelo 98', 'Marca CT', 'Série 098', 'Descrição do item 98', 'Local 98');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (99, 'Modelo 99', 'Marca CU', 'Série 099', 'Descrição do item 99', 'Local 99');
INSERT INTO descricao (idDescr, modeloPtr, marcaPtr, seriePtr, descriPtr, locPtr) VALUES (100, 'Modelo 100', 'Marca CV', 'Série 100', 'Descrição do item 100', 'Local 100');

select * from descricao;

-- avaliação final de descricao --> tms de resposta maximo 0.016s (aceitavel)

-- ---- AVALICAÇÃO DA TABELA PRINCIPAL E TESTE MONITORADO
-- --> inserção 100 valores na tabela patrimonios em conjunto com todas as tabelas anteriores criadas
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (1, 1, 1, 1);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (2, 2, 2, 1);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (3, 3, 3, 2);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (4, 4, 1, 2);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (5, 5, 2, 3);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (6, 6, 3, 3);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (7, 7, 1, 4);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (8, 8, 2, 4);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (9, 9, 3, 5);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (10, 10, 1, 5);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (11, 11, 2, 6);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (12, 12, 3, 6);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (13, 13, 1, 7);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (14, 14, 2, 7);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (15, 15, 3, 8);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (16, 16, 1, 8);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (17, 17, 2, 9);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (18, 18, 3, 9);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (19, 19, 1, 10);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (20, 20, 2, 10);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (21, 21, 3, 11);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (22, 22, 1, 11);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (23, 23, 2, 12);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (24, 24, 3, 12);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (25, 25, 1, 13);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (26, 26, 2, 13);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (27, 27, 3, 14);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (28, 28, 1, 14);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (29, 29, 2, 15);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (30, 30, 3, 15);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (31, 31, 1, 16);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (32, 32, 2, 16);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (33, 33, 3, 17);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (34, 34, 1, 17);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (35, 35, 2, 18);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (36, 36, 3, 18);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (37, 37, 1, 19);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (38, 38, 2, 19);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (39, 39, 3, 20);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (40, 40, 1, 20);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (41, 41, 2, 21);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (42, 42, 3, 21);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (43, 43, 1, 22);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (44, 44, 2, 22);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (45, 45, 3, 23);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (46, 46, 1, 23);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (47, 47, 2, 24);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (48, 48, 3, 24);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (49, 49, 1, 25);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (50, 50, 2, 25);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (51, 51, 3, 26);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (52, 52, 1, 26);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (53, 53, 2, 27);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (54, 54, 3, 27);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (55, 55, 1, 28);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (56, 56, 2, 28);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (57, 57, 3, 29);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (58, 58, 1, 29);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (59, 59, 2, 30);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (60, 60, 3, 30);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (61, 61, 1, 31);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (62, 62, 2, 31);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (63, 63, 3, 32);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (64, 64, 1, 32);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (65, 65, 2, 33);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (66, 66, 3, 33);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (67, 67, 1, 34);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (68, 68, 2, 34);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (69, 69, 3, 35);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (70, 70, 1, 35);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (71, 71, 2, 36);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (72, 72, 3, 36);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (73, 73, 1, 37);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (74, 74, 2, 37);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (75, 75, 3, 38);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (76, 76, 1, 38);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (77, 77, 2, 39);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (78, 78, 3, 39);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (79, 79, 1, 40);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (80, 80, 2, 40);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (81, 81, 3, 41);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (82, 82, 1, 41);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (83, 83, 2, 42);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (84, 84, 3, 42);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (85, 85, 1, 43);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (86, 86, 2, 43);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (87, 87, 3, 44);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (88, 88, 1, 44);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (89, 89, 2, 45);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (90, 90, 3, 45);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (91, 91, 1, 46);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (92, 92, 2, 46);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (93, 93, 3, 47);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (94, 94, 1, 47);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (95, 95, 2, 48);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (96, 96, 3, 48);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (97, 97, 1, 49);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (98, 98, 2, 49);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (99, 99, 3, 50);
INSERT INTO patrimonio (nPtr, idDescr, idUsuario, idCdc) VALUES (100, 100, 1, 50);

select * from patrimonio;
-- Exemplo de uma consulta complexa para medir o tms de busca por coluna de cada tabela vinculada
SELECT 
    p.idPtr,
    p.nPtr,
    d.modeloPtr,
    d.marcaPtr,
    d.seriePtr,
    d.descriPtr,
    d.locPtr,
    u.Usuario,
    u.tipo,
    c.nomeCdc,
    c.siglaCdc,
    c.dataIniCdc,
    c.dataFimCdc
FROM 
    patrimonio p
JOIN 
    descricao d ON p.idDescr = d.idDescr
JOIN 
    usuario u ON p.idUsuario = u.idUsuario
JOIN 
    cdc c ON p.idCdc = c.idCdc;
    
-- exibir status de desempenho do banco de dados erros medição de bytes etc
SHOW STATUS;

-- AVALIAR VARIAVEIS PARA CRIAÇÃO DE TRIGGERS ( GATILHOS )
SHOW VARIABLES LIKE 'event_scheduler';
-- tamanho de ocupação dos dados em disco no banco
 SHOW TABLE STATUS FROM ltadcrm;
 
 -- --> acomapanhamento aproximado 
 SELECT 
    table_name AS patrimonio,
    table_rows AS '1000',
    ROUND(data_length / 1024 / 1024, 3) AS "Tamanho de Dados (MB)",
    ROUND(index_length / 1024 / 1024, 3) AS "Tamanho de Índices (MB)",
    ROUND((data_length + index_length) / 1024 / 1024, 3) AS "Tamanho Total (MB)"
FROM 
    information_schema.TABLES 
WHERE 
    table_schema = 'ltadcrm'
ORDER BY 
    (data_length + index_length) DESC;
    
-- Uso de CPU pelo MYSQL
SHOW VARIABLES LIKE 'innodb_thread_concurrency';
SHOW STATUS LIKE 'Threads%';