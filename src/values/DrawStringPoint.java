/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package values;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author user
 */
public class DrawStringPoint {
    private float x0;
    private float y0;
    private float x;
    private float y;
    private float width;
    private float height;
    private Graphics g;
    private Font font;
    private String text;
    private FontMetrics metrics;
    
    public DrawStringPoint(float x0, float y0, Graphics g, Font font, String text, float width, float height){
        this.x0 = x0;
        this.y0 = y0;
        this.g = g;
        this.font = font;
        this.text = text;
        this.width = width;
        this.height = height;
        metrics = g.getFontMetrics(font);
        this.x = x0 + (width - metrics.stringWidth(text)) / 2;
        this.y = y0 + (height - metrics.getHeight()) / 2 + metrics.getAscent();
    }
    
    public void update(float width, float height){
        this.x = x0 + (width - metrics.stringWidth(text)) / 2;
        this.y = y0 + (height - metrics.getHeight()) / 2 + metrics.getAscent();
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public String getText(){
        return text;
    }
    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }
    public Font getFont(){
        return font;
    }
    //setter
    public void setFont(Font font){
        this.font = font;
    }
    public void setText(String text){
        this.text = text;
    }
    public void setGraphics(Graphics g){
        this.g = g;
    }
    public void setStartPoint(float x0, float y0){
        this.x0 = x0;
        this.y0 = y0;
    }
}
