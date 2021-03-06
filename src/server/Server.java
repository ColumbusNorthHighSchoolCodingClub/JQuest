package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import game.player.Player;

public class Server {

    private final String ip;
    private final int port;
    private final ServerSocket ss;

    // HashMap that maps player ID with the corresponding Player object
    private Map<String, Player> allPlayers = new HashMap<String, Player>();

    public Server(int port) throws IOException {
        this.ip = InetAddress.getLocalHost().toString();
        this.port = port;
        this.ss = new ServerSocket(port);
        System.out.println("Server has been started on port " + port);

        // Populating playerlist with examples for testing...need a way to continuously
        // save this data!
        allPlayers.put("JMONEY", new Player("Jiaxuan", "JMONEY", "Dest1", "StationOne", "Thief"));
        allPlayers.put("SPOCK", new Player("Spock", "SPOCK", "Dest2", "Station999", "Mage"));

    }

    /**
     * Continuously accepts client connections.
     */
    public void acceptConnections() {

        System.out.println("Accepting connections...");

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    Socket clientSocket = ss.accept(); // NOTE: blocks until a new connection is made!
                    String clientIp = clientSocket.getInetAddress().toString();
                    System.out.println("Client has connected at " + clientIp);

                    // Start a new ServerThread to handle the new client connection
                    new ServerThread(clientSocket, Server.this).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 0, 1);
    }

    /**
     * Returns the server IP address.
     * 
     * @return server IP address
     */
    public String getIp() {
        return ip;
    }

    /**
     * Returns the Player object corresponding to the specified player ID.
     * 
     * @param id player ID
     * @return Player object with the specified ID, or null if there is no Player
     *         with that ID
     */
    public Player getPlayer(String id) {
        return allPlayers.get(id);
    }

}
