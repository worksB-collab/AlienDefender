/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import controllers.SceneController;
import scenes.StartScene;
import controllers.CommandSolver.CommandWrapper;
import java.awt.Graphics;
import javax.swing.JPanel;
import values.Loadfont;
import values.Loadfont.CyFont;

/**
 *
 * @author user
 */
public class GameJPanel extends JPanel {

    private SceneController sceneController;
//    private Loadfont f;

    public GameJPanel() {
        sceneController = new SceneController();
        sceneController.changeScene(new StartScene(sceneController));
    }

    public void update(CommandWrapper commands) {
        sceneController.sceneUpdate(commands);
    }

    @Override
    public void paintComponent(Graphics g) {
        sceneController.paint(g);

    }
}
