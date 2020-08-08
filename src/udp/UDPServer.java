/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.net.*;
import java.io.*;

/**
 *
 * @author Thắng Bùi
 */
public class UDPServer {

    DatagramSocket socket;
    DatagramPacket packet;

    //1: Mở cổng cho Client vào
    public void openConnect() {
        try {
            socket = new DatagramSocket(3000);
        } catch (SocketException e) {
            System.out.println(e);
        }
    }

    //4: Nhận dữ liệu từ Client gửi lên
    public String receiveData() {
        try {
            byte[] data = new byte[1024];
            packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String str = new String(packet.getData()).trim();
            return str;
        } catch (IOException e) {
            System.out.println(e);
        }
        return "";
    }

    public int factorial(int n) {
        int fact = 1;
        if (n == 0 || n == 1) {
            return fact;
        } else {
            for (int i = 2; i < n; i++) {
                fact *= i;
            }
            return fact;
        }
    }

    //5: Xử lý.
    public String handle(String str) {
        int n = Integer.parseInt(str);
        int fact = 1;
        if (n == 0 || n == 1) {
            return ""+fact;
        } else {
            for (int i = 2; i <= n; i++) {
                fact *= i;
            }
            return ""+fact;
        }
    }

    //6: Trả dữ liệu về.
    public void sendData(String str) {
        try {
            byte[] data = new byte[1024];
            data = str.getBytes();
            InetAddress ipClient = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(data, data.length, ipClient, port);
            socket.send(packet);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        UDPServer server = new UDPServer();
        server.openConnect();

        String str = server.receiveData();
        server.sendData(server.handle(str));
    }

}
