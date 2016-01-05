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
public class QuizData implements Serializable {
    private String[] choices;
    private String question;
    private int quizNumber;
    
    public QuizData(String question, String[] choices, int quizNumber) {
        this.question = question;
        this.choices = choices;
        this.quizNumber = quizNumber;
    }

    /**
     * @return the choices
     */
    public String[] getChoices() {
        return choices;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @return the quizNumber
     */
    public int getQuizNumber() {
        return quizNumber;
    }
    
}
