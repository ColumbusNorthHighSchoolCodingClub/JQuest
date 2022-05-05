
package game;

import game.player.Player;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This is the version of the game for testing on a single laptop. 
 * The keyboard can be used to change stations (1-8)
 * 
 * @author spockm
 */
public class UniversalClient 
{
    public static int FPS = 4;   //Frames per second (animation speed)


    JFrame frame;
    Player player;
    ScenePanel scene;
    JLabel stationLabel;
    
    Dimension screenSize;
    
//    int stationNumber; //changable by keyboard
//    public Color[] stationColors = {Color.BLUE, Color.RED, Color.GREEN, Color.CYAN, Color.WHITE, Color.ORANGE, Color.MAGENTA, Color.YELLOW};
//
//    int timeline; // 
//    public String[] timelineNames = {"Current day", "Wild West", "Space", "StarWars", "Medieval", "80s", "Dinosaurs", "Current/AltUniv"};
//    
//    int screenUse; // 
//    public String[] screenNames = {"Waiting", "Scene", "Inventory", "JPortal", "Map"};
    
    public UniversalClient()
    {
        frame = new JFrame();
//        frame.setTitle("JQuest");
//        frame.setSize(400,400);
//        frame.addKeyListener(this);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Full screen!! 
//        frame.setUndecorated(true); //Removes the bar at the top of the game
        frame.addWindowListener(new Closer()); //Could be removed, since the top bar is removed
        frame.setVisible(true);
        
        screenSize = frame.getSize();
        
//        stationNumber = 0; //Blue default station (1-8 to change)
//        timeline = 0; //Base timeline (QWERTYUI to change)
//        screenUse = 0; //Waiting for user to arrive ("P" to arrive)        
        
        stationLabel = new JLabel("TEST");
//        playerStatus = new JPanel();
//        playerStatus.setSize(screenSize.width/4, screenSize.height*3/4);
//        playerStatus.setBackground(Color.black);
        scene = new ScenePanel();
        scene.setSize(screenSize.width, screenSize.height);
//        scene.setBackground(stationColors[stationNumber]);
//        other = new JPanel();
//        other.setSize(screenSize.width/4, screenSize.height/4);
//        other.setBackground(Color.blue);
        stationLabel = new JLabel("TEST");
        scene.add(stationLabel);
       
        frame.add(scene);
//        frame.add(playerStatus);
//        frame.add(other);
        startAnimation();
    }
    
    public void renderFrame(Graphics g)
    {
        
    }
    


//    /*
//    ========================================================================
//    The KeyListener methods in this section should be replaced with QRevents 
//    in the finished product. 
//    ========================================================================
//    */
//    public void keyTyped(KeyEvent e) 
//    {
//        char c = e.getKeyChar();
//        
//        if(c > '0' && c <'9') // new Station number 1-8 
//        {            
//            int numPressed = c-49;
//            if(numPressed != scene.getStationNumber())
//            {
//                scene.setStationNumber(c-49); //Converts '1' to 0
//                scene.setScreenUse(0); //put into waiting mode until QR sensed (P pressed)
////            Graphics g = playerStatus.getGraphics();
////            g.setColor(Color.DARK_GRAY);
////            g.drawString("Station #"+stationNumber+" timeline="+timelineNames[timeline], 100, 100);
////            scene.repaint();
//            }
//        }
//        if(c=='p') //Player arrives
//        {
//            if(scene.getScreenUse() == 0)
//                scene.setScreenUse(1);
//        }
//        if(c=='j') //switch to JPortal
//        {
//            scene.setScreenUse(3);
//        }
//        if(c=='i') //switch to inventory
//        {
//            scene.setScreenUse(2);
//        }
//        if(c=='m') //display map
//        {
//            scene.setScreenUse(4);
//        }
//        if(c=='z') //return to scene from another state
//        {
//            if(scene.getScreenUse() != 0)
//                scene.setScreenUse(1);
//        }
//
//    }
//    public void keyPressed(KeyEvent e) 
//    {
//        int v = e.getKeyCode();
//        if(v == KeyEvent.VK_ESCAPE) //Close the application
//        {
//            System.exit(0);
//        }
//    }
//    public void keyReleased(KeyEvent e) {}
    //=======END OF KEY EVENTS===============================================
    
    public static void main(String[] args)
    {
        UniversalClient uClient = new UniversalClient();
    }
    private static class Closer extends java.awt.event.WindowAdapter 
    {   
        public void windowClosing (java.awt.event.WindowEvent e) 
        {   System.exit (0);
        }   //======================
    }      

    public void startAnimation() 
    {
        javax.swing.Timer t = new javax.swing.Timer(1000/FPS, new ActionListener() 
        {   //This is something you may not have seen before...
            //We are coding a method within the ActionListener object during it's construction!
            public void actionPerformed(ActionEvent e) 
            {
                frame.getComponent(0).repaint();
//                frame.setSize(frame.getComponent(0).getPreferredSize());
            }
        }); //--end of construction of Timer--
        t.start();
    }
    
}
