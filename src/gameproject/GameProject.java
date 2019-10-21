/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;


import Value.Global;
import Controller.CommandSolver;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author user
 */
public class GameProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Alien Defender");
        GameJPanel gPanel = new GameJPanel();
        frame.setBounds(400, 300, 1040, 807);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gPanel);
        frame.setVisible(true);
        long startTime = System.currentTimeMillis();
        long lastTime = System.currentTimeMillis();
        long UpdatedFrame = 0;
        CommandSolver cs = new CommandSolver.Builder(Global.MILLISEC_PER_UPDATE, new int[][]{
            {KeyEvent.VK_ENTER, Global.KEY_ENTER},
            {KeyEvent.VK_BACK_SPACE, Global.KEY_BACK_SPACE}
        }).enableMouseTrack(gPanel).trackChar().gen();
        addKeyListener(frame, cs);
        cs.start();
        
        while(true){
            long currentTime = System.currentTimeMillis();
            long targetUpdate = (currentTime - startTime) / Global.MILLISEC_PER_UPDATE;
            
            while(UpdatedFrame < targetUpdate){
                gPanel.update(cs.update());
                UpdatedFrame++;
            }
            
            if((currentTime - lastTime) >= Global.MILLISEC_PER_UPDATE){
                lastTime = currentTime;
                //check Frame Ratio
                int w = frame.getWidth();
                int h = frame.getHeight();
                int rw = w - 16;
                int rh = h - 39;
                Double d1 = new Double(rw / rh);
                Double d2 = new Double(8 / 6);
                if(d1.floatValue() != d2.floatValue()){
                    int r = rw / 4;
                    rw = r * 4;
                    rh = r * 3;
                }
                gPanel.setSize(rw , rh );
                //calibration MIN SIZE
//                Global.FRAME_WIDTH = rw;
//                Global.FRAME_HEIGHT = rh;
//                Global.MIN_PICTURE_SIZE = (int)Math.sqrt((rh * rw) / 768d);
                frame.repaint();
            }
            
            
        }
        
    }
    public static void addKeyListener(JFrame j, CommandSolver cs) {
        j.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                cs.trig(e.getKeyChar(), true);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                cs.trig(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                cs.trig(e.getKeyCode(), false);
            }
        });
        j.setFocusable(true);
    }
    
}
