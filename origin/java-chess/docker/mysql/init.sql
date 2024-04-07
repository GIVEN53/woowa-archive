CREATE DATABASE IF NOT EXISTS `CHESS`;
CREATE DATABASE IF NOT EXISTS `TEST`;

CREATE TABLE `CHESS`.`ROOM`
(
    `ROOM_ID` INT         NOT NULL AUTO_INCREMENT,
    `NAME`    VARCHAR(20) NOT NULL,
    PRIMARY KEY (`ROOM_ID`)
);
CREATE TABLE `TEST`.`ROOM` LIKE `CHESS`.`ROOM`;

CREATE TABLE `CHESS`.`BOARD`
(
    `BOARD_ID` INT        NOT NULL AUTO_INCREMENT,
    `SOURCE`   VARCHAR(5) NOT NULL,
    `TARGET`   VARCHAR(5) NOT NULL,
    `ROOM_ID`  INT        NOT NULL,
    PRIMARY KEY (`BOARD_ID`),
    FOREIGN KEY (`ROOM_ID`) REFERENCES `ROOM` (`ROOM_ID`) ON DELETE CASCADE
);
CREATE TABLE `TEST`.`BOARD` LIKE `CHESS`.`BOARD`;