/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintarserver;

import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author fendy
 */
public class ActiveUsers {
    private static Map<String, ChatThread> activeUsers = null;
    
    private ActiveUsers() {
        ActiveUsers.activeUsers = new Hashtable<>();
    }
    
    public static void init() {
        if (activeUsers == null)new ActiveUsers();
    }
    
    public static boolean isActive(String username) {
        return activeUsers.containsKey(username);
    }
    
    public static void setActive(String username, ChatThread ct) {
        activeUsers.put(username, ct);
    }
    
    public static void setInactive(String username) {
       activeUsers.remove(username);
    }
    
    public static ChatThread getUser(String username) {
        if (isActive(username)) {
            return activeUsers.get(username);
        }
        return null;
    }
}
