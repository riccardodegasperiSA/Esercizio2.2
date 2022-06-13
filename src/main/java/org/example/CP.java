package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CP {

    private static Thread th;
    private static Thread cicle;
    private static ServerSocket serverSocket = null;

    private static Socket clientSocket = null;

    public static ArrayList<ClientHandler> connected = new ArrayList<>();

//    the number on the left is the max number of connected users
//    public static int size = 4 - 1;

//    private static BufferedReader in = null;
//
//    private static PrintWriter out = null;

    public static void main(String[] args) {

        String hostName = new String();
        int portNumber;

        if (args.length == 2) {
            hostName = args[0];
            portNumber = Integer.parseInt(args[1]);
        } else {
            hostName = "127.0.0.1";
            portNumber = 1234;
        }

        creaServerSocket(portNumber);

        System.out.println("Server Started Hostname: " + hostName + " " + Instant.now());

//        while (true){
            timedMessage();
            ciclo(portNumber, hostName);
//            for (ClientHandler ch : connected.subList(0,connected.size()-1)) {
//                ch.getOut().println(connected.get(connected.size()-1).getUserName() + " connected");
//            }
//        }
        while (true);
    }

    private static void ciclo(int portNumber, String hostName) {

        cicle = new Thread(
                ()-> {
                    while (true) {
                        connettiClient();

                        connected.add(new ClientHandler(serverSocket, clientSocket));

                        connected.get(connected.size() - 1).start();

//                    int count = connected.size();
                    }
                });
        cicle.start();
//        timedMessage();

    }

    private static void connettiClient() {
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("accept failed " + e);
        }
    }

    private static void creaServerSocket(int portNumber) {

        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.err.println("reader failed "+ e);
        }

    }

//    private static void broadCast(ClientHandler c, LocalDateTime time, int cl){
//        c.getOut().println("CP " + time + ": POLL CL" + cl);
//        System.out.println("CP " + time + ": POLL CL" + cl);
//    }

    private static void timedMessage(){
        th = new Thread(
                ()->{
                    while (true) {
                        for (ClientHandler c : connected) {
                            c.broadCast(LocalDateTime.now(), connected.indexOf(c));
                        }
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    });
        th.start();
    }

}