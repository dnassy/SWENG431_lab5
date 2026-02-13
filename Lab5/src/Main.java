import Lab4_3.Lab4_3;
import Lab5_2.AnimationFrame;

import javax.swing.*;

/**
 * <Lab4>
 * <p>This is the main driver as required for Lab5 part 2.
 * <p>Course: SWENG431
 * <br>Professor: Dr. Shahid Hussain
 * <br>Assignment: Lab4
 * <br>Due Date: 20260215
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
public static void main(String[] args) {

    //Launches the GUI to record mouse events
    SwingUtilities.invokeLater(() -> {
        AnimationFrame tool = new AnimationFrame();
        tool.setLocation(0, 0);
        tool.setSize(400, 400);

        tool.setVisible(true);
        tool.toFront();
    });

    //Thread to run calculator GUI
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            Lab4_3.main(new String[0]);
        }
    });

    t.start();
}