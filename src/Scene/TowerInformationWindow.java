/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Controller.CommandSolver;
import Controller.ImageController;import Controller.TowerController;
;
import GameObject.Button;
import GameObject.Button.ButtonListener;
import GameObject.Tower;
import GameObject.Tower1;
import Value.Global;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class TowerInformationWindow extends TowerPopUpWindow{
    private ImageController imageController;
    private BufferedImage image;
    private LinkedList<Button> buttonList;
    private LinkedList<Point> towerRange;
    private Tower tower;
    private boolean isEnd;
    public TowerInformationWindow(float x, float y, float width, float height, Tower tower) {
        super(4 * Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE, width, height, null);
        this.tower = tower;
        imageController = ImageController.genInstance();
        buttonList = new LinkedList<Button>();
        towerRange = new LinkedList();
        getButton(4 * Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE);
        isEnd = false;
        super.mouseCommandListener = new CommandSolver.MouseCommandListener(){
            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if(state == CommandSolver.MouseState.RELEASED || state == CommandSolver.MouseState.CLICKED){
                    int x = e.getX();
                    int y = e.getY();
                    for(Button btn : buttonList){
                        if(btn.isRange(x, y)){
                            btn.click(x, y);
                            break;
                        }
                    }
                }
            }    
        };
    }
    
    

    @Override
    public boolean isEnd() {
        return isEnd;
    }
     @Override
    public String getType(){
        return TYPE;
    }
    @Override
    public void update(){
        for(Button btn : buttonList){
            btn.update();
        }
    }
    
    @Override
    public void paint(Graphics g){
        //draw Tower Area
        towerRange = tower.getRange();
        Graphics2D k = (Graphics2D)g;
        k.setStroke(new BasicStroke(2f));
        k.setColor(Color.ORANGE);
        for(int i = 0; i < towerRange.size(); i++){
            Point p = towerRange.get(i);
            k.drawRect((int)p.getX(), (int)p.getY(), (int)Global.MIN_PICTURE_SIZE, (int)Global.MIN_PICTURE_SIZE);
        }
        k.setColor(Color.BLACK);

        
        
        //draw PopUpWindow
        image = imageController.tryGetImage("/Resources/Images/Label/Tower_generate_Label.png");
        float w = width / Global.MIN_PICTURE_SIZE;
        float h = height / Global.MIN_PICTURE_SIZE;
        int x0 = 0;
        int y0 = 0;
        
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                g.drawImage(image, (int)(super.getX() + x0), (int)(super.getY() + y0), (int)Global.MIN_PICTURE_SIZE, (int)Global.MIN_PICTURE_SIZE, null);
                x0 += Global.MIN_PICTURE_SIZE;
            }
            x0 = 0;
            y0 += Global.MIN_PICTURE_SIZE;
        }
        
        for(Button btn : buttonList){
            btn.paint(g);
        }
    }
    
    private void getButton(float x0, float y0){
        BufferedImage img = imageController.tryGetImage("/Resources/Images/Label/Exit.png");
        BufferedImage img2 = imageController.tryGetImage("/Resources/Images/Label/upgrade.png");
        
        Button button = new Button(x0 + 22 * Global.MIN_PICTURE_SIZE, y0, 2 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE, img);
        ButtonListener buttonListener = new Button.ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                isEnd = true;
            }

            @Override
            public void hover(int x, int y) {
                
            }
        
        };
        button.setButtonListener(buttonListener);
        
        Button upgradeButton = new Button(x0 + 20 * Global.MIN_PICTURE_SIZE, y0, 2 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE, img2);
        ButtonListener buttonListener2 = new Button.ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                tower.upgrade();
            }

            @Override
            public void hover(int x, int y) {
                
            }
        
        };
        upgradeButton.setButtonListener(buttonListener2);
        buttonList.add(button);
        buttonList.add(upgradeButton);

    }
    
}
