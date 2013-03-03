package model;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 2/26/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {

    private int id;
    private int groupId;
    private String name;
    private String ip;
    private String hostname;
    private int port;
    private long loginTime;
    private long offTime;
    private long chatStartTime;
    private long chatEndTime;


    public Client() {


    }

    public Client(String name) {
        this.setName(name);
    }

    public Group createGroup(String name){
        Group group = new Group(name);
        setGroupId(group.getId());
      return group;
    }

    //return Group id
    public Group quitGroup(){

        switch(getGroupId()){
            case 0: quit();
                    break;
            default:
        }
        Group group= Groups.getGroup(getGroupId());
        group.removeClient(getId());
        if(group.getClients().size() <= 0){
            Groups.unregisterGroup(getGroupId());
        }
        return group;
    }

    public boolean quit(){
        Group group = Groups.getGroup(getGroupId());
        group.removeClient(getId());
        return true;
    }

    public Group backToDefaultGroup(){
        Group group = Groups.getGroup(getGroupId());
        group.removeClient(getId());
        group = Groups.getDefaultGroup();
        group.addClient(this);
        return group;
    }










    public void setId(int id) {
        this.id = id;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
        Groups.getGroup(groupId).addClient(this);
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public void setOffTime(long offTime) {
        this.offTime = offTime;
    }

    public void setChatStartTime(long chatStartTime) {
        this.chatStartTime = chatStartTime;
    }

    public void setChatEndTime(long chatEndTime) {
        this.chatEndTime = chatEndTime;
    }

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getIp() {
        return ip;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public long getOffTime() {
        return offTime;
    }

    public long getChatStartTime() {
        return chatStartTime;
    }

    public long getChatEndTime() {
        return chatEndTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
