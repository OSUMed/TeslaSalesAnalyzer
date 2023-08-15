package com.coderscampus.assignment6;

import com.coderscampus.assignment6.service.SalesService;

public class SalesApplication {
	
	public static final String[] FILES = { "InterpolWatchReport-Week2.csv", "InterpolWatchReport-Week1.csv", "InterpolWatchReport-Week3.csv" };
	public static final String[] FILES_TEST = { "InterpolWatchReport-Week1.csv" };

	public static void main(String[] args) {
		for (String file_path: FILES_TEST) {
			SalesService salesService = new SalesService(file_path);
//			salesService.calculateData();
//			salesService.WriteToFile();
		}
	}

}
