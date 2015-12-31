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
 * @author Fendy
 */
public class ServerConnection {
    private static ServerConnection connection;
    private static String host;
    private static int port;
    
    private Socket socket = null;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    private ServerConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.oos = new ObjectOutputStream(this.socket.getOutputStream());
        this.ois = new ObjectInputStream(this.socket.getInputStream());
    }
    
    public static synchronized ServerConnection getInstance() throws IOException {
        if (connection == null) {
            Socket socket = new Socket(host, port);
            connection = new ServerConnection(socket);
        }
        return connection;
    }

    /**
     * @return the host
     */
    public static String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public static void setHost(String host) {
        ServerConnection.host = host;
    }

    /**
     * @return the port
     */
    public static int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public static void setPort(int port) {
        ServerConnection.port = port;
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
