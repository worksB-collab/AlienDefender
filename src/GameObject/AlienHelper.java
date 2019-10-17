/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Controller.DelayCounter;
import Controller.ImageController;
import Value.Global;
import static Value.Global.*;
import Value.Path;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class AlienHelper {

    private BufferedImage img;
    private int actorPosition;
    private DelayCounter delay;
    private LinkedList<Alien> aliens;

    public AlienHelper(int alien) {
        img = getActor(alien);
        actorPosition = alien % 6;
        delay = new DelayCounter(3);
        aliens = new LinkedList<Alien>();
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

    public void dead(Graphics g, int x, int y, int alien) {
        actorPosition = alien;
        switch (alien) {
            case 1:
                aliens.add(new Alien1(x, y));
                break;
        }
        for (int i = 0; i < 6; i++) {
            if (delay.update()) {
                g.drawImage(img, x, y, x + SIZE_GRID, y + SIZE_GRID,
                        (i % 2 + 3) * SIZE_OBJECT, actorPosition * SIZE_OBJECT,
                        (i % 2 + 4) * SIZE_OBJECT, SIZE_OBJECT + actorPosition * SIZE_OBJECT, null);
            }
        }
        aliens.removeFirst();
    }
}
