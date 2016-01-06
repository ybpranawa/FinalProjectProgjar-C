/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintarserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.ChoiceData;
import object.EndQuizData;
import object.MapData;
import object.QuizAnswer;
import object.QuizData;
import object.RequestMapData;
import object.RequestMove;
import object.RequestSetUsername;
import object.Response;
import object.StartQuizData;

/**
 *
 * @author fendy
 */
class GameThread implements Runnable {

    private boolean connectionOk;
    private final Socket socket;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;
    private Credentials me;
    private int myx, myy;
    
    public GameThread(Socket socket) throws IOException {
        this.connectionOk = true;
        this.socket = socket;
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
    }
    
    @Override
    public void run() {
        while (connectionOk) {
            try {
                Object obj = this.ois.readObject();
                if (obj instanceof RequestSetUsername) {
                    this.setCredentials((RequestSetUsername) obj);
                } else if (obj instanceof RequestMapData) {
                    this.responseMap((RequestMapData) obj);
                } else if (obj instanceof RequestMove) {
                    this.movePerson((RequestMove) obj);
                } else if (obj instanceof ChoiceData) {
                    this.setCategory((ChoiceData) obj);
                } else if (obj instanceof QuizAnswer) {
                    this.answering((QuizAnswer) obj);
                }
            } catch (IOException ex) {
                this.connectionOk = false;
                try {
                    PlayingUsers.setInactive(this.me.getUsername());
                } catch (IOException ex1) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void setCredentials(RequestSetUsername data) throws IOException {
        String username = data.getUsername();
        
        PlayingUsers.setActive(username, this);
        this.me = new Credentials(username);
        
        this.oos.writeObject(new Response(200, "Ok"));
        this.oos.reset();
        
        PlayingUsers.broadcastMap();
    }
    
    private void movePerson(RequestMove data) throws IOException {
        int move = data.getMove();
        PlayingUsers.movePerson(this.me.getUsername(), move);
    }

    public void responseMap(RequestMapData data) throws IOException {
        this.oos.writeObject(new MapData(PlayingUsers.getMapData()));
        this.oos.reset();
    }
    
    public void responseStartQuiz(StartQuizData data) throws IOException {
        this.oos.writeObject(data);
        this.oos.reset();
    }

    public void responseChoiceData(int i) throws IOException {
        this.oos.writeObject(new ChoiceData(i));
        this.oos.reset();
    }

    public void responseQuestion(QuizData quizData) throws IOException {
        this.oos.writeObject(quizData);
        this.oos.reset();
    }

    private void setCategory(ChoiceData choiceData) throws IOException, SQLException {
        int choice = choiceData.getChoiceData();
        InGameUsers.updateCategory(this.me.getUsername(), choice);
    }

    private void answering(QuizAnswer quizAnswer) throws IOException {
        int answer = quizAnswer.getAnswer();
        InGameUsers.answering(this.me.getUsername(), answer);
    }

    public void responseEndQuizData(EndQuizData endQuizData) throws IOException {
        this.oos.writeObject(endQuizData);
        this.oos.reset();
    }
}
