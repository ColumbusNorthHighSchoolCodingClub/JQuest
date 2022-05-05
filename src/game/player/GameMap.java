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


public class GameMap
{
	private Image ffbackground;
    private Image sfbackground;//Uses image of school layout
    public ArrayList<Integer> availableFFStations;
    public ArrayList<Integer> availableSFStations;
    public ArrayList<String> availableStations;
    
	public GameMap(ArrayList<Integer> availableffStations,ArrayList<Integer> availablesfStations,String currentStation,String currentDestination,String currentTimeline)
	{
		try
		{
			ffbackground = new Image(new FileInputStream("src\\images\\FirstFloor_Reference.png"));
			sfbackground = new Image(new FileInputStream("src\\images\\SecondFloor_Reference.png"));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
        availableFFStations = availableffStations;
        availableSFStations = availablesfStations;
        availableStations = new ArrayList<String>();
	}
    
    public GameMap() {
		// TODO Auto-generated constructor stub
	}

	public Image plantFFStations(Image xx, ArrayList<Integer> list)
    {
        WritableImage result = new WritableImage(ffbackground.getPixelReader(),(int)ffbackground.getWidth(),(int) ffbackground.getHeight());
        PixelWriter editor = result.getPixelWriter();
        for(int x=30; x<40;x++)
            for(int y=30; y<40;y++)
                editor.setColor(x, y, Color.CORAL);
        return result;
    }
    
    public Image plantSFStations(Image xx, ArrayList<Integer> list)
    {
        WritableImage result = new WritableImage(sfbackground.getPixelReader(),(int)sfbackground.getWidth(),(int) sfbackground.getHeight());
        PixelWriter editor = result.getPixelWriter();
        for(int x=30; x<40;x++)
            for(int y=30; y<40;y++)
                editor.setColor(x, y, Color.CORAL);
        return result;
    }
    
    public void TransferFFStations(ArrayList<Integer> availableFFStations)
    {
        
        for(Integer i : availableFFStations)
            switch (i)
            {
                case 1:
                    availableStations.add("Location");
                    continue;
                case 2:
                    availableStations.add("Location");
                    continue;
                case 3:
                    availableStations.add("Location");
                    continue;
                case 4:
                    availableStations.add("Location");
                    continue;
                case 5:
                    availableStations.add("Location");
                    continue;
                case 6:
                    availableStations.add("Location");
                    continue;
                case 7:
                    availableStations.add("Location");
                    continue;
                case 8:
                    availableStations.add("Location");
                    continue;
            }
    }
    
    public void TransferSFStations(ArrayList<Integer> availableSFStations)
    {
        
        for(Integer i : availableSFStations)
            switch (i)
            {
                case 1:
                    availableStations.add("Location");
                    continue;
                case 2:
                    availableStations.add("Location");
                    continue;
                case 3:
                    availableStations.add("Location");
                    continue;
                case 4:
                    availableStations.add("Location");
                    continue;
                case 5:
                    availableStations.add("Location");
                    continue;
                case 6:
                    availableStations.add("Location");
                    continue;
                case 7:
                    availableStations.add("Location");
                    continue;
                case 8:
                    availableStations.add("Location");
                    continue;
            }
    }
}