package softwaredesign;

import java.io.*;
import io.socket.client.IO;
import io.socket.client.Socket;

import java.util.Arrays;
public class Main {
    public static void main (String[] args) throws IOException {
        String url = "http://localhost:9999";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            // Number of failed retries
            options.reconnectionAttempts = 10;
            // Time interval for failed reconnection
            options.reconnectionDelay = 1000;
            // Connection timeout (ms)
            options.timeout = 500;
            final Socket socket = IO.socket(url, options);
            // Listening for custom MSG events
            Socket.on ("msg", "objects-> System.out.println" ("client: received msg->" + Arrays.toString (objects)));
            // Listening for custom subscription events
            Socket.on ("sub", objects - > System.out.println ("client:"+ "successful subscription, feedback - >" + Arrays.toString (objects)));
            socket.on(Socket.EVENT_CONNECT, objects -> {
                Socket. emit ("sub", "I'm a subscriber");
                System. out. println ("client:"+ "successful connection");
            });
            Socket.on (Socket.EVENT_CONNECTING, objects-> System.out.println ("client:"+"in connection");
            Socket.on (Socket.EVENT_CONNECT_TIMEOUT, objects-> System.out.println ("client:"+"connection timeout");
            Socket.on (Socket.EVENT_CONNECT_ERROR, objects-> System.out.println ("client:"+"connection failure");
            socket.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}