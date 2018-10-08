CREATE TABLE IF NOT EXISTS user (
  id          VARCHAR(36) primary key,
  nick        VARCHAR(20),
  password   VARCHAR(64)
);