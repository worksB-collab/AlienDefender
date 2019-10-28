/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import values.Path;

/**
 *
 * @author user
 */
public class TextContent {
    private static TextContent textContent;
    private String text[];
    private TextContent(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Documents\\NetBeansProjects\\GameProject\\src\\resources\\Texts\\text.txt"));
            char charArr[] = new char[500];
            br.read(charArr);
            String str = String.valueOf(charArr);
            text = str.split("-");
            br.close();
        }catch(FileNotFoundException ex){
            
        }catch(IOException ex){
            
        }
    }
    public static TextContent genInstance(){
        if(textContent == null){
            textContent = new TextContent();
        }
        return textContent;
    }
    public String getText(int stage){
        if(stage < 0 || stage > 6){
            return "";
        }
        return text[stage];
    }
}
