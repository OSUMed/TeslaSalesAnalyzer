package com.coderscampus.assignment6.service;

import java.time.DateTimeException;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateService {
	private static final String YEAR_PATTERN = "yy";
	private static final String MONTH_PATTERN = "MMM";
	private static final String OUTPUT_PATTERN = "yyyy-MM";
	private String year;
	private String month;
	private String yearMonthString;

	public DateService(String month, String year) {
		this.month = month;
		this.year = year;
	}

	public void createFormattedDate() {
		try {
			YearMonth yearMonth = parseYearMonth();
			setYearMonthString(formatYearMonth(yearMonth));

		} catch (DateTimeParseException e) {
			System.out.println("Error DateTimeParseException: " + e.getMessage());
		} catch (DateTimeException e) {
			System.out.println("Error DateTimeException: " + e.getMessage());
		}
	}

	private YearMonth parseYearMonth() {
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern(YEAR_PATTERN, Locale.ENGLISH);
		DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern(MONTH_PATTERN, Locale.ENGLISH);
		
		// Use 'this' to refer to the instance variable
		Month month = Month.from(monthFormatter.parse(this.month));
		Year year = Year.from(yearFormatter.parse(this.year));
		
		int yearValue = year.getValue();
		int monthValue = month.getValue();
		return YearMonth.of(yearValue, monthValue);
	}

	private static String formatYearMonth(YearMonth yearMonth) {
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(OUTPUT_PATTERN, Locale.ENGLISH);
		return yearMonth.format(outputFormatter);
	}

	public String getYearMonthString() {
		return yearMonthString;
	}

	public void setYearMonthString(String yearMonthString) {
		this.yearMonthString = yearMonthString;
	}

}
