/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import java.awt.Graphics;
import Value.Global;
import static Value.Global.*;

/**
 *
 * @author billy
 */
public class Alien1 extends Alien {

    private AlienHelper aHelper;
    private int ACT[] = {0, 1, 2, 1};
    private int DEAD[] = {3,4};
    
    

    public Alien1(int x, int y) {
        super(x, y, SIZE_GRID, SIZE_GRID ,100 , 1*Global.SPEED); //x, y, width, height, hp, speed
        alienNum = 1;
        aHelper = new AlienHelper(alienNum);
        act = 0;
    }
    
    @Override
    public void paint(Graphics g) { 
        if(isDead()){
            aHelper.dead(g, x, y, alienNum);
            return;
        }
        aHelper.paint(g, x, y, width, height, ACT[act], hp);
    }
} 
