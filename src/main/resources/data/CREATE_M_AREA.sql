-- Table: public.M_AREA

-- DROP TABLE public.M_AREA;

CREATE TABLE public.M_AREA
(
    AREA_CODE character varying(64) COLLATE pg_catalog.default NOT NULL,
    DISTRICT_NAME character varying(32) COLLATE pg_catalog.default NOT NULL,
    CHAIRMAN_NAME character varying(32) COLLATE pg_catalog.default,
    CHAIRMAN_CONTACT_NO character varying(32) COLLATE pg_catalog.default,
    COMMISSIONER_NAME character varying(32) COLLATE pg_catalog.default,
    COMMISSIONER_CONTACT_NO character varying(31) COLLATE pg_catalog.default,
    REMARKS character varying(256) COLLATE pg_catalog.default,
    VALID_FLAG boolean NOT NULL default true,
    CREATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    CREATED_DATETIME timestamp without time zone NOT NULL,
    UPDATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    UPDATED_DATETIME timestamp without time zone NOT NULL,
    CONSTRAINT M_AREA_pkey PRIMARY KEY (AREA_CODE, DISTRICT_NAME)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.M_AREA
    OWNER to rqquhbbxasusyc;
