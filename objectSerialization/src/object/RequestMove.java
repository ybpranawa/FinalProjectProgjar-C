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
public class RequestMove implements Serializable {
    private int move;
    
    public RequestMove(int move) {
        this.move = move;
    }

    /**
     * @return the move
     */
    public int getMove() {
        return move;
    }
}
