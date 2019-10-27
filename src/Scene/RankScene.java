/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;


import controller.ImageController;
import controller.SceneController;
import gameobject.Button;
import gameobject.Button.ButtonListener;
import value.Path;
import controller.CommandSolver;
import controller.CommandSolver.MouseState;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class RankScene extends Scene{
    private CommandSolver.MouseCommandListener mouseCommandListener;
    private ImageController imageController;
    private BufferedImage image;
    private Button backButton;
    
    public RankScene(SceneController sceneController) {
        super(sceneController);
        imageController = ImageController.genInstance();
        image = imageController.tryGetImage(Path.Image.Scene.RANK_SCENE);
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
    public void sceneBegin(){
        genButton();
        
    }

    @Override
    public void sceneUpdate() {
        backButton.update();
    }

    @Override
    public void sceneEnd() {
        backButton = null;
        imageController.clearImage();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
        backButton.paint(g);
    }
    
    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener(){
        return mouseCommandListener;
    }
    
    private void genButton(){
        backButton = new Button(50, 50,  150, 100,
                imageController.tryGetImage(Path.Image.Button.BackButton.BACK_BUTTON_ROOT));
        
        backButton.setButtonListener(new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                sceneController.changeScene(new MenuScene(sceneController));
            }

            @Override
            public void hover(int x, int y) {
            }
        
        });
    }
    
}
