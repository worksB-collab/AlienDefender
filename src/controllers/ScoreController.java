/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;

/**
 *
 * @author billy
 */
public class ScoreController {

    private ArrayList<Integer> kills;
    public static ScoreController scoreController;
    private int totalScore;

    private ScoreController() {
        kills = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            kills.add(0);
        }
    }

    public static ScoreController genInstance() {
        if (scoreController == null) {
            scoreController = new ScoreController();
        }
        return scoreController;
    }

    public void setScore(int score) {
        totalScore = score;
    }

    public int scoreCount(int alienNum) {
        switch (alienNum) {
            case 0:
                kills.set(0, kills.get(0) + 1);
                break;
            case 1:
                kills.set(1, kills.get(1) + 1);
                break;
            case 2:
                kills.set(2, kills.get(2) + 1);
                break;
            case 3:
                kills.set(3, kills.get(3) + 1);
                break;
            case 4:
                kills.set(4, kills.get(4) + 1);
                break;
                case 5:
                kills.set(5, kills.get(5) + 1);
                break;
            case 6:
                kills.set(6, kills.get(6) + 1);
                break;
            case 7:
                kills.set(7, kills.get(7) + 1);
                break;
            case 8:
                kills.set(8, kills.get(8) + 1);
                break;
            case 9:
                kills.set(9, kills.get(9) + 1);
                break;
        
        }
        return 1;
    }

    public int scoreConverter() {
        totalScore += kills.get(0) * 2
                + kills.get(1) * 2
                + kills.get(2) * 3
                + kills.get(3) * 4
                + kills.get(4) * 5
                + kills.get(5) * 2
                + kills.get(6) * 3
                + kills.get(7) * 4
                + kills.get(8) * 5
                + kills.get(9) * 5;
        return totalScore;
    }

//    public int countPerKill(int alienNum) {
//        switch (alienNum) {
//            case 0:
//                return 1;
//            case 2:
//                return 2;
//            case 3:
//                return 3;
//            case 4:
//                return 4;
//            case 5:
//                return 5;
//        }
//        return -1;
//    }
    public ArrayList<Integer> getKills() {
        return kills;
    }
    
    public int getScore(){
        return totalScore;
    }

    public String toString() {
        String str = "";
        int count = 1;
        for (int kill : kills) {
            str += "Alien *" + kill + "\n";
            count++;
        }
        return str;
    }
}
