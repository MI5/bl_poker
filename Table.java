import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;

/**
 * Klasse Table. Repraesentiert den Pokertisch mit Spielern und Karten.
 * Hauptklasse. Ohne die laueft nichts. :)
 * 
 * @author Christian
 * 
 */
public class Table {

    private CardSet cards = new CardSet();

    private JFrame frame;

    TablePainter painter = new TablePainter();

    private LinkedList<Card> playerCards = new LinkedList<Card>();

    CardChecker check;
    
    PokerClient c;

    /**
     * Konstruktor. Erstellt ein JFrame.
     */
    public Table() {
        // Initialisiere den CardChecker
        check = new CardChecker();
        // erstellt neue JFrameInstanz mit Titel "PokerLauncher"
        frame = new JFrame("Poker Launcher V0.2");
        // ermoeglicht das Beenden ueber Klicken auf das Kreuz rechts oben
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setzt die Groesse des JFrame
        frame.setSize(202, 200);
        // passt die Koordinaten an, damit das Frame nicht ueber dem
        // Pokerfenster liegt.
        frame.setLocation(818, 0);
        // jetzt kann man es auch sehen. :)
        frame.getContentPane().add(getButtons(), BorderLayout.NORTH);
        // fuege die Zeichenklasse hinzu
        frame.getContentPane().add(painter, BorderLayout.CENTER);
        frame.setVisible(true);
        // VERÄNDERUNG Table startet Empfangsthread
        c = new PokerClient(this);
        Thread t1 = new Thread(c);
        t1.start();
    }

    /**
     * Erstellt ein JPanel mit 2 Buttons fuer Karten senden und Beenden.
     * 
     * @return JPanel mit den Buttons.
     */
    public JPanel getButtons() {
        // erstelle die Buttons Laden, Suchen und Beenden
        JButton send = new JButton("Send");
        JButton refresh = new JButton("Refresh");
        // erstelle neues JPanel fuer die Buttons
        JPanel buttons = new JPanel();

        // fuege die 2 Button hinzu
        buttons.add(send);
        buttons.add(refresh);
        // fuege fuer jeden Button einen Listener hinzu
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                
            }
        });
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        return buttons;
    }

    /**
     * Aktualisiert das JPanel.
     */
    public synchronized void refresh() {
        // Refreshe den Screenshot
        check = new CardChecker();
        // entferne alle alten Elemente
        painter.removeAll();
        painter.setOwnCards(check.getOwnCards());
        painter.setTableCards(check.getTableCards());
        painter.setPlayerCards(playerCards);
        // und zeichne neu
        painter.repaint();
    }

    /**
     * Aktualisiert die Informationen.
     * 
     * @param info
     *            neue Infotmationen
     */
    public synchronized void refreshInfo(String info) {
        painter.setInfo(info);
        painter.removeAll();
        painter.repaint();
        refresh();
    }

    /**
     * Diese Methode erstellt den zu sendenden Informations-String zusammen
     * und leitet diesen an den PokerClient weiter, der die Nachricht
     * letztendlich abschickt. 
     */
    public void sendNewClient() {
        String msg;
        // Pruefe ob man Karten besitzt
        if (check.getOwnCards().size() == 0) {
            msg = "nocard";
        } else {
            // wenn ja erstelle Uebertragungsstring
            msg = check.getOwnCards().getFirst().toString()
                    + check.getOwnCards().getLast().toString();
        }
        // Gebe Informationen aus und versende
        refreshInfo("Uebertrage Daten");
        c.send(msg);
        refreshInfo("Uebertragung erfolgreich");
    }
    
    /**
     * Diese Methode wird von PokerClient aufgerufen und teilt die Empfangenen
     * Daten mit. D.h. es erfolgt eine automatische Aktualisierung.
     * @param info Neue Karten
     */
    public void newInfo(String msg) {
        // Bevor die wirklichen Karten veraendert werde, pruefe ob man sie
        // auch veraendern muss!
        // initialisiere also eine temporaere Liste
        LinkedList<Card> playerCardsTemp = new LinkedList<Card>();
        playerCardsTemp.add(new Card(msg.substring(0, 1), new Integer(msg
                .substring(1, 3)).intValue()));
        playerCardsTemp.add(new Card(msg.substring(3, 4), new Integer(msg
                .substring(4, 6)).intValue()));
        // und pruefe nun, ob die eigenen Karten mit den emfangenen Karten
        // uebereinstimmen. Leider erhaelt man ja auch seine eigenen Karten
        // also Informationsstring
        if (!playerCardsTemp.toString().equals(check.getOwnCards().toString())) {
            // Sie stimmen nicht ueberein, d.h. die richtigen Karten von
            // dem Mitspieler sind angekommen
            playerCards = playerCardsTemp;
            refresh();
        }
    }
}
