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
 * @author user
 */
public class AlienHelper {

    private BufferedImage img;
    private int actorPosition;

    public AlienHelper(int alien) {
        img = getActor(alien);
        actorPosition = alien % 6;
    }

    private BufferedImage getActor(int alien) {
        ImageController irc = ImageController.genInstance();
        if (alien >= 0 && alien < 6) {
            return irc.tryGetImage(Path.Image.ALIEN1);
        }
        if (alien < 12) {
            return irc.tryGetImage(Path.Image.ALIEN1); // not yet updated
        }
        return null;
    }

    public void paint(Graphics g, int x, int y, int width, int height, int act) {
        if (img == null) {
            return;
        }

        int dy = 65 * (actorPosition);
        g.drawImage(img, x, y, x + width, y + height,
                act * Global.SIZE_OBJECT, dy,
                65 + act * Global.SIZE_OBJECT, dy + Global.SIZE_OBJECT, null);
    }

}
