/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import gameobjects.Tower;
import controllers.AlienController;
import static controllers.AudioController.audioController;
import controllers.BackgroundController;
import controllers.CommandSolver;
import controllers.CommandSolver.MouseCommandListener;
import controllers.CommandSolver.MouseState;
import controllers.DelayCounter;
import controllers.ImageController;
import controllers.PlayerController;
import controllers.RouteController;
import controllers.RouteController.RoutePoint;
import controllers.SceneController;
import controllers.ScoreController;
import controllers.TowerController;
import gameobjects.Button;
import gameobjects.Button.ButtonListener;
import values.Global;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.sound.sampled.Clip;
import parameter.AlienParameter;
import static values.Global.FONT_WIN;
import values.Path;

/**
 *
 * @author user
 */
public class GameScene extends Scene {

    private CommandSolver.MouseCommandListener mouseCommandListener;
    private PlayerController playerController;
    private BackgroundController backgroundController;
    private ImageController imageController;
    private RouteController routeController;
    private PopUpWindow popUpWindow;
    private LinkedList<Button> buttonList;
    private Button buttonNext;
    private Point spot;
    private AlienController alienController;
    private TowerController towerController;
    private int stage;
    private Clip audio, winAudio;
    private AlienParameter alienParameter;
    private float alienSet[][];
    private BufferedImage trophy, mask;
    private int winState;
    private DelayCounter winDelay;
    private PlayerController plyaerController;
    private ScoreController scoreController;
    private int notEnough;

