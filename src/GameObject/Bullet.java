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
    private double speedX;
    private double speedY;
    private int updateCount;
    private int towerNum;
    private Alien alien;
    private int speed;

    public Bullet(int x, int y, Alien alien, Tower tower, double direction, int speed) {
        super(x, y, SIZE_GRID, SIZE_GRID);
        this.alien = alien;
        ImageController irc = ImageController.genInstance();
        img = irc.tryGetImage(Path.Image.BULLET1);
        this.direction = direction;
        alienX = alien.getX();
        alienY = alien.getY();
        towerNum = tower.getTowerNum();
        this.speed = speed;
        launch();
    }

    public boolean isReached() {
        if (updateCount >= 100) {
            return true;
        }
        return false;
    }

    public void launch() {
        updateCount = 0;
        int dX = alienX - x;
        int dY = alienY - y;
        if (dX != 0 || dY != 0) {
            double rateX = (dX) / (Math.sqrt(Math.pow(dX, 2) +  Math.pow(dY, 2)));
            double rateY = (dY) / (Math.sqrt(Math.pow(dX, 2) +  Math.pow(dY, 2)));
            speedX = rateX * speed;
            speedY = rateY * speed;
            System.out.println("speed = "+ speed);
            System.out.println("ratX" + rateX +"/ rateY" +rateY);
            System.out.println("speedX" +speedX +"/ speedY" + speedY);
            return;
        }
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
        Graphics2D g2 = (Graphics2D) g.create();
        g2.rotate(direction * Math.PI / 180, (x + x + width) / 2, (y + y + height) / 2);
        g2.drawImage(img, x, y, x + width, y + height,
                dx, 0, dx + SIZE_OBJECT, SIZE_OBJECT, null);
        System.out.println("x: " +x + "/ y: " + y);
//        
//        g2.rotate(direction * Math.PI / 180,150 + width / 2,150 + height / 2);
//         g2.drawImage(img, 150, 150, 150 + width, 150 + height,
//                dx, 0, dx + SIZE_OBJECT, SIZE_OBJECT, null);
    }

}
