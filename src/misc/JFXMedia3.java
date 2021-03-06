package misc;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import javax.swing.*;

import game.player.GameMap;
import game.player.Inventory;
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
     * Constructor Method. Places JFXPanel on the JFrame and place the buttons on the JFrame
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
        	//Put media you want to be the initial display here, even though you can update the display right after.
            @Override
            public void run()
            {
    			Scene scene = new VideoPanel2("src/misc/option1.mp4",600,302,270,0,100).VP2;
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
            me = new Player("demo", "1234", "dest", "station", "goblin");
            ArrayList<Integer> fake = new ArrayList<Integer>();
//            fake = me.getAvailableFFStations();
                Integer x = 1;
                fake.add(x);
                ArrayList<Integer> fake2 = new ArrayList<Integer>();
//            fake2 = me.getAvailableSFStations();
                fake2.add(x);
        	GameMap map =  new GameMap(fake, fake2, 
                    me.getStation(), me.getDestination(), me.getTimeline()); // Initializes the map corresponding to the player class
        	Inventory inv =  me.getInventory(); // Initializes the inventory corresponding to the player class
            //any changes to a JFXPanel from a swing element need to be run through a Runnable or Thread
            Platform.runLater(new Thread()
            {
            	@Override
                public void run()
                {
                	Scene scene;
                    switch(sceneNum) //This switch case is used to switch between each scene. Scene 0 is a test video, scene 1 is a example of
                    {				 //displaying two images in one line, and scene 2 displays an example for the menu screen, including maps and inventory
                    	case 0: //This case displays a video from a given file location
                    		//filepath should be filepath of media
                    		scene = new VideoPanel2("src/misc/option1.mp4",600,302,270,0,100).VP2;
                    		fxPanel.setLayout(null);
                    		fxPanel.setScene(scene);
                    		break;
                    	case 1: //This case displays two images put together for the map
                            //filepath should be filepath of media
                            scene = new ImagePanel("src\\game\\player\\images\\FirstFloor_Reference.png","src\\game\\player\\images\\SecondFloor_Reference.png",302,384,0,0,100).image;  // <---- ImagePanel(first filepath, second file path, image width,
                            fxPanel.setLayout(null);
                            fxPanel.setScene(scene);
                    		break;
                    	case 2: //This case sets up the map by modifying multiple, individual images and then adding them together in one display
                    		int mapWidth = 300;
                    		int mapHeight = 400; //Width and height of each map section, i.e. Floor 1 and Floor 2
                    		int boxWidth = 100;
                    		int boxHeight = 100; //Width and height of inventory boxes
                    		int boxXOffset = 50;
                    		int boxYOffset = 400; //Base x and y offsets for inventory boxes
                    		int mapScalar = screenHeight/(2*mapHeight);
                    		imgViews.add(ImagePanel.createImageView("src\\game\\player\\images\\FirstFloor_Reference.png",mapWidth*mapScalar,mapHeight*mapScalar,mapWidth/4,mapHeight/4,0,map));
                    		imgViews.add(ImagePanel.createImageView("src\\game\\player\\images\\SecondFloor_Reference.png",mapWidth*mapScalar,mapHeight*mapScalar,screenWidth/2-mapWidth/4/4,mapHeight/4,0,map));
                    		for(int i=0;i<inv.getMaxCount();i++)
                    		{
                    			imgViews.add(ImagePanel.createImageView("src\\game\\player\\images\\ItemSlot.png",boxWidth,boxHeight,boxXOffset+(i*boxWidth),boxYOffset,0,map));
                    		}
                    		scene = new ImagePanel(imgViews).image;
                    		fxPanel.setLayout(null);
                            fxPanel.setScene(scene);
                            break;
                    	default:
                    		//filepath should be filepath of media
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