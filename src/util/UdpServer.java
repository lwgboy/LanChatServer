package util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 2/25/13
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class UdpServer {


    public void receiveUdp(){

        int sPort = 8899;
        int ECHOMAX = 1024;
        byte[] data = "Server string test".getBytes();
        System.out.println(data.length);

        try{

            // 1. 构建DatagramSocket实例，指定本地端口。
            DatagramSocket server = new DatagramSocket(sPort);

            // 2. 构建需要收发的DatagramPacket报文
            DatagramPacket rPacket = new DatagramPacket(new byte[ECHOMAX],ECHOMAX);
            DatagramPacket sPacket;

            System.out.println("Server is ready! ");
            while(true){

                try{
                    // 3.收报文
                    server.receive(rPacket);
                    System.out.println("Handling client at: " + rPacket.getAddress().getHostAddress() + " on port: " + rPacket.getPort());
                    System.out.println("Server Received: " + new String(rPacket.getData(),0,rPacket.getLength()));

                    //4.发送报文
                    sPacket = new DatagramPacket(data,data.length,rPacket.getAddress(),rPacket.getPort());
                    server.send(sPacket);

                }catch(IOException e){
                    System.out.println("No connection.");
                     System.out.println(e.toString());
                }
            }
        }catch(SocketException socketException){
            socketException.printStackTrace();
        }

    }


}
