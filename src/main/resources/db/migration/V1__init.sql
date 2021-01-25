CREATE TABLE users
(
    id       bigserial,
    username varchar(25) not null,
    password varchar(80) not null,
    score    int default 0,
    primary key (id)
);

CREATE TABLE roles
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE user_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ADMIN');

INSERT INTO users(username, password)
VALUES ('user', '$2y$12$oJWLBXLRj/WopND1bdLzq.XzjzdLHRGgw8qsPPhWfjxQ72nyMqkkK'),
       ('admin', '$2y$12$oJWLBXLRj/WopND1bdLzq.XzjzdLHRGgw8qsPPhWfjxQ72nyMqkkK');

INSERT INTO user_roles(user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (2, 2);