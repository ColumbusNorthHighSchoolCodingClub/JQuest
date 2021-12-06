package server;

import java.io.IOException;
import java.net.Socket;

import common.SocketContainer;

public class ServerThread extends Thread {

    private final Socket socket;
    private final SocketContainer socketContainer;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        socketContainer = new SocketContainer(socket);
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {

                if (socketContainer.read() == null) {
                    System.out.println(socketContainer.getIdentifier() + " has disconnected.");
                    socket.close();
                }

                String input = socketContainer.read().trim();

                if (input.startsWith("ID:")) {
                    String id = input.substring(3);
                    socketContainer.setIdentifier(id);
                    System.out.println("ID " + id + " set for " + socket.getInetAddress());
                } else if (input.startsWith("PING:")) {
                    System.out.println(
                            "Ping received from " + socketContainer.getIdentifier() + " at time " + input.substring(5));
                }

            }

        } catch (IOException e) {

        }
    }

}
