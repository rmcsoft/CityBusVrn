CREATE SCHEMA `bus_vrn`;

CREATE TABLE `bus_vrn`.`buses` (
  `bus_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `route` varchar(45) DEFAULT NULL,
  `lat` float NOT NULL,
  `lon` float NOT NULL,
  PRIMARY KEY (`bus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bus_vrn`.`bus_stops` (
  `bus_stop_id` int(10) unsigned NOT NULL,
  `name` varchar(45) NOT NULL,
  `lat` float NOT NULL,
  `lon` float NOT NULL,
  PRIMARY KEY (`bus_stop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bus_vrn`.`users` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rfid` varchar(45) NOT NULL,
  `name` varchar(100) NOT NULL,
  `money` int(11) DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bus_vrn`.`user_records` (
  `record_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `bus_id` INT UNSIGNED NOT NULL,
  `stop_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `time` TIMESTAMP NOT NULL,
  `type` TINYINT NOT NULL COMMENT '0 - entered in bus. 1 - got out from bus',
  PRIMARY KEY (`record_id`),
  INDEX `user_record_fk1_idx` (`bus_id` ASC),
  INDEX `user_record_fk2_idx` (`stop_id` ASC),
  INDEX `user_record_fk3_idx` (`user_id` ASC),
  CONSTRAINT `user_record_fk1`
    FOREIGN KEY (`bus_id`)
    REFERENCES `bus_vrn`.`bus` (`bus_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user_record_fk2`
    FOREIGN KEY (`stop_id`)
    REFERENCES `bus_vrn`.`bus_stop` (`bus_stop_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user_record_fk3`
    FOREIGN KEY (`user_id`)
    REFERENCES `bus_vrn`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
