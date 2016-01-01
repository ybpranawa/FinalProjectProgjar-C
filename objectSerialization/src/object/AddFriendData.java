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
public class AddFriendData implements Serializable {
    private final String friendUsername;
    
    public AddFriendData(String friendUsername) {
        this.friendUsername = friendUsername;
    }

    /**
     * @return the friendUsername
     */
    public String getFriendUsername() {
        return friendUsername;
    }
    
}
