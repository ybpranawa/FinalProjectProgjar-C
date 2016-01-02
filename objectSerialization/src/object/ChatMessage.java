/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.Serializable;

/**
 *
 * @author fendy
 */
public class ChatMessage implements Serializable {
    private String friendUsername;
    private String message;
    
    public ChatMessage(String friendUsername, String message) {
        this.friendUsername = friendUsername;
        this.message = message;
    }

    /**
     * @return the friendUsername
     */
    public String getFriendUsername() {
        return friendUsername;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
}
