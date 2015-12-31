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
 * @author PRANAWA
 */
class ServerHandle {

    private int port;
    
    ServerHandle(int port) {
        this.port=port;
    }

    void start() {
        try {
            ServerSocket server=new ServerSocket(this.port);
            System.out.println("Server started, port " + this.port);
            while(true){
                Socket socket=server.accept();
                ClientThread client=new ClientThread(socket);
                Thread ct=new Thread(client);
                ct.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerHandle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
