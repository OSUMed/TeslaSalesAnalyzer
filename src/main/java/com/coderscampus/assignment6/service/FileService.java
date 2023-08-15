package com.coderscampus.assignment6.service;

import java.util.List;
import java.util.Map;

import com.coderscampus.assignment6.domain.Sales;

public class FileService {
	private Map<String, List<Sales>> salesData;
	private String filePath;

	public FileService(String filePath) {
		this.filePath = filePath;
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
