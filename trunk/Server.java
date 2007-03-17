import java.net.*;
import java.io.*;

/**
 * Klasse Server.
 * Erlaubt Kommunikation mit dem Poker-Partner.
 * @author Christian
 *
 */
public class Server {
    
    private static String sendString;
    
    public Server(String sendString) {
        this.sendString = sendString;
    }

    public static void main(String[] args) {

        try {
            sendString = "Pik 9";

            // Socket an Port 4712 erstellen
            ServerSocket server = new ServerSocket(4712);
            // Warte, bis Verbindung hergestellt ist.
            Socket client = server.accept();
            
            OutputStream out = client.getOutputStream();
            
            byte b[] = sendString.getBytes();
            
            out.write(b);
            byte[] temp = new byte[100];
            InputStream in = client.getInputStream();
            in.read(b);
            System.out.println("Client antwortet : " + new String(b));

            
            
        } catch (IOException e) {
            System.err.println("Fehler\n" + e);
            System.exit(1);
        }
    }

}
