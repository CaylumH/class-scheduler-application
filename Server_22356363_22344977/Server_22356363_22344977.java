package Server_22356363_22344977;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Client_22356363_22344977.Booking;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * @author CJOE TEAM©
 */
public class Server_22356363_22344977 {


    private static ServerSocket servSock;
    private static final int PORT = 1234;
    private static int clientConnections = 0;
    private static Socket link;
    private static PrintWriter out = null;

    public static void main(String[] args) {
        System.out.println("Opening port...\n");
        try {
            servSock = new ServerSocket(PORT);  //Step 1.
            while (true) {
                link = servSock.accept();
                System.out.println("CONNECTED : " + link.getLocalSocketAddress().toString());
                Thread thread = new Thread(new Multithreading(link));
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }
    }

    public static String request(String code, String option, String day, String time, String room) {
        try {
            switch (option) {
                case ("ADD"):
                    return BookingController.addClass(code, day, time, room);     //Returns the return value of addClass which can be used in response method.

                case ("REMOVE"):
                    return BookingController.removeClass(code, day, time, room);

                case ("DISPLAY"):
                    return BookingController.displaySchedule(code);

                case ("EARLY"):
                    ArrayList<Booking> schedule = BookingController.returnSchedule();
                    return EarlyLectures_ForkJoin.earlyLecture(schedule, code, day, time, room);      //Shifts lecture time to early lecture.
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    static class Multithreading implements Runnable {

        private final Socket link;
        private BufferedReader in;
        private PrintWriter out;
        private boolean running = true;


        public Multithreading(Socket link) {
            this.link = link;
        }   //Ref: AR, ThreadsExamples, CS4076

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(link.getInputStream()));
                out = new PrintWriter(link.getOutputStream(), true);
                while (running) {
                    handleClientMessage();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    System.out.println("\n* Closing connection... *");
                    link.close();
                } catch (IOException e) {
                    System.out.println("Unable to disconnect!");
                    System.exit(1);
                }
            }
        }

        private void handleClientMessage() {
            try {
                String message = in.readLine();
                try {// System.out.println("Sleeping");
                   // Thread.sleep(4000); // Simulate network operation, client server is on the same computer so there is no actual network delay.
                }catch(Exception e){
                    e.printStackTrace();
                }
                if (message.equals("STOP")) {
                    System.out.println("Stopping Server as request by Client");
                    running = false;
                    link.close();
                    return;
                }
                if (message == null) {
                    System.out.println("Failure : 'message' is Null");
                    out.println("Failure : 'message' is Null");
                    return;
                }
                String[] messageArray = message.split(",");

                if (messageArray.length != 5) {
                    System.out.println("Failure : Insufficient Parameters");
                    out.println("Failure : Insufficient Parameters");
                    return;
                }

                for (int i = 0; i < messageArray.length; i++) {
                    if (messageArray[i].equals("EMPTY")) {
                        System.out.println("Failure : Insufficient Parameters");
                        out.println("Failure : Insufficient Parameters");
                        return;
                    }
                }

                String code = messageArray[0];
                String option = messageArray[1];
                String day = messageArray[2];
                String time = messageArray[3];
                String room = messageArray[4];

                System.out.println("Message received from client: " + message);
                String result = Server_22356363_22344977.request(code, option, day, time, room);
                System.out.println(result);
                out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}