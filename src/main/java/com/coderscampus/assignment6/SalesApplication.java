package com.coderscampus.assignment6;

import com.coderscampus.assignment6.service.SalesService;

public class SalesApplication {

	public static final String[] FILES = { "model3.csv", "modelS.csv", "modelX.csv" };

	public static void main(String[] args) {
		System.out.println("Hello!");
		for (String file_path : FILES) {
			SalesService salesService = new SalesService(file_path);
			salesService.calculateData();
			salesService.writeToFile();
		}
	}

}
