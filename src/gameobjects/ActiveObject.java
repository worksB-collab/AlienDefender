/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import static values.Global.*;
import java.awt.Graphics;

/**
 *
 * @author user
 */
public abstract class ActiveObject extends GameObject{

    private float speed;
    private int direction;

    //constructor
    public ActiveObject(float x, float y, float width, float height, float speed) {
        super(x, y, width, height);
        this.direction = RIGHT;
        setSpeed(speed);
    }

    public int getDirection() {
        return direction;
    }
 
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public void setDirection(int direction){
        this.direction = direction;
    }
    @Override
    public abstract void update();

    @Override
    public abstract void paint(Graphics g);
}
