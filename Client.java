/*
 * Time-Client
 */

import java.net.*;
import java.io.*;

public class Client
{
    private Socket server;
    private InputStream in;
    private OutputStream  out;
    private String msg;
    private byte [] b = new byte[100];


    public String leseTastatur() throws Exception
    {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String s = br.readLine();
       return s;
     }

    public void verbindeMitServer() throws Exception
    {
      try
      {
        // Socket an Port 4712
        server = new Socket("91.10.158.37",4712);

        System.out.println("Verbunden mit " + server.getInetAddress());
        
        // InputStream erzeugen
        in = server.getInputStream();        
      }
      catch (IOException e)
      {
        System.err.println("Fehler\n" + e);
        System.exit(1);
      }
    }

    public void leseVomServer() throws Exception
    {
        // max 100 Bytes lesen
        try
        {
          int num = in.read(b);
        }
        catch (IOException e)
        {
          System.err.println("Fehler\n" + e);
          System.exit(1);
        }

        // Byte-Array in String umwandeln
        msg = new String(b);

        // Nachricht des Server ausgeben
        System.out.println("Server : " + msg);
    }

        
    public String sendeAnServer() throws Exception
    {
        msg = leseTastatur();
        out = server.getOutputStream();
        out.write(b);
        out.flush();
        System.out.println("Client-Antwort : '" + msg + "'");
        return msg;
    }


    public static void main (String[] args) throws Exception
    {
        ChatClient v = new ChatClient();

        v.verbindeMitServer();

        String abfrage;
        do
        {
          v.leseVomServer();
          abfrage = v.sendeAnServer();
        } while (!abfrage.equals("exit\n"));
    }
}