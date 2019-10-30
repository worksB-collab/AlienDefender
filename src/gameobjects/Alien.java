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
        for (int i = 0; i < r.size(); i++) {
            route.add(r.get(i));
        }
//        for (int i = 0; i < 5; i++) {
//            RoutePoint p = new RoutePoint((int) route.getLast().getX(), (int) route.getLast().getY() + (int) SIZE_GRID);
//            route.addLast(p);
//        }
//        for(RoutePoint t : route)
//        System.out.println("x: "+t.getX()+" "+" y" +t.getY());
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

            RoutePoint p = route.get(nextPosition);
            if (super.getX() != p.getX() || super.getY() != p.getY()) {
                if (super.getY() - p.getY() <0 ) {
                    changeDirection(DOWN);
                } else if (super.getX() - p.getX() < 0) {
                    changeDirection(RIGHT);
                } else if (super.getX() - p.getX() > 0) {
                    changeDirection(LEFT);
                } else if(super.getY() - p.getY() > 0){
                    changeDirection(UP);
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

                if (super.getX() >= p.getX() && super.getX() <= p.getX() + SIZE_GRID
                        && super.getY() >= p.getY() && super.getY() <= p.getY() + SIZE_GRID) {
                    RoutePoint last = route.getLast();
                    nextPosition++;
                }
            }
        }
        move(super.getDirection());
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
        switch (direction) {
            case Global.UP:
                super.setY(super.getY() - 1f);
                break;
            case Global.DOWN:
                super.setY(super.getY() + 1f);
                break;
            case Global.LEFT:
                super.setX(super.getX() - 1f);
                break;
            case Global.RIGHT:
                super.setX(super.getX() + 1f);
                break;
        } 
    
//        switch (direction) {
//            case Global.UP:
//                super.setY(super.getY() - super.getSpeed());
//                break;
//            case Global.DOWN:
//                super.setY(super.getY() + super.getSpeed());
//                break;
//            case Global.LEFT:
//                super.setX(super.getX() - super.getSpeed());
//                break;
//            case Global.RIGHT:
//                super.setX(super.getX() + super.getSpeed());
//                break;
//        }   
    }

    public abstract int getDeadDelay();

    @Override
    public abstract void paint(Graphics g);

    public abstract void paintDead(Graphics g);

}
