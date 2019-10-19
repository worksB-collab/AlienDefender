/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import static Value.Global.*;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class Tower extends ActiveObject {

    protected int attack;
    protected int attackRangeRight;
    protected LinkedList<Point> range;

    public Tower(int x, int y, int width, int height, int attack, int speed) {
        super(x, y, width, height, speed);
        this.width = width;
        this.height = height;
        this.attack = attack;
        this.speed = speed;
        genRange();
    }

    public LinkedList genRange() {
        range = new LinkedList<Point>();
        for (int i = -TOWER1_ATKRANGE; i < TOWER1_ATKRANGE; i++) {
            for (int j = -TOWER1_ATKRANGE; j < TOWER1_ATKRANGE; j++) {
                if (Math.abs(i) + Math.abs(j) <= TOWER1_ATKRANGE) {
                    range.add(new Point(x + i, y + j));
                }
                j += 24;
            }
            i += 24;
        }
        for (int i = TOWER1_ATKRANGE; i >= 0; i--) {
            for (int j = TOWER1_ATKRANGE; j >= 0; j--) {
                if (Math.abs(i + j) <= TOWER1_ATKRANGE) {
                    range.add(new Point(x + i, y + j));
                }
                j -= 24;
            }
            i -= 24;
        }
        for (Point range : range) {
            System.out.println(range);
        }
        return range;
    }

    public void detection(Alien alien) {
        for (Point range : range) {
            if (alien.getX() >= range.getX() - 2 && alien.getX() <= range.getX() + 2
                    && alien.getY() >= range.getY() - 2 && alien.getY() <= range.getY() + 2) {
                changeDirection(alien);
                attack(alien);
                System.out.println("Attack");
            }
        }
    }

    public int getAttack() {
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
            }else if(w>0 && h > 0){
                direction= 90 + Math.abs(Math.atan((h)
                        / (w)) * 180 / Math.PI);
            }else if(w<0 && h < 0){
                direction= 270 + Math.abs(Math.atan((h)
                        / (w)) * 180 / Math.PI);
            }else{
                direction= 270 - Math.abs(Math.atan((h)
                        / (w)) * 180 / Math.PI);
            }
        }
        System.out.println(direction);
    }

    public void attack(Alien alien) {
        alien.isAttacked(this);
    }

    @Override
    public void update() {
    }

    @Override
    public void paint(Graphics g) {

    }

}
