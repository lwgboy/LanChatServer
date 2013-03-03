package ui;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import model.Client;
import model.Group;
import model.Groups;
import model.UdpData;
import util.DateUtil;

import javax.swing.*;
import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 2/26/13
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server {

    static ServerUI ui;

    public static void main(String[] args){


        int sPort = 8899;
        int ECHOMAX = 1024;
        byte[] sServerResponse = "YouHaveSignOnServer".getBytes();
        Gson gson = new Gson();

        ui = new ServerUI("Server UI");

        try{

            //new DatagramSocket
            DatagramSocket server = new DatagramSocket(sPort);
            System.out.println("Server is ready! ");


            int count = 0;
            while(true){

                try{

                    // new DatagramPacket and receive it
                    DatagramPacket rPacket = new DatagramPacket(new byte[ECHOMAX],ECHOMAX);
                    server.receive(rPacket);


                    /*register online client*/
                    try{
                        System.out.println(count++ + "Server Received: " + new String(rPacket.getData(),0,rPacket.getLength()));
                        JsonReader jsonReader = new JsonReader(new StringReader(new String(rPacket.getData())));
                        jsonReader.setLenient(true); //or throw malformedjsonexception
                        UdpData uData = gson.fromJson(jsonReader,UdpData.class);
                        if(uData.isREGISTER()){
                            register(rPacket, uData);
                        }else if(!(uData.getCREATE_GROUP().equals("")||uData.getCREATE_GROUP()=="")){
                            createGroup(uData.getGROUP_ID(),uData.getCLINET_ID(),uData.getCREATE_GROUP());
                        }else if(uData.isQUIT()){
                            logout(rPacket,uData);
                        }
                    }catch(IllegalStateException illegalStateException){


                    }


                    System.out.println(count++ + "Server Received: " + new String(rPacket.getData(),0,rPacket.getLength()));
                    System.out.println("Group"+ Groups.getDefaultGroup().toString());


                    //send packet
                    DatagramPacket sPacket = new DatagramPacket(sServerResponse,sServerResponse.length,rPacket.getAddress(),rPacket.getPort());
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

    //return client id
    private static int register(DatagramPacket rPacket,UdpData uData){
        Client client = new Client();
        client.setName(new String(rPacket.getData()));
        client.setIp(rPacket.getAddress().getHostAddress());
        client.setHostname(rPacket.getAddress().getHostName());
        client.setPort(rPacket.getPort());
        client.setLoginTime(DateUtil.getCurrentTime());
        client.setGroupId(uData.getGROUP_ID());

        Vector vector = new Vector();
        vector.add(Groups.getGroup(client.getGroupId()).getName());
        vector.add(client.getIp());
        vector.add(client.getPort());

        ui.refleshClientShow(vector);
        return Groups.getGroup(uData.getGROUP_ID()).getId();
    }

    private static int createGroup(int prevGroupId,int clientId,String name){
        Client client = Groups.getGroup(prevGroupId).getClient(clientId);
        Group group = client.createGroup(name);
        return group.addToGroups();
    }

    //return client id
    private static int logout(DatagramPacket oPacket,UdpData uData){
        Group group = Groups.getGroup(uData.getGROUP_ID());
        Client client = group.getClient(uData.getCLINET_ID());
        client.quitGroup();
        return client.getId();
    }

    private static int listGroup(JList jList){

        for(int i = 0; i< Groups.getGroups().size();i++){

        }
        return 0;
    }

    private static int listGroupClient(){

        return 0;
    }

}
