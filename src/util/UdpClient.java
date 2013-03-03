package util;

import java.net.InetAddress;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 2/26/13
 * Time: 9:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class UdpClient implements Runnable{

    //about send
    private String sData;
    private InetAddress sInetA;
    private String ip;
    private int port;

    //about receive
    private String rData;

    public UdpClient(String sData, String ip, int port) {
        this.sData = sData;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.

    }

    //return rData
    private String sendData(){

        return null;

    }

    public String getsData() {
        return sData;
    }

    public void setsData(String sData) {
        this.sData = sData;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
