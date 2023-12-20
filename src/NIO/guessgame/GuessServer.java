package NIO.guessgame;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

//用NIO+Selector（多路复用器）完成一个猜数的服务器游戏
public class GuessServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc=ServerSocketChannel.open();//监听套接字通道NIO
        ssc.configureBlocking(false);//非阻塞模式
        ssc.bind(new InetSocketAddress(8080));
        Selector selector=Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            selector.select();//调用多路复用器的select方法，等待至少有一个channel上有操作就绪
            Set<SelectionKey> keys=selector.selectedKeys();
            Iterator<SelectionKey> it= keys.iterator();
            while (it.hasNext()){
                SelectionKey key=it.next();
                it.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    Protocol pro = new Protocol();
                    sc.register(selector, SelectionKey.OP_READ, pro);
                }
                if (key.isReadable()){
                    handler(key);
                }
            }
        }
    }
    private static void handler(SelectionKey key) throws IOException {
        ByteBuffer buff=ByteBuffer.allocate(4);//接受的是整数
        SocketChannel sc= (SocketChannel) key.channel();
        Protocol pro= (Protocol) key.attachment();
        int len;
        len=sc.read(buff);//buff处于写状态
        if (len>0){
            buff.flip();
            int playerNum=buff.getInt();
            int response=pro.working(playerNum);
            buff.clear();
            buff.putInt(response);
            buff.flip();
            sc.write(buff);
        }else {
            sc.close();
        }
    }
}
