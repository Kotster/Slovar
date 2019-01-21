package Model;

import Controller.SlovarModel;

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
    public static boolean validKey(SlovarModel slovar, String key){
        if (key.length()==slovar.getKeyLength()&&key.matches(slovar.getReg())) {
            return true;
        }
        return false;
    }
//    public static boolean unikalnKey(Slovar slovar, String key){
//        BufferedReader read= null;
//        String str=null;
//        try {
//            read = new BufferedReader(new FileReader(slovar.getFile()));
//            while (read.ready()){
//                if((str=read.readLine()).split("-")[0].equals(key)){
//                    return false;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//    public class NotUniqException extends Exception{
//        public NotUniqException() {
//            super("The key is not uniq");
//        }
//    }

}
