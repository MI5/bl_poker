/*
 * ChatManager.java
 *
 * Hier werden alle Verbindungen in einen Vektor
 * gespeichert und alle Meldungen entsprechnend
 * an die Chat-Teilnehmer im Vector versnad.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.net.*; 
import java.io.*; 
import java.util.*;

public class PokerManager extends Thread { 
    
    // Objekte
    private Socket socket; 
    private DataInputStream in; 
    private DataOutputStream out;
    private String name;
    private static Vector manager = new Vector ();
    public  boolean please_stop = false;
    
    public PokerManager (String name, Socket socket) {


    this.name    = name;
    this.socket  = socket;

    try {

        // Streams erzeugen
        in  = new DataInputStream (new BufferedInputStream (socket.getInputStream()));
        out = new DataOutputStream (new BufferedOutputStream (socket.getOutputStream()));
    
        // Thread starten
        start();
    }
    catch (IOException e) {
        // Fehler im Anzeigetext ausgeben
        System.out.println("\nFehler\n" + e + "\n");
    }
    } 
    
    // run
    public void run () { 

    try { 

        // Meldungen an alle ausgeben
//        sendMessage( name + "\t ist dazugekommen!");

        // dem Vector hinzufuegen
        manager.addElement (this); 

        // Endlosschleife bis stop gewuenscht
        while (!please_stop) { 

        // Nachricht einlesen
        String message = in.readUTF (); 

        // an alle schicken
//        sendMessage(name + ":\t " + message);
        sendMessage(message);
        } 

    } catch (IOException ex) { 
        // Fehler anzeigen
        System.out.println("Verbindung zu " + name + " verloren!\n");
    } finally { 
    
        // beim Vector wieder loeschen
        manager.removeElement (this); 

        // Meldung ausgeben
        //sendMessage(name + "\t hat uns verlassen!");

        // Verbindung beenden
        try { 
        socket.close (); 
        } catch (IOException ex) { 
        // Fehler ausgeben
        System.out.println("Socket zu User " + name + " schon geschlossen!\n");
        }  
    }
    }
    
    // Meldungen ausgeben
    // Diese Methode haben alle Instanten von ChatManager gemeinsam, 
    // daher static und synchronized
    private static void sendMessage (String message) { 

    synchronized (manager) { 

        // Elemente des Vectors ermitteln
        Enumeration e = manager.elements (); 

        // fuer jeden Eintrag
        while (e.hasMoreElements ()) { 

        // ChatManager ermitteln
        PokerManager man = (PokerManager)e.nextElement(); 

        try { 
            // Message senden (sofort)
            man.out.writeUTF (message);
            man.out.flush (); 
        } catch (IOException ex) { 
            // Thread stoppen, da Fehler
            man.please_stop = true; 
        } 
        }
    }
    } 
}

