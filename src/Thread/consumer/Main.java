package Thread.consumer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Resource> list=new ArrayList<>();
        Consume consume=new Consume(list);
        Produce produce=new Produce(list);
        Thread thread1=new Thread(consume,"消费者1");
        Thread thread2=new Thread(consume,"消费者2");
        Thread thread3=new Thread(consume,"消费者3");
        Thread thread4=new Thread(produce,"生产者1");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
