-- Criar um banco de dados VagasOnline
create database VagasOnline;

-- Abrir conexao com o banco
use VagasOnline;

-- Criar tabela tipo_vaga
create table tipo_vaga(
	id int auto_increment primary key,
	nome varchar(28) not null,
	preco int not null,
	id_estacionamento int not null,
	data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=MyISAM;


-- Criar tabela perfil
create table perfil(
	id int primary key,
	nome varchar(28) not null unique,
	data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=MyISAM;

INSERT INTO PERFIL (ID, NOME) VALUES (2, 'ADMINISTRADOR_ESTACIONAMENTO');
INSERT INTO PERFIL (ID, NOME) VALUES (3, 'FUNCIONARIO');
INSERT INTO PERFIL (ID, NOME) VALUES (4, 'CLIENTE');
COMMIT;

-- Criar tabela usuario
create table usuario(
	id int auto_increment primary key,
	nome varchar(100),
	cpf varchar(20) unique,
	email varchar(50) not null unique,
	login varchar(50) unique,
	senha varchar(100),
	rg varchar(20) unique,
	sexo varchar(1),
	id_perfil int(1) not null,
	id_estacionamento_func int(1) DEFAULT 0,
	status varchar(1) DEFAULT 'A' not null,
	data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=MyISAM;


-- Criando indices para campos de empregado
CREATE INDEX IDX_USU_EMAIL ON USUARIO (email);
CREATE INDEX IDX_USU_LOGIN ON USUARIO (login);
CREATE INDEX IDX_USU_SENHA ON USUARIO (senha);

-- Criar tabela pais
create table pais (
	id int auto_increment primary key,
	nome varchar(100) not null unique,
	data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=MyISAM;

-- Criar tabela estado 
create table estado (
	id int auto_increment primary key,
	nome varchar(100) not null unique,
	id_pais int not null,
	data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=MyISAM;

-- Criar tabela cidade
create table cidade (
	id int auto_increment primary key,
	nome varchar(100) not null unique,
	id_estado int not null,
	data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=MyISAM;

-- Criar tabela bairro
create table bairro (
	id int auto_increment primary key,
	nome varchar(100) not null unique,
	id_cidade int not null,
	data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=MyISAM;

-- Criar tabela tipo_logradouro
create table tipo_logradouro (
	id int auto_increment primary key,
	nome varchar(100) not null unique,
	data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=MyISAM;

-- Criar tabela endereco
create table endereco (
	id int auto_increment,
	nome_logradouro varchar(100) not null, 
	numero int,
	cep varchar(10) not null,
	latitude varchar(50) not null,
	longitude varchar(50) not null,
	id_tipo_logradouro int not null,
	id_bairro int not null,
	id_cidade int not null,
	id_estado int not null ,
	id_pais int not null,
	data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (id, nome_logradouro, numero, cep, id_tipo_logradouro, id_bairro, id_cidade)
) ENGINE=MyISAM;

-- Criando indices para campos de endereco
CREATE INDEX IDX_END_NOME_LOG ON ENDERECO (nome_logradouro);
CREATE INDEX IDX_END_CEP ON ENDERECO (cep);
CREATE INDEX IDX_END_TIPO_LOGRADOURO ON ENDERECO (id_tipo_logradouro);
CREATE INDEX IDX_END_BAIRRO ON ENDERECO (id_bairro);
CREATE INDEX IDX_END_CIDADE ON ENDERECO (id_cidade);
CREATE INDEX IDX_END_ESTADO ON ENDERECO (id_estado);
CREATE INDEX IDX_END_PAIS ON ENDERECO (id_pais);

create table status (
	id int primary key,
	nome varchar(1) not null unique,
	data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=MyISAM;

INSERT INTO STATUS (ID, NOME) VALUES (0, 'PENDENTE');
INSERT INTO STATUS (ID, NOME) VALUES (1, 'EM_PROGRESSO');
INSERT INTO STATUS (ID, NOME) VALUES (2, 'ATIVO');
COMMIT;

create table vaga (
	id int auto_increment primary key,
	id_estacionamento int not null,
	id_tipo_vaga int not null,
	codigo varchar(10) not null unique,
	largura int not null,
	altura int not null,
	comprimento int not null,
	status varchar(1) not null DEFAULT 'D',
	data_locacao_vaga TIMESTAMP,
	data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=MyISAM;

create table estacionamento (
	id int auto_increment primary key,
	id_status int not null,
	id_endereco int not null,
	id_adm_estacionamento int not null,
	razao_social varchar(100) not null unique,
	nome_fantasia varchar(100) not null unique,
	cnpj varchar(20) not null unique,
	inscricao_municipal varchar(50) not null unique,
	inscricao_estadual varchar(50) not null unique,
	data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=MyISAM;

-- Criando indices para campos de estacionamento
CREATE INDEX IDX_EST_ENDERECO ON ESTACIONAMENTO (id_endereco);



-- CRIACAO TB TIPO_PAGAMENTO
create table tipo_pagamento (
	id int auto_increment primary key,
	nome varchar(50) not null unique	
); 

-- CRIAÇÃO DA ASSICIAÇÃO ESTACIONAMENTO COM TIPO_PAGAMENTO
create table estacionamento_tp_pagamento (
	id_estacionamento int not null,
	id_tipo_pagamento int not null    
); 

ALTER TABLE estacionamento_tp_pagamento ADD CONSTRAINT pk_tab PRIMARY KEY (id_estacionamento, id_tipo_pagamento);

ALTER TABLE estacionamento_tp_pagamento ADD CONSTRAINT fk_tp_pagamento FOREIGN KEY(id_tipo_pagamento) REFERENCES tipo_pagamento (id);

ALTER TABLE estacionamento_tp_pagamento ADD CONSTRAINT fk_estacionamento FOREIGN KEY(id_estacionamento) REFERENCES estacionamento (id);


-- CRIACAO TB TIPO_PAGAMENTO
create table historico_aluguel (
	id int auto_increment primary key,
	tipo_vaga varchar(28) not null,
	modelo varchar(50) not null,
	placa varchar(10) not null,
	codigo_vaga varchar(10) not null,
	hora_entrada TIMESTAMP not null,
	hora_saida TIMESTAMP,
	valor_cobrado int,
	tipo_pagamento varchar(50)
);


-- CRIACAO DA TABELA DE RESPOSTAS DO USUARIO (USUARIO_RESPOSTAS)
create table usuario_resposta(
	id int auto_increment primary key,
	id_usuario int unique not null,
	resposta1 varchar(255) not null,
	resposta2 varchar(255) not null,
	resposta3 varchar(255) not null,
	resposta4 varchar(255) not null
);



