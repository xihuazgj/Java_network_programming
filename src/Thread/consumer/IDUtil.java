package Thread.consumer;

public class IDUtil {
    static int id=0;

    public static int getId() {
        return ++id;
    }
}
