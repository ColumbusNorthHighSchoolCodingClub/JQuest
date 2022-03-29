import java.awt.image.BufferedImage;

public class Item
{
	private String name; //Name of item
	private BufferedImage image; //Image of item
	private int value; //Cost of item
	public Item(String nm,BufferedImage pic,int val)
	{
		name = nm;
		image = pic.getSubimage(0,0,pic.getWidth(),pic.getHeight());
		value = val;
	}
	public String getName() {return name;}
	public BufferedImage getImage() {return image;}
	public int getValue() {return value;}
}
