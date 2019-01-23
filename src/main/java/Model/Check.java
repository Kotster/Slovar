package Model;

import Controller.SlovarModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Check {
    public static File notFile(File f){
            if(!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return f;
    }
    public static boolean validKey(SlovarModel slovar){
        if (slovar.getKey().length()==slovar.getKeyLength()&&slovar.getKey().matches(slovar.getReg())) {
            return true;
        }
        return false;
    }
}
