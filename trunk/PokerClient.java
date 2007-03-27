/**
 * ChatClient.java
 *
 */

import java.net.*;
import java.io.*;

public class PokerClient implements Runnable {

    // Port-Nummer
    public static final int port = 4712;

    // Objekte
    private Socket socket;

    private DataInputStream in;

    private DataOutputStream out;

    private String name = "Poker";

    private String server = "becks.dnsalias.com";

    Table table;

    // Konstruktor
    public PokerClient(Table table) {

        // Werte uebernehmen
        this.name = "Poker";
        this.server = "becks.dnsalias.com";
        this.table = table;

        // Client starten
        // startClient();

    }

    /**
     * Setzt die TableInstanz.
     * @param table
     */
    public void setTable(Table table) {
        this.table = table;
    }
    
    // starte client
    private void startClient() {
        if (table.getOnlineStatus()) {

            try {
                String msg = "";
                // Socket erzeugen
                socket = new Socket(server, port);

                // Streams erzeugen
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                // Name ausgeben
                out.writeUTF("Pokerspieler");

                // Endlosschleife
                while (true) {
                    // Warten auf Meldungen und diese ausgeben
                    msg = in.readUTF();
                    if (msg.length() > 1) {
                        System.out.println(msg);
                        table.newInfo(msg);
                    }
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                System.out.println("\nFehler\n" + e + "\n");
            }
        }
    }

    public void send(String message) {
        if (table.getOnlineStatus()) {
            try {
                out.writeUTF(message);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void run() {
        startClient();
        

    }
    
    
    public void closeSocket() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
