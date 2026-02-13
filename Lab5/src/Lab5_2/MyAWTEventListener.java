package Lab5_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class MyAWTEventListener implements AWTEventListener {
    ArrayList<AWTEvent> alae;
    boolean recording = false;

    MyAWTEventListener(ArrayList<AWTEvent> alae) {
        this.alae = alae;
    }

    @Override
    public void eventDispatched(AWTEvent event) {

        if(event.getID() == MouseEvent.MOUSE_CLICKED){
            Object o = event.getSource();


            if(o instanceof JButton){
                JButton button = (JButton)o;
                String txt = button.getText();

                //
                if (txt.equals("Record")) {
                    alae.clear();
                    recording = true;
                    return;
                }

                //
                if (txt.equals("Stop")) {
                    recording = false;
                    return;
                }

                //
                if (txt.equals("Play")) {
                    recording = false;
                    return;
                }
            }
        }

        //Add event to list if recording is true
        if (recording) {
            alae.add(event);
        }
    }
    
}
