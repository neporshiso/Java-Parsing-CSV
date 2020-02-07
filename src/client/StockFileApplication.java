package client;

import fileprocessors.StockFileData;
import fileprocessors.StockFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockFileApplication {
	
	public static void main(String args[]) throws IOException{
		StockFileReader fr = new StockFileReader("table.csv");
//		System.out.println(fr.readFileData());  Looks like readFileData is doing what it's supposed to
		
		List<HashMap<String, Double>> dataResult = populateStockFileData(fr.getHeaders(), fr.readFileData());
		StockFileData fileData = new StockFileData();
		fileData.addData(dataResult);
		fileData.printData();
		System.out.println(dataResult.size());
	}
	/**
	 * Complete the method body so that it returns the given structure needed to 
	 * populate the data field in the StockFileData class. 
	 * @param headers
	 * @param lines
	 * @return List
	 */
	public static List<HashMap<String, Double>> populateStockFileData(List<String> headers, List<String> lines){
		List<HashMap<String, Double>> dataResult = new ArrayList<>();

		int counter = 5;

		while (counter > 0) {
			HashMap<String, Double> testData = new HashMap<>();

			for (String header: headers
			) {
				testData.put(header, 70.0);
			}

			dataResult.add(testData);
			counter--;
		}

		return dataResult;
	}
	
	
}
