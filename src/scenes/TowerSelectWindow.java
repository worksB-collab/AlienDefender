/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import controllers.CommandSolver;
import controllers.CommandSolver.MouseCommandListener;
import controllers.CommandSolver.MouseState;
import controllers.ImageController;
import controllers.PlayerController;
import controllers.TowerController;
import gameobjects.*;
import gameobjects.Button.ButtonListener;
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
public class TowerSelectWindow extends TowerPopUpWindow {

    public static final String TYPE = "TowerSelectWindow";
    private ImageController imageController;
    private BufferedImage image, towerImage;
    private PlayerController playerController;
    private LinkedList<Button> buttonList;
    private Tower tower;
    private LinkedList<Point> towerRange;
    private boolean isEnd;
    private boolean isHovering;
    private String atkInfo, costInfo;
    private int hoveringTower;
    private DrawStringPoint infoString;

    public TowerSelectWindow(float x, float y, float width, float height, TowerController towerController) {
        super(6.5f * MIN_PICTURE_SIZE, MIN_PICTURE_SIZE, width, height, towerController);
        if (y < 200) {
            super.setY(300);
        } else if (y < 400) {
            super.setY(500);
        } else if (y < 600) {
            super.setY(300);}
        else if (y < 800) {
            super.setY(500);}
            imageController = ImageController.genInstance();
            playerController = PlayerController.genInstance();

            buttonList = new LinkedList<Button>();
            isEnd = false;
            isHovering = false;
            hoveringTower = -1;
            super.mouseCommandListener = new MouseCommandListener() {
                @Override
                public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                    if (state == MouseState.RELEASED || state == MouseState.CLICKED) {
                        int x = e.getX();
                        int y = e.getY();
                        for (Button btn : buttonList) {
                            if (btn.isRange(x, y)) {
                                btn.click(x, y);
                                break;
                            }
                        }
                    }
                    if (state == MouseState.MOVED) {
                        int x = e.getX();
                        int y = e.getY();
                        for (Button btn : buttonList) {
                            if (btn.isRange(x, y)) {
                                btn.hover(x, y);
                                break;
                            }
                        }
                    }
                }
            };
            genButton(x, y);
        }

    

    public Tower getResult() {
        return tower;
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
        switch (hoveringTower) {
            case 0:
                atkInfo = "Attack: " + (int)TOWER0_ATK;
                costInfo = "$: " + (int)TOWER0_COST;
                break;
            case 1:
                atkInfo = "Attack: " + (int)TOWER1_ATK;
                costInfo = "$: " + (int)TOWER1_COST;
                break;
            case 2:
                atkInfo = "Attack: " + (int)TOWER2_ATK;
                costInfo = "$: " + (int)TOWER2_COST;
                break;
            case 3:
                atkInfo = "Attack: " + (int)TOWER3_ATK;
                costInfo = "$: " + (int)TOWER3_COST;
                break;
            case 4:
                atkInfo = "Attack: " + (int)TOWER4_ATK;
                costInfo = "$: " + (int)TOWER4_COST;
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        image = imageController.tryGetImage("/Resources/Images/Label/Tower_generate_Label5.png");
        g.drawImage(image, (int) super.getX(), (int) super.getY(), (int) width, (int) height, null);
        for (Button btn : buttonList) {
            btn.paint(g);
        }
        //tower info 
        if (isHovering) {
            image = imageController.tryGetImage("/Resources/Images/Label/Tower_info_Label.png");
            towerImage = imageController.tryGetImage("/Resources/Images/GameObject/Tower2.png");
            g.drawImage(image, (int) super.getX() + 150, (int) super.getY() + 80, (int) SIZE_GRID * 9, (int) SIZE_GRID * 3, null);
            
//            infoString = new DrawStringPoint(super.getX() + 180, (int) super.getY() + 80, g, FONT_INFOWINDOW, atkInfo, SIZE_GRID * 5, SIZE_GRID * 2);
            g.setColor(Color.white);
            g.setFont(FONT_INFOWINDOW);
            
            
            
            g.drawString(atkInfo, (int) (super.getX() + SIZE_GRID * 8.5), (int) (super.getY()+SIZE_GRID*3.5));
            g.drawString(costInfo, (int) (super.getX() + SIZE_GRID * 8.5), (int) (super.getY() + SIZE_GRID*4.5));
            //draw preview tower image
            g.drawImage(towerImage, (int) (super.getX() + SIZE_GRID*5.5), (int) (super.getY() + SIZE_GRID*3),
                    (int) (super.getX() + SIZE_GRID * 7.5), (int) (super.getY() + SIZE_GRID * 5),
                    (int) (hoveringTower * SIZE_OBJECT), 0, (int) (hoveringTower * SIZE_OBJECT + SIZE_OBJECT), (int) SIZE_OBJECT, null);
        }

        if (towerRange != null) {
            Graphics2D k = (Graphics2D) g;
            k.setStroke(new BasicStroke(2f));
            k.setColor(Color.ORANGE);
            for (int i = 0; i < towerRange.size(); i++) {
                Point p = towerRange.get(i);
                k.drawRect((int) p.getX(), (int) p.getY(), (int) MIN_PICTURE_SIZE, (int) MIN_PICTURE_SIZE);
            }
            k.setColor(Color.BLACK);
        }
    }

    private void genButton(float x0, float y0) {

        BufferedImage img[] = {imageController.tryGetImage("/Resources/Images/Label/Tower_Icon1.png"),
            imageController.tryGetImage("/Resources/Images/Label/Tower_Icon2.png"),
            imageController.tryGetImage("/Resources/Images/Label/Tower_Icon3.png"),
            imageController.tryGetImage("/Resources/Images/Label/Tower_Icon4.png"),
            imageController.tryGetImage("/Resources/Images/Label/Tower_Icon5.png"),
            imageController.tryGetImage("/Resources/Images/Label/Exit.png")};
        ButtonListener buttonListener[] = new ButtonListener[6];
        TowerController towerController = super.getTowerController();

        buttonListener[0] = new ButtonListener() {

            @Override
            public void onClick(int x, int y) {
                if (playerController.isEnough(TowerController.costArr[0])) {
                    playerController.setMoney(playerController.getMoney() - TowerController.costArr[0]);
                    tower = new Tower1(x0, y0);
                    towerController.getTowers().add(tower);
                    isEnd = true;
                }

            }

            @Override
            public void hover(int x, int y) {
                towerController.genRange(new Point((int) x0, (int) y0), 0);
                towerRange = towerController.getRange();
                isHovering = true;
                hoveringTower = 0;
            }

        };

        buttonListener[1] = new ButtonListener() {

            @Override
            public void onClick(int x, int y) {
                if (playerController.isEnough(TowerController.costArr[1])) {
                    playerController.setMoney(playerController.getMoney() - TowerController.costArr[1]);
                    tower = new Tower2(x0, y0);
                    towerController.getTowers().add(tower);
                    isEnd = true;
                }
            }

            @Override
            public void hover(int x, int y) {
                towerController.genRange(new Point((int) x0, (int) y0), 1);
                towerRange = towerController.getRange();
                isHovering = true;
                hoveringTower = 1;
            }

        };

        buttonListener[2] = new ButtonListener() {

            @Override
            public void onClick(int x, int y) {
                if (playerController.isEnough(TowerController.costArr[2])) {
                    playerController.setMoney(playerController.getMoney() - TowerController.costArr[2]);
                    tower = new Tower3(x0, y0);
                    towerController.getTowers().add(tower);
                    isEnd = true;
                }
            }

            @Override
            public void hover(int x, int y) {
                towerController.genRange(new Point((int) x0, (int) y0), 2);
                towerRange = towerController.getRange();
                isHovering = true;
                hoveringTower = 2;
            }

        };

        buttonListener[3] = new ButtonListener() {

            @Override
            public void onClick(int x, int y) {
                if (playerController.isEnough(TowerController.costArr[3])) {
                    playerController.setMoney(playerController.getMoney() - TowerController.costArr[3]);
                    tower = new Tower4(x0, y0);
                    towerController.getTowers().add(tower);
                    isEnd = true;
                }
            }

            @Override
            public void hover(int x, int y) {
                towerController.genRange(new Point((int) x0, (int) y0), 3);
                towerRange = towerController.getRange();
                isHovering = true;
                hoveringTower = 3;
            }

        };

        buttonListener[4] = new ButtonListener() {

            @Override
            public void onClick(int x, int y) {
                if (playerController.isEnough(TowerController.costArr[4])) {
                    playerController.setMoney(playerController.getMoney() - TowerController.costArr[4]);
                    tower = new Tower5(x0, y0);
                    towerController.getTowers().add(tower);
                    isEnd = true;
                }
            }

            @Override
            public void hover(int x, int y) {
                towerController.genRange(new Point((int) x0, (int) y0), 4);
                towerRange = towerController.getRange();
                isHovering = true;
                hoveringTower = 4;
            }

        };

        buttonListener[5] = new ButtonListener() {

            @Override
            public void onClick(int x, int y) {
                isEnd = true;
            }

            @Override
            public void hover(int x, int y) {

            }

        };

        float dx = 10f;
        float dy = 5f;
        float spece = 10f;
        float x1 = super.getX() + dx;
        float y1 = super.getY() + dy;
        float w = (width - (2f * dx) - (spece * (img.length - 1))) / img.length;
        float h = height - (2f * dy);
        for (int i = 0; i < img.length; i++) {
            Button button = new Button(x1, y1, w, h, img[i]);
            button.setButtonListener(buttonListener[i]);
            x1 += (w + dx);
            buttonList.add(button);
        }
    }
}
