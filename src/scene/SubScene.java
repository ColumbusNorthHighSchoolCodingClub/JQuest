/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author 23hobbeheydars
 */
public class SubScene {
    private BufferedImage show;
    private String question;
    private ArrayList<String> options;
    private ArrayList<SubScene> next;
    private ArrayList<String> changers;
    private int width;
    private int height;
    private JFrame b=null;
    private SceneTracker ts;
    private ArrayList<JButton> buttons;
    
    public SubScene(){
        
    }
    
    public SubScene(ArrayList<String> s) throws IOException{
        show=ImageIO.read(new File(s.get(0)));
        question = s.get(1);
        options = new ArrayList();
        next = new ArrayList();
        changers=new ArrayList();
        buttons=new ArrayList();
        for(int i=2;i<s.size();i++){
            options.add(s.get(i));
        }
        width=500;//show.getWidth();
        height=400;//show.getHeight();
        for(String a: options){
            buttons.add(new JButton(a));
        }
    }
    
    public void addNext(SubScene a){
        next.add(a);
    }
    
    public void addChanger(String a){
        changers.add(a);
    }
    
    public SubScene getNext(int i){
        return next.get(i);
    }
    
    public void showScene(JFrame a, SceneTracker st){
        ts=st;
        b=a;
        a.getContentPane().removeAll();
        a.repaint();
        a.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        a.setSize(width+16, height+100+(options.size()*50));
        JPanel p = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                g.drawImage(show, 0, 0, 400,300,null);
            }
        };
        for(int i=0;i<buttons.size();i++){
            buttons.get(i).setBounds(width/4,height+50+(50*i),width/2,40);
            if(buttons.get(i).getActionListeners().length==0)
                buttons.get(i).addActionListener(new ButtonListener());
            a.add(buttons.get(i));
        }
        JLabel l = new JLabel(question);
        l.setBounds(width/4,height+10,width/2,20);
        a.add(l);
        a.add(p);
        a.validate();
        a.setVisible(true);
    }
    
    public class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            int counter=0;
            for(int i=0;i<options.size();i++){
                if(e.getSource()==buttons.get(i))
                    counter=i;
            }
            ts.add(changers.get(counter));
            next.get(counter).showScene(b, ts);
        }
    }
}
