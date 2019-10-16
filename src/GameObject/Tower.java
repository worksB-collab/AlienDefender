/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import java.awt.Graphics;

/**
 *
 * @author user
 */
public class Tower extends ActiveObject {

    protected int attack;
    protected int attackRangeRight;

    public Tower(int x, int y, int width, int height, int attack, int speed) {
        super(x, y, width, height, speed);
        this.width = width;
        this.height = height;
        this.attack = attack;
        this.speed = speed;
    }

    public int getAttack() {
        return attack;
    }

    public int getDirection() {
        return direction;
    }

    public void changeDirection(int direction) {
        this.direction = direction;
    }

    public void attack(Alien alien) {
    }

    @Override
    public void update() {
    }

    @Override
    public void paint(Graphics g) {

    }

}
