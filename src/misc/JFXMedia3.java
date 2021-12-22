package misc;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JFXMedia JFrame v3.1
 * Switches between two mp4s on a JFrame.
 * This is a demo, a more useful class may be developed later
 * @Requires - VideoPanel.java
 * @Author - Bryce Hill
 */
public class JFXMedia3 extends JFrame implements ActionListener {
    JButton button = new JButton("Click to change");

    private static boolean toggle = true;
    public final JFXPanel fxPanel = new JFXPanel();


    /**
     * Constructor Method. Places JFXPanel on the JFrame. Place the button on the JFrame
     * plays the initial media
     */
    public JFXMedia3() {
        setSize(900, 1600);
        setResizable(true);
        setLayout(null);

        fxPanel.setBounds(0,0,800,600);
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
                new VideoPanel2(fxPanel, "src/misc/option1.mp4",
                        1000, 1000, 270, -100,0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            toggle = !toggle;
            //System.out.println(toggle);
            //any changes to a JFXPanel from a swing element need to be run through a Runnable or Thread
            Platform.runLater(new Thread() {
                @Override
                public void run() {
                    if(toggle)
                        //filepath should be filepath of video
                        new VideoPanel2(fxPanel, "src/misc/option1.mp4",
                                1000, 1000, 270, -100,0);
                    else
                        //filepath should be filepath of video
                        new VideoPanel2(fxPanel, "src/misc/option2.mp4",
                                1000, 1000, 270, -100, 0);
                }
            });

        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFXMedia3();
        frame.setVisible(true);

    }
}

