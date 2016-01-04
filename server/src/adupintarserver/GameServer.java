/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintarserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fendy
 */
class GameServer implements Runnable {

    private int port;
    
    public GameServer(int port) {
        this.port = port;
    }
    
    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            System.out.println("Game server started on port " + this.port);
            
            while (true) {
                Socket socket = server.accept();
                GameThread gt = new GameThread(socket);
                Thread t = new Thread(gt);
                t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
