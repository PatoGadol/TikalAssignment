-- Create Sequence

create sequence role_role_id_seq increment by 1 minvalue 1 no maxvalue start with 1


-- Table: public.role

-- DROP TABLE public.role;

CREATE TABLE public.role
(
    role_id integer NOT NULL DEFAULT nextval('role_role_id_seq'::regclass),
    role_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (role_id),
    CONSTRAINT role_role_name_key UNIQUE (role_name)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.role
    OWNER to postgres;

-- Alter Sequence

alter sequence role_role_id_seq owned by role.role_id