/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Controller.ImageController;
import Value.Global.*;
import static Value.Global.*;
import Value.Path;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author billy
 */
public class UpgradeAnimation extends EffectObject {

    private BufferedImage img;

    public UpgradeAnimation(float x, float y) {
        super(x, y, SIZE_GRID, SIZE_GRID);
        img = getImage();
    }

    private BufferedImage getImage() {
        ImageController irc = ImageController.genInstance();
        return irc.tryGetImage(Path.Image.UPGRADE);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paint(Graphics g) {
        if (img == null) {
            return;
        }
        int dy = 65 * (actorPosition);
        g.drawImage(img, (int)x, (int)y, (int)(x + width), (int)(y + height),
                (int)(act * SIZE_OBJECT), dy,
                65 + (int)(act * SIZE_OBJECT), (int)(dy + SIZE_OBJECT), null);
    }

}
