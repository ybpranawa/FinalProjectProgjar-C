/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintar.client;

/**
 *
 * @author fendy
 */
public class Credentials {
    private static Credentials credentials = null;
    private static String username;
    
    private Credentials(String username) {
        Credentials.username = username;
    }
    
    public static void create(String username) {
        if (credentials == null) {
            credentials = new Credentials(username);
        }
    } 

    public static Credentials getInstance() {
        return credentials;
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    
    public static void dispose() {
        credentials = null;
    }
}
