/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Controller.DelayCounter;
import static Value.Global.*;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public abstract class Tower extends ActiveObject {

    private float attack;
    private LinkedList<Point> range;
    private LinkedList<Bullet> bullets;
    private int towerNum;
    private int towerRange;
    private DelayCounter delay;
    private int upgradeStage;
    private int upgradeNow;
    private float cost, upgradeCost;

    public Tower(float x, float y, float width, float height, float attack, float speed) {
        super(x, y, width, height, speed);
        bullets = new LinkedList<Bullet>();
        delay = new DelayCounter(10);
        upgradeStage = 0;
        setAttack(attack);
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getCost() {
        return cost;
    }

    public void setUpgradeCost(float upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public float getUpgradeCost() {
        return upgradeCost;
    }

    public LinkedList getRange() {
        return range;
    }

    public void setRange(LinkedList<Point> range) {
        this.range = range;
    }

    public int getUpgradeStage() {
        return upgradeStage;
    }

    public int getUpgradeNow() {
        return upgradeNow;
    }

    public void setUpgradeNow(int upgradeNow) {
        this.upgradeNow = upgradeNow;
    }

    public void detection(LinkedList<Alien> aliens) {
        for (Point range : range) {
            for (int i = 0; i < aliens.size(); i++) {
                if (aliens.get(i).getX() + SIZE_GRID - DEVIATION >= range.getX()
                        && aliens.get(i).getX() + DEVIATION <= range.getX() + SIZE_GRID
                        && aliens.get(i).getY() + SIZE_GRID - DEVIATION >= range.getY()
                        && aliens.get(i).getY() + DEVIATION <= range.getY() + SIZE_GRID) {
                    changeDirection(aliens.get(i));
                    attack(aliens.get(i));
                    return;
                }
            }
        }
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float attack) {
        this.attack = attack;
    }

    public void changeDirection(Alien alien) {
        double h = (alien.getY() - this.getY());
        double w = (alien.getX() - this.getX());
        if (h == 0) {
            if (w > 0) {
                super.setDirection(90);
            } else {
                super.setDirection(270);
            }
        }
        if (w == 0) {
            if (h > 0) {
                super.setDirection(180);
            } else {
                super.setDirection(0);
            }
        }
        if (w != 0 && h != 0) {
            if (w > 0 && h < 0) {
                super.setDirection(90 - (int) (Math.abs(Math.atan((h)
                        / (w)) * 180 / Math.PI)));
            } else if (w > 0 && h > 0) {
                super.setDirection(90 + (int) (Math.abs(Math.atan((h)
                        / (w)) * 180 / Math.PI)));
            } else if (w < 0 && h < 0) {
                super.setDirection(270 + (int) (Math.abs(Math.atan((h)
                        / (w)) * 180 / Math.PI)));
            } else {
                super.setDirection(270 - (int) (Math.abs(Math.atan((h)
                        / (w)) * 180 / Math.PI)));
            }
        }
    }

    public void attack(Alien alien) {
        if (delay.update()) {
            alien.isAttacked(this);
            bullets.add(new Bullet(super.getX(), super.getY(), alien, this, super.getDirection(), super.getSpeed()));
        }
    }

    public void setTowerNum(int towerNum) {
        this.towerNum = towerNum;
    }

    public int getTowerNum() {
        return towerNum;
    }

    public void setTowerRange(int towerRange) {
        this.towerRange = towerRange;
    }

    public int getTowerRange() {
        return towerRange;
    }

    public LinkedList<Bullet> getBullets() {
        return bullets;
    }

    // call this function to upgrade the tower
    public void upgrade() {
        if (upgradeStage == 2) {
            return;
        }
        upgradeNow++;
        upgradeStage++;
        setAttack(getAttack() * 1.3f);
        setUpgradeCost(getCost()*1.3f);
    }

    @Override
    public abstract void update();

    @Override
    public abstract void paint(Graphics g);
}
