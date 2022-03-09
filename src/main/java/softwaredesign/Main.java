package softwaredesign;

import java.io.*;

import java.net.*;
import java.util.Arrays;
import io.netty.*
public class Main {
    public static void main (String[] args) throws IOException {
        Socket socket = new Socket();
        InetAddress inetAddress=InetAddress.getByName("localhost");
        System.out.println(inetAddress.toString());
        //the port should be greater or equal to 0, else it will throw an error
        int port=3333;
        //calling the constructor of the SocketAddress class
        SocketAddress socketAddress=new InetSocketAddress(inetAddress, port);
        //binding the  socket with the inetAddress and port number
        //connect() method connects the specified socket to the server
        socket.connect(socketAddress);
        System.out.println("Inet address: "+socket.getInetAddress());
        System.out.println("Port number: "+socket.getLocalPort());
    }
}