import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;


public class TablePainter extends JPanel {
    
    
    private LinkedList<Card> tableCards = new LinkedList<Card>();

    private LinkedList<Card> ownCards = new LinkedList<Card>();
    
    private LinkedList<Card> playerCards = new LinkedList<Card>();
    
    
    public void setOwnCards(LinkedList<Card> own) {
        ownCards = own;
    }
    
    public void setTableCards(LinkedList<Card> table) {
        tableCards = table;
    }
    
    public void setPlayerCards(LinkedList<Card> player) {
        playerCards = player;
    }
    
    /**
     * Zeichnet den Graphen.
     * @param g uebergebenes Graphik-Objekt
     */
    protected void paintComponent(Graphics g) {
        g.drawString("Eigene Karten:", 10, 10);
        g.drawString(ownCards.toString(), 10, 23);
        g.drawString("Mitspieler Karten", 10, 53);
        g.drawString(playerCards.toString(), 10, 66);
        
        
    }

}
