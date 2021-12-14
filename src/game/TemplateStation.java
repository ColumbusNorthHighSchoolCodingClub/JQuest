package game;

import java.io.IOException;
import java.util.Map;

import client.Client;
import util.JsonManager;

public class TemplateStation {

	Map<String,String> currentUserProfile;
	private Client c;
	
	public TemplateStation(String stationName) throws IOException {
		c = new Client(stationName);
		c.connectToServer(scanServerIp(), 6979);
	}
	
	public static String scanServerIp() {
		return "localhost";
	}
	
	public void authenticatePlayer(String id) {
		
		String profileData = c.sendAndAwaitReply(id);
		currentUserProfile = JsonManager.getMap(profileData);
		
	}
	
	public void updateUserProfileOnServer() {
		String exportData = JsonManager.getJSONString(currentUserProfile);
		c.sendAndAwaitReply(exportData);
	}
	
	
}
