package com.coderscampus.assignment6.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
		// Read File and create user objects during FileService initialization:
		this.filePath = filePath;
		readFile();
		loadData();
	}

	private void loadData() {
		for (String line : dataArray) {
			String[] items = line.split(",");
			String[] yearRough = items[0].split("-");

			// Concatenate year to simplify logic later:
			Sales newRecord = new Sales(items[0], items[1], "20" + yearRough[1]);
			saleObjects.add(newRecord);
		}
	}

	private void parseTitle() {
		String[] titleRough = filePath.split("\\.");
		if (titleRough[0].equals("model3")) {
			title = "Model 3";
		} else if (titleRough[0].equals("modelS")) {
			title = "Model S";
		} else {
			title = "Model X";
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
		// Group sales objects by their year, using the year as the key and the sales objects as values.
		salesData = saleObjects.stream().collect(Collectors.groupingBy(Sales::getYear));
		return salesData;
	}

	void writeToFile(String title, String yearMonthMaxFormatted, String yearMonthMinFormatted,
			Map<String, BigDecimal> salesDataSums) {

		// Prepare item to write into file:
		List<String> exportData = makeFileText(title, yearMonthMaxFormatted, yearMonthMinFormatted, salesDataSums);

		// Write lines to file:
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("output.txt", true));
			for (String line : exportData) {
				System.out.println(line);
				writer.write(line);
				writer.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	private List<String> makeFileText(String title, String yearMonthMaxFormatted, String yearMonthMinFormatted,
			Map<String, BigDecimal> salesDataSums) {
		
		// Hard code the expected output format:
		List<String> exportData = new ArrayList<>();
		exportData.add(title + " Yearly Sales Report");
		exportData.add("---------------------------");
		ArrayList<String> collectEntries = new ArrayList<>();
		salesDataSums.entrySet().stream().forEach(entry -> {
			String key = entry.getKey();
			BigDecimal value = entry.getValue();
			collectEntries.add(key + " -> " + value);
		});
		
		// Workaround: file prints 2019, 2018, 2017 so reverse it:
		Collections.reverse(collectEntries);
		for (String entry: collectEntries) {
			exportData.add(entry);
		}
		
		exportData.add("\nThe best month for " + title + " was: " + yearMonthMaxFormatted);
		exportData.add("The worst month for " + title + " was: " + yearMonthMinFormatted + "\n");
		return exportData;

	}

}
