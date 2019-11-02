/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import controllers.AudioController;
import controllers.BackgroundController;
import controllers.CommandSolver;
import controllers.CommandSolver.MouseCommandListener;
import controllers.ImageController;
import controllers.PlayerController;
import controllers.SceneController;
import gameobjects.Button;
import gameobjects.Button.ButtonListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;
import values.DrawStringPoint;
import values.Global;
import values.Path;

/**
 *
 * @author user
 */
public class GameOverScene extends Scene{
    private MouseCommandListener mouseCommandListener;
    private PlayerController playerController;
    private ImageController imageController;
    private AudioController audioController;
    private BackgroundController backgroundController;
    private DrawStringPoint point;
    private Button reStartButton;
    private Clip audio;
    public GameOverScene(SceneController sceneController) {
        super(sceneController);
        playerController = PlayerController.genInstance();
        imageController = ImageController.genInstance();
        backgroundController = new BackgroundController(6);
        audioController = AudioController.genInstance();
        audio = audioController.tryGetAudio(Path.Audios.Musics.LOSE4);
        audio.loop(Clip.LOOP_CONTINUOUSLY);
        mouseCommandListener = new MouseCommandListener(){

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                
                if(state == CommandSolver.MouseState.RELEASED || state == CommandSolver.MouseState.CLICKED){
                    if(reStartButton.isRange(e.getX(), e.getY())){
                        reStartButton.click(e.getX(), e.getY());
                    }
                }else if(state == CommandSolver.MouseState.MOVED){
                    if(reStartButton.isRange(e.getX(), e.getY())){
                        reStartButton.hover(e.getX(), e.getY());
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
        backgroundController.update();
        reStartButton.update();
//        if(audio.getMicrosecondLength() == audio.getMicrosecondPosition()){
//            audio = audioController.tryGetAudio(Path.Audios.Musics.LOSE4);
//            audio.loop(Clip.LOOP_CONTINUOUSLY);
//        }
    }

    @Override
    public void sceneEnd() {
        imageController.clearImage();
        audio.close();
        audioController.clearAudio();
    }

    @Override
    public void paint(Graphics g) {
        backgroundController.paint(g);
        if(point == null){
            point = new DrawStringPoint(0, 0, g, Global.FONT_01, "GAME OVER", (int)Global.FRAME_WIDTH, (int)Global.FRAME_HEIGHT);
        }
        g.setFont(Global.FONT_01);
        g.setColor(Color.orange);
        g.drawString(point.getText(), (int)point.getX(), (int)point.getY());
        g.setColor(Color.BLACK);
        reStartButton.paint(g);
    }
    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener(){
        return mouseCommandListener;
    }
    public void genButton(){
        
        reStartButton = new Button(0, 0, (int)(Global.FRAME_WIDTH), (int)(Global.FRAME_HEIGHT + 14 * Global.MIN_PICTURE_SIZE),"Click to Retry");

        reStartButton.setButtonListener(new ButtonListener(){
            @Override
            public void onClick(int x, int y) {
                playerController.initialize();
                sceneController.changeScene(new StartScene(sceneController));
            }

            @Override
            public void hover(int x, int y) {
            }
        }
        );
    }
    
}
