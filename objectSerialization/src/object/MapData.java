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
public class MapData implements Serializable {
    private String[][] map;
    
    public MapData(String[][] map) {
        this.map = map;
    }

    /**
     * @return the map
     */
    public String[][] getMap() {
        return map;
    }
}
