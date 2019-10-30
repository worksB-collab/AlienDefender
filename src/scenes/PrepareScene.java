/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import static controllers.AudioController.audioController;
import controllers.ImageController;
import controllers.SceneController;
import gameobjects.Button;
import values.Path;
import controllers.CommandSolver;
import controllers.CommandSolver.KeyCommandListener;
import controllers.CommandSolver.MouseCommandListener;
import controllers.CommandSolver.MouseState;
import controllers.CommandSolver.TypedListener;
import controllers.PlayerController;
import values.Global;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.sound.sampled.Clip;

/**
 *
 * @author user
 */
public class PrepareScene extends Scene {

    private MouseCommandListener mouseCommandListener;
    private TypedListener typedListener;
    private KeyCommandListener keyCommandListener;
    private InputPopWindow popWindow;
    private LinkedList<Character> charList;
    private ImageController imageController;
    private PlayerController playerController;
    private BufferedImage image;
    private Button backButton;
    private Clip audio;

    public PrepareScene(SceneController sceneController, Clip audio) {
        super(sceneController);
        imageController = ImageController.genInstance();
        playerController = PlayerController.genInstance();
        image = imageController.tryGetImage(Path.Image.Scene.PREPARE_SCENE);
        this.audio = audio;
        charList = new LinkedList();

        mouseCommandListener = new MouseCommandListener() {
            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == MouseState.RELEASED || state == MouseState.CLICKED) {
//                    if(backButton.isRange(e.getX(), e.getY())){
//                        backButton.click(e.getX(), e.getY());
//                    }
                }
            }

        };
        typedListener = new TypedListener() {
            @Override
            public void keyTyped(char c, long trigTime) {
            }
        };
        keyCommandListener = new KeyCommandListener() {

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
        popWindow = new InputPopWindow((Global.FRAME_WIDTH - Global.MIN_PICTURE_SIZE * 12f) / 2f, (Global.FRAME_HEIGHT - Global.MIN_PICTURE_SIZE * 4f) / 2f,
                Global.MIN_PICTURE_SIZE * 12f, Global.MIN_PICTURE_SIZE * 4f, charList, 15);
//        genButton();
    }

    @Override
    public void sceneUpdate() {
//        backButton.update();
        if (popWindow != null) {
            popWindow.update();
            if (popWindow.isEnd()) {
                //store information
                playerController.setName(popWindow.getResult());
                popWindow = null;
                //preLoading
                TextContent text = TextContent.genInstance();
                sceneController.changeScene(new TextReader(sceneController, 1));
//                sceneController.changeScene(new GameScene1(sceneController));

            }
        }
    }

    @Override
    public void sceneEnd() {
//        backButton = null;
        imageController.clearImage();
        audio.close();
        audioController.clearAudio();
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(image, 0, 0, (int) (32 * Global.MIN_PICTURE_SIZE), (int) (24 * Global.MIN_PICTURE_SIZE), null);
//        backButton.paint(g);
        if (popWindow != null) {
            popWindow.paint(g);
        }
    }

    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener() {
        if (popWindow != null) {
            return popWindow.getMouseCommandListener();
        }
        return mouseCommandListener;
    }

    @Override
    public CommandSolver.TypedListener getTypedListener() {
        if (popWindow != null) {

            return popWindow.getTypedListener();
        }
        return typedListener;
    }

    @Override
    public CommandSolver.KeyCommandListener getKeyCommandListener() {
        if (popWindow != null) {
            return popWindow.getKeyCommandListener();
        }
        return keyCommandListener;
    }

    private void genButton() {
        backButton = new Button(2 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE, 8 * Global.MIN_PICTURE_SIZE, 4 * Global.MIN_PICTURE_SIZE,
                imageController.tryGetImage(Path.Image.Button.BACK_BUTTON));

        backButton.setButtonListener(new Button.ButtonListener() {

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
