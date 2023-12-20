package Thread.sellticket;


public class Main {
    public static void main(String[] args) {
        SellTicket sellTicket=new SellTicket();
        Thread thread1=new Thread(sellTicket,"窗口1");
        Thread thread2=new Thread(sellTicket,"窗口2");
        Thread thread3=new Thread(sellTicket,"窗口3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
