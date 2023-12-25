/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitap_12_chatroom_server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ChatServer {

    private final int port = 50000;
    private List<ClientHandler> clients = new ArrayList<>();

    public void startServer() {
        try {
            // webSocket
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started! Listening on port: " + port);

            // clients connect:
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New Client connected: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, System.currentTimeMillis() + "", this);
                clients.add(clientHandler);
                new Thread(clientHandler).start();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String id, String message) {
        for (ClientHandler client : clients) {
            if (!(client.getId().equals(id))) {
                client.sendMessage(id + ": " +  message + " " +  java.time.LocalTime.now().toString() +  " " +java.time.LocalDate.now().toString());
            }
        }
    }

}
