-- Table: public.M_USER

-- DROP TABLE public.M_USER;

CREATE TABLE public.m_user
(
    USER_ID serial NOT NULL,
    SURNAME character varying(32) COLLATE pg_catalog.default NOT NULL,
    GIVEN_NAME character varying(32) COLLATE pg_catalog.default NOT NULL,
    MIDDLE_INITIAL character varying(1) COLLATE pg_catalog.default,
    EMAIL_ADDRESS character varying(32) COLLATE pg_catalog.default NOT NULL,
    MOBILE_NUMBER character varying(32) COLLATE pg_catalog.default,
    INSTITUTION_ID integer,
    CREATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    CREATED_DATETIME timestamp without time zone NOT NULL,
    UPDATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    UPDATED_DATETIME timestamp without time zone NOT NULL,
    CONSTRAINT M_USER_pkey PRIMARY KEY (USER_ID)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.m_user
    OWNER to rqquhbbxasusyc;

