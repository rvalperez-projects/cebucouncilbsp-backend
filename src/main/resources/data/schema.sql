-- Table: M_USER

-- DROP TABLE M_USER;

CREATE TABLE M_USER
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

-- Table: M_AUTH

-- DROP TABLE M_AUTH;

CREATE TABLE M_AUTH
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
      FOREIGN KEY (USER_ID) REFERENCES m_user(USER_ID)
      ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

COMMENT ON COLUMN m_auth.ROLE_CODE IS '"00": General User; "01": Council; "99": Admin;';

-- Table: M_AREA

-- DROP TABLE M_AREA;

CREATE TABLE M_AREA
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

-- Table: M_INSTITUTION

-- DROP TABLE M_INSTITUTION;

CREATE TABLE M_INSTITUTION
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

COMMENT ON COLUMN M_INSTITUTION.CATEGORY_CODE IS '"00": preschool; "01": primary; "02": secondary; "03": senior high; "04": college; "05": community';

-- Table: M_UNIT_NUMBER

-- DROP TABLE M_UNIT_NUMBER;

CREATE TABLE M_UNIT_NUMBER
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

COMMENT ON COLUMN M_UNIT_NUMBER.SECTION_CODE IS '"00": Langkay; "01": Kawan; "02": Troop; "03": Outfit; "04": Circle';


-- Table: T_UNIT_REGISTRATION

-- DROP TABLE T_UNIT_REGISTRATION;

CREATE TABLE T_UNIT_REGISTRATION
(
    FORM_ID serial NOT NULL,
    INSTITUTION_ID integer NOT NULL,
    UNIT_NUMBER character varying(8) COLLATE pg_catalog.default NOT NULL,
    UNIT_REGISTRATION_NO character varying(10) COLLATE pg_catalog.default,
    SECTION_CODE character varying(2) COLLATE pg_catalog.default NOT NULL,
    CHARTER_FLAG boolean default false NOT NULL,
    STATUS_CODE character varying(2) COLLATE pg_catalog.default NOT NULL,
    DATE_APPLIED timestamp without time zone NOT NULL,
    OFFICIAL_RECEIPT_NO character varying(10) COLLATE pg_catalog.default,
    OFFICIAL_RECEIPT_DATE date,
    EXPIRATION_DATE date,
	COUNCIL_REGISTRATION_OFFICER character varying(32),
	COUNCIL_PROCESSED_DATE timestamp without time zone,
	COUNCIL_SCOUT_EXECUTIVE character varying(32),
	COUNCIL_APPROVED_DATE date,
	REGIONAL_REGISTRATION_OFFICER character varying(32),
	REGIONAL_PROCESSED_DATE timestamp without time zone,
	REGIONAL_SCOUT_EXECUTIVE character varying(32),
	REGIONAL_APPROVED_DATE date,
    CREATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    CREATED_DATETIME timestamp without time zone NOT NULL,
    UPDATED_BY character varying(32) COLLATE pg_catalog.default NOT NULL,
    UPDATED_DATETIME timestamp without time zone NOT NULL,
    CONSTRAINT T_UNIT_REGISTRATION_pkey PRIMARY KEY (FORM_ID)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

COMMENT ON COLUMN T_UNIT_REGISTRATION.SECTION_CODE IS '"00": Langkay; "01": Kawan; "02": Troop; "03": Outfit; "04": Circle';
COMMENT ON COLUMN T_UNIT_REGISTRATION.STATUS_CODE IS '"00": Submitted; "01": Paid; "02": Processed';

-- Table: T_ISCOM_DETAILS

-- DROP TABLE T_ISCOM_DETAILS;

CREATE TABLE T_ISCOM_DETAILS
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

COMMENT ON COLUMN T_ISCOM_DETAILS.POSITION_CODE IS '"00": Inst''l Scouting Rep; "01": Parent Representative; "02": ISC Chair/Coor./Memb; "03": Inst''l Sctng. Coordinator; "04": Unit Leader/Circle Adviser; "05": Asst. Unit Leader/ACA';
COMMENT ON COLUMN T_ISCOM_DETAILS.HIGHEST_TRAINING_CODE IS '"00": Scouting Orientation; "01": Basic Training Course; "02": Advanced Training Course; "03": Course of Managers for Learning; "04": Course of Managers for Training; "05": xxx';

-- Table: T_MEMBER_DETAILS

-- DROP TABLE T_MEMBER_DETAILS;

CREATE TABLE T_MEMBER_DETAILS
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

COMMENT ON COLUMN T_MEMBER_DETAILS.POSITION_CODE IS '"00": SPL; "01": SCL; "02": RL; "03": ARL1; "04": ARL2; "05": AUDITOR; "06": SCRIBE; "07": TREASURER; "08": QUARTERMASTER; "09": MEMBER';
COMMENT ON COLUMN T_MEMBER_DETAILS.REGISTRATION_STATUS_CODE IS '"N": New; "RR": Re-registering';
COMMENT ON COLUMN T_MEMBER_DETAILS.HIGHEST_BADGE_CODE IS '"00": Membership; "01": Young USA; "02": Growing USA; "03": Leaping USA; "10": Tenderfoot; "11": Second Class; "12": First Class; "21": Explorer; "22": Pathfinder; "23": Outdoorsman; "24": Venturer; "25": Eagle; "30": Yellow Quadrant; "31": Green Quadrant; "32": Red Quadrant; "33": Blue Quadrant; "34": Chief Scout''s Nation Builder';
