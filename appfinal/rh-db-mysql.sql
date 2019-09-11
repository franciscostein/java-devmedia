SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `rh-db` ;
CREATE SCHEMA IF NOT EXISTS `rh-db` DEFAULT CHARACTER SET latin1 ;
USE `rh-db` ;

-- -----------------------------------------------------
-- Table `rh-db`.`departamentos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rh-db`.`departamentos` ;

CREATE  TABLE IF NOT EXISTS `rh-db`.`departamentos` (
  `ID_DEPARTAMENTO` INT(11) NOT NULL AUTO_INCREMENT ,
  `DEPARTAMENTO` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID_DEPARTAMENTO`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;

CREATE UNIQUE INDEX `DEPARTAMENTO_UNIQUE` ON `rh-db`.`departamentos` (`DEPARTAMENTO` ASC) ;

-- insert
INSERT INTO `departamentos` (`ID_DEPARTAMENTO`,`DEPARTAMENTO`) VALUES (1,'Fiscal');
INSERT INTO `departamentos` (`ID_DEPARTAMENTO`,`DEPARTAMENTO`) VALUES (2,'Tec. Informação');


-- -----------------------------------------------------
-- Table `rh-db`.`cargos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rh-db`.`cargos` ;

CREATE  TABLE IF NOT EXISTS `rh-db`.`cargos` (
  `ID_CARGO` INT(11) NOT NULL AUTO_INCREMENT ,
  `CARGO` VARCHAR(80) NOT NULL ,
  `ID_DEPARTAMENTO` INT(11) NOT NULL ,
  PRIMARY KEY (`ID_CARGO`, `CARGO`) ,
  CONSTRAINT `FK_ID_DEPARTAMENTO`
    FOREIGN KEY (`ID_DEPARTAMENTO` )
    REFERENCES `rh-db`.`departamentos` (`ID_DEPARTAMENTO` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;

CREATE UNIQUE INDEX `CARGO_UNIQUE` ON `rh-db`.`cargos` (`CARGO` ASC) ;

CREATE INDEX `FK_ID_DEPARTAMENTO_idx` ON `rh-db`.`cargos` (`ID_DEPARTAMENTO` ASC) ;

-- insert
INSERT INTO `cargos` (`ID_CARGO`,`CARGO`,`ID_DEPARTAMENTO`) VALUES (1,'Analista Fiscal I',1);
INSERT INTO `cargos` (`ID_CARGO`,`CARGO`,`ID_DEPARTAMENTO`) VALUES (4,'Analista Fiscal II',1);
INSERT INTO `cargos` (`ID_CARGO`,`CARGO`,`ID_DEPARTAMENTO`) VALUES (2,'Programador Delphi',2);
INSERT INTO `cargos` (`ID_CARGO`,`CARGO`,`ID_DEPARTAMENTO`) VALUES (3,'Programador PHP',2);


-- -----------------------------------------------------
-- Table `rh-db`.`enderecos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rh-db`.`enderecos` ;

CREATE  TABLE IF NOT EXISTS `rh-db`.`enderecos` (
  `ID_ENDERECO` INT(11) NOT NULL AUTO_INCREMENT ,
  `LOGRADOURO` VARCHAR(80) NOT NULL ,
  `NUMERO` INT(4) NOT NULL ,
  `COMPLEMENTO` VARCHAR(12) NULL DEFAULT NULL ,
  `BAIRRO` VARCHAR(45) NOT NULL ,
  `CIDADE` VARCHAR(45) NOT NULL ,
  `ESTADO` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID_ENDERECO`) )
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;

