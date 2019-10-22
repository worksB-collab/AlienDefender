/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Controller.AlienController;
import Controller.BackgroundController;
import Controller.CommandSolver;
import Controller.CommandSolver.MouseCommandListener;
import Controller.CommandSolver.MouseState;
import Controller.DelayCounter;
import Controller.ImageController;
import Controller.PlayerController;
import Controller.SceneController;
import Controller.ScoreController;
import Controller.TowerController;
import GameObject.*;
import GameObject.Button;
import GameObject.Button.ButtonListener;
import Value.Global;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
    private BufferedImage image;
    private TowerSelectWindow towerSelectWindow;
    private TowerInformationWindow towerInformationWindow;
    private LinkedList<Point> standardRoute;
    private LinkedList<Point> route;
    private LinkedList<Point> setPoint;
    private LinkedList<Button> buttonList;
    private Point spot;
    private AlienController alienController;
    private TowerController towerController;

    public GameScene1(SceneController sceneController) {
        super(sceneController);
        playerController = PlayerController.genInstance();
        imageController = ImageController.genInstance();
        backgroundController = new BackgroundController(1);
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

                            spot = new Point((x / Global.MIN_PICTURE_SIZE) * Global.MIN_PICTURE_SIZE, (y / Global.MIN_PICTURE_SIZE) * Global.MIN_PICTURE_SIZE);
                        }
                    }
                }
                if (state == MouseState.MOVED) {
                    int x = e.getX();
                    int y = e.getY();
                    for (Button tmp : buttonList) {
                        if (tmp.isRange(x, y)) {
                            tmp.hover(x, y);

                            spot = new Point((x / Global.MIN_PICTURE_SIZE) * Global.MIN_PICTURE_SIZE, (y / Global.MIN_PICTURE_SIZE) * Global.MIN_PICTURE_SIZE);
                        }
                    }
                }
            }
        };

    }

    @Override
    public void sceneBegin() {
        genFixedRoad();

    }

    @Override
    public void sceneUpdate() {
        if (buttonList.size() != 0) {

            if (buttonList.get(0).getWidth() != Global.MIN_PICTURE_SIZE) {
//                reCheckSetPoint();
                genFixedRoad();

            }
        }
        for (Button button : buttonList) {
            button.update();
        }
        if (alienController == null) {
            alienController = new AlienController(route);
        }
        if (towerController == null) {
            towerController = new TowerController(alienController);
        }
        alienController.update();
        towerController.update();

        //TowerSelectWindow
        if (towerSelectWindow != null) {
            towerSelectWindow.update();
            if (towerSelectWindow.isEnd()) {
                Tower tower = towerSelectWindow.getResult();
                if (tower != null) {
                    towerController.getTowers().add(towerSelectWindow.getResult());
                }
                towerSelectWindow = null;
            }
        }
        //TowerInformationWindow
        if (towerInformationWindow != null) {
            towerInformationWindow.update();
            if (towerInformationWindow.isEnd()) {
                towerInformationWindow = null;
            }
        }
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        backgroundController.paint(g);
        paintRoad(g);
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
            g.drawRect((int) spot.getX(), (int) spot.getY(), Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE);
            g.setColor(Color.BLACK);
        }
        if (towerSelectWindow != null) {
            towerSelectWindow.paint(g);
        } else if (towerInformationWindow != null) {
            towerInformationWindow.paint(g);
        }
    }

    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener() {
        if (towerSelectWindow != null) {
            return towerSelectWindow.getMouseCommandListener();
        } else if (towerInformationWindow != null) {
            return towerInformationWindow.getMouseCommandListener();
        }
        return mouseCommandListener;
    }

    //paint
    private void paintGrass(Graphics g) {

    }

    private void paintRoad(Graphics g) {
        BufferedImage imgD = imageController.tryGetImage("/Resources/Images/Background/dirt.png");
        for (Point p : route) {
            g.drawImage(imgD, (int) p.getX(), (int) p.getY(), Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE, null);
        }
    }

    private void paintSetPoint(Graphics g) {
        BufferedImage imgS = imageController.tryGetImage("/Resources/Images/Background/setPoint.png");
        for (Point tmp : setPoint) {
            g.drawImage(imgS, (int) tmp.getX(), (int) tmp.getY(), Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE, null);
        }
    }

    //generate
    private void genRandomRoad() {
        route = new LinkedList();
        setPoint = new LinkedList();

        int x = 0;
        int y = Global.MIN_PICTURE_SIZE * 2;
        route.add(new Point(x, y));
        int maxX = Global.MIN_PICTURE_SIZE * 30;
        int maxY = Global.MIN_PICTURE_SIZE * 22;
        while (true) {
            int r = (int) (Math.random() * 2);
            if (r == 0 && x <= maxX) {
                x += Global.MIN_PICTURE_SIZE;
            } else {
                if (y > maxY) {
                    break;
                }
                y += Global.MIN_PICTURE_SIZE;

            }
            route.add(new Point(x, y));
        }
        genSetPoint();
    }

    private void genFixedRoad() {
        route = new LinkedList();
        setPoint = new LinkedList();

        int x = 0;
        int y = Global.MIN_PICTURE_SIZE * 2;
        route.add(new Point(x, y));
        int maxX = Global.MIN_PICTURE_SIZE * 30;
        int maxY = Global.MIN_PICTURE_SIZE * 22;
        int r = 0;
        while (true) {
            if (r == 0 && x <= maxX) {
                x += Global.MIN_PICTURE_SIZE;
                r = 1;
            } else {
                if (y > maxY) {
                    break;
                }
                y += Global.MIN_PICTURE_SIZE;
                r = 0;

            }
            route.add(new Point(x, y));
        }
        genSetPoint();
    }

    private void genStandardRoute() {
        standardRoute = new LinkedList<Point>();
        for (int i = 0; i < route.size(); i++) {
            Point p = route.get(i);
            standardRoute.add(new Point((int) p.getX(), (int) p.getY()));
        }
    }

    private void genSetPoint() {
        int maxX = 31 * Global.MIN_PICTURE_SIZE;
        int maxY = 23 * Global.MIN_PICTURE_SIZE;
        if (setPoint != null) {
            setPoint = new LinkedList<Point>();
        }
        for (Point p : route) {
            addSetPoint((int) p.getX(), (int) p.getY());
        }
        //delete route
        for (int i = 0; i < setPoint.size(); i++) {
            Point tmp = setPoint.get(i);
            if (tmp.getX() < 0 || tmp.getX() > maxX || tmp.getY() < 0 || tmp.getY() > maxY) {
                setPoint.remove(i--);
            }
            for (Point tmp2 : route) {
                if (tmp.getX() == tmp2.getX() && tmp.getY() == tmp2.getY()) {
                    setPoint.remove(i--);
                }
            }
        }
        //delete repeat point
        for (int i = 0; i < setPoint.size() - 1; i++) {
            Point p1 = setPoint.get(i);
            for (int j = i + 1; j < setPoint.size(); j++) {
                Point p2 = setPoint.get(j);
                if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
                    setPoint.remove(j--);
                }
            }
        }
        genButton();

    }

    private void reCheckSetPoint() {

        double d = Global.MIN_PICTURE_SIZE / (double) Global.STANDAR_MIN_SIZE;
        for (int i = 0; i < route.size() - 1; i++) {
            Point p = route.get(i);
            Point ps = standardRoute.get(i);
            System.out.println(ps.x);
            p.x = (int) (ps.getX() * d);
            System.out.println(p.x);
            p.y = (int) (ps.getY() * d);
        }
        genSetPoint();
    }

    private void addSetPoint(int x, int y) {
        //distance 25
        setPoint.add(new Point(x, y - Global.MIN_PICTURE_SIZE));
        setPoint.add(new Point(x, y + Global.MIN_PICTURE_SIZE));
        setPoint.add(new Point(x - Global.MIN_PICTURE_SIZE, y));
        setPoint.add(new Point(x + Global.MIN_PICTURE_SIZE, y));
        setPoint.add(new Point(x + Global.MIN_PICTURE_SIZE, y - Global.MIN_PICTURE_SIZE));
        setPoint.add(new Point(x - Global.MIN_PICTURE_SIZE, y + Global.MIN_PICTURE_SIZE));
    }

    private void genButton() {
        if (buttonList.size() != 0) {
            buttonList = new LinkedList<Button>();
        }

        for (Point tmp : setPoint) {
            int x0 = (int) tmp.getX();
            int y0 = (int) tmp.getY();
            Button button = new Button(x0, y0, Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE,
                    imageController.tryGetImage("/Resources/Images/Background/setPoint.png"),
                    imageController.tryGetImage("/Resources/Images/Background/setPoint.png"),
                    imageController.tryGetImage("/Resources/Images/Background/setPoint.png"));
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
                        towerSelectWindow = new TowerSelectWindow(x0, y0, 24 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE);
                    } else {
                        towerInformationWindow = new TowerInformationWindow(x0, y0, 24 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE, tower);
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
