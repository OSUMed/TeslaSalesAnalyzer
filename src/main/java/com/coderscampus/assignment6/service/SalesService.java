package com.coderscampus.assignment6.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.coderscampus.assignment6.domain.Sales;

public class SalesService {
	private FileService fileService;
//	private DateService dateService;
	private Map<String, List<Sales>> salesData;
	private Map<String, BigDecimal> salesDataSums;
//	private Integer sum;
	private String title;
	private String minMonthYear;
	private String maxMonthYear;
	private String yearMonthMaxFormatted;
	private String yearMonthMinFormatted;

	public SalesService(String filePath) {
		System.out.println("Inside sales service! " + filePath);
		fileService = new FileService(filePath);
		title = fileService.getTitle();

		salesData = fileService.groupData(); // -> sales objects
	}

	public void calculateData() {
		System.out.println("Print the dict: ");
		
		// Get the Sum:
		this.salesDataSums = new HashMap<String, BigDecimal>();
		salesData.entrySet().stream().forEach(entry -> {
			String key = entry.getKey();
			List<Sales> listValues = entry.getValue();
			BigDecimal sum = listValues.stream().map(salesItem -> salesItem.getSales()).reduce(BigDecimal.ZERO,
					(accumulator, value) -> accumulator.add(value));
			this.salesDataSums.put(key, sum);
		});
		
		// Get the Max and Min:
		BigDecimal globalMax = 0;
		salesData.entrySet().stream().forEach(entry -> {
			String key = entry.getKey();
			List<Sales> salesList = entry.getValue();
		  // Using max to find the Sales object with the highest sales value
	        Optional<Sales> maxSales = salesList.stream()
	            .max((sales1, sales2) -> sales1.getSales().compareTo(sales2.getSales()));
	        if (maxSales.isPresent()) {	        	
				globalMax = Math.max(globalMax, maxSales.get().getSales());
	        }

			this.salesDataSums.put(key, sum);
		});
//		for (Map.Entry<String, BigDecimal> entry2 : this.salesDataSums.entrySet()) {
//			String key2 = entry2.getKey();
//			BigDecimal value2 = entry2.getValue();
//			System.out.println(key2 + " total is: " + value2);
//		}
//        for key in salesData:
//            yearMonthMax = findMax(value)
//            yearMonthMin = findMin(value)
//            3 times so try updating yearMonthMax/yearMonthMin
//		  
//		  Get the formatted dates for exporting:
//		  yearMonthMaxFormatted = getDate(yearMonthMax)
//		  yearMonthMinFormatted = getDate(yearMonthMin)

//		  Go through each year and sum them and insert into gra:
//        for key in salesData:
//            sum = findSum(value)
//		  	  salesDataSums[key] = sum
	}
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
