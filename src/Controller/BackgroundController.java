/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Value.DrawStringPoint;
import Value.Global;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class BackgroundController {
    public interface Stage{
        public void update();
        public void paint(Graphics g);
    }
    public class Stage0 implements Stage{
        private ImageController imageController;
        private BufferedImage image;
        private BufferedImage imageMeteor[];
        private int meteorPointer;
        private DrawStringPoint[] points;
        private Point meteorPoint;
        private DelayCounter delay;
        public Stage0(){
            imageController = ImageController.genInstance();
            image = imageController.tryGetImage("/Resources/Images/Background/Background_00-00.png");
            imageMeteor = new BufferedImage[4];
            imageMeteor[0] = imageController.tryGetImage("/Resources/Images/Background/Background_00-02.png");
            imageMeteor[1] = imageController.tryGetImage("/Resources/Images/Background/Background_00-03.png");
            imageMeteor[2] = imageController.tryGetImage("/Resources/Images/Background/Background_00-04.png");
            imageMeteor[3] = imageController.tryGetImage("/Resources/Images/Background/Background_00-05.png");
            meteorPointer = 0;
//            image2 = imageController.tryGetImage("/Resources/Images/Background/Background_00-01.png");
            meteorPoint = new Point(0,0);
            points = new DrawStringPoint[2];
            delay = new DelayCounter(30);
        }

        @Override
        public void update() {
            if(points[0] != null){
                if(points[0].getHeight() != Global.FRAME_HEIGHT){
                    for(int i = 0; i < points.length; i++){
                    points[i].update(Global.FRAME_WIDTH, Global.FRAME_HEIGHT);
                    }
                }
            }
            
            meteorPoint.x -= 5;
            meteorPoint.y += 5;
            if(delay.update()){
                meteorPointer = ++meteorPointer % 4;
                meteorPoint.x = 0;
                meteorPoint.y = 0;
            }
            
        }

        @Override
        public void paint(Graphics g) {
            g.drawImage(image, 0, 0, 32 * Global.MIN_PICTURE_SIZE, 24 * Global.MIN_PICTURE_SIZE, null);
            g.drawImage(imageMeteor[meteorPointer], (int)meteorPoint.getX(), (int)meteorPoint.getY(), 32 * Global.MIN_PICTURE_SIZE, 24 * Global.MIN_PICTURE_SIZE, null);
            if(points[0] == null){
               points[0] = new DrawStringPoint(0, 0, g, Global.FONT_00, "ALIEN", Global.FRAME_WIDTH, Global.FRAME_WIDTH);
               points[1] = new DrawStringPoint(0, 0, g, Global.FONT_00, "DEFENDER", Global.FRAME_WIDTH, Global.FRAME_WIDTH);
            }
            
            g.setColor(Color.ORANGE);
            g.setFont(Global.FONT_00);
            g.drawString(points[0].getText(), points[0].getX(), points[0].getY() - Global.MIN_PICTURE_SIZE * 8);
            g.drawString(points[1].getText(), points[1].getX(), points[1].getY() - Global.MIN_PICTURE_SIZE * 4);
            g.setColor(Color.BLACK);
        }
    }
    public class Stage1 implements Stage{
        private ImageController imageController;
        private BufferedImage image1;
        private BufferedImage image2;
        private int jupiterVar;
        private int jupiterAce;
        private int jupiterDir;
        private int speed;
        private DelayCounter delay;
        
        public Stage1(){
            imageController = ImageController.genInstance();
            image1 = imageController.tryGetImage("/Resources/Images/Background/Background_01_00-01.png");
            image2 = imageController.tryGetImage("/Resources/Images/Background/Background_01_00-03.png");
            jupiterVar = 0;
            jupiterAce = 5;
            jupiterDir = 1;
            
            speed = 10;
            delay = new DelayCounter(2);
        }
        @Override
        public void update() {
            if(delay.update()){
                jupiterVar = jupiterVar + speed;
                speed += 1 * jupiterDir * -1;
                if(jupiterAce > 0){
                    if(speed <= 0){
                        jupiterAce *= -1;
                        speed += jupiterAce;
                        jupiterDir = -1;
                    }
                }else{
                    if(speed >= 0){
                        jupiterAce *= -1;
                        speed += jupiterAce;
                        jupiterDir = 1;
                    }    
                }
            }

            
        }

        @Override
        public void paint(Graphics g) {
            g.drawImage(image1, 0, 0, 32 * Global.MIN_PICTURE_SIZE, 24 * Global.MIN_PICTURE_SIZE, null);
            int width = (int)(32 * 0.807 * Global.MIN_PICTURE_SIZE);
            int height = (int)(24 * 0.495 * Global.MIN_PICTURE_SIZE);
            g.drawImage(image2, (Global.FRAME_WIDTH - width) /2, (Global.FRAME_HEIGHT - height) / 2 + jupiterVar, width, height,  null);
            
        }
        
    }
    private Stage stage;
    public BackgroundController(int number){
        genStage(number);
    }
    
    private void genStage(int number){
        
        switch(number){
            case 0:
                stage = new Stage0();
                break;
            case 1:
                stage = new Stage1();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
        }
    }
    
    public void update(){
        stage.update();
    }
    public void paint(Graphics g){
        stage.paint(g);
    }
    
}
