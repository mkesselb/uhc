package test;

import heatingClasses.Building;
import heatingClasses.CsvImporter;

public class ImportTester {

	public static void main(String[] args){
		Building b = CsvImporter.parseFromCSV("building.csv");
		System.out.println(b);
	}
}