/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author user
 */
public class AudioController {
    public static class AudioKeyPair{
        private String path;
        private Clip audio;
        //Constructor
        public AudioKeyPair(String path, Clip audio){
            this.path = path;
            this.audio = audio;
        }
    }
    
    public static AudioController audioController;
    private ArrayList<AudioKeyPair> audioList;
  
    //constructor
    private AudioController(){
        audioList = new ArrayList<AudioKeyPair>();
    };
    
    //accessor
    public static AudioController genInstance(){
        if(audioController == null){
           audioController = new AudioController();
        }
        return audioController;
    }
    
    
    public void clearAudio(){
        audioList.clear();
    }
    
    public Clip tryGetAudio(String path){
        Clip audio = searchAudio(path);
        if(audio == null){
            audio = addAudio(path);
        }
        return audio;
    }
    
    private Clip addAudio(String path){
        try{
            URL url;
            url = getClass().getResource(path);
            File file = new File(url.getPath());
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            return clip;
        }catch(MalformedURLException ex){
            return null;
        }catch(IOException ex){
            return null;
        }catch(UnsupportedAudioFileException ex){
            return null;
        }catch(LineUnavailableException ex){
            return null;
        }
        
    }
    private Clip searchAudio(String path){
        AudioKeyPair key = null;
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
