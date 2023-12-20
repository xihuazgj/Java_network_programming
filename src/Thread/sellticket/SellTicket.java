package Thread.sellticket;

public class SellTicket extends Thread {
    private int ticket = 0;

    @Override
    public void run() {
        while (true) {
            if(!sell()){
                break;
            }
        }
    }

    synchronized boolean sell() {
        if(ticket>100){
            return false;
        }else {
            System.out.println(Thread.currentThread().getName()+"正在售卖第"+ticket+++"张票。");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
    }
}
