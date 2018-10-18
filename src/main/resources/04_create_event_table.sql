CREATE TABLE IF NOT EXISTS event (
  id          VARCHAR(36) primary key,
  name        VARCHAR(100),
  description VARCHAR(500),
  event_start  TIMESTAMP,
  event_finish TIMESTAMP,
  user_email VARCHAR(30),
  category INTEGER,
  FOREIGN KEY (user_email) REFERENCES user(email)
);