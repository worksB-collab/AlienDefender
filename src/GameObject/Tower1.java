/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Value.Global;
import static Value.Global.*;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author billy
 */
public class Tower1 extends Tower {

    private TowerHelper tHelper;

    public Tower1(int x, int y) {
        super(x, y, SIZE_GRID, SIZE_GRID, 10, 1 * Global.SPEED); // x, y, width, height, attack, speed
        tHelper = new TowerHelper(4);
        towerNum = 0;
        towerRange = checkTowerNum(towerNum);
        genRange();
    }

    public int checkTowerNum(int towerNum) {
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

    public LinkedList genRange() {
        range = new LinkedList<Point>();
        for (int i = -towerRange; i < towerRange; i++) {
            for (int j = -towerRange; j < towerRange; j++) {
                if (Math.abs(i) + Math.abs(j) <= towerRange) {
                    range.add(new Point(x + i, y + j));
                }
                j += (SIZE_GRID - 1);
            }
            i += (SIZE_GRID - 1);
        }
        for (int i = towerRange; i >= 0; i--) {
            for (int j = towerRange; j >= 0; j--) {
                if (Math.abs(i + j) <= towerRange) {
                    range.add(new Point(x + i, y + j));
                }
                j -= (SIZE_GRID - 1);
            }
            i -= (SIZE_GRID - 1);
        }
        return range;

    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        tHelper.paint(g, x, y, SIZE_GRID, SIZE_GRID, direction);
    }
}
