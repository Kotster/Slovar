package com.example.Controller;

import com.example.Model.Check;

import java.io.*;
import java.util.ArrayList;

public class Slovar implements ISlovService {
    private File file;
    private int KeyLength;
    private String reg;
    BufferedReader read;
    BufferedWriter write;

    public Slovar(File file, int KeyLength, String reg) {
        this.file=file;
        this.KeyLength=KeyLength;
        this.reg=reg;
    }

    public File getFile() {
        return file;
    }

    public int getKeyLength() {
        return KeyLength;
    }

    public String getReg() {
        return reg;
    }

    public void Show(){
        try {
            read=new BufferedReader(new FileReader(Check.notFile(file)));
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
        File file=Check.notFile(this.file);
        try {
            read=new BufferedReader(new FileReader(file));
            while (read.ready()){
                arr.add(read.readLine());
            }
            read.close();
            write=new BufferedWriter(new FileWriter(file));
            for (String str:arr
            ) {
                if(!str.split("-")[0].equals(key)){
                    write.write(str+System.lineSeparator());
                }
            }
            write.flush();
            write.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String Serch(String key){
        File file=Check.notFile(this.file);
        String str="";
        try {
            read=new BufferedReader(new FileReader(file));
            while (read.ready()){
                if((str=read.readLine()).split("-")[0].equals(key)){
                    return str;
                }
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Key not find";
    }
    public void Add(String key, String value) throws NotUniqException{
        if (Check.UnikalnKey(this,key)) {
            String str="";
            File file=Check.notFile(this.file);
            try {
                write=new BufferedWriter(new FileWriter(file,true));
                write.write(key+"-"+value+System.lineSeparator());
                write.flush();
                write.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            throw new NotUniqException();
    }
    public class NotUniqException extends Exception{
        public NotUniqException() {
            super("The key is not uniq");
        }
    }
}
