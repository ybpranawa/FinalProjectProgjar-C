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
public class ChatServerConnection {
    private static ChatServerConnection connection = null;
    private static String host;
    private static int port;

    public static void dispose() {
        connection = null;
    }
    
    public static void connect() throws IOException {
        if (connection == null) {
            Socket socket = new Socket(getHost(), getPort());
            connection = new ChatServerConnection(socket);
        }
    }
    
    public static ChatServerConnection getInstance() {
        return connection;
    }
    
    private final Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    private ChatServerConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * @return the host
     */
    public static String getHost() {
        return host;
    }

    /**
     * @param aHost the host to set
     */
    public static void setHost(String aHost) {
        host = aHost;
    }

    /**
     * @return the port
     */
    public static int getPort() {
        return port;
    }

    /**
     * @param aPort the port to set
     */
    public static void setPort(int aPort) {
        port = aPort;
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
