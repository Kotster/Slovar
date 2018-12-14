
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
            System.out.println("������� ���� � �������1 �� ������� �� �������� ������ 4 �������");
            file1 = new File(read.readLine());
            if(!file1.exists()) {
                file1.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("������� ���� � �������2 �� ������� �� ���� ������ 5 ��������");
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
            System.out.println("1:������� �������");
            System.out.println("2:������� �������");
            System.out.println("3:�������� �������� � �������");
            System.out.println("4:������� �������� �� �������");
            System.out.println("5:����� �������� �� �����");
            while (!(key=read.readLine()).equals("z")) {
                switch (key){
                    case "1":
                        System.out.println("�������1");
                        System.out.println("�������2");
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
                                        System.out.println("��� 1 ��� 2");
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
                            System.out.println("������ �������");
                        }
                        break;
                    case "3":
                        String Key,Value;
                        System.out.println("������� ����:");
                        Key=read.readLine();
                        System.out.println("������� ��������:");
                        Value=read.readLine();
                        obj.Add(Key,Value);
                        break;
                    case "4":
                        System.out.println("������� ����:");
                        Key=read.readLine();
                        obj.Delete(Key);
                        break;
                    case "5":
                        System.out.println("������� ����:");
                        Key=read.readLine();
                        System.out.println(obj.Serch(Key));
                        break;
                }
                System.out.println("1:������� �������");
                System.out.println("2:������� �������");
                System.out.println("3:�������� �������� � �������");
                System.out.println("4:������� �������� �� �������");
                System.out.println("5:����� �������� �� �����");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}