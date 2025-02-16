CREATE DATABASE estoque;

CREATE TABLE produto(
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    descricao VARCHAR(50),
    validade DATE,
    cadastro DATE,
    preco DECIMAL NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);