/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.Serializable;

/**
 *
 * @author Fendy
 */
public class SignUpData implements Serializable{
    private final String username;
    private final String name;
    private final String password;
    
    public SignUpData(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
}
