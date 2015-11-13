DROP DATABASE IF EXISTS todo_service;
CREATE DATABASE todo_service;

USE todo_service;
CREATE TABLE todos (
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(1000) NOT NULL,
	is_done BOOL NOT NULL,
	PRIMARY KEY (id)
) Engine=InnoDB;
