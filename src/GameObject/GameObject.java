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
public  class GameObject{

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    //constructor
    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        setWidth(width);
        setHeight(height);
        
    }

    //setter

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //accessor
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void update() {

    }

    public void paint(Graphics g) {

    }
}
