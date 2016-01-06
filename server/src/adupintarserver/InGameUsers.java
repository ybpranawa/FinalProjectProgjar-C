/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintarserver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Fendy
 */

public class InGameUsers {
    private static Map<String, GameId> userGame = null;
    private static Map<GameId, GameData> games = null;
    
    public InGameUsers() {
        userGame = new Hashtable<>();
        games = new Hashtable<>();
    }
    
    public static void init() {
        if (userGame == null && games == null) {
            new InGameUsers();
        }
    }
    
    public static void addUser(String username, GameId gameId) {
        userGame.put(username, gameId);
    }
    
    public static void addGame(GameId id, GameData data) {
        games.put(id, data);
    }
    
    public static void updateCategory(String username, int category) throws IOException, SQLException {
        GameId gid = userGame.get(username);
        GameData gd = games.get(gid);
        
        gd.setCategory(category);
        gd.broadcastQuestion(gid.getaUsername(), gid.getbUsername());
    }
    
    public static void answering(String username, int answer) throws IOException {
        GameId gid = userGame.get(username);
        GameData gd = games.get(gid);
        
        gd.answering(gid.getIndex(username), answer);
        if (gd.getTurn() / 2 == gd.getRounds()) {
            String aUsername = gid.getaUsername();
            String bUsername = gid.getbUsername();
            gd.endGame(aUsername, gid.getIndex(aUsername));
            gd.endGame(bUsername, gid.getIndex(bUsername));
        } else if (gd.getTurn() % 2 == 0) {
            gid.chooseCategory();
        }
    }
}
