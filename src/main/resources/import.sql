DROP SCHEMA IF EXISTS `namesaver`;

CREATE SCHEMA `namesaver`;

CREATE TABLE `namesaver`.`names` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Forename` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));