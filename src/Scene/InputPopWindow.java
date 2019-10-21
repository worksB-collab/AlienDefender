/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Controller.CommandSolver;
import Controller.CommandSolver.KeyCommandListener;
import Controller.CommandSolver.MouseCommandListener;
import Controller.CommandSolver.TypedListener;
import Value.DrawStringPoint;
import Value.Global;
import com.sun.glass.events.KeyEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 *
 * @author user
 */
public class InputPopWindow extends PopUpWindow{
    private DrawStringPoint point;
    private Font font;
     private String text;
    private boolean isEnd;
   
    public InputPopWindow(int x, int y, int width, int height, LinkedList<Character> charList) {
        super(x, y, width, height);
        font = Global.FONT_INPUT;
        text = "";
        isEnd = false;
        super.setKeyCommandListener( new KeyCommandListener(){

            @Override
            public void keyPressed(int commandCode, long trigTime) {
            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {
            }
            
        });
        
        super.setTypedListener( new TypedListener(){
            @Override
            public void keyTyped(char c, long trigTime) {

                if(c == KeyEvent.VK_ENTER){
                    isEnd = true;
                }else if(c == KeyEvent.VK_BACKSPACE){
                    if(charList.size() >= 1){
                       charList.removeLast(); 
                    }
                }else{
                    charList.add(c);
                }
                text  = "";
                for(Character character : charList){
                    text += character.charValue();
                }
            }

        });
        super.setMouseCommandListener( new MouseCommandListener(){

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                
            }
            
        });
    }
     
    @Override
    public boolean isEnd() {
        return isEnd;
    }
    
    @Override
    public void update(){
        if(point != null){
            point.setText(text);
            point.update(width, height);
        }
    }
    @Override
    public void paint(Graphics g){
        if(point == null){
            point = new DrawStringPoint(x, y, g, font,text, width, height);
        }
        g.setFont(font);
        g.drawString(text, point.getX(), point.getY());
    }
    
}
