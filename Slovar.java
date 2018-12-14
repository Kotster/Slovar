

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Slovar implements ISlov{
    private File file=new File("");
    private int KeyLength;
    private String reg;
    private BufferedReader read;
    private BufferedWriter write;

    public Slovar(File file, int KeyLength, String reg) {
        this.file=file;
        this.KeyLength=KeyLength;
        this.reg=reg;
    }

    public void Show(){
        try {
            read=new BufferedReader(new FileReader(file));
            while (read.ready()){
                System.out.println(read.readLine());
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Delete(String key){
        ArrayList<String> arr=new ArrayList<>();
        try {
            read=new BufferedReader(new FileReader(file));
            while (read.ready()){
                arr.add(read.readLine());
            }
            read.close();
            write=new BufferedWriter(new FileWriter(file));
            for (String s:arr
                 ) {
                if(!s.split("-")[0].equals(key)){
                    write.write(s+System.lineSeparator());
                }
            }
            write.flush();
            write.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String Serch(String key){
        String s="";
        try {
            read=new BufferedReader(new FileReader(file));
            while (read.ready()){
                if((s=read.readLine()).split("-")[0].equals(key)){
                    return s;
                }
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public void Add(String key, String value){
        if (key.length()==KeyLength&&key.matches(reg)) {
            try {
                write=new BufferedWriter(new FileWriter(file,true));
                //write.newLine();
                write.write(key+"-"+value+System.lineSeparator());
                write.flush();
                write.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
