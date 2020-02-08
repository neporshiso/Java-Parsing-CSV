package client;

import fileprocessors.StockFileData;
import fileprocessors.StockFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StockFileApplication {

    public static void main(String args[]) throws IOException {
        StockFileReader fr = new StockFileReader("table.csv");
        List<HashMap<String, Double>> dataResult = populateStockFileData(fr.getHeaders(), fr.readFileData());
        StockFileData fileData = new StockFileData();
        fileData.addData(dataResult);
        fileData.printData();
        System.out.println(dataResult.size());
    }

    public static List<HashMap<String, Double>> populateStockFileData(List<String> headers, List<String> lines) {
        List<HashMap<String, Double>> dataResult = new ArrayList<>();
        List<List<String>> priceHolder = new ArrayList<>();
        HashMap<String, Double> priceData = new HashMap<>();

		// This gives a list of a list of prices
        for (String line : lines
        ) {
            String[] values = line.split(",");
            List<String> valuesManipulated = Arrays.asList(values);
            priceHolder.add(valuesManipulated);
        }

        // Get rid of header row
        priceHolder.remove(0);


        String key;
        Double value;

        for (List currentLine : priceHolder) {
            for (int j = 0; j < currentLine.size(); ++j) {
                key = headers.get(j);
                Object price = currentLine.get(j);
                value = Double.parseDouble(price.toString());
                priceData.put(key, value);
            }


            dataResult.add(priceData);
            // Need to initialize a new hashmap each time we populate the 6 values to ensure nothing gets overridden
            priceData = new HashMap<>();

        }

        return dataResult;
    }

}
