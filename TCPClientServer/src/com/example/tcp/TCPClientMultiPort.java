package com.example.tcp;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.List;

public class TCPClientMultiPort {
    public static void main(String[] args) {
        List<Integer> ports = Arrays.asList(6789, 6790, 6791); // Lista de portas para tentar se conectar
        String sentence;
        String modifiedSentence;
        boolean connected = false;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        for (int port : ports) {
            try {
                Socket clientSocket = new Socket("localhost", port);
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                System.out.print("Enter a sentence: ");
                sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + '\n');
                modifiedSentence = inFromServer.readLine();

                System.out.println("FROM SERVER: " + modifiedSentence);
                clientSocket.close();
                connected = true;
                break; // Se a conexão for bem-sucedida, sai do loop
            } catch (IOException e) {
                System.out.println("Não é possível conectar à porta: " + port);
            }
        }

        if (!connected) {
            System.out.println("Falha ao conectar ao servidor.");
        }
    }
}

