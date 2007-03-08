/*
 * Chat-Server
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

    public static void main(String[] args) {

        try {

            // Socket an Port 4712
            ServerSocket server = new ServerSocket(4712);

            // Endlosschleife
            while (true) {

                System.out.println("Warten auf den Matze...");

                // warten auf Matze
                Socket client = server.accept();

                System.out.println("Mit Matze " + client.getInetAddress()
                        + " verbunden");
                
                while (true) {
                    // OutputStream erzeugen
                    OutputStream out = client.getOutputStream();

                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(System.in));
                    System.out.println("Was sage ich?");
                    String s = br.readLine();
                    byte b[] = s.getBytes();

                    // Nachricht ausgeben
                    out.write(b);
                    byte[] temp = b;
                    InputStream in = client.getInputStream();
                    while (temp.equals(b) == true) {
                        b = new byte[100];
                        
                        in.read(b);
                    }
                    System.out.println("Client antwortet : " + new String(b));
                    if (new String(b).startsWith("exit")) {
                        break;
                    }

                    
                }
            }
        } catch (IOException e) {
            System.err.println("Fehler\n" + e);
            System.exit(1);
        }
    }
    /**
     * InputStream in = client.getInputStream(); InputStreamReader read = new
     * InputStreamReader(in); BufferedReader bread = new BufferedReader(read);
     * String temp = bread.readLine(); System.out.println(temp); while (temp !=
     * null) { System.out.println("Botschaft: "+temp); temp = bread.readLine(); }
     */
}
