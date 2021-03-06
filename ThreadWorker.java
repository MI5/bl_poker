import java.util.LinkedList;

/**
 * Klasse ThreadWorker. 
 * Ermoeglicht voll automatische KartenUpdates.
 * @author Christian
 *
 */
public class ThreadWorker implements Runnable {

    /**
     * Thread wird gestartet.
     */
    public void run() {
        // erzeuge Table-Instanz 
        Table table = new Table();
        // Pruefvariable auf Fold
        boolean fold = false;
        // Liste fuer eigene Karten
        LinkedList<Card> cards = new LinkedList<Card>();
        // lese eigene Karten aus
        cards = table.check.getOwnCards();
        while(true) {
            // Scanne permanent den Tisch
            table.check = new CardChecker();
            table.refresh();
            // Gibt es Veraenderungen?
            if (cards.toString().equals(table.check.getOwnCards().toString())) {
                try {
                    // Wenn nein, schlafe 2 Sekunden
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Neue Karten auf der Hand?
            else if (table.check.getOwnCards().toString().replaceAll("0", "").length() > 7) {
                // Lese neue Karten aus
                cards = table.check.getOwnCards();
                table.refresh();
                fold = false;
                // Aenderung! Man hat neue Karten bekommen was passiert?
                // Man schickt Sie an den Partner durch:
                if (table.getOnlineStatus()) {
                    System.out.println("ONLINE");
                    table.sendNewClient();
                }
            }
            // Pruefe auf Fold. Fuer spaetere Verwendung
            else if (table.check.getOwnCards().toString().replaceAll("0", "").length() == 2 && fold == false) {
//                table.refresh();
                //cards = table.check.getOwnCards();
                fold = true;
                System.out.println("FOLD");
                
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
        }
        
        
        
    }

}
