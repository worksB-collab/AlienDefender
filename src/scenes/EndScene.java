/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import controllers.AudioController;
import controllers.BackgroundController;
import controllers.CommandSolver;
import controllers.ImageController;
import controllers.PlayerController;
import controllers.RankController;
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
public class EndScene extends Scene{
    private CommandSolver.MouseCommandListener mouseCommandListener;
    private PlayerController playerController;
    private RankController rankController;
    private ImageController imageController;
    private AudioController audioController;
    private BackgroundController backgroundController;
    private BufferedImage image;
    private Button reStartButton;
    private String nameString;
    private String scoreString;
    private DrawStringPoint point[];
    private Clip audio;
    public EndScene(SceneController sceneController) {
        super(sceneController);
        playerController = PlayerController.genInstance();
        imageController = ImageController.genInstance();
        rankController = RankController.genInstance();
        backgroundController = new BackgroundController(6);
        audioController = AudioController.genInstance();
        audio = audioController.tryGetAudio(Path.Audios.Musics.WIN1);
        audio.start();
        nameString = playerController.getName();
        scoreString = "Total socre : " + Long.toString(playerController.getScore());
        point = new DrawStringPoint[2];
        image = imageController.tryGetImage(Path.Image.TROPHY);
        mouseCommandListener = new CommandSolver.MouseCommandListener(){
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
        if(audio.getMicrosecondLength() == audio.getMicrosecondPosition()){
            audio = audioController.tryGetAudio(Path.Audios.Musics.WIN2);
            audio.loop(Clip.LOOP_CONTINUOUSLY);
        }
        if(point[0] != null){
            point[0].update(Global.FRAME_WIDTH, Global.FRAME_HEIGHT);
            point[1].update(Global.FRAME_WIDTH, Global.FRAME_HEIGHT);
        }
    }

    @Override
    public void sceneEnd() {
        reStartButton = null;
        imageController.clearImage();
        audio.close();
        audioController.clearAudio();
    }

    @Override
    public void paint(Graphics g) {
        backgroundController.paint(g);
        if(point[0] == null){
            point[0] = new DrawStringPoint(0, 0, g, Global.FONT_BUTTON, scoreString, Global.FRAME_WIDTH, Global.FRAME_HEIGHT);
            point[1] = new DrawStringPoint(0, 0, g, Global.FONT_BUTTON, nameString, Global.FRAME_WIDTH, Global.FRAME_HEIGHT);
        }
        reStartButton.paint(g);
        g.setFont(Global.FONT_BUTTON);
        g.setColor(Color.YELLOW);
        g.drawString(point[0].getText(), (int)point[0].getX(), (int)(point[0].getY()+ 4f * Global.MIN_PICTURE_SIZE));
        g.drawString(point[1].getText(), (int)point[1].getX(), (int)(point[1].getY()));
        g.drawImage(image, (int)((Global.FRAME_WIDTH  - (Global.MIN_PICTURE_SIZE * 4f) )/ 2), (int)(point[1].getY() - 6 * Global.MIN_PICTURE_SIZE), (int)(Global.MIN_PICTURE_SIZE * 4f), (int) (Global.MIN_PICTURE_SIZE * 4f), null);
    }
    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener(){
        return mouseCommandListener;
    }
    public void genButton(){
        
        reStartButton = new Button( 0, 0, Global.FRAME_WIDTH, Global.FRAME_WIDTH + 10 * Global.MIN_PICTURE_SIZE, "Press anywhere to continue !");
        reStartButton.setFont(Global.FONT_SCORE);
        reStartButton.setButtonListener(new ButtonListener(){
            @Override
            public void onClick(int x, int y) {
                rankController.addRank(playerController.getName(), playerController.getScore());
                playerController.initialize();
                sceneController.changeScene(new StartScene(sceneController));
            }

            @Override
            public void hover(int x, int y) {
            }
        }
        );
    }
    
    public void wirteRank(){

        
        
    }
}
