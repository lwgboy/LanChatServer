package util;

import model.UdpData;

import java.io.IOException;
import java.net.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 2/26/13
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil {


    public static long getCurrentTime(){
        return new Date().getTime();
    }

    public static long timeConvert(){
        return 0;
    }

    /**
     * Created with IntelliJ IDEA.
     * User: rain
     * Date: 2/25/13
     * Time: 2:57 PM
     * To change this template use File | Settings | File Templates.
     */
    public static class UdpClient {

        public static void main(String[] args){

            int TIMEOUT = 1000;

            UdpData dataS =  new UdpData();
            dataS.setCREATE_GROUP("clientONE");
            dataS.setREGISTER(true);
            byte[] data = dataS.toString().getBytes();
            int port = 8899;
            String servera = "localhost";


            try{
                // 1. 构造UDP DatagramSocket对象
                DatagramSocket client = new DatagramSocket();

                // 2。指定timeout时间，防止进入无限等待状态
                client.setSoTimeout(TIMEOUT);

                // 3. 构造收发的报文对象
                InetAddress ineta = InetAddress.getByName(servera);
                DatagramPacket sPacket = new DatagramPacket(data,data.length,ineta,port);

                byte[] buf = new byte[100];
                DatagramPacket rPacket = new DatagramPacket(buf,buf.length);

                // 4.指定尝试的次数
                int MAXTRIES = 5;
                int tries = 0;
                boolean hasResponse = false;

                do {
                    try{
                        client.send(sPacket);

                        try{
                            client.receive(rPacket);

                            System.out.println(tries + " Client Receive: "+new String(buf,0,rPacket.getLength()));

                            if(!rPacket.getAddress().equals(servera)){
                                throw new IOException("Received packet from an unknown source");
                            };

                            hasResponse = true;

                        }catch(IOException ioException){
                            System.out.println("Timed out, " + (MAXTRIES - tries) + "");
                            tries += 1;
                        }
                    }catch(IOException io){
                        System.out.println("Fail to Send.");
                    }

                }while((!hasResponse)&&(tries<MAXTRIES));


                if(hasResponse){
                    System.out.println("Receive: "+rPacket.getData());
                }else{
                    System.out.println("No Response, Give up");
                }
                client.close();

            }catch(SocketException e){

            }catch(UnknownHostException unknownHostException){

            }


        }


    }
}
