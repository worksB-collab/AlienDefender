/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Controller.DelayCounter;
import Controller.ImageController;
import Value.Global;
import static Value.Global.*;
import Value.Path;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class AlienHelper {

    private BufferedImage img;
    private int actorPosition;
    private DelayCounter delay;
    private LinkedList<Alien> aliens;
    private double orignHP;
    private double currentHP;

    public AlienHelper(int alien) {
        img = getActor(alien);
        actorPosition = alien % 6;
        delay = new DelayCounter(3);
        aliens = new LinkedList<Alien>();
        orignHP =-1;
    }

    private BufferedImage getActor(int alien) {
        ImageController irc = ImageController.genInstance();
        if (alien >= 0 && alien < 6) {
            return irc.tryGetImage(Path.Image.ALIEN1);
        }
        if (alien < 12) {
            return irc.tryGetImage(Path.Image.ALIEN1); // not yet updated
        }
        return null;
    }

    public void paint(Graphics g, int x, int y, int width, int height, int act, double hp) {
        setOrignHP(hp);
        if (img == null) {
            return;
        }
        int dy = 65 * (actorPosition);
        g.drawImage(img, x, y, x + width, y + height,
                act * Global.SIZE_OBJECT, dy,
                65 + act * Global.SIZE_OBJECT, dy + Global.SIZE_OBJECT, null);
        bloodLine(g, x, y, width, height, hp);
    }
    
    public void setOrignHP(double hp){
        if (orignHP==-1){
            orignHP = hp;
        }
    }

    public void bloodLine(Graphics g, int x, int y, int width, int height, double hp) {
        g.setColor(Color.black);
        g.drawRect(x, y, width, SIZE_BLOODLINE);
        
        double percentage = bloodPercentage(hp);
        g.setColor(Color.red);
        g.fillRect(x + 1, y + 1, (int)(percentage*width)-2, SIZE_BLOODLINE - 2);
        g.setColor(Color.black);
    }

    public double bloodPercentage(double hp){
        return (hp/orignHP);
    }
    
    public void dead(Graphics g, int x, int y, int alien) {
        actorPosition = alien;
        switch (alien) {
            case 1:
                aliens.add(new Alien1(x, y));
                break;
        }
        for (int i = 0; i < 6; i++) {
            if (delay.update()) {
                g.drawImage(img, x, y, x + SIZE_GRID, y + SIZE_GRID,
                        (i % 2 + 3) * SIZE_OBJECT, actorPosition * SIZE_OBJECT,
                        (i % 2 + 4) * SIZE_OBJECT, SIZE_OBJECT + actorPosition * SIZE_OBJECT, null);
            }
        }
        aliens.removeFirst();
    }
}
