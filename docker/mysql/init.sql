CREATE DATABASE IF NOT EXISTS `CHESS`;
CREATE DATABASE IF NOT EXISTS `TEST`;

CREATE TABLE `CHESS`.`CHESS_GAME`
(
    `GAME_ID`    int         NOT NULL AUTO_INCREMENT,
    `STATE`      varchar(10) NOT NULL,
    `CREATED_AT` datetime    NOT NULL,
    PRIMARY KEY (`GAME_ID`)
);
CREATE TABLE `TEST`.`CHESS_GAME` LIKE `CHESS`.`CHESS_GAME`;

CREATE TABLE `CHESS`.`PIECE`
(
    `PIECE_ID` int         NOT NULL AUTO_INCREMENT,
    `TYPE`     varchar(10) NOT NULL,
    `COLOR`    varchar(10) NOT NULL,
    PRIMARY KEY (`PIECE_ID`)
);
CREATE TABLE `TEST`.`PIECE` LIKE `CHESS`.`PIECE`;

CREATE TABLE `CHESS`.`POSITION`
(
    `GAME_ID`  int NOT NULL,
    `PIECE_ID` int NOT NULL,
    `FILE`     int NOT NULL,
    `RANK`     int NOT NULL,
    PRIMARY KEY (`GAME_ID`, `PIECE_ID`),
    FOREIGN KEY (`GAME_ID`) REFERENCES `CHESS_GAME` (`GAME_ID`) ON DELETE CASCADE,
    FOREIGN KEY (`PIECE_ID`) REFERENCES `PIECE` (`PIECE_ID`)
);
CREATE TABLE `TEST`.`POSITION` LIKE `CHESS`.`POSITION`;

INSERT INTO `CHESS`.`PIECE` (`PIECE_ID`, `TYPE`, `COLOR`)
VALUES (1, 'ROOK', 'BLACK'),
       (2, 'KNIGHT', 'BLACK'),
       (3, 'BISHOP', 'BLACK'),
       (4, 'QUEEN', 'BLACK'),
       (5, 'KING', 'BLACK'),
       (6, 'BISHOP', 'BLACK'),
       (7, 'KNIGHT', 'BLACK'),
       (8, 'ROOK', 'BLACK'),
       (9, 'PAWN', 'BLACK'),
       (10, 'PAWN', 'BLACK'),
       (11, 'PAWN', 'BLACK'),
       (12, 'PAWN', 'BLACK'),
       (13, 'PAWN', 'BLACK'),
       (14, 'PAWN', 'BLACK'),
       (15, 'PAWN', 'BLACK'),
       (16, 'PAWN', 'BLACK'),
       (17, 'ROOK', 'WHITE'),
       (18, 'KNIGHT', 'WHITE'),
       (19, 'BISHOP', 'WHITE'),
       (20, 'QUEEN', 'WHITE'),
       (21, 'KING', 'WHITE'),
       (22, 'BISHOP', 'WHITE'),
       (23, 'KNIGHT', 'WHITE'),
       (24, 'ROOK', 'WHITE'),
       (25, 'PAWN', 'WHITE'),
       (26, 'PAWN', 'WHITE'),
       (27, 'PAWN', 'WHITE'),
       (28, 'PAWN', 'WHITE'),
       (29, 'PAWN', 'WHITE'),
       (30, 'PAWN', 'WHITE'),
       (31, 'PAWN', 'WHITE'),
       (32, 'PAWN', 'WHITE');

INSERT INTO `TEST`.`PIECE`
SELECT *
FROM `CHESS`.`PIECE`;
