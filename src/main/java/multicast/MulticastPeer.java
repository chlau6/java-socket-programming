package multicast;

import java.net.*;
import java.io.*;

public class MulticastPeer {
    public static final String message = "hello world";
    public static final String multicastGroupDestination = "228.5.6.7";
    public static final int port = 6789;

    public static void main(String[] args) {
        MulticastSocket s = null;

        try {
            InetAddress group = InetAddress.getByName(multicastGroupDestination);
            s = new MulticastSocket(port);

            s.joinGroup(group);
            
            byte[] m = message.getBytes();
            DatagramPacket messageOut = new DatagramPacket(m, m.length, group, port);
            s.send(messageOut);

            byte[] buffer = new byte[1000];
            for (int i = 0; i < 3; i++) { // get messages from others in group
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                s.receive(messageIn);
                System.out.println("Received:" + new String(messageIn.getData()));
            }

            s.leaveGroup(group);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (s != null) s.close();
        }
    }
}