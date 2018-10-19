INSERT INTO user (email, first_name, last_name, password, enabled)
VALUES ('admin@gmail.com', 'admin', 'adminadmin', '$2a$08$EZB3.a32eH06NTeiCoC8gevEJa7h5ppqvm6zssH8gnw4UoM.m1JGa', true);

INSERT INTO users_roles (user_email, role_id)
VALUES ('admin@gmail.com', 1);