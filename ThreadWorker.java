import java.util.LinkedList;


public class ThreadWorker implements Runnable {

    public void run() {
        boolean fold = false;
        LinkedList<Card> cards = new LinkedList<Card>();
        CardChecker card = new CardChecker();
        cards = card.getOwnCards();
        while(true) {
            card = new CardChecker();
//            System.out.println(card.getOwnCards().toString());
//            System.out.println(cards.toString());
//            System.out.println(card.getOwnCards().toString().replaceAll("0", "").length());
            if (cards.toString().equals(card.getOwnCards().toString())) {
                try {
                    Thread.sleep(2000);
                    
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else if (card.getOwnCards().toString().replaceAll("0", "").length() > 7) {
                System.out.println("WECHSEL mit");
                System.out.println(cards.toString());
                System.out.println(card.getOwnCards().toString());
                System.out.println(card.getOwnCards().toString().replaceAll("0", "").length());
                cards = card.getOwnCards();
                fold = false;
                
                
            }
            else if (card.getOwnCards().toString().replaceAll("0", "").length() == 2 && fold == false) {
                System.out.println("FOLD");
                System.out.println("WECHSEL mit");
                System.out.println(cards.toString());
                System.out.println(card.getOwnCards().toString());
                
                cards = card.getOwnCards();
                fold = true;
                
            }
            
            
        }
        
        
        
    }

}
