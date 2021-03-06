package model;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 2/26/13
 * Time: 4:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Group {

    private int id;
    private String name;
    private int clientId = 0;

    public Map<Integer,Client> clients;

    public Group(String name) {
        this.name = name;
        clients = new HashMap<Integer,Client>();
        //default
        id = 0;
    }

    public String toString(){
        return new Gson().toJson(this);
    }

    //return Client id
    public int addClient(Client client){
        clients.put(clientId, client);
        clientId++;
        return clientId - 1;

    }

    public int removeClient(int clientId){
       return clients.remove(clientId).getId();
    }

    public int addToGroups(){
        Groups.register(this);
        return this.getId();
    }

    public Client getClient(int clientId){
        return clients.get(clientId);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Client> getClients() {
        return clients;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
