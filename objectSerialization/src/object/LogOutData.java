/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.Serializable;

/**
 *
 * @author fendy
 */
public class LogOutData implements Serializable {
    private String request;
    
    public LogOutData() {
        this.request = "LogOut";
    }

    /**
     * @return the request
     */
    public String getRequest() {
        return request;
    }
}
