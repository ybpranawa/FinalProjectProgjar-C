/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintar.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fendy
 */
public class AduPintar {
    public static void main(String args[]) {
        String host = "192.168.0.103";
        int port = 1111;
        
        try {
            ServerConnection.setHost(host);
            ServerConnection.setPort(port);
            ServerConnection.getInstance();
            
            Login loginForm = new Login(null);
            loginForm.showForm();
        } catch (IOException ex) {
            System.out.println("Cannot connect to server!");
            //Logger.getLogger(AduPintar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
