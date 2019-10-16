/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;


import Value.Global;
import Controller.CommandSolver;
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
        frame.setBounds(400, 300, 816, 639);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gPanel);
        frame.setVisible(true);
        
        long startTime = System.currentTimeMillis();
        long lastTime = System.currentTimeMillis();
        long UpdatedFrame = 0;
        CommandSolver cs = new CommandSolver.Builder(Global.MILLISEC_PER_UPDATE).enableMouseTrack(gPanel).gen();
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
                frame.repaint();
            }
            
            
        }
        
    }
    
}
