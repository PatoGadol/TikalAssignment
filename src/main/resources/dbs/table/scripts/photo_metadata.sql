-- Create Sequence

create sequence photo_metadata_id_seq increment by 1 minvalue 1 no maxvalue start with 1;


-- Table: public.photo_metadata

-- DROP TABLE public.photo_metadata;

CREATE TABLE public.photo_metadata
(
    photo_metadata_id integer NOT NULL DEFAULT nextval('photo_metadata_id_seq'::regclass),
    photo_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    location character varying(100) COLLATE pg_catalog."default" NOT NULL,
    landscape character varying(355) COLLATE pg_catalog."default" NOT NULL,
    date_created timestamp without time zone,
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.photo_metadata
    OWNER to postgres;

-- Alter Sequence

alter sequence photo_metadata_id_seq owned by photo_metadata.photo_metadata_id