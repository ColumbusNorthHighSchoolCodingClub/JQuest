package server;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import common.SocketContainer;
import game.player.Player;
import util.JsonManager;

public class ServerThread extends Thread {

    private final int MAX_PING_DELAY = 500;

    private final Socket socket;
    private final SocketContainer socketContainer;

    private long lastPingTime;
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
        while (true) {
            String input = socketContainer.readUTF().trim();
//            lastPingTime = System.currentTimeMillis();

            if (input.startsWith("ID:")) {
                String id = input.substring("ID:".length());
                socketContainer.setIdentifier(id);
                System.out.println("ID " + id + " set for " + socket.getInetAddress());
            }

            else if (input.startsWith("UPDATE:")) {
                String playerId = input.substring("UPDATE:".length(), input.indexOf(";"));
                String commands = input.substring(input.indexOf(";") + 1);
                Player player = server.getPlayer(playerId);
                player.giveCommand(commands);
                System.out.println(playerId + " has " + player.getMoney() + " monies");
            }
        }

//		while (System.currentTimeMillis() - lastPingTime < maxPingDelay) {
//
//			String input = socketContainer.read();
//
//			if (input != null) {
//				lastPingTime = System.currentTimeMillis();
////                input = input.trim();
//
//				// System.out.println(input);
//				if (input.startsWith("ID:")) {
//					String id = input.substring(3);
//					socketContainer.setIdentifier(id);
//					System.out.println("ID " + id + " set for " + socket.getInetAddress());
//				} else if (input.startsWith("PING:")) {
//					// System.out.println("Ping number " + input.substring(5) + " received from " +
//					// socketContainer.getIdentifier());
//				} else if (input.startsWith("Authenticate:")) {
//					System.out.println(input);
//					String playerId = input.replace("Authenticate:", "");
//					Map<String,String> profile = outer.getAllProfiles().get(playerId);
//					socketContainer.writeUTF(JsonManager.getJSONString(profile));
//
//				} else if (input.startsWith("UPDATE:")) {
//					String[] data = input.replace("UPDATE:", "").split("%");
//					outer.getAllProfiles().put(data[0], JsonManager.getMap(data[1]));
//					
//					
//				}
//			}
//
//		}

//		System.out.println("Socket closed.");
////        this.interrupt();
    }

}
