/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Controller.CommandSolver;
import Controller.CommandSolver.MouseCommandListener;
import Controller.CommandSolver.MouseState;
import Controller.DelayCounter;
import Controller.ImageController;
import Controller.SceneController;
import GameObject.*;
import static Value.Global.*;
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
    private ImageController imageController;
    private BufferedImage image;
    private PopUpWindow popWindow;
    private LinkedList<Point> standardRoute;
    private LinkedList<Point> route;
    private LinkedList<Point> setPoint;
    private LinkedList<Button> buttonList;
    private LinkedList<Alien> aliens;
    private LinkedList<Alien> deadAliens; //
    private LinkedList<Tower> towers;
    private int count;
    private DelayCounter moveDelay, genDelay;
    private Point spot;
    private ArrayList<Integer> kills;

    public GameScene1(SceneController sceneController) {
        super(sceneController);
        buttonList = new LinkedList();
        imageController = ImageController.genInstance();
        aliens = new LinkedList<Alien>();
        deadAliens = new LinkedList<Alien>(); //
        towers = new LinkedList<Tower>();
        towers.add(new Tower1(50, 75));
        moveDelay = new DelayCounter(1);
        genDelay = new DelayCounter(5);
        kills = new ArrayList<Integer>();
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
        
        if (moveDelay.update()) {
            if (count < 50) { // first chapter > 50 aliens
                if (genDelay.update()) {
                    genAlien();
                }
            }
            for (int i = 0; i < aliens.size(); i++) {
                aliens.get(i).update();
                if (aliens.get(i).getY() >= 639) {
                    aliens.remove(i);
                }
                if (aliens.get(i).isDead())// kill count, alien type yet updated
                {
                    switch (aliens.get(i).getAlienNum()) {
                        case 1:
                            kills.set(0, kills.get(0) + 1);
                            break;
                        case 2:
                            kills.set(1, kills.get(1) + 1);
                            break;
                        case 3:
                            kills.set(2, kills.get(2) + 1);
                            break;
                        case 4:
                            kills.set(3, kills.get(3) + 1);
                            break;
                        case 5:
                            kills.set(4, kills.get(4) + 1);
                            break;
                    }
                }
            }
        }
        removeAlien();

        if (aliens.size() != 0) {
            for (int i = 0; i < towers.size(); i++) {
                for (int j = 0; j < aliens.size(); j++) {
                    towers.get(i).detection(aliens.get(j));
                }
            }
        }

        if (popWindow != null) {
            popWindow.update();
            if (popWindow.isEnd()) {
                popWindow.getResult();
                popWindow = null;
            }
        }
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        paintGrass(g);
        paintRoad(g);
        if (buttonList != null) {
            for (Button button : buttonList) {
                button.paint(g);
            }
        }
        for (int i = 0; i < aliens.size(); i++) {
            aliens.get(i).paint(g);
        }
        for (int i = 0; i < towers.size(); i++) {
            towers.get(i).paint(g);
        }
        if (popWindow != null) {
            popWindow.paint(g);
            g.setColor(Color.red);
            g.drawRect((int) spot.getX(), (int) spot.getY(), Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE);
            g.setColor(Color.BLACK);
        }
    }

    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener() {
        if (popWindow != null) {
            return popWindow.getMouseCommandListener();
        }
        return mouseCommandListener;
    }
    
    //paint
    private void paintGrass(Graphics g) {
        BufferedImage imgG = imageController.tryGetImage("/Resources/Images/Background/grass.png");
        int x0, y0;
        x0 = y0 = 0;
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 32; j++) {
                g.drawImage(imgG, x0, y0, Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE, null);
                x0 += Global.MIN_PICTURE_SIZE;
            }
            x0 = 0;
            y0 += Global.MIN_PICTURE_SIZE;
        }
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
    private void genStandardRoute(){
        standardRoute = new LinkedList<Point>();
        for(int i = 0; i < route.size(); i++){
            Point p = route.get(i);
            standardRoute.add(new Point((int)p.getX(), (int)p.getY()));
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
        for (int i = 0; i < setPoint.size(); i++) {
//            System.out.println("SetPoint " + (i + 1) + " :\t" + setPoint.get(i).getX() + "," + setPoint.get(i).getY());
        }
        genButton();

    }

    private void reCheckSetPoint() {

        double d = Global.MIN_PICTURE_SIZE / (double)Global.STANDAR_MIN_SIZE;
        for(int i = 0 ; i < route.size() - 1; i++){
            Point p = route.get(i);
            Point ps = standardRoute.get(i);
            System.out.println(ps.x);
            p.x = (int)(ps.getX() * d);
            System.out.println(p.x);
            p.y = (int)(ps.getY() * d) ;
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
                    imageController.tryGetImage("/Resources/Images/Background/setPoint_clicked.png"),
                    imageController.tryGetImage("/Resources/Images/Background/setPoint.png"));
            button.setButtonListener(new ButtonListener() {
                @Override
                public void onClick(int x, int y) {
                    popWindow = new TowerSelectWindow(4 * Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE, 24 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE);
                }

                @Override
                public void hover(int x, int y) {

                }

            });
            buttonList.add(button);
        }
    }

    private void genAlien() {
        if ((int) (Math.random() * 100) > 10) {
            aliens.add(new Alien1(-25, 50));
            Alien.setRoute(route);
            count++;
        }
    }

    private void removeAlien() {
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).isDead()) {
                aliens.remove(i--);
            }
        }
    }

}
