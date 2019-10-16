/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Controller.CommandSolver;
import Controller.SceneController;
import GameObject.GameObject;
import java.awt.Graphics;

/**
 *
 * @author user
 */
public abstract class PopUpWindow extends GameObject{
    protected CommandSolver.KeyCommandListener keyCommandListener;
    protected CommandSolver.MouseCommandListener mouseCommandListener;
    
    public PopUpWindow(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
    
    public void setKeyCommandListener(CommandSolver.KeyCommandListener eyCommandListener){
        this.keyCommandListener = keyCommandListener;
    }
    
    public void setMouseCommandListener(CommandSolver.KeyCommandListener eyCommandListener){
        this.mouseCommandListener = mouseCommandListener;
    }
    
    public CommandSolver.KeyCommandListener getKeyCommandListener(){
        return keyCommandListener;
    }
    
    public CommandSolver.MouseCommandListener getMouseCommandListener(){
        return mouseCommandListener;
    }
    public abstract Object getResult();
    public abstract boolean isEnd();
    @Override
    public void update(){
        
    }
    @Override
    public void paint(Graphics g){
        
    }
}
