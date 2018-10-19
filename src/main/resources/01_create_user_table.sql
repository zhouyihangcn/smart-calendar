CREATE TABLE IF NOT EXISTS user (
  email           VARCHAR(30) not null primary key,
  first_name      VARCHAR(30) not null,
  last_name       VARCHAR(30) not null,
  password        VARCHAR(64),
  provider        VARCHAR(30),
  phone_number    VARCHAR(15),
  enabled         BOOLEAN
);
