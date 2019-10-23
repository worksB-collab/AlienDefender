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
        delay = new DelayCounter(10);
        upgradeStage = 0;
    }
    
    public void upgrade(){
        if(upgradeStage==2){
            return;
        }
        upgradeStage++;
        upgrade++;
    }
    
    public LinkedList getRange(){
        return null;
    }
    
    public int getUpgradeStage(){
        return upgradeStage;
    }

    public void detection(LinkedList<Alien> aliens) {
        for (Point range : range) {
            for(int i = 0; i <aliens.size() ; i++)
            if (aliens.get(i).getX() + SIZE_GRID - DEVIATION >= range.getX() &&
                    aliens.get(i).getX() + DEVIATION <= range.getX() + SIZE_GRID&&
                    aliens.get(i).getY() + SIZE_GRID - DEVIATION >= range.getY() &&
                    aliens.get(i).getY() + DEVIATION <= range.getY() + SIZE_GRID) {
                changeDirection(aliens.get(i));
                attack(aliens.get(i));
                return;
            }
        }
    }

    public double getAttack() {
        return attack;
    }

    public void changeDirection(Alien alien) {
        double h = ((alien.getY()+alien.getY()+SIZE_GRID)/2 - this.getY());
        double w = ((alien.getX()+alien.getX()+SIZE_GRID)/2 - this.getX());
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
            bullets.add(new Bullet(x, y, alien, this, direction, speed));
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
       for(int i = 0 ; i<bullets.size() ; i++){
           bullets.get(i).update();
           if(bullets.get(i).isReached())
               bullets.remove(i);
       }
    }

    @Override
    public void paint(Graphics g) {

    }

}
