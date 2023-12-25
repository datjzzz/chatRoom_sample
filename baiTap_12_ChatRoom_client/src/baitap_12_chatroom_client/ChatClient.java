/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitap_12_chatroom_client;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class ChatClient {

    private static final String URL = "localhost"; // Bbiến constant nên là đặt tên in hoa.
    private static final int PORT = 50000;

    public void startClient() {
        try {
            // đọc dữ liệu xuất ra cửa sổ console
            Socket socket = new Socket(URL, PORT);
            System.out.println("Connected to Server!");

            // liên tục đọc dữ liệu từ Server
            ClientListener client = new ClientListener(socket);
            new Thread(client).start();

            // liên tục đọc dữ liệu từ Scanner
            OutputStream output = socket.getOutputStream();
            Scanner sc = new Scanner(System.in); // đọc dữ liệu từ console.
            while (true) {
                String message = sc.nextLine();
                output.write(message.getBytes()); // 
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
