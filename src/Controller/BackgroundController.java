/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Value.Global;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Transparency;
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
        public Stage0(){
            imageController = ImageController.genInstance();
            image = imageController.tryGetImage("/Resources/Images/Background/Background_00.png");
        }

        @Override
        public void update() {
            
        }

        @Override
        public void paint(Graphics g) {
            g.drawImage(image, 0, 0, 32 * Global.MIN_PICTURE_SIZE, 24 * Global.MIN_PICTURE_SIZE, null);
            g.setColor(Color.ORANGE);
            g.setFont(Global.FONT_00);
            g.drawString("ALIEN", Global.FRAME_WIDTH / 4 + 4 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE + 39);
            g.drawString("DEFENDER", 5 *Global.MIN_PICTURE_SIZE + 8 , 8 * Global.MIN_PICTURE_SIZE + 39);
            g.setColor(Color.BLACK);
        }
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
