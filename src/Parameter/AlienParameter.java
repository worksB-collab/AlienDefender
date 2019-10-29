/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parameter;

import controllers.AlienController;

/**
 *
 * @author billy
 */
public class AlienParameter {

    private int[][] alienSet;
    private AlienController alienController;
    private int stage;

    public AlienParameter(int stage) {
        this.stage = stage;
        alienSet = new int[5][5];
    }

    public int[][] setStageValue() {
        switch (stage) {
            case 1:
                alienSet[0][0] = -25;
                alienSet[0][1] = 50;
                alienSet[0][2] = 80;
                alienSet[0][3] = 1;
                alienSet[0][4] = 30;
                return alienSet;
            case 2:
                alienSet[0][0] = -25;
                alienSet[0][1] = 50;
                alienSet[0][2] = 80;
                alienSet[0][3] = 3;
                alienSet[0][4] = 15;

                alienSet[1][0] = -25;
                alienSet[1][1] = 50;
                alienSet[1][2] = 90;
                alienSet[1][3] = 2;
                alienSet[1][4] = 25;
                return alienSet;
            case 3:
                alienSet[0][0] = -25;
                alienSet[0][1] = 50;
                alienSet[0][2] = 30;
                alienSet[0][3] = 4;
                alienSet[0][4] = 20;

                alienSet[1][0] = -25;
                alienSet[1][1] = 50;
                alienSet[1][2] = 50;
                alienSet[1][3] = 5;
                alienSet[1][4] = 20;

                alienSet[2][0] = -25;
                alienSet[2][1] = 50;
                alienSet[2][2] = 30;
                alienSet[2][3] = 6;
                alienSet[2][4] = 10;
                return alienSet;
            case 4:
                alienSet[0][0] = -25;
                alienSet[0][1] = 50;
                alienSet[0][2] = 80;
                alienSet[0][3] = 1;
                alienSet[0][4] = 20;

                alienSet[1][0] = -25;
                alienSet[1][1] = 50;
                alienSet[1][2] = 20;
                alienSet[1][3] = 5;
                alienSet[1][4] = 10;

                alienSet[2][0] = -25;
                alienSet[2][1] = 50;
                alienSet[2][2] = 50;
                alienSet[2][3] = 7;
                alienSet[2][4] = 10;

                alienSet[3][0] = -25;
                alienSet[3][1] = 50;
                alienSet[3][2] = 20;
                alienSet[3][3] = 8;
                alienSet[3][4] = 10;

                return alienSet;
            case 5:
                alienSet[0][0] = -25;
                alienSet[0][1] = 50;
                alienSet[0][2] = 80;
                alienSet[0][3] = 6;
                alienSet[0][4] = 10;

                alienSet[1][0] = -25;
                alienSet[1][1] = 50;
                alienSet[1][2] = 20;
                alienSet[1][3] = 7;
                alienSet[1][4] = 10;

                alienSet[2][0] = -25;
                alienSet[2][1] = 50;
                alienSet[2][2] = 60;
                alienSet[2][3] = 8;
                alienSet[2][4] = 10;

                alienSet[3][0] = -25;
                alienSet[3][1] = 50;
                alienSet[3][2] = 60;
                alienSet[3][3] = 9;
                alienSet[3][4] = 10;

                alienSet[4][0] = -25;
                alienSet[4][1] = 50;
                alienSet[4][2] = 50;
                alienSet[4][3] = 10;
                alienSet[4][4] = 10;
                return alienSet;
        }
        return alienSet;
    }

}
