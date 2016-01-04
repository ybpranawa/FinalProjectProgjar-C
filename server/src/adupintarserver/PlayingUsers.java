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
import object.StartQuizData;

/**
 *
 * @author fendy
 */
public class PlayingUsers {
    private static Map<String, GameThread> playingUsers = null;
    private static String[][] mapData;
    private static int dimension;
    private static final Object lock = new Object();

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
        
        synchronized (lock) {
            for (int i=0; i<dimension; i++) {
                for (int j=0; j<dimension; j++) {
                    mapData[i][j] = "";
                }
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
            
            synchronized (lock) {
                if (mapData[x][y].isEmpty()) {
                    mapData[x][y] = username;
                    break;
                }
            }
        }
    }
    
    public static void setInactive(String username) throws IOException {
        getPlayingUsers().remove(username);
       
        boolean flag = false;
        for (int i=0; i<dimension; i++) {
            for (int j=0; j<dimension; j++) {
                synchronized (lock) {
                    if (username == null ? mapData[i][j] == null : username.equals(mapData[i][j])) {
                         mapData[i][j] = "";
                         flag = true;
                         break;
                    }
                }
            }
            if (flag) break;
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
            gt.responseMap(null);
        }
    }
    
    public static void movePerson(String username, int move) throws IOException {
        int[] axis = {0,-1,0,1};
        int[] oord = {-1,0,1,0};
        
        boolean flag = false;
        boolean meetSomeone = false;
        String enemyUsername = "";
        for (int i=0; i<dimension; i++) {
            for (int j=0; j<dimension; j++) {
                synchronized (lock) {
                    if (username == null ? mapData[i][j] == null : username.equals(mapData[i][j])) {
                        mapData[i][j] = "";
                        int nextAxis = i + axis[move];
                        int nextOord = j + oord[move];
                        nextAxis = nextAxis<0 ? 0 : nextAxis;
                        nextAxis = nextAxis>=dimension ? dimension-1 : nextAxis;
                        nextOord = nextOord<0 ? 0 : nextOord;
                        nextOord = nextOord>=dimension ? dimension-1 : nextOord;
                        if (mapData[nextAxis][nextOord].equals("")) {
                            mapData[nextAxis][nextOord] = username;
                        } else {
                            meetSomeone = true;
                            enemyUsername = mapData[nextAxis][nextOord];
                            mapData[nextAxis][nextOord] = "";
                        }
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) break;
        }
        
        if (flag) {
            if(!meetSomeone) {
                broadcastMap();
            } else {
                if (!enemyUsername.isEmpty()) {
                    synchronized (lock) {
                        GameThread a = PlayingUsers.getUser(username);
                        GameThread b = PlayingUsers.getUser(enemyUsername);
                        a.responseStartQuiz(new StartQuizData(enemyUsername, 4));
                        b.responseStartQuiz(new StartQuizData(username, 4));
                        
                        PlayingUsers.setInactive(username);
                        PlayingUsers.setInactive(enemyUsername);
                    }
                }
            }
        }
    }
}
