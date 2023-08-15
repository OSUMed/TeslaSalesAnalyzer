package com.coderscampus.assignment6.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.coderscampus.assignment6.domain.Sales;

public class FileService {
	private Map<String, List<Sales>> salesData;
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
			Sales newRecord = new Sales(items[0], items[1]);
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

	private Map<String, List<Sales>> groupData() {
		return salesData;
	}

	String getTitle() {
		return title;
	}

	private void writeToFile(String title) {

	}

}
