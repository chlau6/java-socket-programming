package tcp;

import java.net.*;
import java.io.*;

public class TCPClient {
    public static final String message = "hello world";
    public static final String hostname = "localhost";
    public static final int serverPort = 7896;

    public static void main(String[] args) {
        try (Socket s = new Socket(hostname, serverPort)) {
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(message);

            String data = in.readUTF();
            System.out.println("Received: " + data);
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
    }
}
