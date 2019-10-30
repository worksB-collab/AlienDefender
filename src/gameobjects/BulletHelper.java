/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import controllers.ImageController;
import static values.Global.SIZE_OBJECT;
import values.Path;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author billy
 */
public class BulletHelper {

    private BufferedImage img, boom;

    public BulletHelper(int towerNum) {
        ImageController irc = ImageController.genInstance();
        img = irc.tryGetImage(Path.Image.BULLET2);
        boom = irc.tryGetImage(Path.Image.BOOM1);
    }

    public void paint(Graphics g, float x, float y, float width, float height, int towerNum, float direction) {
        int dx = 65 * towerNum;
        Graphics2D g2 = (Graphics2D) g.create();
        g2.rotate(direction * Math.PI / 180, (x + x + width) / 2, (y + y + height) / 2);
        g2.drawImage(img, (int) x, (int) y, (int) (x + width), (int) (y + height),
                dx, 0, dx + (int) SIZE_OBJECT, (int) SIZE_OBJECT, null);
    }

    public void paintBoom(Graphics g, float x, float y, float width, float height, int act, int towerNum, float direction) {
        int dx = 65 * (act%5);
        
        g.drawImage(boom, (int) x, (int) y, (int) (x + width), (int) (y + height),
                dx, 0, dx + (int) SIZE_OBJECT, (int) SIZE_OBJECT, null);
    }
}
