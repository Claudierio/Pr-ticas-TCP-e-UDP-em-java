package com.example.tcp;

import java.io.*;
import java.net.*;

public class TCPServerMultiPort {
    public static void main(String[] args) throws Exception {
        try (ServerSocket welcomeSocket = new ServerSocket(6789)) {
			while (true) {
			    Socket connectionSocket = welcomeSocket.accept();
			    // Inicie um novo thread para cada conexão
			    new Thread(new ClientHandler(connectionSocket)).start();
			}
		}
    }
}

class ClientHandler implements Runnable {
    private Socket connectionSocket;

    public ClientHandler(Socket socket) {
        this.connectionSocket = socket;
    }

    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            String clientSentence;
            String capitalizedSentence;

            clientSentence = inFromClient.readLine();
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


