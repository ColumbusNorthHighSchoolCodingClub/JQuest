package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Server {

    private final String ip;
    private final int port;
    private final ServerSocket ss;
    private Map<String,Map<String,String>> allProfiles = new HashMap<>();
    
    public Server(int port) throws IOException {
        this.ip = InetAddress.getLocalHost().toString();
        this.port = port;
        this.ss = new ServerSocket(port);
        System.out.println("Server has been started on port " + port);
        
        //test server profiles
        Map<String,String> profileA = new HashMap<>();
        profileA.put("Name", "JMONEYANDRISHI");
        allProfiles.put("1", profileA);
        
    }

    public void acceptConnections() {

        System.out.println("Accepting connections...");

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {

                    Socket clientSocket = ss.accept();
                    String clientIp = clientSocket.getInetAddress().toString();
                    System.out.println("Client has connected at " + clientIp);
                    new ServerThread(clientSocket, Server.this).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 0, 1);

    }
    
    

    public Map<String, Map<String, String>> getAllProfiles() {
		return allProfiles;
	}



	public String getIp() {
        return ip;
    }

}
