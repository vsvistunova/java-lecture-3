CREATE TABLE events
(
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR(256) NOT NULL,
    description      TEXT,
    event_time       TIMESTAMP    NOT NULL,
    location         VARCHAR(256) NOT NULL,
    max_participants INTEGER
);

COMMENT ON TABLE events IS 'Таблица для хранения информации о событиях';
COMMENT ON COLUMN events.id IS 'Уникальный идентификатор события';
COMMENT ON COLUMN events.title IS 'Название мероприятия';
COMMENT ON COLUMN events.description IS 'Подробное описание мероприятия';
COMMENT ON COLUMN events.event_time IS 'Дата и время проведения мероприятия';
COMMENT ON COLUMN events.location IS 'Место проведения мероприятия';
COMMENT ON COLUMN events.max_participants IS 'Максимальное количество участников';