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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.MapData;
import object.RequestMapData;
import object.RequestMove;
import object.RequestSetUsername;
import object.Response;

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
                    this.SetCredentials((RequestSetUsername) obj);
                } else if (obj instanceof RequestMapData) {
                    this.ResponseMap((RequestMapData) obj);
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
            }
        }
    }
    
    private void SetCredentials(RequestSetUsername data) throws IOException {
        String username = data.getUsername();
        
        PlayingUsers.setActive(username, this);
        this.me = new Credentials(username);
        
        this.oos.writeObject(new Response(200, "Ok"));
        this.oos.reset();
        
        PlayingUsers.broadcastMap();
    }

    public void ResponseMap(RequestMapData data) throws IOException {
        this.oos.writeObject(new MapData(PlayingUsers.getMapData()));
        this.oos.reset();
    }
}
