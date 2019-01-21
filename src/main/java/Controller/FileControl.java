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
    File file=new File("resources");

    @Autowired
    private SessionFactory sessionFactory;

    public List<String> all() {
        try {
            read = new BufferedReader(new FileReader(Check.notFile(file)));
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
    public void update(String Key, String Value) {
        String str="";
        try {
            List<String> content=new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
            for (int i = 0; i < content.size(); i++) {
                if(content.get(i).split("-")[0].equals(Key)){
                    content.set(i, Key+" - "+Value);
                    break;
                }
            }
            Files.write(file.toPath(), content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String key) {
        ArrayList<String> arr = new ArrayList<>();
        File file = Check.notFile(this.file);
        try {
            read = new BufferedReader(new FileReader(file));
            while (read.ready()) {
                arr.add(read.readLine());
            }
            read.close();
            write = new BufferedWriter(new FileWriter(file));
            for (String str : arr) {
                if (!str.split("-")[0].equals(key)) {
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
    public List<String> serch(String key) throws Exception {
        File file = Check.notFile(this.file);
        String str = "";
        try {
            read = new BufferedReader(new FileReader(file));
            while (read.ready()) {
                if ((str = read.readLine()).split("-")[0].equals(key)) {
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
    public void add(String key, String value) {

        String str = "";
        File file = Check.notFile(this.file);

        try {
            write = new BufferedWriter(new FileWriter(file, true));
            write.write(key + "-" + value + System.lineSeparator());
            write.flush();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


