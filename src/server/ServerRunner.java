package server;

public class ServerRunner {

    public static void main(String[] args) {
        try {
            Server server = new Server(6979);
            server.acceptConnections();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
