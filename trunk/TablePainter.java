import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

/**
 * Klasse TablePainter.
 * Zeichnet in dem Frame die Informationen.
 * @author Christian
 *
 */
public class TablePainter extends JPanel {
    
    // Informations-String
    private String info = "";
    // Liste mit Karten, die auf dem Tisch liegen
    private LinkedList<Card> tableCards = new LinkedList<Card>();
    // Liste mit eigenen Karten
    private LinkedList<Card> ownCards = new LinkedList<Card>();
    // Liste mit Mitspielerkarten
    private LinkedList<Card> playerCards = new LinkedList<Card>();
    
    /**
     * Setzt die eigene Spielerhand.
     * @param own Liste mit eigenen Karten
     */
    public void setOwnCards(LinkedList<Card> own) {
        ownCards = own;
    }
    
    /**
     * Setzt die Tischkarten.
     * @param table Liste mit Tischkarten
     */
    public void setTableCards(LinkedList<Card> table) {
        tableCards = table;
    }
    
    /**
     * Setzt die Karten des Mitspielers.
     * @param player Liste mit Karten des Mitspielers.
     */
    public void setPlayerCards(LinkedList<Card> player) {
        playerCards = player;
    }
    
    /**
     * Setzt den Informations-String.
     * @param info String mit Information
     */
    public void setInfo(String info) {
        this.info = info;
    }
    
    /**
     * Zeichnet den Graphen.
     * @param g uebergebenes Graphik-Objekt
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Ausgespielte Karten", 10, 15);
        g.drawString(tableCards.toString(), 10, 30);
        g.drawString("_________________________________", 0, 35);
        g.drawString("Eigene Karten:", 10, 60);
        g.drawString(ownCards.toString(), 10, 75);
        g.drawString("Mitspieler Karten", 10, 105);
        g.drawString(playerCards.toString(), 10, 120);
        g.drawString("Informationen: ", 10, 150);
        g.drawString(info, 10, 165);
        
        
    }

}

