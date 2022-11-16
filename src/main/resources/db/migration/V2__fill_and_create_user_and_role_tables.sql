CREATE TABLE IF NOT EXISTS roles
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

insert into roles (name) values ('ROLE_USER'), ('ROLE_ADMIN');

CREATE TABLE IF NOT EXISTS users
(
    id           VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255),
    password     VARCHAR(255),
    created      TIMESTAMP WITHOUT TIME ZONE,
    updated      TIMESTAMP WITHOUT TIME ZONE,
    status       VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_roles
(
    role_id BIGINT       NOT NULL,
    user_id VARCHAR(255) NOT NULL
);

ALTER TABLE user_roles DROP CONSTRAINT IF EXISTS fk_userol_on_role_entity;

ALTER TABLE user_roles DROP CONSTRAINT IF EXISTS fk_userol_on_user_entity;

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role_entity FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user_entity FOREIGN KEY (user_id) REFERENCES users (id);