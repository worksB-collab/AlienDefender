/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import controllers.CommandSolver;
import controllers.CommandSolver.KeyCommandListener;
import controllers.CommandSolver.MouseCommandListener;
import controllers.CommandSolver.TypedListener;
import values.DrawStringPoint;
import values.Global;
import com.sun.glass.events.KeyEvent;
import controllers.ImageController;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import static values.Global.FONT_00;
import static values.Global.FONT_BUTTON;
import static values.Global.FONT_INFOWINDOW;

/**
 *
 * @author user
 */
public class InputPopWindow extends PopUpWindow {

    private ImageController imageController;
    private BufferedImage image;
    private DrawStringPoint point;
    private Font font;
    private String text;
    private int limitChar;
    private boolean isEnd;

    public InputPopWindow(float x, float y, float width, float height, LinkedList<Character> charList, int limitChar) {
        super(x, y, width, height);
        imageController = ImageController.genInstance();
        image = imageController.tryGetImage("/resources/Images/Label/Tower_generate_Label5.png");
        font = Global.FONT_INPUT;
        text = "";
        this.limitChar = limitChar;
        isEnd = false;
        super.setKeyCommandListener(new KeyCommandListener() {

            @Override
            public void keyPressed(int commandCode, long trigTime) {
            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {
            }

        });

        super.setTypedListener(new TypedListener() {
            @Override
            public void keyTyped(char c, long trigTime) {

                if (c == KeyEvent.VK_ENTER) {
                    text = "";
                    for (Character character : charList) {
                        text += character.charValue();
                    }
                    isEnd = true;
                } else if (c == KeyEvent.VK_BACKSPACE) {
                    if (charList.size() >= 1) {
                        charList.removeLast();
                    }
                } else {
                    if (charList.size() < limitChar) {
                        charList.add(c);
                    }
                }
                text = "";
                for (Character character : charList) {
                    text += character.charValue();
                }
            }

        });
        super.setMouseCommandListener(new MouseCommandListener() {

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {

            }

        });
    }

    @Override
    public boolean isEnd() {
        return isEnd;

    }

    public String getResult() {
        if (isEnd) {
            return text;
        }
        return null;
    }

    @Override
    public void update() {
        if (point != null) {
            point.setText(text);
            point.update(width, height);
        }
    }

    @Override
    public void paint(Graphics g) {

        if (point == null) {
            point = new DrawStringPoint(super.getX(), super.getY(), g, font, text, width, height);
        }
        g.drawImage(image, (int) super.getX(), (int) super.getY(), (int) super.getWidth(), (int) point.getHeight(), null);
        g.setColor(Color.orange);
        g.setFont(font);
        g.drawString(text, (int) point.getX(), (int) point.getY());

        g.setFont(FONT_BUTTON);
        g.drawString("What's your name?", 350, 300);
        g.setFont(FONT_INFOWINDOW);
        g.drawString("< English and Numbers Only >", 380, 485);
    }

}
