package com.coderscampus.assignment6.domain;

import java.math.BigDecimal;

public class Sales {
	private String date;
	private String year;
	private BigDecimal sales;

	
	public Sales(String date, String sales, String year) {
		this.date = date;
		this.sales = new BigDecimal(sales);
		this.year = year;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public BigDecimal getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = new BigDecimal(sales);
	}
	
	@Override
	public String toString() {
		return "Sales [date=" + date + ", year=" + year + ", sales=" + sales + "]";
	}
	
	
}
