/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitap_12_chatroom_server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author ASUS
 */
public class ClientHandler implements Runnable {

    private Socket mySocket;
    private String id;
    private InputStream input;
    private OutputStream output;
    private ChatServer server;

    public ClientHandler(Socket mySocket, String id, ChatServer server) {
        this.mySocket = mySocket;
        this.id = id;
        try {
            this.input = mySocket.getInputStream();
            this.output = mySocket.getOutputStream();
            this.server = server;
        } catch (Exception e) {
        }

    }

    @Override
    public void run() {
        // có text ở trong input thì xuất ra console.
        // có người nhập dữ liệu từ bàn phím thì ghi vào output.

        try {
            byte[] buffer = new byte[1024]; // số lượng ký tự giới hạn
            int readableByte;
            while ((readableByte = input.read(buffer)) != -1) { // khong co du lieu doc thi ra -1, con thi ra khac -1
                String message = new String(buffer, 0, readableByte); // đọc từ buffer, bắt đầu từ phần tử đầu tiên của buffer, lượng byte thực sự đã đọc.
                server.broadcastMessage(this.id,  message); // gửi hết cho tất cả mọi người
            }
        } catch (Exception e) {
        }

    }

    public void sendMessage(String message) {
        try {
            output.write(message.getBytes()); // viet ra du lieu tu bien message
        } catch (Exception e) {
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

}
