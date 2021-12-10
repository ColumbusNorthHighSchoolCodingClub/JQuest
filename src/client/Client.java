package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import common.SocketContainer;

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

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    int pingCount = 0;

    /**
     * @return True if connection is successful
     */
    public boolean connectToServer(String serverIp, int port) {
        this.serverIp = serverIp;
        try {
            socket = new Socket(serverIp, port);
            System.out.println("Client " + identifier + " has connected to server " + serverIp + " on port " + port);
            socketContainer = new SocketContainer(socket);
            socketContainer.write("ID:" + identifier);
            socketContainer.setIdentifier(identifier);

            TimerTask task = new TimerTask() {

                @Override
                public void run() {
                    socketContainer.write("PING:" + pingCount);
                    System.out.println(pingCount);
                    pingCount++;
                    System.out.println("Ping!");
                }

            };
            Timer timer = new Timer();
            timer.schedule(task, 100, 200);

            return true;
        } catch (IOException e) {
            System.out.println(e);
        }

        return false;
    }

//    //data comes from GameManager and contains HashMap entry with gameID
//    public abstract String packData(HashMap<String,String> data);
//    
//    
//    //unpacks, and after unpacking, client checks gameID and sends to respective game instance
//    public abstract HashMap<String,String> unpackData(String data);

}