    public GameScene(SceneController sceneController, int stage) {
        super(sceneController);
        this.stage = stage;
        playerController = PlayerController.genInstance();
        playerController.setStage(stage);
        imageController = ImageController.genInstance();
        backgroundController = new BackgroundController(stage);
        routeController = new RouteController();
        buttonList = new LinkedList();
        playerController = PlayerController.genInstance();
        scoreController = ScoreController.genInstance();
        switch (stage) {
            case 1:
                audio = audioController.tryGetAudio(Path.Audios.Musics.INTHEGAME1);
                break;
            case 2:
                audio = audioController.tryGetAudio(Path.Audios.Musics.INTHEGAME2);
                break;
            case 3:
                audio = audioController.tryGetAudio(Path.Audios.Musics.INTHEGAME3);
                break;
            case 4:
                audio = audioController.tryGetAudio(Path.Audios.Musics.INTHEGAME4);
                break;
            case 5:
                audio = audioController.tryGetAudio(Path.Audios.Musics.INTHEGAME5);
                break;

        }
        winAudio = audioController.tryGetAudio(Path.Audios.Musics.WIN1);
        audio.loop(Clip.LOOP_CONTINUOUSLY);
        trophy = imageController.tryGetImage(Path.Image.TROPHY);
        mask = imageController.tryGetImage(Path.Image.Scene.MASK);
        winDelay = new DelayCounter(100);
        notEnough = 1;
        mouseCommandListener = new MouseCommandListener() {

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == MouseState.RELEASED || state == MouseState.CLICKED) {
                    int x = e.getX();
                    int y = e.getY();
                    for (Button tmp : buttonList) {
                        if (tmp.isRange(x, y)) {
                            tmp.click(x, y);

                            spot = new Point(x, y);
                        }
                    }
                    if (buttonNext.isRange(x, y)) {
                        buttonNext.click(x, y);
                    }
                }
                if (state == MouseState.MOVED) {
                    int x = e.getX();
                    int y = e.getY();
                    for (Button tmp : buttonList) {
                        if (tmp.isRange(x, y)) {
                            tmp.hover(x, y);
                            spot = new Point(x, y);
                        }
                    }
                }
            }
        };

    }

    public int getNotEnough() {
        return notEnough;
    }

    @Override
    public void sceneBegin() {
        playerController.reset();
        routeController.genRoad(stage);
        genButton(routeController.getSetPoint());
        alienController = new AlienController(routeController.getRoute());
        alienParameter = new AlienParameter(stage);
        alienSet = alienParameter.setStageValue();
        int enemyCount = 0;
        for (int i = 0; i < stage; i++) {
            alienController.gameLevelSetting(alienSet[i][0], alienSet[i][1],
                    alienSet[i][2], (int) alienSet[i][3], (int) alienSet[i][4]);
            enemyCount += alienSet[i][4];
        }
        alienController.setEnemyAmount(enemyCount);
//        alienController.gameLevelSetting(alienSet[3][0], alienSet[3][1], 80, 1, 1);
//        alienController.setEnemyAmount(1);

    }

    @Override
    public void sceneUpdate() {
        //check player life
        if (playerController.getHP() <= 0) {
            sceneController.changeScene(new GameOverScene(sceneController));
        }
        backgroundController.update();
        if (buttonList.size() != 0) {

            if (buttonList.get(0).getWidth() != Global.MIN_PICTURE_SIZE) {
                routeController.genRoad(1);
                genButton(routeController.getSetPoint());
            }
        }
        for (Button button : buttonList) {
            button.update();
        }

        buttonNext.update();

        alienController.update();

        if (towerController == null) {
            towerController = new TowerController(alienController);
        }
        //Player
        playerController.update();
        alienController.update();
        towerController.update();

        if (popUpWindow != null) {
            playerController.setNotEnough(popUpWindow.getNotEnough());
            popUpWindow.update();
            if (popUpWindow.isEnd()) {
                popUpWindow = null;
            }
        }
        //check clear or not
        if (alienController.isEnd()) {
            popUpWindow = null;
            winState = 1;
            if (winDelay.update()) {
//                sceneController.changeScene(new TextReader(sceneController, ++stage));
                winState = 0;
            }
        }
    }

    @Override
    public void sceneEnd() {
        audio.close();
        winAudio.close();
        audioController.clearAudio();
        playerController.setScore(playerController.getScore() + (playerController.getHP() / 10)
                + (int) (playerController.getMoney()) / 10);
        System.out.println(scoreController.getKills());
    }

    @Override
    public void paint(Graphics g) {
        backgroundController.paint(g);
        routeController.paint(g);
        //Button paint
        if (buttonList != null) {
            for (Button button : buttonList) {
                button.paint(g);
            }
        }

        alienController.paint(g);
        towerController.paint(g);
        if (spot != null) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect((int) spot.getX() / (int) Global.MIN_PICTURE_SIZE * (int) Global.MIN_PICTURE_SIZE, (int) spot.getY() / (int) Global.MIN_PICTURE_SIZE * (int) Global.MIN_PICTURE_SIZE, (int) Global.MIN_PICTURE_SIZE, (int) Global.MIN_PICTURE_SIZE);
            g.setColor(Color.BLACK);
        }
        //player
        playerController.paint(g);
        //PopUpWindow
        if (popUpWindow != null) {
            popUpWindow.paint(g);
        }
        //win trophy
        if (winState == 1) {
            audio.stop();
            winAudio.start();
            g.drawImage(mask, 0, 0, null);
            g.setFont(FONT_WIN);
            g.setColor(Color.gray);
            g.drawString("You Win", 300, 420);
            g.setFont(FONT_WIN);
            g.setColor(Color.orange);
            g.drawString("You Win", 310, 425);
        }
        buttonNext.paint(g);
    }

    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener() {
        if (popUpWindow != null) {
            return popUpWindow.getMouseCommandListener();
        }
        return mouseCommandListener;
    }

    //generate
    private void genButton(LinkedList<RoutePoint> setPoint) {
        if (buttonList.size() != 0) {
            buttonList = new LinkedList<Button>();
        }

        for (RoutePoint tmp : setPoint) {
            float x0 = tmp.getX();
            float y0 = tmp.getY();
            Button button = new Button(x0, y0, Global.MIN_PICTURE_SIZE, Global.MIN_PICTURE_SIZE,
                    imageController.tryGetImage("/Resources/Images/Background/setPoint5.png"));

            button.setButtonListener(new ButtonListener() {
                @Override
                public void onClick(int x, int y) {
                    boolean isBuilt = false;
                    Tower tower = null;
                    if (towerController != null) {
                        for (int i = 0; i < towerController.getTowers().size(); i++) {
                            tower = towerController.getTowers().get(i);
                            if (tower.getX() == x0 && tower.getY() == y0) {
                                isBuilt = true;
                                break;
                            }
                        }
                    }
                    if (!isBuilt) {
                        popUpWindow = new TowerSelectWindow(x0, y0, 12 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE, towerController);
                    } else {
                        popUpWindow = new TowerInformationWindow(x0, y0, 12 * Global.MIN_PICTURE_SIZE, 2 * Global.MIN_PICTURE_SIZE, tower, playerController);
                    }

                }

                @Override
                public void hover(int x, int y) {

                }

            });
            buttonList.add(button);
        }

        buttonNext = new Button(27 * Global.MIN_PICTURE_SIZE, 21f * Global.MIN_PICTURE_SIZE, 4f * Global.MIN_PICTURE_SIZE, 2f * Global.MIN_PICTURE_SIZE,
                imageController.tryGetImage("/Resources/Images/Button/Button_01_1.png"));
        buttonNext.setFont(Global.FONT_INFOWINDOW);
        buttonNext.setText("NEXT");

        buttonNext.setButtonListener(new Button.ButtonListener() {
            @Override
            public void onClick(int x, int y) {
                sceneController.changeScene(new TextReader(sceneController, ++stage));

            }

            @Override
            public void hover(int x, int y) {
            }
        }
        );
    }
}
