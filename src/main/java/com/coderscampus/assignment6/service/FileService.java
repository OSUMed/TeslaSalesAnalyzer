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
	      for (Sales s: saleObjects) {
	        	System.out.println(s);
	        }

	}

	private void loadData() {
		// TODO Auto-generated method stub
		for (String line : dataArray) {
			String[] items = line.split(",");
			String[] yearRough = items[0].split("-");
			for (String i: yearRough) {
				
				System.out.println(i);
			}
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
		for (Sales sale: saleObjects) {
			
		}
		
		
		
		
		return salesData;
	}

	private void writeToFile(String title) {

	}

}
