package Thread.consumer;

import java.util.List;

public class Consume extends Thread {
    public List<Resource> list;

    public Consume(List<Resource> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                if (list.size() > 0) {
                    Resource remove = list.remove(0);
                    System.out.println(Thread.currentThread().getName()+"消耗了编号为"+remove.getId()+"的资源。");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    list.notifyAll();
                }else {
                    try {
                        list.wait();

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
