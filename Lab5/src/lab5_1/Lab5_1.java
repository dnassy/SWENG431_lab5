package lab5_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lab5_1 {
    // Read in contents of Eq.txt for parsing.
    // Will remain as working copy throughout
    public static final ArrayList<ArrayList<Range>> input = readFile("Eq.txt");
    
    // Global var for access per requirements for check function prototype
    public static ArrayList<Range> working = null;

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        recursiveGeneration(0, "", list);
    }
    
    static void recursiveGeneration(int index, String output, ArrayList<Integer> values) {
        // Base case, end when no more lines in file to read.
        if (index >= input.size()) {
            // Run for to add up running sum per requirements
            int sum = foo(values);
            // Add sum to end of string in values to coomplete test case
            output = output + sum + "\n";
            // Write test case out to file.
            try {
                Files.writeString(Path.of("test.txt"),
                        output,
                        StandardOpenOption.CREATE, // Create if file does not exist
                        StandardOpenOption.APPEND); // Otherwise append
            } catch (IOException e) {
                return;
            }
        }
        else {
            // Read current working ECs
            working = input.get(index);
            // For each range in the given ranges, generate a value
            for(Range EC: working) {
                Random random = new Random();
                Integer value;
                // Random generation bound by current range
                // Last range in list is fully inclusive
                if (EC == working.getLast()) {
                    value = random.nextInt(EC.low, EC.high + 1);
                // All other ranges left inclusive only
                } else {
                    value = random.nextInt(EC.low, EC.high);
                }
                // Run check to determine equivalency per requirement and
                // add to array of values for use in foo per requirements
                values.add(check(value));
                
                // recurse for next line, adding value to running string
                recursiveGeneration(index + 1, output + value + ",", values);
                
                // Reinstate working to current working for correct checks on next run
                working=input.get(index);
                
                // Remove generated value before iterating to next range
                values.removeLast();
            }
        }
    }
    
    private static ArrayList<ArrayList<Range>> readFile(String inputFile) {
        /**
         * Reads lines from a file into a 2d array of ranges
         * 
         * @param inputFile The file from which to read
         * 
         * @return 2D array of ranges, representing a collection of equivalency classes
         */
        ArrayList<ArrayList<Range>> ECs = new ArrayList<ArrayList<Range>>();
        File infile = new File(inputFile);
        try (Scanner reader = new Scanner(infile)) {
            while(reader.hasNextLine()) {
                // read in a working line
                String line = reader.nextLine();
                // Parse line into list of ranges as strings
                String[] rangesAsStrings = line.split(";");
                ArrayList<Range> ranges = new ArrayList<Range>();
                
                // For each range, designating an EC, parse into a Range
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
                // Add ranges list to ECs for return
                ECs.add(ranges);
            }
        }
        catch (FileNotFoundException e) {
            return null;
        }
        return ECs;
    }
    
    private static int check(int val) {
        /**
         * Returns the EC of a given value compared to working.
         * Depends on array of values in working as global. 
         * DESIGN NOTE: check is only included for project requirements.
         * The recursive generation can use a running sum based on its own iteration
         * rather than use check and foo.
         * 
         * 
         * @param val The integer to compare in working
         * 
         * @returns index + 1 of working to which val belongs
         */
        for (Range range: working) {
            // If we iterate past all other ranges, just return last index of working
            if (range == working.getLast()) {
                return working.indexOf(range) + 1;
            }
            // Check for left inclusivity of each EC until we reach the last one
            // Explicitly showing comparison to prove understanding of
            // assignment requirement.
            else if(range.low <= val && val < range.high){
                return working.indexOf(range) + 1;
            }
        }
        return 0;
    }
    
    private static int foo(ArrayList<Integer> values) {
        // Sums all values in list values
        int sum = 0;
        for (Integer value: values) {
            sum += value;
        }
        return sum;
    }
    
}
