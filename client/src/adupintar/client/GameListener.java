/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintar.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import object.ChatMessage;
import object.MapData;
import object.StartQuizData;

/**
 *
 * @author fendy
 */
class GameListener implements Runnable {
    private boolean connectionOk = false;
    private Map<String, Chat> chatWith;
    private int dimension;
    private Play form;
    
    public GameListener(int dimension, Play form) {
        this.connectionOk = true;
        this.dimension = dimension;
        this.form = form;
    }
    
    @Override
    public void run() {
        this.chatWith = new Hashtable<>();
        try {
            GameServerConnection connection = GameServerConnection.getInstance();
            ObjectInputStream ois = connection.getOis();
            
            while (this.connectionOk) {
                Object obj = ois.readObject();
                if (obj instanceof MapData) {
                    MapData mapData = (MapData) obj;
                    String[][] mep = mapData.getMap();
                    for (int i=0; i<dimension; i++) {
                        for (int j=0; j<dimension; j++) {
                            this.form.setMap(i,j,mep[i][j]);
                        }
                    }
                } else if (obj instanceof StartQuizData) {
                    this.Quiz((StartQuizData) obj);
                }
            }
        } catch (IOException ex) {
            this.connectionOk = false;
            Logger.getLogger(ChatListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addChatWith(String friendUsername, Chat chatForm) {
        this.chatWith.put(friendUsername, chatForm);
    }
    
    public void removeChat(String friendUsername) {
        this.chatWith.remove(friendUsername);
    }
    
    public void stopListening() {
        this.connectionOk = false;
    }
    
    private void Quiz(StartQuizData data) {
        QuizOverview overviewForm = new QuizOverview(data.getEnemyUsername(), data.getRound(), form);
        form.setVisible(false);
        overviewForm.showForm();
    }
}
