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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.AddFriendData;
import object.*;

/**
 *
 * @author PRANAWA
 */
public class ClientThread implements Runnable {
    
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Credentials me = null;
    private boolean connectionOk = false;
    
    ClientThread(Socket socket) throws IOException{
        this.socket = socket;
        this.ois = new ObjectInputStream(this.socket.getInputStream());
        this.oos = new ObjectOutputStream(this.socket.getOutputStream());
        this.connectionOk = true;
    }

    @Override
    public void run() {
        while (connectionOk) {
            try {
                Object obj = this.ois.readObject();
                if (obj instanceof SignUpData) {
                    this.SignUp((SignUpData) obj);
                } else if (obj instanceof LogInData) {
                    this.LogIn((LogInData) obj);
                }
            } catch (IOException ex) {
                this.connectionOk = false;
                //System.out.println("lala");
                //Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void SignUp(SignUpData data) throws IOException, SQLException {
        String username = data.getUsername();
        String password = data.getPassword();
        String name = data.getName();

        DbConnect db = DbConnect.getDbCon();
        ArrayList<String> args = new ArrayList<>();
        args.add(username);
        args.add(password);
        ResultSet rs = db.query("SELECT * FROM users WHERE username=? and password=?", args);

        int responseCode = 500;
        String responseString = null;
        if (rs.next()) {
            responseCode = 301;
            responseString = "Username Sudah Dipakai!";
        } else {
            args.add(name);
            int stat = db.insert("INSERT INTO users VALUES (?,?,?)", args);
            if (stat > 0) {
                responseCode = 200;
                responseString = "Ok";
            }
        }

        this.oos.writeObject(new Response(responseCode, responseString));
    }

    private void LogIn(LogInData data) throws SQLException, IOException {
        String username = data.getUsername();
        String password = data.getPassword();

        DbConnect db = DbConnect.getDbCon();
        ArrayList<String> args = new ArrayList<>();
        args.add(username);
        args.add(password);
        ResultSet rs = db.query("SELECT * FROM users WHERE username=? and password=?", args);

        int responseCode = 500;
        String responseString = null;
        if (rs.next()) {
            responseCode = 200;
            responseString = "Ok";
            
            this.me = new Credentials(username);
        } else {
            responseCode = 300;
            responseString = "Invalid credentials!";
        }
        
        this.oos.writeObject(new Response(responseCode, responseString));
    }
    
}
