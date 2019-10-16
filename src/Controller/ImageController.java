/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author user
 */
public class ImageController {
    public static class KeyPair{
        private String path;
        private BufferedImage image;
        //constructor
        public KeyPair(String path, BufferedImage image){
            this.path = path;
            this.image = image;
        }
    }
    
    public static ImageController imageController;
    private ArrayList<KeyPair> imageList;
  
    //constructor
    private ImageController(){
        imageList = new ArrayList<KeyPair>();
    };
    
    //accessor
    public static ImageController genInstance(){
        if(imageController == null){
           imageController = new ImageController();
        }
        return imageController;
    }
    
    public BufferedImage tryGetImage(String path){
        BufferedImage image = searchImage(path);
        if(image == null){
            image = addImage(path);
        }
        return image;
    }
    public void clearImage(){
        imageList.clear();
    }
    
    private BufferedImage addImage(String path){
        try{
            BufferedImage image = ImageIO.read(getClass().getResource(path));
            if(image == null){
                return null;
            }
            imageList.add(new KeyPair(path, image));
            return image;
        }catch(IOException e){
            
        }
        return null;
    }
    
    
    private BufferedImage searchImage(String path){
        KeyPair key = null;
        for(int i = 0; i < imageList.size(); i++){
            if(imageList.get(i).path.equals(path)){
                key = imageList.get(i);
                break;
            }
        }
        if(key == null){
            return null;
        }
        return key.image;
    }
}

