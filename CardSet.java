import java.util.LinkedList;

/**
 * Klasse CardSet.
 * Beinhaltet 52 Spielkarten.
 * @author Christian
 *
 */
public class CardSet {
    
    // Datenstruktur fuer Spielkarten
    LinkedList<Card> cards = new LinkedList<Card>();
    
    /**
     * Konstruktor.
     * Erzeugt ein Spielkarten-Set mit 52 Karten
     */
    public CardSet() {
        for (int i = 2; i < 15; i++) {
            cards.add(new Card("KREUZ", i));
            cards.add(new Card("PIK", i));
            cards.add(new Card("HERZ", i));
            cards.add(new Card("KARO", i));
        }
    }

}
