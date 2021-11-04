INSERT INTO USER (id, email, lastname, name, password, phone, username)
VALUES (1, 'user1@email.com', 'user1', 'user1', '{bcrypt}$2y$12$y8uY.LDwuib2e.CFpoEF7.lWcG2P.n33o0jXBBUCc4pykroZn0RvS',
        '+37060090000', 'user1'),
       (2, 'user2@email.com', 'user2', 'user2', '{bcrypt}$2y$12$y8uY.LDwuib2e.CFpoEF7.lWcG2P.n33o0jXBBUCc4pykroZn0RvS',
        '+37060090000', 'user2'),
       (3, 'user3@email.com', 'user3', 'user3', '{bcrypt}$2y$12$y8uY.LDwuib2e.CFpoEF7.lWcG2P.n33o0jXBBUCc4pykroZn0RvS',
        '+37060090000', 'user3'),
       (4, 'user4@email.com', 'user4', 'user3', '{bcrypt}$2y$12$y8uY.LDwuib2e.CFpoEF7.lWcG2P.n33o0jXBBUCc4pykroZn0RvS',
        '+37060090000', 'user4'),
       (5, 'admin@email.com', 'adminas', 'adminukas',
        '{bcrypt}$2y$12$6C5T4j7HlR8CaokuYbtvMuKU5GAHJxVmq7v9oQonieq5jTAtEiRuG', '+37069099999', 'admin');

INSERT INTO ROLES (id, role_name)
VALUES (1, 'USER'),
       (2, 'ADMIN');

INSERT INTO USER_ROLES (user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 2),
       (5, 1);
INSERT INTO TASK (id, description, name, status, task_group, assignee_id, task_id)
VALUES (1, 'example', 'example', 'Open', 'example', 1, NULL),
       (2, 'example1', 'example1', 'Open', 'example1', 1, 1),
    (3, 'example2', 'example2', 'Done', 'example2', 1, NULL);

INSERT INTO TIME_SPENT (task_id, end_time, start_time)
VALUES ( 1, '-1-11-28 00:00:00', '2021-10-26 11:54:27' ),
       (2, '-1-11-28 00:00:00', '2021-10-26 11:56:23'),
       (3, '2021-10-27 12:53:12', '2021-10-26 11:56:23');

