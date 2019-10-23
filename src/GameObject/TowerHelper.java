/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Controller.ImageController;
import static Value.Global.*;
import Value.Path;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
        actorPosition = actor % 5;
    }

    private BufferedImage getActor(int actor) {
        ImageController irc = ImageController.genInstance();
        if (actor >= 0 && actor < 5) {
            return irc.tryGetImage(Path.Image.TOWER1);
        }
        return null;
    }

    public void paint(Graphics g, int x, int y, int width, int height, double direction, int upgradeStage) {
        if (img == null) {
            return;
        }
        int dx = 65 * (actorPosition);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.rotate(direction*Math.PI/180, (x+x+width)/2, (y+y+height)/2);
        g2.drawImage(img, x, y, x + width, y + height,
                dx, upgradeStage*SIZE_OBJECT, dx + SIZE_OBJECT, SIZE_OBJECT+upgradeStage*SIZE_OBJECT, null);

    }
}
