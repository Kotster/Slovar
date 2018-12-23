import java.io.*;

public class Check {
    public void notFile(File f){
        try {
            if(!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean validKey(ISlovar slovar, String key){
        if (key.length()==slovar.getKeyLength()&&key.matches(slovar.getReg())) {
            return true;
        }
        return false;
    }
    public boolean UnikalnKey(ISlovar slovar, String key){
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
