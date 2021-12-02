package server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

	public final String ip;
	public final int port;
	public final ServerSocket ss;
	
	public HashMap<String,Socket> clientConnections;
	
	public Server(int port) throws Exception{
		this.ip = InetAddress.getLocalHost().toString();
		this.port = port;
		this.ss = new ServerSocket(port);
		this.clientConnections = new HashMap<>();
	}
	
	
	
}
