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
public class Response implements Serializable{
    private final int responseCode;
    private final String responseString;
    
    public Response(int responseCode, String responseString) {
        this.responseCode = responseCode;
        this.responseString = responseString;
    }

    /**
     * @return the responseCode
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * @return the responseString
     */
    public String getResponseString() {
        return responseString;
    }
}
