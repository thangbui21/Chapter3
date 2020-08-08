/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.*;
import java.net.*;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 *
 * @author Thắng Bùi
 */
public class TCPClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2811);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            Scanner scan = new Scanner(System.in);
            String message = "";
            
            //Handle
            while (true) {
                System.out.print("Type a message: ");
                message = scan.nextLine();
                dos.writeUTF(message);
                
                message = "";
                message = dis.readUTF();
                System.out.println("From Server: "+message);
           
                if (message.equalsIgnoreCase("bye")) {
                    System.out.println("Job done!");
                    break;
                }
            }
            socket.close();
        } catch (IOException e) {
            //System.out.println(e);
        }
    }
}
