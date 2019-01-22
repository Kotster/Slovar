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
    public static boolean validKey(SlovarModel slovar){
        if (slovar.getKey().length()==slovar.getKeyLength()&&slovar.getKey().matches(slovar.getReg())) {
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
