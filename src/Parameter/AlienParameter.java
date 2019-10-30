/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parameter;

import static values.Global.*;

/**
 *
 * @author billy
 */
public class AlienParameter {

    private float [][] alienSet;
    private int stage;
    private float start1x, start1y, start2x, start2y, start3x, start3y,start4x, start4y, start5x, start5y;

    public AlienParameter(int stage) {
        this.stage = stage;
        alienSet = new float[5][5];
        start1x = START_POINT1[0];
        start1y = START_POINT1[1];
        start2x = START_POINT2[0];
        start2y = START_POINT2[1];
        start3x = START_POINT3[0];
        start3y = START_POINT3[1];
        start4x = START_POINT4[0];
        start4y = START_POINT4[1];
        start5x = START_POINT5[0];
        start5y = START_POINT5[1];
    }

    public float[][] setStageValue() {//  x,  y,  frequency,  type,  alienQuantity
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
