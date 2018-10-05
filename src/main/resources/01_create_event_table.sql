CREATE TABLE IF NOT EXISTS event (
  id          VARCHAR(36) primary key,
  name        VARCHAR(100),
  description VARCHAR(500),
  event_start  DATETIME,
  event_finish DATETIME
);