CREATE DATABASE IF NOT EXISTS "cebucouncilbsp-db";

USE "cebucouncilbsp-db";

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

-- Table: public.M_INSTITUTION

-- DROP TABLE public."M_INSTITUTION";

CREATE TABLE public."M_INSTITUTION"
(
    "INSTITUTION_ID" serial NOT NULL,
    "INSTITUTION_NAME" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "ADDRESS" character varying(64) COLLATE pg_catalog."default" NOT NULL,
    "CONTACT_NUMBER" character varying(32) COLLATE pg_catalog."default",
    "DISTRICT" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "AREA" character varying(3) COLLATE pg_catalog."default",
    "CATEGORY_CODE" character varying(2) COLLATE pg_catalog."default" NOT NULL,
    "CREATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "CREATED_DATETIME" timestamp without time zone NOT NULL,
    "UPDATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "UPDATED_DATETIME" timestamp without time zone NOT NULL,
    CONSTRAINT "M_INSTITUTION_pkey" PRIMARY KEY ("INSTITUTION_ID")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."M_INSTITUTION"
    OWNER to postgres;

COMMENT ON COLUMN public."M_INSTITUTION"."CATEGORY_CODE" IS '"00": preschool; "01": primary; "02": secondary; "03": senior high; "04": college; "05": community';

-- Table: public.M_AREA

-- DROP TABLE public."M_AREA";

CREATE TABLE public."M_AREA"
(
    "AREA_CODE" character varying(3) COLLATE pg_catalog."default" NOT NULL,
    "DISTRICT_NAME" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "CHAIRMAN_NAME" character varying(32) COLLATE pg_catalog."default",
    "CHAIRMAN_CONTACT_NO" character varying(32) COLLATE pg_catalog."default",
    "COMMISSIONER_NAME" character varying(32) COLLATE pg_catalog."default",
    "COMMISSIONER_CONTACT_NO" character varying(31) COLLATE pg_catalog."default",
    "REMARKS" character varying(256) COLLATE pg_catalog."default",
    "CREATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "CREATED_DATETIME" timestamp without time zone NOT NULL,
    "UPDATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "UPDATED_DATETIME" timestamp without time zone NOT NULL,
    CONSTRAINT "M_AREA_pkey" PRIMARY KEY ("AREA_CODE", "DISTRICT_NAME")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."M_AREA"
    OWNER to postgres;

-- Table: public.M_UNIT_NUMBER

-- DROP TABLE public."M_UNIT_NUMBER";

CREATE TABLE public."M_UNIT_NUMBER"
(
    "UNIT_NUMBER" character varying(6) COLLATE pg_catalog."default" NOT NULL,
    "INSTITUTION_ID" integer,
    "SECTION_CODE" character varying(2) COLLATE pg_catalog."default" NOT NULL,
    "LAST_USED_YEAR" character varying(4) COLLATE pg_catalog."default",
    "CREATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "CREATED_DATETIME" timestamp without time zone NOT NULL,
    "UPDATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "UPDATED_DATETIME" timestamp without time zone NOT NULL,
    CONSTRAINT "M_UNIT_NUMBER_pkey" PRIMARY KEY ("UNIT_NUMBER")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."M_UNIT_NUMBER"
    OWNER to postgres;

COMMENT ON COLUMN public."M_UNIT_NUMBER"."SECTION_CODE" IS '"00": Langkay; "01": Kawan; "02": Troop; "03": Outfit; "04": Circle';

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

-- Table: public.T_ISCOM_DETAILS

-- DROP TABLE public."T_ISCOM_DETAILS";

CREATE TABLE public."T_ISCOM_DETAILS"
(
    "ISCOM_ID" serial NOT NULL,
    "FORM_ID" integer NOT NULL,
    "POSITION_CODE" character varying(2) COLLATE pg_catalog."default" NOT NULL,
    "SURNAME" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "GIVEN_NAME" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "MIDDLE_INITIAL" character varying(1) COLLATE pg_catalog."default",
    "SIGNATURE" character varying(32) COLLATE pg_catalog."default",
    "AGE" integer NOT NULL,
    "MEMBERSHIP_CERT_NO" character varying(32) COLLATE pg_catalog."default",
    "HIGHEST_TRAINING_CODE" character varying(2) COLLATE pg_catalog."default",
    "TENURE" integer,
    "RELIGION" character varying(32) COLLATE pg_catalog."default",
    "CREATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "CREATED_DATETIME" timestamp without time zone NOT NULL,
    "UPDATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "UPDATED_DATETIME" timestamp without time zone NOT NULL,
    CONSTRAINT "T_ISCOM_DETAILS_pkey" PRIMARY KEY ("ISCOM_ID", "FORM_ID")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."T_ISCOM_DETAILS"
    OWNER to postgres;

COMMENT ON COLUMN public."T_ISCOM_DETAILS"."POSITION_CODE" IS '"00": Inst''l Scouting Rep; "01": Parent Representative; "02": ISC Chair/Coor./Memb; "03": Inst''l Sctng. Coordinator; "04": Unit Leader/Circle Adviser; "05": Asst. Unit Leader/ACA';
COMMENT ON COLUMN public."T_ISCOM_DETAILS"."HIGHEST_TRAINING_CODE" IS '"00": Scouting Orientation; "01": Basic Training Course; "02": Advanced Training Course; "03": Course of Managers for Learning; "04": Course of Managers for Training; "05": xxx';

-- Table: public.T_MEMBER_DETAILS

-- DROP TABLE public."T_MEMBER_DETAILS";

CREATE TABLE public."T_MEMBER_DETAILS"
(
    "MEMBER_ID" serial NOT NULL,
    "FORM_ID" integer NOT NULL,
    "POSITION_CODE" character varying(2) COLLATE pg_catalog."default" NOT NULL,
    "SURNAME" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "GIVEN_NAME" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "MIDDLE_INITIAL" character varying(1) COLLATE pg_catalog."default",
    "REGISTRATION_STATUS_CODE" character varying(2) COLLATE pg_catalog."default" NOT NULL,
    "AGE" integer NOT NULL,
    "MEMBERSHIP_CERT_NO" character varying(32) COLLATE pg_catalog."default",
    "HIGHEST_BADGE_CODE" character varying(2) COLLATE pg_catalog."default",
    "TENURE" integer,
    "RELIGION" character varying(32) COLLATE pg_catalog."default",
    "CREATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "CREATED_DATETIME" timestamp without time zone NOT NULL,
    "UPDATED_BY" character varying(32) COLLATE pg_catalog."default" NOT NULL,
    "UPDATED_DATETIME" timestamp without time zone NOT NULL,
    CONSTRAINT "T_MEMBER_DETAILS_pkey" PRIMARY KEY ("MEMBER_ID", "FORM_ID")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."T_MEMBER_DETAILS"
    OWNER to postgres;

COMMENT ON COLUMN public."T_MEMBER_DETAILS"."POSITION_CODE" IS '"00": SPL; "01": SCL; "02": RL; "03": ARL1; "04": ARL2; "05": AUDITOR; "06": SCRIBE; "07": TREASURER; "08": QUARTERMASTER; "09": MEMBER';
COMMENT ON COLUMN public."T_MEMBER_DETAILS"."REGISTRATION_STATUS_CODE" IS '"N": New; "RR": Re-registering';
COMMENT ON COLUMN public."T_MEMBER_DETAILS"."HIGHEST_BADGE_CODE" IS '"00": Membership; "01": Young USA; "02": Growing USA; "03": Leaping USA; "10": Tenderfoot; "11": Second Class; "12": First Class; "21": Explorer; "22": Pathfinder; "23": Outdoorsman; "24": Venturer; "25": Eagle; "30": Yellow Quadrant; "31": Green Quadrant; "32": Red Quadrant; "33": Blue Quadrant; "34": Chief Scout''s Nation Builder';