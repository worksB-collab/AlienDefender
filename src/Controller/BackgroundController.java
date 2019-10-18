/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Value.Global;
import java.awt.Graphics;
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
    public class Stage1 implements Stage{
        private ImageController imageController;
        private BufferedImage image;
        public Stage1(){
            imageController = ImageController.genInstance();
            image = imageController.tryGetImage("/Resources/Images/Background/grass.png");
        }
        @Override
        public void update() {
            
        }

        @Override
        public void paint(Graphics g) {
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
        
    }
    private Stage stage;
    public BackgroundController(int number){
        genStage(number);
    }
    
    private void genStage(int number){
        
        switch(number){
            case 1:
                stage = new Stage1();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
        }
    }
    
    public void update(){
        stage.update();
    }
    public void paint(Graphics g){
        stage.paint(g);
    }
    
}
