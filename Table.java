 /**
 * Klasse Table.
 * Repraesentiert den Pokertisch mit Spielern und Karten.
 * @author Christian
 *
 */
public class Table {
    
    private CardSet cards = new CardSet();
    
    public static void main(String[] args) {
        CardChecker check = new CardChecker();
        System.out.println(check.toString());
    }
    

}
