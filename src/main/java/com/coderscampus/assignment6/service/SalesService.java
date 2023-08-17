package com.coderscampus.assignment6.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.coderscampus.assignment6.domain.Sales;

public class SalesService {
	private FileService fileService;
	private Map<String, List<Sales>> salesData;
	private Map<String, BigDecimal> salesDataSums;
	private String title;
	private Sales minMonthYear;
	private Sales maxMonthYear;
	private String yearMonthMaxFormatted;
	private String yearMonthMinFormatted;

	public SalesService(String filePath) {
		
		// Initialize Service with data grouped by year & stored title:
		fileService = new FileService(filePath);
		title = fileService.getTitle();
		salesData = fileService.groupData(); 
	}

	public void calculateData() {
		System.out.println("Print the dict: ");

		// Do Calculations:
		findSum();
		findMax();
		findMin();

		// Format dates for write to file:
		yearMonthMaxFormatted = formatDates(maxMonthYear);
		yearMonthMinFormatted = formatDates(minMonthYear);

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
			// Use min to see what element will return -1 against others, and thus, the
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
			
			// Use the add method from the BigDecimal class as the accumulator method for reduce()
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

	public void writeToFile() {
		fileService.writeToFile(title, yearMonthMaxFormatted, yearMonthMinFormatted, salesDataSums);
	}
}
