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
    public static class RoutePoint{
        private float x;
        private float y;
        
        public RoutePoint(float x, float y){
            this.x = x;
            this.y = y;
        }
        
        public float getX(){
            return x;
        }
        public float getY(){
            return y;
        }
        
        public void setX(float x){
            this.x = x;
        }
        
        public void setY(float y){
            this.y = y;
        }
    }
    private ImageController imageController;
    private LinkedList<RoutePoint> standardRoute;
    private LinkedList<RoutePoint> route;
    private LinkedList<RoutePoint> setPoint;
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
    public LinkedList<RoutePoint> getSetPoint(){
        if(setPoint == null){
            return null;
        }
        return setPoint;
    }
    public LinkedList<RoutePoint> getRoute(){
        if(route == null){
            return null;
        }
        return route;
    }
    public void update(){
        
    }
    public void paint(Graphics g){
        for (RoutePoint p : route) {
            g.drawImage(imgRoad, (int) p.getX(), (int) p.getY(), (int)Global.MIN_PICTURE_SIZE, (int)Global.MIN_PICTURE_SIZE, null);
        }
    }

    private void paintSetPoint(Graphics g) {
        BufferedImage imgS = imageController.tryGetImage("/Resources/Images/Background/setPoint.png");
        for (RoutePoint tmp : setPoint) {
            g.drawImage(imgS, (int) tmp.getX(), (int) tmp.getY(), (int)Global.MIN_PICTURE_SIZE, (int)Global.MIN_PICTURE_SIZE, null);
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

        float x = 0;
        float y = Global.MIN_PICTURE_SIZE * 2;
        route.add(new RoutePoint(x, y));
        float maxX = Global.MIN_PICTURE_SIZE * 30;
        float maxY = Global.MIN_PICTURE_SIZE * 22;
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
            route.add(new RoutePoint(x, y));
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
        float maxX = 31 * Global.MIN_PICTURE_SIZE;
        float maxY = 23 * Global.MIN_PICTURE_SIZE;
        if (setPoint != null) {
            setPoint = new LinkedList<RoutePoint>();
        }
        for (RoutePoint p : route) {
            addSetPoint(p.getX(), p.getY());
        }
        //delete route
        for (int i = 0; i < setPoint.size(); i++) {
            RoutePoint tmp = setPoint.get(i);
            if (tmp.getX() < 0 || tmp.getX() > maxX || tmp.getY() < 0 || tmp.getY() > maxY) {
                setPoint.remove(i--);
            }
            for (RoutePoint tmp2 : route) {
                if (tmp.getX() == tmp2.getX() && tmp.getY() == tmp2.getY()) {
                    setPoint.remove(i--);
                }
            }
        }
        //delete repeat point
        for (int i = 0; i < setPoint.size() - 1; i++) {
            RoutePoint p1 = setPoint.get(i);
            for (int j = i + 1; j < setPoint.size(); j++) {
                RoutePoint p2 = setPoint.get(j);
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

    private void addSetPoint(float x, float y) {
        //distance 25
        setPoint.add(new RoutePoint(x, y - Global.MIN_PICTURE_SIZE));
        setPoint.add(new RoutePoint(x, y + Global.MIN_PICTURE_SIZE));
        setPoint.add(new RoutePoint(x - Global.MIN_PICTURE_SIZE, y));
        setPoint.add(new RoutePoint(x + Global.MIN_PICTURE_SIZE, y));
        setPoint.add(new RoutePoint(x + Global.MIN_PICTURE_SIZE, y - Global.MIN_PICTURE_SIZE));
        setPoint.add(new RoutePoint(x - Global.MIN_PICTURE_SIZE, y + Global.MIN_PICTURE_SIZE));
    }
}
