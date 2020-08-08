/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Thắng Bùi
 */
public class TCPServer {

    public static void main(String[] args) {
        try {
            ServerSocket myServer = new ServerSocket(2811);
            System.out.println("Server OK");

            while (true) {
                Socket socket = myServer.accept();
                System.out.println("Client connected!");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                
                Scanner scan = new Scanner(System.in);
                String message = "";
                
                while(true) {
                    message = dis.readUTF();
                    System.out.println("From Client: "+message);
                    if (message.equalsIgnoreCase("bye")) {
                        System.out.println("Job done!");
                        break;
                    }
                    
                    message = "";
                    System.out.print("Type a message: ");
                    message = scan.nextLine();
                    dos.writeUTF(message);
                }
                socket.close();
            }
        } catch (IOException e) {
            //System.out.println(e);
        }
    }

}
