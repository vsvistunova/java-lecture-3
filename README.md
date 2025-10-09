

# Post /users

![postman](images/post_users_postman.png)

![db](images/post_users_db.png)

# Get /users (all)

![get](images/get_users_all.png)

# Get /users/{id}

![get_by_id](images/get_users_byid.png)

# Put

## Было:

![put1_db](images/put1_db.png)

## Request и Responce

![put_req_res](images/put_req_res.png)

# Стало:

![put2_db](images/put2_db.png)

# Delete

## Request
![delete_req](images/delete_req.png)

## Стало в postgre

![delete_db](images/delete_db.png)

# Events

---

## Создание ивента:

![cr_ev](images/events/create_ev.png)

### Результат:

![cr_db](images/events/create_db.png)


## Запись на ивент:

### Запись на прошедший ивент

![passed](images/events/event_alr_passed.png)

### Запись на предстоящий ивент

![ref](images/events/reg_us_on_ev.png)

Также создается запись в many-to-many таблице

event to user

![res](images/events/reg_result.png)


За