/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import Value.Global;
import static Value.Global.SIZE_GRID;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author billy
 */
public class Tower1 extends Tower {

    private TowerHelper tHelper;

    public Tower1(int x, int y) {
        super(x, y, SIZE_GRID, SIZE_GRID, 1, 1 * Global.SPEED);
        tHelper = new TowerHelper(2);
    }

    @Override
    public void paint(Graphics g) {
        tHelper.paint(g, x, y, SIZE_GRID, SIZE_GRID, direction);
    }
}
