/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Controller.ImageController;
import Value.Global;
import Value.Path;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author billy
 */
public class TowerHelper {
    private BufferedImage img;
    private int actorPosition;

    public TowerHelper(int actor) {    
        img = getActor(actor);         
        actorPosition = actor % 6;   
    }

    private BufferedImage getActor(int actor) { 
        ImageController irc = ImageController.genInstance();
        if (actor >= 0 && actor < 6) {  
            return irc.tryGetImage(Path.Image.ALIEN1);
        }
        if (actor < 12) {         
            return irc.tryGetImage(Path.Image.ALIEN1); // not yet updated
        }
        return null;
    }
                                                             
    public void paint(Graphics g, int x, int y, int width, int height, int act){
        if(img == null){    
            return;                                               
        }        
        
        int dy = 65 * (actorPosition); 
        g.drawImage(img, x, y, x + width, y + height,
                act * Global.SIZE_ALIEN, dy, 
                65 + act * Global.SIZE_ALIEN, dy + Global.SIZE_ALIEN, null);  
    }
}


