/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import controllers.DelayCounter;
import values.Global;
import static values.Global.*;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author billy
 */
public class Tower1 extends Tower {

    private TowerHelper tHelper;
    private UpgradeAnimation upgradeAnimation;
    private LinkedList<Bullet> bullets;
    private DelayCounter delay, delayForUpgrade;

    public Tower1(float x, float y) {
        super(x, y, SIZE_GRID, SIZE_GRID, TOWER0_ATK, 2 * Global.SPEED); // x, y, width, height, attack, speed
        super.setTowerNum(0);
        tHelper = new TowerHelper(super.getTowerNum());
        super.setTowerRange((int) checkTowerNum(super.getTowerNum()));
        genRange();
        bullets = super.getBullets();
        delay = new DelayCounter(1);
        delayForUpgrade = new DelayCounter(5);
        upgradeAnimation = new UpgradeAnimation(super.getX(), getY());
        super.setCost(TOWER0_COST);
        super.setUpgradeCost(super.getCost()/2);
    }

    public float checkTowerNum(int towerNum) {
        switch (towerNum) {
            case 0:
                return TOWER0_ATKRANGE;
            case 1:
                return TOWER1_ATKRANGE;
            case 2:
                return TOWER2_ATKRANGE;
            case 3:
                return TOWER3_ATKRANGE;
            case 4:
                return TOWER4_ATKRANGE;
        }
        return -1;
    }

    public void genRange() {
        super.setRange(new LinkedList<Point>());
        int towerRange = super.getTowerRange();
        for (int i = -towerRange; i < towerRange; i++) {
            for (int j = -towerRange; j < towerRange; j++) {
                if (Math.abs(i) + Math.abs(j) <= towerRange) {
                    super.getRange().add(new Point((int) (super.getX() + i), (int) (super.getY() + j)));
                }
                j += (SIZE_GRID - 1);
            }
            i += (SIZE_GRID - 1);
        }
        for (int i = towerRange; i >= 0; i--) {
            for (int j = towerRange; j >= 0; j--) {
                if (Math.abs(i + j) <= towerRange) {
                    super.getRange().add(new Point((int) (super.getX() + i), (int) (super.getY() + j)));
                }
                j -= (SIZE_GRID - 1);
            }
            i -= (SIZE_GRID - 1);
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).isReached()) {
                return;
            }
            bullets.get(i).update();
        }
        upgradeAnimation.update();
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < super.getBullets().size(); i++) {
            bullets.get(i).paint(g);
            if (bullets.get(i).isReached()) {
                if (delay.update()) {
                    bullets.remove(i);
                }
            }
        }
        tHelper.paint(g, super.getX(), getY(), SIZE_GRID, SIZE_GRID, super.getDirection(), super.getUpgradeStage());
        if (super.getUpgradeNow() == 1) {

            upgradeAnimation.paint(g);
            if (delayForUpgrade.update()) { // delay 1 to let animation run
                super.setUpgradeNow(0);
            }
        }
    }
}
