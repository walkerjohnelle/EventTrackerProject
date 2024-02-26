-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tvwatchlistdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tvwatchlistdb` ;

-- -----------------------------------------------------
-- Schema tvwatchlistdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tvwatchlistdb` DEFAULT CHARACTER SET utf8 ;
USE `tvwatchlistdb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `profile_picture_url` TEXT NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tv_show`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tv_show` ;

CREATE TABLE IF NOT EXISTS `tv_show` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `genre` VARCHAR(45) NOT NULL,
  `description` VARCHAR(1000) NULL,
  `release_year` INT NOT NULL,
  `seasons` INT NOT NULL,
  `total_episodes` INT NOT NULL,
  `streaming_platform` VARCHAR(45) NOT NULL,
  `active` TINYINT NOT NULL,
  `image_url` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rating` ;

CREATE TABLE IF NOT EXISTS `rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL,
  `review` TEXT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_rating_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_rating_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_tv_show`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_tv_show` ;

CREATE TABLE IF NOT EXISTS `user_has_tv_show` (
  `user_id` INT NOT NULL,
  `tv_show_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `tv_show_id`),
  INDEX `fk_user_has_tv_show_tv_show1_idx` (`tv_show_id` ASC),
  INDEX `fk_user_has_tv_show_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_tv_show_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_tv_show_tv_show1`
    FOREIGN KEY (`tv_show_id`)
    REFERENCES `tv_show` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tv_show_has_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tv_show_has_rating` ;

CREATE TABLE IF NOT EXISTS `tv_show_has_rating` (
  `tv_show_id` INT NOT NULL,
  `rating_id` INT NOT NULL,
  PRIMARY KEY (`tv_show_id`, `rating_id`),
  INDEX `fk_tv_show_has_rating_rating1_idx` (`rating_id` ASC),
  INDEX `fk_tv_show_has_rating_tv_show1_idx` (`tv_show_id` ASC),
  CONSTRAINT `fk_tv_show_has_rating_tv_show1`
    FOREIGN KEY (`tv_show_id`)
    REFERENCES `tv_show` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tv_show_has_rating_rating1`
    FOREIGN KEY (`rating_id`)
    REFERENCES `rating` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS tvguru@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'tvguru'@'localhost' IDENTIFIED BY 'tvguru';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'tvguru'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `tvwatchlistdb`;
INSERT INTO `user` (`id`, `email`, `username`, `password`, `profile_picture_url`, `active`) VALUES (1, 'johnellewalker@gmail.com', 'walkerjohnelle', 'password', '', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tv_show`
-- -----------------------------------------------------
START TRANSACTION;
USE `tvwatchlistdb`;
INSERT INTO `tv_show` (`id`, `title`, `genre`, `description`, `release_year`, `seasons`, `total_episodes`, `streaming_platform`, `active`, `image_url`) VALUES (1, 'The Sapranos', 'Drama', 'Tony Soprano juggles the problems of his fractious family with those of a \"Family\" of a different sort - the mob. He sees a therapist to deal with his professional and personal problems, which bring on panic attacks. He deals with personal and professional power struggles, affairs, violence, the threat of exposure and betrayal, and a whole bunch of people being whacked.', 1999, 6, 86, 'MAX', 1, NULL);
INSERT INTO `tv_show` (`id`, `title`, `genre`, `description`, `release_year`, `seasons`, `total_episodes`, `streaming_platform`, `active`, `image_url`) VALUES (2, 'Atlanta', 'Comedy', 'Atlanta is one of the top cities for young rappers looking to make a name for themselves in the business. Among those up-and-comers is Alfred Miles, a hot new artist who is trying to understand the line between real life and street life. He is managed by his cousin, Earn, who gets caught up in the local rap scene and his cousin\'s career after returning home to the ATL. Earn does whatever he can to try to get Alfred\'s career to the next level. Darius, the rapper\'s right-hand man and visionary, is also in Alfred\'s entourage. When Earn isn\'t busy managing his cousin\'s career, he spends much of his time with best friend Vanessa, who is also the mother of his daughter.', 2016, 4, 41, 'Hulu', 1, NULL);
INSERT INTO `tv_show` (`id`, `title`, `genre`, `description`, `release_year`, `seasons`, `total_episodes`, `streaming_platform`, `active`, `image_url`) VALUES (3, 'Severance', 'Thriller', 'Mark leads a team of office workers whose memories have been surgically divided between their work and personal lives; when a mysterious colleague appears outside of work, it begins a journey to discover the truth about their jobs.', 2022, 1, 9, 'Apple TV+', 2, NULL);
INSERT INTO `tv_show` (`id`, `title`, `genre`, `description`, `release_year`, `seasons`, `total_episodes`, `streaming_platform`, `active`, `image_url`) VALUES (4, 'Mr. & Mrs. Smith', 'Thriller', 'John and Jane Smith become secret agents for a mysterious organisation. Their work puts both their skills as spies and their relationship to the test. The two have to deal with a world full of dangers.', 2024, 1, 8, 'Amazon Prime Video', 2, NULL);
INSERT INTO `tv_show` (`id`, `title`, `genre`, `description`, `release_year`, `seasons`, `total_episodes`, `streaming_platform`, `active`, `image_url`) VALUES (5, 'Ozark', 'Drama', 'Created by Bill Dubuque (\"The Accountant,\" \"The Judge\"), this drama series stars Jason Bateman as Marty Byrde, a financial planner who relocates his family from Chicago to a summer resort community in the Ozarks. With wife Wendy and their two kids in tow, Marty is on the move after a money-laundering scheme goes wrong, forcing him to pay off a substantial debt to a Mexican drug lord in order to keep his family safe. While the Byrdes\' fate hangs in the balance, the dire circumstances force the fractured family to reconnect.', 2017, 4, 44, 'Netflix', 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `tvwatchlistdb`;
INSERT INTO `rating` (`id`, `rating`, `review`, `user_id`) VALUES (1, 10, 'This show is now quite literally the gold standard of television for me now! It\'s going to be really hard for anything to really compete. The Sapranos literally has everything from comedy, family drama, crime thrills, and did I mention comedy? I  seriously can\'t believe that this show has been out for over 25 years and I\'m just now getting to it!', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_tv_show`
-- -----------------------------------------------------
START TRANSACTION;
USE `tvwatchlistdb`;
INSERT INTO `user_has_tv_show` (`user_id`, `tv_show_id`) VALUES (1, 1);
INSERT INTO `user_has_tv_show` (`user_id`, `tv_show_id`) VALUES (1, 2);
INSERT INTO `user_has_tv_show` (`user_id`, `tv_show_id`) VALUES (1, 3);
INSERT INTO `user_has_tv_show` (`user_id`, `tv_show_id`) VALUES (1, 4);
INSERT INTO `user_has_tv_show` (`user_id`, `tv_show_id`) VALUES (1, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tv_show_has_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `tvwatchlistdb`;
INSERT INTO `tv_show_has_rating` (`tv_show_id`, `rating_id`) VALUES (1, 1);

COMMIT;

