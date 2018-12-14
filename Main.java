
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static void clear(){
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
        Slovar sl= null;
        Slovar sl1=null;
        File file1= null;
        File file2= null;
        try {
            System.out.println("Введите путь к словарю1 со словами из латиницы длиной 4 символа");
            file1 = new File(read.readLine());
            if(!file1.exists()) {
                file1.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Введите путь к словарю2 со словами из цифр длиной 5 символов");
            file2 = new File(read.readLine());
            if(!file2.exists()) {
                file2.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sl = new Slovar(file1,4,"^[a-zA-Z]+$");
        sl1=new Slovar(file2,5,"^[0-9]+$");
        String key="";
        ISlov obj=null;
        try {
            System.out.println("1:Выбрать словарь");
            System.out.println("2:Вывести словарь");
            System.out.println("3:Добавить значение в словарь");
            System.out.println("4:Удалить значение из словаря");
            System.out.println("5:Найти значение по ключу");
            while (!(key=read.readLine()).equals("z")) {
                switch (key){
                    case "1":
                        System.out.println("Словарь1");
                        System.out.println("Словарь2");
                        boolean flag=true;
                        while (flag&&!(key=read.readLine()).equals("z")) {
                            switch (key){
                                case "1":
                                    obj=sl;
                                    flag=false;
                                    break;
                                case "2":
                                    obj=sl1;
                                    flag=false;
                                    break;
                                    default:
                                        System.out.println("Жми 1 или 2");
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                            }
                        }
                        break;
                    case "2":
                        if(obj!=null){
                            obj.Show();
                        }
                        else{
                            System.out.println("Выбери словарь");
                        }
                        break;
                    case "3":
                        String Key,Value;
                        System.out.println("Введите Ключ:");
                        Key=read.readLine();
                        System.out.println("Введите Значение:");
                        Value=read.readLine();
                        obj.Add(Key,Value);
                        break;
                    case "4":
                        System.out.println("Введите Ключ:");
                        Key=read.readLine();
                        obj.Delete(Key);
                        break;
                    case "5":
                        System.out.println("Введите Ключ:");
                        Key=read.readLine();
                        System.out.println(obj.Serch(Key));
                        break;
                }
                System.out.println("1:Выбрать словарь");
                System.out.println("2:Вывести словарь");
                System.out.println("3:Добавить значение в словарь");
                System.out.println("4:Удалить значение из словаря");
                System.out.println("5:Найти значение по ключу");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}