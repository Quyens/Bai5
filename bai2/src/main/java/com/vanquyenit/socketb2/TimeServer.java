package com.vanquyenit.socketb2;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());

                PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                // Nhận yêu cầu từ client
                String request = "time";

                if (request.equals("time")) {
                    // Gửi thời gian hiện tại cho client
                    LocalDateTime currentTime = LocalDateTime.now();
                    String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    out.println("Current time: " + formattedTime);
                } else {
                    out.println("Invalid request");
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}