package com.coderscampus.assignment6;

import com.coderscampus.assignment6.service.SalesService;

public class SalesApplication {
	
	public static final String[] FILES = { "model3.csv", "modelS.csv", "modelS.csv" };
	public static final String[] FILES_TEST = { "modelS.csv" };

	public static void main(String[] args) {
		for (String file_path: FILES_TEST) {
			SalesService salesService = new SalesService(file_path);
//			salesService.calculateData();
//			salesService.WriteToFile();
		}
	}

}
