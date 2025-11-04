CREATE TABLE groups (
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(64)  NOT NULL,
    curator_name VARCHAR(128) NOT NULL
);

CREATE TABLE users (
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(64)         NOT NULL,
    age      INTEGER,
    email    VARCHAR(128) UNIQUE NOT NULL,
    role     VARCHAR(32)         NOT NULL DEFAULT 'student',
    group_id INTEGER,

    FOREIGN KEY (group_id) REFERENCES groups (id)
);

CREATE TABLE events (
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR(256) NOT NULL,
    description      TEXT,
    event_time       TIMESTAMP    NOT NULL,
    location         VARCHAR(256) NOT NULL,
    max_participants INTEGER
);

CREATE TABLE event_registrations (
    id       SERIAL PRIMARY KEY,
    user_id  INTEGER NOT NULL,
    event_id INTEGER NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (event_id) REFERENCES events (id)
);
