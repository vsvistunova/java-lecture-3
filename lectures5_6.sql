
--------------------------------------Выполненные задания с 150-190 строчку кода----------------------------------------------

SET timezone = 'Europe/Moscow';
SELECT now();

CREATE TABLE users(
                      id SERIAL PRIMARY KEY ,
                      name VARCHAR(100) NOT NULL,
                      age INTEGER,
                      email VARCHAR(150) UNIQUE NOT NULL
);

CREATE TABLE groups (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(50) NOT NULL,           -- "3ПМ", "2ИСИТ"
                        curator_name VARCHAR(100) NOT NULL   -- "Иванова Мария Петровна"
);

ALTER TABLE users
    ADD COLUMN group_id INTEGER;

ALTER TABLE users
    ADD COLUMN role VARCHAR(30) NOT NULL DEFAULT 'student';

ALTER TABLE users
    ADD CONSTRAINT fk_user_group
        FOREIGN KEY (group_id) REFERENCES groups(id);


CREATE TABLE events (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(200) NOT NULL,
                        description TEXT,
                        event_time TIMESTAMP NOT NULL,
                        location VARCHAR(200) NOT NULL,
                        max_participants INTEGER
);

CREATE TABLE event_registrations (
                                     id SERIAL PRIMARY KEY,
                                     user_id INTEGER NOT NULL,
                                     event_id INTEGER NOT NULL,

                                     FOREIGN KEY (user_id) REFERENCES users(id),
                                     FOREIGN KEY (event_id) REFERENCES events(id)
);




INSERT INTO users (name, age, email,group_id,role)VALUES
                                                      ('Анна',25,'anna@university.ru',1,'student'),
                                                      ('Иван',30,'ivan@university.ru',2,'student'),
                                                      ('Мария',22,'maria@university.ru',2,'student'),
                                                      ('Петр',35,'petr@mail.com',3,'student'),
                                                      ('Ольга',28,'olga@mail.com',1,'student'),
                                                      ('Сергей',27,'sergey@mail.com',3,'student'),
                                                      ('Елена',29,'elena@mail.com',2,'student'),
                                                      ('Алексей',31,'alex@mail.com',3,'student'),
                                                      ('Дмитрий',22,'dmitry@mail.com',1,'student'),
                                                      ('Светлана',33,'svetlana@mail.com',2,'student'),
                                                      ('Ольга',45,'olga_45@mail.com',null,'teacher'),
                                                      ('Антон',55,'anton@mail.com',null,'teacher'),
                                                      ('Светлана',62,'svetlana_62@mail.com',null,'teacher'),
                                                      ('Виктор',26,'victor@mail.com',null,'admin'),
                                                      ('Эльдар',19,'eldar@mail.com',null,'admin'),
                                                      ('test_user1',1,'1',null,'student'),
                                                      ('test_user2',2,'2',null,'student'),
                                                      ('test_user3',3,'3',null,'student'),
                                                      ('test_user4',4,'4',null,'student'),
                                                      ('test_user4',5,'5',null,'student');





INSERT INTO groups(name, curator_name) VALUES
                                           ('3ИВТ','Светлана Анатолевна Готра'),
                                           ('2ТИИ','Антон Сергеивич Хачатрян'),
                                           ('4Ф','Ольга Анатолевна Шкварковская');

INSERT INTO event_registrations(user_id, event_id) VALUES
                                                       (5,1),
                                                       (11,1),
                                                       (19,1),
                                                       (7,1),
                                                       (12,1),
                                                       (3,1),
                                                       (18,1),
                                                       (8,1),
                                                       (20,1),
                                                       (4,1),
                                                       (9,1),
                                                       (10,1),
                                                       (3,2),
                                                       (6,2),
                                                       (8,2),
                                                       (10,2),
                                                       (11,2),
                                                       (12,3),
                                                       (8,3),
                                                       (7,3),
                                                       (18,3),
                                                       (5,3),
                                                       (19,3),
                                                       (4,3),
                                                       (20,3),
                                                       (21,3),
                                                       (13,3);



INSERT INTO events(title, description, event_time, location, max_participants) VALUES
                                                                                   ('Структуры данных','Лекция по структурам данных','2025-11-11 14:00:00.000000','Аудитория 318',30),
                                                                                   ('Новогоднее мероприятие','Празднуем наступающий новый год','2025-12-26 18:00:00.000000','Актовый зал', null),
                                                                                   ('Лекции оп Java','Основы Java разработки','2025-11-09 16:50:00.000000','Удаленно',15);


SELECT * FROM users;

SELECT name,email FROM users;

SELECT * FROM users WHERE id = 4;

SELECT age, string_agg(name,', '), COUNT(*) AS user_count
FROM users
GROUP BY age
HAVING COUNT(*) > 1;

SELECT COUNT(*) AS total_count,
       MIN(age),
       MAX(age),
       AVG(age)
FROM users;

SELECT *
FROM users u
         JOIN groups g ON u.group_id = g.id;

SELECT u.name, g.name, e.title, e.location
FROM users u
         LEFT JOIN event_registrations er ON u.id = er.user_id
         LEFT JOIN events e ON  e.id = er.event_id
         LEFT JOIN groups g ON g.id = u.group_id
ORDER BY  u.name;



--#1 Вывести имя, возраст и email всех пользователей старше 25 лет,
-- отсортировать по возрасту (по убыванию)
SELECT name,age,email
FROM users
WHERE age >= 25
ORDER BY age DESC;

--#2 Показать название, дату и место событий, которые еще не прошли
-- (event_time в будущем), отсортировать по дате по возрастанию
SELECT title, event_time,location
FROM events
WHERE event_time > NOW()
ORDER BY event_time ASC;

--#3 Вывести 3 самых молодых пользователя
SELECT *
FROM users
ORDER BY age ASC
    LIMIT 3 OFFSET (1-1)*3;

--#4 Посчитать количество пользователей каждой роли и средний возраст по ролям
SELECT role, COUNT(*) AS user_count, AVG(age) AS AVG_age
FROM users
GROUP BY role;

--#5 Вывести все группы с именами кураторов и количеством студентов в каждой группе
SELECT g.name,g.curator_name,COUNT(*) AS users_count
FROM users u
         JOIN groups g ON u.group_id = g.id
group by g.name, g.curator_name;

--#6 Найти прошедшие события, на которые записалось больше 10 студентов (role = “student”)

SELECT e.title, COUNT(*) AS users_count, u.role
FROM events e
LEFT JOIN event_registrations er ON e.id = er.event_id
JOIN users u on u.id = er.user_id
WHERE event_time < NOW()
group by e.title,u.role
HAVING u.role = 'student' AND COUNT(*) >= 10;
