/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.Serializable;

/**
 *
 * @author Fendy
 */
public class StartQuizData implements Serializable {
    private String enemyUsername;
    private int round;
    
    public StartQuizData(String enemyUsername,int round) {
        this.enemyUsername = enemyUsername;
        this.round = round;
    }

    /**
     * @return the enemyUsername
     */
    public String getEnemyUsername() {
        return enemyUsername;
    }

    /**
     * @return the round
     */
    public int getRound() {
        return round;
    }
}
