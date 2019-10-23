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
public abstract class ActiveObject extends GameObject {

    private float speed;
    protected float direction;

    //constructor
    public ActiveObject(float x, float y, float width, float height, float speed) {
        super(x, y, width, height);
        this.direction = RIGHT;
    }

    public double getDirection() {
        return direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void paint(Graphics g);
}
