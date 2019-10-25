/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.RouteController.RoutePoint;
import GameObject.Alien;
import GameObject.Alien1;
import Value.Global;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author billy
 */
public class AlienController {

    private LinkedList<Alien> aliens;
    private DelayCounter moveDelay, genDelay;
    private int count;
    private ScoreController scoreController;
    private LinkedList<RoutePoint> route;
    private PlayerController playerController;
    private int stop; // stop generating aliens
    // NOT YET connecting to sceneController to the next scene to zerolize stop;

    public AlienController(LinkedList<RoutePoint> route) {
        aliens = new LinkedList<Alien>();
        moveDelay = new DelayCounter(1);
        genDelay = new DelayCounter(5);
        scoreController = new ScoreController();
        playerController = PlayerController.genInstance();
        this.route = route;
        stop = 0;
    }

    public LinkedList<Alien> getAliens() {
        return aliens;
    }

    private void genAlien(float x, float y, int frequency, int alienNum) {
        if ((int) (Math.random() * 100) >= frequency / 100) {
            switch (alienNum) {
                case 1:
                    aliens.add(new Alien1(x, y));
                    break;
//                case 2:
//                    aliens.add(new Alien2(x, y));
//                    break;
//                case 3:
//                    aliens.add(new Alien3(x, y));
//                    break;
//                case 4:
//                    aliens.add(new Alien4(x, y));
//                    break;
//                case 5:
//                    aliens.add(new Alien5(x, y));
//                    break;
//                case 6:
//                    aliens.add(new Alien6(x, y));
//                    break;
//                case 7:
//                    aliens.add(new Alien7(x, y));
//                    break;
//                case 8:
//                    aliens.add(new Alien8(x, y));
//                    break;
//                case 9:
//                    aliens.add(new Alien9(x, y));
//                    break;
//                case 10:
//                    aliens.add(new Alien10(x, y));
//                    break;
            }
        }
    }

    private void gameLevelSetting(float x, float y, int frequency, int alienNum, int alienQuantity) {
        if (moveDelay.update()) {
            if (count < alienQuantity) {
                if (genDelay.update()) {
                    genAlien(x, y, frequency, alienNum);
                    Alien.setRoute(route);
                    count++;
                }
            }
        }
    }

    public void update() {
        //Aliens update
        if (moveDelay.update()) {
            for (int i = 0; i < aliens.size(); i++) {
                Alien a = aliens.get(i);
                a.update();
                if (a.getY() >= 24f * Global.MIN_PICTURE_SIZE) {
                    playerController.setHP(playerController.getHP() - a.getAlienNum() * 5);
                    aliens.remove(i);
                }
            }
        }
    }

    public void paint(Graphics g) {
        //Alines paint
        for (int i = 0; i < aliens.size(); i++) {
            Alien alien = aliens.get(i);
            if (alien.isDead()) {
                alien.paintDead(g);
                if (alien.isDead()) {// kill counts 
                    scoreController.scoreCount(alien.getAlienNum());
                    playerController.addScore((long) scoreController.countPerKill(alien.getAlienNum()));
                }
                if (aliens.get(i).getDeadDelay() % 6 == 0) {
                    aliens.remove(i);
                }
            } else {
                aliens.get(i).paint(g);
            }
        }
    }

}
