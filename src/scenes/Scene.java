/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;


import controllers.SceneController;
import controllers.CommandSolver.KeyCommandListener;
import controllers.CommandSolver.MouseCommandListener;
import controllers.CommandSolver.TypedListener;
import java.awt.Graphics;

/**
 *
 * @author user
 */
public abstract class Scene {
    protected SceneController sceneController;
    
    public Scene(SceneController sceneController){
        this.sceneController = sceneController;
    }
    
    public abstract void sceneBegin();
    public abstract void sceneUpdate();
    public abstract void sceneEnd();
    public abstract void paint(Graphics g);
    public KeyCommandListener getKeyCommandListener(){return null;}
    public MouseCommandListener getMouseCommandListener(){return null;}
    public TypedListener getTypedListener(){return null;}
}
