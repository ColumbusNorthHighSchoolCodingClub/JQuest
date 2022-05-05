package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import misc.JFXMedia3;
import misc.VideoPanel2;

/**
 * Class ScenePanel
 */



public class ScenePanel extends JPanel implements KeyListener 
{
    int stationNumber; //changable by keyboard
    public Color[] stationColors = {Color.BLUE, Color.RED, Color.GREEN, Color.CYAN, Color.WHITE, Color.ORANGE, Color.MAGENTA, Color.YELLOW};

    int timeline; // 
    public String[] timelineNames = {"Current day", "Wild West", "Space", "StarWars", "Medieval", "80s", "Dinosaurs", "Current/AltUniv"};
    
    int screenUse; // 
    public String[] screenNames = {"Waiting", "Scene", "Inventory", "JPortal", "Map"};
    
    Dimension screenSize;
    
    public final JFXPanel fxPanel = new JFXPanel();;

    
    public ScenePanel()
    {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screenSize);
        this.addKeyListener(this);
//         this.setSize(new Dimension(width,height));
//        this.setLocation(80,80);    //move to the right
        this.setVisible (true);         // make it visible to the user
        this.setFocusable(true);
        
//        JFrame frame = new JFXMedia3();
//        frame.setVisible(true);
        
        fxPanel.setBounds(0,0,600,800);
        add(fxPanel);
    }
    
    public void setStationNumber(int n) { stationNumber = n; }
    public int getStationNumber() { return stationNumber; }
    public void setTimeline(int n) { timeline = n; } 
    public void setScreenUse(int n) { screenUse = n; }
    public int getScreenUse() { return screenUse; }
    
    
    
    
///////////////////////////////////////////////////    
    /* Method renderFrame()
     * This is what is repeatedly animated,
     * it paints your graphics to the frame.
     * Don't forget to extend this!
     */
    protected void renderFrame(Graphics g) 
    {
        g.setColor(stationColors[stationNumber]);
        g.fillRect(0,0,screenSize.width, screenSize.height);
        
        if(screenUse == 0)
            renderWaiting(g);
        else if(screenUse == 1)
            renderScene(g);
        else if(screenUse == 2)
            renderInventory(g);
        else if(screenUse == 3)
            renderJPortal(g);
        else if(screenUse == 4)
            renderMap(g);
        
        renderSidePanel(g);
        Graphics fxg = fxPanel.getGraphics();
        g.setColor(Color.GREEN);
        fxg.fillRect(0,0,20,20);
    }
    
    public void renderWaiting(Graphics g)
    {
        //Loop the Waiting Video
    }
    
    public void renderScene(Graphics g)
    {
        
    }
    
    public void renderInventory(Graphics g)
    {


        
    }
    
    public void renderJPortal(Graphics g)
    {
        renderSidePanel(g);
        g.setColor(Color.gray);
        g.fillRect(20, 20, screenSize.width*3/4-40, screenSize.height-40);
    }
    
    public void renderMap(Graphics g)
    {

    }

    public void renderSidePanel(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(screenSize.width*3/4, 0, screenSize.width/4, screenSize.height);
        g.setColor(Color.black);
        g.drawString("Station #"+(stationNumber+1)+" timeline="+timelineNames[timeline]+" "+screenNames[screenUse],screenSize.width*3/4+10,50);
    }
    // method paintComponent
    // inherited from Class JPanel
    // does the repaint() of your panel
    // --- DO NOT MODifY ---
    public void paintComponent(Graphics g) 
    {
        this.requestFocusInWindow();
        renderFrame(g);
    }
    
    /*
    ========================================================================
    The KeyListener methods in this section should be replaced with QRevents 
    in the finished product. 
    ========================================================================
    */
    public void keyTyped(KeyEvent e) 
    {
        char c = e.getKeyChar();
        
        if(c > '0' && c <'9') // new Station number 1-8 
        {            
            int numPressed = c-49;
            if(numPressed != getStationNumber())
            {
                setStationNumber(c-49); //Converts '1' to 0
                setScreenUse(0); //put into waiting mode until QR sensed (P pressed)
//            Graphics g = playerStatus.getGraphics();
//            g.setColor(Color.DARK_GRAY);
//            g.drawString("Station #"+stationNumber+" timeline="+timelineNames[timeline], 100, 100);
//            scene.repaint();
            }
        }
        if(c=='p') //Player arrives
        {
            if(getScreenUse() == 0)
                setScreenUse(1);
        }
        if(c=='j') //switch to JPortal
        {
            setScreenUse(3);
        }
        if(c=='i') //switch to inventory
        {
        //all elements of a JFXPanel must be run through a runnable or thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //filepath should be filepath of video
//                new VideoPanel2(fxPanel, "src/misc/option1.mp4",200, 200, 270, 0,0);
                new VideoPanel2("src/misc/option1.mp4",200, 200, 270, 0,0);
            }
        });
            setScreenUse(2);
        }
        if(c=='m') //display map
        {
            setScreenUse(4);
        }
        if(c=='z') //return to scene from another state
        {
            if(getScreenUse() != 0)
                setScreenUse(1);
        }

    }
    public void keyPressed(KeyEvent e) 
    {
        int v = e.getKeyCode();
        if(v == KeyEvent.VK_ESCAPE) //Close the application
        {
            System.exit(0);
        }
    }
    public void keyReleased(KeyEvent e) {}
    //=======END OF KEY EVENTS===============================================
    
   
} //-- End AnimationPanel() Class
