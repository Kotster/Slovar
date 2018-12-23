import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
        Check check=new Check();
        File file1=null;
        File file2=null;
        ResourceBundle resBun= ResourceBundle.getBundle("res", Locale.getDefault());
        try {
            System.out.println(new String(resBun.getString("MessagePathSlov1").getBytes("ISO8859-1"),"UTF-8"));
            check.notFile(file1=new File(read.readLine()));
            System.out.println(new String(resBun.getString("MessagePathSlov2").getBytes("ISO8859-1"),"UTF-8"));
            check.notFile(file2=new File(read.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Slovar slovar1=new Slovar(file1,4,"^[a-zA-Z]+$");
        Slovar slovar2=new Slovar(file2,5,"^[0-9]+$");

        SlovService service=new SlovService();
        View view=new View(check,slovar1,slovar2,service);
        view.main();
    }
}