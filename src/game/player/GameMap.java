package game.player;

import java.util.ArrayList;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import misc.ImagePanel;


@SuppressWarnings("unused")
public class GameMap
{
	private Image ffbackground;
    private Image sfbackground;//Uses image of school layout
    public ArrayList<Integer> availableFFStations;
    public ArrayList<Integer> availableSFStations;
    public ArrayList<String> FFStations;
    public ArrayList<String> SFStations;
    
	public GameMap(ArrayList<Integer> availableffStations,ArrayList<Integer> availablesfStations,String currentStation,String currentDestination,String currentTimeline)
	{
		try
		{
			ffbackground = new Image(new FileInputStream("src\\game\\player\\images\\FirstFloor_Reference.png"));
			sfbackground = new Image(new FileInputStream("src\\game\\player\\images\\SecondFloor_Reference.png"));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
        availableFFStations = availableffStations;
        availableSFStations = availablesfStations;
        FFStations = new ArrayList<String>();
        SFStations = new ArrayList<String>();
	}
    
    public GameMap() {
		// TODO Auto-generated constructor stub
	}
    
    //Adds first floor map station indicators based on available coordinates in FFStations
	public Image plantFFStations(Image xx, ArrayList<Integer> list)
    {
       WritableImage result = new WritableImage(ffbackground.getPixelReader(),(int)ffbackground.getWidth(),(int) ffbackground.getHeight());
       PixelWriter editor = result.getPixelWriter();
       TransferFFStations(list);
       for(String i : FFStations) //Loop through all the available stations
        {
            int Xcoordinate = Integer.valueOf(i.substring(0,i.indexOf(","))); //get x-coordinate
            int Ycoordinate = Integer.valueOf(i.substring(i.indexOf(",")+1)); //get y-coordinate
            for(int x=Xcoordinate; x<Xcoordinate+15; x++)
                for(int y=Ycoordinate; y<Ycoordinate+15;y++)
                  editor.setColor(x, y, Color.GREEN);
        }
        return result;
    }

    //Adds second floor map station indicators based on available coordinates in SFStations
    public Image plantSFStations(Image xx, ArrayList<Integer> list)
    {
        WritableImage result = new WritableImage(sfbackground.getPixelReader(),(int)sfbackground.getWidth(),(int) sfbackground.getHeight());
        PixelWriter editor = result.getPixelWriter();
        TransferSFStations(list);
       for(String i : SFStations) //Loop through all the available stations
        {
            int Xcoordinate = Integer.valueOf(i.substring(0,i.indexOf(","))); //get x-coordinate
            int Ycoordinate = Integer.valueOf(i.substring(i.indexOf(",")+1)); //get y-coordinate
            for(int x=Xcoordinate; x<Xcoordinate+15; x++)
                for(int y=Ycoordinate; y<Ycoordinate+15;y++)
                  editor.setColor(x, y, Color.BLUE);
        }
        return result;
    }
    
    //Sets up x- and y-coordinates for first floor map stations
    public void TransferFFStations(ArrayList<Integer> availableFFStations)
    {
//        System.out.println("got to the transfer methodFF");
        for(Integer i : availableFFStations)
            switch (i)
            {
                case 1:
                    FFStations.add("120,100"); //Location of MR Spocks room
//                    System.out.println("got to FFstation1");
                    break ;
                case 2:
                    FFStations.add("x,y");
                    break;
                case 3:
                    FFStations.add("x,y");
                    break;
                case 4:
                    FFStations.add("x,y");
                    break;
                case 5:
                    FFStations.add("x,y");
                    break;
                case 6:
                    FFStations.add("x,y");
                    break;
                case 7:
                    FFStations.add("x,y");
                    break;
                case 8:
                    FFStations.add("x,y");
                    break;
            }
    }

    //Sets up x- and y-coordinates for second floor map stations
    public void TransferSFStations(ArrayList<Integer> availableSFStations)
    {
//        System.out.println("got to the transfer methodSF"); //check for transfer method in the trasnfer method 
        for(Integer i : availableSFStations)
            switch (i)
            {
                case 1:
                    SFStations.add("150,220"); //Location of Mr Spock's room
//                    System.out.println("got to SFstation1");
                    break;
                case 2:
                    SFStations.add("x,y");
                    break;
                case 3:
                    SFStations.add("x,y");
                    break;
                case 4:
                    SFStations.add("x,y");
                    break;
                case 5:
                    SFStations.add("x,y");
                    break;
                case 6:
                    SFStations.add("x,y");
                    break;
                case 7:
                    SFStations.add("x,y");
                    break;
                case 8:
                    SFStations.add("x,y");
                    break;
            }
    }
}