package model;

import com.google.gson.Gson;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 2/28/13
 * Time: 10:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class UdpData {

    private int CLINET_ID = -1;
    private int GROUP_ID = 0;
    private boolean REGISTER = false;
    private String CREATE_GROUP = null;
    private int JOIN_GROUP = 0;
    private int QUIT_GROUP = -1;
    private boolean QUIT = false;

    public String toString(){
        return new Gson().toJson(this);
    }

    public void setCLINET_ID(int CLINET_ID) {
        this.CLINET_ID = CLINET_ID;
    }

    public void setCREATE_GROUP(String name) {
        this.CREATE_GROUP = name;
    }

    public void setGROUP_ID(int GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    public void setJOIN_GROUP(int JOIN_GROUP) {
        this.JOIN_GROUP = JOIN_GROUP;
    }

    public void setQUIT(boolean QUIT) {
        this.QUIT = QUIT;
    }

    public void setQUIT_GROUP(int QUIT_GROUP) {
        this.QUIT_GROUP = QUIT_GROUP;
    }

    public void setREGISTER(boolean REGISTER) {
        this.REGISTER = REGISTER;
    }

    public int getCLINET_ID() {
        return CLINET_ID;
    }

    public String getCREATE_GROUP() {
        return CREATE_GROUP;
    }

    public int getGROUP_ID() {
        return GROUP_ID;
    }

    public int getJOIN_GROUP() {
        return JOIN_GROUP;
    }

    public boolean isQUIT() {
        return QUIT;
    }

    public int getQUIT_GROUP() {
        return QUIT_GROUP;
    }

    public boolean isREGISTER() {
        return REGISTER;
    }
}
