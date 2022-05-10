package misc;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import javax.swing.*;

import game.player.GameMap;
import game.player.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Authors - Bryce Hill, Carlos Fabian, Nolan Sandlin
 * 
 * Holds the main method, so run this file.
 * Setup the to-be-displayed content here.
 */
@SuppressWarnings({ "serial", "unused" })
public class JFXMedia3 extends JFrame implements ActionListener
{
    JButton nextButton = new JButton("Click to go to next view");
    JButton backButton = new JButton("Click to go to previous view");

    private int windowWidth = 1600;
    private int windowHeight = 900;
    private int screenWidth = 800;
    private int screenHeight = 600;
    private int sceneNum = 0;
    private int sceneNumMax = 2;
    private ArrayList<ImageView> imgViews = new ArrayList<ImageView>();
    public JFXPanel fxPanel = new JFXPanel();


    /**
     * Constructor Method. Places JFXPanel on the JFrame. Place the button on the JFrame
     * Plays the initial media
     */
    public JFXMedia3()
    {
        setSize(windowHeight,windowWidth);
        setResizable(true);
        setLayout(null);

        fxPanel.setBounds(0,-100,screenWidth,screenHeight);
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
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
    			Scene scene = new VideoPanel2("src/misc/option1.mp4",600, 302, 270, 0,100).VP2;
                fxPanel.setLayout(null);        //filepath, media width, height, rotation, x-coordinate, y-coordinate)
                fxPanel.setScene(scene);
            }
        });
    }
    
    /*
     * Whenever called, updates code within method.
     * Called whenever a button is pressed, such as nextButton or backButton.
     */
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
        	Player me = null;
            try
            {
                me = new Player("demo", "1234", "dest", "station", "goblin");
            }
            catch (FileNotFoundException e1)
            {
                e1.printStackTrace();
            } // creates a player to reference the map
            ArrayList<Integer> fake = new ArrayList<Integer>();
//            fake = me.getAvailableFFStations();
                fake.add(1);
                ArrayList<Integer> fake2 = new ArrayList<Integer>();
//            fake2 = me.getAvailableSFStations();
                fake2.add(1);
        	GameMap map =  new GameMap(fake, fake2, 
                    me.getStation(), me.getDestination(), me.getTimeline()); // initiallizes the map corresponding to the player class
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
                    		//filepath should be filepath of media
                    		scene = new VideoPanel2("src/misc/option1.mp4",600,302,270,0,100).VP2;
                    		fxPanel.setLayout(null);
                    		fxPanel.setScene(scene);
                    		break;
                    	case 1:
                            //filepath should be filepath of media
                            scene = new ImagePanel("src\\game\\player\\images\\FirstFloor_Reference.png","src\\game\\player\\images\\SecondFloor_Reference.png",302,384,0,0,100).image;  // <---- ImagePanel(first filepath, second file path, image width,
                            fxPanel.setLayout(null);
                            fxPanel.setScene(scene);
                    		break;
                    	case 2:
                    		int mapWidth = 300;
                    		int mapHeight = 400;
                    		int boxWidth = 200;
                    		int boxHeight = 200;
                    		int mapScalar = screenHeight/(2*mapHeight);
                    		imgViews.add(ImagePanel.createImageView("src\\game\\player\\images\\FirstFloor_Reference.png",mapWidth*mapScalar,mapHeight*mapScalar,1*mapScalar-1,100*mapScalar,0,map));
                    		imgViews.add(ImagePanel.createImageView("src\\game\\player\\images\\SecondFloor_Reference.png",mapWidth*mapScalar,mapHeight*mapScalar,400*mapScalar,100*mapScalar,0,map));
                    		imgViews.add(ImagePanel.createImageView("src\\game\\player\\images\\ItemSlot.png",boxWidth,boxHeight,200,400,0,map));
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

    public static void main(String[] args)
    {
        JFrame frame = new JFXMedia3();
        frame.setVisible(true);
    }
}