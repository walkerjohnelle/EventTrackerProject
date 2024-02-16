-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema jobtrackerdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `jobtrackerdb` ;

-- -----------------------------------------------------
-- Schema jobtrackerdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jobtrackerdb` DEFAULT CHARACTER SET utf8 ;
USE `jobtrackerdb` ;

-- -----------------------------------------------------
-- Table `preferences`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `preferences` ;

CREATE TABLE IF NOT EXISTS `preferences` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `salary_importance` INT NOT NULL,
  `location_importance` INT NOT NULL,
  `benefits_importance` INT NOT NULL,
  `remote_work_importance` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(50) NOT NULL,
  `address2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NOT NULL,
  `state_province` VARCHAR(20) NOT NULL,
  `postal_code` VARCHAR(10) NULL,
  `country_code` CHAR(2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `employed` TINYINT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `clearance` VARCHAR(45) NULL,
  `education` VARCHAR(100) NULL,
  `skills` JSON NULL,
  `preferences_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_preferences1_idx` (`preferences_id` ASC),
  INDEX `fk_user_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_user_preferences1`
    FOREIGN KEY (`preferences_id`)
    REFERENCES `preferences` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job` ;

CREATE TABLE IF NOT EXISTS `job` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(250) NULL,
  `company` VARCHAR(200) NULL,
  `min_salary` DECIMAL(10,2) NOT NULL,
  `max_salary` DECIMAL(10,2) NOT NULL,
  `required_skills` JSON NOT NULL,
  `education` VARCHAR(45) NOT NULL,
  `min_experience` INT NOT NULL,
  `max_experience` INT NOT NULL,
  `date_posted` DATE NULL,
  `application_date` DATE NULL,
  `application_status` VARCHAR(45) NULL,
  `contact_info` VARCHAR(100) NULL,
  `job_listing` TEXT NULL,
  `clearance` VARCHAR(45) NULL,
  `remote_hybrid` TINYINT NULL,
  `benefits` JSON NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_job_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_job_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `match`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match` ;

CREATE TABLE IF NOT EXISTS `match` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `match_score` DECIMAL NULL,
  `preferences_id` INT NOT NULL,
  `job_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_match_preferences1_idx` (`preferences_id` ASC),
  INDEX `fk_match_job1_idx` (`job_id` ASC),
  INDEX `fk_match_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_match_preferences1`
    FOREIGN KEY (`preferences_id`)
    REFERENCES `preferences` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_match_job1`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_match_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_job`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_job` ;

CREATE TABLE IF NOT EXISTS `user_has_job` (
  `user_id` INT NOT NULL,
  `job_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `job_id`),
  INDEX `fk_user_has_job_job1_idx` (`job_id` ASC),
  INDEX `fk_user_has_job_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_job_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_job_job1`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS jobseeker@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'jobseeker'@'localhost' IDENTIFIED BY 'jobseeker';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'jobseeker'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `preferences`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobtrackerdb`;
INSERT INTO `preferences` (`id`, `salary_importance`, `location_importance`, `benefits_importance`, `remote_work_importance`) VALUES (1, 70, 20, 5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `location`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobtrackerdb`;
INSERT INTO `location` (`id`, `address`, `address2`, `city`, `state_province`, `postal_code`, `country_code`) VALUES (1, '6715 E Union Ave', 'Unit 118', 'Denver', 'Colorado', '80237', 'US');
INSERT INTO `location` (`id`, `address`, `address2`, `city`, `state_province`, `postal_code`, `country_code`) VALUES (2, '17455 E Exposition Dr', NULL, 'Aurora', 'Colorado', '80017', 'US');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobtrackerdb`;
INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `password`, `employed`, `role`, `clearance`, `education`, `skills`, `preferences_id`, `location_id`) VALUES (1, 'johnellewalker@gmail.com', 'Johnelle', 'Walker', 'password', 2, 'admin', 'TS/SCI', 'Bachelor\'s degree', '[\"Java\", \"SQL\", \"Spring Boot\", \"REST APIs\", \"JSON\", \"JavaScript\", \"Angular\", \"HTML\", \"C++\", \"Node.js\", \"React.js\"]', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobtrackerdb`;
INSERT INTO `job` (`id`, `title`, `company`, `min_salary`, `max_salary`, `required_skills`, `education`, `min_experience`, `max_experience`, `date_posted`, `application_date`, `application_status`, `contact_info`, `job_listing`, `clearance`, `remote_hybrid`, `benefits`, `location_id`) VALUES (1, 'Software Developer', 'Northrop Grumman', 81000, 121600, '[\"Python\", \"OOP\", \"Linux\", \"Jira\", \"Java\", \"Docker\", \"C++\", \"Agile\", \"AWS\", \"Confluence\"]', 'Bachelor’s degree', 2, 4, '2024-01-30', NULL, NULL, NULL, 'https://www.northropgrumman.com/jobs/Engineering/Software/United-States-of-America/Colorado/Aurora/R10106447/northrop-grumman-dod-skillbridge-engineer-softwareprincipal-engineer-software-active-top-secretsci-c', 'TS/SCI with CI Poly', 2, '[\"bonus\", \"health insurance\", \"life insurance\", \"savings plan\", \"PTO\"]', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_job`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobtrackerdb`;
INSERT INTO `user_has_job` (`user_id`, `job_id`) VALUES (1, 1);

COMMIT;

