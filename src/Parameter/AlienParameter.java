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
    private int start1x;
    private int start1y;
    private int start2x;
    private int start2y;
    private int start3x;
    private int start3y;
    private int start4x;
    private int start4y;
    private int start5x;
    private int start5y;

    public AlienParameter(int stage) {
        this.stage = stage;
        alienSet = new int[5][5];
        start1x = -25;
        start1y = 50;
        start2x = 0;
        start2y = 0;
        start3x = 0;
        start3y = 0;
        start4x = 0;
        start4y = 0;
        start5x = 0;
        start5y = 0;
    }

    public int[][] setStageValue() {
        switch (stage) {
            case 1:
                alienSet[0][0] = start1x;
                alienSet[0][1] = start1y;
                alienSet[0][2] = 80;
                alienSet[0][3] = 1;
                alienSet[0][4] = 30;
                return alienSet;
            case 2:
                alienSet[0][0] = start2x;
                alienSet[0][1] = start2y;
                alienSet[0][2] = 80;
                alienSet[0][3] = 3;
                alienSet[0][4] = 15;

                alienSet[1][0] = start2x;
                alienSet[1][1] = start2y;
                alienSet[1][2] = 90;
                alienSet[1][3] = 2;
                alienSet[1][4] = 25;
                return alienSet;
            case 3:
                alienSet[0][0] = start3x;
                alienSet[0][1] = start3y;
                alienSet[0][2] = 30;
                alienSet[0][3] = 4;
                alienSet[0][4] = 20;

                alienSet[1][0] = start3x;
                alienSet[1][1] = start3y;
                alienSet[1][2] = 50;
                alienSet[1][3] = 5;
                alienSet[1][4] = 20;

                alienSet[2][0] = start3x;
                alienSet[2][1] = start3y;
                alienSet[2][2] = 30;
                alienSet[2][3] = 6;
                alienSet[2][4] = 10;
                return alienSet;
            case 4:
                alienSet[0][0] = start4x;
                alienSet[0][1] = start4y;
                alienSet[0][2] = 80;
                alienSet[0][3] = 1;
                alienSet[0][4] = 20;

                alienSet[1][0] = start4x;
                alienSet[1][1] = start4y;
                alienSet[1][2] = 20;
                alienSet[1][3] = 5;
                alienSet[1][4] = 10;

                alienSet[2][0] = start4x;
                alienSet[2][1] = start4y;
                alienSet[2][2] = 50;
                alienSet[2][3] = 7;
                alienSet[2][4] = 10;

                alienSet[3][0] = start4x;
                alienSet[3][1] = start4y;
                alienSet[3][2] = 20;
                alienSet[3][3] = 8;
                alienSet[3][4] = 10;

                return alienSet;
            case 5:
                alienSet[0][0] = start5x;
                alienSet[0][1] = start5y;
                alienSet[0][2] = 80;
                alienSet[0][3] = 6;
                alienSet[0][4] = 10;

                alienSet[1][0] = start5x;
                alienSet[1][1] = start5y;
                alienSet[1][2] = 20;
                alienSet[1][3] = 7;
                alienSet[1][4] = 10;

                alienSet[2][0] = start5x;
                alienSet[2][1] = start5y;
                alienSet[2][2] = 60;
                alienSet[2][3] = 8;
                alienSet[2][4] = 10;

                alienSet[3][0] = start5x;
                alienSet[3][1] = start5y;
                alienSet[3][2] = 60;
                alienSet[3][3] = 9;
                alienSet[3][4] = 10;

                alienSet[4][0] = start5x;
                alienSet[4][1] = start5y;
                alienSet[4][2] = 50;
                alienSet[4][3] = 10;
                alienSet[4][4] = 10;
                return alienSet;
        }
        return alienSet;
    }

}
