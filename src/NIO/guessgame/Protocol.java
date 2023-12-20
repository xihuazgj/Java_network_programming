package NIO.guessgame;

//应用层协议：客户端和服务器端的一个请求和响应关系
public class Protocol {
    private final int serverNum=(int)(Math.random()*10)+1;
    int working(int playerNum){
        if (playerNum>serverNum){
            return 1;
        } else if (playerNum<serverNum) {
            return -1;
        }else return 0;
    }
}
