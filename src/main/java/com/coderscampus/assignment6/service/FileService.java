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

	public FileService(String filePath) {
		this.filePath = filePath;
		readFile();
//		for (String line: dataArray) {
//			System.out.println(line);
//		}
		loadData();
		for (Sales sale: saleObjects) {
			System.out.println(sale);
		}
	}

	private void loadData() {
		// TODO Auto-generated method stub
		for (String line: dataArray) {
			String[] items = line.split(",");
			Sales newRecord = new Sales(items[0], items[1]);
			saleObjects.add(newRecord);
		}
	}

	private void readFile() {
		// TODO Auto-generated method stub
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

	private String getTitle() {
		return "String";
	}

	private void writeToFile(String title) {

	}

}
