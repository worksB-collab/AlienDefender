/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import controllers.AudioController;
import controllers.BackgroundController;
import controllers.ImageController;
import controllers.SceneController;
import gameobjects.Button;
import gameobjects.Button.ButtonListener;
import controllers.CommandSolver;
import controllers.CommandSolver.MouseCommandListener;
import controllers.CommandSolver.MouseState;
import values.Global;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.sound.sampled.Clip;
import values.Path;

/**
 *
 * @author user
 */
public class MenuScene extends Scene {

    private MouseCommandListener mouseCommandListener;
    private ImageController imageController;
    private ArrayList<Button> buttonList;
    private BackgroundController backgroundController;
    private BufferedImage image;
    private Clip audio;
    private AudioController audioController;

    public MenuScene(SceneController sceneController, Clip audio) {
        super(sceneController);
        this.audio = audio;
        imageController = ImageController.genInstance();
        backgroundController = new BackgroundController(1);
        //mouse listener
        mouseCommandListener = new MouseCommandListener() {
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                //check click
                if (state == MouseState.RELEASED || state == MouseState.CLICKED) {
                    int x = e.getX();
                    int y = e.getY();
                    for (int i = 0; i < buttonList.size(); i++) {
                        Button tmp = buttonList.get(i);
                        if (tmp.isRange(x, y)) {
                            tmp.click(x, y);
                            break;
                        }
                    }
                }
                //check hover
                if (state == MouseState.MOVED) {
                    int x = e.getX();
                    int y = e.getY();
                    for (int i = 0; i < buttonList.size(); i++) {
                        Button tmp = buttonList.get(i);
                        if (tmp.isRange(x, y)) {
                            tmp.hover(x, y);
                            break;
                        } else {

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
        backgroundController.update();

        if (buttonList == null) {
            if (buttonList.get(0).getHeight() != Global.MIN_PICTURE_SIZE * 4) {
                buttonList = new ArrayList();
                genButton();
            }
        }
        for (int i = 0; i < buttonList.size(); i++) {
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
        backgroundController.paint(g);

        if (buttonList != null) {
            for (int i = 0; i < buttonList.size(); i++) {
                Button btn = buttonList.get(i);
                btn.paint(g);
            }
        }
    }

    @Override
    public MouseCommandListener getMouseCommandListener() {
        return mouseCommandListener;
    }

    private void genButton() {
        float buttonParameter[] = {Global.FRAME_WIDTH / 2f, Global.FRAME_HEIGHT / 2f + 6f * Global.MIN_PICTURE_SIZE, 5f * Global.MIN_PICTURE_SIZE, 2f * Global.MIN_PICTURE_SIZE};
        //new Game Button
        Button newGameButton = new Button(buttonParameter[0] - 14 * Global.MIN_PICTURE_SIZE, buttonParameter[1], buttonParameter[2], buttonParameter[3],
                imageController.tryGetImage("/Resources/Images/Button/Button_01_1.png"));
        newGameButton.setText("NEW");
        newGameButton.setButtonListener(new ButtonListener() {

            @Override
            public void onClick(int x, int y) {
                sceneController.changeScene(new PrepareScene(sceneController));
                audio.close();
//                audioController.clearAudio();
            }

            @Override
            public void hover(int x, int y) {
            }

        });
        //Load Data Button
        Button loadGameButton = new Button(buttonParameter[0] - 6 * Global.MIN_PICTURE_SIZE, buttonParameter[1], buttonParameter[2], buttonParameter[3],
                imageController.tryGetImage("/Resources/Images/Button/Button_01_1.png"));
        loadGameButton.setText("LOAD");
        loadGameButton.setButtonListener(new ButtonListener() {

            @Override
            public void onClick(int x, int y) {
                sceneController.changeScene(new LoadDataScene(sceneController));
            }

            @Override
            public void hover(int x, int y) {

            }

        });

        //Rank Game Button
        Button rankButton = new Button(buttonParameter[0] + 2 * Global.MIN_PICTURE_SIZE, buttonParameter[1], buttonParameter[2], buttonParameter[3],
                imageController.tryGetImage("/Resources/Images/Button/Button_01_1.png"));
        rankButton.setText("RANK");

        rankButton.setButtonListener(new ButtonListener() {

            @Override
            public void onClick(int x, int y) {
                sceneController.changeScene(new RankScene(sceneController));
            }

            @Override
            public void hover(int x, int y) {

            }

        });
        //Exit Game Button
        Button exitButton = new Button(buttonParameter[0] + 10 * Global.MIN_PICTURE_SIZE, buttonParameter[1], buttonParameter[2], buttonParameter[3], imageController.tryGetImage("/Resources/Images/Button/Button_01_1.png")
        );
        exitButton.setText("EXIT");
        exitButton.setButtonListener(new ButtonListener() {

            @Override
            public void onClick(int x, int y) {
                System.exit(0);
            }

            @Override
            public void hover(int x, int y) {

            }

        });

        //arraylist element 1 = newGaemeButton, 2 = loadGameButton, 3 = rankButton, 4 = exitButton
        buttonList = new ArrayList<>();
        buttonList.add(newGameButton);
        buttonList.add(loadGameButton);
        buttonList.add(rankButton);
        buttonList.add(exitButton);

    }

}
