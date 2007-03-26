/**
 * PokerServer.java
 *
 * Oberflaeche mit Swing
 *
 */


import java.net.*;
import java.io.*;

public class PokerServer {

    // Port-Nummer
    public static final int port = 4712;

    // Konstruktor
    public PokerServer() {


        // starte Server
        System.out.println("Server gestartet\n");
        startServer();
    }

    // start Server
    private void startServer() {

        try {

            // ServerSocket erstellen
            ServerSocket server = new ServerSocket(port);

            // Endlosschleife
            while (true) {

                // warten auf Client
                Socket client = server.accept();

                // InputStream erzeugen
                DataInputStream in = new DataInputStream(client
                        .getInputStream());

                // Name aus Stream auslesen
                String name = in.readUTF();

                // Name ausgeben
                System.out.println("neuer Client : " + name + " von "
                        + client.getInetAddress() + "\n");

                // ChatManager erzeugen und mit Parametern versorgen
                PokerManager cm = new PokerManager(name, client);

            }
        } catch (IOException e) {
            // Fehler im Anzeigetext ausgeben
            System.out.println("\nFehler\n" + e + "\n");
        }
    }

    
    public static void main(String[] args) {
        PokerServer pok = new PokerServer();
    }
    
}
