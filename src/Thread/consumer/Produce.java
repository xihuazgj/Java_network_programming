package Thread.consumer;

import java.util.List;

public class Produce extends Thread{
    public List<Resource> list;
    public Produce(List<Resource> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true){
            synchronized (list){
                if(list.size()>100){
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    Resource resource=new Resource(IDUtil.getId());
                    list.add(resource);
                    System.out.println(Thread.currentThread().getName()+"生产了编号为"+resource.getId()+"的资源。");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    list.notifyAll();;
                }
            }
        }
    }
}
