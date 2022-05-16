package game;

import java.io.IOException;

import client.Client;

public class TemplateStation {

    // Variables have protected access so they can be accessed by subclasses
    protected Client client;
    protected String currentUserId;

    public TemplateStation(String stationName) throws IOException {
        client = new Client(stationName);
        client.connectToServer(scanServerIp(), 6979);
    }

    public static String scanServerIp() {
        // "localhost" is if you are running the server on the same computer.
        // This will have to be changed for the final game, either by implementing a way
        // to scan for the server IP, or it can be hardcoded in if we know what the
        // server IP will be.
        return "localhost";
    }

    // -------------------------------------------------------------------------
    // The following methods all command the Player who currentUserId points to.
    // --------------------------------------------------------------------------

    public void addHealthToPlayer(int amount) {
        client.sendData("COMMAND:" + currentUserId + ";" + "addHealth " + amount);
    }

    public void removeHealthFromPlayer(int amount) {
        client.sendData("COMMAND:" + currentUserId + ";" + "removeHealth " + amount);
    }

    public void resetPlayerHealth() {
        client.sendData("COMMAND:" + currentUserId + ";" + "resetHealth");
    }

    public int getPlayerHealth() throws IOException {
        String data = client.sendAndAwaitReply("COMMAND:" + currentUserId + ";" + "getHealth", 100);
        if (data == null) {
            throw new IOException();
        }
        return Integer.parseInt(data);
    }

    public void addMoneyToPlayer(int amount) {
        client.sendData("COMMAND:" + currentUserId + ";" + "addMoney " + amount);
    }

    public void removeMoneyFromPlayer(int amount) {
        client.sendData("COMMAND:" + currentUserId + ";" + "removeMoney " + amount);
    }

    public int getPlayerMoney() throws IOException {
        String data = client.sendAndAwaitReply("COMMAND:" + currentUserId + ";" + "getMoney", 100);
        if (data == null) {
            throw new IOException();
        }
        return Integer.parseInt(data);
    }

    public void removeItemFromPlayer(String itemName) {
        client.sendData("COMMAND:" + currentUserId + ";" + "removeItem " + itemName);
    }

}
