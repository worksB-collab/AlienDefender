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
    private BufferedImage image;
    private Clip audio;
    private TextContent textContent;
    private LinkedList<Character> textList[];
    private String nowtext[];
    private int pointer;
    private DrawStringPoint points[];
    private DelayCounter delay;
    private Button buttonNext;
    private int stage;

    public TextReader(SceneController sceneController, int stage) {
        super(sceneController);
        this.stage = stage;
        imageController = ImageController.genInstance();
        image = imageController.tryGetImage(Path.Image.Scene.PREPARE_SCENE);
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
    }

    @Override
    public void sceneEnd() {
        imageController.clearImage();
        audio.close();
        audioController.clearAudio();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, (int) (32f * Global.MIN_PICTURE_SIZE), (int) (24f * Global.MIN_PICTURE_SIZE), null);
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
        
        buttonNext = new Button( (Global.FRAME_WIDTH / 2f) + 4 * Global.MIN_PICTURE_SIZE, (Global.FRAME_HEIGHT/ 2f) + 8f * Global.MIN_PICTURE_SIZE, 10f * Global.MIN_PICTURE_SIZE, 4f * Global.MIN_PICTURE_SIZE,
        imageController.tryGetImage("/Resources/Images/Button/Button_01_1.png"));
        buttonNext.setText("NEXT");
        
        buttonNext.setButtonListener(new Button.ButtonListener(){
            @Override
            public void onClick(int x, int y) {
                 sceneController.changeScene(new GameScene(sceneController, stage));
            }
            @Override
            public void hover(int x, int y) {
            }
        }
        );
    }

}
