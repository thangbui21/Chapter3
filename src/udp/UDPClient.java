/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Thắng Bùi
 */
public class UDPClient {

    DatagramSocket socket;
    DatagramPacket packet;

    //2: Mở một socket để kết nối.
    public void openConnect() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println(e);
        }
    }

    //3: Đóng gói thông tin vào DatagramPacket để gửi đi
    public void sendData(String str) {
        try {
            byte[] data = new byte[1024];
            data = str.getBytes();
            InetAddress ipServer = InetAddress.getByName("localhost");
            packet = new DatagramPacket(data, data.length, ipServer, 3000);
            socket.send(packet);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    //7: Nhận dữ liệu Server trả về. Phương thức này ở Client và Server là giống nhau.
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
        
    public static void main(String[] args) {
        UDPClient client = new UDPClient();
        client.openConnect();
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhập vào một số nguyên dương bất kỳ:");
        int x = scan.nextInt();
        
        client.sendData(String.valueOf(x));
        System.out.println(x + "! = "+client.receiveData());
        
    }
    
}
