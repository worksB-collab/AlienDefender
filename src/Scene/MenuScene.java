/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Controller.ImageController;
import Controller.SceneController;
import GameObject.Button;
import GameObject.Button.ButtonListener;
import Value.Path;
import Controller.CommandSolver;
import Controller.CommandSolver.MouseCommandListener;
import Controller.CommandSolver.MouseState;
import Value.Global;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class MenuScene extends Scene{
    private MouseCommandListener mouseCommandListener;
    private ImageController imageController;
    private ArrayList<Button> buttonList;
    private BufferedImage image;
    
    public MenuScene(SceneController sceneController) {
        super(sceneController);
        imageController = ImageController.genInstance();
        image = imageController.tryGetImage(Path.Image.Scene.MENU_SCENE);
        buttonList = new ArrayList<Button>();
        
        //mouse listener
        mouseCommandListener = new MouseCommandListener(){
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                //check click
                if(state == MouseState.CLICKED){
                    for(int i = 0; i < buttonList.size(); i++){
                        Button tmp = buttonList.get(i);
                        if(tmp.isRange(e.getX(), e.getY())){
                            tmp.click(e.getX(), e.getY());
                            break;
                        }
                    }
                }
                //check hover
                if(state == MouseState.MOVED){
                    for(int i = 0; i < buttonList.size(); i++){
                        Button tmp = buttonList.get(i);
                        if(tmp.isRange(e.getX(), e.getY())){
                            tmp.hover(e.getX(), e.getY());
                            break;
                        }
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
        if(buttonList.size() != 0){
            if(buttonList.get(0).getHeight() != Global.MIN_PICTURE_SIZE * 4){
                buttonList = new ArrayList();
                genButton();
            }
        }
        for(int i = 0 ; i < buttonList.size(); i++){
            buttonList.get(i).update();
        }
    }

    @Override
    public void sceneEnd() {
        buttonList = null;
        imageController.clearImage();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, 32 * Global.MIN_PICTURE_SIZE, 24 * Global.MIN_PICTURE_SIZE, null);
        if(buttonList != null){
            for(int i = 0 ; i < buttonList.size(); i++){
            buttonList.get(i).paint(g);
            }
        }  
    }
    @Override
    public MouseCommandListener getMouseCommandListener(){
        return mouseCommandListener;
    }
    
    
    private void genButton(){
        int buttonParameter[] = {12 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE, 8 * Global.MIN_PICTURE_SIZE, 4 * Global.MIN_PICTURE_SIZE};
        //new Game Button
        Button newGameButton = new Button(buttonParameter[0], buttonParameter[1], buttonParameter[2], buttonParameter[3]
                , imageController.tryGetImage(Path.Image.Button.StartButton.START_BUTTON_ROOT)
                , imageController.tryGetImage(Path.Image.Button.StartButton.START_BUTTON_CLICK)
                , imageController.tryGetImage(Path.Image.Button.StartButton.START_BUTTON_HOVER));
        
        newGameButton.setButtonListener(new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                sceneController.changeScene(new GameScene1(sceneController));
            }

            @Override
            public void hover(int x, int y) {
                
            }
            
        });
        //Load Data Button
        Button loadGameButton = new Button(buttonParameter[0], buttonParameter[1] +  6 * Global.MIN_PICTURE_SIZE, buttonParameter[2], buttonParameter[3]
                , imageController.tryGetImage(Path.Image.Button.LoadGameButton.LOAD_GAME_BUTTON_ROOT)
                , imageController.tryGetImage(Path.Image.Button.LoadGameButton.LOAD_GAME_BUTTON_CLICK)
                , imageController.tryGetImage(Path.Image.Button.LoadGameButton.LOAD_GAME_BUTTON_HOVER));
        
        loadGameButton.setButtonListener(new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                sceneController.changeScene(new LoadDataScene(sceneController));
            }

            @Override
            public void hover(int x, int y) {
                
            }
            
        });
        
        //Rank Game Button
        Button rankButton = new Button(buttonParameter[0], buttonParameter[1] + 12 * Global.MIN_PICTURE_SIZE, buttonParameter[2], buttonParameter[3]
                , imageController.tryGetImage(Path.Image.Button.RankButton.RANK_BUTTON_ROOT)
                , imageController.tryGetImage(Path.Image.Button.RankButton.RANK_BUTTON_CLICK)
                , imageController.tryGetImage(Path.Image.Button.RankButton.RANK_BUTTON_HOVER));
        
        rankButton.setButtonListener(new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                sceneController.changeScene(new RankScene(sceneController));
            }

            @Override
            public void hover(int x, int y) {
                
            }
            
        });
        //Exit Game Button
        Button exitButton = new Button(buttonParameter[0], buttonParameter[1] + 18 * Global.MIN_PICTURE_SIZE, buttonParameter[2], buttonParameter[3]
                , imageController.tryGetImage(Path.Image.Button.ExitButton.EXIT_BUTTON_ROOT)
                , imageController.tryGetImage(Path.Image.Button.ExitButton.EXIT_BUTTON_CLICK)
                , imageController.tryGetImage(Path.Image.Button.ExitButton.EXIT_BUTTON_HOVER));
        
        exitButton.setButtonListener(new ButtonListener(){

            @Override
            public void onClick(int x, int y) {
                System.exit(0);
            }

            @Override
            public void hover(int x, int y) {
                
            }
            
        });
        
        //arraylist element 1 = newGaemeButton, 2 = loadGameButton, 3 = rankButton, 4 = exitButton
        buttonList.add(newGameButton);
        buttonList.add(loadGameButton);
        buttonList.add(rankButton);
        buttonList.add(exitButton);

    }
    
}
