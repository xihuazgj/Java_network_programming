package NIO.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();// 创建一个服务器套接字通道
        Selector selector = Selector.open(); // 创建一个选择器

        ssc.configureBlocking(false);// 设置通道非阻塞模式
        ssc.bind(new InetSocketAddress(8000)); // 绑定套接字地址
        ssc.register(selector, SelectionKey.OP_ACCEPT); // 将服务器套接字通道注册到选择器中，并指定操作为接受连接

        while (true) {// 循环接受客户端连接
            selector.select();  // 等待选择器中有就绪事件发生
            Set<SelectionKey> keys = selector.selectedKeys();// 获取就绪事件集合
            Iterator<SelectionKey> it = keys.iterator();// 创建一个迭代器遍历就绪事件集合
            while (it.hasNext()) {// 遍历就绪事件集合
                SelectionKey key = it.next(); // 获取当前就绪事件
                it.remove(); // 从就绪事件集合中移除当前就绪事件
                if (key.isAcceptable()) { // 如果当前就绪事件为可接受连接状态
                    SocketChannel sc = ssc.accept();// 接受客户端连接
                    sc.configureBlocking(false);    // 设置客户端套接字通道非阻塞模式
                    sc.register(selector, SelectionKey.OP_READ); // 将客户端套接字通道注册到选择器中，并指定操作为读取数据
                    System.out.println(sc.getRemoteAddress()+"客户端连接了。");
                }
                if (key.isReadable()) {  // 如果当前就绪事件为可读取状态
                    handler(key);
                }
            }
        }
    }
    private static void handler(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel(); // 获取SocketChannel对象
        ByteBuffer buff = ByteBuffer.allocate(32); // 创建ByteBuffer对象，用于数据传输
        int len;
        StringBuilder mess = new StringBuilder();
        while ((len = sc.read(buff)) > 0) {// 循环读取输入流直到读取完所有数据
            buff.flip();// 将缓冲区翻转，使得缓冲区头指针指向下一个要读取的位置，缓冲区尾指针指向下一个要写入的位置
            mess.append(new String(buff.array(), buff.position(), buff.limit(), "utf-8"));  // 将缓冲区中的数据转换为Unicode编码的字符串，并将其添加到字符串对象mess中
            System.out.println("收到来自"+sc.getRemoteAddress()+"的消息："+mess);
            buff.clear(); // 清空缓冲区，将缓冲区头指针和尾指针都重置为初始位置
        }
        if (len == -1) {   // 判断是否读取到输入流的末尾
            key.cancel(); // 如果读取到输入流的末尾，则取消操作
        } else {
            ByteBuffer writeBuff = ByteBuffer.wrap(mess.toString().getBytes("utf-8"));   // 如果未读取到输入流的末尾，则将mess中的字符串转换为Unicode编码的字节数组，并创建一个可写入字节数组的缓冲区writeBuff
            sc.write(writeBuff);  // 将写入的字节数组写入SocketChannel
        }
    }
}
