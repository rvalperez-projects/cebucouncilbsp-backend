-- Table: public.M_UNIT_NUMBER

-- DROP TABLE public.M_UNIT_NUMBER;

CREATE TABLE public.M_UNIT_NUMBER
(
    UNIT_NUMBER character varying(8) COLLATE pg_catalog.default NOT NULL,
    INSTITUTION_ID integer,
    SECTION_CODE character varying(2) COLLATE pg_catalog.default NOT NULL,
    LAST_USED_YEAR integer,
    CREATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    CREATED_DATETIME timestamp without time zone NOT NULL,
    UPDATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    UPDATED_DATETIME timestamp without time zone NOT NULL,
    CONSTRAINT M_UNIT_NUMBER_pkey PRIMARY KEY (UNIT_NUMBER)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.M_UNIT_NUMBER
    OWNER to postgres;

COMMENT ON COLUMN public.M_UNIT_NUMBER.SECTION_CODE IS '"00": Langkay; "01": Kawan; "02": Troop; "03": Outfit; "04": Circle';

