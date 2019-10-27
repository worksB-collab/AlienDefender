/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import value.DrawStringPoint;
import value.Global;
import static value.Global.FONT_HP;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class PlayerController {

    public static PlayerController playerController;
    private String name;
    private long score;
    private DrawStringPoint namePoint,scorePoint, hpPoint, moneyPoint;
    private ImageController imageController;
    private BufferedImage hpImage[];
    private int stage;
    private long money;
    private int hp;
    private float ratio;

    private PlayerController() {
        this.name = "Player";
        this.score = 0;
        this.stage = 1;
        this.money = 300;
        this.hp = 100;
        this.ratio = 1f;
        imageController = ImageController.genInstance();
        hpImage = new BufferedImage[2];
        hpImage[0] = imageController.tryGetImage("/Resources/Images/GameObject/BloodLineInner.png");
        hpImage[1] = imageController.tryGetImage("/Resources/Images/GameObject/BloodLineOutter.png");
    }

    public static PlayerController genInstance() {
        if (playerController == null) {
            playerController = new PlayerController();
        }
        return playerController;
    }

    //setter
    
    public void setMoney(long money){
        this.money = money;
    }
    public long getMoney(){
        return money;
    }
    public boolean isEnough(long money){
        if(this.money < money){
            return false;
        }
        return true;
    }
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
        if(this.hp <= 0){
            this.hp = 0;
            return;
        }
        this.hp = hp;
    }

    public int getHP() {
        return hp;
    }

    public void update() {
        if(namePoint != null){
            if(!namePoint.getText().equals(name)){
                namePoint.setText(name);
            }
        }
        if (scorePoint != null) {
            scorePoint.setText(Long.toString(score));
            if (scorePoint.getHeight() != Global.FRAME_HEIGHT) {
                scorePoint.update(8 * Global.MIN_PICTURE_SIZE, 4 * Global.MIN_PICTURE_SIZE);
            }
        }
        if (hpPoint != null) {
            hpPoint.setText(Long.toString(hp));
            if (hpPoint.getHeight() != Global.FRAME_HEIGHT) {
                hpPoint.update(4 * Global.MIN_PICTURE_SIZE, 4 * Global.MIN_PICTURE_SIZE);
            }
        }
        if(moneyPoint != null){
            moneyPoint.setText(Long.toString(money));
            if (moneyPoint.getHeight() != Global.FRAME_HEIGHT){
                moneyPoint.update(2 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE);
            }
        }
        
        if(ratio >= 0){
          ratio = ((float)hp / 100f);  
        }else{
            ratio = 0;
        }

    }                                         
    public void paint(Graphics g) {
        if(namePoint == null){
            namePoint = new DrawStringPoint(24f * Global.MIN_PICTURE_SIZE, 0, g, Global.FONT_NAME, name, 2f * Global.MIN_PICTURE_SIZE, 2f * Global.MIN_PICTURE_SIZE);
        }
        if (scorePoint == null) {
            scorePoint = new DrawStringPoint(21f * Global.MIN_PICTURE_SIZE, 2f * Global.MIN_PICTURE_SIZE, g, Global.FONT_SCORE, Long.toString(score), 4f * Global.MIN_PICTURE_SIZE, 4f * Global.MIN_PICTURE_SIZE);
        }
        if (hpPoint == null) {
            hpPoint = new DrawStringPoint(28f * Global.MIN_PICTURE_SIZE, 0.5f * Global.MIN_PICTURE_SIZE, g, Global.FONT_HP, Long.toString(hp), 4f * Global.MIN_PICTURE_SIZE, 4f * Global.MIN_PICTURE_SIZE);
        }
        if(moneyPoint == null){
            moneyPoint = new DrawStringPoint(24f * Global.MIN_PICTURE_SIZE, 4.5f * Global.MIN_PICTURE_SIZE, g, Global.FONT_MONEY, Long.toString(money), 4f * Global.MIN_PICTURE_SIZE, 2f * Global.MIN_PICTURE_SIZE);
        }
        //drawHp
        g.drawImage(hpImage[0], (int)(17f * Global.MIN_PICTURE_SIZE) + (int)((1 - ratio) * (12f * Global.MIN_PICTURE_SIZE)),  (int)(2 * Global.MIN_PICTURE_SIZE),
                                (int)( ratio * (12f * Global.MIN_PICTURE_SIZE) ), (int)(1f * Global.MIN_PICTURE_SIZE), null);
        g.drawImage(hpImage[1], (int)(17f * Global.MIN_PICTURE_SIZE),  (int)(2 * Global.MIN_PICTURE_SIZE), 
                                (int)(12f * Global.MIN_PICTURE_SIZE), (int)(1f * Global.MIN_PICTURE_SIZE), null);
        
        g.setColor(Color.white);
        //drawName
        g.setFont(namePoint.getFont());
        g.drawString(namePoint.getText(), (int)(namePoint.getX()), (int)(namePoint.getY()));
        //drawHP
        g.setFont(hpPoint.getFont());
        g.drawString(hpPoint.getText() , (int) (hpPoint.getX()), (int) (hpPoint.getY()));
        //drawMoney
        g.setFont(moneyPoint.getFont());
        g.drawString(moneyPoint.getText(), (int)(moneyPoint.getX()), (int)(moneyPoint.getY()));
        g.drawString(" Coin", (int)(28.5f * Global.MIN_PICTURE_SIZE), (int)(6f*Global.MIN_PICTURE_SIZE));

        //drawScore
        
        g.setFont(scorePoint.getFont());
        g.drawString(scorePoint.getText(), (int) (scorePoint.getX()), (int) (scorePoint.getY()));
        g.drawString(" Kill", (int)(29f * Global.MIN_PICTURE_SIZE), (int)(4.5f*Global.MIN_PICTURE_SIZE));
        //reset
        g.setColor(Color.black);
    }

}
