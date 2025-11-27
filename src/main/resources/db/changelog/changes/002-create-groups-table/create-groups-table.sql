CREATE TABLE public."groups" (
                                 id serial4 NOT NULL,
                                 "name" varchar(50) NOT NULL,
                                 curator_name varchar(100) NOT NULL,
                                 CONSTRAINT groups_pkey PRIMARY KEY (id)
);