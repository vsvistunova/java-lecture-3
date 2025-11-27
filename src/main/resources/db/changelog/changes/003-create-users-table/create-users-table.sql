CREATE TABLE users
(
    id       serial4                                          NOT NULL,
    "name"   varchar(100)                                     NOT NULL,
    age      int4                                             NULL,
    email    varchar(150)                                     NOT NULL,
    "role"   varchar(20) DEFAULT 'student'::character varying NOT NULL,
    group_id int4                                             NULL,
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_group_id_fkey FOREIGN KEY (group_id) REFERENCES "groups" (id)
);
