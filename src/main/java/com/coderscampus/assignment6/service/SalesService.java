package com.coderscampus.assignment6.service;

import java.util.List;
import java.util.Map;

import com.coderscampus.assignment6.domain.Sales;

public class SalesService {
	private FileService fileService;
//	private DateService dateService;
	private Map<String, List<Sales>> salesData;
//	private Map<String,BigDecimal<>>salesDataSums;
//	private Integer sum;
	private String title;
//	private String minMonthYear;
//	private String maxMonthYear;
//	private String yearMonthMaxFormatted;
//	private String yearMonthMinFormatted;

	public SalesService(String filePath) {
		System.out.println("Inside sales service! " + filePath);
        fileService = new FileService(filePath);
        title = fileService.getTitle();
  
        salesData = fileService.groupData(); // -> sales objects
	}

//	private void calculateData() {
//
////        for key in salesData:
////            yearMonthMax = findMax(value)
////            yearMonthMin = findMin(value)
////            3 times so try updating yearMonthMax/yearMonthMin
////        for key in salesData:
////            findSum(value)
//	}
//
//	private void findMax() {
//
////        Stream -> max
////        return month & year
//	}
//
//	private void findMin() {
//
////    	Stream -> min
////    	return month & year
//	}
//
//	private void findSum() {
//
////    	Stream -> update salesDataSums
//	}
//
//	private void formatDates(String yearMonth) {
//
////		year, month = yearMonth
////				dateService = new DateService(month, year)
////				dateService.createFormattedDate
//	}
//
//	private void writeToFile() {
////        fileService.writeToFile(pass title, formatted max/min, salesDataSums)
}
