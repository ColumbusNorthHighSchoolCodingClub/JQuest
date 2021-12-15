package game;

import java.io.IOException;
import java.util.Map;

import client.Client;
import util.JsonManager;

public class TemplateStation {

	Map<String,String> currentUserProfile;
	private Client c;
	private String currentUserId;
	
	public TemplateStation(String stationName) throws IOException {
		c = new Client(stationName);
		c.connectToServer(scanServerIp(), 6979);
	}
	
	public static String scanServerIp() {
		return "localhost";
	}
	
	public void authenticatePlayer(String id) {
		
		var profileData = c.sendAndAwaitReply("Authenticate:"+id);
		currentUserProfile = JsonManager.getMap(profileData);
		currentUserId = id;
		System.out.println(currentUserProfile);
	}
	
	public void updateUserProfileOnServer() {
		var exportData = JsonManager.getJSONString(currentUserProfile);
		c.sendAndAwaitReply("UPDATE:"+currentUserId + "%" + exportData);
	}
	
	
}
