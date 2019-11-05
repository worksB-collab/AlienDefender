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
public class RankScene extends Scene {

    private CommandSolver.MouseCommandListener mouseCommandListener;
    private ImageController imageController;
    private RankController rankController;
    private ArrayList<Rank> rankList;
    private DrawStringPoint points[][];
    private String text[][];
    private BufferedImage image, image2;
    private Button backButton;
    private Clip audio;
    private int sX;

    public RankScene(SceneController sceneController, Clip audio) {
        super(sceneController);
        imageController = ImageController.genInstance();
        image = imageController.tryGetImage(Path.Image.Scene.SPACE1);
        image2 = imageController.tryGetImage(Path.Image.Scene.SPACE2);
        this.audio = audio;
        rankController = RankController.genInstance();
        rankList = rankController.getList();
        text = new String[rankList.size() + 2][2];
        for (int i = 0; i < rankList.size() + 2; i++) {
            
            if(i == 0){
                text[i][0] = "Player"; 
                text[i][1] = "Score";  
            }else if( i == 1){
                text[i][0] = "";
                text[i][1] = "";
            }else{
                Rank tmp = rankList.get(i - 2);
                text[i][0] = tmp.getName();
                text[i][1] = Long.toString(tmp.getScore());
            }
        }
        points = new DrawStringPoint[rankList.size() + 2][2];

        mouseCommandListener = new CommandSolver.MouseCommandListener() {
            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == MouseState.RELEASED || state == MouseState.CLICKED) {
                    if (backButton.isRange(e.getX(), e.getY())) {
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
        if (sX == -(int) (2*32 * Global.MIN_PICTURE_SIZE)) {
            sX = 0;
        }
        sX -= 1;
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
                    sX + (int) (32 * Global.MIN_PICTURE_SIZE), 0,
                    (int) (32 * Global.MIN_PICTURE_SIZE), (int) (24 * Global.MIN_PICTURE_SIZE), null);
            g.drawImage(image,
                    sX + (int) (2*32 * Global.MIN_PICTURE_SIZE), 0,
                    (int) (32 * Global.MIN_PICTURE_SIZE), (int) (24 * Global.MIN_PICTURE_SIZE), null);
//        backButton.paint(g);

        backButton.paint(g);
        g.setColor(Color.orange);
        if (points[0][0] == null) {
            float x = 0;
            float y = 2 * Global.MIN_PICTURE_SIZE;
            int width = (int) (Global.FRAME_WIDTH) / 2;
            int height = (int) ((Global.FRAME_HEIGHT - 5 * Global.MIN_PICTURE_SIZE) / points.length);
            for (int i = 0; i < points.length; i++) {
                points[i][0] = new DrawStringPoint(x, y, g, Global.FONT_TEXT, text[i][0], width, height);
                points[i][1] = new DrawStringPoint(x +  16 * Global.MIN_PICTURE_SIZE, y, g, Global.FONT_TEXT, text[i][1], width, height);
                y += height;
            }
            

        }
        for (int i = 0; i < points.length; i++) {
            g.drawString(points[i][0].getText(), (int) points[i][0].getX(), (int) points[i][0].getY());
            g.drawString(points[i][1].getText(), (int) points[i][1].getX(), (int) points[i][1].getY());
        }
    }

    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener() {
        return mouseCommandListener;
    }

    private void genButton() {
        backButton = new Button(27 * Global.MIN_PICTURE_SIZE, 21f * Global.MIN_PICTURE_SIZE, 4f * Global.MIN_PICTURE_SIZE, 2f * Global.MIN_PICTURE_SIZE,
                imageController.tryGetImage(Path.Image.Button.BACK_BUTTON));
        backButton.setText("Back");
        backButton.setButtonListener(new ButtonListener() {

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
