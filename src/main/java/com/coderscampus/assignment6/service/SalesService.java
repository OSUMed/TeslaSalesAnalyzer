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
	private Sales minMonthYear;
	private Sales maxMonthYear;
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

		// Get the Max and Min:
		
		findSum();
		findMax();
		findMin();
		
		
		yearMonthMaxFormatted = formatDates(maxMonthYear);
		yearMonthMinFormatted = formatDates(minMonthYear);
		
//		String[] minMonthYearList = minMonthYear.getDate().split("-");
//		String[] maxMonthYearList = maxMonthYear.getDate().split("-");
//		DateService dateServiceMin = new DateService(minMonthYearList[0], minMonthYearList[1]);
//		DateService dateServiceMax = new DateService(maxMonthYearList[0], maxMonthYearList[1]);
//		yearMonthMaxFormatted = dateServiceMin.getYearMonthString();
//		yearMonthMinFormatted = dateServiceMax.getYearMonthString();

//		System.out.println(maxMonthYear);
//		System.out.println(yearMonthMaxFormatted);
//		System.out.println(minMonthYear);
//		System.out.println(yearMonthMinFormatted);
	}

	private void findMax() {
		maxMonthYear = new Sales("0");

		// Find Max:
		salesData.entrySet().stream().forEach(entry -> {
			String key = entry.getKey();
			List<Sales> salesList = entry.getValue();
			// Use max to see what element will return -1 against others, and thus, the
			// greatest
			Optional<Sales> maxSales = salesList.stream()
					.max((sales1, sales2) -> sales1.getSales().compareTo(sales2.getSales()));
			if (maxSales.isPresent()) {
				if (maxSales.get().getSales().compareTo(maxMonthYear.getSales()) > 0) {
					maxMonthYear = maxSales.get();
				}
			}
		});
	}

	private void findMin() {
		minMonthYear = new Sales(Integer.toString(Integer.MAX_VALUE));
		// Find Min:
		salesData.entrySet().stream().forEach(entry -> {
			String key = entry.getKey();
			List<Sales> salesList = entry.getValue();
			// Use max to see what element will return -1 against others, and thus, the
			// greatest
			Optional<Sales> minSales = salesList.stream()
					.min((sales1, sales2) -> sales1.getSales().compareTo(sales2.getSales()));
			if (minSales.isPresent()) {
				if (minSales.get().getSales().compareTo(minMonthYear.getSales()) < 0) {
					minMonthYear = minSales.get();
				}
			}
		});
	}

	private void findSum() {
		// Get the Sum:
		this.salesDataSums = new HashMap<String, BigDecimal>();
		salesData.entrySet().stream().forEach(entry -> {
			String key = entry.getKey();
			List<Sales> listValues = entry.getValue();
			BigDecimal sum = listValues.stream().map(salesItem -> salesItem.getSales()).reduce(BigDecimal.ZERO,
					(accumulator, value) -> accumulator.add(value));
			this.salesDataSums.put(key, sum);
		});
	}

	private String formatDates(Sales yearMonth) {
		String[] monthYearList = yearMonth.getDate().split("-");
		DateService dateService = new DateService(monthYearList[0], monthYearList[1]);
		String yearMonthFormatted = dateService.getYearMonthString();
		return yearMonthFormatted;
	}

//	private void writeToFile() {
////        fileService.writeToFile(pass title, formatted max/min, salesDataSums)
}
