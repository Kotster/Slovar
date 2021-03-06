package com.example.Model;

import com.example.Controller.ISlovService;
import com.example.Controller.Slovar;

import java.io.*;

public class Check {
    public static File notFile(File f){
        try {
            if(!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }
    public static boolean validKey(ISlovService slovar, String key){
        if (key.length()==slovar.getKeyLength()&&key.matches(slovar.getReg())) {
            return true;
        }
        return false;
    }
    public static boolean UnikalnKey(Slovar slovar, String key){
        BufferedReader read= null;
        String str=null;
        try {
            read = new BufferedReader(new FileReader(slovar.getFile()));
            while (read.ready()){
                if((str=read.readLine()).split("-")[0].equals(key)){
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
