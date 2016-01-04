/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintarserver;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author fendy
 */
public class PlayingUsers {
    private static Map<String, GameThread> playingUsers = null;
    private static String[][] mapData;
    private static int dimension;

    /**
     * @return the playingUsers
     */
    public static Map<String, GameThread> getPlayingUsers() {
        return playingUsers;
    }
    
    private PlayingUsers(int dimension) {
        PlayingUsers.dimension = dimension;
        PlayingUsers.playingUsers = new Hashtable<>();
        PlayingUsers.mapData = new String[dimension][dimension];
        
        for (int i=0; i<dimension; i++) {
            for (int j=0; j<dimension; j++) {
                mapData[i][j] = "";
            }
        }
    }
    
    public static void init(int dimension) {
        if (getPlayingUsers() == null) {
            new PlayingUsers(dimension);
        }
    }
    
    public static boolean isActive(String username) {
        return getPlayingUsers().containsKey(username);
    }
    
    public static void setActive(String username, GameThread gt) {
        getPlayingUsers().put(username, gt);
        Random rand = new Random();
        while (true) {
            int randomNum = rand.nextInt(dimension*dimension);
            
            int x = randomNum / dimension;
            int y = randomNum % dimension;
            
            if (mapData[x][y].isEmpty()) {
                mapData[x][y] = username;
                break;
            }
        }
    }
    
    public static void setInactive(String username) throws IOException {
        getPlayingUsers().remove(username);
       
        for (int i=0; i<dimension; i++) {
            for (int j=0; j<dimension; j++) {
                if (username == null ? mapData[i][j] == null : username.equals(mapData[i][j])) {
                     mapData[i][j] = "";
                     break;
                }
            }
        }
        
        broadcastMap();
    }
    
    public static GameThread getUser(String username) {
        if (isActive(username)) {
            return getPlayingUsers().get(username);
        }
        return null;
    }
    
    public static String[][] getMapData() {
        return mapData;
    }
    
    public static void broadcastMap() throws IOException {
        for (Map.Entry<String, GameThread> entry : playingUsers.entrySet()) {
            GameThread gt = entry.getValue();
            gt.ResponseMap(null);
        }
    }
}
