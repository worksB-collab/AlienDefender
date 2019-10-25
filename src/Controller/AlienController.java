/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.RouteController.RoutePoint;
import GameObject.Alien;
import GameObject.Alien1;
import GameObject.Alien2;
import Value.Global;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author billy
 */
public class AlienController {
    public static class AlienSet{
        private float x;
        private float y;
        private  int type;
        private  int number;
        private int generateNumber;
        private float frequency;
        private boolean isEnd;
        public AlienSet(float x, float y, int type, int number, float frequency){
            this.x = x;
            this.y = y;
            this.type = type;
            this.number = number;
            this.frequency = frequency;
            generateNumber = 0;
            this.isEnd = false; 
        }
        public int getNumber(){
            return number;
        }
        public int getType(){
            return type;
        }
        public float getFrequency(){
            return frequency;
        }
        public boolean isEnd(){
            return isEnd;
        }
        
        private Alien genAlien() {
            Alien alien = null;
            if ((Math.random() * 100) > 100 - frequency) {
                switch (type) {
                    case 1:
                        alien =   new Alien1(x, y);
                    case 2:
                        alien =   new Alien2(x, y);
                 }
            generateNumber++;
            if(generateNumber >= number){
                isEnd = true;
            }

            return alien;
        }
        return null;
       
        }
    }
    
    private LinkedList<Alien> aliens;
    private LinkedList<AlienSet> alienPairs;
    private AlienSet  presentAlienSet;
    private Iterator<AlienSet> iter;
    private DelayCounter moveDelay, genDelay;
    private int count;
    private ScoreController scoreController;
    private PlayerController playerController;
    private int stop; // stop generating aliens
    private float x, y, frequency;
//    private int alienNum, alienQuantity;
    private int a = -1;
    // NOT YET connecting to sceneController to the next scene to zerolize stop;

    public AlienController(LinkedList<RoutePoint> route) {
        aliens = new LinkedList<Alien>();
        alienPairs = new LinkedList<AlienSet>();
        moveDelay = new DelayCounter(2);
        genDelay = new DelayCounter(40);
        scoreController = new ScoreController();
        playerController = PlayerController.genInstance();
        Alien.setRoute(route);
        stop = 0;
    }

    public LinkedList<Alien> getAliens() {
        return aliens;
    }
    
    public void gameLevelSetting(float x, float y, float frequency, int alienNum, int alienQuantity) {
        this.x = x; //
        this.y = y;//
        alienPairs.add(new AlienSet(x, y, alienNum, alienQuantity, frequency));
//        this.frequency = frequency;
//        this.alienNum = alienNum;
//        this.alienQuantity += alienQuantity; //
//        if (moveDelay.update()) {
//            if (count < alienQuantity) {
//                    if (genDelay.update()) {
//                        genAlien(x, y, frequency, alienNum);
//                        Alien.setRoute(route);
//                        count++;
//                    }
//                }
//        }
    }

    public void update() {
        //Aliens genearate Setting
         if(iter == null){
             iter = alienPairs.listIterator();
             if(iter.hasNext()){
                 presentAlienSet = iter.next();
             }
         }
         if(presentAlienSet.isEnd){
             if(iter.hasNext()){
                 presentAlienSet = iter.next();
             }
         }
        
        //Aliens update
         if(genDelay.update()){
             //Aliens genearate
             if(!presentAlienSet.isEnd){
                Alien alien = presentAlienSet.genAlien();
                if(alien != null){
                    aliens.add(alien);
                }
             }
         }
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
