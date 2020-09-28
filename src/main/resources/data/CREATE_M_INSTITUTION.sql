-- Table: public.M_INSTITUTION

-- DROP TABLE public.M_INSTITUTION;

CREATE TABLE public.M_INSTITUTION
(
    INSTITUTION_ID serial NOT NULL,
    INSTITUTION_NAME character varying(64) COLLATE pg_catalog.default NOT NULL,
    ADDRESS character varying(256) COLLATE pg_catalog.default,
    CONTACT_NUMBER character varying(32) COLLATE pg_catalog.default,
    DISTRICT character varying(32) COLLATE pg_catalog.default NOT NULL,
    AREA character varying(64) COLLATE pg_catalog.default,
    CATEGORY_CODE character varying(2) COLLATE pg_catalog.default NOT NULL,
    CREATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    CREATED_DATETIME timestamp without time zone NOT NULL,
    UPDATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    UPDATED_DATETIME timestamp without time zone NOT NULL,
    CONSTRAINT M_INSTITUTION_pkey PRIMARY KEY (INSTITUTION_ID)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.M_INSTITUTION
    OWNER to postgres;

COMMENT ON COLUMN public.M_INSTITUTION.CATEGORY_CODE IS '"00": preschool; "01": primary; "02": secondary; "03": senior high; "04": college; "05": community';

