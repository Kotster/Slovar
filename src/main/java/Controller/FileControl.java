package Controller;


import Model.Check;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
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

    public List<String> All() {
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
    public void Update(String Key, String Value) {

    }

    public void Delete(String key) {
        ArrayList<String> arr = new ArrayList<>();
        File file = Check.notFile(this.file);
        try {
            read = new BufferedReader(new FileReader(file));
            while (read.ready()) {
                arr.add(read.readLine());
            }
            read.close();
            write = new BufferedWriter(new FileWriter(file));
            for (String str : arr
                    ) {
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

    public List<String> Serch(String key) {
        File file = Check.notFile(this.file);
        String str = "";
        try {
            read = new BufferedReader(new FileReader(file));
            while (read.ready()) {
                if ((str = read.readLine()).split("-")[0].equals(key)) {
                    return Arrays.asList("");
                }
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList("");
    }

    public void Add(String key, String value) {

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


