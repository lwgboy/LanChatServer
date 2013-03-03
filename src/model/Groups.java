package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 2/26/13
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Groups {

    static int groupId = 0;//0 for group of all client
    static Map<Integer,Group> groups = new HashMap<Integer, Group>();
    static Group defaultGroup = new Group("ALL");

    static {
        groups.put(0,getDefaultGroup());
        groupId++;
    }

    public static int register(Group group){
        if(group.getId() == 0){
          return 0;
        }
        groups.put(groupId,group);
        groupId++;
        return groupId - 1;

    }

    //return group id
    public static int unregisterGroup(int groupId){
        return getGroups().remove(groupId).getId();
    }

    public static Map<Integer,Group> getGroups(){
        return groups;
    }

    public static Group getGroup(int groupId){
         return groups.get(groupId);
    }

    public static Group getDefaultGroup() {
        return defaultGroup;
    }

}
