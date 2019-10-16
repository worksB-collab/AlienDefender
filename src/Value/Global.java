/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value;

/**
 *
 * @author user
 */
public class Global {
    //Update 
    public static final int UPDATE_PER_SEC = 60;
    public static final int MILLISEC_PER_UPDATE = 1000 / UPDATE_PER_SEC;
    
    //Frame Limit
    public static final int FRAME_LIMIT = 60;
    public static final int LIMIT_DELAY_TIME = 1000 / FRAME_LIMIT;

    //SPEED
    public static final int SPEED = 120 / UPDATE_PER_SEC ;
    public static final int ANIMATE_DELAY_PER_SEC = UPDATE_PER_SEC / 15 ;

    //Delay
    public static final int BUTTON_UPDATE_DELAY = 60;

    //Alien
    public static final int SIZE_ALIEN =65; 
    
    //Grid
    public static final int SIZE_GRID =25;
    
    //direction
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
   
}
