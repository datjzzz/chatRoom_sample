/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitap_12_chatroom_client;

import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author ASUS
 */
public class ClientListener implements Runnable {

    private Socket socket;
    private InputStream input;

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[1024];
            int readableByte;
            while ((readableByte = input.read(buffer)) != -1) {
                String message = new String(buffer, 0, readableByte);
                System.out.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ClientListener(Socket socket) {
        this.socket = socket;
        this.input = input;

        try {
            this.input = socket.getInputStream();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
