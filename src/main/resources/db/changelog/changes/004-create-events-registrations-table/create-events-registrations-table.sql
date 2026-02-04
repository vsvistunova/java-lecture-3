-- public.event_registrations определение

-- Drop table

-- DROP TABLE public.event_registrations;

CREATE TABLE public.event_registrations (
    id serial4 NOT NULL,
    user_id int4 NOT NULL,
    event_id int4 NOT NULL,
    CONSTRAINT event_registrations_pkey PRIMARY KEY (id)
);


-- public.event_registrations внешние включи

ALTER TABLE public.event_registrations ADD CONSTRAINT event_registrations_event_id_fkey FOREIGN KEY (event_id) REFERENCES public.events(id);
ALTER TABLE public.event_registrations ADD CONSTRAINT event_registrations_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);