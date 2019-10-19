/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;


import Controller.BackgroundController;
import Controller.ImageController;
import Controller.SceneController;
import GameObject.Button;
import GameObject.Button.ButtonListener;
import Value.Path;
import Controller.CommandSolver.MouseCommandListener;
import Controller.CommandSolver.MouseState;
import Value.Global;
import java.awt.Graphics;
import java.awt.event.MouseEvent;


/**
 *
 * @author user
 */
public class StartScene extends Scene{

    private MouseCommandListener mouseCommandListener;
    private BackgroundController backgroundController;
    private ImageController imageController;
    private Button buttonStart;
    
    
    public StartScene(SceneController sceneController) {
        super(sceneController);
        backgroundController = new BackgroundController(0);
        imageController = ImageController.genInstance();
        mouseCommandListener = new MouseCommandListener(){
            @Override
            public void mouseTrig(MouseEvent e, MouseState state, long trigTime) {
                
                if(state == MouseState.CLICKED){
                    if(buttonStart.isRange(e.getX(), e.getY())){
                        buttonStart.click(e.getX(), e.getY());
                    }
                }else if(state == MouseState.MOVED){
                    if(buttonStart.isRange(e.getX(), e.getY())){
                        buttonStart.hover(e.getX(), e.getY());
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
        if(buttonStart.getWidth() !=  7 * Global.MIN_PICTURE_SIZE){
            genButton();
        }
        buttonStart.update();
    }

    @Override
    public void sceneEnd() {
        buttonStart = null;
        imageController.clearImage();

    }

    @Override
    public void paint(Graphics g) {
        backgroundController.paint(g);
        buttonStart.paint(g);
    }   

    public MouseCommandListener getMouseCommandListener(){
        return mouseCommandListener;
    }
    
    public void genButton(){
        
        buttonStart = new Button( (Global.FRAME_WIDTH - 18 * Global.MIN_PICTURE_SIZE) / 2, ((Global.FRAME_HEIGHT - 7 * Global.MIN_PICTURE_SIZE) / 2) + 8 * Global.MIN_PICTURE_SIZE, 18 * Global.MIN_PICTURE_SIZE, 7 * Global.MIN_PICTURE_SIZE, "START");
        
        buttonStart.setButtonListener(new ButtonListener(){
            @Override
            public void onClick(int x, int y) {
                 sceneController.changeScene(new MenuScene(sceneController));
            }

            @Override
            public void hover(int x, int y) {
            }
        }
        );
        buttonStart.setFont(Global.FONT_01);
    }
}
