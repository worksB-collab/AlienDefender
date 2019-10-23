/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

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
    private LinkedList<Alien> aliens;
    private float orignHP;

    public AlienHelper(int alien) {
        img = getActor(alien);
        actorPosition = alien % 6;
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

    public void paint(Graphics g, float x, float y, float width, float height, int act, float hp) {
        setOrignHP(hp);
        if (img == null) {
            return;
        }
        int dy = 65 * (actorPosition);
        g.drawImage(img, (int)x, (int)y, (int)(x + width), (int)(y + height),
                (int)(act * Global.SIZE_OBJECT), dy,
                65 + (int)(act * Global.SIZE_OBJECT), (int)(dy + Global.SIZE_OBJECT), null);
        bloodLine(g, x, y, width, height, hp);
    }
    
    public void setOrignHP(float hp){
        if (orignHP==-1){
            orignHP = hp;
        }
    }

    public void bloodLine(Graphics g, float x, float y, float width, float height, float hp) {
        g.setColor(Color.black);
        g.drawRect((int)x, (int)y, (int)width, (int)SIZE_BLOODLINE);
        
        double percentage = bloodPercentage(hp);
        g.setColor(Color.red);
        g.fillRect((int)(x + 1), (int)(y + 1), (int)(percentage*width)-2, (int)SIZE_BLOODLINE - 2);
        g.setColor(Color.black);
    }

    public double bloodPercentage(float hp){
        return (hp/orignHP);
    }
    
    public void dead(Graphics g, float x, float y, int alienNum) {
        actorPosition = alienNum;
        switch (alienNum) {
            case 1:
                aliens.add(new Alien1(x, y));
                break;
        }
//        if (delay.update()) {
        for (int i = 0; i < 6; i++) {
            
                g.drawImage(img, (int)x, (int)y,(int)(x + SIZE_GRID), (int)(y + SIZE_GRID),
                        (int)((i % 2 + 3) * SIZE_OBJECT), (int)(actorPosition * SIZE_OBJECT),
                        (int)((i % 2 + 4) * SIZE_OBJECT), (int)(SIZE_OBJECT + actorPosition * SIZE_OBJECT), null);
            }
//        }
    }
}
