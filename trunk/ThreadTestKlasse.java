
public class ThreadTestKlasse {

    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(new ThreadWorker());
//        t1.start();
//        Client c = new Client("INFO");
//        System.out.println(c.send());
        
        PokerClient c = new PokerClient(new Table());
        
        Thread t1 = new Thread(c);
        t1.start();
        
        
        
        Thread.sleep(10000);
        c.send("Hallo2");
        
    }
}
