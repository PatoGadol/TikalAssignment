-- Table: public.account_role

-- DROP TABLE public.account_role;

CREATE TABLE public.account_role
(
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    grant_date timestamp without time zone,
    CONSTRAINT account_role_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT account_role_role_id_fkey FOREIGN KEY (role_id)
        REFERENCES public.role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT account_role_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.account (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.account_role
    OWNER to postgres;