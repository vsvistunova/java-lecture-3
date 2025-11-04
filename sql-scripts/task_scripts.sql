-- ================================
-- 1. Вывести имя, возраст и email всех пользователей старше 25 лет,
-- отсортировать по возрасту (по убыванию)
-- ================================
SELECT name, age, email
FROM users
WHERE age > 25
ORDER BY age DESC;

-- ================================
-- 2. Показать название, дату и место событий, которые еще не прошли
-- (event_time в будущем), отсортировать по дате по возрастанию
-- ================================
SELECT title, event_time, location
FROM events
WHERE event_time > CURRENT_TIMESTAMP
ORDER BY event_time;

-- ================================
-- 3. Вывести 3 самых молодых пользователя
-- ================================
SELECT *
FROM users
ORDER BY age
LIMIT 3;

-- ================================
-- 4. Посчитать количество пользователей каждой роли и средний возраст по ролям
-- ================================
SELECT role, COUNT(*) as user_count, AVG(age) as average_age
FROM users
GROUP BY role
ORDER BY user_count DESC;

-- ================================
-- 5. Вывести все группы с именами кураторов и количеством студентов в каждой группе
-- ================================
SELECT g.name as group_name, g.curator_name, COUNT(u.id) as student_count
FROM groups g
     LEFT JOIN users u ON g.id = u.group_id
WHERE u.role = 'student'
GROUP BY g.id, g.name, g.curator_name
ORDER BY g.name;

-- ================================
-- 6. Найти прошедшие события, на которые записалось больше 10 студентов (role = “student”)
-- ================================
SELECT e.title, e.event_time, COUNT(er.user_id) as student_count
FROM events e
     JOIN event_registrations er ON e.id = er.event_id
     JOIN users u ON er.user_id = u.id AND u.role = 'student'
WHERE e.event_time < CURRENT_TIMESTAMP
GROUP BY e.id, e.title, e.event_time, e.location
HAVING COUNT(er.user_id) > 10;