/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Value.Global;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class RouteController {
    private ImageController imageController;
    private LinkedList<Point> standardRoute;
    private LinkedList<Point> route;
    private LinkedList<Point> setPoint;
    private BufferedImage imgRoad;
    
    
    public RouteController(){
        imageController = ImageController.genInstance();
        imgRoad = imageController.tryGetImage("/Resources/Images/Background/dirt.png");
        genRoad(1);
    }
    public void genRoad(int stage){
        switch(stage){
            case 1:
                genFixedRoad();
                break;
            case 2:
                break;
        }
    }
    public LinkedList<Point> getSetPoint(){
        if(setPoint == null){
            return null;
        }
        return setPoint;
    }
    public LinkedList<Point> getRoute(){
        if(route == null){
            return null;
        }
        return route;
    }
    public void update(){
        
    }
    public void paint(Graphics g){
        for (Point p : route) {
            g.drawImage(imgRoad, (int) p.getX(), (int) p.getY(), Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE, null);
        }
    }

    private void paintSetPoint(Graphics g) {
        BufferedImage imgS = imageController.tryGetImage("/Resources/Images/Background/setPoint.png");
        for (Point tmp : setPoint) {
            g.drawImage(imgS, (int) tmp.getX(), (int) tmp.getY(), Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE, null);
        }
    }
    
//    private void genRandomRoad() {
//        route = new LinkedList();
//        setPoint = new LinkedList();
//
//        int x = 0;
//        int y = Global.MIN_PICTURE_SIZE * 2;
//        route.add(new Point(x, y));
//        int maxX = Global.MIN_PICTURE_SIZE * 30;
//        int maxY = Global.MIN_PICTURE_SIZE * 22;
//        while (true) {
//            int r = (int) (Math.random() * 2);
//            if (r == 0 && x <= maxX) {
//                x += Global.MIN_PICTURE_SIZE;
//            } else {
//                if (y > maxY) {
//                    break;
//                }
//                y += Global.MIN_PICTURE_SIZE;
//
//            }
//            route.add(new Point(x, y));
//        }
//        genSetPoint();
//    }

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

//    private void genStandardRoute() {
//        standardRoute = new LinkedList<Point>();
//        for (int i = 0; i < route.size(); i++) {
//            Point p = route.get(i);
//            standardRoute.add(new Point((int) p.getX(), (int) p.getY()));
//        }
//    }
    
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
    }

//    private void reCheckSetPoint() {
//
//        double d = Global.MIN_PICTURE_SIZE / (double) Global.STANDAR_MIN_SIZE;
//        for (int i = 0; i < route.size() - 1; i++) {
//            Point p = route.get(i);
//            Point ps = standardRoute.get(i);
//            System.out.println(ps.x);
//            p.x = (int) (ps.getX() * d);
//            System.out.println(p.x);
//            p.y = (int) (ps.getY() * d);
//        }
//        genSetPoint();
//    }

    private void addSetPoint(int x, int y) {
        //distance 25
        setPoint.add(new Point(x, y - Global.MIN_PICTURE_SIZE));
        setPoint.add(new Point(x, y + Global.MIN_PICTURE_SIZE));
        setPoint.add(new Point(x - Global.MIN_PICTURE_SIZE, y));
        setPoint.add(new Point(x + Global.MIN_PICTURE_SIZE, y));
        setPoint.add(new Point(x + Global.MIN_PICTURE_SIZE, y - Global.MIN_PICTURE_SIZE));
        setPoint.add(new Point(x - Global.MIN_PICTURE_SIZE, y + Global.MIN_PICTURE_SIZE));
    }
}
