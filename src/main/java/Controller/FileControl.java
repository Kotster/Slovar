package Controller;


import Model.Check;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FileControl implements ISlovService{
    BufferedReader read;
    BufferedWriter write;

    public List<String> all(SlovarModel model) {
        try {
            read = new BufferedReader(new FileReader(Check.notFile(new File(model.getName()))));
            while (read.ready()) {
                System.out.println(read.readLine());
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList("");
    }

    @Override
    public void update(SlovarModel model) {
        File file=Check.notFile(new File(model.getName()));
        try {
            List<String> content=new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
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
    public void delete(SlovarModel model) {
        File file=Check.notFile(new File(model.getName()));
        ArrayList<String> arr = new ArrayList<>();
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
    public List<String> serch(SlovarModel model) throws Exception {
        File file = Check.notFile(new File(model.getName()));
        String str = "";
        try {
            read = new BufferedReader(new FileReader(file));
            while (read.ready()) {
                if ((str = read.readLine()).split("-")[0].trim().equals(model.getKey())) {
                    return Arrays.asList(str);
                }
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new Exception("SerchError");
    }

    @Override
    public void add(SlovarModel model) {
        File file = Check.notFile(new File(model.getName()));

        try {
            write = new BufferedWriter(new FileWriter(file, true));
            write.write(model.getKey() + "-" + model.getValue() + System.lineSeparator());
            write.flush();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


