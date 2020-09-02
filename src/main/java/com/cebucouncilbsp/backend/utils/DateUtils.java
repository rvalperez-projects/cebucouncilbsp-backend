/**
 *
 */
package com.cebucouncilbsp.backend.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author reneir.val.t.perez
 *
 */
public class DateUtils {

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

	public static LocalDateTime getCurrentDateTime() {
		ZonedDateTime nowInPH = ZonedDateTime.now(ZoneId.of("Asia/Manila"));
		return nowInPH.toLocalDateTime();
	}

	public static LocalDate getCurrentDate() {
		ZonedDateTime nowInPH = ZonedDateTime.now(ZoneId.of("Asia/Manila"));
		return nowInPH.toLocalDate();
	}

	public static String getFormattedDate() {
		ZonedDateTime nowInPH = ZonedDateTime.now(ZoneId.of("Asia/Manila"));
		return nowInPH.toLocalDateTime().format(formatter);
	}
}
