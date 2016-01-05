/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintarserver;

import java.io.IOException;
import object.EndQuizData;
import object.QuizData;

/**
 *
 * @author Fendy
 */
public class GameData {
    private int turn, aScore, bScore, rounds;
    private String[] category, questions, answers, aAns, bAns;
    private String[] choices;
    private int[] scoreList;
    
    public GameData(int rounds) {
       this.rounds = rounds;
       this.turn = 0;
       this.aScore = 0;
       this.bScore = 0;
       this.category = new String[rounds];
       this.questions = new String[rounds];
       this.answers = new String[rounds];
       this.aAns = new String[rounds];
       this.bAns = new String[rounds];
    }

    /**
     * @return the turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * @return the score
     */
    public int getScore(int idx) {
        int score = -1;
        if (idx == 0) {
            score = aScore;
        } else {
            score = bScore;
        }
        return score;
    }
   
    public void answering(int idx, int answer) {
        this.turn++;
        if (idx == 0 ) {
            aScore += scoreList[answer];
        } else {
            bScore += scoreList[answer];
        }
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategory(int categoryId) {
        String category = "";
        if (categoryId == 1) {
            category = "Pendidikan";
        } else if (categoryId == 2) {
            category = "Kesehatan";
        } else if (categoryId == 3) {
            category = "Sains";
        }
        
        this.category[turn/2] = category;
        this.setQuestion();
    }

    public void broadcastQuestion(String aUsername, String bUsername) throws IOException {
        GameThread a = PlayingUsers.getUser(aUsername);
        GameThread b = PlayingUsers.getUser(bUsername);
        
        a.responseQuestion(new QuizData(this.questions[turn/2], choices, (turn/2)+1));
        b.responseQuestion(new QuizData(this.questions[turn/2], choices, (turn/2)+1));
    }
    
    private void setQuestion() {
        this.questions[turn/2] = "test";
        this.answers[turn/2] = "ansTest";
        int[] dum = {1,0,0,0};
        this.scoreList = dum;
        String[] dumm = {"benar", "salah", "salah", "salah"};
        this.choices = dumm;
    }
    
    private boolean isWinner(int idx) {
        return getScore(idx) > getScore((idx+1)%2);
    }

    /**
     * @return the rounds
     */
    public int getRounds() {
        return rounds;
    }

    public void endGame(String username, int idx) throws IOException {
        GameThread gt = PlayingUsers.getUser(username);
        gt.responseEndQuizData(new EndQuizData(this.questions, this.answers, getScore(idx), isWinner(idx)));
    }
}
