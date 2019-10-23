/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Controller.DelayCounter;
import Value.DrawStringPoint;
import Value.Global;
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

        public void onClick(float x, float y);

        public void hover(float x, float y);
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
        this.font = new Font(Font.DIALOG, Font.PLAIN, (40 * (Global.STANDAR_MIN_SIZE / Global.MIN_PICTURE_SIZE)));
        this.color = Color.ORANGE;
        isHovered = false;
        isClicked = false;
        delayCounter = new DelayCounter(Global.BUTTON_UPDATE_DELAY);

    }

    public Button(float x, float y, float width, float height, String text) {
        super(x, y, width, height);
        this.text = text;
        this.font = new Font(Font.DIALOG, Font.PLAIN, (40 * (Global.STANDAR_MIN_SIZE / Global.MIN_PICTURE_SIZE)));
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

    public boolean isRange(float x, float y) {
        if (x < super.getX() || x > super.getX() + width) {
            return false;
        }
        if (y < super.getY() || y >super.getY() + height) {
            return false;
        }
        return true;
    }

    public void click(float x, float y) {
        if (buttonListener == null) {
            return;
        }
        isClicked = true;
        buttonListener.onClick(x, y);
    }

    public void hover(float x, float y) {
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
//        g.drawRect(x, y, width, height);
        g.drawString(text, point.getX(), point.getY());
        g.setColor(Global.DEFAULT_FONT_COLOR);
        if (isHovered) {
            g.setColor(Color.ORANGE);
            g.drawRoundRect((int)super.getX(), (int)super.getY(), (int)width, (int)height, (int)Global.MIN_PICTURE_SIZE / 2, (int)Global.MIN_PICTURE_SIZE / 2);
            g.setColor(Color.BLACK);
        }
    }

}
