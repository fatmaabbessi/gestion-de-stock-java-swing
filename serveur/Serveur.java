package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String [] args) {
        try {
            ServerSocket serveur = new ServerSocket(9000); //le definie comme serveur
            System.out.println("serveur en ecoute");

           while (true) {
               Socket s = serveur.accept();
               BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
               String login = br.readLine();
               PrintWriter pw = new PrintWriter(s.getOutputStream());

               if (login.equals("yousef 012")) {
                   pw.println(1);
                   pw.flush();
               } else if (login.equals(" ")) {
                   pw.println(0);
                   pw.flush();
               } else
                   pw.println(-1);
               pw.flush();
           }
            } catch(IOException e){
                throw new RuntimeException(e);
            }

    }
}
