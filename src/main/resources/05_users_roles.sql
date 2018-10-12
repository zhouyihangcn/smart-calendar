CREATE TABLE IF NOT EXISTS users_roles (
  user_email   VARCHAR(30) not null,
  role_id   BIGINT not null,
  FOREIGN KEY (user_email) REFERENCES user(email),
  FOREIGN KEY (role_id) REFERENCES role(id),
  UNIQUE (user_email, role_id)
);