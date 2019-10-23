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
    public static final String TYPE = "PopUpWindow";
    protected CommandSolver.KeyCommandListener keyCommandListener;
    protected CommandSolver.MouseCommandListener mouseCommandListener;
    protected CommandSolver.TypedListener typedListener;
    public PopUpWindow(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    
    
    public void setKeyCommandListener(CommandSolver.KeyCommandListener keyCommandListener){
        this.keyCommandListener = keyCommandListener;
    }
    
    public void setMouseCommandListener(CommandSolver.MouseCommandListener mouseCommandListener){
        this.mouseCommandListener = mouseCommandListener;
    }
    
    public void setTypedListener(CommandSolver.TypedListener typedListener){
        this.typedListener = typedListener;
    }
    
    public CommandSolver.KeyCommandListener getKeyCommandListener(){
        return keyCommandListener;
    }
    
    public CommandSolver.MouseCommandListener getMouseCommandListener(){
        return mouseCommandListener;
    }
    public CommandSolver.TypedListener getTypedListener(){
        return typedListener;
    }
    public abstract boolean isEnd();
    
    public String getType(){
        return TYPE;
    }
    @Override
    public void update(){
        
    }
    @Override
    public void paint(Graphics g){
        
    }
}
