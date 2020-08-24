INSERT INTO public.M_USER(
	SURNAME, GIVEN_NAME, MIDDLE_INITIAL, EMAIL_ADDRESS, MOBILE_NUMBER, USERNAME, PASSWORD, AUTHORITY_CODE, INSTITUTION_ID, CREATED_BY, CREATED_DATETIME, UPDATED_BY, UPDATED_DATETIME)
	VALUES
	('Dela Cruz', 'Juan', 'M', 'email@email.com', '09101234567', 'user1', 'password', '00', 0, 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
	('Roberto', 'Gerald', 'Q', 'email@email.com', '09101234567', 'user2', 'password', '00', 0, 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
	('Fernandez', 'Natanael', 'A', 'email@email.com', '09101234567', 'user3', 'password', '00', 0, 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
	('Laurel', 'Blake', 'S', 'email@email.com', '09101234567', 'council', 'password', '01', 0, 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
	('Espa¡¦', 'Nicholas', null, 'email@email.com', '09101234567', 'admin', 'password', '99', 0, 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00');

INSERT INTO public.M_AREA(
	AREA_CODE, DISTRICT_NAME, CHAIRMAN_NAME, CHAIRMAN_CONTACT_NO, COMMISSIONER_NAME, COMMISSIONER_CONTACT_NO, REMARKS, CREATED_BY, CREATED_DATETIME, UPDATED_BY, UPDATED_DATETIME)
	VALUES
		('I', 'North 1', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'North 2', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'North 3', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'North 4', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'North 5', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'North 6', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'North 7', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'North 8', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'South 1', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'South 2', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'South 3', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'South 4', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'South 5', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'South 6', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'South 7', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('I', 'South 8', null, null, null, null, '', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00');

INSERT INTO public.M_INSTITUTION(
	INSTITUTION_NAME, ADDRESS, CONTACT_NUMBER, DISTRICT, AREA, CATEGORY_CODE, CREATED_BY, CREATED_DATETIME, UPDATED_BY, UPDATED_DATETIME)
	VALUES
		('Tejero Elementary School', 'MJ Cuenco Avenue, Cebu City', null, 'North 5', 'I', '01', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('Tejero National High School', 'MJ Cuenco Avenue, Cebu City', null, 'North 5', 'I', '02', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('Cebu City Central Elementary School', 'N. Bacalso Avenue, Cebu City', null, 'North 1', 'I', '01', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('Cebu City National Science High School', 'Brgy. Labangon, Cebu City', null, 'South 5', 'I', '02', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00');

INSERT INTO public.M_UNIT_NUMBER(
	UNIT_NUMBER, INSTITUTION_ID, SECTION_CODE, LAST_USED_YEAR, CREATED_BY, CREATED_DATETIME, UPDATED_BY, UPDATED_DATETIME)
	VALUES
		('L-21', 1, '00', '2019', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('L-33', 1, '00', '2017', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('K-092', 1, '01', '2019', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('K-093', null, '01', null, 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('T-099', 1, '02', '2020', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('T-100', 1, '02', '2019', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('T-101', null, '02', null, 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('S-190', 2, '03', '2020', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('S-191', 2, '03', '2019', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('R-32', 2, '04', '2020', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('R-33', 2, '04', '2019', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		('R-34', null, '04', null, 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00');

INSERT INTO public.T_UNIT_REGISTRATION(
	INSTITUTION_ID, UNIT_NUMBER, UNIT_REGISTRATION_NO, SECTION_CODE, STATUS_CODE, DATE_APPLIED, OFFICIAL_RECEIPT_NO, OFFICIAL_RECEIPT_DATE, EXPIRATION_DATE, CREATED_BY, CREATED_DATETIME, UPDATED_BY, UPDATED_DATETIME)
	VALUES
		(1, 'T-099', null, '02', '00', '2020-07-29 12:00:00', null, null, null, 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00');

INSERT INTO public.T_ISCOM_DETAILS(
	FORM_ID, POSITION_CODE, SURNAME, GIVEN_NAME, MIDDLE_INITIAL, SIGNATURE, AGE, MEMBERSHIP_CERT_NO, HIGHEST_TRAINING_CODE, TENURE, RELIGION, CREATED_BY, CREATED_DATETIME, UPDATED_BY, UPDATED_DATETIME)
	VALUES
		(1, '00', 'Surname 1', 'Given Name 1', 'A', null, 45, null, '02', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '01', 'Surname 2', 'Given Name 2', 'A', null, 41, null, '00', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '02', 'Surname 3', 'Given Name 3', 'A', null, 30, null, '01', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '03', 'Surname 4', 'Given Name 4', 'A', null, 28, null, '03', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '04', 'Surname 5', 'Given Name 5', 'A', null, 40, null, '02', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '05', 'Surname 6', 'Given Name 6', 'A', null, 28, null, '01', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '06', 'Surname 7', 'Given Name 7', 'A', null, 23, null, '01', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00');

INSERT INTO public.T_MEMBER_DETAILS(
	FORM_ID, POSITION_CODE, SURNAME, GIVEN_NAME, MIDDLE_INITIAL, REGISTRATION_STATUS_CODE, AGE, MEMBERSHIP_CERT_NO, HIGHEST_BADGE_CODE, TENURE, RELIGION, CREATED_BY, CREATED_DATETIME, UPDATED_BY, UPDATED_DATETIME)
	VALUES
		(1, '00', 'Surname 1', 'Given Name 1', 'A', 'RR', 12, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '06', 'Surname 2', 'Given Name 2', 'A', 'RR', 12, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '07', 'Surname 3', 'Given Name 3', 'A', 'RR', 12, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '08', 'Surname 4', 'Given Name 4', 'A', 'RR', 12, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-1-1', 'Given Name 1', 'A', 'RR', 10, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-1-2', 'Given Name 2', 'A', 'RR', 10, null, '11', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-1-3', 'Given Name 3', 'A', 'RR', 10, null, '11', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-1-4', 'Given Name 4', 'A', 'RR', 10, null, '10', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-1-5', 'Given Name 5', 'A', 'RR', 10, null, '10', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-1-6', 'Given Name 6', 'A', 'RR', 11, null, '10', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-1-7', 'Given Name 7', 'A', 'RR', 11, null, '11', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-1-8', 'Given Name 8', 'A', 'RR', 11, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-2-1', 'Given Name 1', 'A', 'N', 10, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-2-2', 'Given Name 2', 'A', 'N', 10, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-2-3', 'Given Name 3', 'A', 'N', 10, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-2-4', 'Given Name 4', 'A', 'N', 10, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-2-5', 'Given Name 5', 'A', 'N', 10, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-2-6', 'Given Name 6', 'A', 'N', 11, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-2-7', 'Given Name 7', 'A', 'N', 11, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-2-8', 'Given Name 8', 'A', 'N', 11, null, '12', 6, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-3-1', 'Given Name 1', 'A', 'RR', 10, null, '11', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-3-2', 'Given Name 2', 'A', 'RR', 10, null, '11', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-3-3', 'Given Name 3', 'A', 'RR', 10, null, '11', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-3-4', 'Given Name 4', 'A', 'RR', 10, null, '11', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-3-5', 'Given Name 5', 'A', 'RR', 10, null, '11', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-3-6', 'Given Name 6', 'A', 'RR', 11, null, '11', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-3-7', 'Given Name 7', 'A', 'RR', 11, null, '11', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-3-8', 'Given Name 8', 'A', 'RR', 11, null, '11', 5, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-4-1', 'Given Name 1', 'A', 'N', 10, null, '9', 4, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-4-2', 'Given Name 2', 'A', 'N', 10, null, '9', 4, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-4-3', 'Given Name 3', 'A', 'N', 10, null, '9', 4, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-4-4', 'Given Name 4', 'A', 'N', 10, null, '9', 4, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-4-5', 'Given Name 5', 'A', 'N', 10, null, '9', 4, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-4-6', 'Given Name 6', 'A', 'N', 11, null, '9', 4, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-4-7', 'Given Name 7', 'A', 'N', 11, null, '9', 4, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
		(1, '09', 'Patrol-4-8', 'Given Name 8', 'A', 'N', 11, null, '9', 4, 'Roman Catholic', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00');
