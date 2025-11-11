CREATE TABLE groups
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(64)  NOT NULL,
    curator_name VARCHAR(128) NOT NULL
);

COMMENT ON TABLE groups IS 'Таблица для хранения информации об учебных группах';
COMMENT ON COLUMN groups.id IS 'Уникальный идентификатор группы';
COMMENT ON COLUMN groups.name IS 'Таблица учебных групп';
COMMENT ON COLUMN groups.curator_name IS 'ФИО куратора группы';