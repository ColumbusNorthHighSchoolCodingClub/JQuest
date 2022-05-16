package common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Container class for a socket connection that provides method for reading and
 * writing String objects.
 * 
 * Every time we create a Socket object, we will probably want to create a
 * SocketContainer containing it for ease of reading/writing data.
 *
 */
public class SocketContainer {

    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;

    private String identifier; // This is just a name you may want to give to help identify this
                               // SocketContainer

    public SocketContainer(Socket socket) throws IOException {
        this.socket = socket;

        // This is where you read data sent by the other side of the connection
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        // This is where you write data to send to the other side of the connection
        out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public Socket getSocket() {
        return socket;
    }

    /**
     * Reads the next String received from the data input stream.
     * 
     * NOTE: This method blocks until it has received data OR the connection has
     * been terminated.
     * 
     * @return String read from data input, or null if connection terminated
     */
    public String readUTF() {
        try {
            return in.readUTF();
        } catch (IOException e) {
            System.out.println("Socket " + identifier + " closed!");
        }
        return null;
    }

    /**
     * Writes the specified String to the data output stream.
     * 
     * @param str the String to write
     */
    public void writeUTF(String str) {
        try {
            out.writeUTF(str);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

}
