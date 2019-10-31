/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import controllers.AudioController;
import controllers.DelayCounter;
import controllers.ImageController;
import static values.Global.*;
import values.Path;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;

/**
 *
 * @author billy
 */
public class UpgradeAnimation extends EffectObject {

    private BufferedImage img;
    private DelayCounter delay;
    private Clip audio;
    private AudioController audioController;

    public UpgradeAnimation(float x, float y) {
        super(x, y, SIZE_GRID, SIZE_GRID);
        img = getImage();
        delay = new DelayCounter(5);
        audioController = AudioController.genInstance();
        audio = audioController.tryGetAudio(Path.Audios.Sounds.Effect.UPGRADE);
    }

    private BufferedImage getImage() {
        ImageController irc = ImageController.genInstance();
        return irc.tryGetImage(Path.Image.UPGRADE);
    }

    @Override
    public void update() {
        if (audio.getMicrosecondLength()== audio.getMicrosecondPosition()) {
            audio.close();
            audio = audioController.tryGetAudio(Path.Audios.Sounds.Effect.UPGRADE);
        }
    }

    @Override
    public void paint(Graphics g) {
        if (img == null) {
            return;
        }
        audio.start();
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
