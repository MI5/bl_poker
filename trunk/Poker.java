/**
 * Hauptklasse.
 * Startet das Programm.
 * @author Christian
 *
 */
public class Poker {
    
    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadWorker());
        t1.start();

    }

}
