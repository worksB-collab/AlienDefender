/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gameobjects.Alien;
import gameobjects.Tower;
import static values.Global.*;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author billy
 */
public class TowerController {

    private LinkedList<Tower> towers;
    private LinkedList<Alien> aliens;
    private LinkedList<Point> range;
    public static final int costArr[] = {(int)TOWER0_COST, (int)TOWER1_COST, (int)TOWER2_COST, (int)TOWER3_COST, (int)TOWER4_COST};
    public static final int upgradeCostArr[] = {(int)TOWER0_COST/2, (int)TOWER1_COST/2, (int)TOWER2_COST/2, (int)TOWER3_COST/2, (int)TOWER4_COST/2};

    public TowerController(AlienController alienController) {
        towers = new LinkedList<Tower>();
        this.aliens = alienController.getAliens();
    }

    public int[] getCostArr() {
        return costArr;
    }

    public LinkedList<Tower> getTowers() {
        return towers;
    }

    public LinkedList<Point> getRange() {
        return range;
    }

    public float getCost(int index) {
        return towers.get(index).getCost();
    }

    public float getUpgradeCost(int index) {
        return towers.get(index).getUpgradeCost();
    }

    public float checkTowerRange(int towerNum) {
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

    public void genRange(Point point, int towerNum) {
        range = new LinkedList<Point>();
        int towerRange = (int) checkTowerRange(towerNum);
        for (int i = -towerRange; i < towerRange; i++) {
            for (int j = -towerRange; j < towerRange; j++) {
                if (Math.abs(i) + Math.abs(j) <= towerRange) {
                    range.add(new Point((int) (point.getX() + i), (int) (point.getY() + j)));
                }
                j += (SIZE_GRID - 1);
            }
            i += (SIZE_GRID - 1);
        }
        for (int i = towerRange; i >= 0; i--) {
            for (int j = towerRange; j >= 0; j--) {
                if (Math.abs(i + j) <= towerRange) {
                    range.add(new Point((int) (point.getX() + i), (int) (point.getY() + j)));
                }
                j -= (SIZE_GRID - 1);
            }
            i -= (SIZE_GRID - 1);
        }
    }

    public void update() {
        //Tower update
        if (aliens.size() != 0) {
            for (int i = 0; i < towers.size(); i++) {
                towers.get(i).detection(aliens);
                towers.get(i).update();
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
