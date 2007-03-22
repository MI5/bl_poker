import java.net.*;
import java.io.*;

/**
 * Klasse Client.
 * Ermoeglicht Kommunikation mit dem Poker-Partner.
 * @author Christian
 *
 */
public class Client {
    
    // Variable fuer Socket
    private Socket server;
    // InputStream fuer Verbindung
    private InputStream in;
    // OutputStream fuer Verbindung
    private OutputStream out;
    // zu uebertragene Information (Karten auf der Hand)
    private String msg;
    // Array fuer Zerlegung in einzelne Zeichen
    private byte[] b = new byte[100];

    /**
     * Konstuktor.
     * Erzeugt einen Client mit uebergebener Zeichenkette.
     * @param msg zu uebertragene Zeichenkette.
     */
    public Client(String msg) {
        this.msg = msg;
    }
    
    
    
    /**
     * Baut Verbindung mit dem Server auf und probiert dies solange bis die
     * Verbindung hergestellt wurde.
     */
    public void connect() {
        // Preufvariable ob Verbindung hergestellt
        boolean connected = false;
        System.out.println("Versuche Daten auszutauschen");
        while (!connected) {
            connected = true;
            try {
                // Socket an Port 4712
                server = new Socket("becks.dnsalias.com", 4712);
                System.out.println("Verbunden mit " + server.getInetAddress());

                // InputStream erzeugen
                in = server.getInputStream();
            } catch (IOException e) {
                // Gab es eine Exception, konnte Verbindung nicht hergestellt
                // werden.
                // Versuche es also erneut.
                if (e instanceof ConnectException) {
                    connected = false;
                }
            }
        }

    }

    /**
     * Sendet die eigenen Karten an den Poker-Partner.
     */
    public String send() {
        // Connecte mit dem Poker-Server
        connect();

        b = msg.getBytes();
        try {

            // hole Outputstream vom Server
            out = server.getOutputStream();
            // Schreibe die Karten hinein
            out.write(b);
            // schließe den Outputstream
            out.flush();
            in.read(b);
            
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // Byte-Array in String umwandeln
        msg = new String(b);
        // Nachricht des Server ausgeben
        System.out.println("Server : " + msg);
        return msg;
    }
    
    

    
    
}