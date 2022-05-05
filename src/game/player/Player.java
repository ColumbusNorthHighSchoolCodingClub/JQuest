package game.player;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Player // The Class that stores and manipulates data relative to the players in the
                    // game.
{
    private String userName; // Name of player
    private String userID; // Unique string login for the player
    private Inventory inv; // Arraylist of all items stored in the player
    private int health; // Hitpoints
    private final int maxHealth; // Maximum possible health value
    private final int baseDamage; // Starting damage that is added upon
    private int jMoney; // Currency value
    private Item weapon; // Currently held weapon Item
    private String charClass; // Class of player
    private String currentStation; // Current station on map
    private String currentTimeline; // Current location in timelines
    private String currentDestination; // Station of current(selected?) objective/quest
    private ArrayList<String> currentObjectives; // Arraylist of all objectives/quests
    private ArrayList<Integer> availableFFStations; // Arraylist of all stations available to go to on first floor
    private ArrayList<Integer> availableSFStations; // Arraylist of all stations available to go to on second floor
    private GameMap map; // Map object relative to player

    public Player(String name, String id, String dest, String station, String characterClass)
            throws FileNotFoundException {
        userName = name;
        userID = id;
        charClass = characterClass;
        switch (charClass) {
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
        availableFFStations = new ArrayList<Integer>();
        availableSFStations = new ArrayList<Integer>();
        inv = new Inventory();
        map = new GameMap(availableFFStations, availableSFStations, currentStation, currentDestination,
                currentTimeline);
    }

    public String getUserName() {
        return userName;
    } // Returns player's userName

    public String getID() {
        return userID;
    } // Returns player's userID

    public Inventory getInventory() {
        return inv;
    } // Returns arraylist of Strings in player's inventory

    public void addItem(Item item) // Adds item to the inventory
    {
        inv.addItem(item);
        updateMap();
    }

    public void removeItem(Item item) // Removes item from the inventory
    {
        inv.removeItem(item);
        updateMap();
    }

    public void removeItem(String name) // Removes item from the inventory
    {
        inv.removeItem(name);
        updateMap();
    }

    public int getHealth() {
        return health;
    } // Returns player's health value

    public void addHealth(int heal) // Easy way of modifying health
    {
        health += heal;
        if (health > maxHealth)
            health = maxHealth;
    }

    public void removeHealth(int heal) // Easy way of modifying health
    {
        health -= heal;
        if (health < 0)
            health = 0;
    }

    public void resetHealth() {
        health = maxHealth;
    } // Resets player's to maximum possible value

    public int getMoney() {
        return jMoney;
    } // Returns player's currency value

    public void addMoney(int mon) {
        jMoney += mon;
    } // Easy way of modifying currency

    public void removeMoney(int mon) {
        jMoney -= mon;
    } // Easy way of modifying currency

    public Item getWeapon() {
        return weapon;
    } // Returns player's weapon

    public String getCharClass() {
        return charClass;
    } // Returns player's class

    public String getStation() {
        return currentStation;
    } // Returns player's location within the map

    public String getTimeline() {
        return currentTimeline;
    } // Returns player's timeline

    public String getDestination() {
        return currentDestination;
    } // Returns player's destination within the map

    public ArrayList<Integer> getAvailableFFStations() {
        return availableFFStations;
    } // Returns arraylist of available stations to go to

    public ArrayList<Integer> getAvailableSFStations() {
        return availableSFStations;
    }

    public GameMap getMap() {
        return map;
    } // Returns a map relative to this player

    
    /*
     * Commands are given as follows:
     * 
     * addHealth 10
     * addMoney 30
     * 
     * 
     */
    public void giveCommand(String command) {
        if (command.length() > 0) {
            command = command.trim();
            String[] commands = command.split("\n");
            for (String fullCommand : commands) {
                String[] args = fullCommand.split(" ");

                String commandKeyword = args[0];

                if (commandKeyword.equals("addHealth")) {
                    int actionValue = Integer.valueOf(args[1]);
                    addHealth(actionValue);
                }
                if (commandKeyword.equals("removeHealth")) {

                    int actionValue = Integer.valueOf(args[1]);
                    removeHealth(actionValue);
                }
                if (commandKeyword.equals("resetHealth")) {
                    resetHealth();
                }
                if (commandKeyword.equals("getHealth")) {
                    String output = "" + getHealth();
                    outputCommandResponse(output);
                }
                if (commandKeyword.equals("addMoney")) {
                    int actionValue = Integer.valueOf(args[1]);
                    addMoney(actionValue);
                }
                if (commandKeyword.equals("removeMoney")) {
                    int actionValue = Integer.valueOf(args[1]);
                    removeMoney(actionValue);
                }
                if (commandKeyword.equals("getMoney")) {
                    String output = "" + getMoney();
                    outputCommandResponse(output);
                }
                if (commandKeyword.equals("removeItem")) {
                    String itemName = args[1];
                    removeItem(itemName);
                }
            }

        }
    }

    private void outputCommandResponse(String out) {
        System.out.println(out);
    }

    public void setCharClass(String chaCla) {
        if (charClass.equals(null))
            charClass = chaCla;
    }

    public void addObjective(String obj) {
        if (!currentObjectives.contains(obj)) {
            currentObjectives.add(obj);
            updateMap();
        }
    }

    public void removeObjective(String obj) {
        if (currentObjectives.contains(obj)) {
            currentObjectives.remove(obj);
            updateMap();
        }
    }

    public void updateStation(String station) {
        if (!currentStation.equals(station)) {
            currentStation = station;
            updateMap();
        }
    }

    public void updateTimeline(String tl) {
        if (!currentTimeline.equals(tl)) {
            currentTimeline = tl;
            updateMap();
        }
    }

    public void updateDestination(String dest) {
        if (!currentDestination.equals(dest)) {
            currentDestination = dest;
            updateMap();
        }
    }

    // Makes the map into a new object based on given station and objective data
    public void updateMap() {
        try {
            map = new GameMap(availableFFStations, availableSFStations, currentStation, currentDestination,
                    currentTimeline);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // If the given item is a weapon, sets the current weapon to the given weapon,
    // and adds any equipped weapons to the inventory.
    public void equipItem(Item item) {
        if (item.getUtility().matches("Weapon")) {
            if (weapon != null) {
                addItem(weapon);
            }
            weapon = item.clone();
            removeItem(weapon);
        }
    }

    // If the given item is a weapon, sets the current weapon to the given weapon,
    // and adds any equipped weapons to the inventory.
    public void unequipItem() {
        if (weapon != null) {
            addItem(weapon);
            weapon = null;
        }
    }

    // Returns damage based on and the player's base damage and weapon's damage
    public int getDamage() {
        int damage;
        if (weapon == null)
            damage = baseDamage;
        else
            damage = baseDamage * weapon.getActionValue();
        damage = (int) Math.floor(Math.random() * (damage + baseDamage + 1));
        return damage;
    }
}