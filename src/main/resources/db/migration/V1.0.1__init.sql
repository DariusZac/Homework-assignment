use movie_wikidb;

DROP TABLE IF EXISTS movie_detail;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS main_actor;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS movie_actor;

CREATE TABLE movie(
id bigint(20) NOT NULL AUTO_INCREMENT,
name varchar(255),
PRIMARY KEY(id)
);

CREATE TABLE movie_detail(
movie_id bigint(20) NOT NULL,
description varchar(255),
budget int(11),
release_date date,
PRIMARY KEY(movie_id),
CONSTRAINT movie_dt_cs FOREIGN KEY (movie_id) REFERENCES movie(id)
);

CREATE TABLE review(
id bigint(20) NOT NULL AUTO_INCREMENT,
movie_id bigint(20) NOT NULL,
reviewer varchar(255) NOT NULL,
review varchar(255),
score int(3) NOT NULL,
date_created datetime DEFAULT current_timestamp,
PRIMARY KEY(id),
CONSTRAINT review_cs FOREIGN KEY (movie_id) REFERENCES movie(id)
);

CREATE TABLE main_actor(
id bigint(20) NOT NULL AUTO_INCREMENT,
fullname varchar(255) NOT NULL,
nationality varchar(255),
date_of_birth date,
PRIMARY KEY (id)
);

CREATE TABLE movie_actor(
movie_id bigint(20) NOT NULL,
actor_id bigint(20) NOT NULL,
PRIMARY KEY (movie_id, actor_id),
CONSTRAINT movie_cs FOREIGN KEY (movie_id) REFERENCES movie(id),
CONSTRAINT actor_cs FOREIGN KEY (actor_id) REFERENCES main_actor(id)
);