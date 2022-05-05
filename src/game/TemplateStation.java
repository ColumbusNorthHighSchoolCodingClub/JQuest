package game;

import java.io.IOException;

import client.Client;

public class TemplateStation {

    private Client c;

    protected String currentUserId;

    public TemplateStation(String stationName) throws IOException {
        c = new Client(stationName);
        c.connectToServer(scanServerIp(), 6979);
    }

    public static String scanServerIp() {
        return "localhost";
    }
    
    public void sendData(String data) {
        c.sendData(data);
    }

}
