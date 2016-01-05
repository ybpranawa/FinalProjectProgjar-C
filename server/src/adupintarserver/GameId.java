/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintarserver;

import java.io.IOException;

/**
 *
 * @author Fendy
 */

public class GameId {
    private String aUsername, bUsername;
    private int who;
    
    public GameId(String aUsername, String bUsername, int who) {
        this.aUsername = aUsername;
        this.bUsername = bUsername;
        this.who = who;
    }
    
    public int getIndex(String username) {
        if (username.equals(getaUsername())) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * @return the aUsername
     */
    public String getaUsername() {
        return aUsername;
    }

    /**
     * @return the bUsername
     */
    public String getbUsername() {
        return bUsername;
    }

    public void chooseCategory() throws IOException {
        GameThread gt;
        if (who == 0) {
            gt = PlayingUsers.getUser(aUsername);
        } else {
            gt = PlayingUsers.getUser(bUsername);
        }
        who++;
        who%=2;
        gt.responseChoiceData(-1);
    }
}
