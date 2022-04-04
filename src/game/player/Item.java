package game.player;

import java.awt.image.BufferedImage;

public class Item
{
	private String name; //Name of item
	private String utilityType; //Use for item; whether it is a weapon, healing, etc...
	private int actionValue; //Value for item's utility; # of health recovered if healing, # of damage if weapon, etc...
	private int value; //Cost of item
	private BufferedImage image; //Image of item
	public Item(String nm,String useType,int actVal,int val,BufferedImage pic)
	{
		name = nm;
		utilityType = useType;
		actionValue = actVal;
		value = val;
		image = pic.getSubimage(0,0,pic.getWidth(),pic.getHeight());
	}
	public String getName() {return name;}
	public String getUtility() {return utilityType;}
	public int getActionValue() {return actionValue;}
	public int getValue() {return value;}
	public BufferedImage getImage() {return image;}
}
