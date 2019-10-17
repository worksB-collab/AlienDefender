/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Controller.ImageController;
import Controller.SceneController;
import GameObject.Button;
import Value.Path;
import Controller.CommandSolver;
import Controller.CommandSolver.MouseCommandListener;
import Controller.CommandSolver.MouseState;
import Value.Global;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class PrepareScene extends Scene{
    private MouseCommandListener mouseCommandListener;
    private ImageController imageController;
    private BufferedImage image;
    private Button backButton;
    
    public PrepareScene(SceneController sceneController) {
        super(sceneController);
        imageController = ImageController.genInstance();
        image = imageController.tryGetImage(Path.Image.Scene.PREPARE_SCENE);
        mouseCommandListener = new MouseCommandListener(){
            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if(state == MouseState.CLICKED){
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
        if(backButton != null){
            if(backButton.getHeight() != 6 * Global.MIN_PICTURE_SIZE){
                genButton();
            }
        }
        backButton.update();
    }

    @Override
    public void sceneEnd() {
        backButton = null;
        imageController.clearImage();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, 32 * Global.MIN_PICTURE_SIZE, 24 * Global.MIN_PICTURE_SIZE, null);
        backButton.paint(g);
    }
    
    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener(){
        return mouseCommandListener;
    }
    private void genButton(){
        backButton = new Button(2 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE,  8 * Global.MIN_PICTURE_SIZE, 4 * Global.MIN_PICTURE_SIZE,
                imageController.tryGetImage(Path.Image.Button.BackButton.BACK_BUTTON_ROOT),
                imageController.tryGetImage(Path.Image.Button.BackButton.BACK_BUTTON_CLICK),
                imageController.tryGetImage(Path.Image.Button.BackButton.BACK_BUTTON_HOVER));
        backButton.setButtonListener(new Button.ButtonListener(){

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
