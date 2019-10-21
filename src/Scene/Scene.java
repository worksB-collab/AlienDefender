/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;


import Controller.CommandSolver;
import Controller.SceneController;
import Controller.CommandSolver.KeyCommandListener;
import Controller.CommandSolver.MouseCommandListener;
import Controller.CommandSolver.TypedListener;
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
