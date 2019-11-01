/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;




import controllers.CommandSolver;
import controllers.CommandSolver.MouseState;
import controllers.ImageController;
import controllers.SceneController;
import gameobjects.Button;
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
public class DescriptionScene extends Scene{
    private CommandSolver.MouseCommandListener mouseCommandListener;
    private ImageController imageController;
    private BufferedImage image, image2;
    private Button backButton;
    private Clip audio;
    private int sX;
    public DescriptionScene(SceneController sceneController, Clip audio) {
        super(sceneController);
        imageController = ImageController.genInstance();
        image = imageController.tryGetImage(Path.Image.Scene.SPACE1);
        image2 = imageController.tryGetImage(Path.Image.Scene.SPACE2);
        this.audio = audio;
        mouseCommandListener = new CommandSolver.MouseCommandListener(){
            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if(state == MouseState.RELEASED || state == MouseState.CLICKED){
                    if(backButton.isRange(e.getX(), e.getY())){
                        backButton.click(e.getX(), e.getY());
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
        backButton.update();
        if(sX ==-(int) (32 * Global.MIN_PICTURE_SIZE)){
            sX=0;
        }
        sX -=1;
    }

    @Override
    public void sceneEnd() {
        backButton = null;
        imageController.clearImage();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 
                sX, 0, 
                (int) (32 * Global.MIN_PICTURE_SIZE), (int) (24 * Global.MIN_PICTURE_SIZE), null);
        g.drawImage(image2, 
                sX+(int) (32 * Global.MIN_PICTURE_SIZE), 0, 
                (int) (32* Global.MIN_PICTURE_SIZE), (int) (24 * Global.MIN_PICTURE_SIZE), null);
        backButton.paint(g);
        
    }
    
    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener(){
        return mouseCommandListener;
    }
    
    private void genButton(){
        backButton = new Button(27 * Global.MIN_PICTURE_SIZE, 21f * Global.MIN_PICTURE_SIZE, 4f * Global.MIN_PICTURE_SIZE, 2f * Global.MIN_PICTURE_SIZE,
                imageController.tryGetImage(Path.Image.Button.BACK_BUTTON));
        backButton.setText("Back");
        backButton.setButtonListener(new Button.ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                sceneController.changeScene(new MenuScene(sceneController, audio));
            }

            @Override
            public void hover(int x, int y) {
            }
        
        });
    }
}
