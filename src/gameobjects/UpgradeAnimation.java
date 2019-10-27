/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import controllers.DelayCounter;
import controllers.ImageController;
import values.Global.*;
import static values.Global.*;
import values.Path;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author billy
 */
public class UpgradeAnimation extends EffectObject {

    private BufferedImage img;
    private DelayCounter delay;

    public UpgradeAnimation(float x, float y) {
        super(x, y, SIZE_GRID, SIZE_GRID);
        img = getImage();
        delay = new DelayCounter(2);
    }

    private BufferedImage getImage() {
        ImageController irc = ImageController.genInstance();
        return irc.tryGetImage(Path.Image.UPGRADE);
    }

    @Override
    public void update() {
    }

    @Override
    public void paint(Graphics g) {
        if (img == null) {
            return;
        }
        int dy = 0;
        for (int act = 0; act < 5; act++) {
            if (delay.update()) {
                g.drawImage(img, (int) super.getX(), (int) super.getY(),
                        (int) (super.getX() + super.getWidth()), (int) (super.getY() + super.getHeight()),
                        (int) (act * SIZE_OBJECT), dy,
                        65 + (int) (act * SIZE_OBJECT), (int) (dy + SIZE_OBJECT), null);
            }
        }
    }
}
