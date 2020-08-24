-- Table: public.T_ISCOM_DETAILS

-- DROP TABLE public.T_ISCOM_DETAILS;

CREATE TABLE public.T_ISCOM_DETAILS
(
    ISCOM_ID serial NOT NULL,
    FORM_ID integer NOT NULL,
    POSITION_CODE character varying(2) COLLATE pg_catalog.default NOT NULL,
    SURNAME character varying(32) COLLATE pg_catalog.default NOT NULL,
    GIVEN_NAME character varying(32) COLLATE pg_catalog.default NOT NULL,
    MIDDLE_INITIAL character varying(1) COLLATE pg_catalog.default,
    SIGNATURE character varying(32) COLLATE pg_catalog.default,
    AGE integer NOT NULL,
    MEMBERSHIP_CERT_NO character varying(32) COLLATE pg_catalog.default,
    HIGHEST_TRAINING_CODE character varying(2) COLLATE pg_catalog.default,
    TENURE integer default 0,
    RELIGION character varying(32) COLLATE pg_catalog.default,
    CREATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    CREATED_DATETIME timestamp without time zone NOT NULL,
    UPDATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    UPDATED_DATETIME timestamp without time zone NOT NULL,
    CONSTRAINT T_ISCOM_DETAILS_pkey PRIMARY KEY (ISCOM_ID, FORM_ID)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.T_ISCOM_DETAILS
    OWNER to postgres;

COMMENT ON COLUMN public.T_ISCOM_DETAILS.POSITION_CODE IS '"00": Inst''l Scouting Rep; "01": Parent Representative; "02": ISC Chair/Coor./Memb; "03": Inst''l Sctng. Coordinator; "04": Unit Leader/Circle Adviser; "05": Asst. Unit Leader/ACA';
COMMENT ON COLUMN public.T_ISCOM_DETAILS.HIGHEST_TRAINING_CODE IS '"00": Scouting Orientation; "01": Basic Training Course; "02": Advanced Training Course; "03": Course of Managers for Learning; "04": Course of Managers for Training; "05": xxx';

