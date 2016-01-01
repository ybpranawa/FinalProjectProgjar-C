/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author fendy
 */
public class ResponseFriendData implements Serializable {
    private final ArrayList<String> friendList;
    
    public ResponseFriendData(ArrayList<String> friendList) {
        this.friendList = friendList;
    }

    /**
     * @return the friendList
     */
    public ArrayList<String> getFriendList() {
        return friendList;
    }
}
