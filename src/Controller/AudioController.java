/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Value.Path;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
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
    public static class SoundKeyPair{
        private String path;
        private AudioClip audio;
        //constructor
        public SoundKeyPair(String path, AudioClip audio){
            this.path = path;
            this.audio = audio;
        }
    }
    public static class MusicKeyPair{
        private String path;
        private Clip audio;
        //Constructor
        public MusicKeyPair(String path, Clip audio){
            this.path = path;
            this.audio = audio;
        }
    }
    
    public static AudioController audioController;
    private ArrayList<SoundKeyPair> soundList;
    private ArrayList<MusicKeyPair> musicList;
  
    //constructor
    private AudioController(){
        soundList = new ArrayList<SoundKeyPair>();
        musicList = new ArrayList<MusicKeyPair>();
    };
    
    //accessor
    public static AudioController genInstance(){
        if(audioController == null){
           audioController = new AudioController();
        }
        return audioController;
    }
    
    
    public void clearAudio(){
        soundList.clear();
        musicList.clear();
    }
    //Sound
    public AudioClip tryGetSound(String path){
        AudioClip sound = searchSound(path);
        if(sound == null){
            sound = addSound(path);
        }
        return sound;
    }
    
    private AudioClip addSound(String path){
            URL url = getClass().getResource(path);
            AudioClip audio =  Applet.newAudioClip(url);
            if(audio == null){
                return null;
            }
            soundList.add(new SoundKeyPair(path, audio));
            return audio;    
    }
    private AudioClip searchSound(String path){
        SoundKeyPair key = null;
        for(int i = 0; i < soundList.size(); i++){
            if(soundList.get(i).path.equals(path)){
                key = soundList.get(i);
                break;
            }
        }
        if(key == null){
            return null;
        }
        return key.audio;
    }
    //Mousic
    public Clip tryGetMusic(String path){
        Clip music = searchMusic(path);
        if(music == null){
            music = addMusic(path);
        }
        return music;
    }
    
    private Clip addMusic(String path){
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
    private Clip searchMusic(String path){
        MusicKeyPair key = null;
        for(int i = 0; i < musicList.size(); i++){
            if(musicList.get(i).path.equals(path)){
                key = musicList.get(i);
                break;
            }
        }
        if(key == null){
            return null;
        }
        return key.audio;
    }
    
    

}
