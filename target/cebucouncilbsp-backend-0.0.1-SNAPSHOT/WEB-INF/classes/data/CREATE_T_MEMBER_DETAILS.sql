-- Table: public.T_MEMBER_DETAILS

-- DROP TABLE public.T_MEMBER_DETAILS;

CREATE TABLE public.T_MEMBER_DETAILS
(
    MEMBER_ID serial NOT NULL,
    FORM_ID integer NOT NULL,
    POSITION_CODE character varying(2) COLLATE pg_catalog.default NOT NULL,
    SURNAME character varying(32) COLLATE pg_catalog.default NOT NULL,
    GIVEN_NAME character varying(32) COLLATE pg_catalog.default NOT NULL,
    MIDDLE_INITIAL character varying(1) COLLATE pg_catalog.default,
    REGISTRATION_STATUS_CODE character varying(2) COLLATE pg_catalog.default NOT NULL,
    AGE integer NOT NULL,
    MEMBERSHIP_CERT_NO character varying(32) COLLATE pg_catalog.default,
    HIGHEST_BADGE_CODE character varying(2) COLLATE pg_catalog.default,
    TENURE integer default 0,
    RELIGION character varying(32) COLLATE pg_catalog.default,
    CREATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    CREATED_DATETIME timestamp without time zone NOT NULL,
    UPDATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    UPDATED_DATETIME timestamp without time zone NOT NULL,
    CONSTRAINT T_MEMBER_DETAILS_pkey PRIMARY KEY (MEMBER_ID, FORM_ID)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.T_MEMBER_DETAILS
    OWNER to postgres;

COMMENT ON COLUMN public.T_MEMBER_DETAILS.POSITION_CODE IS '"00": SPL; "01": SCL; "02": RL; "03": ARL1; "04": ARL2; "05": AUDITOR; "06": SCRIBE; "07": TREASURER; "08": QUARTERMASTER; "09": MEMBER';
COMMENT ON COLUMN public.T_MEMBER_DETAILS.REGISTRATION_STATUS_CODE IS '"N": New; "RR": Re-registering';
COMMENT ON COLUMN public.T_MEMBER_DETAILS.HIGHEST_BADGE_CODE IS '"00": Membership; "01": Young USA; "02": Growing USA; "03": Leaping USA; "10": Tenderfoot; "11": Second Class; "12": First Class; "21": Explorer; "22": Pathfinder; "23": Outdoorsman; "24": Venturer; "25": Eagle; "30": Yellow Quadrant; "31": Green Quadrant; "32": Red Quadrant; "33": Blue Quadrant; "34": Chief Scout''s Nation Builder';
