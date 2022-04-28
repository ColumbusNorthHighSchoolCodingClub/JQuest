package misc;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JFXMedia JFrame v3.1
 * Switches between two mp4s on a JFrame.
 * This is a demo, a more useful class may be developed later
 * @Requires - VideoPanel.java
 * @Author - Bryce Hill
 */
public class JFXMedia3 extends JFrame implements ActionListener {
    JButton button = new JButton("Click to change");

    private static int sceneNum = 0;
    public JFXPanel fxPanel = new JFXPanel();


    /**
     * Constructor Method. Places JFXPanel on the JFrame. Place the button on the JFrame
     * plays the initial media
     */
    public JFXMedia3() {
        setSize(900, 1600);
        setResizable(true);
        setLayout(null);

        fxPanel.setBounds(0,-100,800,600);
        add(fxPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.setBounds(350, 600, 300, 100);
        button.addActionListener(this);
        add(button);


        //all elements of a JFXPanel must be run through a runnable or thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //filepath should be filepath of video
                try {
                            //filepath should be filepath of video
                            Scene scene = new ImagePanel("src\\images\\FirstFloor_Reference.png","src\\images\\SecondFloor_Reference.png",
                                    302, 384, 0, 0,100).image;  // <---- ImagePanel(first filepath, second file path, image width,
                            fxPanel.setLayout(null);            //                    image height, rotation, x-coordinate, y-coordiante)
                            fxPanel.setScene(scene);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(JFXMedia3.class.getName()).log(Level.SEVERE, null, ex);
                        }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            sceneNum = 1-sceneNum;
            //System.out.println(sceneNum);
            //any changes to a JFXPanel from a swing element need to be run through a Runnable or Thread
            Platform.runLater(new Thread() {
                @Override
                public void run() {
                    if(sceneNum == 0)
                    {
                        //filepath should be filepath of video
                         Scene scene = new VideoPanel2("src/misc/option1.mp4",
                                600, 302, 270, 0,100).VP2;
                         fxPanel.setLayout(null);
                         fxPanel.setScene(scene);
                 
                    
                    }
                    else
                    {
                        try {
                            //filepath should be filepath of video
                            Scene scene = new ImagePanel("src\\images\\FirstFloor_Reference.png","src\\images\\SecondFloor_Reference.png",
                                    302, 384, 0, 0,100).image;  // <---- ImagePanel(first filepath, second file path, image width,
                            fxPanel.setLayout(null);            //                    image height, rotation, x-coordinate, y-coordiante)
                            fxPanel.setScene(scene);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(JFXMedia3.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });

        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFXMedia3();
        frame.setVisible(true);

    }
}

