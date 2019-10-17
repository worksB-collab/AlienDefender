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
                if (Math.abs(i + j) <= TOWER1_ATKRANGE) {
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
                System.out.println("555");
            }
        }
    }

    public int getAttack() {
        return attack;
    }

    public int getDirection() {
        return direction;
    }

    public void changeDirection(Alien alien) {
        direction = (int) (Math.pow(Math.tan(Math.abs(alien.getX() - this.getX())
                / Math.abs(alien.getY() - this.getY())), -1));
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
