/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintarserver;

import java.io.IOException;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import object.EndQuizData;
import object.QuizData;

/**
 *
 * @author Fendy
 */
public class GameData {
    private int turn, aScore, bScore, rounds, categoryId;
    private String[] category, questions, answers, aAns, bAns;
    private String[] choices;
    private int[] scoreList;
    
    public GameData(int rounds) {
       this.rounds = rounds;
       this.turn = 0;
       this.aScore = 0;
       this.bScore = 0;
       this.choices = new String[rounds];
       this.scoreList = new int[rounds];
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
    public void setCategory(int categoryId) throws SQLException {
        String category = "";
        if (categoryId == 1) {
            category = "Pendidikan";
        } else if (categoryId == 2) {
            category = "Kesehatan";
        } else if (categoryId == 3) {
            category = "Sains";
        }
        this.categoryId = categoryId;
        this.category[turn/2] = category;
        this.setQuestion();
    }

    public void broadcastQuestion(String aUsername, String bUsername) throws IOException {
        GameThread a = PlayingUsers.getUser(aUsername);
        GameThread b = PlayingUsers.getUser(bUsername);
        
        a.responseQuestion(new QuizData(this.questions[turn/2], choices, (turn/2)+1));
        b.responseQuestion(new QuizData(this.questions[turn/2], choices, (turn/2)+1));
    }
    
    private void setQuestion() throws SQLException {
        Random rand = new Random();
        
        DbConnect db = DbConnect.getDbCon();
        ArrayList<String> args = new ArrayList<>();
        args.add(Integer.toString(categoryId));
        String query = "SELECT id_soal, pertanyaan FROM soal WHERE id_kategori=?";
        ResultSet rs = db.query(query, args);
        rs.last();
        int idx = rand.nextInt(rs.getRow());
        rs.beforeFirst();
        for (int i=0;i<=idx;i++)rs.next();
        this.questions[turn/2] = (String)rs.getString("pertanyaan");
        
        args.clear();
        args.add((String)rs.getString("id_soal"));
        args.add(Integer.toString(categoryId));
        query = "SELECT jawab, score FROM jawaban WHERE id_soal=? AND id_kategori=?";
        rs = db.query(query, args);
        int i=0;
        while (rs.next()) {
            System.out.println(i + " " + rs.getString("jawab") + " " + rs.getString("score"));
            if (rs.getString("score").equals("1")) {
                this.answers[turn/2] = rs.getString("jawab");
            }
            this.choices[i] = rs.getString("jawab");
            this.scoreList[i] = Integer.parseInt(rs.getString("score"));
            i++;
        }
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
