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
    private float alienX;
    private float alienY;
    private float speedX;
    private float speedY;
    private int updateCount;
    private int towerNum;
    private Alien alien;
    private float speed;

    public Bullet(float x, float y, Alien alien, Tower tower, float direction, float speed) {
        super(x, y, SIZE_GRID, SIZE_GRID);
        this.alien = alien;
        ImageController irc = ImageController.genInstance();
        img = irc.tryGetImage(Path.Image.BULLET1);
        this.direction = direction;
        alienX = alien.getX();
        alienY = alien.getY();
        towerNum = tower.getTowerNum();
        setSpeed(speed);
        launch();
    }

    public boolean isReached() {
        if (updateCount >= 100) {
            return true;
        }
        return false;
    }
    
        public float getSpeed() {
        return speed;
    }
        public void setSpeed(float speed){
            this.speed = speed;
        }

    public void launch() {
        updateCount = 0;
        float dX = alienX - super.getX();
        float dY = alienY - super.getY();
        if (dX != 0 || dY != 0) {
            float rateX = (dX) / (float)(Math.sqrt(Math.pow(dX, 2) +  Math.pow(dY, 2)));
            float rateY = (dY) / (float)(Math.sqrt(Math.pow(dX, 2) +  Math.pow(dY, 2)));
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
        super.setX(super.getX()+speedX);
        super.setY(super.getY()+speedY);
        updateCount++;
    }

    @Override
    public void paint(Graphics g) {
        int dx = 65 * towerNum;
        Graphics2D g2 = (Graphics2D) g.create();
        g2.rotate(direction * Math.PI / 180, (super.getX() + super.getX() + width) / 2, (super.getY() + super.getY() + height) / 2);
        g2.drawImage(img, (int)super.getX(), (int)super.getY(), (int)(super.getX() + width), (int)(super.getY() + height),
                dx, 0, dx + (int)SIZE_OBJECT, (int)SIZE_OBJECT, null);
//        
//        g2.rotate(direction * Math.PI / 180,150 + width / 2,150 + height / 2);
//         g2.drawImage(img, 150, 150, 150 + width, 150 + height,
//                dx, 0, dx + SIZE_OBJECT, SIZE_OBJECT, null);
    }

}
