CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(64)         NOT NULL,
    age      INTEGER,
    email    VARCHAR(128) UNIQUE NOT NULL,
    role     VARCHAR(32)         NOT NULL DEFAULT 'student',
    group_id INTEGER,

    FOREIGN KEY (group_id) REFERENCES groups (id)
);

COMMENT ON TABLE users IS 'Таблица для хранения информации о пользователях';
COMMENT ON COLUMN users.id IS 'Уникальный идентификатор пользователя';
COMMENT ON COLUMN users.name IS 'Имя пользователя';
COMMENT ON COLUMN users.age IS 'Возраст пользователя';
COMMENT ON COLUMN users.email IS 'Электронная почта (уникальная)';
COMMENT ON COLUMN users.role IS 'Роль пользователя в системе';
COMMENT ON COLUMN users.group_id IS 'Ссылка на группу, к которой привязан пользователь';