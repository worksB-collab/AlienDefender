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
import javafx.scene.media.AudioClip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author user
 */
public class AudioControllerForAudioClip {

    public static class AudioKeyPair {

        private String path;
        private AudioClip audio;

        //Constructor

        public AudioKeyPair(String path, AudioClip audio) {
            this.path = path;
            this.audio = audio;
        }
    }

    public static AudioControllerForAudioClip audioController;
    private ArrayList<AudioKeyPair> audioList;

    //constructor
    private AudioControllerForAudioClip() {
        audioList = new ArrayList<AudioKeyPair>();
    }

    ;
    
    //accessor
    public static AudioControllerForAudioClip genInstance() {
        if (audioController == null) {
            audioController = new AudioControllerForAudioClip();
        }
        return audioController;
    }

    public void clearAudio() {
        audioList.clear();
    }

    public AudioClip tryGetAudio(String path) {
        AudioClip audio = searchAudio(path);
        if (audio == null) {
            audio = addAudio(path);
        }
        return audio;
    }

    private AudioClip addAudio(String path) {
        URL url;
        url = getClass().getResource(path);

        AudioClip clip = new AudioClip(url.toString());
        AudioKeyPair key = new AudioKeyPair(path, clip);
        return clip;
    }

    private AudioClip searchAudio(String path) {
        AudioKeyPair key = null;
        for (int i = 0; i < audioList.size(); i++) {
            if (audioList.get(i).path.equals(path)) {
                key = audioList.get(i);
                break;
            }
        }
        if (key == null) {
            return null;
        }
        return key.audio;
    }

}
