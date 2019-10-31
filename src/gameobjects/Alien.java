/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import controllers.DelayCounter;
import controllers.RouteController.RoutePoint;
import values.Global;
import java.awt.Graphics;
import static values.Global.*;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public abstract class Alien extends ActiveObject {

    protected int act;
    private float hp;
    public int nextPosition;
    private static LinkedList<RoutePoint> route = new LinkedList<RoutePoint>();
    protected int alienNum;
    private int money;
    private DelayCounter delay;
    private RoutePoint nextPoint;

    public Alien(float x, float y, float width, float height, float hp, float speed) {
        super(x, y, width, height, speed);
        act = 0;
        this.hp = hp;
        this.height = height;
        this.width = width;
        nextPosition = 0;
        delay = new DelayCounter(1);
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public static void setRoute(LinkedList<RoutePoint> r) {
        route.clear();
        for (int i = 0; i < r.size(); i++) {
            route.add(r.get(i));
        }
        for (int i = 0; i < 5; i++) {
            RoutePoint p = new RoutePoint((int) route.getLast().getX(), (int) route.getLast().getY() + (int) SIZE_GRID);
            route.addLast(p);
        }
//        int i = 0;
//        for(RoutePoint t : route){
//            i++;
//            System.out.println(i + "\tx: "+t.getX()+" "+" y" +t.getY());
//        }
    }
    public int getAlienNum() {
        return alienNum;
    }

    @Override
    public void update() {
        if (this.isDead()) {
            return;
        }
        if (route.get(nextPosition) != null) {
            nextPoint = route.get(nextPosition);  
            float vx = Math.abs(super.getX() - nextPoint.getX());
            float vy = Math.abs(super.getY() - nextPoint.getY());
            
            if (vx != 0 ) {
                if(vy != 0){
                    if(vx > vy){
                        if(super.getX() < nextPoint.getX()){
                            changeDirection(RIGHT);
                        }
                        else if(super.getX() > nextPoint.getX()){
                            changeDirection(LEFT);
                        }
                    }else{
                        if(super.getY() < nextPoint.getY()){
                            changeDirection(DOWN);
                        }else if(super.getY() > nextPoint.getY()){
                            changeDirection(UP);
                        }
                    }
                }else{
                    if(super.getX() < nextPoint.getX()){
                            changeDirection(RIGHT);
                     }else if(super.getX() > nextPoint.getX()){
                            changeDirection(LEFT);
                    }
                }

            }else if(vy != 0){
                if(vx != 0){
                    if(vy > vx){
                        if(super.getY() < nextPoint.getY()){
                            changeDirection(DOWN);
                        }else if(super.getY() > nextPoint.getY()){
                            changeDirection(UP);
                        }
                    }else{
                        if(super.getX() < nextPoint.getX()){
                            changeDirection(RIGHT);
                        }
                        else if(super.getX() > nextPoint.getX()){
                            changeDirection(LEFT);
                        }
                    }
                }else{
                    if(super.getY() < nextPoint.getY()){
                            changeDirection(DOWN);
                    }else if(super.getY() > nextPoint.getY()){
                            changeDirection(UP);
                    }
                }
                
            }
            
                
                
//                if (super.getY() <= p.getY()) {
//                    changeDirection(DOWN);
//                } else if (super.getX() <= p.getX() + DEVIATION) {
//                    changeDirection(RIGHT);
//                } else if (super.getX() >= p.getX() + DEVIATION) {
//                    changeDirection(LEFT);
//                } else {
//                    changeDirection(UP);
//                }
       

        }
        move(super.getDirection());
        if (super.getX() == nextPoint.getX() && super.getY() == nextPoint.getY()) {
           nextPosition++;

        }


        if (delay.update()) {
            act = ++act % 4;
        }
    }

    public boolean isDead() {
        if (hp <= 0f) {
            return true;
        }
        return false;
    }

    public float getHp() {
        return hp;
    }

    public void isAttacked(Tower tower) {
        this.hp = this.hp - tower.getAttack();
    }

    public int changeDirection(int direction) {
        super.setDirection(direction);
        return direction;
    }

    public void move(int direction) {
//        switch (direction) {
//            case Global.UP:
//                super.setY(super.getY() - 1f);
//                break;
//            case Global.DOWN:
//                super.setY(super.getY() + 1f);
//                break;
//            case Global.LEFT:
//                super.setX(super.getX() - 1f);
//                break;
//            case Global.RIGHT:
//                super.setX(super.getX() + 1f);
//                break;
//        } 
    
        switch (direction) {
            case Global.UP:
                super.setY(super.getY() - super.getSpeed());
                if(super.getY() < nextPoint.getY()){
                    super.setY(nextPoint.getY());
                }
                break;
            case Global.DOWN:
                super.setY(super.getY() + super.getSpeed());
                if(super.getY() > nextPoint.getY()){
                    super.setY(nextPoint.getY());
                }
                break;
            case Global.LEFT:
                super.setX(super.getX() - super.getSpeed());
                if(super.getX() < nextPoint.getX()){
                    super.setX(nextPoint.getX());
                }
                break;
            case Global.RIGHT:
                super.setX(super.getX() + super.getSpeed());
                if(super.getX() > nextPoint.getX()){
                    super.setX(nextPoint.getX());
                }
                break;
        }
    }

    public abstract int getDeadDelay();

    @Override
    public abstract void paint(Graphics g);

    public abstract void paintDead(Graphics g);

}
