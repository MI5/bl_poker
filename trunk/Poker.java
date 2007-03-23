/**
 * Hauptklasse.
 * Startet das Programm.
 * @author Christian
 *
 */
public class Poker {
    
    /**
     * Startet den Thread. Vorbereitung fuer nebenlaeufige Programmierung.
     * @param args Kommandozeilenparameter
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadWorker());
        t1.start();
        

    }

}
