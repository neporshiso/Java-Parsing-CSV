package fileprocessors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StockFileReader {

    String filePath;

    public StockFileReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getHeaders() throws IOException {
        String line = readFirstLine(filePath);
        String[] header = line.split(",");
        List<String> values = new ArrayList<>();
        values = Arrays.asList(header);
        return values;
    }

    static String readFirstLine(String path) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    public List<String> readFileData() throws IOException {
        List<String> lines = new ArrayList<>();
        String path = this.filePath;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();

            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Problem Reading the file " + path);
        }


        return lines;
    }


}
