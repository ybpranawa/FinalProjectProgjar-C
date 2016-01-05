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

public class QuizAnswer implements Serializable {
    private int answer;
    
    public QuizAnswer(int answer) {
        this.answer = answer;
    }

    /**
     * @return the answer
     */
    public int getAnswer() {
        return answer;
    }
    
}
