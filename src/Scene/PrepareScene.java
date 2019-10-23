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
import Controller.CommandSolver.KeyCommandListener;
import Controller.CommandSolver.MouseCommandListener;
import Controller.CommandSolver.MouseState;
import Controller.CommandSolver.TypedListener;
import Value.Global;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class PrepareScene extends Scene{
    private MouseCommandListener mouseCommandListener;
    private TypedListener typedListener;
    private KeyCommandListener keyCommandListener;
    private PopUpWindow popWindow;
    private LinkedList<Character> charList;
    private ImageController imageController;
    private BufferedImage image;
    private Button backButton;
    
    public PrepareScene(SceneController sceneController) {
        super(sceneController);
        imageController = ImageController.genInstance();
        image = imageController.tryGetImage(Path.Image.Scene.PREPARE_SCENE);
        charList = new LinkedList();
        
        mouseCommandListener = new MouseCommandListener(){
            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if(state == MouseState.RELEASED || state == MouseState.CLICKED){
                    if(backButton.isRange(e.getX(), e.getY())){
                        backButton.click(e.getX(), e.getY());
                    }
                }
            }
        
        };
        typedListener = new TypedListener(){
            @Override
            public void keyTyped(char c, long trigTime) {
            }
        };
        keyCommandListener = new KeyCommandListener(){

            @Override
            public void keyPressed(int commandCode, long trigTime) {

            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {

            }
            
        };
    }

    
    @Override
    public void sceneBegin() {
        popWindow = new InputPopWindow((Global.FRAME_WIDTH - Global.MIN_PICTURE_SIZE * 4) / 2 , (Global.FRAME_HEIGHT - Global.MIN_PICTURE_SIZE * 8) / 2 , 
                Global.MIN_PICTURE_SIZE * 4, Global.MIN_PICTURE_SIZE * 8, charList);
        genButton();
    }

    @Override
    public void sceneUpdate() {
        backButton.update();
        if(popWindow != null){
            popWindow.update();
            if(popWindow.isEnd()){
                popWindow = null;
                //store information
            }
        }
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
        if(popWindow != null){
            popWindow.paint(g);
        }
    }
    
    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener(){
        if(popWindow != null){
            return popWindow.getMouseCommandListener();
        }
        return mouseCommandListener;
    }
    
    @Override
    public CommandSolver.TypedListener getTypedListener(){
        if(popWindow != null){

            return popWindow.getTypedListener();
        }
        return typedListener;
    }
    
    @Override
    public CommandSolver.KeyCommandListener getKeyCommandListener(){
        if(popWindow != null){
            return popWindow.getKeyCommandListener();
        }
        return keyCommandListener;
    }
    private void genButton(){
        backButton = new Button(2 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE,  8 * Global.MIN_PICTURE_SIZE, 4 * Global.MIN_PICTURE_SIZE,
                imageController.tryGetImage(Path.Image.Button.BackButton.BACK_BUTTON_ROOT));
        
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
