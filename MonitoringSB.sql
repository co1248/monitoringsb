CREATE DATABASE MonitoringSB;
USE MonitoringSB;
CREATE TABLE host(
	id int(10) NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) UNIQUE KEY DEFAULT NULL,
	address VARCHAR(30) UNIQUE KEY DEFAULT NULL,
             regDate datetime default current_timestamp,
             modDate datetime default current_timestamp,
             alive char(1) DEFAULT 'N',
             aliveDate datetime DEFAULT NULL,
PRIMARY KEY (`id`)
)COLLATE='utf8_general_ci' ENGINE=INNODB;
INSERT INTO host(NAME, ADDRESS) VALUES('google.com', '173.194.127.110'); --테스트코드
COMMIT;