/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import java.awt.Graphics;
import static Value.Global.*;
import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public abstract class Alien extends ActiveObject {

    protected int act;
    protected float hp;
    public int nextPosition;
    private static LinkedList<Point> route;
    protected int alienNum;

    public Alien(float x, float y, float width, float height, float hp, float speed) {
        super(x, y, width, height, speed);
        act = 0;
        this.hp = hp;
        this.height = height;
        this.width = width;
        nextPosition = 0;
        if(route == null)
            route = new LinkedList<Point>();
    }

    public static void setRoute(LinkedList<Point> r) {
        for (int i = 0; i < r.size(); i++) {
            route.add(r.get(i));
        }
        for (int i = 0; i < 5; i++) {
            Point p = new Point((int) route.getLast().getX(), (int)route.getLast().getY() + (int)SIZE_GRID);
            route.addLast(p);
        }
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

            Point p = route.get(nextPosition);
            if (super.getX() != p.getX() || super.getY() != p.getY()) {
                if (super.getX() < p.getX()) {
                    changeDirection(RIGHT);
                }
                if (super.getY() < p.getY()) {
                    changeDirection(DOWN);
                }
                if (super.getX() >= p.getX() - super.getSpeed() && super.getX() <= p.getX() + super.getSpeed()
                        && super.getY() >= p.getY() - super.getSpeed() && super.getY() <= p.getY() + super.getSpeed()) {
                    Point last = route.getLast();
                    nextPosition++;
                }
            }
        }
        move(direction);
        act = ++act % 4;
    }

    public boolean isDead() {
        if (hp <= 0) {
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
        this.direction = direction;
        return direction;
    }

    public void move(double direction) {
        switch ((int)(direction)) {
            case UP:
                super.setY(super.getY()-super.getSpeed());
            case DOWN:
                super.setY(super.getY()+super.getSpeed());
            case LEFT:
                super.setX(super.getX()-super.getSpeed());
            case RIGHT:
                super.setX(super.getX()+super.getSpeed());
        }
    }


    @Override
    public abstract void paint(Graphics g) ;
}
