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
public abstract class EffectObject extends GameObject {

    private float x, y, height, width;

    public EffectObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    //accessor
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void paint(Graphics g);
}
