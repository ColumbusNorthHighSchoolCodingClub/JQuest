package client;

import java.net.InetAddress;

public abstract class Client {

	protected final String identifier;
	protected final String ip;
	protected String serverIp;
	
	public Client(String identifier) throws Exception {
		this.identifier = identifier;
		this.ip = InetAddress.getLocalHost().toString();
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

	/**
	 * @return True if connection is successful
	 */
	public boolean connectToServer(String serverIp) {
		this.serverIp = serverIp;
		return false;
	}
	
	public abstract String packData();
	
	public void sendData(String data) {
		
	}
	
	public String receiveData() {
		
		return "";
	}
	
	public abstract void unpackData(String data);
	
	
}
