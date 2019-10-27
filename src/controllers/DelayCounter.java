/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import values.Global;

/**
 *
 * @author user
 */
public class DelayCounter {
    private int delay;
    private int count;
    
    public DelayCounter(int delay){
        this.delay = delay * Global.ANIMATE_DELAY_PER_SEC;
        count = 0;
    }
    public boolean update(){
        if(count++ < delay){
            return false;
        }
        count = 0;
        return true;
    }
}
