CREATE TABLE IF NOT EXISTS users_roles (
  user_id   VARCHAR(36) not null,
  role_id   VARCHAR(36) not null,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (role_id) REFERENCES role(id),
  UNIQUE (user_id, role_id)
);