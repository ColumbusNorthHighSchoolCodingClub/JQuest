package client;

public class ClientRunner {
    
    public static void main(String[] args) {
        try {
            Client client = new Client("Jmoney");
            client.connectToServer("localhost", 6979);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
