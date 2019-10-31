/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import controllers.AudioController;
import controllers.CommandSolver;
import controllers.ImageController;
import controllers.PlayerController;
import controllers.RankController;
import controllers.SceneController;
import gameobjects.Button;
import gameobjects.Button.ButtonListener;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;
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
    private BufferedImage image;
    private Button reStartButton;
    private Clip audio;
    public EndScene(SceneController sceneController) {
        super(sceneController);
        playerController = PlayerController.genInstance();
        imageController = ImageController.genInstance();
        rankController = RankController.genInstance();
        audioController = AudioController.genInstance();
        audio = audioController.tryGetAudio(Path.Audios.Musics.WIN1);
        audio.start();
        image = imageController.tryGetImage(Path.Image.Scene.END_SCENE);
        this.audio = audio;
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
        reStartButton.update();
        if(audio.getMicrosecondLength() == audio.getMicrosecondPosition()){
            audio = audioController.tryGetAudio(Path.Audios.Musics.WIN2);
            audio.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    @Override
    public void sceneEnd() {
        reStartButton = null;
        imageController.clearImage();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, (int)Global.FRAME_WIDTH, (int)Global.FRAME_HEIGHT, null);
        reStartButton.paint(g);
    }
    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener(){
        return mouseCommandListener;
    }
    public void genButton(){
        
        reStartButton = new Button( (Global.FRAME_WIDTH - 10f * Global.MIN_PICTURE_SIZE) / 2f, ((Global.FRAME_HEIGHT - 4f * Global.MIN_PICTURE_SIZE) / 2f) + 8f * Global.MIN_PICTURE_SIZE, 10f * Global.MIN_PICTURE_SIZE, 4f * Global.MIN_PICTURE_SIZE,
        imageController.tryGetImage("/Resources/Images/Button/Button_01_1.png"));
        reStartButton.setText("End");
        
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
