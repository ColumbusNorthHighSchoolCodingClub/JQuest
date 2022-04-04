package game.player;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Map
{
	private final int imgWidth = 1200;
	private final int imgHeight = 800;
	private BufferedImage background; //Uses image of school layout?
	private BufferedImage overlay; //Overlay of stations on background
	public Map(ArrayList<Integer> availableStations,String currentStation,String currentDestination,String currentTimeline)
	{
		background = new BufferedImage(imgWidth,imgHeight, 0);
		overlay = new BufferedImage(imgWidth,imgHeight, 0);
	}
}
