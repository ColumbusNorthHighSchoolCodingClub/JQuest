package game.jportal;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class JPortal
{
  //instance variables
  ArrayList<Image> timelineImg;
  int displayCount;
  String command;
  boolean end;
  
  //constructors
  public JPortal()
  {
    timelineImg = new ArrayList<>();
    displayCount = 0;
    command = "";//QRReader.getCommand();//?
    end = false;
  }
  
  public static void main(String args[])
  {
      JPortal jportal = new JPortal();
      jportal.scroll();
      jportal.display();
  }
  
  //methods
  public void scroll()
  {
      if(command=="up")
      {
          displayCount--;
      }
      else if(command=="down")
      {
          displayCount++;
      }
      if(displayCount<0)
      {
          displayCount = timelineImg.size() + displayCount;
      }
  }

  public void travel()
  {
    //return new timeline for player to server
    //player.setTimeline();
  }

  public void display()
  {
	//what timeline is the player in? display appropriate background image 
	//player.getTimeline();
     
     //display images
//     for(int c=displayCount; c<=displayCount+3; c++)
//	{
//       //make sure c isn’t over list size	
//	  Image img1 = timelineImg.get(c);
//       //display img on screen
//	}
  }
}
