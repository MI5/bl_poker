package OldClasses;
import java.net.*;
import java.io.*;

/**
 * Klasse Client. Ermoeglicht Kommunikation mit dem Poker-Partner.
 * 
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
    private String sendString;

    // Array fuer Zerlegung in einzelne Zeichen
    private byte[] b = new byte[100];

    /**
     * Konstuktor. Erzeugt einen Client mit uebergebener Zeichenkette.
     * 
     * @param msg
     *            zu uebertragene Zeichenkette.
     */
    public Client(String sendString) {
        this.sendString = sendString;
    }

    /**
     * Sendet die eigenen Karten an den Poker-Partner.
     */
    public String send() {
        // wandel den String in Zeichen um
        b = sendString.getBytes();
        try {
            // SocketVerbindung herstellen
            server = new Socket();
            // Und zwar an der Addresse
            SocketAddress addr = new InetSocketAddress("becks.dnsalias.com",
                    4712);
            // und nun verbinde
            server.connect(addr, 2000);
            // hole Outputstream vom Server
            out = server.getOutputStream();
            // Schreibe die Karten hinein
            out.write(b);
            // schließe den Outputstream
            out.flush();
            // InputStream erzeugen
            in = server.getInputStream();
            // ausgetauschte Informationen auslesen
            in.read(b);
            // Byte-Array in String umwandeln
            sendString = new String(b);

        } catch (Exception e1) {
            // Melde bei Fehlern
            sendString = "error";
        }

        return sendString;
    }

}