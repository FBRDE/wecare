-- DROP DATABASE IF EXISTS MEDISCREENDB

/** PRODUCTION DB **/

-- CREATE Database
CREATE DATABASE IF NOT EXISTS `mediscreendb`;
	USE `mediscreendb`;
    SET autocommit=1;

-- CREATE Tables
create table if not exists patient (
  id bigint not null auto_increment,
  last_name varchar(125) default null,
  first_name varchar(125) default null ,
  birth_date DATE default null,
  gender char(1) default null,
  address varchar(150) default null,
  phone_number varchar(20) default null,
  primary key (ID)
)
engine INNODB
AUTO_INCREMENT=0;

-- INSERT data
LOCK TABLES patient WRITE;

INSERT INTO `patient` (`address`, `birth_date`, `first_name`, `last_name`, `phone_number`, `gender`) VALUES ('1 Brookside St', '1966-12-31', 'Test', 'TestNone', '100-222-3333', 'F');
INSERT INTO `patient` (`address`, `birth_date`, `first_name`, `last_name`, `phone_number`, `gender`) VALUES ('2 High St', '1945-06-24', 'Test', 'TestBorderline', '200-333-4444', 'M');
INSERT INTO `patient` (`address`, `birth_date`, `first_name`, `last_name`, `phone_number`, `gender`) VALUES ('3 Club Road', '2004-06-18', 'Test', 'TestInDanger', '300-444-5555', 'M');
INSERT INTO `patient` (`address`, `birth_date`, `first_name`, `last_name`, `phone_number`, `gender`) VALUES ('4 Valley Dr', '2004-06-18', 'Test', 'TestEarlyOnset', '400-555-6666', 'F');

UNLOCK TABLES;