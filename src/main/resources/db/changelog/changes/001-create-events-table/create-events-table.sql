CREATE TABLE public.events
(
    id serial4 NOT NULL,
    title varchar(200) NOT NULL,
    description text NULL,
    event_time timestamp NOT NULL,
    "location" varchar(200) NOT NULL,
    max_participants int4 NULL,
    CONSTRAINT events_pkey PRIMARY KEY (id)
);