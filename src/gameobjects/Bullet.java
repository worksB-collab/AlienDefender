/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import controllers.AudioControllerForAudioClip;
import controllers.DelayCounter;
import static values.Global.*;
import values.Path;
import java.awt.Graphics;
import javafx.scene.media.AudioClip;

/**
 *
 * @author billy
 */
public class Bullet extends EffectObject {

    private float direction;
    private float alienX;
    private float alienY;
    private float speedX;
    private float speedY;
    private int updateCount;
    private int towerNum;
    private float speed;
    private BulletHelper bHelper;
    private int ACT[] = {0, 1, 2, 3, 4};
    private int act;
    private DelayCounter delay;
    private AudioClip level1, level2, level3;
    private AudioControllerForAudioClip audioController;
    private Tower tower;

    public Bullet(float x, float y, Alien alien, Tower tower, float direction, float speed) {
        super(x, y, SIZE_GRID, SIZE_GRID);
        this.tower = tower;
        this.direction = direction;
        alienX = alien.getX();
        alienY = alien.getY();
        towerNum = tower.getTowerNum();
        setSpeed(speed);
        bHelper = new BulletHelper(towerNum);
        act = 0;
        delay = new DelayCounter(1);
        audioController = AudioControllerForAudioClip.genInstance();
        level1 = audioController.tryGetAudio(Path.Audios.Sounds.Attack.SHOT4);
        level2 = audioController.tryGetAudio(Path.Audios.Sounds.Attack.LEVEL2);
        level3 = audioController.tryGetAudio(Path.Audios.Sounds.Attack.LEVEL3);
        launch();
    }

    public boolean isReached() {
        if (updateCount >= 100) {
            return true;
        } else if (alienX + SIZE_GRID * 2 / 3 - DEVIATION >= super.getX()
                && alienX + SIZE_GRID / 3 + DEVIATION <= super.getX() + SIZE_GRID
                && alienY + SIZE_GRID * 2 / 3 - DEVIATION >= super.getY()
                && alienY + SIZE_GRID / 3 + DEVIATION <= super.getY() + SIZE_GRID) {
            return true;
        }
        return false;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void launch() {
        switch (tower.getUpgradeStage()) {
            case 0:
                level1.play();
                break;
            case 1:
                level2.play();
                break;
            case 2:
                level3.play();
                break;
        }
        updateCount = 0;
        float dX = alienX - super.getX();
        float dY = alienY - super.getY();
        if (dX != 0 || dY != 0) {
            float rateX = (dX) / (float) (Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2)));
            float rateY = (dY) / (float) (Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2)));
            speedX = rateX * speed;
            speedY = rateY * speed;
            return;
        }
    }

    @Override
    public void update() {
        super.setX(super.getX() + speedX);
        super.setY(super.getY() + speedY);
        updateCount++;
    }

    @Override
    public void paint(Graphics g) {
        if (isReached()) {
            bHelper.paintBoom(g, super.getX(), super.getY(), super.getWidth(), super.getHeight(), ACT[act], towerNum, direction);
            act = (act + 1) % 5;

        }
        bHelper.paint(g, super.getX(), super.getY(), super.getWidth(), super.getHeight(), towerNum, direction);
    }
}
