-- Create Sequence

create sequence account_user_id_seq increment by 1 minvalue 1 no maxvalue start with 1;


-- Table: public.account

-- DROP TABLE public.account;

CREATE TABLE public.account
(
    user_id integer NOT NULL DEFAULT nextval('account_user_id_seq'::regclass),
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(355) COLLATE pg_catalog."default" NOT NULL,
    created_on timestamp without time zone NOT NULL,
    last_login timestamp without time zone,
    CONSTRAINT account_pkey PRIMARY KEY (user_id),
    CONSTRAINT account_email_key UNIQUE (email),
    CONSTRAINT account_username_key UNIQUE (username)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.account
    OWNER to postgres;

-- Alter Sequence

alter sequence account_user_id_seq owned by account.user_id