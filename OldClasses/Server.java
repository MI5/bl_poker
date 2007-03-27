package OldClasses;
import java.net.*;
import java.io.*;

/**
 * Klasse Server.
 * Erlaubt Kommunikation mit dem Poker-Partner.
 * @author Christian
 *
 */
public class Server {
    
    // der zu sendene String mit Karteninformationen
    private String sendString;

    /**
     * Konstruktor. 
     * @param sendString zu sendende Karten
     */
    public Server(String sendString) {
        this.sendString = sendString;
    }

    /**
     * Sendet die eigenen Karten zum Poker-Partner.
     */
    public String send() {

        try {
            // Socket an Port 4712 erstellen
            ServerSocket serverSock = new ServerSocket(4712);
            // Warte höchstens 2 Sekunden auf Verbindung
            serverSock.setSoTimeout(2000);
            // Jetzt sitze diese Zeit ab.
            Socket client = serverSock.accept();
            // hole Outputstream
            OutputStream out = client.getOutputStream();
            // schreibe Karten in ein ByteArray
            byte b[] = sendString.getBytes();
            // schreibe Daten auf den Outputstream
            out.write(b);
            out.flush();
            // hole den Inputstream und lese ihn aus
            InputStream in = client.getInputStream();
            in.read(b);
            return new String(b);
        // konnten die Karten nicht uebertragen werden, gebe Fehler aus
        } catch (Exception e) {
            sendString = "error";
        }
        return sendString;
    }
}
