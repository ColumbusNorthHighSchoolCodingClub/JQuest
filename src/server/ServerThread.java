package server;

import java.io.IOException;
import java.net.Socket;

import common.SocketContainer;

public class ServerThread extends Thread {

    private final int maxPingDelay = 500;

    private final Socket socket;
    private final SocketContainer socketContainer;

    private long lastPingTime;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        socketContainer = new SocketContainer(socket);
        lastPingTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (System.currentTimeMillis() - lastPingTime < maxPingDelay) {

            String input = socketContainer.read();

            if (input != null) {
                lastPingTime = System.currentTimeMillis();
//                input = input.trim();

//                  System.out.println(input);
                if (input.startsWith("ID:")) {
                    String id = input.substring(3);
                    socketContainer.setIdentifier(id);
                    System.out.println("ID " + id + " set for " + socket.getInetAddress());
                } else if (input.startsWith("PING:")) {
                    System.out.println(
                            "Ping number " + input.substring(5) + " received from " + socketContainer.getIdentifier());
                }
            }

        }

        System.out.println("Socket closed.");
//        this.interrupt();
    }

}
