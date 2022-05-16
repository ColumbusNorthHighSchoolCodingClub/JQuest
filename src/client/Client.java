package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

import common.SocketContainer;

/**
 * Client that connects to a server.
 * 
 * For JQuest, each "Client" would be a station. This would probably best be
 * done by creating Station classes that contain a Client object--see
 * TemplateStation for how this can be done!
 */
public class Client {

    private final String identifier;
    private final String ip;
    private String serverIp;
    private Socket socket;
    private SocketContainer socketContainer;

    public Client(String identifier) throws IOException {
        this.identifier = identifier;
        this.ip = InetAddress.getLocalHost().toString();
        System.out.println("Client " + identifier + " has been started at " + ip);
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getIp() {
        return ip;
    }

    public String getServerIp() {
        return serverIp;
    }

//    public void setServerIp(String serverIp) {
//        this.serverIp = serverIp;
//    }

    int pingCount = 0;

    /**
     * Establish a connection with a server at the specified IP address and port
     * number.
     * 
     * @param serverIp server IP address
     * @param port     server port number
     * @return true if connection is successful, false if there is an exception
     */
    public boolean connectToServer(String serverIp, int port) {
        this.serverIp = serverIp;
        try {
            socket = new Socket(serverIp, port);
            System.out.println("Client " + identifier + " has connected to server " + serverIp + " on port " + port);
            socketContainer = new SocketContainer(socket);

            // Tells the server this Client's identifier (name)
            socketContainer.writeUTF("ID:" + identifier);
            socketContainer.setIdentifier(identifier);

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    // Put any code that must be continuously run while connected to server here...
                    // (For instance, if you want the client to continuously ping the server)
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 100, 200);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Instance variable used as part of sendAndAwaitReply method
    private String lastReceivedData = null;

    /**
     * Sends a specified String to the server and awaits a reply from the server,
     * with a specified timeout. If the timeout is exceeded, the method returns
     * null.
     * 
     * @param data    String to send to the server
     * @param timeout timeout, in milliseconds
     * @return the server's reply, or null if timed out
     */
    public String sendAndAwaitReply(String data, int timeout) {
        // Set lastReceivedData to null to indicate no new data has been received
        lastReceivedData = null;

        // Write data to server
        socketContainer.writeUTF(data);

        long initialTime = System.currentTimeMillis();
        long stopTime = initialTime + timeout;

        // Start new thread that tries to read data from the server
        Thread awaitReplyThread = new Thread(() -> lastReceivedData = socketContainer.readUTF());
        awaitReplyThread.start();

        // Interrupt the thread if data has been received OR timeout has been exceeded
        while (!awaitReplyThread.isInterrupted()) {
            if (lastReceivedData != null || System.currentTimeMillis() > stopTime) {
                awaitReplyThread.interrupt();
            }
        }

        return lastReceivedData;
    }

    /**
     * Sends a specified String to the server.
     * 
     * @param data String to send to the server
     */
    public void sendData(String data) {
        socketContainer.writeUTF(data);
    }

    public String sendAsync(String data) {
        CompletableFuture<String> complete = CompletableFuture.supplyAsync(() -> {
            return sendAndAwaitReply(data, 2000);
        });
        try {
            return complete.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
