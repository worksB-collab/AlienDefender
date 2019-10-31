/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;


import controllers.ImageController;
import controllers.SceneController;
import gameobjects.Button;
import gameobjects.Button.ButtonListener;
import values.Path;
import controllers.CommandSolver;
import controllers.CommandSolver.MouseState;
import controllers.RankController;
import controllers.RankController.Rank;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.sound.sampled.Clip;
import values.DrawStringPoint;
import values.Global;

/**
 *
 * @author user
 */
public class RankScene extends Scene{
    private CommandSolver.MouseCommandListener mouseCommandListener;
    private ImageController imageController;
    private RankController rankController;
    private ArrayList<Rank> rankList;
    private DrawStringPoint points[];
    private String text[];
    private BufferedImage image;
    private Button backButton;
    private Clip audio;
    public RankScene(SceneController sceneController, Clip audio) {
        super(sceneController);
        imageController = ImageController.genInstance();
        image = imageController.tryGetImage(Path.Image.Scene.RANK_SCENE);
        this.audio = audio;
        rankController = RankController.genInstance();
        rankList = rankController.getList();
        text = new String[rankList.size()];
        for(int i = 0; i < rankList.size(); i++){
            Rank tmp = rankList.get(i);
            text[i] = "Name: " + tmp.getName() + "       Score: " + tmp.getScore();
        }
        points = new DrawStringPoint[rankList.size()];

        
        
        
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
        g.drawImage(image, 0, 0, (int)(32 * Global.MIN_PICTURE_SIZE), (int)(24 * Global.MIN_PICTURE_SIZE), null);
        backButton.paint(g);
        g.setColor(Color.orange);
        if(points[0] == null){
            float x = 0;
            float y = 2 * Global.MIN_PICTURE_SIZE;
            int width = (int) ((Global.FRAME_WIDTH - 2 * Global.MIN_PICTURE_SIZE));
            int height = (int) ((Global.FRAME_HEIGHT - 3 * Global.MIN_PICTURE_SIZE) / points.length);
            for (int i = 0; i < points.length; i++) {
                points[i] = new DrawStringPoint(x, y, g, Global.FONT_TEXT, text[i], width, height);
                y += height;
            }
        }
        for(int i = 0 ; i < points.length; i++){
            g.drawString(points[i].getText(), (int)points[i].getX(), (int)points[i].getY());
        }
    }
    
    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener(){
        return mouseCommandListener;
    }
    
    private void genButton(){
        backButton = new Button(50, 50,  150, 100,
                imageController.tryGetImage(Path.Image.Button.BACK_BUTTON));
        
        backButton.setButtonListener(new ButtonListener(){

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
