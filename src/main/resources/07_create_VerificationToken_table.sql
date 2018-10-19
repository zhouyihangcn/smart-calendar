CREATE TABLE IF NOT EXISTS verification_token (
  id          VARCHAR(36) primary key,
  token        VARCHAR(60),
  user_email        VARCHAR(30),
  expiry_date   TIMESTAMP,
  FOREIGN KEY (user_email) REFERENCES user(email)
);