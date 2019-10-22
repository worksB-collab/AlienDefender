/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GameObject.Alien;
import GameObject.Bullet;
import GameObject.Tower;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author billy
 */
public class TowerController {

    private LinkedList<Tower> towers;
    private LinkedList<Alien> aliens;

    public TowerController(AlienController alienController) {
        towers = new LinkedList<Tower>();
        this.aliens = alienController.getAliens();
        
    }

    public LinkedList<Tower> getTowers(){
        return towers;
    }
    
    public void update() {
        //Tower update
        if (aliens.size() != 0) {
            for (int i = 0; i < towers.size(); i++) {
                for (int j = 0; j < aliens.size(); j++) {
                    towers.get(i).detection(aliens.get(j));
                    towers.get(i).update();
                }
            }
        } else {
            for (int i = 0; i < towers.size(); i++) {
                LinkedList<Bullet> bullets = towers.get(i).getBullets();
                for (int j = 0; j < bullets.size(); j++) {
                    bullets.remove(j);
                }
            }
        }
    }

    public void paint(Graphics g) {
        //Tower paint
        for (int i = 0; i < towers.size(); i++) {
            towers.get(i).paint(g);
        }
    }
}
