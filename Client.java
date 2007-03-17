import java.net.*;
import java.io.*;

/**
 * Klasse Client.
 * Ermoeglicht Kommunikation mit dem Poker-Partner.
 * @author Christian
 *
 */
public class Client {
    private Socket server;

    private InputStream in;

    private OutputStream out;

    private String msg;

    private byte[] b = new byte[100];

    
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
    public void send() {
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
    }
    
    
    public static void main(String[] args) {
        //Testmethode
        Client c = new Client("s02d14");
        c.send();
    }
    
    
}