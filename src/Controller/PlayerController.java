/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Graphics;

/**
 *
 * @author user
 */
public class PlayerController {
    public static  PlayerController playerController;
    private String name;
    private long score;
    private int stage;
    
    private PlayerController(){
        this.name = "Player";
        this.score = 0;
        this.stage = 1;
    }
    
    public static PlayerController genInstance(){
        if(playerController == null){
            playerController = new PlayerController();
        }
        return playerController;
    }
    
    //setter
    public void setName(String name){
        this.name = name;
    }
    public void setScore(long score){
        this.score = score;
    }
    public void addScore(int score){
        this.score += score;
    }
    public void setState(int stage){
        this.stage = stage;
    }
    public void addStage(int stage){
        this.stage += stage;
    }
    
    public void update(){
        
    }
    
    public void paint(Graphics g){
        
    }
    
}
