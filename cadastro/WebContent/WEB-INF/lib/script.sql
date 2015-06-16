DROP TABLE IF EXISTS usuario

CREATE TABLE usuario (
    nome varchar(255) DEFAULT NULL,
    login varchar(50) DEFAULT NULL,
    hash varchar(50) DEFAULT NULL,
    salt varchar(50) DEFAULT NULL,
    PRIMARY KEY (login)
 );