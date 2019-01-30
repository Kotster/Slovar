package system;

import system.model.ModelDictionary;

import java.io.File;
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
    public static boolean validKey(ModelDictionary slovar){
        if (slovar.getKey().length()==slovar.getKeyLength()&&slovar.getKey().matches(slovar.getReg())) {
            return true;
        }
        return false;
    }
}
