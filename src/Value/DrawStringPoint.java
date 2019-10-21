/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Value;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author user
 */
public class DrawStringPoint {
    private int x0;
    private int y0;
    private int x;
    private int y;
    private int width;
    private int height;
    private Graphics g;
    private Font font;
    private String text;
    private FontMetrics metrics;
    
    public DrawStringPoint(int x0, int y0, Graphics g, Font font, String text, int width, int height){
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
    
    public void update(int width, int height){
        this.x = x0 + (width - metrics.stringWidth(text)) / 2;
        this.y = y0 + (height - metrics.getHeight()) / 2 + metrics.getAscent();
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public String getText(){
        return text;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
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
    public void setStartPoint(int x0, int y0){
        this.x0 = x0;
        this.y0 = y0;
    }
}
