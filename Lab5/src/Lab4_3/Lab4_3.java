/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab4_3;

import gui.MyGUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

/**
 * <Lab4>
 * <p>This is the main driver as required for Lab4 part 3.
 * <p>Course: SWENG431
 * <br>Professor: Dr. Shahid Hussain
 * <br>Assignment: Lab4
 * <br>Due Date: 20260208
 *
 * <p>ChatGPT5.2 was used to generate all javadoc headers by submitting the
 * following prompt:
 *
 * <p>"Generate an informational  javadoc header appropriate for this class,
 * developed to fulfill these technical requirements:
 * - requirement 1
 * - requirement 2
 *
 * \Include source code written copied from IDE"
 *
 * @author Demetrius A. Nassy, dan5409@psu.edu
 * @author Luis Eduardo Cintron Zayas, lec5532@psu.edu
 * @author Donovan Neel, dpn5200@psu.edu
 * @version 1.0
 */

public class Lab4_3 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Instantiate GUI
        MyGUI gui = new MyGUI();
        gui.setSize(400, 350);
        gui.setVisible(true);
        gui.setLocation(0,400);
        
        // Get root container
        Container root = getRootContainer(gui);

        // Get all required components for test, validated from original code
        Component[] components = root.getComponents();
        Component field1 = components[1];
        Component field2 = components[3];
        Component output = components[5];
        JButton button = (JButton) components [6];
        
        // Attempt to open test file in test directory
        File input = new File("input.txt");
        try (Scanner reader = new Scanner(input)) {
            
            // Attempt to read
            while (reader.hasNextLine()) {

                //Read item into list
                String line = reader.nextLine();
                System.out.println(line);
                String[] data = line.trim().split("\\s+");
                
                //Assert the test case is valid
                assert((Integer.parseInt(data[1])) + (Integer.parseInt(data[0])) == (Integer.parseInt(data[2])));

                SwingUtilities.invokeAndWait(() -> {

                    ((JTextField) output).setText("");
                    ((JTextField)field2).setText(data[0]);
                    ((JTextField)field1).setText(data[1]);

                });

                //Wait until OK is clicked
                button.addActionListener(e -> {
                    synchronized(button){
                        button.notify();
                    }
                });

                //
                synchronized(button){
                    button.wait();
                }

                //
                String outText = ((JTextField) output).getText().trim();
                while (outText.isEmpty()) {
                    Thread.sleep(50);
                    outText = ((JTextField) output).getText().trim();
                }


                //Get output text from output field
                int actual = Integer.parseInt(((JTextField)output).getText());
                int expected = Integer.parseInt(data[2]);
                
                boolean valid = actual == expected;
                System.out.println(valid);
                
                Thread.sleep(1000);
            }      
        } catch (FileNotFoundException e) {
            System.out.println(input + " not found.");
            e.printStackTrace();
            System.exit(1);
        } catch (InterruptedException e) {
            System.out.println("Aborted");
            e.printStackTrace();
            System.exit(1);
        } catch (InvocationTargetException e) {
            System.out.println("Invocation target exception");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static Container getRootContainer(MyGUI gui) {
        return ((JFrame) gui).getContentPane();
    }
}
