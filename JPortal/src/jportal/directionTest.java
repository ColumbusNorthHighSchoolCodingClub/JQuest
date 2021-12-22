package jportal;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

public abstract class directionTest extends JPanel implements KeyListener, MouseListener, MouseMotionListener 
{
    
    public directionTest()
    {
        this.setFocusable(true);
        this.addKeyListener(this);
    }  
 
    // KeyListener
    public void keyPressed (KeyEvent e) {}
    public void keyTyped (KeyEvent e) {}
    public void keyReleased (KeyEvent e) {}  
}
