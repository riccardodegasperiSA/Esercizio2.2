package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ClientHandler extends Thread{

    private ServerSocket serverSocket;

    private Socket clientSocket = null;

    private BufferedReader in = null;

    private PrintWriter out = null;

//    private String userName;

    public PrintWriter getOut() {
        return out;
    }

//    public BufferedReader getIn() {
//        return in;
//    }

//    public String getUserName() {
//        return userName;
//    }

    public ClientHandler(ServerSocket serverSocket, Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.serverSocket = serverSocket;

        creaOut();

//        if (App.connected.size() > App.size) {
//            out.println("Server full");
//            App.connected.remove(this);
//            return;
//        }

        creaIn();

//        out.println("Inserisci user name");
//
//        try {
//            userName = in.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(userName + " logged in");
//        out.println("Benvenuto " + userName);
//        broadCast(App.connected, userName + " logged in");
    }

    public void run(){

//        creaIn();

//        creaOut();

            ascolta();

//            System.out.println(userName + " disconnected");
//            broadCast(App.connected, userName + " disconnected");
//        App.connected.remove(this);
    }

    private void ascolta() {

        String s;

//        out.println("Inserisci user name");
//
//        try {
//            userName = in.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(userName + " logged in");

//        out.println("Benvenuto " + userName);

        try {

            while ((s = in.readLine()) != null) {
                System.out.println(s);

            }
        } catch (IOException e) {
            System.err.println("Connection failed " + e);
        }
        finally {
            App.connected.remove(this);
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void creaOut() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void creaIn() {
        try {
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("accept failed "+ e);
        }
    }

//    private void process(String s) {
//
//        String [] processString;
//
////        String result;
//
//        if (!s.startsWith("@")){
//            broadCast(App.connected,s);
//        } else {
//            processString = s.split(" ", 2);
//            message(processString[0].split("@")[1], processString[1]);
//        }
//
////        processString = s.split(" ");
////
////        double x, y;
////
////        try {
////            x = Double.parseDouble(processString[0]);
////            y = Double.parseDouble(processString[2]);
////        } catch (NumberFormatException e) {
////            return s;
////        } catch (ArrayIndexOutOfBoundsException e) {
////            return s;
////        }
////
////
////        switch (processString[1]) {
////            case "+": {
////                result = String.valueOf((x+y));
////                break;
////            }
////            case "-": {
////                result = String.valueOf((x-y));
////                break;
////            }
////            case "*": {
////                result = String.valueOf((x*y));
////                break;
////            }
////            case "/": {
////                if (y != 0)
////                    result = String.valueOf((x/y));
////                else
////                    result = "Can't divide by 0";
////                break;
////            }
////            default: {
////                return s;
////            }
////        }
////        return "risposta: " + result;
////        return result;
//    }
//
    public void broadCast(LocalDateTime time,  int cl){
        this.getOut().println("CP " + time + ": POLL CL" + cl);
    }
//
//    private void message(String target, String message){
//        message = userName + ": " + message;
//        for (ClientHandler current : App.connected){
//            if (current.userName.equals(target)){
//                current.getOut().println(message);
//                return;
//            }
//        }
//    }

}