-- insert
INSERT INTO `enderecos` (`ID_ENDERECO`,`LOGRADOURO`,`NUMERO`,`COMPLEMENTO`,`BAIRRO`,`CIDADE`,`ESTADO`) VALUES (1,'Rua das Oliveiras',363,'AP. 103','Centro','Porto Alegre','Rio Grande do Sul');
INSERT INTO `enderecos` (`ID_ENDERECO`,`LOGRADOURO`,`NUMERO`,`COMPLEMENTO`,`BAIRRO`,`CIDADE`,`ESTADO`) VALUES (2,'Rua das Orquideas',103,'','Floresta','Porto Alegre','Rio Grande do Sul');
INSERT INTO `enderecos` (`ID_ENDERECO`,`LOGRADOURO`,`NUMERO`,`COMPLEMENTO`,`BAIRRO`,`CIDADE`,`ESTADO`) VALUES (3,'Av. das Palmeiras',56,'Fundos','Jardins','São Paulo','São Paulo');
INSERT INTO `enderecos` (`ID_ENDERECO`,`LOGRADOURO`,`NUMERO`,`COMPLEMENTO`,`BAIRRO`,`CIDADE`,`ESTADO`) VALUES (4,'Av. Riachuelo',963,'AP. 105','Botanica','Florianopolis','Santa Catarina');
INSERT INTO `enderecos` (`ID_ENDERECO`,`LOGRADOURO`,`NUMERO`,`COMPLEMENTO`,`BAIRRO`,`CIDADE`,`ESTADO`) VALUES (5,'Rua da Esperança',202,'','Centro','Rio de Janeiro','Rio de Janeiro');


-- -----------------------------------------------------
-- Table `rh-db`.`funcionarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rh-db`.`funcionarios` ;

CREATE  TABLE IF NOT EXISTS `rh-db`.`funcionarios` (
  `ID_FUNCIONARIO` INT(11) NOT NULL AUTO_INCREMENT ,
  `NOME` VARCHAR(80) NOT NULL ,
  `DATA_ENTRADA` DATE NOT NULL ,
  `DATA_SAIDA` DATE NULL DEFAULT NULL ,
  `SALARIO` DOUBLE(8,2) NOT NULL ,
  `ID_CARGO` INT(11) NOT NULL ,
  `ID_ENDERECO` INT(11) NOT NULL ,
  PRIMARY KEY (`ID_FUNCIONARIO`) ,
  CONSTRAINT `FK_ID_CARGO`
    FOREIGN KEY (`ID_CARGO` )
    REFERENCES `rh-db`.`cargos` (`ID_CARGO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ID_ENDERECO`
    FOREIGN KEY (`ID_ENDERECO` )
    REFERENCES `rh-db`.`enderecos` (`ID_ENDERECO` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;

CREATE UNIQUE INDEX `NOME_UNIQUE` ON `rh-db`.`funcionarios` (`NOME` ASC) ;

CREATE INDEX `FK_ID_CARGO_idx` ON `rh-db`.`funcionarios` (`ID_CARGO` ASC) ;

CREATE INDEX `FK_ID_ENDERECO_idx` ON `rh-db`.`funcionarios` (`ID_ENDERECO` ASC) ;

-- insert
INSERT INTO `funcionarios` (`ID_FUNCIONARIO`,`NOME`,`DATA_ENTRADA`,`DATA_SAIDA`,`SALARIO`,`ID_CARGO`,`ID_ENDERECO`) VALUES (1,'Aline da Silva','2012-03-05',NULL,1800.00,1,1);
INSERT INTO `funcionarios` (`ID_FUNCIONARIO`,`NOME`,`DATA_ENTRADA`,`DATA_SAIDA`,`SALARIO`,`ID_CARGO`,`ID_ENDERECO`) VALUES (2,'Bianca Rios','2013-08-26','2015-03-28',1900.00,1,2);
INSERT INTO `funcionarios` (`ID_FUNCIONARIO`,`NOME`,`DATA_ENTRADA`,`DATA_SAIDA`,`SALARIO`,`ID_CARGO`,`ID_ENDERECO`) VALUES (3,'Carlos Andre Silveira','2014-02-28',NULL,2500.00,3,3);
INSERT INTO `funcionarios` (`ID_FUNCIONARIO`,`NOME`,`DATA_ENTRADA`,`DATA_SAIDA`,`SALARIO`,`ID_CARGO`,`ID_ENDERECO`) VALUES (4,'Rodrigo de Souza','2013-08-22',NULL,2800.00,2,4);
INSERT INTO `funcionarios` (`ID_FUNCIONARIO`,`NOME`,`DATA_ENTRADA`,`DATA_SAIDA`,`SALARIO`,`ID_CARGO`,`ID_ENDERECO`) VALUES (5,'Bruno Rodrigues','2014-03-11','2015-05-22',2180.00,4,5);


USE `rh-db` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
