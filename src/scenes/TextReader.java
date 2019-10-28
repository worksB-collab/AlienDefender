/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import controllers.ImageController;
import controllers.SceneController;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
    private TextContent textContent;
    private String text[];
    private String nowtext[];
    private int pointer;
    private DrawStringPoint  points[];
    public TextReader(SceneController sceneController, int stage) {
        super(sceneController);
        imageController = ImageController.genInstance();
        image = imageController.tryGetImage(Path.Image.Scene.PREPARE_SCENE);
        textContent = TextContent.genInstance();
        text = textContent.getText(stage).split("\n");
        points = new DrawStringPoint[text.length];
        nowtext = new String[points.length];
        pointer = 0;
    }

    @Override
    public void sceneBegin() {
        
    }

    @Override
    public void sceneUpdate() {
        if(
    }

    @Override
    public void sceneEnd() {
        
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, (int)(32f * Global.MIN_PICTURE_SIZE), (int)(24f * Global.MIN_PICTURE_SIZE), null);
        if(points[0] == null){
            float x = 0;
            float y = 0;
            int width = (int)((Global.FRAME_WIDTH - 2 * Global.MIN_PICTURE_SIZE) / points.length) ;
            int height = (int)((Global.FRAME_HEIGHT - 2 * Global.MIN_PICTURE_SIZE));
            for(int i = 0; i < points.length; i++){
                points[i] = new DrawStringPoint(x, y, g, Global.FONT_TEXT, text[i], width, height);
                x += Global.MIN_PICTURE_SIZE * 2f;
            }
        }
        g.drawString(text, pointer, pointer);
        
   
    }
    
}
