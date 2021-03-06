/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import static controllers.AudioController.audioController;
import controllers.CommandSolver;
import controllers.CommandSolver.MouseCommandListener;
import controllers.DelayCounter;
import controllers.ImageController;
import controllers.SceneController;
import gameobjects.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
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
public class TextReader extends Scene {
    private MouseCommandListener mouseCommandListener;
    private ImageController imageController;
    private BufferedImage image, image2;
    private Clip audio;
    private TextContent textContent;
    private LinkedList<Character> textList[];
    private String nowtext[];
    private int pointer;
    private DrawStringPoint points[];
    private DelayCounter delay;
    private Button buttonNext;
    private int stage;
    private int sX;

    public TextReader(SceneController sceneController, int stage) {
        super(sceneController);
        this.stage = stage;
        imageController = ImageController.genInstance();
        image = imageController.tryGetImage(Path.Image.Scene.SPACE1);
        image2 = imageController.tryGetImage(Path.Image.Scene.SPACE2);
        audio = audioController.tryGetAudio(Path.Audios.Musics.BETWEENSCENES);
        audio.loop(Clip.LOOP_CONTINUOUSLY);
        textContent = TextContent.genInstance();
        String text[] = textContent.getText(stage - 1).split("\n");
        textList = new LinkedList[text.length];
        for (int i = 0; i < text.length; i++) {
            int count = 0;
            textList[i] = new LinkedList();
            while (count != text[i].length()) { 
                textList[i].add(text[i].charAt(count++));
            }
        }
        points = new DrawStringPoint[text.length];
        nowtext = new String[points.length];
        for (int i = 0; i < nowtext.length; i++) {
            nowtext[i] = "";
        }
        pointer = 0;
        delay = new DelayCounter(1);
        mouseCommandListener = new CommandSolver.MouseCommandListener(){
            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                
                if(state == CommandSolver.MouseState.RELEASED || state == CommandSolver.MouseState.CLICKED){
                    if(buttonNext.isRange(e.getX(), e.getY())){
                        buttonNext.click(e.getX(), e.getY());
                    }
                }else if(state == CommandSolver.MouseState.MOVED){
                    if(buttonNext.isRange(e.getX(), e.getY())){
                        buttonNext.hover(e.getX(), e.getY());
                    }
                }
            }
        };
    }

    @Override
    public void sceneBegin() {
        genButton();
    }

    @Override
    public void sceneUpdate() {
        if (points[0] != null) {

            if (pointer < textList.length) {
                   
                if (delay.update()) {
                    nowtext[pointer] += textList[pointer].pop();
                    points[pointer].setText(nowtext[pointer]);
                    if (textList[pointer].isEmpty()) {
                        pointer++;
                    }
                    for (int i = 0; i < points.length; i++) {
                        points[i].update();
                    }
                }
            }
        }
        if(sX ==-(int) (32 * Global.MIN_PICTURE_SIZE)){
            sX=0;
        }
        sX -=1;
    }

    @Override
    public void sceneEnd() {
        imageController.clearImage();
        audio.close();
        audioController.clearAudio();
    }

    @Override
    public void paint(Graphics g) {
        
         g.drawImage(image, 
                sX, 0, 
                (int) (32 * Global.MIN_PICTURE_SIZE), (int) (24 * Global.MIN_PICTURE_SIZE), null);
        g.drawImage(image2, 
                sX+(int) (32 * Global.MIN_PICTURE_SIZE), 0, 
                (int) (32* Global.MIN_PICTURE_SIZE), (int) (24 * Global.MIN_PICTURE_SIZE), null);
        if (points[0] == null) {
            float x = 0;
            float y = 0;
            int width = (int) ((Global.FRAME_WIDTH - 2 * Global.MIN_PICTURE_SIZE));
            int height = (int) ((Global.FRAME_HEIGHT - 2 * Global.MIN_PICTURE_SIZE) / points.length);
            for (int i = 0; i < points.length; i++) {
                points[i] = new DrawStringPoint(x, y, g, Global.FONT_TEXT, nowtext[i], width, height);
                y += height;
            }
        }
        buttonNext.paint(g);
        g.setFont(Global.FONT_TEXT);
        g.setColor(Color.white);
        for(int i = 0; i < nowtext.length; i++){
            g.drawString(nowtext[i], (int)points[i].getX(), (int)points[i].getY());
        }
        g.setColor(Color.black);

    }
    @Override
    public MouseCommandListener getMouseCommandListener(){
        return mouseCommandListener;
    }
    public void genButton(){
        
        buttonNext = new Button(27 * Global.MIN_PICTURE_SIZE, 21f * Global.MIN_PICTURE_SIZE, 4f * Global.MIN_PICTURE_SIZE, 2f * Global.MIN_PICTURE_SIZE,
        imageController.tryGetImage("/Resources/Images/Button/Button_01_1.png"));
        buttonNext.setFont(Global.FONT_INFOWINDOW);
        buttonNext.setText("NEXT");
        buttonNext.setButtonListener(new Button.ButtonListener(){
            @Override
            public void onClick(int x, int y) {
                if(stage > 5){
                    sceneController.changeScene(new EndScene(sceneController));
                }else{
                    sceneController.changeScene(new GameScene(sceneController, stage));
//                    sceneController.changeScene(new GameScene(sceneController, 4));
                }
                        
                 
            }
            @Override
            public void hover(int x, int y) {
            }
        }
        );
    }

}
