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
        alienSet = new int [5][5];
    }

    public int[][] setStageValue() {
        switch (stage) {
            case 1:
                alienSet[0][0]=-25;
                alienSet[0][1]=50;
                alienSet[0][2]=80;
                alienSet[0][3]=1;
                alienSet[0][4]=10;
                break;
            case 2:
                alienSet[0][0]=-25;
                alienSet[0][1]=50;
                alienSet[0][2]=80;
                alienSet[0][3]=1;
                alienSet[0][4]=10;
                
                alienSet[0][0]=-25;
                alienSet[0][1]=50;
                alienSet[0][2]=20;
                alienSet[0][3]=2;
                alienSet[0][4]=10;
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
        return alienSet;
    }

}
