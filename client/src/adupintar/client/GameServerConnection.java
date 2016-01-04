/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintar.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author fendy
 */
class GameServerConnection {
    
    private static GameServerConnection connection = null;
    private static String host;
    private static int port;

    public static void dispose() {
        connection = null;
    }
    
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    private GameServerConnection() throws IOException {
        this.socket = new Socket(host, port);
        this.ois = new ObjectInputStream(this.socket.getInputStream());
        this.oos = new ObjectOutputStream(this.socket.getOutputStream());
    }
    
    public static GameServerConnection getInstance() {
        return connection;
    }

    public static void connect() throws IOException {
        if (connection == null) {
            connection = new GameServerConnection();
        }
    }

    public static void setPort(int port) {
        GameServerConnection.port = port;
    }

    public static void setHost(String host) {
        GameServerConnection.host = host;
    }

    /**
     * @return the oos
     */
    public ObjectOutputStream getOos() {
        return oos;
    }

    /**
     * @return the ois
     */
    public ObjectInputStream getOis() {
        return ois;
    }
    
}
