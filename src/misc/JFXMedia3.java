package misc;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import javax.swing.*;

import game.player.Map;
import game.player.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JFXMedia JFrame v3.1
 * Switches between two mp4s on a JFrame.
 * This is a demo, a more useful class may be developed later
 * @Requires - VideoPanel.java
 * @Author - Bryce Hill
 */
public class JFXMedia3 extends JFrame implements ActionListener
{
    JButton nextButton = new JButton("Click to go to next view");
    JButton backButton = new JButton("Click to go to previous view");

    private static int sceneNum = 0;
    private static int sceneNumMax = 2;
    private ArrayList<ImageView> imgViews = new ArrayList<ImageView>();
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
        nextButton.setBounds(550, 600, 300, 100);
        nextButton.addActionListener(this);
        add(nextButton);
        backButton.setBounds(150, 600, 300, 100);
        backButton.addActionListener(this);
        add(backButton);


        //all elements of a JFXPanel must be run through a runnable or thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //filepath should be filepath of video
//                try {
                            //filepath should be filepath of video
//	                  	Scene scene = new ImagePanel("src\\images\\FirstFloor_Reference.png","src\\images\\SecondFloor_Reference.png",
//	                	302, 384, 0, 0,100).image;  // <---- ImagePanel(first filepath, second file path, image width,
            			Scene scene = new VideoPanel2("src/misc/option1.mp4",600, 302, 270, 0,100).VP2;
                        fxPanel.setLayout(null);            //                    image height, rotation, x-coordinate, y-coordiante)
                        fxPanel.setScene(scene);
//                        } catch (FileNotFoundException ex) {
//                            Logger.getLogger(JFXMedia3.class.getName()).log(Level.SEVERE, null, ex);
//                        }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == nextButton || e.getSource() == backButton)
        {
        	if(e.getSource() == nextButton)
        	{
	            sceneNum++;
	            if(sceneNum > sceneNumMax) sceneNum = 0;
        	}
        	if(e.getSource() == backButton)
        	{
	            sceneNum--;
	            if(sceneNum < 0) sceneNum = sceneNumMax;
        	}
        	Player me = new Player("demo", "1234", "dest", "station", "goblin"); // creates a player to reference the map
        	Map map =  new Map(me.getAvailableFFStations(), me.getAvailableSFStations(), 
                    me.getStation(), me.getDestination(), me.getTimeline()); // initiallizes the map corresponding to the player class
//            System.out.println(sceneNum);
            //any changes to a JFXPanel from a swing element need to be run through a Runnable or Thread
            Platform.runLater(new Thread()
            {
                @Override
                public void run()
                {
                	Scene scene;
                    switch(sceneNum)
                    {
                    	case 0:
                    		//filepath should be filepath of video
                    		scene = new VideoPanel2("src/misc/option1.mp4",600, 302, 270, 0,100).VP2;
                    		fxPanel.setLayout(null);
                    		fxPanel.setScene(scene);
                    		break;
                    	case 1:
                    		try
                    		{
	                            //filepath should be filepath of video
	                            scene = new ImagePanel("src\\images\\FirstFloor_Reference.png","src\\images\\SecondFloor_Reference.png",
	                                    302, 384, 0, 0,100).image;  // <---- ImagePanel(first filepath, second file path, image width,
	                            fxPanel.setLayout(null);            // image height, rotation, x-coordinate, y-coordinate)
	                            fxPanel.setScene(scene);
                    		} catch (FileNotFoundException ex)
                    		{
                    			Logger.getLogger(JFXMedia3.class.getName()).log(Level.SEVERE, null, ex);
                    		}
                    		break;
                    	case 2:
                    		imgViews.add(ImagePanel.createImageView("src\\images\\FirstFloor_Reference.png",300,400,0,100,0,map));
                    		imgViews.add(ImagePanel.createImageView("src\\images\\SecondFloor_Reference.png",300,400,400,100,0,map));
                    		scene = new ImagePanel(imgViews).image;
                    		fxPanel.setLayout(null);
                            fxPanel.setScene(scene);
                            break;
                    	default:
                    		//filepath should be filepath of video
                    		scene = new VideoPanel2("src/misc/option1.mp4",600, 302, 270, 0,100).VP2;
                    		fxPanel.setLayout(null);
                    		fxPanel.setScene(scene);
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

