package test;

import heatingClasses.Building;
import heatingClasses.CsvImporter;

public class ImportTester {

	public static void main(String[] args){
		Building b = CsvImporter.parseFromCSV("C:\\Users\\Max\\git\\uhc\\building.csv");
		System.out.println(b);
		
	}
}