package server;

public class ServerRunner {

    public static void main(String[] args) {
        try {
            Server server = new Server(6979); // be sure to specify the port you want here!!
            server.acceptConnections();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Uh oh!");
        }
    }

}
