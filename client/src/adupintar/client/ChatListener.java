/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintar.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import object.ChatMessage;

/**
 *
 * @author fendy
 */
public class ChatListener implements Runnable {

    private boolean connectionOk = false;
    private Map<String, Chat> chatWith;
    
    public ChatListener() {
        this.connectionOk = true;
    }
    
    @Override
    public void run() {
        this.chatWith = new Hashtable<>();
        try {
            ChatServerConnection connection = ChatServerConnection.getInstance();
            ObjectInputStream ois = connection.getOis();
            
            while (this.connectionOk) {
                ChatMessage msg = (ChatMessage) ois.readObject();
                String friendUsername = msg.getFriendUsername();
                String message = msg.getMessage();
                
                if (!this.chatWith.containsKey(friendUsername)) {
                    Chat chatForm = new Chat(friendUsername, this);
                    this.chatWith.put(friendUsername, chatForm);
                    chatForm.showForm();
                }
                
                Chat friendForm = this.chatWith.get(friendUsername);
                friendForm.appendMessage(message);
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addChatWith(String friendUsername, Chat chatForm) {
        this.chatWith.put(friendUsername, chatForm);
    }
    
    public void removeChat(String friendUsername) {
        this.chatWith.remove(friendUsername);
    }
    
    public void stopListening() {
        this.connectionOk = false;
    }
}
