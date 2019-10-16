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
import GameObject.*; // new
import static Value.Global.*; // new
import GameObject.Button;
import GameObject.Button.ButtonListener;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
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
    private LinkedList<Point> route;
    private LinkedList<Point> setPoint;
    private LinkedList<Button> buttonList;
    private LinkedList<Alien> aliens; // new
    private int count;
    private DelayCounter moveDelay, genDelay;

    public GameScene1(SceneController sceneController) {
        super(sceneController);
        buttonList = new LinkedList();
        imageController = ImageController.genInstance();
        aliens = new LinkedList<Alien>();
        moveDelay = new DelayCounter(1);
        genDelay = new DelayCounter(5);
        mouseCommandListener = new MouseCommandListener() {

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == MouseState.CLICKED) {
                    int x = e.getX();
                    int y = e.getY();
                    for (Button tmp : buttonList) {
                        if (tmp.isRange(x, y)) {
                            tmp.click(x, y);
                        }
                    }

                }
            }
        };

    }

    @Override
    public void sceneBegin() {
        genRoad();

    }

    @Override
    public void sceneUpdate() {
        for (Button button : buttonList) {
            button.update();
        }
        if (moveDelay.update()) {
            if (count < 50) { // first chapter > 50 aliens
                if (genDelay.update()) {
                    genAlien();
                }
            }
            for(int i =0; i <aliens.size(); i++){
                if(aliens.get(i).getY()>=639)
                    aliens.remove(i);
            }
            removeAlien();
            for (Alien alien : aliens) {
                alien.update();
            }
        }
        if(popWindow != null){
            popWindow.update();
            if(popWindow.isEnd()){
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
        for (Button button : buttonList) {
            button.paint(g);
        }
        for (Alien alien : aliens) {
            alien.paint(g);
        }
        if(popWindow != null){
            popWindow.paint(g);
        }
    }

    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener() {
        if(popWindow != null){
            return popWindow.getMouseCommandListener();
        }
        return mouseCommandListener;
    }

    private void paintGrass(Graphics g) {
        BufferedImage imgG = imageController.tryGetImage("/Resources/Images/Background/grass.png");
        int x0, y0;
        x0 = y0 = 0;
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 32; j++) {
                g.drawImage(imgG, x0, y0, 25, 25, null);
                x0 += 25;
            }
            x0 = 0;
            y0 += 25;
        }
    }

    private void genRoad() {
        route = new LinkedList();
        setPoint = new LinkedList();

        int x = 0;
        int y = 50;
        route.add(new Point(x, y));
        addSetPoint(x, y);

        while (true) {
            int r = (int) (Math.random() * 2);
            if (r == 0 && x <= 750) {
                x += 25;
            } else {
                if (y > 550) {
                    break;
                }
                y += 25;

            }
            route.add(new Point(x, y));
            addSetPoint(x, y);
        }
        genSetPoint();
        
        
    }

    private void genSetPoint() {
        //delete route
        for (int i = 0; i < setPoint.size(); i++) {
            Point tmp = setPoint.get(i);
            if (tmp.getX() < 0 || tmp.getX() > 775 || tmp.getY() < 0 || tmp.getY() > 575) {
                setPoint.remove(i--);
            }
            for (Point tmp2 : route) {
                if (tmp.getX() == tmp2.getX() && tmp.getY() == tmp2.getY()) {
                    setPoint.remove(i--);
                }
            }
        }
        //delete repeat point
        for(int i = 0; i < setPoint.size() - 1; i++){
            Point p1 = setPoint.get(i);
            for(int j = i + 1; j < setPoint.size(); j++){
               Point p2 = setPoint.get(j);
                if(p1.getX() == p2.getX() && p1.getY() == p2.getY()){
                    setPoint.remove(j--);
                }
            }
        }
        genButton();
    }

    private void paintRoad(Graphics g) {
        BufferedImage imgD = imageController.tryGetImage("/Resources/Images/Background/dirt.png");
        for (Point p : route) {
            g.drawImage(imgD, (int) p.getX(), (int) p.getY(), 25, 25, null);
        }
    }

    private void paintSetPoint(Graphics g) {
        BufferedImage imgS = imageController.tryGetImage("/Resources/Images/Background/setPoint.png");
        for (Point tmp : setPoint) {
            g.drawImage(imgS, (int) tmp.getX(), (int) tmp.getY(), 25, 25, null);
        }
    }

    private void addSetPoint(int x, int y) {
        //distance 25
        setPoint.add(new Point(x, y - 25));
        setPoint.add(new Point(x, y + 25));
        setPoint.add(new Point(x - 25, y));
        setPoint.add(new Point(x + 25, y));

        //distance 50(2 square)
//        setPoint.add(new Point(x, y - 50));
//        setPoint.add(new Point(x, y + 50));
//        setPoint.add(new Point(x - 50, y));
//        setPoint.add(new Point(x + 50, y));
        setPoint.add(new Point(x + 25, y - 25));
        setPoint.add(new Point(x - 25, y + 25));

    }

    private void genButton() {
        for (Point tmp : setPoint) {
            int x0 = (int) tmp.getX();
            int y0 = (int) tmp.getY();
            Button button = new Button(x0, y0, 25, 25,
                    imageController.tryGetImage("/Resources/Images/Background/setPoint.png"),
                    imageController.tryGetImage("/Resources/Images/Background/setPoint_clicked.png"),
                    imageController.tryGetImage("/Resources/Images/Background/setPoint.png"));
            int number = buttonList.size();
            button.setButtonListener(new ButtonListener() {
                @Override
                public void onClick(int x, int y) {
                    popWindow = new TowerSelectWindow(100, 25, 600, 50 );
                }

                @Override
                public void hover(int x, int y) {

                }

            });
            buttonList.add(button);
        }
    }

    private void genTower(int x, int y, int index) {
        Button button = new Button(x, y, 25, 25,
                imageController.tryGetImage("/Resources/Images/Label/Tower_generate_Label.png"),
                imageController.tryGetImage("/Resources/Images/Label/Tower_generate_Label.png"),
                imageController.tryGetImage("/Resources/Images/Label/Tower_generate_Label.png"));
        button.setButtonListener(new ButtonListener() {

            @Override
            public void onClick(int x, int y) {

            }

            @Override
            public void hover(int x, int y) {

            }
        });
        buttonList.set(index, button);
    }

    private void genAlien() { // new
        if ((int) (Math.random() * 100) > 10) {
            aliens.add(new Alien1(-25, 50));
            Alien.setRoute(route);
            count++;
        }
    }

    private void removeAlien() { // new
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).isDead()) {
                aliens.remove(i);
            }
        }
    }

}
