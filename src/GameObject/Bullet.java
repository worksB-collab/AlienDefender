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
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author billy
 */
public class Bullet extends EffectObject {

    private BufferedImage img;
    private double direction;
    private int alienX;
    private int alienY;
    private int speedX;
    private int speedY;
    private int updateCount;
    private int towerNum;
//    private LinkedList <Bullet> bullets;

    public Bullet(int x, int y, Alien alien, Tower tower) {
        super(x, y, SIZE_GRID, SIZE_GRID);
        ImageController irc = ImageController.genInstance();
        img = irc.tryGetImage(Path.Image.BULLET1);
        this.direction = direction;
        alienX = alien.getX();
        alienY = alien.getY();
        towerNum = tower.getTowerNum();
        launch();
    }

    public boolean isReached() {
        if (updateCount >= 20) {
            return true;
        }
        return false;
    }

    public void launch() {
//        x += (alienX - x) / Math.sqrt(Math.pow(alienX - x, 2) + Math.pow(alienX - x, 2));
//        y += (alienY - y) / Math.sqrt(Math.pow(alienY - y, 2) + Math.pow(alienY - y, 2));
        speedX = (alienX - x) / 20;
        speedY = (alienY - y) / 20;
        updateCount = 0;
    }

    @Override
    public void update() {
        x += speedX;
        y += speedY;
        updateCount++;
    }

    @Override
    public void paint(Graphics g) {
        int dx = 65 * towerNum;
        g.drawImage(img, x, y, x + width, y + height,
                dx, 0, dx + SIZE_OBJECT, SIZE_OBJECT, null);
    }

}
