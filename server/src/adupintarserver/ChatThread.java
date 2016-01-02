/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintarserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.*;

/**
 *
 * @author fendy
 */
public class ChatThread implements Runnable {
    
    private final Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Credentials me = null;
    private boolean connectionOk = false;
    
    public ChatThread(Socket socket) throws IOException {
        this.socket = socket;
        this.ois = new ObjectInputStream(socket.getInputStream());
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.connectionOk = true;
    }
    
    @Override
    public void run() {
        while (connectionOk) {
            try {
                Object obj = this.ois.readObject();
                if (obj instanceof RequestSetUsername) {
                    this.SetCredentials((RequestSetUsername) obj);
                }else if (obj instanceof AddFriendData) {
                    this.AddFriend((AddFriendData) obj);
                } else if (obj instanceof RequestFriendData) {
                    this.FriendData((RequestFriendData) obj);
                }
            } catch (IOException ex) {
                this.connectionOk = false;
                //System.out.println("lala");
                //Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void SetCredentials(RequestSetUsername data) throws IOException {
        String username = data.getUsername();
        
        this.me = new Credentials(username);
        
        this.oos.writeObject(new Response(200, "Ok"));
    }
    
    private void AddFriend(AddFriendData data) throws SQLException, IOException {
        String friendUsername = data.getFriendUsername();
        
        DbConnect db = DbConnect.getDbCon();
        ArrayList<String> args = new ArrayList<>();
        args.add(friendUsername);
        String query = "SELECT * "
                + "FROM users "
                + "WHERE username=?";
        ResultSet rs = db.query(query, args);
        
        int responseCode = -1;
        String responseString = null;
        if (rs.next()) {
            args.add(0, this.me.getUsername());
            query = "SELECT * "
                    + "FROM friendships "
                    + "WHERE me=? AND friend=?";
            rs = db.query(query, args);
            
            if (rs.next()) {
                responseCode = 400;
                responseString = "You already friend with " + friendUsername + "!";
            } else {
                query = "INSERT INTO friendships VALUES(?,?)";
                int res = db.insert(query, args);
                if (res > 0) {
                    responseCode = 200;
                    responseString = "Ok";
                }
            }
        } else {
            responseCode = 400;
            responseString = "Username not found!";
        }
        
        this.oos.writeObject(new Response(responseCode, responseString));
    }

    private void FriendData(RequestFriendData data) throws SQLException, IOException {
        String returntype = data.getReturntype();
        
        DbConnect db = DbConnect.getDbCon();
        ArrayList<String> args = new ArrayList<>();
        args.add(this.me.getUsername());
        String query = "SELECT * "
                + "FROM friendships "
                + "WHERE me=? "
                + "ORDER BY friend";
        ResultSet rs = db.query(query, args);
        
        ArrayList<String> friendList = new ArrayList<>();
        while (rs.next()) {
            friendList.add(rs.getString("friend"));
        }
        
        this.oos.writeObject(new ResponseFriendData(friendList));
    }
}
