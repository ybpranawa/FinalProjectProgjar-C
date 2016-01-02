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
public class ChatServer implements Runnable {
    private final int port;
    
    public ChatServer(int port) {
        this.port = port;
    }
    
    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Chat server started on port " + this.port);
            
            while (true) {
                Socket socket = server.accept();
                ChatThread ct = new ChatThread(socket);
                Thread t = new Thread(ct);
                t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
