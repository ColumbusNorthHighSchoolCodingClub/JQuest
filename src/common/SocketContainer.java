package common;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketContainer {

    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;

    private String identifier;

    public SocketContainer(Socket socket) throws IOException {
        this.socket = socket;
        InputStream inStream = socket.getInputStream();
        in = new DataInputStream(new BufferedInputStream(inStream));
        out = new DataOutputStream(socket.getOutputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public String read() {
        try {
            return in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String str) {
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
