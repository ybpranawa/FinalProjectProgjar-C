/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintar.client;

/**
 *
 * @author fendy
 */
public class Manager {
    private static ChatListener chatListener;
    private static GameListener gameListener;

    /**
     * @return the chatListener
     */
    public static ChatListener getChatListener() {
        return chatListener;
    }

    /**
     * @param aChatListener the chatListener to set
     */
    public static void setChatListener(ChatListener aChatListener) {
        chatListener = aChatListener;
    }

    /**
     * @return the gameListener
     */
    public static GameListener getGameListener() {
        return gameListener;
    }

    /**
     * @param aGameListener the gameListener to set
     */
    public static void setGameListener(GameListener aGameListener) {
        gameListener = aGameListener;
    }
    
    
}
