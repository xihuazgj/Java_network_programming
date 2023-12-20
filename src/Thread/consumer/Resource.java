package Thread.consumer;

public class Resource {
    private int id;
    private String content="资源内容是。。";

    public Resource(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

}
