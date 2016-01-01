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
public class RequestFriendData implements Serializable {
    private final String returntype;
    
    public RequestFriendData(String ret) {
        this.returntype = ret;
    }

    /**
     * @return the returntype
     */
    public String getReturntype() {
        return returntype;
    }
}
