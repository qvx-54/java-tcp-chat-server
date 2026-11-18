package dev.chatserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatServer {

    private final int port;

    public ChatServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Server started on port: " + port);

            while (true) {

                Socket client = socket.accept();
                System.out.println("Client connected: " + client.getInetAddress());

                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received: " + message);
                }
                client.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
