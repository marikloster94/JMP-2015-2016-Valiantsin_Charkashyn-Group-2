CREATE DATABASE `module3`;
CREATE TABLE `person` (
	`idPerson` INT NOT NULL AUTO_INCREMENT,
	`surname` VARCHAR(50) NOT NULL ,
	`name` VARCHAR(50) NOT NULL ,
	`age` INT NOT NULL ,
	PRIMARY KEY (`idPerson`)
);