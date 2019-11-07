/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import controllers.DelayCounter;
import java.awt.Graphics;
import values.Global;
import static values.Global.*;

/**
 *
 * @author billy
 */
public class Alien3 extends Alien {

    private AlienHelper aHelper;
    private int ACT[] = {0, 1, 2, 1};
    private int DEAD[] = {3, 4};
    private DelayCounter delay;
    private int deadDelay;


    public Alien3(float x, float y) {
        super(x, y, SIZE_GRID, SIZE_GRID, 170, ALIEN_SPEED[2]); //x, y, width, height, hp, speed
        alienNum = 2;
        aHelper = new AlienHelper(alienNum);
        act = 0;
        delay = new DelayCounter(5);
        deadDelay = 0;
        super.setMoney((int)super.getHp()/20);
    }
    

    
    public int getDeadDelay(){
        return deadDelay;
    }

    @Override
    public void paint(Graphics g) {
        aHelper.paint(g, super.getX(), super.getY(), width, height, ACT[act], super.getHp());
    }

//    @Override
//    public void paintDead(Graphics g) {
//        if (delay.update()) {
//            aHelper.dead(g, super.getX(), super.getY(), alienNum);
//            deadDelay ++;
//            
//        }
//    }
}
