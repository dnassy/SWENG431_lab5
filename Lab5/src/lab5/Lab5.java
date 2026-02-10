package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Lab5 {

    public static void main(String[] args) {
        String inputFile = "Eq.txt";
        File infile = new File(inputFile);
        try (Scanner reader = new Scanner(infile)) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            recursiveGeneration(reader, list, 0);
        }
        catch (FileNotFoundException e) {
            
        }
        String outfile = "test.txt";
        
    }
    
    static void recursiveGeneration(Scanner reader, ArrayList<Integer> base, Integer sum) {
        // Base case, end when no more lines in file to read.
        if (!reader.hasNextLine()) {
            // Add sum to end of list
            base.add(sum);
            // Write list to file
            System.out.println(base);
        }
        else {
            // Read current line of file
            String line = reader.nextLine();
            // Parse into list of ranges
            String[] rangesAsStrings = line.split(";");
            List<Range> ranges = new ArrayList<Range>();
            for(String rangeAsString: rangesAsStrings) {
                // Parse each range into individual high and lows
                String[] rangeAsList = rangeAsString.split(",");
                // remove comma from low
                int low = Integer.parseInt(rangeAsList[0].replace(",","").trim());
                int high = Integer.parseInt(rangeAsList[1].trim());
                // Create new range object and add to ranges list
                Range newRange = new Range(low, high);
                ranges.add(newRange);
            }
            // once all ranges in this line have been converted, generate
            // cases and recurse
            for(Range range: ranges) {
                Random random = new Random();
                
                // Last range in list is fully inclusive
                if (range == ranges.getLast()) {
                    base.add(random.nextInt(range.low, range.high + 1));
                // All other ranges left inclusive only
                } else {
                    base.add(random.nextInt(range.low, range.high));
                }
                // recurse for next line, adding current index to sum
                recursiveGeneration(reader, base, sum+ranges.indexOf(range));
            }
        }
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
