package NIO.guessgame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Player {
    public static void main(String[] args) throws IOException {
        Socket sc=new Socket("localhost",8080);
        DataInputStream in=new DataInputStream(sc.getInputStream());
        DataOutputStream out=new DataOutputStream(sc.getOutputStream());
        int playerNum,response;
        Scanner scanner=new Scanner(System.in);
        System.out.println("请猜一个1-10之间的数");

        while (scanner.hasNext()){
            playerNum= scanner.nextInt();
            out.writeInt(playerNum);
            response=in.readInt();
            if ((response==1)){
                System.out.println("你猜大了");
            } else if (response==-1) {
                System.out.println("你猜小了");
            }else {
                System.out.println("你猜对了");
                break;
            }
        }

    }
}
