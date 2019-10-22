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
public class Alien extends ActiveObject {

    protected int act;
    protected double hp;
    public int nextPosition;
    private static LinkedList<Point> route;
    protected int alienNum;

    public Alien(int x, int y, int width, int height, int hp, int speed) {
        super(x, y, width, height, speed);
        act = 0;
        this.hp = hp;
        this.speed = speed;
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
            Point p = new Point((int) route.getLast().getX(), (int) route.getLast().getY() + SIZE_GRID);
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
            if (x != p.getX() || y != p.getY()) {
                if (x < p.getX()) {
                    changeDirection(RIGHT);
                }
                if (y < p.getY()) {
                    changeDirection(DOWN);
                }
                if (x >= p.getX() - speed && x <= p.getX() + speed
                        && y >= p.getY() - speed && y <= p.getY() + speed) {
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

    public double getHp() {
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
                y -= speed;
            case DOWN:
                y += speed;
            case LEFT:
                x -= speed;
            case RIGHT:
                x += speed;
        }
    }


    @Override
    public void paint(Graphics g) {
    }
}
