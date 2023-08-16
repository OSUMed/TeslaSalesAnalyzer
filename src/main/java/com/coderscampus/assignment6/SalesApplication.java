package com.coderscampus.assignment6;

import com.coderscampus.assignment6.service.SalesService;

public class SalesApplication {
	
	public static final String[] FILES = { "model3.csv", "modelS.csv", "modelS.csv" };
	public static final String[] FILES_TEST = { "modelS.csv" };

	public static void main(String[] args) {
		System.out.println("Hello!");
		for (String file_path: FILES) {
			SalesService salesService = new SalesService(file_path);
			salesService.calculateData();
			salesService.writeToFile();
		}
	}
	
	// To Dos:
	// 1. get sum
	// 2. get min and remember year
	// 3. get max and remember year
	// 4. write to file
	// 
	// 

}
