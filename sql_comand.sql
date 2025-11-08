CREATE TABLE users (
                       id SERIAL PRIMARY KEY ,
                       name VARCHAR(50) NOT NULL,
                       age INT NOT NULL,
                       email VARCHAR(150) NOT NULL
);
INSERT INTO users (name, age, email) VALUES
                                         ('Анна', 25, 'anna@mail.com'),
                                         ('Иван', 30, 'ivan@mail.com'),
                                         ('Мария', 22, 'maria@mail.com'),
                                         ('Петр', 35, 'petr@mail.com'),
                                         ('Ольга', 28, 'olga@mail.com'),
                                         ('Сергей', 27, 'sergey@mail.com'),
                                         ('Елена', 29, 'elena@mail.com'),
                                         ('Алексей', 31, 'alex@mail.com'),
                                         ('Дмитрий', 26, 'dmitry@mail.com'),
                                         ('Светлана', 33, 'svetlana@mail.com');
SELECT * FROM users;
SELECT name, email FROM users;
SELECT * FROM users WHERE id = 1;
SELECT * FROM users WHERE name = 'Анна';
SELECT * FROM users WHERE age >= 18 AND age <=30;
SELECT * FROM users WHERE age BETWEEN 18 AND 30;
SELECT * FROM users
WHERE age BETWEEN 18 AND 30
  AND name = 'Мария';
SELECT * FROM users
WHERE name = 'Иван'
   OR name = 'Мария';
SELECT * FROM users WHERE name LIKE 'И%';
SELECT * FROM users ORDER BY age ASC;
SELECT * FROM users ORDER BY age DESC;
SELECT * FROM users ORDER BY name ASC;
SELECT * FROM users LIMIT 5;
SELECT * FROM users ORDER BY age ASC LIMIT 5;

--

CREATE TABLE groups (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(50) NOT NULL,           -- "3ПМ", "2ИСИТ"
                        curator_name VARCHAR(100) NOT NULL   -- "Иванова Мария Петровна"
);

ALTER TABLE users
    ADD COLUMN group_id INTEGER;
ALTER TABLE users
    ADD COLUMN role VARCHAR(20) NOT NULL DEFAULT 'student';
ALTER TABLE users
    ADD CONSTRAINT fk_user_group
        FOREIGN KEY (group_id)
            REFERENCES groups(id);
INSERT INTO groups (name, curator_name) VALUES
                                            ('3ПМ', 'Иванова Мария Петровна'),
                                            ('1MAT', 'Сидорова Ольга Николаевна'),
                                            ('2ИСИТ', 'Петров Петр Петрович');
SELECT * FROM groups;
SELECT * FROM users;
SELECT u.name, u.role, g.name as group_name
FROM users u
         JOIN groups g ON u.group_id = g.id;
SELECT u.name, u.role, g.name as group_name
FROM users u
         LEFT JOIN groups g ON u.group_id = g.id;
--

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
INSERT INTO events(title, description, event_time, location, max_participants) VALUES
                                                                                   ('Лекция по Spring Boot', 'Основы Spring Boot для начинающих', '2026-03-20 14:00:00.000', 'Аудитория 101', 30),
                                                                                   ('Конференция по Java', 'Практический опыт работы с Java', '2026-03-21 10:00:00.000', 'Аудитория 102', 25),
                                                                                   ('Воркшоп по Git', 'Практическое использование Git','2026-03-25 18:00:00.000', 'CoffeShop"Code"', null);
INSERT INTO event_registrations(user_id, event_id) VALUES
                                                       (1,1),
                                                       (2,1),
                                                       (3,1),
                                                       (5,2),
                                                       (7,2),
                                                       (8,3);
SELECT *
FROM events e
WHERE e.title = 'Лекция по Spring Boot';
SELECT * FROM event_registrations;
SELECT *
FROM events e
         JOIN event_registrations er ON er.event_id = e.id
WHERE e.title = 'Лекция по Spring Boot';
SELECT u."name", u."role", g."name"
FROM events e
         JOIN event_registrations er ON er.event_id = e.id
         JOIN users u on u.id = er.user_id
         LEFT JOIN groups g ON g.id = u.group_id
WHERE e.title = 'Лекция по Spring Boot';
SELECT *
FROM events e;
SELECT *
FROM events e
         JOIN event_registrations er on e.id = er.event_id;
SELECT e.id, COUNT(*)
FROM events e
         JOIN event_registrations er ON er.event_id = e.id
GROUP BY e.id;
SELECT e.title, e."location", COUNT(*) AS participants_count, e.max_participants
FROM events e
         JOIN event_registrations er ON er.event_id = e.id
GROUP BY e.id;

-- WORKS!!! 66-68 SLIDE

SELECT u.name, u.age, u.email
FROM users u
WHERE u.age > 25
ORDER BY age DESC;

SELECT e.title, e.event_time, e.location
FROM events e
WHERE event_time > NOW()
ORDER BY e.event_time ASC;

SELECT u.name, u.age, u.email
FROM users u
ORDER BY age ASC LIMIT 3;

SELECT
    u.role,
    COUNT(*) AS user_count,
    ROUND(AVG(u.age), 2) AS average_age
FROM users u
GROUP BY u.role
ORDER BY u.role;
SELECT
    g.name,
    g.curator_name,
    COUNT(u.id) AS student_count
FROM groups g
         LEFT JOIN users u ON g.id = u.group_id
GROUP BY g.id, g.name, g.curator_name
ORDER BY g.name;
SELECT * FROM event_registrations;
SELECT * FROM events;
SELECT * FROM users;
SELECT
    e.title,
    COUNT(er.user_id) AS student_registrations
FROM events e
         JOIN event_registrations er ON e.id = er.event_id
         JOIN users u ON er.user_id = u.id
WHERE e.event_time < NOW()
  AND u.role = 'student'
GROUP BY e.id, e.title
HAVING COUNT(er.user_id) > 1
ORDER BY student_registrations DESC;
SELECT
    e.title,
    COUNT(er.user_id) AS student_registrations
FROM events e
         JOIN event_registrations er ON e.id = er.event_id
         JOIN users u ON er.user_id = u.id
WHERE e.event_time < NOW()
  AND u.role = 'student'
GROUP BY e.id, e.title
HAVING COUNT(er.user_id) > 10
ORDER BY student_registrations DESC;