/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Controller.DelayCounter;
import Value.Global;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class Button extends GameObject{
    public interface ButtonListener{
        public void onClick(int x, int y);
        public void hover(int x, int y);
    }
    private BufferedImage rootImage;
    private BufferedImage clickImage;
    private BufferedImage hoverImage;
    private BufferedImage nowImage;
    private DelayCounter delayCounter;
    private ButtonListener buttonListener;
    private boolean isClicked;
    
    public Button(int x, int y , int width, int height,BufferedImage rootImage, BufferedImage clickImage, BufferedImage hoverImage){
        super(x, y, width, height);
        this.rootImage = rootImage;
        this.clickImage = clickImage;
        this.hoverImage = hoverImage;
        this.nowImage = rootImage;
        delayCounter = new DelayCounter(Global.BUTTON_UPDATE_DELAY);

    }
    
    public void setButtonListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }
    public boolean isRange(int x, int y){
        if(x < this.x || x > this.x + width){
            return false;
        }
        if(y < this.y || y > this.y + height){
            return false;
        }
        return true;
    }
    public void click(int x, int y){
        if(buttonListener == null){
            return ;
        }
        nowImage = clickImage;
        isClicked = true;
        buttonListener.onClick(x, y);
    }
    public void hover(int x, int y){
        if(buttonListener == null){
            return;
        }
        nowImage = hoverImage;
        buttonListener.hover(x, y);
    }
    public boolean getState(){
        return isClicked;
    }
    
    @Override
    public void update(){
        if(isClicked){
            nowImage = clickImage;
        }
        if(delayCounter.update()){
            nowImage = rootImage;
            isClicked = false;
        }
    }
    
    @Override
    public void paint(Graphics g){
        if(isClicked){
            g.setXORMode(Color.ORANGE);
        }
        g.drawImage(nowImage, x, y, width, height, null);
        g.drawRect(x, y, width, height);
        g.setPaintMode();
    }
    
    
}
