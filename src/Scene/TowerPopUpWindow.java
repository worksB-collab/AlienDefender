/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import controller.TowerController;

/**
 *
 * @author user
 */
public class TowerPopUpWindow extends PopUpWindow{
    private TowerController towerController;
    
    public TowerPopUpWindow(float x, float y, float width, float height, TowerController towerController){
        super(x, y, width, height);
        this.towerController = towerController;
    }
    
    public TowerController getTowerController(){
        return towerController;
    }
    public void setTowerController(){
        this.towerController = towerController;
    }
    @Override
    public boolean isEnd() {
        return true;
    }
    
}
