/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Controller.DelayCounter;
import Value.DrawStringPoint;
import Value.Global;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    private String text;
    private Font font;
    private DrawStringPoint point;
    private boolean isClicked;
    
    public Button(int x, int y , int width, int height,BufferedImage rootImage, BufferedImage clickImage, BufferedImage hoverImage){
        super(x, y, width, height);
        this.rootImage = rootImage;
        this.clickImage = clickImage;
        this.hoverImage = hoverImage;
        this.nowImage = rootImage;
        this.text = "";
        this.font = Global.FONT_01;
        delayCounter = new DelayCounter(Global.BUTTON_UPDATE_DELAY);

    }
    public Button(int x, int y , int width, int height, String text){
        super(x, y, width, height);
        this.text = text;
        this.font = Global.FONT_01;
        delayCounter = new DelayCounter(Global.BUTTON_UPDATE_DELAY);

    }
    
    public void setButtonListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }
    public void setText(String text){
        this.text = text;
    }
    public void setFont(Font font){
        this.font = font;
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
        if(point != null){
            point.update(width, height);
        }
    }
    @Override
    public void paint(Graphics g){
       
        if(nowImage != null){
            g.drawImage(nowImage, x, y, width, height, null);
            g.drawRect(x, y, width, height);
        }
        
        g.setFont(Global.FONT_01);
        if(point == null){
            point = new DrawStringPoint(x, y, g, font, text, width, height);
        }
        g.drawString(text, point.getX() , point.getY());    
    }
    
    
}
