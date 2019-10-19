/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import static Value.Global.*;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author billy
 */
public class Bullet extends EffectObject {

    private double direction;
    private LinkedList <Bullet> bullets;
    public Bullet(int x, int y, double direction) {
        super(x, y, SIZE_GRID / 10, SIZE_GRID / 10);
        this.direction = direction;
        bullets = new LinkedList<Bullet>();
    }
    public void launch(int x, int y, double direction){
       bullets.add(new Bullet(x,y, direction));
    }
    
    @Override
    public void update(){
        
    }
    
    @Override
    public void paint(Graphics g){
        
    }
}
