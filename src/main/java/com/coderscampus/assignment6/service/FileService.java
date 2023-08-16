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

	void writeToFile(String title, String yearMonthMaxFormatted, String yearMonthMinFormatted,
			Map<String, BigDecimal> salesDataSums) {
		// Write to File Logic:
		List<String> exportData = makeFileText(title, yearMonthMaxFormatted, yearMonthMinFormatted, salesDataSums);

		// Get lines:
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
		List<String> exportData = new ArrayList<>();
		exportData.add(title + " Yearly Sales Report");
		exportData.add("---------------------------");
		ArrayList<String> collectEntries = new ArrayList<>();
		salesDataSums.entrySet().stream().forEach(entry -> {
			String key = entry.getKey();
			BigDecimal value = entry.getValue();
			collectEntries.add(key + " -> " + value);
		});
		Collections.reverse(collectEntries);
		for (String entry: collectEntries) {
			exportData.add(entry);
		}
		
		exportData.add("\nThe best month for " + title + " was: " + yearMonthMaxFormatted);
		exportData.add("\nThe worst month for " + title + " was: " + yearMonthMinFormatted + "\n");
		return exportData;

	}

}
