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
public abstract class EffectObject extends GameObject{
    
    public EffectObject(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    
    @Override
    public abstract void update();
    
    @Override
    public abstract void paint(Graphics g);
}
