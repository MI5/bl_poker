import java.net.*;
import java.io.*;

/**
 * Klasse Server.
 * Erlaubt Kommunikation mit dem Poker-Partner.
 * @author Christian
 *
 */
public class Server {
    
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
            // Warte, bis Verbindung hergestellt ist.
            Socket client = serverSock.accept();
            // hole Outputstream
            OutputStream out = client.getOutputStream();
            // schreibe Karten in ein ByteArray
            byte b[] = sendString.getBytes();
            // schreibe Daten auf den Outputstream
            out.write(b);
            out.flush();
            // hole den Inputstream
            InputStream in = client.getInputStream();
            in.read(b);
            System.out.println("Client antwortet : " + new String(b));
            return new String(b);

            
            
        } catch (IOException e) {
            System.err.println("Fehler\n" + e);
            System.exit(1);
        }
        return sendString;
    }
    
    
//    public static void main(String[] args) {
//        //Testmethode
//        Server s = new Server("s04h10");
//        s.send();
//    }
    

}
