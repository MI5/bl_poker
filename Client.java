/*
 * Time-Client
 */

import java.net.*;
import java.io.*;

public class Client {
    private static Socket server;

    private static InputStream in;

    private static OutputStream out;

    private static String msg;

    private static byte[] b = new byte[100];

    /**
     * Baut Verbindung mit dem Server auf und probiert dies solange bis die
     * Verbindung hergestellt wurde.
     */
    public static void connect() {
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

    public static void main(String[] args) throws Exception {

        connect();

        String abfrage;

        out = server.getOutputStream();
        b = "Pik 2".getBytes();
        out.write(b);
        out.flush();

        try {
            int num = in.read(b);
        } catch (IOException e) {
            System.err.println("Fehler\n" + e);
            System.exit(1);
        }

        // Byte-Array in String umwandeln
        msg = new String(b);

        // Nachricht des Server ausgeben
        System.out.println("Server : " + msg);

    }
}