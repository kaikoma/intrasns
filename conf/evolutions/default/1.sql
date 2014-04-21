# Users スキーマ

# --- !Ups

CREATE TABLE User (
  user_id varchar(64) NOT NULL,
  position varchar(64) DEFAULT NULL,
  work_location varchar(64) NOT NULL,
  PRIMARY KEY (user_id)
);

# --- !Downs

DROP TABLE User;
