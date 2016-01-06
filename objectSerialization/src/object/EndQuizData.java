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
 * @author Fendy
 */
public class EndQuizData implements Serializable {
    private String[] questions, answers;
    private int score;
    private boolean status;
    
    public EndQuizData(String[] questions, String[] answers, int score, boolean status) {
        this.questions = questions;
        this.answers = answers;
        this.score = score;
        this.status = status;
    }

    /**
     * @return the questions
     */
    public String[] getQuestions() {
        return questions;
    }

    /**
     * @return the yourAnswers
     */
    public String[] getAnswers() {
        return answers;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }
}
