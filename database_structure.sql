-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema tagged_photos
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tagged_photos` ;

-- -----------------------------------------------------
-- Schema tagged_photos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tagged_photos` DEFAULT CHARACTER SET latin1 ;
USE `tagged_photos` ;

-- -----------------------------------------------------
-- Table `tagged_photos`.`facebook_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tagged_photos`.`facebook_user` (
  `id` BIGINT(5) NOT NULL,
  `name` VARCHAR(150) NOT NULL,
  `gender` VARCHAR(6) NOT NULL,
  `profile_picture` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UC_ID` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tagged_photos`.`photos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tagged_photos`.`photos` (
  `id` BIGINT(5) UNSIGNED NOT NULL,
  `name` VARCHAR(150) NULL DEFAULT NULL,
  `link` VARCHAR(250) NOT NULL,
  `image` VARCHAR(250) NOT NULL,
  `album` VARCHAR(75) NULL DEFAULT NULL,
  `user_id` BIGINT(5) NOT NULL,
  `reactions_total` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK_PHOTOS_USER` (`user_id` ASC),
  CONSTRAINT `FK_PHOTOS_USER`
    FOREIGN KEY (`user_id`)
    REFERENCES `tagged_photos`.`facebook_user` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tagged_photos`.`reaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tagged_photos`.`reaction` (
  `id` BIGINT(5) NOT NULL,
  `type` VARCHAR(8) NOT NULL,
  `photo_id` BIGINT(5) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `type`, `photo_id`),
  INDEX `id_idx` (`photo_id` ASC),
  CONSTRAINT `FK_PHOTO_REACTION`
    FOREIGN KEY (`photo_id`)
    REFERENCES `tagged_photos`.`photos` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

USE `tagged_photos` ;

-- -----------------------------------------------------
-- Placeholder table for view `tagged_photos`.`reaction_stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tagged_photos`.`reaction_stats` (`count` INT, `type` INT, `photo_id` INT);

-- -----------------------------------------------------
-- View `tagged_photos`.`reaction_stats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tagged_photos`.`reaction_stats`;
USE `tagged_photos`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `tagged_photos`.`reaction_stats` AS select count(`r`.`id`) AS `count`,`r`.`type` AS `type`,`r`.`photo_id` AS `photo_id` from `tagged_photos`.`reaction` `r` group by `r`.`type`,`r`.`photo_id` order by `r`.`photo_id`,`r`.`type`;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
