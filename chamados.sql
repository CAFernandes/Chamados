create database requisicao
go
use requisicao
go

CREATE TABLE usuario (
	id INT NOT NULL IDENTITY,
	nome VARCHAR(80) NOT NULL,
	nascimento DATE NOT NULL DEFAULT ('1111-11-11'),
	cpf VARCHAR(11) NOT NULL,
	email VARCHAR(100) NOT NULL,
	senha VARCHAR(100) NOT NULL DEFAULT (123456),
	permissao CHAR(1) NOT NULL DEFAULT ('C'),
	responsavel INT NULL DEFAULT NULL,
	status CHAR(1) NOT NULL DEFAULT ('a'),
	PRIMARY KEY (id)
)
INSERT INTO usuario (nome, nascimento, cpf, email, permissao) VALUES('Caio alberto Fernandes', '1996-10-21', '12345678910', 'caioalberto@tcc.com.br', 'G')

CREATE TABLE chamado (
	id INT IDENTITY NOT NULL,
	id_cliente INT NOT NULL,
	id_tecnico INT,
	titulo VARCHAR(30) NOT NULL,
	descricao VARCHAR(255) NOT NULL,
	data_cri DATE NOT NULL,
	hora_cri TIME NOT NULL,
	data_atualiza DATE,
	hora_atualiza TIME,
	resposta VARCHAR(255),
	status CHAR(1) NOT NULL DEFAULT('a'),
	PRIMARY KEY (id),
	FOREIGN KEY (id_cliente) REFERENCES usuario(id),
	FOREIGN KEY (id_tecnico) REFERENCES usuario(id),
	/*a = aguardando, e = análise, p = espera, r = resolvido*/
	CHECK(status = 'a' OR status = 'e' OR status = 'p' OR status = 'r')
)
INSERT INTO chamado(id_cliente, id_tecnico, titulo, descricao, data_cri, hora_cri)
VALUES(388, 385, 'qualquer coisa', 'só um teste', GETDATE(), CONVERT (time, GETDATE()))
CREATE TABLE feedback (
	id INT IDENTITY NOT NULL,
	id_cliente INT NOT NULL,
	id_requisicao INT NOT NULL,
	nota INT NOT NULL,
	comentario VARCHAR(255) NOT NULL
)
SELECT id, nome, email, permissao FROM usuario WHERE email = 'caioalberto@tcc.com.br' AND senha = '123456'

SELECT COUNT(id) as pizza FROM chamado c WHERE c.status = 'a'
UNION
SELECT COUNT(id) as pizza FROM chamado c WHERE c.status = 'r'
UNION
SELECT COUNT(id) as pizza FROM chamado c WHERE c.status = 'p'
UNION
SELECT COUNT(id) as pizza FROM chamado c WHERE c.status = 'e'



'dbo.chamado