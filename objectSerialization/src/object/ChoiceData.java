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
public class ChoiceData implements Serializable {
    private int choice;
    
    public ChoiceData(int choice) {
        this.choice = choice;
    }
    
    public int getChoiceData() {
        return choice;
    }
}
