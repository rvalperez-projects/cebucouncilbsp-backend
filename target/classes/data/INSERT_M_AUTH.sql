INSERT INTO public.M_AUTH(
	USER_ID, TOKEN, USERNAME, PASSWORD, ROLE_CODE, CREATED_BY, CREATED_DATETIME, UPDATED_BY, UPDATED_DATETIME)
	VALUES
	(1, 'token1', 'user1', '$2a$10$GjvKIgjykJoa8dcwE3h5ceq26yHN5agON82jLXYrD7BuW9q/yNB8y', '00', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
	(2, 'token2', 'user2', '$2a$10$GjvKIgjykJoa8dcwE3h5ceq26yHN5agON82jLXYrD7BuW9q/yNB8y', '00', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
	(3, 'token3', 'user3', '$2a$10$GjvKIgjykJoa8dcwE3h5ceq26yHN5agON82jLXYrD7BuW9q/yNB8y', '00', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
	(4, 'token4', 'council', '$2a$10$GjvKIgjykJoa8dcwE3h5ceq26yHN5agON82jLXYrD7BuW9q/yNB8y', '01', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00'),
	(5, 'token5', 'admin', '$2a$10$GjvKIgjykJoa8dcwE3h5ceq26yHN5agON82jLXYrD7BuW9q/yNB8y', '99', 'Admin', '2020-07-29 12:00:00', 'Admin', '2020-07-29 12:00:00');

