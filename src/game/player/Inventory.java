package game.player;

import java.util.ArrayList;

public class Inventory
{
	private ArrayList<Item> items;
	public Inventory()
	{
		items = new ArrayList<>();
	}
	public ArrayList<Item> getInventory() //Returns Item ArrayList
	{
		return items;
	}
	public void addItem(Item item) //Adds given Item to the ArrayList
	{
		items.add(item);
	}
	public void removeItem(Item item) //Removes given Item from the ArrayList
	{
		for(int i=0;i<items.size();i++)
		{
			if(items.get(i).getName().equals(item.getName()))
			{
				items.remove(i);
				break;
			}
		}
	}
	public boolean hasItem(Item item) //Checks if the name of the given Item is the name of an Item in the ArrayList
	{
		for(int i=0;i<items.size();i++)
		{
			if(items.get(i).getName().equals(item.getName()))
			{
				return true;
			}
		}
		return false;
	}
	public boolean hasItem(String name) //Checks if the given name is the name of an Item in the ArrayList
	{
		for(int i=0;i<items.size();i++)
		{
			if(items.get(i).getName().equals(name))
			{
				return true;
			}
		}
		return false;
	}
	public boolean hasItemWithUtility(Item item) //Checks if the utility of the given Item is the utility of an Item in the ArrayList
	{
		for(int i=0;i<items.size();i++)
		{
			if(items.get(i).getUtility().equals(item.getUtility()))
			{
				return true;
			}
		}
		return false;
	}
	public boolean hasItemWithUtility(String utility) //Checks if the given utility is the utility of an Item in the ArrayList
	{
		for(int i=0;i<items.size();i++)
		{
			if(items.get(i).getUtility().equals(utility))
			{
				return true;
			}
		}
		return false;
	}
	public Item getItem(Item item) //Gets Item from the ArrayList with the same name as the given Item
	{
		for(int i=0;i<items.size();i++)
		{
			if(items.get(i).getName().equals(item.getName()))
			{
				return items.get(i);
			}
		}
		return null;
	}
	public Item getItem(String name) //Gets Item from the ArrayList with the same name as the given String
	{
		for(int i=0;i<items.size();i++)
		{
			if(items.get(i).getName().equals(name))
			{
				return items.get(i);
			}
		}
		return null;
	}
	public Item getItemWithUtility(Item item) //Gets Item from the ArrayList with the same utility as the given Item
	{
		for(int i=0;i<items.size();i++)
		{
			if(items.get(i).getUtility().equals(item.getUtility()))
			{
				return items.get(i);
			}
		}
		return null;
	}
	public Item getItemWithUtility(String utility) //Gets Item from the ArrayList with the same utility as the given String
	{
		for(int i=0;i<items.size();i++)
		{
			if(items.get(i).getUtility().equals(utility))
			{
				return items.get(i);
			}
		}
		return null;
	}
}