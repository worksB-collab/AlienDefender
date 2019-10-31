/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import controllers.CommandSolver;
import controllers.ImageController;
import controllers.PlayerController;
import controllers.TowerController;
import gameobjects.Button;
import gameobjects.Button.ButtonListener;
import gameobjects.Tower;
import values.Global;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import values.DrawStringPoint;
import static values.Global.*;

/**
 *
 * @author user
 */
public class TowerInformationWindow extends TowerPopUpWindow {

    private ImageController imageController;
    private BufferedImage image, towerImage;
    private PlayerController playerController;
    private LinkedList<Button> buttonList;
    private LinkedList<Point> towerRange;
    private Tower tower;
    private int upgradeStage;
    private boolean isEnd;
    private String atkInfo, costInfo;
    private int hoveringTower;
    private DrawStringPoint infoString;

    public TowerInformationWindow(float x, float y, float width, float height, Tower tower, PlayerController playerController) {
        super(6.5f * Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE, width, height, null);
        if (y < 200) {
            super.setY(300);
        } else if (y < 400) {
            super.setY(500);
        } else if (y < 600) {
            super.setY(300);
        } else if (y < 800) {
            super.setY(500);
        }
        this.tower = tower;
        hoveringTower = tower.getTowerNum();
        this.upgradeStage = tower.getUpgradeStage();
        imageController = ImageController.genInstance();
        this.playerController = playerController;
        buttonList = new LinkedList<Button>();
        towerRange = new LinkedList();
        getButton(super.getX() - 2 * SIZE_GRID, super.getY());
        isEnd = false;
        super.mouseCommandListener = new CommandSolver.MouseCommandListener() {
            @Override
            // 關閉視窗
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == CommandSolver.MouseState.RELEASED || state == CommandSolver.MouseState.CLICKED) {
                    int x = e.getX();
                    int y = e.getY();
                    //check out of window's bound
                    if (x < 6.5f * Global.MIN_PICTURE_SIZE || x > (6.5f * Global.MIN_PICTURE_SIZE + width) || y < Global.MIN_PICTURE_SIZE || y > (Global.MIN_PICTURE_SIZE + height)) {
                        isEnd = true;
                    }

                    for (Button btn : buttonList) {
                        if (btn.isRange(x, y)) {
                            btn.click(x, y);
                            break;
                        }
                    }
                }
            }
        };
    }

    @Override
    public boolean isEnd() {
        return isEnd;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void update() {
        for (Button btn : buttonList) {
            btn.update();
        }
        if (upgradeStage == 2) {
            switch (hoveringTower) {

                case 0:
                    atkInfo = "Attack : " + (int) (TOWER0_ATK * Math.pow(1.3, upgradeStage));
                    costInfo = "Max Level";
                    return;
                case 1:
                    atkInfo = "Attack : " + (int) (TOWER1_ATK * Math.pow(1.3, upgradeStage));
                    costInfo = "Max Level";
                    return;
                case 2:
                    atkInfo = "Attack : " + (int) (TOWER2_ATK * Math.pow(1.3, upgradeStage));
                    costInfo = "Max Level";
                    return;
                case 3:
                    atkInfo = "Attack : " + (int) (TOWER3_ATK * Math.pow(1.3, upgradeStage));
                    costInfo = "Max Level";
                    return;
                case 4:
                    atkInfo = "Attack : " + (int) (TOWER4_ATK * Math.pow(1.3, upgradeStage));
                    costInfo = "Max Level";
                    return;
            }
        }
        switch (hoveringTower) {

            case 0:
                atkInfo = "Attack : " + (int) (TOWER0_ATK * Math.pow(1.3, upgradeStage)) + " 🢖🢖 " + (int) (TOWER0_ATK * Math.pow(1.3, upgradeStage + 1));
                costInfo = "Upgrade Cost : " + (int)(TOWER0_COST / 2);
                break;
            case 1:
                atkInfo = "Attack : " + (int) (TOWER1_ATK * Math.pow(1.3, upgradeStage)) + " 🢖🢖 " + (int) (TOWER1_ATK * Math.pow(1.3, upgradeStage + 1));
                costInfo =  "Upgrade Cost : " + (int)(TOWER1_COST / 2);
                break;
            case 2:
                atkInfo = "Attack : " + (int) (TOWER2_ATK * Math.pow(1.3, upgradeStage)) + " 🢖🢖 " + (int) (TOWER2_ATK * Math.pow(1.3, upgradeStage + 1));
                costInfo =  "Upgrade Cost : " + (int)(TOWER2_COST / 2);
                break;
            case 3:
                atkInfo = "Attack : " + (int) (TOWER3_ATK * Math.pow(1.3, upgradeStage)) + " 🢖🢖 " + (int) (TOWER3_ATK * Math.pow(1.3, upgradeStage + 1));
                costInfo =  "Upgrade Cost : " + (int)(TOWER3_COST / 2);
                break;
            case 4:
                atkInfo = "Attack : " + (int) (TOWER4_ATK * Math.pow(1.3, upgradeStage)) + " 🢖🢖 " + (int) (TOWER4_ATK * Math.pow(1.3, upgradeStage + 1));
                costInfo =  "Upgrade Cost : " + (int)(TOWER4_COST / 2);
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        //draw Tower Area
        towerRange = tower.getRange();
        Graphics2D k = (Graphics2D) g;
        k.setStroke(new BasicStroke(2f));
        k.setColor(Color.ORANGE);
        for (int i = 0; i < towerRange.size(); i++) {
            Point p = towerRange.get(i);
            k.drawRect((int) p.getX(), (int) p.getY(), (int) Global.MIN_PICTURE_SIZE, (int) Global.MIN_PICTURE_SIZE);
        }
        k.setColor(Color.BLACK);

        //draw PopUpWindow
        image = imageController.tryGetImage("/Resources/Images/Label/Tower_info_Label.png");
        towerImage = imageController.tryGetImage("/Resources/Images/GameObject/Tower2.png");
        int upgrade = upgradeStage + 1;
        if (upgradeStage == 2) {
            upgrade = 2;
        }
        g.drawImage(image, (int) super.getX() + 180, (int) super.getY(), (int)(SIZE_GRID * 12.5), (int) SIZE_GRID * 3, null);
        infoString = new DrawStringPoint(super.getX() + 180, (int) super.getY() + 80, g, FONT_INFOWINDOW, atkInfo, SIZE_GRID * 7, SIZE_GRID * 2);
        g.setColor(Color.white);
        g.setFont(FONT_INFOWINDOW);
        g.drawString(atkInfo, (int) (infoString.getX() + SIZE_GRID * 2.5), (int) (infoString.getY() - SIZE_GRID * 2.5));
        g.drawString(costInfo, (int) (infoString.getX() + SIZE_GRID * 2.5), (int) (infoString.getY() - SIZE_GRID * 1.5));
        g.drawImage(towerImage,
                (int) (infoString.getX() - SIZE_GRID), (int) (infoString.getY() - SIZE_GRID * 3.5),
                (int) (infoString.getX() + SIZE_GRID * 1.5), (int) (infoString.getY() - SIZE_GRID * 1),
                (int) (hoveringTower * SIZE_OBJECT), (int) SIZE_OBJECT * (upgrade),
                (int) (hoveringTower * SIZE_OBJECT + SIZE_OBJECT), (int) SIZE_OBJECT * (upgrade + 1),
                null);

        for (Button btn : buttonList) {
            btn.paint(g);
        }
    }

    private void getButton(float x0, float y0) {

        BufferedImage img = imageController.tryGetImage("/Resources/Images/Label/upgrade.png");
        Button upgradeButton = new Button(x0 - 2 * MIN_PICTURE_SIZE + 19 * MIN_PICTURE_SIZE, y0 + (int) (0.5 * MIN_PICTURE_SIZE), 2 * MIN_PICTURE_SIZE, 2 * MIN_PICTURE_SIZE, img);
        ButtonListener buttonListener2 = new Button.ButtonListener() {

            //
            @Override
            public void onClick(int x, int y) {
                if (playerController.isEnough(TowerController.upgradeCostArr[tower.getTowerNum()])) {
                    if (tower.upgrade()) {
                        playerController.setMoney(playerController.getMoney() - TowerController.upgradeCostArr[tower.getTowerNum()]);
                    }

                }

            }

            @Override
            public void hover(int x, int y) {

            }

        };
        if (upgradeStage != 2) {
            upgradeButton.setButtonListener(buttonListener2);
            buttonList.add(upgradeButton);
        }
    }

}
