import java.util.LinkedList;


public class ThreadWorker implements Runnable {

    public void run() {
        Table table = new Table();
        boolean fold = false;
        LinkedList<Card> cards = new LinkedList<Card>();
        //CardChecker card = new CardChecker();
        cards = table.check.getOwnCards();
        System.out.println("Ueberpruefung startet jetzt");
        while(true) {
            table.check = new CardChecker();
//            System.out.println(card.getOwnCards().toString());
//            System.out.println(cards.toString());
//            System.out.println(card.getOwnCards().toString().replaceAll("0", "").length());
            // Nichts neues
            if (cards.toString().equals(table.check.getOwnCards().toString())) {
                try {
                    Thread.sleep(2000);
                    
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // Neue Karten auf der Hand?
            else if (table.check.getOwnCards().toString().replaceAll("0", "").length() > 7) {
//                System.out.println("WECHSEL mit");
//                System.out.println(cards.toString());
//                System.out.println(table.check.getOwnCards().toString());
//                System.out.println(table.check.getOwnCards().toString().replaceAll("0", "").length());
                cards = table.check.getOwnCards();
                fold = false;
                System.out.println("Sende meine neuen Karten");
                table.sendClient();
                //table.sendServer();
                
            }
            // Wird gefoldet?
            else if (table.check.getOwnCards().toString().replaceAll("0", "").length() == 2 && fold == false) {
                table.refresh();
//                System.out.println("FOLD");
//                System.out.println("WECHSEL mit");
//                System.out.println(cards.toString());
//                System.out.println(card.getOwnCards().toString());
                
                cards = table.check.getOwnCards();
                fold = true;
                
            }
            
            
        }
        
        
        
    }

}
