import java.io.*;
import java.util.ArrayList;

public class SlovService implements ISlovService{
    BufferedReader read;
    BufferedWriter write;
    public void Show(ISlovar s){
        ISlovar slovar=s;
        try {
            read=new BufferedReader(new FileReader(slovar.getFile()));
            while (read.ready()){
                System.out.println(read.readLine());
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Delete(ISlovar s,String key){
        ArrayList<String> arr=new ArrayList<>();
        ISlovar slovar=s;
        File file=slovar.getFile();
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
    public String Serch(ISlovar s,String key){
        ISlovar slovar=s;
        File file=slovar.getFile();
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
        return "Значение не найдено";
    }
    public void Add(ISlovar s,String key, String value){
        String str="";
        ISlovar slovar=s;
        File file=slovar.getFile();
            try {
                write=new BufferedWriter(new FileWriter(file,true));
                write.write(key+"-"+value+System.lineSeparator());
                write.flush();
                write.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
