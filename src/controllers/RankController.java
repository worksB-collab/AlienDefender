/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import values.Path;

/**
 *
 * @author user
 */
public class RankController {
    public static class Rank{
        private String name;
        private long score;
        
        public Rank(String name, long score){
            this.name = name;
            this.score = score;
        }
        
        public String getName(){
            return name;
        }
        public long getScore(){
            return score;
        }
    }
    private static RankController rankController;
    private ArrayList<Rank> rankList;
    private RankController(){
        rankList = new ArrayList();
        try{
//            BufferedReader br = new BufferedReader(new FileReader(getClass().getResource(Path.Texts.RANK).getPath()));
            BufferedReader br = new BufferedReader(new FileReader("rank.txt"));
            while(br.ready()){
                String str[] = br.readLine().split(",");
                if(str.length < 2){
                    continue;
                }
                String name = str[0];
                long score = Long.valueOf(str[1]);
                rankList.add(new Rank(name, score));
            }
            br.close();
            
        }catch(FileNotFoundException ex){
            
        }catch(IOException ex){
            
        }
        
    }
    
    public static RankController genInstance(){
        if(rankController == null){
            rankController = new RankController();
        }
        return rankController;
    }
    public ArrayList<Rank> getList(){
        return rankList;
    }
    public void addRank(String name, long score){
        Rank rank = new Rank(name, score);
        rankList.add(rank);
        sort();
        while(rankList.size() > 10){
            rankList.remove(rankList.size() - 1);
        }
        
        try{
//            BufferedWriter bw = new BufferedWriter(new FileWriter(getClass().getResource(Path.Texts.RANK).getPath()));
            BufferedWriter bw = new BufferedWriter(new FileWriter("rank.txt"));
            String text = "";
            for(int i = 0; i < rankList.size(); i++){ 
                Rank tmp = rankList.get(i);
                text += tmp.getName() + "," + tmp.getScore() + "\r\n";
            }
            
            bw.write(text);
            System.out.println(text);
            bw.flush();
            bw.close();
            System.out.println("!!!!");
        }catch(IOException ex){
            Logger.getLogger(RankController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sort(){
        for(int i = 1; i < rankList.size(); i++){
            for(int j = 0; j < rankList.size() - i; j++){
                if(rankList.get(j).score < rankList.get(j + 1).score){
                   Rank tmp = rankList.get(j);
                   rankList.set(j, rankList.get(j + 1));
                   rankList.set(j + 1, tmp);
                }
            }
        }
    }
    
    
    
}
