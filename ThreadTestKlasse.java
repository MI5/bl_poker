
public class ThreadTestKlasse {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ThreadWorker());
        t1.start();
        
    }
}
