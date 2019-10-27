/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import controller.AlienController;
import controller.BackgroundController;
import controller.CommandSolver;
import controller.CommandSolver.MouseCommandListener;
import controller.CommandSolver.MouseState;
import controller.ImageController;
import controller.PlayerController;
import controller.RouteController;
import controller.RouteController.RoutePoint;
import controller.SceneController;
import controller.TowerController;
import gameobject.*;
import gameobject.Button;
import gameobject.Button.ButtonListener;
import value.Global;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class GameScene1 extends Scene {

    private CommandSolver.MouseCommandListener mouseCommandListener;
    private PlayerController playerController;
    private BackgroundController backgroundController;
    private ImageController imageController;
    private RouteController routeController;
    private PopUpWindow popUpWindow;
    private LinkedList<Button> buttonList;
    private Point spot;
    private AlienController alienController;
    private TowerController towerController;

    public GameScene1(SceneController sceneController) {
        super(sceneController);
        playerController = PlayerController.genInstance();
        imageController = ImageController.genInstance();
        backgroundController = new BackgroundController(2);
        routeController = new RouteController();
        buttonList = new LinkedList();

        mouseCommandListener = new MouseCommandListener() {

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == MouseState.RELEASED || state == MouseState.CLICKED) {
                    int x = e.getX();
                    int y = e.getY();
                    for (Button tmp : buttonList) {
                        if (tmp.isRange(x, y)) {
                            tmp.click(x, y);

                            spot = new Point(x, y);
                        }
                    }
                }
                if (state == MouseState.MOVED) {
                    int x = e.getX();
                    int y = e.getY();
                    for (Button tmp : buttonList) {
                        if (tmp.isRange(x, y)) {
                            tmp.hover(x, y);
                            spot = new Point(x, y);
                        }
                    }
                }
            }
        };

    }

    @Override
    public void sceneBegin() {
        routeController.genRoad(1);
        genButton(routeController.getSetPoint());
        alienController = new AlienController(routeController.getRoute());
        alienController.gameLevelSetting(-25, 50, 80, 1, 50);
    }

    @Override
    public void sceneUpdate() {
        backgroundController.update();
        if (buttonList.size() != 0) {

            if (buttonList.get(0).getWidth() != Global.MIN_PICTURE_SIZE) {
                routeController.genRoad(1);
                genButton(routeController.getSetPoint());
            }
        }
        for (Button button : buttonList) {
            button.update();
        }
        
        alienController.update();
        
        if (towerController == null) {
            towerController = new TowerController(alienController);
        }
        //Player
        playerController.update();
        alienController.update();
        towerController.update();
        
        if(popUpWindow != null){
           popUpWindow.update();
           if (popUpWindow.isEnd()){
               popUpWindow = null;
           }
        }
//        //TowerSelectWindow
//        if (towerSelectWindow != null) {
//            towerSelectWindow.update();
//            if (towerSelectWindow.isEnd()) {
//                Tower tower = towerSelectWindow.getResult();
//                if (tower != null) {
//                    towerController.getTowers().add(towerSelectWindow.getResult());
//                }
//                towerSelectWindow = null;
//            }
//        }
//        //TowerInformationWindow
//        if (towerInformationWindow != null) {
//            towerInformationWindow.update();
//            if (towerInformationWindow.isEnd()) {
//                towerInformationWindow = null;
//            }
//        }
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        backgroundController.paint(g);
        routeController.paint(g);
        //Button paint
        if (buttonList != null) {
            for (Button button : buttonList) {
                button.paint(g);
            }
        }

        alienController.paint(g);
        towerController.paint(g);

        if (spot != null) {
            g.setColor(Color.red);
            g.drawRect((int)spot.getX() / (int)Global.MIN_PICTURE_SIZE * (int)Global.MIN_PICTURE_SIZE, (int)spot.getY() / (int)Global.MIN_PICTURE_SIZE * (int)Global.MIN_PICTURE_SIZE, (int)Global.MIN_PICTURE_SIZE, (int)Global.MIN_PICTURE_SIZE);
            g.setColor(Color.BLACK);
        }
        //player
        playerController.paint(g);
        //PopUpWindow
        if(popUpWindow != null){
            popUpWindow.paint(g);
        }
//        if (towerSelectWindow != null) {
//            towerSelectWindow.paint(g);
//        } else if (towerInformationWindow != null) {
//            towerInformationWindow.paint(g);
//        }
    }

    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener() {
        if(popUpWindow != null){
            return popUpWindow.getMouseCommandListener();
        }
//        if (towerSelectWindow != null) {
//            return towerSelectWindow.getMouseCommandListener();
//        } else if (towerInformationWindow != null) {
//            return towerInformationWindow.getMouseCommandListener();
//        }
        return mouseCommandListener;
    }
    //generate
    private void genButton(LinkedList<RoutePoint> setPoint) {
        if (buttonList.size() != 0) {
            buttonList = new LinkedList<Button>();
        }

        for (RoutePoint tmp : setPoint) {
            float x0 = tmp.getX();
            float y0 = tmp.getY();
            Button button = new Button(x0, y0, Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE,
                    imageController.tryGetImage("/Resources/Images/Background/setPoint5.png"));
            
            button.setButtonListener(new ButtonListener() {
                @Override
                public void onClick(int x, int y) {
                    boolean isBuilt = false;
                    Tower tower = null;
                    for (int i = 0; i < towerController.getTowers().size(); i++) {
                        tower = towerController.getTowers().get(i);
                        if (tower.getX() == x0 && tower.getY() == y0) {
                            isBuilt = true;
                            break;
                        }
                    }
                    if (!isBuilt) {
                        popUpWindow = new TowerSelectWindow(x0, y0, 137/7 * Global.MIN_PICTURE_SIZE, 41/20 * Global.MIN_PICTURE_SIZE, towerController);
                    } else {
                        popUpWindow = new TowerInformationWindow(x0, y0, 137/7 * Global.MIN_PICTURE_SIZE, 41/20 * Global.MIN_PICTURE_SIZE, tower, playerController);
                    }

                }

                @Override
                public void hover(int x, int y) {

                }

            });
            buttonList.add(button);
        }
    }
}
