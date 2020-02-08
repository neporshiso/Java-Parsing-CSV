package client;

import fileprocessors.StockFileData;
import fileprocessors.StockFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StockFileApplication {
	
	public static void main(String args[]) throws IOException{
		StockFileReader fr = new StockFileReader("table.csv");
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
		List<List<String>> priceHolder = new ArrayList<>();

		HashMap<String, Double> priceData = new HashMap<>();


		for (String line: lines
			 ) {
			try {
				String[] values = line.split(",");
				List<String> valuesManipulated = Arrays.asList(values);
				priceHolder.add(valuesManipulated);
			} catch (NumberFormatException e) {
				continue;
			}
		}

		priceHolder.remove(0);


		String key = null;
		Double value = null;

		for (int i = 0; i < priceHolder.size(); i++ ) {
			List currentLine = priceHolder.get(i);

			for (int j = 0; j < currentLine.size(); ++j) {
				key = headers.get(j);
				Object price = currentLine.get(j);
				value = Double.parseDouble(price.toString());

				priceData.put(key, value);

			}

			priceData = new HashMap<>();
			dataResult.add(priceData);


		}


		return dataResult;
	}
	
	
}
