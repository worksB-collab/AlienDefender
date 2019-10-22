/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Controller.DelayCounter;
import static Value.Global.*;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class Tower extends ActiveObject {

    protected double attack;
    protected LinkedList<Point> range;
    protected LinkedList<Bullet> bullets;
    protected int towerNum;
    protected int towerRange;
    protected DelayCounter delay;
    protected int upgradeStage;
    protected int upgrade;

    public Tower(int x, int y, int width, int height, int attack, int speed) {
        super(x, y, width, height, speed);
        this.width = width;
        this.height = height;
        this.attack = attack;
        this.speed = speed;
        bullets = new LinkedList<Bullet>();
        delay = new DelayCounter(30);
        upgradeStage = 0;
    }
    public LinkedList getRange(){
        return null;
    }
    public void upgrade(){
        if(upgradeStage==2){
            return;
        }
        upgradeStage++;
        upgrade++;
    }
    
    public int getUpgradeStage(){
        return upgradeStage;
    }

    public void detection(Alien alien) {
        for (Point range : range) {
            if (alien.getX() + SIZE_GRID - DEVIATION >= range.getX() &&
                    alien.getX() + DEVIATION <= range.getX() + SIZE_GRID&&
                    alien.getY() + SIZE_GRID - DEVIATION >= range.getY() &&
                    alien.getY() + DEVIATION <= range.getY() + SIZE_GRID) {
                changeDirection(alien);
                attack(alien);
            }
        }
    }

    public double getAttack() {
        return attack;
    }

    public void changeDirection(Alien alien) {
        double h = (alien.getY() - this.getY());
        double w = (alien.getX() - this.getX());
        if (h == 0) {
            if (w > 0) {
                direction = 90;
            } else {
                direction = 270;
            }
        }
        if (w == 0) {
            if (h > 0) {
                direction = 180;
            } else {
                direction = 0;
            }
        }
        if (w != 0 && h != 0) {
            if (w > 0 && h < 0) {
                direction = 90 - Math.abs(Math.atan((h)
                        / (w)) * 180 / Math.PI);
            } else if (w > 0 && h > 0) {
                direction = 90 + Math.abs(Math.atan((h)
                        / (w)) * 180 / Math.PI);
            } else if (w < 0 && h < 0) {
                direction = 270 + Math.abs(Math.atan((h)
                        / (w)) * 180 / Math.PI);
            } else {
                direction = 270 - Math.abs(Math.atan((h)
                        / (w)) * 180 / Math.PI);
            }
        }
    }

    public void attack(Alien alien) {
        if(delay.update()){
            alien.isAttacked(this);
            bullets.add(new Bullet(x, y, alien, this));
        }
    }

    public int getTowerNum() {
        return towerNum;
    }
    
    public LinkedList<Bullet> getBullets(){
        return bullets;
    }

    @Override
    public void update() {
       
    }

    @Override
    public void paint(Graphics g) {

    }

}
