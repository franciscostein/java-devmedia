CREATE TABLE DEPARTAMENTOS (
  ID_DEPARTAMENTO INT NOT NULL IDENTITY,
  DEPARTAMENTO VARCHAR(255) NOT NULL
);

CREATE TABLE CARGOS(
  ID_CARGO INT NOT NULL AUTO_INCREMENT,
  CARGO VARCHAR(255) NOT NULL,
  ID_DEPARTAMENTO INT NOT NULL,
    PRIMARY KEY (ID_CARGO),
    CONSTRAINT FK_DEPARTAMENTOS FOREIGN KEY (ID_DEPARTAMENTO) REFERENCES DEPARTAMENTOS(ID_DEPARTAMENTO) ON DELETE CASCADE
);

CREATE TABLE ENDERECOS(
  ID_ENDERECO INT NOT NULL IDENTITY,
  LOGRADOURO VARCHAR(255) NOT NULL,
  NUMERO INT NOT NULL ,
  COMPLEMENTO VARCHAR(255),
  CIDADE VARCHAR(255) NOT NULL,
  BAIRRO VARCHAR(255) NOT NULL,
  ESTADO VARCHAR(255) NOT NULL
);

CREATE TABLE FUNCIONARIOS(
  ID_FUNCIONARIO INT NOT NULL IDENTITY,
  ID_CARGO INT NOT NULL,
  ID_ENDERECO INT NOT NULL,
  NOME VARCHAR(255) NOT NULL,
  SALARIO DOUBLE NOT NULL,
  DATA_ENTRADA DATE NOT NULL,
  DATA_SAIDA DATE,
    CONSTRAINT FK_CARGOS FOREIGN KEY (ID_CARGO) REFERENCES CARGOS(ID_CARGO),
    CONSTRAINT FK_ENDERECOS FOREIGN KEY (ID_ENDERECO) REFERENCES ENDERECOS(ID_ENDERECO)
);