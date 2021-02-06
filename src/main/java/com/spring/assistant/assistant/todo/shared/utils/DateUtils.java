package com.spring.assistant.assistant.todo.shared.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author semih on Aralık, 2019, 29.12.2019, 23:07:04
 */
@Component
@NoArgsConstructor
public class DateUtils {

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static Date stringToDate(String dob) {
		//Instantiating the SimpleDateFormat class
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		//Parsing the given String to Date object
		try {
			Date date = formatter.parse(dob);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
