package server;

import java.io.IOException;
import java.net.Socket;

import common.SocketContainer;
import game.player.Player;

public class ServerThread extends Thread {

    // Maximum number of milliseconds of reading null before socket is closed
    private final int MAX_PING_DELAY = 500;

    private final Socket socket;
    private final SocketContainer socketContainer;

    // Keeps track of last time data was received for timeout
    private long lastPingTime;

    // Server object that started this thread
    private Server server;

    public ServerThread(Socket socket, Server outer) throws IOException {
        this.socket = socket;
        this.server = outer;
        socketContainer = new SocketContainer(socket);
        lastPingTime = System.currentTimeMillis();

    }

    @Override
    public void run() {

        // Continuously try to read data from the client
        while (!socket.isClosed()) {
            // Close socket if it has been continuously reading null for some time
            if (System.currentTimeMillis() - lastPingTime > MAX_PING_DELAY) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            String input = socketContainer.readUTF();

            // Input is null only if connection has been terminated
            if (input != null) {
                lastPingTime = System.currentTimeMillis();

                input = input.trim();

                if (input.startsWith("ID:")) {
                    String id = input.substring("ID:".length());
                    socketContainer.setIdentifier(id);
                    System.out.println("ID " + id + " set for " + socket.getInetAddress());
                }

                else if (input.startsWith("COMMAND:")) {
                    String playerId = input.substring("COMMAND:".length(), input.indexOf(";"));
                    String commands = input.substring(input.indexOf(";") + 1);
                    Player player = server.getPlayer(playerId);

                    // If player is not null, there is a Player that exists with the given ID
                    if (player != null) {
                        // Give command to the player
                        String output = player.giveSingleCommand(commands);

                        // If the command resulted in something being returned, send it to the client
                        if (output != null) {
                            socketContainer.writeUTF(output);
                        }
                        System.out.println(playerId + " has " + player.getMoney() + " monies");
                    }
                    // player is null if there is no Player with the given ID
                    else {
                        System.out.println("No player found with ID " + playerId);
                    }
                }
            }

        }

    }

}
