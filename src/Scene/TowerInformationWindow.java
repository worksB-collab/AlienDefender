/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Controller.CommandSolver;
import Controller.ImageController;;
import GameObject.Button;
import GameObject.Button.ButtonListener;
import GameObject.Tower;
import GameObject.Tower1;
import Value.Global;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class TowerInformationWindow extends PopUpWindow{
    private ImageController imageController;
    private BufferedImage image;
    private LinkedList<Button> buttonList;
    private Tower tower;
    private boolean isEnd;
    public TowerInformationWindow(int x, int y, int width, int height, Tower tower) {
        super(4 * Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE, width, height);
        this.tower = tower;
        imageController = ImageController.genInstance();
        buttonList = new LinkedList<Button>();
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
        image = imageController.tryGetImage("/Resources/Images/Label/Tower_generate_Label.png");
        int w = width / Global.MIN_PICTURE_SIZE;
        int h = height / Global.MIN_PICTURE_SIZE;
        int x0 = 0;
        int y0 = 0;
        
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                g.drawImage(image, x + x0, y + y0, Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE, null);
                x0 += Global.MIN_PICTURE_SIZE;
            }
            x0 = 0;
            y0 += Global.MIN_PICTURE_SIZE;
        }
        
        for(Button btn : buttonList){
            btn.paint(g);
        }
    }
    
    private void getButton(int x, int y){
        BufferedImage img = imageController.tryGetImage("/Resources/Images/Label/Exit.png");
        Button button = new Button(x + 22 * Global.MIN_PICTURE_SIZE, y, 2 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE, img, img, img);
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
        buttonList.add(button);

    }
}
