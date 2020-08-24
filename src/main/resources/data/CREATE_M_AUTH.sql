-- Table: public.M_AUTH

-- DROP TABLE public.M_AUTH;

CREATE TABLE public.M_AUTH
(
    USER_ID integer NOT NULL,
    TOKEN character varying(64) COLLATE pg_catalog.default NOT NULL,
    USERNAME character varying(32) COLLATE pg_catalog.default NOT NULL,
    PASSWORD character varying(64) COLLATE pg_catalog.default NOT NULL,
    ROLE_CODE character varying(2) COLLATE pg_catalog.default NOT NULL,
    CREATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    CREATED_DATETIME timestamp without time zone NOT NULL,
    UPDATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    UPDATED_DATETIME timestamp without time zone NOT NULL,
    CONSTRAINT M_AUTH_pkey PRIMARY KEY (USER_ID, USERNAME),
    CONSTRAINT M_USER_fkey
      FOREIGN KEY (USER_ID) REFERENCES M_USER(USER_ID)
      ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.M_AUTH
    OWNER to postgres;

COMMENT ON COLUMN public.M_AUTH.ROLE_CODE IS '"00": General User; "01": Council; "99": Admin;';

