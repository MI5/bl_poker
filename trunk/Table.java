import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;



 /**
 * Klasse Table.
 * Repraesentiert den Pokertisch mit Spielern und Karten.
 * Hauptklasse. Ohne die laueft nichts. :)
 * @author Christian
 *
 */
public class Table {
    
    private CardSet cards = new CardSet();
    private JFrame frame;
    TablePainter painter = new TablePainter(); 
    private LinkedList<Card> playerCards = new LinkedList<Card>();

    CardChecker check;
    
    /**
     * Konstruktor.
     * Erstellt ein JFrame.
     */
    public Table() {
        
        check = new CardChecker();
        
        
//      erstellt neue JFrameInstanz mit Titel "PokerLauncher"
        frame = new JFrame("Poker Launcher V0.1");
        // ermoeglicht das Beenden ueber Klicken auf das Kreuz rechts oben
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setzt die Groesse des JFrame
        frame.setSize(202, 200);
        // passt die Koordinaten an, damit das Frame nicht ueber dem Pokerfenster liegt.
        frame.setLocation(818, 0);
        // jetzt kann man es auch sehen. :)
        frame.getContentPane().add(getButtons(), BorderLayout.NORTH);
        
        

        
        frame.getContentPane().add(painter, BorderLayout.CENTER);
       

        
        frame.setVisible(true);
    }
    
    
    /**
     * Erstellt ein JPanel mit 2 Buttons fuer Karten senden und Beenden.
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
                // SERVER:
//                Server server;
//                if (check.getOwnCards().size() == 0) { 
//                    server = new Server("nocard");
//                }
//                else {
//                    server = new Server(check.getOwnCards().getFirst().toString()+check.getOwnCards().getLast().toString());
//                }
//                String msg = server.send();
                
//                // CLIENT
//                Client client;
//                if (check.getOwnCards().size() == 0) { 
//                    client = new Client("nocard");
//                }
//                else {
//                    client = new Client(check.getOwnCards().getFirst().toString()+check.getOwnCards().getLast().toString());
//                }
//                String msg = client.send();
//                
//                
//                playerCards = new LinkedList<Card>();
//                playerCards.add(new Card(msg.substring(0, 1),new Integer(msg.substring(1,3)).intValue()));
//                playerCards.add(new Card(msg.substring(3, 4),new Integer(msg.substring(4,6)).intValue()));
//                
//                check = new CardChecker();
//                painter.removeAll();
//                painter.setOwnCards(check.getOwnCards());
//                painter.setTableCards(check.getTableCards());
//                painter.setPlayerCards(playerCards);
//                painter.repaint();
//                System.out.println(playerCards.toString());
                
                

            }
        });
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Refreshe den Screenshot
//                check = new CardChecker();
//                painter.removeAll();
//                painter.setOwnCards(check.getOwnCards());
//                painter.setTableCards(check.getTableCards());
//                painter.setPlayerCards(playerCards);
//                painter.repaint();

                
                

            }
        });
        return buttons;
    }

    public void refresh() {
        // Refreshe den Screenshot
        check = new CardChecker();
        painter.removeAll();
        painter.setOwnCards(check.getOwnCards());
        painter.setTableCards(check.getTableCards());
        painter.setPlayerCards(playerCards);
        painter.repaint();

        
    }

    public void sendClient() {
        // CLIENT
        Client client;
        if (check.getOwnCards().size() == 0) { 
            client = new Client("nocard");
        }
        else {
            client = new Client(check.getOwnCards().getFirst().toString()+check.getOwnCards().getLast().toString());
        }
        painter.removeAll();
        painter.repaint();
        painter.setInfo("Uebertrage Daten");
        String msg = client.send();
        
        
        
        playerCards = new LinkedList<Card>();
        playerCards.add(new Card(msg.substring(0, 1),new Integer(msg.substring(1,3)).intValue()));
        playerCards.add(new Card(msg.substring(3, 4),new Integer(msg.substring(4,6)).intValue()));
        
        check = new CardChecker();
        painter.removeAll();
        painter.setOwnCards(check.getOwnCards());
        painter.setTableCards(check.getTableCards());
        painter.setPlayerCards(playerCards);
        painter.repaint();
        System.out.println(playerCards.toString());
    }

    public void sendServer() {
        // SERVER:
      Server server;
      if (check.getOwnCards().size() == 0) { 
          server = new Server("nocard");
      }
      else {
          server = new Server(check.getOwnCards().getFirst().toString()+check.getOwnCards().getLast().toString());
      }
      String msg = server.send();
      
      // CLIENT
//      Client client;
//      if (check.getOwnCards().size() == 0) { 
//          client = new Client("nocard");
//      }
//      else {
//          client = new Client(check.getOwnCards().getFirst().toString()+check.getOwnCards().getLast().toString());
//      }
//      String msg = client.send();
      
      
      playerCards = new LinkedList<Card>();
      playerCards.add(new Card(msg.substring(0, 1),new Integer(msg.substring(1,3)).intValue()));
      playerCards.add(new Card(msg.substring(3, 4),new Integer(msg.substring(4,6)).intValue()));
      
      check = new CardChecker();
      painter.removeAll();
      painter.setOwnCards(check.getOwnCards());
      painter.setTableCards(check.getTableCards());
      painter.setPlayerCards(playerCards);
      painter.repaint();
      System.out.println(playerCards.toString());
      
      

    }
    
    
    
//    public static void main(String[] args) {
//        Table table = new Table();
        //table.check = new CardChecker();
//        System.out.println("Eigene Karten");
//        System.out.println(table.check.getOwnCards().toString());
//        System.out.println("Tischkarten");
//        System.out.println(table.check.getTableCards().toString());
        //System.out.println(check.toString());
//    }
    

}
