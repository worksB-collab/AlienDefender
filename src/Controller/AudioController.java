/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author user
 */
public class AudioController {
        public static class KeyPair{
        private String path;
        private AudioClip  audio;
        //constructor
        public KeyPair(String path, AudioClip audio){
            this.path = path;
            this.audio = audio;
        }
    }
    
    public static AudioController audioController;
    private ArrayList<KeyPair> audioList;
  
    //constructor
    private AudioController(){
        audioList = new ArrayList<KeyPair>();
    };
    
    //accessor
    public static AudioController genInstance(){
        if(audioController == null){
           audioController = new AudioController();
        }
        return audioController;
    }
    
    public AudioClip tryGetAudio(String path){
        AudioClip audio = searchAudio(path);
        if(audio == null){
            audio = addAudio(path);
        }
        return audio;
    }
    public void clearAudio(){
        audioList.clear();
    }
    
    private AudioClip addAudio(String path){
        AudioClip audio =  Applet.newAudioClip(getClass().getResource(path));
        if(audio == null){
            return null;
        }
        audioList.add(new KeyPair(path, audio));
        return audio;
    }
    
    
    private AudioClip searchAudio(String path){
        KeyPair key = null;
        for(int i = 0; i < audioList.size(); i++){
            if(audioList.get(i).path.equals(path)){
                key = audioList.get(i);
                break;
            }
        }
        if(key == null){
            return null;
        }
        return key.audio;
    }
}
