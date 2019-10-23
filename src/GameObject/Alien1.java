/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Controller.DelayCounter;
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
    private int DEAD[] = {3, 4};
    private DelayCounter delay;
    private int deadDelay;

    public Alien1(float x, float y) {
        super(x, y, SIZE_GRID, SIZE_GRID, 100, 1 * Global.SPEED); //x, y, width, height, hp, speed
        alienNum = 1;
        aHelper = new AlienHelper(alienNum);
        act = 0;
        delay = new DelayCounter(5);
        deadDelay = 0;
    }
    
    public int getDeadDelay(){
        return deadDelay;
    }

    @Override
    public void paint(Graphics g) {
        aHelper.paint(g, super.getX(), super.getY(), width, height, ACT[act], super.getHp());
    }

    @Override
    public void paintDead(Graphics g) {
        if (delay.update()) {
            aHelper.dead(g, super.getX(), super.getY(), alienNum);
            deadDelay ++;
            
        }
    }
}
