package com.coderscampus.assignment6.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.coderscampus.assignment6.domain.Sales;

public class FileService {
	private Map<String, List<Sales>> salesData = new HashMap<>();
	private ArrayList<Sales> saleObjects = new ArrayList<>();
	private List<String> dataArray = new ArrayList<String>();
	private String filePath;
	private String title;

	public FileService(String filePath) {
		this.filePath = filePath;
		readFile();
		loadData();
	}

	private void loadData() {
		// TODO Auto-generated method stub
		for (String line : dataArray) {
			String[] items = line.split(",");
			String[] yearRough = items[0].split("-");

			Sales newRecord = new Sales(items[0], items[1], "19" + yearRough[1]);
			saleObjects.add(newRecord);
		}
	}

	private void parseTitle() {
		String[] titleRough = filePath.split("\\.");
		if (titleRough[0].equals("model3")) {
			title = "Model 3";
		} else if (titleRough[0].equals("modelX")) {
			title = "Model X";
		} else if (titleRough[0].equals("modelS")) {
			title = "Model S";
		}

	}

	String getTitle() {
		return title;
	}

	private void readFile() {
		// Get Title:
		parseTitle();

		// Get lines:
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line;
			Boolean firstLine = true;
			while ((line = reader.readLine()) != null) {
				if (firstLine) {
					firstLine = false;
					continue;
				}
				this.dataArray.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	Map<String, List<Sales>> groupData() {
		salesData = saleObjects.stream().collect(Collectors.groupingBy(Sales::getYear));

		for (Map.Entry<String, List<Sales>> entry : salesData.entrySet()) {
			String key = entry.getKey();
			List<Sales> salesList = entry.getValue();

			System.out.println("Key: " + key + ", Value: " + salesList);
		}

		return salesData;
	}

	private void writeToFile(Map<String, BigDecimal> salesDataSums, String title, String bestMonth, String minMonth) {
		// Write to File Logic:
	}

	private List<String> makeFileText(Map<String, BigDecimal> salesDataSums, String title, String bestMonth,
			String minMonth) {
		List<String> exportData = new ArrayList<>();
//		exportData.add ( {title} Yearly Sales Report )
//		exportData.add ( --------------------------- )
//		---------------------------
//      for each in salesDataSums
//            exportData.add(salesDataSums[key] -> salesDataSums[value]
//      
//		
//		exportData.add (\nThe best month for {title} was: {bestMonth})
//		 exportData.add (The worst month for {title} was: {minMonth})
		return exportData;

	}

}
