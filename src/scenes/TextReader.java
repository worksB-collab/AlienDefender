/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import controllers.DelayCounter;
import controllers.ImageController;
import controllers.SceneController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.sound.sampled.Clip;
import values.DrawStringPoint;
import values.Global;
import values.Path;

/**
 *
 * @author user
 */
public class TextReader extends Scene{
    private ImageController imageController;
    private BufferedImage image;
    private Clip audio;
    private TextContent textContent;    
    private LinkedList<Character> textList[];
    private String nowtext[];
    private int pointer;
    private DrawStringPoint  points[];
    private DelayCounter delay;
    public TextReader(SceneController sceneController, int stage) {
        super(sceneController);
        imageController = ImageController.genInstance();
        image = imageController.tryGetImage(Path.Image.Scene.PREPARE_SCENE);
        this.audio = audio;
        textContent = TextContent.genInstance();
        String text[] = textContent.getText(stage).split("\n");
        textList = new LinkedList[text.length];
        for(int i = 0; i < text.length; i++){
            int count = 0;
            textList[i] = new LinkedList();
            while(count != text[i].length()){
                textList[i].add(text[i].charAt(count++));
            }
        }
        points = new DrawStringPoint[text.length];
        nowtext = new String[points.length];
        for(int i = 0; i < nowtext.length; i++){
            nowtext[i] = "";
        }
        pointer = 0;
        delay = new DelayCounter(1);
    }

    @Override
    public void sceneBegin() {
        
    }

    @Override
    public void sceneUpdate() {
        if(points[0] != null){

            if(delay.update()){
                nowtext[pointer] += textList[pointer].pop();
                points[pointer].setText(nowtext[pointer]);
                if(textList[pointer].isEmpty()){
                    pointer++;
                }
                if(pointer >= textList.length){
                    sceneController.changeScene(new GameScene1(sceneController));
                }
                for(int i = 0; i < points.length; i++){
                    points[i].update();
                }
            }
        }
    }

    @Override
    public void sceneEnd() {
        imageController.clearImage();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, (int)(32f * Global.MIN_PICTURE_SIZE), (int)(24f * Global.MIN_PICTURE_SIZE), null);
        if(points[0] == null){
            float x = 0;
            float y = 0;
            int width = (int)((Global.FRAME_WIDTH - 2 * Global.MIN_PICTURE_SIZE)) ;
            int height = (int)((Global.FRAME_HEIGHT - 2 * Global.MIN_PICTURE_SIZE) / points.length);
            for(int i = 0; i < points.length; i++){
                points[i] = new DrawStringPoint(x, y, g, Global.FONT_TEXT, nowtext[i], width, height);
                y += height;
            }
        }
        g.setFont(Global.FONT_TEXT);
        g.setColor(Color.white);
        for(int i = 0; i < nowtext.length; i++){
            g.drawString(nowtext[i], (int)points[i].getX(), (int)points[i].getY());
        }
        g.setColor(Color.black);
   
    }
    
}
