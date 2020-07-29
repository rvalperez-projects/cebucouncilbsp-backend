
-- Table: public.T_UNIT_REGISTRATION

-- DROP TABLE public."T_UNIT_REGISTRATION";

CREATE TABLE public."T_UNIT_REGISTRATION"
(
    "FORM_ID" serial NOT NULL,
    "INSTITUTION_ID" integer NOT NULL,
    "UNIT_NUMBER" character varying(6) COLLATE pg_catalog."default" NOT NULL,
    "UNIT_REGISTRATION_NO" character varying(10) COLLATE pg_catalog."default",
    "SECTION_CODE" character varying(2) COLLATE pg_catalog."default" NOT NULL,
    "STATUS_CODE" character varying(2) COLLATE pg_catalog."default" NOT NULL,
    "DATE_APPLIED" timestamp without time zone NOT NULL,
    "OFFICIAL_RECEIPT_NO" character varying(10) COLLATE pg_catalog."default",
    "OFFICIAL_RECEIPT_DATE" date,
    "EXPIRATION_DATE" date,
    "CREATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "CREATED_DATETIME" timestamp without time zone NOT NULL,
    "UPDATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "UPDATED_DATETIME" timestamp without time zone NOT NULL,
    CONSTRAINT "T_UNIT_REGISTRATION_pkey" PRIMARY KEY ("FORM_ID")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."T_UNIT_REGISTRATION"
    OWNER to postgres;

COMMENT ON COLUMN public."T_UNIT_REGISTRATION"."SECTION_CODE" IS '"00": Langkay; "01": Kawan; "02": Troop; "03": Outfit; "04": Circle';
COMMENT ON COLUMN public."T_UNIT_REGISTRATION"."STATUS_CODE" IS '"00": Submitted; "01": Paid; "02": Processed';

