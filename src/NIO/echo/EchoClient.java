package NIO.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open(); // 创建一个Socket通道
        sc.connect(new InetSocketAddress("localhost", 8000));// 连接到本地主机的7000端口
        ByteBuffer buff = ByteBuffer.allocate(1024);// 创建一个字节缓冲区，大小为1024
        String mess; // 定义消息变量
        Scanner scan = new Scanner(System.in); // 创建一个扫描器对象，用于读取输入
        while (scan.hasNext()) { // 进入循环，扫描下一行输入
            mess = scan.nextLine();// 从控制台获取用户输入的一行消息
            buff.put(mess.getBytes("utf-8"));// 将输入的消息以UTF-8编码转换为字节数组，并存入字节缓冲区
            buff.flip();// 切换缓冲区为读取模式，准备将数据写入通道
            sc.write(buff);// 通过Socket通道将字节缓冲区的数据写入服务器端
            buff.clear();// 清空字节缓冲区，准备接收从服务器端返回的数据
            sc.read(buff);// 通过Socket通道读取服务器端的响应数据到字节缓冲区
            buff.flip();// 切换缓冲区为读取模式，准备从缓冲区中获取服务器端的响应数据
            System.out.println("服务器:" + new String(buff.array(), 0, buff.limit(), "utf-8")); // 输出接收到的回显消息
            buff.clear(); // 清空字节缓冲区，等待下一轮循环
            if (mess.equals("bye")) {  // 如果消息为"bye"，跳出循环
                break;
            }
        }
        sc.close();  // 关闭Socket通道
    }
}


