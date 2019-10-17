/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Controller.CommandSolver;
import Controller.CommandSolver.MouseCommandListener;
import Controller.CommandSolver.MouseState;
import Controller.ImageController;
import GameObject.Button;
import GameObject.Button.ButtonListener;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class TowerSelectWindow extends PopUpWindow{
    private ImageController imageController;
    private BufferedImage image;
    private LinkedList<Button> buttonList;
    private Object tower;
    private boolean isEnd;
    
    public TowerSelectWindow(int x, int y, int width, int height) {
        super(x, y, width, height);
        imageController = ImageController.genInstance();
        buttonList = new LinkedList<Button>();
        
        super.mouseCommandListener = new MouseCommandListener(){
            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if(state == MouseState.RELEASED || state == MouseState.CLICKED){
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
        genButton();
    }
    @Override
    public Object getResult(){
        return tower;
    }
    
    @Override
    public boolean isEnd(){
        return isEnd;
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
        int w = width / 25;
        int h = height / 25;
        int x0 = 0;
        int y0 = 0;
        
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                g.drawImage(image, x + x0, y + y0, 25, 25, null);
                x0 += 25;
            }
            x0 = 0;
            y0 += 25;
        }
        for(Button btn : buttonList){
            btn.paint(g);
        }
    }
    
    private void genButton(){
        BufferedImage img[] = { imageController.tryGetImage("/Resources/Images/Label/Tower_Icon1.png"),
                                imageController.tryGetImage("/Resources/Images/Label/Tower_Icon2.png"),
                                imageController.tryGetImage("/Resources/Images/Label/Tower_Icon3.png"),
                                imageController.tryGetImage("/Resources/Images/Label/Tower_Icon4.png"),
                                imageController.tryGetImage("/Resources/Images/Label/Tower_Icon5.png"),
                                imageController.tryGetImage("/Resources/Images/Label/Exit.png")};
        ButtonListener buttonListener[] = new ButtonListener[6];
        
        buttonListener[0] = new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                
            }

            @Override
            public void hover(int x, int y) {
                
            }
        
        };
        
        buttonListener[1] = new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                
            }

            @Override
            public void hover(int x, int y) {
                
            }
        
        };
        
        buttonListener[2] = new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                
            }

            @Override
            public void hover(int x, int y) {
                
            }
        
        };
        
        buttonListener[3] = new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                
            }

            @Override
            public void hover(int x, int y) {
                
            }
        
        };
        
        buttonListener[4] = new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                
            }

            @Override
            public void hover(int x, int y) {
                
            }
        
        };
        
        buttonListener[5] = new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                isEnd = true;
            }

            @Override
            public void hover(int x, int y) {
                
            }
        
        };
        
        int dx = 10;
        int dy = 5;
        int spece = 10;
        int x0 = x + dx;
        int y0 = y + dy;
        int w = (width - (2 * dx ) - (spece * (img.length - 1)) ) / img.length ;
        int h = height - (2 * dy);
        for(int i = 0; i < img.length; i++){
            Button button = new Button(x0, y0, w, h,img[i], img[i], img[i]);
            button.setButtonListener(buttonListener[i]);
            x0 += (w + dx);
            buttonList.add(button);
        }
    }
}
