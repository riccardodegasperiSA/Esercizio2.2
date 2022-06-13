package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CL {

    static int portNumer = 1234;
    static String ip = "127.0.0.1";
    static Socket clientSocket = null;
    static Socket socket;
    static OutputStream out;
    static InputStream in;
    static BufferedReader br;

    static {

    }

    public static void main(String[] args) {

        String data = new String();
        socket = openToServer();
        while (true) {

            try {
                data = br.readLine();
                System.out.println(data);
            } catch (IOException e) {
                e.printStackTrace();
            }

//            System.out.println(clientSocket.getLocalAddress());
//
//            ClientHandler clientHandler = new ClientHandler(clientSocket);
//
//            new Thread(clientHandler).start();


        }

    }

    static private Socket openToServer() {
        try {
            socket = new Socket(ip, portNumer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        br = new BufferedReader(
                new InputStreamReader(in)
        );
        return socket;
    }


}