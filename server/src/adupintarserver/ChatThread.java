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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
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
                } else if (obj instanceof ChatMessage) {
                    this.ChatHandler((ChatMessage) obj);
                }
            } catch (IOException ex) {
                this.connectionOk = false;
                ActiveUsers.setInactive(this.me.getUsername());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void SetCredentials(RequestSetUsername data) throws IOException {
        String username = data.getUsername();
        
        ActiveUsers.setActive(username, this);
        this.me = new Credentials(username);
        
        this.oos.writeObject(new Response(200, "Ok"));
        this.oos.reset();
    }
    
    private void ChatHandler(ChatMessage msg) throws IOException {
        String fromUsername = this.me.getUsername();
        String destUsername = msg.getTo();
        String message = msg.getMessage();
        
        this.oos.writeObject(new ChatMessage(fromUsername, destUsername, AddTimestamp("You", message)));
        
        if (destUsername.equals("")) {
            ChatMessage data = new ChatMessage(fromUsername, destUsername, AddTimestamp(fromUsername, message));
            for (Map.Entry<String, ChatThread> entry : ActiveUsers.getActiveUsers().entrySet()) {
                if (!entry.getKey().equals(fromUsername)) {
                    entry.getValue().SendMessage(data);
                }
            }
        } else {
            ChatThread ct = ActiveUsers.getUser(destUsername);
            if (ct != null) {
                ct.SendMessage(new ChatMessage(destUsername, fromUsername, AddTimestamp(fromUsername, message)));
            } else {
                this.oos.writeObject(new ChatMessage(fromUsername, destUsername, AddTimestamp("Server", destUsername + " is currently offline!\n")));
                this.oos.reset();
            }
        }
    }
    
    private void SendMessage(ChatMessage msg) throws IOException {
        this.oos.writeObject(msg);
        this.oos.reset();
    }
    
    private String AddTimestamp(String username, String message) {
        DateFormat df = new SimpleDateFormat("(HH:mm)");
        Date dateobj = new Date();
        return username + ": " + df.format(dateobj) + " " + message;
    }
}
