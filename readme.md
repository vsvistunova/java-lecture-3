# **API Documentation**

---

# **Events API**

## **GET `/events`**

Получить список всех событий (с пагинацией).

**Query params:**

* `page` — номер страницы
* `size` — количество элементов (по умолчанию 5)

**Response:**
`List<EventResponse>`

---

## **GET `/events/{id}/relevance`**

Проверить, актуально ли событие.

**Path params:**

* `id` — ID события

**Response:**
`true` / `false`

---

## **POST `/events`**

Создать событие.

**Body:**
`EventRequest`

**Response:**
`EventResponse`

---

## **PUT `/events/{id}`**

Обновить событие.

**Path params:**

* `id` — ID события

**Body:**
`EventRequest`

**Response:**
`EventResponse`

---

## **DELETE `/events/{id}`**

Удалить событие.

**Path params:**

* `id` — ID события

**Response:**
`true` / `false`

---

# **Event Participants API**

Base URL: `/events`

## **GET `/events/participants`**

Получить регистрации пользователя на события.

**Query params:**

* `userId` — ID пользователя
* `page`, `size` — пагинация (size=5 по умолчанию)

**Response:**
`List<EventRegistrationResponse>`

---

## **POST `/events/{eventId}/participants/{userId}`**

Зарегистрировать пользователя на событие.

**Path params:**

* `eventId` — ID события
* `userId` — ID пользователя

**Response:**
`EventRegistrationResponse`

---

# **Groups API**

Base URL: `/groups`

## **GET `/groups`**

Получить список групп (с пагинацией).

**Query params:**
`page`, `size`

**Response:**
`List<GroupResponse>`

---

## **POST `/groups`**

Создать новую группу.

**Body:**
`GroupRequest`

**Response:**
`GroupResponse`

---

# **Users API**

Base URL: `/users`

## **GET `/users`**

Получить всех пользователей (с пагинацией).

**Query params:**

* `page`, `size`

**Response:**
`List<UserSummaryResponse>`

---

## **GET `/users/{userId}`**

Получить подробную информацию о пользователе.

**Path params:**
`userId`

**Response:**
`UserDetailedResponse`

---

## **GET `/users/filtered`**

Получить пользователей по возрасту.

**Query params:**

* `minAge`
* `maxAge`
* `page`, `size`

**Response:**
`List<UserSummaryResponse>`

---

## **POST `/users`**

Создать нового пользователя.

**Body:**
`UserRequest`

**Response:**
`UserDetailedResponse`

---

## **PUT `/users/{userId}`**

Изменить данные пользователя.

**Path params:**
`userId`

**Body:**
`UserRequest`

**Response:**
`UserDetailedResponse`

---

## **DELETE `/users/{userId}`**

Удалить пользователя.

**Path params:**
`userId`

**Response:**
`true` / `false`

