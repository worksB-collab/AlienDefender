/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value;

import java.awt.Color;
import java.awt.Font;

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
    public static final int SPEED = 120 / UPDATE_PER_SEC;
    public static final int ANIMATE_DELAY_PER_SEC = UPDATE_PER_SEC / 15;

    //Delay
    public static final int BUTTON_UPDATE_DELAY = 3;


    //Grid
    public static final int SIZE_GRID = 32;
    
    ///Objects
    public static final int SIZE_OBJECT = 65;
    public static final int DEVIATION = SIZE_GRID/11; // deviation for grid judgement
    public static final int SIZE_BLOODLINE = SIZE_OBJECT/15;
    
    //Tower
    public static final int TOWER0_ATKRANGE = 1 * SIZE_GRID;
    public static final int TOWER1_ATKRANGE = 2 * SIZE_GRID;
    public static final int TOWER2_ATKRANGE = 3 * SIZE_GRID;
    public static final int TOWER3_ATKRANGE = 4 * SIZE_GRID;
    public static final int TOWER4_ATKRANGE = 5 * SIZE_GRID;

    //Cailbaration
    public static int MIN_PICTURE_SIZE = 32;
    public static final int STANDAR_MIN_SIZE = 32;
    public static int FRAME_WIDTH = 1024;
    public static int FRAME_HEIGHT = 768;

    //direction
    public static final int UP = 0;
    public static final int DOWN = 180;
    public static final int LEFT = 270;
    public static final int RIGHT = 90;
    
    //Font
    public static final Font FONT_00 = new Font(Font.DIALOG, Font.PLAIN, 100);
    public static final Font FONT_01 = new Font(Font.DIALOG, Font.PLAIN, 120);
    public static final Font FONT_INPUT = new Font(Font.DIALOG, Font.PLAIN, 30);
    public static final Font FONT_SCORE = new Font(Font.DIALOG, Font.PLAIN, 60);
    public static final Color DEFAULT_FONT_COLOR = Color.BLACK;
    
    //Key
    public static final int KEY_ENTER = 6666;
    public static final int KEY_BACK_SPACE = 6667;

}
