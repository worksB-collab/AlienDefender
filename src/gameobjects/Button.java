/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import controllers.DelayCounter;
import values.DrawStringPoint;
import values.Global;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class Button extends GameObject {

    public interface ButtonListener {

        public void onClick(int x, int y);

        public void hover(int x, int y);
    }
    private BufferedImage rootImage;
    private DelayCounter delayCounter;
    private ButtonListener buttonListener;
    private String text;
    private Font font;
    private Color color;
    private DrawStringPoint point;
    private boolean isClicked;
    private boolean isHovered;

    public Button(float x, float y, float width, float height, BufferedImage rootImage) {
        super(x, y, width, height);
        this.rootImage = rootImage;
        this.text = "";
        this.font = new Font(Font.DIALOG, Font.PLAIN, (int)(40 * (Global.STANDAR_MIN_SIZE / Global.MIN_PICTURE_SIZE)));
        this.color = Color.ORANGE;
        isHovered = false;
        isClicked = false;
        delayCounter = new DelayCounter(Global.BUTTON_UPDATE_DELAY);

    }

    public Button(float x, float y, float width, float height, String text) {
        super(x, y, width, height);
        this.text = text;
        this.font = new Font(Font.DIALOG, Font.PLAIN, (int)(40 * (Global.STANDAR_MIN_SIZE / Global.MIN_PICTURE_SIZE)));
        this.color = Color.LIGHT_GRAY;
        isHovered = false;
        isClicked = false;
        delayCounter = new DelayCounter(Global.BUTTON_UPDATE_DELAY);

    }

    public void setButtonListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setFontColor(Color color) {
        this.color = color;
    }

    public boolean isRange(int x, int y) {
        if (x < super.getX() || x > super.getX() + width) {
            return false;
        }
        if (y < super.getY() || y >super.getY() + height) {
            return false;
        }
        return true;
    }

    public void click(int x, int y) {
        if (buttonListener == null) {
            return;
        }
        isClicked = true;
        buttonListener.onClick(x, y);
    }

    public void hover(int x, int y) {
        if (buttonListener == null) {
            return;
        }
        isHovered = true;
        buttonListener.hover(x, y);
    }

    public boolean getState() {
        return isClicked;
    }

    @Override
    public void update() {
        if (isClicked) {
        }
        if (delayCounter.update()) {
            isClicked = false;
            isHovered = false;
        }
        if (point != null) {
            if (point.getHeight() != height) {
                point.update(width, height);
            }
        }
    }

    @Override
    public void paint(Graphics g) {

        if (rootImage != null) {
            g.drawImage(rootImage, (int)super.getX(), (int)super.getY(), (int)width, (int)height, null);
        }

        g.setFont(font);
        if (point == null) {
            point = new DrawStringPoint((int)super.getX(), (int)super.getY(), g, font, text, (int)width, (int)height);
        }
        g.setColor(color);
        g.drawString(text, (int)point.getX(), (int)point.getY());
        g.setColor(Global.DEFAULT_FONT_COLOR);
    }

}
