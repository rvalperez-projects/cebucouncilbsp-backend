-- Table: public.M_USER

-- DROP TABLE public."M_USER";

CREATE TABLE public."M_USER"
(
    "USER_ID" serial NOT NULL,
    "SURNAME" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "GIVEN_NAME" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "MIDDLE_INITIAL" character varying(1) COLLATE pg_catalog."default",
    "EMAIL_ADDRESS" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "MOBILE_NUMBER" character varying(32) COLLATE pg_catalog."default",
    "USERNAME" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "PASSWORD" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "AUTHORITY_CODE" character varying(2) COLLATE pg_catalog."default" NOT NULL,
    "INSTITUTION_ID" integer,
    "CREATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "CREATED_DATETIME" timestamp without time zone NOT NULL,
    "UPDATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "UPDATED_DATETIME" timestamp without time zone NOT NULL,
    CONSTRAINT "M_USER_pkey" PRIMARY KEY ("USER_ID")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."M_USER"
    OWNER to postgres;

COMMENT ON COLUMN public."M_USER"."AUTHORITY_CODE" IS '"00": General User; "01": Council; "99": Admin;';

