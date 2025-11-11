CREATE TABLE event_registrations
(
    id       BIGSERIAL PRIMARY KEY,
    user_id  INTEGER NOT NULL,
    event_id INTEGER NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (event_id) REFERENCES events (id)
);

COMMENT ON TABLE event_registrations IS 'Таблица регистраций на мероприятия - связывает пользователей с мероприятиями';
COMMENT ON COLUMN event_registrations.id IS 'Уникальный идентификатор регистрации';
COMMENT ON COLUMN event_registrations.user_id IS 'Ссылка на пользователя';
COMMENT ON COLUMN event_registrations.event_id IS 'Ссылка на мероприятие';