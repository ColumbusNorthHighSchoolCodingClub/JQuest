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


public class Map
{
	private Image ffbackground;
    private Image sfbackground;
    public ArrayList<String> availableStations;
    
	public Map(ArrayList<Integer> availableFFStations,ArrayList<Integer> availableSFStations,String currentStation,String currentDestination,String currentTimeline) throws FileNotFoundException
	{
		ffbackground = new Image(new FileInputStream("src\\images\\FirstFloor_Reference.png"));
		sfbackground = new Image(new FileInputStream("src\\images\\SecondFloor_Reference.png"));
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
