package system.dao;


import system.Check;
import org.springframework.stereotype.Component;
import system.model.ModelDictionary;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FileDAO implements ISlovService {
    BufferedReader read;
    BufferedWriter write;

    public List<ModelDictionary> all(ModelDictionary model) {
        try {
            read = new BufferedReader(new FileReader(Check.notFile(new File(model.getName()))));
            while (read.ready()) {
                System.out.println(read.readLine());
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void update(ModelDictionary model) {
        File file=Check.notFile(new File(model.getName()));
        try {
            List<String> content=new ArrayList<String>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
            for (int i = 0; i < content.size(); i++) {
                if(content.get(i).split("-")[0].equals(model.getKey())){
                    content.set(i, model.getKey()+"-"+model.getValue());
                    break;
                }
            }
            Files.write(file.toPath(), content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ModelDictionary model){
        File file=Check.notFile(new File(model.getName()));
        ArrayList<String> arr = new ArrayList<String>();
        try {
            read = new BufferedReader(new FileReader(file));
            while (read.ready()) {
                arr.add(read.readLine());
            }
            read.close();
            write = new BufferedWriter(new FileWriter(file));
            for (String str : arr) {
                if (!str.split("-")[0].equals(model.getKey())) {
                    write.write(str + System.lineSeparator());
                }
            }
            write.flush();
            write.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelDictionary> serch(ModelDictionary model) throws Exception {
        File file = Check.notFile(new File(model.getName()));
        String str = "";
        String key="",value="";
        try {
            read = new BufferedReader(new FileReader(file));
            while (read.ready()) {
                if ((str = read.readLine()).split("-")[0].trim().equals(model.getKey())) {
                    key=str.split("-")[0].trim();
                    value=str.split("-")[1].trim();
                    return Arrays.asList(new ModelDictionary(key,value));
                }
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new Exception("SerchError");
    }

    @Override
    public void add(ModelDictionary model) throws Exception {
        File file = Check.notFile(new File(model.getName()));
        unikalnKey(model);

        try {
            write = new BufferedWriter(new FileWriter(file, true));
            write.write(model.getKey() + "-" + model.getValue() + System.lineSeparator());
            write.flush();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unikalnKey(ModelDictionary model) throws Exception {
        BufferedReader read= null;
        String str=null;
        File file=null;
        try {
            read = new BufferedReader(new FileReader(model.getName()));
            while (read.ready()){
                if((str=read.readLine()).split("-")[0].equals(model.getKey())){
                    throw new Exception("Key not uniq");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


