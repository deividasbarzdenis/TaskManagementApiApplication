CREATE TABLE user
(
    id       INTEGER      NOT NULL AUTO_INCREMENT,
    email    VARCHAR(250),
    lastname VARCHAR(250),
    name     VARCHAR(250),
    password VARCHAR(250) NOT NULL,
    phone    VARCHAR(250),
    username VARCHAR(250) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE roles
(
    id        INTEGER NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(250),
    PRIMARY KEY (id)
);
CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) references user (id),
    FOREIGN KEY (role_id) references roles (id)
);
-- CREATE TABLE task
-- (
--     id INTEGER NOT NULL AUTO_INCREMENT,
--     name VARCHAR(250),
--     description VARCHAR(250),
--     task_group VARCHAR(250),
--     status VARCHAR(250),
--     FOREIGN KEY (assignee_id) references  user (id),
--     FOREIGN KEY (time_spent_id) references time_spent (id),
--     FOREIGN KEY (task_id) references task (id)
-- );
