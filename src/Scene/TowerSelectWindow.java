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
import Controller.TowerController;
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
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class TowerSelectWindow extends TowerPopUpWindow{
    public static final String TYPE = "TowerSelectWindow";
    private ImageController imageController;
    private BufferedImage image;
    private LinkedList<Button> buttonList;
    private Tower tower;
    private LinkedList<Point> towerRange;
    private boolean isEnd;
    
    public TowerSelectWindow(float x, float y, float width, float height, TowerController towerController) {
        super(6 * Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE, width, height, towerController);
        imageController = ImageController.genInstance();
        buttonList = new LinkedList<Button>();
        isEnd = false;
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
                if(state == MouseState.MOVED){
                    int x = e.getX();
                    int y = e.getY();
                    for(Button btn : buttonList){
                        if(btn.isRange(x, y)){
                            btn.hover(x, y);
                            break;
                        }
                    }
                }
            }    
        };
        genButton(x, y);
    }

    public Tower getResult(){
        return tower;
    }
    
    @Override
    public boolean isEnd(){
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
        image = imageController.tryGetImage("/Resources/Images/Label/Tower_generate_Label5.png");
        float w = width / Global.MIN_PICTURE_SIZE;
        float h = height / Global.MIN_PICTURE_SIZE;
        float x0 = 0;
        float y0 = 0;
        g.drawImage(image, (int)super.getX(), (int)super.getY(), (int)width, (int)height, null);
//        for(int i = 0; i < h; i++){
//            for(int j = 0; j < w; j++){
//                g.drawImage(image, (int)(super.getX() + x0), (int)(super.getY() + y0), (int)Global.MIN_PICTURE_SIZE, (int)Global.MIN_PICTURE_SIZE, null);
//                x0 += Global.MIN_PICTURE_SIZE;
//            }
//            x0 = 0;
//            y0 += Global.MIN_PICTURE_SIZE;
//        }
        for(Button btn : buttonList){
            btn.paint(g);
        }
        if(towerRange != null){
            Graphics2D k = (Graphics2D)g;
            k.setStroke(new BasicStroke(2f));
            k.setColor(Color.ORANGE);
            for(int i = 0; i < towerRange.size(); i++){
                Point p = towerRange.get(i);
                k.drawRect((int)p.getX(), (int)p.getY(), (int)Global.MIN_PICTURE_SIZE, (int)Global.MIN_PICTURE_SIZE);
            }
            k.setColor(Color.BLACK);
        }
    }
    
    private void genButton(float x0, float y0){
        BufferedImage img[] = { imageController.tryGetImage("/Resources/Images/Label/Tower_Icon1.png"),
                                imageController.tryGetImage("/Resources/Images/Label/Tower_Icon2.png"),
                                imageController.tryGetImage("/Resources/Images/Label/Tower_Icon3.png"),
                                imageController.tryGetImage("/Resources/Images/Label/Tower_Icon4.png"),
                                imageController.tryGetImage("/Resources/Images/Label/Tower_Icon5.png"),
                                imageController.tryGetImage("/Resources/Images/Label/Exit.png")};
        ButtonListener buttonListener[] = new ButtonListener[6];
        TowerController towerController = super.getTowerController();
        buttonListener[0] = new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                tower = new Tower1(x0, y0);
                towerController.getTowers().add(tower);
                isEnd = true;
            }

            @Override
            public void hover(int x, int y) {
                towerController.genRange(new Point((int)x0, (int)y0), 0);
                towerRange = towerController.getRange();
            }
        
        };
        
        buttonListener[1] = new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                tower = new Tower1(x0, y0);
                towerController.getTowers().add(tower);
                isEnd = true;
            }

            @Override
            public void hover(int x, int y) {
                towerController.genRange(new Point((int)x0, (int)y0), 1);
                towerRange = towerController.getRange();
            }
        
        };
        
        buttonListener[2] = new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                tower = new Tower1(x0, y0);
                towerController.getTowers().add(tower);
                isEnd = true;
            }

            @Override
            public void hover(int x, int y) {
                towerController.genRange(new Point((int)x0, (int)y0), 2);
                towerRange = towerController.getRange();
            }
        
        };
        
        buttonListener[3] = new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                tower = new Tower1(x0, y0);
                towerController.getTowers().add(tower);
                isEnd = true;
            }

            @Override
            public void hover(int x, int y) {
                towerController.genRange(new Point((int)x0, (int)y0), 3);
                towerRange = towerController.getRange();
            }
        
        };
        
        buttonListener[4] = new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                tower = new Tower1(x0, y0);
                towerController.getTowers().add(tower);
                isEnd = true;
            }

            @Override
            public void hover(int x, int y) {
                towerController.genRange(new Point((int)x0, (int)y0), 4);
                towerRange = towerController.getRange();
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
        
        float dx = 10f;
        float dy = 5f;
        float spece = 10f;
        float x1 = super.getX() + dx;
        float y1 = super.getY() + dy;
        float w = (width - (2f * dx ) - (spece * (img.length - 1)) ) / img.length ;
        float h = height - (2f * dy);
        for(int i = 0; i < img.length; i++){
            Button button = new Button(x1, y1, w, h,img[i]);
            button.setButtonListener(buttonListener[i]);
            x1 += (w + dx);
            buttonList.add(button);
        }
    }
}
