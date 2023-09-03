# Tesla Sales Data Analysis in Java

## Table of Contents

1. [Description](#description)
2. [Requirements](#requirements)
3. [Features](#features)
4. [Installation](#installation)
5. [How to Run](#how-to-run)
6. [Classes](#classes)
   - [DateService](#dateservice)
   - [FileService](#fileservice)
   - [SalesService](#salesservice)
   - [SalesApplication](#salesapplication)
7. [Sample Output](#sample-output)
8. [Notes](#notes)
9. [License](#license)
10. [Contributing](#contributing)
11. [Acknowledgments](#acknowledgments)

## Description

This application reads Tesla vehicle sales data from CSV files for the years 2016 to 2019 and provides a yearly sales report for Tesla Model 3, Model S, and Model X. It also identifies the best and worst months for each model. The sales data, once processed, is output to both the console and a text file.

## Requirements

- Java 8 or above

## Features

- Data parsing from CSV files
- Yearly sales report generation
- Identification of best and worst months for each model

## Installation

1. Clone the repository to your local machine.
2. Import the project into your preferred Java IDE.

## How to Run

1. Download the three CSV files from the specified URLs.
2. Place the CSV files in your project directory.
3. Compile and run `SalesApplication`.

## Classes

### DateService

Handles date formatting and conversion. Utilizes Java's DateTimeFormatter to format dates and months.

### FileService

Responsible for reading from the CSV files, parsing the data into `Sales` objects, and writing the processed data to an output text file.

### SalesService

Conducts the analysis of the sales data. Calculates the yearly sums, and finds the best and worst months for each Tesla model.

### SalesApplication

The entry point for the application. Loops through each CSV file to trigger the data analysis and output generation.

## Sample Output

Model 3 Yearly Sales Report

2017 -> ####
2018 -> ####
2019 -> ####

The best month for Model 3 was: yyyy-MM
The worst month for Model 3 was: yyyy-MM


## Notes

- If you're outside of the USA, you'll need to pass in a Locale when using DateTimeFormatter (e.g., Locale.CANADA or Locale.UK).

## License

This project is open source and available under the [MIT License](LICENSE).

## Acknowledgments

- Special thanks to the developers of Java for providing such a powerful language for backend development.

