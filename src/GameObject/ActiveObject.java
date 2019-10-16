/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import static Value.Global.*;
import java.awt.Graphics;


/**
 *
 * @author user
 */
class ActiveObject extends GameObject {

    protected int speed;
    protected int direction;

    //constructor
    public ActiveObject(int x, int y, int width, int height, int speed) {
        super(x, y, width, height);
        this.direction = RIGHT;
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics g) {

    }
}
