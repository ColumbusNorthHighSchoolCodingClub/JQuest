package client;

public class ClientRunner {

    public static void main(String[] args) {
        try {
            Client client = new Client("Jmoney");
            client.connectToServer("localhost", 6979);
            Thread.sleep(1000);

            System.exit(0);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
