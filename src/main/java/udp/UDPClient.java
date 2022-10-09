package udp;

import java.net.*;
import java.io.*;

public class UDPClient {
    public static final String message = "hello world";
    public static final String hostname = "localhost";
    public static final int serverPort = 6789;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] m = message.getBytes();
            InetAddress host = InetAddress.getByName(hostname);
            DatagramPacket request = new DatagramPacket(m, m.length, host, serverPort);

            socket.send(request);

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

            socket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData()));
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
