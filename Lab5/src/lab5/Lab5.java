package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Lab5 {
    
    // Row number, list of equivalency class definitions for all rows
    public static Map<Integer, List<Range>> Row_to_Class_Map = new HashMap<>();
    public static int rowCounter = 0;

    public static void main(String[] args) {
        String inputFile = "Eq.txt";
        File infile = new File(inputFile);
        try (Scanner reader = new Scanner(infile)) {
            while (reader.hasNextLine()) {
                // Read line of file
                String line = reader.nextLine();
                // Parse into list of ranges
                String[] rangesAsStrings = line.split(";");
                List<Range> ranges = new ArrayList<Range>();
                for(String rangeAsString: rangesAsStrings) {
                    // Parse each range into individual high and lows
                    String[] rangeAsList = rangeAsString.split(",");
                    // remove comma from low
                    int low = Integer.parseInt(rangeAsList[0].replace(",",""));
                    int high = Integer.parseInt(rangeAsList[1]);
                    // Create new range object and add to ranges list
                    Range newRange = new Range(low, high);
                    ranges.add(newRange);
                }
                // once all ranges have been converted, add to hashmap
                Row_to_Class_Map.put(rowCounter, ranges);
            }        
        }
        catch (FileNotFoundException e) {
            
        }
        String outfile = "test.txt";
        
    }  
    
    private int check(int val) {
        // return EC of given value for row
        
        // For row rowCounter in HashMap
        // Check which range in List<Range> of HashMap value belongs
        // return index +1 
        return 0;
    }
    
    private int foo(int values[]) {
        // Sums all values in list values
        return 0;
    }
    
}
