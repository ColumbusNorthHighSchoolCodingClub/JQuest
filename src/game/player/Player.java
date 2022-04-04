
package game.player;

import java.util.ArrayList;

public class Player //The Class that stores and manipulates data relative to the players in the game.
{
	private String userName;
	private String userID;
	private ArrayList<Item> inv; //Arraylist of all items stored in the player
	private int health;
	private final int maxHealth; //Maximum possible health value
	private final int baseDamage; //Starting damage that is added upon
	private int jMoney; //Currency value
	private String charClass;
	private String currentStation; //Current station on map
	private String currentTimeline; //Current location in timelines
	private String currentDestination; //Station of current(selected?) objective/quest
	private ArrayList<String> currentObjectives; //Arraylist of all objectives/quests
	private ArrayList<Integer> availableStations; //Arraylist of all stations available to go to
	private Map map; //Map object relative to player
	
	public Player(String name, String id, String dest, String station, String characterClass)
	{
		userName = name;
		userID = id;
		charClass = characterClass;
		switch(charClass)
		{
			case "Thief":
				maxHealth = 10;
				baseDamage = 1;
				break;
			case "Mage":
				maxHealth = 15;
				baseDamage = 2;
				break;
			case "Brute":
				maxHealth = 20;
				baseDamage = 3;
				break;
			default:
				maxHealth = 10;
				baseDamage = 1;
		}
		health = maxHealth;
		jMoney = 0;
		currentStation = station;
		currentTimeline = "Modern";
		currentDestination = dest;
		currentObjectives = new ArrayList<String>();
		availableStations = new ArrayList<Integer>();
		inv = new ArrayList<Item>();
		map = new Map(availableStations,currentStation,currentDestination,currentTimeline);
	}

	public String getUserName() {return userName;} //Returns player's userName
	public String getID() {return userID;} //Returns player's userID
	public ArrayList<Item> getInventory() {return inv;} //Returns arraylist of Strings in player's inventory
	public void addItem(Item item)
	{
		inv.add(item);
		updateMap();
	}
	public void removeItem(Item item)
	{
		if(inv.contains(item))
		{
			inv.remove(inv.indexOf(item));
			updateMap();
		}
	}
	public int getHealth() {return health;} //Returns player's health value
	public void addHealth(int heal) //Easy way of modifying health
	{
		health += heal;
		if(health > maxHealth) health = maxHealth;
	}
	public void removeHealth(int heal) //Easy way of modifying health
	{
		health -= heal;
		if(health < 0) health = 0;
	}
	public void resetHealth() {health = maxHealth;}  //Resets player's to maximum possible value
	public int getMoney() {return jMoney;} //Returns player's currency value
	public void addMoney(int mon) {jMoney += mon;} //Easy way of modifying currency
	public void removeMoney(int mon) {jMoney -= mon;} //Easy way of modifying currency
	public String getCharClass() {return charClass;} //Returns player's class
	public String getStation() {return currentStation;} //Returns player's location within the map
	public String getTimeline() {return currentTimeline;} //Returns player's timeline
	public String getDestination() {return currentDestination;} //Returns player's destination within the map
	public ArrayList<Integer> getAvailableStations() {return availableStations;} //Returns arraylist of available stations to go to
	public Map getMap() {return map;} //Returns a map relative to this player
	
	public void setCharClass(String chaCla)
	{
		if(charClass.equals(null))
			charClass = chaCla;
	}

	public void addObjective(String obj)
	{
		if(!currentObjectives.contains(obj))
		{
			currentObjectives.add(obj);
			updateMap();
		}
	}
	
	public void removeObjective(String obj)
	{
		if(currentObjectives.contains(obj))
		{
			currentObjectives.remove(obj);
			updateMap();
		}
	}
	
	public void updateStation(String station)
	{
		if(!currentStation.equals(station))
		{
			currentStation = station;
			updateMap();
		}
	}
	
	public void updateTimeline(String tl)
	{
		if(!currentTimeline.equals(tl))
		{
			currentTimeline = tl;
			updateMap();
		}
	}
	
	public void updateDestination(String dest)
	{
		if(!currentDestination.equals(dest))
		{
			currentDestination = dest;
			updateMap();
		}
	}
	
	//Makes the map into a new object based on given station and objective data
	public void updateMap()
	{
		map = new Map(availableStations,currentStation,currentDestination,currentTimeline);
	}
	
	//Returns damage based on a given weapon's damage and the player's base damage
	public int getDamage(int weaponDamage)
	{
		int damage;
		damage = baseDamage * weaponDamage;
		damage = (int) Math.floor(Math.random()*(damage+baseDamage+1));
		return damage;
	}
}