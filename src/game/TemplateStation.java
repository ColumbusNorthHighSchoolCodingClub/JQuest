package game;

import java.io.IOException;
import java.util.Map;

import client.Client;
import util.JsonManager;

public class TemplateStation {

    private Client c;

    protected Map<String, String> currentUserProfile;
    protected String currentUserId;

    public TemplateStation(String stationName) throws IOException {
        c = new Client(stationName);
        c.connectToServer(scanServerIp(), 6979);
    }

    public static String scanServerIp() {
        return "localhost";
    }

    public void authenticatePlayer(String id) {
        String profileData = c.sendAndAwaitReply("Authenticate:" + id);
        this.currentUserProfile = JsonManager.getMap(profileData);
        this.currentUserId = id;
        System.out.println(currentUserProfile);
    }
    
    public void addToProfile(String key, String value) {
        currentUserProfile.put(key, value);
        
    }

    public void updateUserProfileOnServer() {
        String exportData = JsonManager.getJSONString(currentUserProfile);
        c.sendData("UPDATE:" + currentUserId + "%" + exportData);
    }

}
