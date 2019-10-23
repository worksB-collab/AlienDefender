/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Value.DrawStringPoint;
import Value.Global;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author user
 */
public class PlayerController {

    public static PlayerController playerController;
    private String name;
    private long score;
    private DrawStringPoint scorePoint;
    private Font font;
    private int stage;
    private int money;
    private int hp;

    private PlayerController() {
        this.name = "Player";
        this.score = 0;
        this.stage = 1;
        font = Global.FONT_SCORE;
        money = 0;
        hp = 100;
    }

    public static PlayerController genInstance() {
        if (playerController == null) {
            playerController = new PlayerController();
        }
        return playerController;
    }

    //setter
    public void setName(String name) {
        this.name = name;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void addScore(long score) {
        this.score += score;
    }

    public void setState(int stage) {
        this.stage = stage;
    }

    public void addStage(int stage) {
        this.stage += stage;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public int getHP() {
        return hp;
    }

    public void update() {
        if (scorePoint != null) {
            if (scorePoint.getHeight() != Global.FRAME_HEIGHT) {
                scorePoint.setText(Long.toString(score));
                scorePoint.update(8 * Global.MIN_PICTURE_SIZE, 4 * Global.MIN_PICTURE_SIZE);
            }
        }
    }

    public void gainScore() {

    }

    public void paint(Graphics g) {
        if (scorePoint == null) {
            scorePoint = new DrawStringPoint(24f * Global.MIN_PICTURE_SIZE, 0, g, font, Long.toString(score), 8f * Global.MIN_PICTURE_SIZE, 4f * Global.MIN_PICTURE_SIZE);
        }
        g.setFont(font);
        g.drawString(scorePoint.getText(), (int) scorePoint.getX(), (int) scorePoint.getY());
    }

}
