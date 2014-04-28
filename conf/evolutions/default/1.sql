# Users スキーマ

# --- !Ups

CREATE TABLE User (
  id int AUTO_INCREMENT,
  user_id varchar(64) NOT NULL,
  name varchar(64) NOT NULL,
  email varchar(64) NOT NULL,
  resignation_flg char(1) NOT NULL,
  admin_flg char(1) NOT NULL,
  PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE User;
